package com.example.arouselsham.ui.accountInfo;

import static com.example.arouselsham.pojo.utilities.Common.CUSTOMER_INFO_REFERENCE;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
import com.example.arouselsham.databinding.AccountInfoFragmentBinding;
import com.example.arouselsham.pojo.model.Customer;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class AccountInfoFragment extends Fragment implements FirebaseInterface {

    private static final String TAG = "AccountInfoFragment";

    private final Calendar myCalendar = Calendar.getInstance();
    private final String myFormat = "dd/MM/yyyy";
    private final SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


    private AccountInfoViewModel accountInfoViewModel;
    private AccountInfoFragmentBinding binding;

    private FirebaseInterface firebaseInterface;

    private Long birthDay;

    public static AccountInfoFragment newInstance() {
        return new AccountInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        accountInfoViewModel = new ViewModelProvider(this).get(AccountInfoViewModel.class);
        binding = AccountInfoFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        firebaseInterface = (FirebaseInterface) this;
        accountInfoViewModel.getCurrentUser(firebaseInterface);

        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
            myCalendar.set(YEAR, year);
            myCalendar.set(MONTH, month);
            myCalendar.set(DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        binding.inputBirthdate.getEditText().setOnClickListener(v -> {
            new DatePickerDialog(getContext(), date, myCalendar.get(YEAR), myCalendar.get(MONTH),
                    myCalendar.get(DAY_OF_MONTH)).show();
        });

        binding.save.setOnClickListener(v -> update());

        return root;
    }


    public void update() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(CUSTOMER_INFO_REFERENCE);


        HashMap<String, Object> user = new HashMap<>();

        if (birthDay != null && birthDay != 0)
            user.put("birthDay", birthDay);

        if (binding.btnMale.isChecked())
            user.put("gender", binding.btnMale.getText().toString());
        else if (binding.btnFemale.isChecked())
            user.put("gender", binding.btnFemale.getText().toString());

        user.put("isP1Granted", binding.cbP1.isChecked());
        user.put("isP2Granted", binding.cbP2.isChecked());

        if (binding.inputFirstName.getEditText().getText() == null) {
            binding.inputFirstName.setError(getString(R.string.f_name_null));
            return;
        } else
            user.put("firstName", binding.inputFirstName.getEditText().getText().toString());

        if (binding.inputLastName.getEditText().getText() == null) {
            binding.inputLastName.setError(getString(R.string.l_name_null));
            return;
        } else
            user.put("lastName", binding.inputLastName.getEditText().getText().toString());

        reference.child(AuthUI.getInstance().getAuth().getCurrentUser().getUid())
                .updateChildren(user)
                .addOnSuccessListener(unused -> makeToast("Saved."))
                .addOnFailureListener(e -> makeToast(e.getMessage()));

    }

    private void updateLabel() {
        binding.inputBirthdate.getEditText().setText(sdf.format(myCalendar.getTime()));
        birthDay = myCalendar.getTime().getTime();
    }

    private void makeToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDataReceived(Customer customer, DataSnapshot snapshot) {
        binding.loadingScreen.setVisibility(View.GONE);
        binding.linearUserInfo.setVisibility(View.VISIBLE);
        if (customer.getGender() != null)
            if (customer.getGender().contentEquals(binding.btnFemale.getText())) {
                binding.btnFemale.setChecked(true);

            } else if (customer.getGender().contentEquals(binding.btnMale.getText())) {
                binding.btnMale.setChecked(true);
            }

        if (customer.getBirthDay() != null && customer.getBirthDay() != 0)
            binding.inputBirthdate.getEditText()
                    .setText(sdf.format(new Date(customer.getBirthDay())).toString());

        if (snapshot.child("isP1Granted").getValue() != null)
            customer.setP1Granted((boolean) snapshot.child("isP1Granted").getValue());
        if (snapshot.child("isP2Granted").getValue() != null)
            customer.setP2Granted((boolean) snapshot.child("isP2Granted").getValue());

        binding.setCustomer(customer);
    }
}