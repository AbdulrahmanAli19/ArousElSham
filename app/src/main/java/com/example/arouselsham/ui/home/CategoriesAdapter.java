package com.example.arouselsham.ui.home;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {


    private static final String TAG = "CategoriesAdapter";
    private Context mContext;
    private int images[] = {R.drawable.shawarma, R.drawable.sandwich, R.drawable.dish,
            R.drawable.crepe, R.drawable.crepe_sweet, R.drawable.pie, R.drawable.pie_sweet,
            R.drawable.pizza, R.drawable.roast_chicken, R.drawable.barbecue};
    private String meals[] = {"Shawarma", "Sandwitch", "Fattet Shawarma", "Crepe", "Sweet crepe", "Pie", "Sweet pie", "Pizza", "Chicken", "BBQ"};


    public CategoriesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @NotNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.categori_layout, parent, false);
        CategoriesViewHolder viewHolder = new CategoriesViewHolder(view);
        return viewHolder;
    }

    private int selectedPosition = -1;
    private boolean isPosition0Changed = false;

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoriesViewHolder holder, int position) {
        int image = images[position];
        String meal = meals[position];

        holder.imageView.setImageResource(image);
        holder.textView.setText(meal);

        if (position == 0 && !isPosition0Changed) {
            holder.mainCard.setCardBackgroundColor(mContext.getColor(R.color.gold));
            isPosition0Changed = true;
        } else if (selectedPosition == position) {
            int colorFrom = mContext.getResources().getColor(R.color.white);
            int colorTo = mContext.getResources().getColor(R.color.gold);
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            colorAnimation.setDuration(250); // milliseconds
            colorAnimation.addUpdateListener(animator -> holder.mainCard.setCardBackgroundColor((int) animator.getAnimatedValue()));
            colorAnimation.start();
        } else if (holder.mainCard.getCardBackgroundColor().getDefaultColor() == mContext.getResources().getColor(R.color.white)) {

        } else {
            int colorFrom = mContext.getResources().getColor(R.color.gold);
            int colorTo = mContext.getResources().getColor(R.color.white);
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            colorAnimation.setDuration(250); // milliseconds
            colorAnimation.addUpdateListener(animator -> holder.mainCard.setCardBackgroundColor((int) animator.getAnimatedValue()));
            colorAnimation.start();
        }

        holder.mainCard.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();

        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }



    class CategoriesViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private CardView mainCard;

        public CategoriesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imd_category);
            textView = itemView.findViewById(R.id.txt_category);
            mainCard = itemView.findViewById(R.id.main_card);
        }
    }
}
