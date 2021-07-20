package com.example.arouselsham.ui.home;

import android.content.Context;
import android.content.Intent;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.arouselsham.R;
import com.example.arouselsham.SecondActivity;
import com.example.arouselsham.databinding.FragmentHomeBinding;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.example.arouselsham.pojo.model.maleModels.MenuSection;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements CategoriesAdapter.OnItemClickListener{
    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private NavController navController;
    private MenuSection section;
    private SetViewV setViewV;
    private final SharedPreferences preferences = getContext().getSharedPreferences("the tags",Context.MODE_PRIVATE);
    private final SharedPreferences.Editor prefEditor = preferences.edit();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setViewV = (SetViewV) getContext();
        homeViewModel.getAllMeals().observe(getViewLifecycleOwner(), menus -> {

            if (menus.size() > 0) {
                Toast.makeText(getActivity(), "Menu has" + menus.size(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "menu is empty has " +
                        menus.size(), Toast.LENGTH_SHORT).show();
            }

        });

        CategoriesAdapter adapter = setUpRecyclerView();

        float menuVersion =  preferences.getFloat("menuVersion", 0.0f);

        homeViewModel.getMenuTags().observe(getViewLifecycleOwner(), menuSections -> {
            section = menuSections.get(0);
            if (menuVersion == section.getMenuVersion()) {
                Log.d(TAG, "GET DATA FROM DATA BASE: ");
                adapter.setmSection(section);
                binding.recyclerCategories.setAdapter(adapter);

            } else {
                Log.d(TAG, "SAVE DATA TO DATA BASE: ");

                prefEditor.putFloat("menuVersion", section.getMenuVersion());
                prefEditor.apply();
                adapter.setmSection(section);
                binding.recyclerCategories.setAdapter(adapter);
                saveMenuToDatabase();
            }

        });


        return root;
    }

    private void saveMenuToDatabase() {
        for (int i = 0; i < section.getTagsMap().size(); i++) {

            homeViewModel.getMeals(section.getTagsMap().get(i).get("enName"))
                    .observe(getViewLifecycleOwner(), meals -> {

                        for (int j = 0; j < meals.size(); j++) {
                            com.example.arouselsham.pojo.db.entities.Menu menu
                                    = new com.example.arouselsham.pojo.db.entities.Menu(meals.get(j));
                            menu.setId(meals.get(j).getId());

                            homeViewModel.insert(menu);
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
        switch (item.getItemId()) {
            case R.id.navigation_settings:
                navController.navigate(R.id.action_navigation_home_to_navigation_settings);
                break;
            case R.id.navigation_about:
                navController.navigate(R.id.action_navigation_home_to_navigation_about);
                break;
            case R.id.navigation_search:
                navController.navigate(R.id.action_navigation_home_to_navigation_search);
                break;
            default:
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(int position) {
        //String tag = section.getTagsMap().get(position).get("enName");
        FirebaseFirestore.getInstance()
                .collection("Menu")
                .document(section.getTagsMap().get(position).get("enName"))
                .collection("MenuItems")
                .get()
                .addOnCompleteListener(task1 -> {
                    List<Meal> meals = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task1.getResult()) {
                        meals.add(document.toObject(Meal.class));
                        Log.d(TAG, "onComplete: called");
                    }

                    Intent intent = new Intent(getContext(), SecondActivity.class);
                    intent.putExtra("Meals", (Serializable) meals);
                    getContext().startActivity(intent);
                });

    }

    public interface SetViewV {
        void onClick();
    }
}