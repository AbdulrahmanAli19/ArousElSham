package com.example.arouselsham.ui.details;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.DetailsFragmentBinding;
import com.example.arouselsham.pojo.Common;
import com.example.arouselsham.pojo.db.entities.Cart;
import com.example.arouselsham.pojo.db.entities.Favorite;
import com.example.arouselsham.pojo.model.maleModels.KeyValue;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.example.arouselsham.pojo.model.maleModels.MenuTopping;
import com.example.arouselsham.pojo.model.maleModels.PriceOption;
import com.example.arouselsham.ui.SelectorAdapter;
import com.example.arouselsham.ui.ToppingAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class DetailsFragment extends Fragment implements SelectorAdapter.ListItemClickListener,
        ToppingAdapter.ItemClickListener, OnLikeListener, View.OnClickListener {

    private static final String TAG = "DetailsFragment";

    private DetailsViewModel detailsViewModel;
    private DetailsFragmentBinding binding;
    private Meal meal;
    private KeyValue prices;
    private SelectorAdapter selectorAdapter;

    private List<MenuTopping> selectedToppings = new ArrayList<>();

    public static DetailsFragment newInstance() {
        return new DetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        detailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        binding = DetailsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        meal = DetailsFragmentArgs.fromBundle(getArguments()).getMeal();

        binding.setMeal(meal);
        Picasso.get().load(meal.getImageUrl()).into(binding.mainImage);

        detailsViewModel.getAllFavorites().observe(getViewLifecycleOwner(), favorites -> {
            for (int i = 0; i < favorites.size(); i++)
                if (favorites.get(i).getFirebaseID().trim() == meal.getId().trim()
                        || favorites.get(i).getFirebaseID().equals(meal.getId())) {
                    binding.likeBtn.setLiked(true);
                }
        });

        binding.likeBtn.setOnLikeListener(this);
        binding.plusCard.setOnClickListener(this);
        binding.minusCard.setOnClickListener(this);
        binding.addToCartCard.setOnClickListener(this);
        prices = new KeyValue(meal.getPrice().keySet(), meal.getPrice().values());
        binding.setToppingsPrice(0.0);
        binding.setNumberOfItems(1);
        setUpPriceSelectorRecycler();
        setUpToppingsRecycler();

        return root;
    }

    private void setUpToppingsRecycler() {
        if (meal.getToppings().size() == 0)
            binding.toppingRecycler.setVisibility(View.GONE);

        else {
            ToppingAdapter toppingAdapter = new ToppingAdapter(getContext(), meal.getToppings(),
                    false, this);
            binding.toppingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.toppingRecycler.setAdapter(toppingAdapter);
        }
    }

    private void setUpPriceSelectorRecycler() {
        binding.setMainPrice(prices.getValue().get(0));

        if (prices.getValue().size() > 0 && !prices.getKey().get(0).equals(Common.priceByOne)) {
            List<PriceOption> priceOptions = new ArrayList<>();
            for (int i = 0; i < prices.getValue().size(); i++) {
                priceOptions.add(new PriceOption(prices.getKey().get(i),
                        prices.getValue().get(i)));
            }
            selectorAdapter = new SelectorAdapter(getContext(), priceOptions, this);
            binding.sizeSelectorRecycler.setLayoutManager(new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL, false));
            binding.sizeSelectorRecycler.setAdapter(selectorAdapter);

        } else {
            binding.sizeSelectorRecycler.setVisibility(View.GONE);

        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onItemClick(int position, double price) {
        binding.setMainPrice(price);
        selectorAdapter.changeBackground(position);
    }

    @Override
    public void onItemCLickListener(int position, double price, CheckBox checkBox, MenuTopping menuTopping) {
        checkBox.setChecked(!checkBox.isChecked());

        if (checkBox.isChecked()) {
            binding.setToppingsPrice(binding.getToppingsPrice() + price);
            selectedToppings.add(menuTopping);
        } else {
            binding.setToppingsPrice(binding.getToppingsPrice() - price);
            selectedToppings.remove(menuTopping);
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.plusCard:
                binding.setNumberOfItems(binding.getNumberOfItems() + 1);
                break;

            case R.id.minusCard:
                if (binding.getNumberOfItems() == 1)
                    Toast.makeText(getContext(), "The minimum number of order is 1!",
                            Toast.LENGTH_LONG).show();
                else {
                    binding.setNumberOfItems(binding.getNumberOfItems() - 1);
                }
                break;

            case R.id.addToCartCard:

                Double price = (binding.getMainPrice() + binding.getToppingsPrice()) * binding.getNumberOfItems();
                Cart cart = new Cart(meal.getId(),meal.getEnName(),price, binding.getNumberOfItems(), selectedToppings);
                detailsViewModel.insertToCart(cart);
                makeSnackBar("You have added " + binding.getNumberOfItems() + " for " +
                        (binding.getNumberOfItems() * binding.getMainPrice()) + " EGP");
                break;
        }

    }

    private void makeSnackBar(String text) {
        Snackbar.make(getActivity().findViewById(android.R.id.content),
                text, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void liked(LikeButton likeButton) {
        detailsViewModel.getAllFavorites().observe(getViewLifecycleOwner(), favorites -> {
            for (int i = 0; i < favorites.size(); i++) {
                Log.d(TAG, "LICKED CALLED: IT HAS "+favorites.size());
                Log.d(TAG, "fav = : "+favorites.get(i).getFirebaseID());
                if (favorites.get(i).getFirebaseID().trim() == meal.getId().trim()
                        ||favorites.get(i).getFirebaseID().equals(meal.getId())) {
                    break;
                }
            }
            return;
        });
        Favorite favorite = new Favorite(meal.getId());
        detailsViewModel.insertToFavorite(favorite);
    }

    @Override
    public void unLiked(LikeButton likeButton) {
        detailsViewModel.getAllFavorites().observe(getViewLifecycleOwner(), favorites -> {
            for (int i = 0; i < favorites.size(); i++) {
                Log.d(TAG, "fav = : "+favorites.get(i).getFirebaseID());
                if (favorites.get(i).getFirebaseID().trim() == meal.getId().trim()
                        ||favorites.get(i).getFirebaseID().equals(meal.getId())) {
                    Favorite favorite = favorites.get(i);
                    detailsViewModel.deleteFromFavorite(favorite);
                    Log.d(TAG, "UNLICKED CALLED: REMOVED NOW IT HAS "+favorites.size());
                }
            }
        });


    }

}