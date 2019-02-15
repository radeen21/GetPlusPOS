package id.mygetplus.getpluspos.mvp.payment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentHolder>{

    private List<ResponsePojo> responsePojos;

    private Context context;
    private String title[];
    int intMin;
    int intMax;
    int intHarga = 10000;
    int Jumlah;

    public PaymentAdapter(Context context, List<ResponsePojo> responsePojos) {
        this.context = context;
        this.responsePojos = responsePojos;
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
        if (paymentHolder instanceof PaymentHolder) {
            ResponsePojo responsePojo = responsePojos.get(position);

            paymentHolder.tvTitle.setText(responsePojo.getAValue().getBAccountName());
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

    }

    public void addListPayment(List<ResponsePojo> responsePojo) {
        this.responsePojos = responsePojo;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return responsePojos.size();
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
