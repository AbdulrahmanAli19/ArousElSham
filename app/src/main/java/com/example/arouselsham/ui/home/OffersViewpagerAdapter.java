package com.example.arouselsham.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.arouselsham.R;

import java.util.List;

public class OffersViewpagerAdapter extends RecyclerView.Adapter<OffersViewpagerAdapter.OffersViewHolder> {

    private Context mContext;
    private List<Integer> images;
    private ViewPager2 viewPager2;

    public OffersViewpagerAdapter(Context mContext, List<Integer> image, ViewPager2 viewPager2) {
        this.mContext = mContext;
        this.images = image;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.offers_layout, parent, false);
        return new OffersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersViewHolder holder, int position) {
        holder.offerImage.setImageResource(images.get(position));
        if (position == images.size() - 2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class OffersViewHolder extends RecyclerView.ViewHolder {
        private ImageView offerImage;
        public OffersViewHolder(@NonNull View itemView) {
            super(itemView);
            offerImage = itemView.findViewById(R.id.iv_offer);
        }
        void setImage (int image){
            offerImage.setImageResource(image);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            images.addAll(images);
            notifyDataSetChanged();
        }

    };
}
