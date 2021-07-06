package com.example.arouselsham.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.example.arouselsham.SecondActivity;
import com.example.arouselsham.pojo.model.maleModels.Meal;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> implements Serializable {

    private static final String TAG = "CategoriesAdapter";
    private Context mContext;
    private List<String> images;
    private List<String> enNames, arNames;

    public CategoriesAdapter(Context mContext, List<String> enNames, List<String> arNames, List<String> images) {
        this.mContext = mContext;
        this.enNames = enNames;
        this.arNames = arNames;
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
        String enName = enNames.get(position);
        holder.textView.setText(arNames.get(position));

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

        holder.mainCard.setOnClickListener(v -> {
            getDataFromFirebase(enName);
        });

    }

    private void getDataFromFirebase(String selectedItem) {
        FirebaseFirestore.getInstance()
                .collection("Menu")
                .document(selectedItem)
                .collection("MenuItems")
                .get()
                .addOnCompleteListener(task1 -> {
                    List<Meal> meals = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task1.getResult()) {
                        meals.add(document.toObject(Meal.class));
                        Log.d(TAG, "onComplete: called");
                    }

                    Intent intent = new Intent(mContext, SecondActivity.class);
                    intent.putExtra("Meals", (Serializable) meals);
                    mContext.startActivity(intent);
                });
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    static class CategoriesViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;
        private final CardView mainCard;
        private final ProgressBar progressBar;

        public CategoriesViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imd_category);
            textView = itemView.findViewById(R.id.txt_category);
            mainCard = itemView.findViewById(R.id.main_card);
            progressBar = itemView.findViewById(R.id.menu_progress_par);
        }
    }
}
