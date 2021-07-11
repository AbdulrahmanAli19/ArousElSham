package com.example.arouselsham.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.arouselsham.R;
import com.example.arouselsham.pojo.model.maleModels.MenuSection;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    private static final String TAG = "Cannot invoke method length() on null object";

    private HomeViewModel homeViewModel;
    private List<String> enNames, arNames, images;
    private Handler sliderHandler = new Handler();
    private Double menuVersion;
    @BindView(R.id.offers_viewpager)
    ViewPager2 viewPager2;

    @BindView(R.id.recycler_categories)
    RecyclerView categoryRecycler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        homeViewModel.getAllMeals().observe(getViewLifecycleOwner(), menus -> {

        });


        init();

        return root;
    }

    ///TODO:change the viewPager layout
    private void setUpViewPager() {
        List<Integer> images = new ArrayList();
        images.add(R.drawable.offer3);
        images.add(R.drawable.offer3);
        images.add(R.drawable.offer3);

        OffersViewpagerAdapter viewpagerAdapter = new OffersViewpagerAdapter(getContext(), images, viewPager2);
        viewPager2.setAdapter(viewpagerAdapter);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(0));
        transformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * .15f);
        });
        viewPager2.setPageTransformer(transformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunable);
                sliderHandler.postDelayed(sliderRunable, 2000);
            }
        });
    }

    private void init() {
        FirebaseFirestore.getInstance()
                .collection("MainManu")
                .document("Tags")
                .get()
                .addOnCompleteListener(task -> {

                    MenuSection section;

                    if (task.isSuccessful()) {
                        ///TODO: check if the menuVersion equals the menuVersion from SharedPref
                        menuVersion = (Double) task.getResult().get("menuVersion");

                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot != null) {
                            section = documentSnapshot.toObject(MenuSection.class);
                            CategoriesAdapter adapter = new CategoriesAdapter(getActivity(), section);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                            gridLayoutManager.setSpanCount(2);
                            categoryRecycler.setLayoutManager(gridLayoutManager);
                            categoryRecycler.setAdapter(adapter);

                        }
                    }

                });
        viewPager2.setVisibility(View.GONE);
        // setUpViewPager();
    }

    private Runnable sliderRunable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        // sliderHandler.removeCallbacks(sliderRunable);
    }

    @Override
    public void onResume() {
        super.onResume();
        // sliderHandler.post(sliderRunable);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.home_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            default:
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}