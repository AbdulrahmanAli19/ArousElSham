package com.example.arouselsham.ui.paymentMethod;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arouselsham.R;
import com.example.arouselsham.databinding.PaymentLayoutBinding;
import com.example.arouselsham.pojo.model.PaymentModel;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder> {

    private static final String TAG = "PaymentMethodAdapter";
    public int selectedItem = 0;
    private final Context context;
    private final List<PaymentModel> paymentList;
    private final OnItemClick onItemClick;

    public PaymentMethodAdapter(Context context, OnItemClick onItemClick) {
        this.context = context;
        this.onItemClick = onItemClick;
        paymentList = new ArrayList<>();
        paymentList.add(new PaymentModel(context.getString(R.string.cahs_on_delivery), 0));
        paymentList.add(new PaymentModel(context.getString(R.string.paypal), R.drawable.ic_paypal));
        paymentList.add(new PaymentModel(context.getString(R.string.cradited_card), R.drawable.on_payment));
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PaymentLayoutBinding binding = PaymentLayoutBinding.inflate(inflater);
        return new PaymentMethodViewHolder(binding, onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, int position) {
        PaymentModel current = paymentList.get(position);
        holder.binding.radioBtn.setClickable(false);

        holder.binding.radioBtn.post(() -> holder.binding.radioBtn.setChecked(selectedItem == position));


        if (position == paymentList.size())
            holder.binding.card.setPadding(0, 0, 0, 16);


        if (current.getPaymentImage() != 0) {
            holder.binding.image.setImageResource(current.getPaymentImage());
            holder.binding.radioBtn.setText("");
        } else
            holder.binding.radioBtn.setText(context.getText(R.string.cahs_on_delivery));
    }

    public void changeColor(int pos) {
        selectedItem = pos;
        notifyDataSetChanged();
    }


    public String getSelectedPaymentMethod(int pos) {
        return paymentList.get(pos).getPaymentText();
    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }

    static class PaymentMethodViewHolder extends RecyclerView.ViewHolder {
        private final PaymentLayoutBinding binding;
        private final OnItemClick onItemClick;

        public PaymentMethodViewHolder(PaymentLayoutBinding binding, OnItemClick onItemClick) {
            super(binding.getRoot());
            this.binding = binding;
            this.onItemClick = onItemClick;
            binding.item.setOnClickListener(v -> onItemClick.onItemClick(getBindingAdapterPosition()));
        }

    }

    interface OnItemClick {
        void onItemClick(int pos);
    }
}
