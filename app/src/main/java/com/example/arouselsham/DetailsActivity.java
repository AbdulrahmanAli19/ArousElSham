package com.example.arouselsham;

import static com.example.arouselsham.pojo.Common.dataBaseManger;
import static com.example.arouselsham.pojo.Common.isArabicEnabled;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.pojo.db.Cart;
import com.example.arouselsham.pojo.db.Favorite;
import com.example.arouselsham.pojo.model.KeyValue;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.example.arouselsham.pojo.model.maleModels.MenuTopping;
import com.example.arouselsham.pojo.model.maleModels.PriceOption;
import com.example.arouselsham.ui.SelectorAdapter;
import com.example.arouselsham.ui.ToppingAdapter;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener,
        SelectorAdapter.ListItemClickListener, ToppingAdapter.ItemClickListener {

    private static final String TAG = "DetailsActivity";
    private double mainPrice;
    private double toppingPrice;
    private int numberOfItems = 1;
    private SelectorAdapter selectorAdapter;
    private Meal meal;


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_details_name)
    TextView txtName;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_details_tag)
    TextView txtTag;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.img_details)
    ImageView imgMain;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.details_like_btn)
    LikeButton likeBtn;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.size_selector_recycler)
    RecyclerView sizeSelectorRecycler;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.topping_recycler)
    RecyclerView toppingRecycler;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.minus_card)
    CardView minusCard;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.plus_card)
    CardView plusCard;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.add_to_cart_card)
    CardView addToCartCard;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_details_price)
    TextView txtPrice;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_number_of_item)
    TextView txtNumberOfSelectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        meal = (Meal) getIntent().getSerializableExtra("Meal");
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        String localeLanguage = Locale.getDefault().getDisplayLanguage();
        boolean isItArabic;
        List<MenuTopping> toppings = meal.getToppings();

        txtNumberOfSelectedItems.setText(String.valueOf(numberOfItems));

        if (isArabicEnabled || localeLanguage.equals("ar")) {
            txtName.setText(meal.getArName());
            txtTag.setText(meal.getTags().get(0).getArName());
            isItArabic = true;
        } else {
            txtName.setText(meal.getEnName());
            txtTag.setText(meal.getTags().get(0).getEnName());
            isItArabic = false;
        }

        ToppingAdapter toppingAdapter = new ToppingAdapter(this, toppings, isItArabic, this);
        toppingRecycler.setLayoutManager(new LinearLayoutManager(this));
        toppingRecycler.setAdapter(toppingAdapter);

        inflateSelectorRecyclerView(createSelectorList(meal));

        txtPrice.setText(mainPrice + " EGP");
        minusCard.setOnClickListener(this);
        plusCard.setOnClickListener(this);
        addToCartCard.setOnClickListener(this);

        likeBtn.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                dataBaseManger.favoriteDao().insert(new Favorite(meal));
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                dataBaseManger.favoriteDao().delete(new Favorite(meal));
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
        sizeSelectorRecycler.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        sizeSelectorRecycler.setAdapter(selectorAdapter);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.plus_card:
                numberOfItems++;
                setNewPrice();
                break;

            case R.id.minus_card:
                if (numberOfItems == 1)
                    Toast.makeText(this, "The minimum number of order is 1!",
                            Toast.LENGTH_LONG).show();
                else {
                    numberOfItems--;
                    setNewPrice();
                }
                break;

            case R.id.add_to_cart_card:
                dataBaseManger.cartDao().Insert(new Cart(meal));
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void setNewPrice() {
        double operationPrice = numberOfItems * mainPrice;
        operationPrice += toppingPrice * numberOfItems;
        txtNumberOfSelectedItems.setText(String.valueOf(numberOfItems));
        txtPrice.setText(operationPrice + " EGP");
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