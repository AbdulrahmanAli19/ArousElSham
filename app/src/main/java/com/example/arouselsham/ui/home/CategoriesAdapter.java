package com.example.arouselsham.ui.home;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {


    private static final String TAG = "CategoriesAdapter";
    private Context mContext;
    private List<String> images;
    private List<String> meals;
    private int selectedPosition = -1;
    private boolean isPosition0Changed = false;

    public CategoriesAdapter(Context mContext, List<String> meals, List<String> images) {
        this.mContext = mContext;
        this.meals = meals;
        this.images = images;
    }

    @NonNull
    @NotNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.categori_layout, parent, false);
        CategoriesViewHolder viewHolder = new CategoriesViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoriesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String image = images.get(position);
        String meal = meals.get(position);
        holder.textView.setText(meal);
        Picasso.get().load(image).into(holder.imageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                holder.progressBar.setVisibility(View.GONE);
                holder.imageView.setImageResource(R.drawable.cancel);
            }
        });

        if (position == 0 && !isPosition0Changed) {
            holder.mainCard.setCardBackgroundColor(mContext.getColor(R.color.gold));
            isPosition0Changed = true;

        } else if (selectedPosition == position) {

            int colorFrom = mContext.getResources().getColor(R.color.white);
            int colorTo = mContext.getResources().getColor(R.color.gold);
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            colorAnimation.setDuration(150); // milliseconds
            colorAnimation.addUpdateListener(animator -> holder.mainCard.setCardBackgroundColor((int) animator.getAnimatedValue()));
            colorAnimation.start();

        } else if (holder.mainCard.getCardBackgroundColor().getDefaultColor() == mContext.getResources().getColor(R.color.white)) {

            Log.d(TAG, "onBindViewHolder: getDefaultColor() = white");
        } else {

            int colorFrom = mContext.getResources().getColor(R.color.gold);
            int colorTo = mContext.getResources().getColor(R.color.white);
            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            colorAnimation.setDuration(150); // milliseconds
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
        return images.size();
    }


    class CategoriesViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private CardView mainCard;
        private ProgressBar progressBar;

        public CategoriesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imd_category);
            textView = itemView.findViewById(R.id.txt_category);
            mainCard = itemView.findViewById(R.id.main_card);
            progressBar = itemView.findViewById(R.id.menu_progress_par);
        }
    }
}
