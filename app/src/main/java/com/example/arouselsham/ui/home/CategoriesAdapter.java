package com.example.arouselsham.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.example.arouselsham.pojo.db.entities.MenuTags;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> implements Serializable {

    private static final String TAG = "CategoriesAdapter";
    private Context mContext;
    private List<MenuTags> tags = new ArrayList<>();
    private final OnItemClickListener onItemClickListener;

    public CategoriesAdapter(Context mContext, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.onItemClickListener = onItemClickListener;
    }

    public void setTags(List<MenuTags> tags) {
        this.tags = tags;
        notifyDataSetChanged();

    }

    @NonNull
    @NotNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.categori_layout, parent, false);
        CategoriesViewHolder viewHolder = new CategoriesViewHolder(view, onItemClickListener);
        return viewHolder;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoriesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String image = tags.get(position).getImageUrl();
        String enName = tags.get(position).getEnName();
        String arName = tags.get(position).getArName();

        holder.textView.setText(enName);

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

    }

    @Override
    public int getItemCount() {

        return tags.size();
    }


    static class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView imageView;
        private final TextView textView;
        private final CardView mainCard;
        private final ProgressBar progressBar;
        private OnItemClickListener onItemClickListener;

        public CategoriesViewHolder(@NonNull @NotNull View itemView,
                                    OnItemClickListener onItemClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imd_category);
            textView = itemView.findViewById(R.id.txt_category);
            mainCard = itemView.findViewById(R.id.main_card);
            progressBar = itemView.findViewById(R.id.menu_progress_par);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClickListener(getBindingAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }
}
