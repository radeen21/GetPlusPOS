package id.mygetplus.getpluspos.mvp.payment.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;
import id.mygetplus.getpluspos.mvp.payment.presenter.PaymentContract;
import id.mygetplus.getpluspos.mvp.payment.presenter.PaymentPresenter;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class InformasiPayment extends AppCompatActivity  implements PaymentContract.View{
    PaymentPresenter paymentPresenter;

    Dialog myDialog;
    @BindView(R.id.tv_PaymentTukar)
    TextView tvPaymentTukar;
    private Context context = this;

    String GetPlusID;
    int Amount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informasipayment);
        ButterKnife.bind(this);

        GetPlusID = Fungsi.getStringFromSharedPref(getApplicationContext(), Preference.PrefGetPlusID);;
        Amount = Fungsi.getIntFromSharedPref(context, Preference.PrefJumlahHarga);

        initPopUp();
        myDialog = new Dialog(this);

        AValue aValue = Fungsi.getObjectFromSharedPref(context, AValue.class, Preference.PrefResponsePojo);
        paymentPresenter = new PaymentPresenter(this, this);
        paymentPresenter.loadPaymentData(PosLinkGenerator.createService(this), aValue, GetPlusID, Amount);
    }

    void initPopUp() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));
    }

    @OnClick({R.id.btnPaymentDone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPaymentDone:
                BackHomeProcess();
                break;
        }
    }

    private void BackHomeProcess() {
        Intent BackVoucher = new Intent(this, HomeActivity.class);
        startActivity(BackVoucher);
        finish();
    }

    @Override
    public void onBackPressed() {
        BackHomeProcess();
    }

    @Override
    public void setPaymentPoint(ResponsePojo responsePojo) {
        if (responsePojo.getAFaultCode().matches("0"))
        {
            double d = Double.parseDouble(responsePojo.getAValue().getBLoyaltyPointsBalance());
            tvPaymentTukar.setText(Fungsi.FormatDesimal((int) d));
        }
        else
            Toast.makeText(getApplicationContext(), responsePojo.getAFaultDescription(), Toast.LENGTH_SHORT).show();
    }
}
