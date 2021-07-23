package com.example.arouselsham.ui.section;

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

import com.example.arouselsham.MainActivity;
import com.example.arouselsham.databinding.SectionFragmentBinding;
import com.example.arouselsham.pojo.model.maleModels.Meal;

import java.util.ArrayList;
import java.util.List;

public class SectionFragment extends Fragment implements MealAdapter.OnItemClickListener {
    private static final String TAG = "SectionFragment";
    private NavController navController;
    private SectionViewModel sectionViewModel;
    private SectionFragmentBinding binding;
    private List<Meal> meals;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SectionFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sectionViewModel = new ViewModelProvider(this).get(SectionViewModel.class);

        String section = SectionFragmentArgs.fromBundle(getArguments()).getSection();
        MealAdapter adapter = new MealAdapter(getContext(), this);


        sectionViewModel.getMealsBySection(section).observe(getViewLifecycleOwner(), menus -> {
            meals = new ArrayList<>();
            for (int i = 0; i < menus.size(); i++)
                meals.add(menus.get(i).getMeal());

            adapter.setMeals(meals);
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            binding.mealsRecyclerview.setLayoutManager(manager);
            binding.mealsRecyclerview.setAdapter(adapter);
        });


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onClick(int position) {
        SectionFragmentDirections.ActionSectionFragmentToDetailsFragment action =
                SectionFragmentDirections.actionSectionFragmentToDetailsFragment(meals.get(position));
        navController.navigate(action);

    }
}