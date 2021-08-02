package com.example.arouselsham.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.databinding.CartFragmentBinding;
import com.example.arouselsham.pojo.db.entities.Cart;

import java.util.List;

import io.reactivex.rxjava3.annotations.Nullable;

public class CartFragment extends Fragment implements CartAdapter.OnItemClickListener, OnCartChange {
    private static final String TAG = "CartFragment";
    private CartViewModel cartViewModel;
    private CartFragmentBinding binding;
    private final CartAdapter adapter = new CartAdapter(this);
    private NavController navController;

    @Override
    public View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding = CartFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.cartRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.cartRecycler.setAdapter(adapter);
        cartViewModel.getCartList().observe(getViewLifecycleOwner(), carts -> {
            adapter.setCart(carts);
            binding.setTotalPrice(0.0);
            getTotalPrice(carts);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@androidx.annotation.NonNull RecyclerView recyclerView,
                                  @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder,
                                  @androidx.annotation.NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                cartViewModel.removeOneCart(adapter.getCurrentCart(viewHolder
                        .getBindingAdapterPosition()));
                Toast.makeText(getContext(), "Item deleted.", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.cartRecycler);

        return root;
    }

    @Override
    public void onViewCreated(@androidx.annotation.NonNull View view,
                              @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onItemClick(int position) {
        NavDirections direction = CartFragmentDirections.actionNavigationOrdersToAddAddressFragment();
        navController.navigate(direction);
    }

    @Override
    public void onRemoveItem(int position) {
        cartViewModel.removeOneCart(adapter.getCurrentCart(position));
        adapter.removeCartAt(position);
    }

    @Override
    public void onPlus(int position) {
        Cart cart = adapter.getCurrentCart(position);
        int newNumber = cart.getMealNum() + 1;
        Double singleItemPrice = cart.getPrice() / cart.getMealNum();
        cart.setMealNum(newNumber);
        cart.setPrice(singleItemPrice * newNumber);
        adapter.updateCart(cart, position);
        cartViewModel.updateCart(cart);
        onCartUpdate();
    }

    @Override
    public void onMinus(int position) {
        Cart cart = adapter.getCurrentCart(position);
        int newNumber = cart.getMealNum() - 1;
        if (newNumber >= 1) {
            Double singleItemPrice = cart.getPrice() / cart.getMealNum();
            cart.setMealNum(newNumber);
            cart.setPrice(singleItemPrice * newNumber);
            adapter.updateCart(cart, position);
            cartViewModel.updateCart(cart);
            onCartUpdate();
        } else {
            Toast.makeText(getContext(), "The minimum number of order is 1 !",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCartUpdate() {
        cartViewModel.getCartList().observe(getViewLifecycleOwner(), this::getTotalPrice);
    }

    private void getTotalPrice(List<Cart> carts) {
        binding.setTotalPrice(0.0);
        for (int i = 0; i < carts.size(); i++) {
            binding.setTotalPrice(binding.getTotalPrice() + carts.get(i).getPrice());
        }
    }
}