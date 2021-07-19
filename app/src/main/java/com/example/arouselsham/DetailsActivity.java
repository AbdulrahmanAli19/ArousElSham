package com.example.arouselsham;

import static com.example.arouselsham.pojo.Common.isArabicEnabled;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.arouselsham.databinding.ActivityDetailsBinding;
import com.example.arouselsham.pojo.Common;
import com.example.arouselsham.pojo.model.maleModels.KeyValue;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.example.arouselsham.pojo.model.maleModels.MenuTopping;
import com.example.arouselsham.pojo.model.maleModels.PriceOption;
import com.example.arouselsham.ui.SelectorAdapter;
import com.example.arouselsham.ui.ToppingAdapter;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener,
        SelectorAdapter.ListItemClickListener, ToppingAdapter.ItemClickListener {

    private static final String TAG = "DetailsActivity";
    private double mainPrice;
    private double toppingPrice;
    private int numberOfItems = 1;
    private SelectorAdapter selectorAdapter;
    private Meal meal;

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        meal = (Meal) getIntent().getSerializableExtra("Meal");
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        String localeLanguage = Locale.getDefault().getDisplayLanguage();
        boolean isItArabic;

        List<MenuTopping> toppings = meal.getToppings();

        binding.txtNumberOfItems.setText(String.valueOf(numberOfItems));

        if (isArabicEnabled || localeLanguage.equals("ar")) {
            binding.txtName.setText(meal.getArName());
            binding.txtSection.setText(meal.getTags().get(0).getArName());
            isItArabic = true;
        } else {
            binding.txtName.setText(meal.getEnName());
            binding.txtSection.setText(meal.getTags().get(0).getEnName());
            isItArabic = false;
        }

        if (meal.getToppings().size() <= 0) {
            binding.toppingRecycler.setVisibility(View.GONE);
        } else {
            ToppingAdapter toppingAdapter = new ToppingAdapter(this, toppings, isItArabic, this);
            binding.toppingRecycler.setLayoutManager(new LinearLayoutManager(this));
            binding.toppingRecycler.setAdapter(toppingAdapter);
        }


        Picasso.get().load(meal.getImageUrl()).into(binding.mainImage);

        KeyValue prices = new KeyValue(meal.getPrice().keySet(), meal.getPrice().values());

        if (prices.getValue().size() > 0 && !prices.getKey().get(0).equals(Common.priceByOne)) {
            inflateSelectorRecyclerView(createSelectorList(meal));
            mainPrice = prices.getValue().get(0);
            setNewPrice();
        } else {
            binding.sizeSelectorRecycler.setVisibility(View.GONE);
            mainPrice = prices.getValue().get(0);
            setNewPrice();
        }
        binding.txtMainPrice.setText(String.valueOf(mainPrice));

        binding.txtPrice.setText(mainPrice + " EGP");
        binding.minusCard.setOnClickListener(this);
        binding.plusCard.setOnClickListener(this);
        binding.addToCartCard.setOnClickListener(this);

        binding.likeBtn.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                //TODO: add to db
                //dataBaseManger.favoriteDao().insert(new Favorite(meal));
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                //dataBaseManger.favoriteDao().delete(new Favorite(meal));
            }
        });

    }

    public List<PriceOption> createSelectorList(Meal meal) {
        List<PriceOption> models = new ArrayList<>();

        KeyValue keyValue = new KeyValue(meal.getPrice().keySet(), meal.getPrice().values());

        for (int i = 0; i < keyValue.getValue().size(); i++) {
            models.add(new PriceOption(keyValue.getKey().get(i), keyValue.getValue().get(i)));
        }

        return models;
    }


    public void inflateSelectorRecyclerView(List<PriceOption> priceOptions) {
        selectorAdapter = new SelectorAdapter(this, priceOptions, this);
        binding.sizeSelectorRecycler.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        binding.sizeSelectorRecycler.setAdapter(selectorAdapter);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plusCard:
                numberOfItems++;
                setNewPrice();
                break;

            case R.id.minusCard:
                if (numberOfItems == 1)
                    Toast.makeText(this, "The minimum number of order is 1!",
                            Toast.LENGTH_LONG).show();
                else {
                    numberOfItems--;
                    setNewPrice();
                }
                break;

            case R.id.addToCartCard:
                //TODO: add meal to cart
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void setNewPrice() {
        double operationPrice = numberOfItems * mainPrice;
        operationPrice += toppingPrice * numberOfItems;
        binding.txtNumberOfItems.setText(String.valueOf(numberOfItems));
        binding.txtPrice.setText(operationPrice + " EGP");
        binding.txtMainPrice.setText(String.valueOf(mainPrice));
    }

    @Override
    public void onItemClick(int position, double price) {
        mainPrice = price;
        selectorAdapter.changeBackground(position);
        setNewPrice();
    }

    @Override
    public void onItemCLickListener(int position, double price, CheckBox checkBox) {
        checkBox.setChecked(!checkBox.isChecked());

        if (checkBox.isChecked()) {
            toppingPrice += price;
        } else {
            toppingPrice -= price;
        }
        setNewPrice();
    }
}