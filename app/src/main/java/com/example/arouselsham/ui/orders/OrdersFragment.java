package com.example.arouselsham.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.UserFragmentBinding;
import com.example.arouselsham.ui.home.HomeFragment;

public class OrdersFragment extends Fragment {
    private static final String TAG = "UserFragment";

    private UserFragmentBinding binding;
    private NavController navController;
    private OrdersViewModel ordersViewModel;

    public static OrdersFragment getInstance(){
        return new OrdersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = UserFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ordersViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.order_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}