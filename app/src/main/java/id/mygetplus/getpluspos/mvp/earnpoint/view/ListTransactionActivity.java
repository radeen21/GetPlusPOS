package id.mygetplus.getpluspos.mvp.earnpoint.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.mygetplus.getpluspos.R;

public class ListTransactionActivity extends AppCompatActivity {

    public static final String LIST_TRANSACTION = "LISTTRANSACTION";


    @BindView(R.id.tv_merchant_name)
    TextView tvMerchantName;

    @BindView(R.id.tv_amount)
    TextView tvAmount;

    @BindView(R.id.img_close)
    ImageView imgClose;

    @BindView(R.id.lin_main)
    LinearLayout linMain;

    String merchantName;
    String amount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn_transaction);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        merchantName = intent.getStringExtra("Nama Merchant");
        amount       = intent.getStringExtra("Amount");
    }

}
