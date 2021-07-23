package com.example.arouselsham.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.FragmentHomeBinding;
import com.example.arouselsham.pojo.db.entities.MenuTags;
import com.example.arouselsham.pojo.model.maleModels.MenuSection;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeFragment extends Fragment implements CategoriesAdapter.OnItemClickListener {
    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private NavController navController;
    private MenuSection section;
    private SetViewV setViewV;
    private SharedPreferences preferences;
    private SharedPreferences.Editor prefEditor;
    private List<MenuTags> tagsList;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        preferences = getContext().getSharedPreferences("the tags", Context.MODE_PRIVATE);
        prefEditor = preferences.edit();

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setViewV = (SetViewV) getContext();

        homeViewModel.getAllMenuTags().observe(getViewLifecycleOwner(), menuTags -> {
            if (menuTags.size() > 0) {
                this.tagsList = menuTags;
                CategoriesAdapter adapter = setUpRecyclerView();
                adapter.setTags(menuTags);
                binding.recyclerCategories.setAdapter(adapter);
            }
        });

        float menuVersion = preferences.getFloat("menuVersion", 0.0f);

        homeViewModel.getMenuTagsFromFirestore().observe(getViewLifecycleOwner(), menuSections -> {
            section = menuSections.get(0);
            if (menuVersion != section.getMenuVersion()) {
                Log.d(TAG, "SAVE DATA TO DATA BASE: ");
                prefEditor.putFloat("menuVersion", section.getMenuVersion());
                prefEditor.apply();
                saveMenuToDatabase();
                saveMenuTagsToDatabase();
            }

        });


        return root;
    }

    private void saveMenuTagsToDatabase() {
        homeViewModel.deleteAllMenuTags();
        for (int i = 0; i < section.getTagsMap().size(); i++) {
            String imageUrl = section.getTagsMap().get(i).get("imageUrl");
            String enName = section.getTagsMap().get(i).get("enName");
            String arName = section.getTagsMap().get(i).get("arName");
            MenuTags menuTags = new MenuTags(imageUrl, enName, arName);
            homeViewModel.insertMenuTags(menuTags);
        }
    }

    private void saveMenuToDatabase() {
        homeViewModel.deleteAllMeals();
        for (int i = 0; i < section.getTagsMap().size(); i++) {

            homeViewModel.getMealsFromFirestore(section.getTagsMap().get(i).get("enName"))
                    .observe(getViewLifecycleOwner(), meals -> {

                        for (int j = 0; j < meals.size(); j++) {
                            com.example.arouselsham.pojo.db.entities.Menu menu
                                    = new com.example.arouselsham.pojo.db.entities.Menu(meals.get(j));
                            menu.setId(meals.get(j).getId());

                            homeViewModel.insertMeal(menu);
                        }

                    });
        }

    }

    private CategoriesAdapter setUpRecyclerView() {
        CategoriesAdapter adapter = new CategoriesAdapter(getContext(), this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSpanCount(2);
        binding.recyclerCategories.setLayoutManager(gridLayoutManager);
        binding.recyclerCategories.setAdapter(adapter);
        return adapter;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        NavDirections action;
        switch (item.getItemId()) {
            case R.id.navigation_settings:
                action = HomeFragmentDirections.actionNavigationHomeToNavigationSettings();
                navController.navigate(action);
                break;
            case R.id.navigation_about:
                action = HomeFragmentDirections.actionNavigationHomeToNavigationAbout();
                navController.navigate(action);
                break;
            case R.id.navigation_search:
                action = HomeFragmentDirections.actionNavigationHomeToNavigationSearch();
                navController.navigate(action);
                break;
            default:
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(int position) {
        String tag = section.getTagsMap().get(position).get("enName");
        HomeFragmentDirections.ActionNavigationHomeToSectionFragment action =
                HomeFragmentDirections.actionNavigationHomeToSectionFragment(tag);

        navController.navigate(action);
    }

    public interface SetViewV {
        void onClick();
    }
}