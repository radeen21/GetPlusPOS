package id.mygetplus.getpluspos.mvp.earnpoint.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.mvp.earnpoint.model.EarnListModel;
import id.mygetplus.getpluspos.mvp.payment.adapter.PaymentAdapter;

public class EarnTransactionAdapter extends RecyclerView.Adapter<EarnTransactionAdapter.EarnTransactionHolder>{

    private List<EarnListModel> earnListModels = new ArrayList<>();

    private EventClickListenr eventClickListenr;

    public EarnTransactionAdapter(EventClickListenr eventClickListenr) {
        this.eventClickListenr = eventClickListenr;
    }

    public void removeItemAdapter(EarnListModel earnListModels) {
        this.earnListModels.remove(earnListModels);
        notifyDataSetChanged();
    }

    public void addAdapter(EarnListModel earnListModels) {
        this.earnListModels.add(earnListModels);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EarnTransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_earn_point,
                parent, false);
        EarnTransactionHolder earnTransactionHolder = new EarnTransactionHolder(view);
        return earnTransactionHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EarnTransactionHolder earnTransactionHolder, int position) {
        if (earnTransactionHolder instanceof EarnTransactionHolder) {
            EarnListModel earnListModel = earnListModels.get(position);

            earnTransactionHolder.tvMerchantName.setText(earnListModel.getMerchantName());
            earnTransactionHolder.tvAmount.setText(earnListModel.getJumlah());

            earnTransactionHolder.imgClose.setOnClickListener(v ->
                    eventClickListenr.setRemove(earnListModel, position));
        }
    }

    @Override
    public int getItemCount() {
        return earnListModels.size();
    }

    public class EarnTransactionHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.tv_merchant_name)
        TextView tvMerchantName;

        @BindView(R.id.tv_amount)
        TextView tvAmount;

        @BindView(R.id.img_close)
        ImageView imgClose;

        public EarnTransactionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface EventClickListenr {
        void setRemove(EarnListModel earnListModel, int position);
    }
}
