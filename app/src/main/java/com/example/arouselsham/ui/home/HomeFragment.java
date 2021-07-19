package com.example.arouselsham.ui.home;

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
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.FragmentHomeBinding;
import com.example.arouselsham.pojo.model.maleModels.MenuSection;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        homeViewModel.getAllMeals().observe(getViewLifecycleOwner(), menus -> {

        });

        CategoriesAdapter adapter = new CategoriesAdapter(getContext());
        setUpRecyclerView(adapter);

        homeViewModel.getMenuTags().observe(getViewLifecycleOwner(), new Observer<List<MenuSection>>() {
            @Override
            public void onChanged(List<MenuSection> menuSections) {
                MenuSection section = menuSections.get(0);
                adapter.setmSection(section);
                binding.recyclerCategories.setAdapter(adapter);

            }
        });

        return root;
    }

    void setUpRecyclerView(CategoriesAdapter adapter) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setSpanCount(2);
        binding.recyclerCategories.setLayoutManager(gridLayoutManager);
        binding.recyclerCategories.setAdapter(adapter);
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
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            default:
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}