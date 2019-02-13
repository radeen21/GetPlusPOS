package id.mygetplus.getpluspos.mvp.earnpoint.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
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
import id.mygetplus.getpluspos.mvp.earnpoint.presenter.EarnPointContract;
import id.mygetplus.getpluspos.mvp.earnpoint.presenter.EarnPointPresenter;
import id.mygetplus.getpluspos.mvp.tukarpoin.view.InformasiTukar;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class EarnConfirmPopUp extends AppCompatActivity implements EarnPointContract.View {

    EarnPointPresenter earnPointPresenter;

    @BindView(R.id.tv_name_user)
    TextView tvNameUser;

    @BindView(R.id.tv_getplusid)
    TextView tvGetPlusId;

    @BindView(R.id.tv_merchant)
    TextView tvMerchant;

    @BindView(R.id.tv_reff_no)
    TextView tvReffNo;

    @BindView(R.id.tv_amount)
    TextView tvAmount;

    @BindView(R.id.btn_back)
    Button btnBack;

    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;

    String getPlusID;
    String reffID;
    String merchantName;
    String amount;
    String nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_confirm_earn);
        ButterKnife.bind(this);

        btnBack.setVisibility(View.GONE);
        tvToolbar.setText("KONFIRMASI");

        Intent intent = getIntent();
        getPlusID = intent.getStringExtra("GetPlusID");
        reffID = intent.getStringExtra("ReffID");
        amount = intent.getStringExtra("Amount");
        nama = intent.getStringExtra("Nama");
        merchantName = intent.getStringExtra("Nama Merchant");

        tvGetPlusId.setText(getPlusID);
        tvNameUser.setText(nama);
        tvReffNo.setText(reffID);
        tvMerchant.setText(merchantName);
        tvAmount.setText("Rp " + Fungsi.FormatDesimal(Integer.valueOf(amount)));

        initPopUp();
        earnPointPresenter = new EarnPointPresenter(this, this);
    }

    void initPopUp()
    {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .8));
    }

    @Override
    public void setEarnPoint(ResponsePojo cekPoint) {
    {
        if (cekPoint.getAFaultCode().matches("0")) {
             Intent InformasiTukar = new Intent(this, InformasiTukar.class);
             InformasiTukar.putExtra("JumlahPoin",
                     cekPoint.getAValue().getBLoyaltyPointsBalance());
             startActivity(InformasiTukar);
             finish();
        } else
            Toast.makeText(getApplicationContext(), cekPoint.getAFaultDescription(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_next)
    void btnNext() {
        AValue aValue = Fungsi.getObjectFromSharedPref(this,
                AValue.class, Preference.PrefResponsePojo);
        earnPointPresenter.loadEarnPointData(PosLinkGenerator.createService(this), aValue,
                getPlusID, reffID, amount);
    }
}
