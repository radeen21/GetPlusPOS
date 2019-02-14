package id.mygetplus.getpluspos.mvp.payment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.mygetplus.getpluspos.R;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentHolder>{

    private Context context;
    private String title[];
    int intMin;
    int intMax;
    int intHarga = 10000;
    int Jumlah;

    public PaymentAdapter(Context context, String[] title) {
        this.context = context;
        this.title = title;
    }

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
        paymentHolder.tvTitle.setText(title[position]);
        paymentHolder.imgMin.setOnClickListener(v -> {
            intMin = Integer.valueOf(paymentHolder.tvValue.getText().toString());
            intMin--;
            Jumlah = intHarga * intMin;
            paymentHolder.tvPoint.setText("Rp " + String.valueOf(Jumlah));

            if(intMin <= 0)
            {
                paymentHolder.tvValue.setText("0");
                paymentHolder.tvPoint.setText("Rp 0");
                Jumlah = 0;
            }
        });

        paymentHolder.imgPlus.setOnClickListener(v -> {
            intMax = Integer.valueOf(paymentHolder.tvValue.getText().toString());
            intMax++;
            Jumlah = intHarga * intMax;
            paymentHolder.tvValue.setText(String.valueOf(intMax));
            paymentHolder.tvPoint.setText("Rp " + String.valueOf(Jumlah));
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
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


        public PaymentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
