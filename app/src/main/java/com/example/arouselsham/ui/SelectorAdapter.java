package com.example.arouselsham.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.example.arouselsham.pojo.model.maleModels.PriceOption;

import java.util.ArrayList;
import java.util.List;


public class SelectorAdapter extends RecyclerView.Adapter<SelectorAdapter.SelectorViewHolder> {

    private boolean isItJustInflated = true;
    private final Context mContext;
    private List<PriceOption> list = new ArrayList<>();
    private int row_index = -1;
    private final ListItemClickListener mOnClickListener;


    public SelectorAdapter(Context mContext, List<PriceOption> priceOption,
                           ListItemClickListener mOnClickListener) {
        this.mContext = mContext;
        this.list = priceOption;
        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public SelectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.selector_layout, parent, false);
        return new SelectorViewHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectorViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtTitle.setText(list.get(position).getOption());

        if (row_index == position) {
            holder.cardView.setCardBackgroundColor(mContext.getColor(R.color.d_gold));
            holder.txtTitle.setTextColor(mContext.getColor(R.color.white));
        } else if (isItJustInflated && position == 0) {
            holder.cardView.setCardBackgroundColor(mContext.getColor(R.color.d_gold));
            holder.txtTitle.setTextColor(mContext.getColor(R.color.white));
        } else {
            holder.cardView.setCardBackgroundColor(mContext.getColor(R.color.white));
            holder.txtTitle.setTextColor(mContext.getColor(R.color.black));
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    public void changeBackground(int position) {
        isItJustInflated = false;
        row_index = position;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SelectorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtTitle;
        private final CardView cardView;
        private final ListItemClickListener mListClickListener;

        public SelectorViewHolder(@NonNull View itemView, ListItemClickListener listener) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.size_selector_txt);
            cardView = itemView.findViewById(R.id.size_selector_card);
            this.mListClickListener = listener;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            mListClickListener.onItemClick(getBindingAdapterPosition(),
                    list.get(getBindingAdapterPosition()).getPrice());
        }
    }

    public interface ListItemClickListener {
        void onItemClick(int position, double price);
    }
}
