package com.example.arouselsham.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.arouselsham.R;
import com.example.arouselsham.databinding.FavoriteFragmentBinding;
import com.example.arouselsham.pojo.db.entities.Favorite;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.example.arouselsham.ui.section.MealAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FavoriteFragment extends Fragment implements MealAdapter.OnItemClickListener {
    private static final String TAG = "FavoriteFragment";
    private NavController navController;
    private FavoriteFragmentBinding binding;
    private FavoriteViewModel favoriteViewModel;
    private List<Favorite> favorites;
    private final List<Meal> meals = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        binding = FavoriteFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MealAdapter adapter = new MealAdapter(getContext(), this);
        binding.favoriteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.favoriteRecyclerView.setAdapter(adapter);


        favoriteViewModel.getFavorites().observe(getViewLifecycleOwner(), favorites1 -> {
            favorites = favorites1;
            if (favorites != null) {
                try {
                    getMealsById();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                adapter.setMeals(meals);
                if (adapter.getItemCount() == 0) {
                    binding.emptyList.setVisibility(View.VISIBLE);
                } else {
                    binding.emptyList.setVisibility(View.GONE);
                }
            }
        });


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void getMealsById() throws ExecutionException, InterruptedException {
        meals.clear();
        for (int i = 0; i < favorites.size(); i++) {
            meals.add(favoriteViewModel.getMealById(favorites.get(i).getFirebaseID()).getMeal());
        }
    }

    @Override
    public void onClick(int position) {
        FavoriteFragmentDirections.ActionNavigationOffersToDetailsFragment action =
                FavoriteFragmentDirections.actionNavigationOffersToDetailsFragment(meals.get(position));
        navController.navigate(action);
    }


}