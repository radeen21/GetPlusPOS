package id.mygetplus.getpluspos.mvp.payment.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.FixValue;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.PopupMessege;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.ScanQR;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;
import id.mygetplus.getpluspos.mvp.payment.adapter.PaymentAdapter;
import id.mygetplus.getpluspos.mvp.payment.presenter.PaymentContract;
import id.mygetplus.getpluspos.mvp.payment.presenter.PaymentPresenter;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class PaymentActivity extends AppCompatActivity implements PaymentContract.View {
     private Context context = this;

    @BindView(R.id.tvQty)
    TextView tvQty;
    @BindView(R.id.tvAmount)
    TextView tvAmount;
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    @BindView(R.id.ivMin)
    ImageView ivMin;
    @BindView(R.id.ivMax)
    ImageView ivMax;

    @BindView(R.id.rec_payment_point)
    RecyclerView recPaymentPoint;

    private PaymentAdapter paymentAdapter;
    private PaymentPresenter paymentPresenter;

    String accountRSN = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_point);
        ButterKnife.bind(this);
        tvToolbar.setText("Payment");

        AValue aValue = Fungsi.getObjectFromSharedPref(context, AValue.class, Preference.PrefResponsePojo);
        accountRSN = aValue.getBAccountRSN();
        aValue.setBAccountRSN(accountRSN);

        paymentPresenter = new PaymentPresenter(this, this);
        paymentPresenter.loadListPayment(PosLinkGenerator.createService(this),
                accountRSN);
        init();
    }

    void init() {
        paymentAdapter = new PaymentAdapter();

        recPaymentPoint.setHasFixedSize(true);
		recPaymentPoint.setLayoutManager(new LinearLayoutManager(this));
		recPaymentPoint.setAdapter(paymentAdapter);
    }

    @OnClick(R.id.btn_next_Payment)
    void btnNextPayment() {
        int summary = 0;
        for (int i=0; i<this.paymentAdapter.getItemCount(); i++) {
            summary += this.paymentAdapter.getSum();
        }
        if (summary == 0) {
            Toast.makeText(this, context.getString(R.string.msgBarangKosong), Toast.LENGTH_SHORT).show();
        } else {
            Fungsi.storeToSharedPref(getApplicationContext(), summary, Preference.PrefJumlahHarga);
            Fungsi.storeToSharedPref(context, 5, Preference.PrefActiveMenu);
            Intent EVoucher = new Intent(this, ScanQR.class);
            startActivity(EVoucher);
        }
    }

    private void goHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        goHome();
    }

    @Override
    public void setPaymentPoint(ResponsePojo responsePojo) {
        if (responsePojo.getAFaultCode().matches("0"))
            this.paymentAdapter.addListPayment(responsePojo.getAvalueLists());
        else
            Toast.makeText(getApplicationContext(), responsePojo.getAFaultDescription(),
                    Toast.LENGTH_SHORT).show();
    }
}