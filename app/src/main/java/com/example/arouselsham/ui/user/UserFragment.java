package com.example.arouselsham.ui.user;

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

import com.example.arouselsham.databinding.UserFragmentBinding;
import com.example.arouselsham.pojo.model.CustomerInfoModel;
import com.google.firebase.auth.FirebaseAuth;

public class UserFragment extends Fragment implements FirebaseInterface {
    private static final String TAG = "UserFragment";

    private SigningOutListener listener;
    private UserFragmentBinding binding;

    private NavController navController;

    private UserViewModel userViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = UserFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getCurrentUser(this);
        listener = (SigningOutListener) getActivity();


        binding.btnSignOut.setOnClickListener(v -> signOut());
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    private void signOut() {
        listener.onSignOut();
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    public void onDataReceived(CustomerInfoModel customerInfoModel) {
        binding.setCustomer(customerInfoModel);
    }
}