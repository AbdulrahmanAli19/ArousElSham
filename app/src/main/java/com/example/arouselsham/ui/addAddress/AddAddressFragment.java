package com.example.arouselsham.ui.addAddress;

import static com.example.arouselsham.pojo.Common.CUSTOMER_INFO_REFERENCE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.AddAddressFragmentBinding;
import com.example.arouselsham.pojo.model.Customer;
import com.example.arouselsham.pojo.model.UserAddress;
import com.example.arouselsham.ui.accountInfo.FirebaseInterface;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class AddAddressFragment extends Fragment implements MaterialSpinner.OnItemSelectedListener,
        View.OnClickListener, FirebaseInterface {

    private static final String TAG = AddAddressFragment.class.toString();

    private NavController navController;
    private AddAddressViewModel addAddressViewModel;
    private AddAddressFragmentBinding binding;

    private FirebaseInterface firebaseInterface;
    private UserAddress address;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddAddressFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        firebaseInterface = this;
        address = AddAddressFragmentArgs.fromBundle(getArguments()).getAddress();
        if (address.getArea() != null)
            binding.setAddress(address);
        addAddressViewModel = new ViewModelProvider(this).get(AddAddressViewModel.class);
        binding.spinner.setItems(getText(R.string.apartment), getText(R.string.office), getText(R.string.villa));
        binding.spinner.setOnItemSelectedListener(this);
        binding.cardSaveAddress.setOnClickListener(this);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        if (item == getString(R.string.apartment)) {
            settAllTextsEmpty();
            binding.buildingNumber.setVisibility(View.VISIBLE);
            binding.floorNumber.setVisibility(View.VISIBLE);
            binding.aptNumber.setVisibility(View.VISIBLE);
            binding.buildingNumber.setHint(R.string.building_number);
            binding.aptNumber.setHint(R.string.apartment_number);
            binding.aptNumber.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (item == getString(R.string.office)) {
            settAllTextsEmpty();
            binding.buildingNumber.setVisibility(View.VISIBLE);
            binding.floorNumber.setVisibility(View.VISIBLE);
            binding.aptNumber.setVisibility(View.VISIBLE);
            binding.aptNumber.setHint(R.string.office);
            binding.aptNumber.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        } else if (item == getString(R.string.villa)) {
            settAllTextsEmpty();
            binding.buildingNumber.setVisibility(View.VISIBLE);
            binding.buildingNumber.setHint(R.string.villa);
            binding.floorNumber.setVisibility(View.GONE);
            binding.aptNumber.setVisibility(View.GONE);
        }

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cardSaveAddress) {
            if (TextUtils.isEmpty(binding.area.getEditText().getText().toString())) {
                binding.area.getEditText().setError(getString(R.string.cant_be_empty));
                return;
            }

            if (TextUtils.isEmpty(binding.streetName.getEditText().getText().toString())) {
                binding.streetName.getEditText().setError(getString(R.string.cant_be_empty));
                return;
            }

            if (TextUtils.isEmpty(binding.buildingNumber.getEditText().getText().toString())
                    && binding.buildingNumber.getVisibility() == View.VISIBLE) {
                binding.buildingNumber.getEditText().setError(getString(R.string.cant_be_empty));
                return;
            }

            if (TextUtils.isEmpty(binding.aptNumber.getEditText().getText().toString())
                    && binding.aptNumber.getVisibility() == View.VISIBLE) {
                binding.aptNumber.getEditText().setError(getString(R.string.cant_be_empty));
                return;
            }

            if (TextUtils.isEmpty(binding.floorNumber.getEditText().getText().toString())
                    && binding.floorNumber.getVisibility() == View.VISIBLE) {
                binding.floorNumber.getEditText().setError(getString(R.string.cant_be_empty));
                return;
            }

            if (TextUtils.isEmpty(binding.landmark.getEditText().getText().toString())
                    && binding.landmark.getVisibility() == View.VISIBLE) {
                binding.landmark.getEditText().setError(getString(R.string.cant_be_empty));
                return;
            }

            if (TextUtils.isEmpty(binding.phoneNumber.getEditText().getText().toString())) {
                binding.phoneNumber.getEditText().setError(getString(R.string.cant_be_empty));
                return;
            }

            address = new UserAddress(
                    binding.area.getEditText().getText().toString(),
                    binding.streetName.getEditText().getText().toString(),
                    binding.landmark.getEditText().getText().toString(),
                    binding.spinner.getItems().get(binding.spinner.getSelectedIndex()).toString(),
                    binding.buildingNumber.getEditText().getText().toString(),
                    binding.phoneNumber.getEditText().getText().toString());

            if (binding.floorNumber.getVisibility() == View.VISIBLE)
                address.setFloorNumber(binding.buildingNumber.getEditText().getText().toString());

            if (binding.aptNumber.getVisibility() == View.VISIBLE)
                address.setAptNumber(binding.aptNumber.getEditText().getText().toString());

            if (TextUtils.isEmpty(binding.landLineNumber.getEditText().getText().toString()))
                address.setLandLineNumber(binding.landLineNumber.getEditText().getText().toString());

            addAddressViewModel.getCurrentUser(firebaseInterface);
        }
    }

    private void settAllTextsEmpty() {
        binding.buildingNumber.getEditText().setText("");
        binding.floorNumber.getEditText().setText("");
        binding.aptNumber.getEditText().setText("");
        binding.landmark.getEditText().setText("");
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onDataReceived(Customer customer, DataSnapshot snapshot) {
        customer.getAddresses().add(address);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(CUSTOMER_INFO_REFERENCE);

        reference.child(AuthUI.getInstance().getAuth().getCurrentUser().getUid())
                .setValue(customer)
                .addOnSuccessListener(unused -> Toast.makeText(getActivity(), "Saved.",
                        Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(getActivity(), "Error: " + e.getMessage(),
                        Toast.LENGTH_SHORT).show());
    }
}