package com.example.arouselsham.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.databinding.CartFragmentBinding;
import com.example.arouselsham.pojo.db.entities.Cart;

import java.util.List;

import io.reactivex.rxjava3.annotations.Nullable;

public class CartFragment extends Fragment implements CartAdapter.OnItemClickListener, OnCartChange {
    private static final String TAG = "CartFragment";
    private CartViewModel cartViewModel;
    private CartFragmentBinding binding;
    private CartAdapter adapter;
    private NavController navController;

    public static CartFragment getInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding = CartFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new CartAdapter(this);

        binding.cartRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.cartRecycler.setAdapter(adapter);

        cartViewModel.getCartList().observe(getViewLifecycleOwner(), carts -> {
            adapter.setCart(carts);
            binding.setTotalPrice(0.0);
            showEmptyIfList0();
            getTotalPrice(carts);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                cartViewModel.removeOneCart(adapter.getCurrentCart(viewHolder
                        .getBindingAdapterPosition()));
                Toast.makeText(getContext(), "Item deleted.", Toast.LENGTH_SHORT).show();
            }
        })
                .attachToRecyclerView(binding.cartRecycler);

        binding.cardProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections navDirections = CartFragmentDirections.actionNavigationCartToSavedAddressesFragment2(true);
                navController.navigate(navDirections);
                Toast.makeText(getContext(), "cli", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    private void showEmptyIfList0() {
        if (adapter.getItemCount() == 0) {
            binding.emptyList.setVisibility(View.VISIBLE);
            binding.cardProceed.setVisibility(View.GONE);
            binding.toatlPriceLayout.setVisibility(View.GONE);
        } else {
            binding.emptyList.setVisibility(View.GONE);
            binding.cardProceed.setVisibility(View.VISIBLE);
            binding.toatlPriceLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter.getItemCount() == 0)
            binding.image.playAnimation();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onItemClick(int position) {
        ///TODO: go to deiteals fragment
        /*NavDirections direction = CartFragmentDirections.actionNavigationOrdersToAddAddressFragment();
        navController.navigate(direction);*/
    }

    @Override
    public void onRemoveItem(int position) {
        cartViewModel.removeOneCart(adapter.getCurrentCart(position));
        adapter.removeCartAt(position);
        showEmptyIfList0();
    }

    @Override
    public void onPlus(int position) {
        Cart cart = adapter.getCurrentCart(position);
        int newNumber = cart.getMealNum() + 1;
        Double singleItemPrice = cart.getPrice() / cart.getMealNum();
        cart.setMealNum(newNumber);
        cart.setPrice(singleItemPrice * newNumber);
        adapter.updateCart(cart, position);
        cartViewModel.updateCart(cart);
        onCartUpdate();
    }

    @Override
    public void onMinus(int position) {
        Cart cart = adapter.getCurrentCart(position);
        int newNumber = cart.getMealNum() - 1;
        if (newNumber >= 1) {
            Double singleItemPrice = cart.getPrice() / cart.getMealNum();
            cart.setMealNum(newNumber);
            cart.setPrice(singleItemPrice * newNumber);
            adapter.updateCart(cart, position);
            cartViewModel.updateCart(cart);
            onCartUpdate();
        } else {
            Toast.makeText(getContext(), "The minimum number of order is 1 !",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCartUpdate() {
        cartViewModel.getCartList().observe(getViewLifecycleOwner(), this::getTotalPrice);
    }

    private void getTotalPrice(List<Cart> carts) {
        binding.setTotalPrice(0.0);
        for (int i = 0; i < carts.size(); i++) {
            binding.setTotalPrice(binding.getTotalPrice() + carts.get(i).getPrice());
        }
    }
}