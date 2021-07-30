package com.example.arouselsham.ui.cart;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.databinding.CartFragmentBinding;
import com.example.arouselsham.pojo.db.entities.Cart;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;

public class CartFragment extends Fragment implements CartAdapter.OnItemClickListener {
    private static final String TAG = "CartFragment";
    private CartViewModel cartViewModel;
    private CartFragmentBinding binding;
    private final CartAdapter adapter = new CartAdapter(this);
    private NavController navController;

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        binding = CartFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.cartRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.cartRecycler.setAdapter(adapter);
        cartViewModel.getCartList().observe(getViewLifecycleOwner(), carts -> {
            adapter.setCart(carts);
            binding.setTotalPrice(0.0);
            for (int i = 0; i < carts.size(); i++) {
                binding.setTotalPrice(binding.getTotalPrice() + carts.get(i).getPrice());
            }
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
        ///TODO : navigate to new fragment
    }

    @Override
    public void onRemoveItem(int position) {
        Log.d(TAG, "onRemoveItem: position= " + position);
        cartViewModel.removeOneCart(adapter.getCurrentCart(position));
        adapter.removeCartAt(position);
    }

    @Override
    public void onPlus(int position) {
        Cart cart = adapter.getCurrentCart(position);
        cart.setMealNum(cart.getMealNum() + 1);
        adapter.updateCart(cart, position);
    }

    @Override
    public void onMinus(int position) {
        Cart cart = adapter.getCurrentCart(position);
        cart.setMealNum(cart.getMealNum() - 1);
        adapter.updateCart(cart, position);
    }
}