package id.mygetplus.getpluspos.mvp.payment.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.BrandsRsp;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.PopupMessege;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.ScanQR;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;
import id.mygetplus.getpluspos.mvp.payment.adapter.PaymentAdapter;
import id.mygetplus.getpluspos.mvp.payment.model.AvalueList;
import id.mygetplus.getpluspos.mvp.payment.presenter.PaymentContract;
import id.mygetplus.getpluspos.mvp.payment.presenter.PaymentPresenter;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class PaymentActivity extends AppCompatActivity implements PaymentContract.View {
    private PopupMessege popupMessege = new PopupMessege();
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

    private List<ResponsePojo> responsePojos;

    private PaymentPresenter paymentPresenter;

    private String titleMain[] = {
            "Wheels of Fortune"
    };

    int intMin;
    int intMax;
    int intHarga = 10000;
    int Jumlah;

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

//    @OnClick({R.id.btn_back, R.id.btn_next_Payment, R.id.ivMin, R.id.ivMax})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_back:
//                goHome();
//                break;
//            case R.id.ivMin:
//                intMin = Integer.valueOf(tvQty.getText().toString());
//                intMin--;
//                tvQty.setText(String.valueOf(intMin));
//
//                Jumlah = intHarga * intMin;
//                tvAmount.setText("Rp " + String.valueOf(Jumlah));
//
//                if(intMin <= 0)
//                {
//                    tvQty.setText("0");
//                    tvAmount.setText("Rp 0");
//                    Jumlah = 0;
//                }
//                break;
//            case R.id.ivMax:
//                intMax = Integer.valueOf(tvQty.getText().toString());
//                intMax++;
//                Jumlah = intHarga * intMax;
//                tvQty.setText(String.valueOf(intMax));
//                tvAmount.setText("Rp " + String.valueOf(Jumlah));
//                break;
//            case R.id.btn_next_Payment:
//                if (tvQty.getText().toString().matches("0"))
//                    popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgEmptyProduct));
//                else
//                    Fungsi.storeToSharedPref(getApplicationContext(), Jumlah, Preference.PrefJumlahHarga);
//                    Fungsi.storeToSharedPref(context, 5, Preference.PrefActiveMenu);
//                    Intent EVoucher = new Intent(this, ScanQR.class);
//                    startActivity(EVoucher);
//                break;
//        }
//    }

    @OnClick(R.id.btn_next_Payment)
    void btnNextPayment() {
        int summary = 0;
        for (int i=0; i<this.paymentAdapter.getItemCount(); i++) {
            summary += this.paymentAdapter.getSum();
        }
        Fungsi.storeToSharedPref(getApplicationContext(), summary, Preference.PrefJumlahHarga);
        Fungsi.storeToSharedPref(context, 5, Preference.PrefActiveMenu);
        Intent EVoucher = new Intent(this, ScanQR.class);
        startActivity(EVoucher);
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

    }

    @Override
    public void setListPayment(List<AvalueList> avalueLists) {
        this.paymentAdapter.addListPayment(avalueLists);
    }

}