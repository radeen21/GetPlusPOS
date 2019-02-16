package id.mygetplus.getpluspos.mvp.payment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.mygetplus.getpluspos.BrandsRsp;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.mvp.payment.model.AvalueList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentHolder>{

    private List<AvalueList> avalueLists = new ArrayList<>();

    int quantity;
    int Jumlah;

    @NonNull
    @Override
    public PaymentHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_payment,
                parent, false);
        PaymentHolder paymentHolder = new PaymentHolder(view);
        return paymentHolder;
    }

    @Override
    public void onBindViewHolder(PaymentHolder paymentHolder, int position) {
        if (paymentHolder instanceof PaymentHolder) {
            AvalueList avalueList = avalueLists.get(position);

//            AtomicInteger minQty = new AtomicInteger(avalueList.getMinQty());
//            AtomicInteger maxQty = new AtomicInteger(avalueList.getMaxQty());

            paymentHolder.tvTitle.setText(avalueList.getBrandName());
            Glide.with(paymentHolder.itemView.getContext())
                    .load(avalueList.getImageURL())
                    .into(paymentHolder.imgPayment);

            paymentHolder.imgMin.setOnClickListener(v -> {
                quantity = Integer.valueOf(paymentHolder.tvValue.getText().toString());
                quantity--;
                Jumlah = avalueList.getPrice() * quantity;
                paymentHolder.tvValue.setText(String.valueOf(quantity));
                paymentHolder.tvPoint.setText("Rp " + String.valueOf(Jumlah));

                if(quantity <= 0)
                {
                    paymentHolder.tvValue.setText("0");
                    paymentHolder.tvPoint.setText("Rp 0");
                    Jumlah = 0;
                }
            });

            paymentHolder.imgPlus.setOnClickListener(v -> {
                quantity = Integer.valueOf(paymentHolder.tvValue.getText().toString());
                quantity++;
                Jumlah = avalueList.getPrice() * quantity;
                paymentHolder.tvValue.setText(String.valueOf(quantity));
                paymentHolder.tvPoint.setText("Rp " + String.valueOf(Jumlah));
            });
        }

    }

    public void addListPayment(List<AvalueList> avalueLists) {
        this.avalueLists = avalueLists;
        notifyDataSetChanged();
    }

    public List<AvalueList> getBrandsRspsList() {
        return avalueLists;
    }

    public int getSum() {
        return Jumlah;
    }

    @Override
    public int getItemCount() {
        return avalueLists.size();
    }

    public class PaymentHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.img_min)
        ImageView imgMin;

        @BindView(R.id.img_plus)
        ImageView imgPlus;

        @BindView(R.id.tv_value)
        TextView tvValue;

        @BindView(R.id.tv_point)
        TextView tvPoint;

        @BindView(R.id.img_payment)
        ImageView imgPayment;


        public PaymentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
