package com.example.arouselsham.ui.paymentMethod;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.arouselsham.databinding.PaymentMethodFragmentBinding;
import com.example.arouselsham.pojo.model.Order;

public class PaymentMethodFragment extends Fragment implements PaymentMethodAdapter.OnItemClick {

    private PaymentMethodViewModel mViewModel;
    private PaymentMethodFragmentBinding binding;
    private PaymentMethodAdapter adapter;
    private NavController navigation;
    private Order order;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PaymentMethodFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        order = PaymentMethodFragmentArgs.fromBundle(getArguments()).getOrder();
        adapter = new PaymentMethodAdapter(getContext(), this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
        binding.btnNext.setOnClickListener(v -> {
            adapter.getSelectedPaymentMethod(adapter.selectedItem);
            navigation.navigate(
                    PaymentMethodFragmentDirections.actionPaymentMethodFragmentToConfirmPaymentFragment(order));
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigation = Navigation.findNavController(view);
    }

    @Override
    public void onItemClick(int pos) {
        adapter.changeColor(pos);
    }
}