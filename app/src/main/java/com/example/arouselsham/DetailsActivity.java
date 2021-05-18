package com.example.arouselsham;

import static com.example.arouselsham.pojo.Common.isArabicEnabled;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arouselsham.pojo.model.maleModels.MealModel;
import com.example.arouselsham.pojo.model.maleModels.MenuTopping;
import com.example.arouselsham.pojo.model.maleModels.PriceByBreadTypes;
import com.example.arouselsham.pojo.model.maleModels.PriceByKilogram;
import com.example.arouselsham.pojo.model.maleModels.PriceByPiece;
import com.example.arouselsham.pojo.model.maleModels.PriceBySize;
import com.example.arouselsham.pojo.model.maleModels.SelectorModel;
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
        SelectorAdapter.ListItemClickListener {

    private static final String TAG = "DetailsActivity";
    public static double mainPrice;
    private int numberOfItems = 1;

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
        MealModel meal = (MealModel) getIntent().getSerializableExtra("Meal");
        init(meal);
    }

    @SuppressLint("SetTextI18n")
    private void init(MealModel meal) {
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

        ToppingAdapter toppingAdapter = new ToppingAdapter(this, toppings, isItArabic);
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
                //TODO: Create database for favorite meals and the meal inside of it.
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                //TODO: find the meal in the database and remove it.
            }
        });

    }

    public List<SelectorModel> createSelectorList(MealModel meal) {

        List<SelectorModel> models = new ArrayList<>();

        if (meal.getPriceByPiece() != null) {
            models.add(new SelectorModel("¼ Chicken", meal.getPriceByPiece().getQuarterChicken()));
            models.add(new SelectorModel("½ Chicken", meal.getPriceByPiece().getHalfChicken()));
            models.add(new SelectorModel("1 Chicken", meal.getPriceByPiece().getHoleChicken()));

            mainPrice = meal.getPriceByPiece().getQuarterChicken();

            return models;

        } else if (meal.getPriceByBreadTypes() != null) {

            if (meal.getPriceByBreadTypes().getSyrianPrice() != 0)
                models.add(new SelectorModel("Arabic bread", meal.getPriceByBreadTypes().getSyrianPrice()));

            if (meal.getPriceByBreadTypes().getFrenchPrice() != 0)
                models.add(new SelectorModel("French bread", meal.getPriceByBreadTypes().getFrenchPrice()));

            if (meal.getPriceByBreadTypes().getSaro5Price() != 0)
                models.add(new SelectorModel("Saro5 bread", meal.getPriceByBreadTypes().getSaro5Price()));

            mainPrice = meal.getPriceByBreadTypes().getSyrianPrice();

            return models;

        } else if (meal.getPriceBySize() != null) {

            if (meal.getPriceBySize().getMediumSizePrice() != 0)
                models.add(new SelectorModel("Medium", meal.getPriceBySize().getMediumSizePrice()));

            if (meal.getPriceBySize().getBigSizePrice() != 0)
                models.add(new SelectorModel("Large", meal.getPriceBySize().getBigSizePrice()));

            mainPrice = meal.getPriceBySize().getMediumSizePrice();

            return models;

        } else if (meal.getPriceByKilogram() != null) {

            if (meal.getPriceByKilogram().getTomnKilograms() != 0)
                models.add(new SelectorModel("⅛ KG", meal.getPriceByKilogram().getTomnKilograms()));

            if (meal.getPriceByKilogram().getQuarterKilograms() != 0)
                models.add(new SelectorModel("¼ KG", meal.getPriceByKilogram().getQuarterKilograms()));

            if (meal.getPriceByKilogram().getHalfKilograms() != 0)
                models.add(new SelectorModel("½ KG", meal.getPriceByKilogram().getHalfKilograms()));

            if (meal.getPriceByKilogram().getKilograms() != 0)
                models.add(new SelectorModel("1 KG", meal.getPriceByKilogram().getKilograms()));

            mainPrice = (meal.getPriceByKilogram().getTomnKilograms() != 0) ?
                    meal.getPriceByKilogram().getTomnKilograms() : meal.getPriceByKilogram().getQuarterKilograms();

            return models;

        } else {
            mainPrice = meal.getPriceByOne().getPrice();

            return models;
        }
    }

    private SelectorAdapter selectorAdapter;

    public void inflateSelectorRecyclerView(List<SelectorModel> selectorModels) {
        selectorAdapter = new SelectorAdapter(this, selectorModels, this);
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

                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void setNewPrice() {
        double operationPrice = numberOfItems * mainPrice;
        txtNumberOfSelectedItems.setText(String.valueOf(numberOfItems));
        txtPrice.setText(operationPrice + " EGP");
    }

    @Override
    public void onItemClick(int position, double price) {
        mainPrice = price;
        selectorAdapter.changeBackground(position);
        setNewPrice();
    }
}