package com.example.arouselsham.ui.savedAddresses;

import static com.example.arouselsham.pojo.Common.CUSTOMER_INFO_REFERENCE;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.SavedAddressesFragmentBinding;
import com.example.arouselsham.pojo.model.Customer;
import com.example.arouselsham.pojo.model.UserAddress;
import com.example.arouselsham.ui.accountInfo.FirebaseInterface;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SavedAddressesFragment extends Fragment implements
        AddressesAdapter.OnItemClickListener, FirebaseInterface {
    private static final String TAG = "SavedAddressesFragment";
    private SavedAddressesViewModel mViewModel;
    private NavController navController;
    private SavedAddressesFragmentBinding binding;

    private Customer customer;
    private AddressesAdapter addressesAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SavedAddressesViewModel.class);
        binding = SavedAddressesFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel.getCurrentUser(this);
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
        inflater.inflate(R.menu.saved_addresses_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavDirections navDirections;
        if (item.getItemId() == R.id.navigation_add) {
            navDirections = SavedAddressesFragmentDirections
                    .actionSavedAddressesFragmentToAddAddressFragment(new UserAddress());
            navController.navigate(navDirections);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onItemClick(int pos) {
        navController.navigate(SavedAddressesFragmentDirections
                .actionSavedAddressesFragmentToAddAddressFragment(customer.getAddresses().get(pos)));
    }

    private void showLayoutIfListIsEmpty() {
        if (customer.getAddresses().size() != 0)
            binding.emptyLayout.setVisibility(View.GONE);
        else
            binding.emptyLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemDeleted(int pos) {
        deleteAddress(pos);
    }

    @SuppressLint("RestrictedApi")
    private void deleteAddress(int pos) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(CUSTOMER_INFO_REFERENCE);
        addressesAdapter.deleteItemOn(pos);
        customer.setAddresses(addressesAdapter.getAddresses());
        reference.child(AuthUI.getInstance().getAuth().getCurrentUser().getUid())
                .setValue(customer)
                .addOnSuccessListener(unused -> {
                    showLayoutIfListIsEmpty();
                })
                .addOnFailureListener(e -> Toast.makeText(getActivity(), "Error: " + e.getMessage(),
                        Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onDataReceived(Customer customer, DataSnapshot snapshot) {
        addressesAdapter = new AddressesAdapter(getContext(), this);
        addressesAdapter.setAddresses(customer.getAddresses());
        this.customer = customer;
        showLayoutIfListIsEmpty();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(addressesAdapter);
    }
}