package com.example.arouselsham.ui.addAddress;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.AddAddressFragmentBinding;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class AddAddressFragment extends Fragment implements MaterialSpinner.OnItemSelectedListener,
        View.OnClickListener {
    private NavController navController;

    private AddAddressViewModel addAddressViewModel;

    private AddAddressFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddAddressFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
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
            binding.buildingNumber.setVisibility(View.VISIBLE);
            binding.floorNumber.setVisibility(View.VISIBLE);
            binding.aptNumber.setVisibility(View.VISIBLE);
            binding.buildingNumber.setHint(R.string.building_number);
            binding.aptNumber.setHint(R.string.apartment_number);
            binding.aptNumber.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (item == getString(R.string.office)) {
            binding.buildingNumber.setVisibility(View.VISIBLE);
            binding.floorNumber.setVisibility(View.VISIBLE);
            binding.aptNumber.setVisibility(View.VISIBLE);
            binding.aptNumber.setHint(R.string.office);
            binding.aptNumber.getEditText().setInputType(InputType.TYPE_CLASS_TEXT);
        } else if (item == getString(R.string.villa)) {
            binding.buildingNumber.setVisibility(View.VISIBLE);
            binding.buildingNumber.setHint(R.string.villa);
            binding.floorNumber.setVisibility(View.GONE);
            binding.aptNumber.setVisibility(View.GONE);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardSaveAddress:
                break;

        }
    }
}