package id.mygetplus.getpluspos.mvp.earnpoint.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.BrandsRsp;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.ScanQR;
import id.mygetplus.getpluspos.SimValue;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointContract;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointPresenter;
import id.mygetplus.getpluspos.mvp.earnpoint.presenter.EarnPointContract;
import id.mygetplus.getpluspos.mvp.earnpoint.presenter.EarnPointPresenter;
import id.mygetplus.getpluspos.mvp.tukarpoin.view.KonfirmasiTukar;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class EarnPointActivity extends AppCompatActivity implements CekPointContract.View {

    CekPointPresenter cekPointPresenter;

    @BindView(R.id.etGetPlusId)
    TextInputEditText etGetPlusID;

    @BindView(R.id.etNoReff)
    TextInputEditText etNoReff;

    @BindView(R.id.etAmount)
    TextInputEditText etAmount;

    @BindView(R.id.etMechantName)
    TextInputEditText etMerchantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn_point);
        ButterKnife.bind(this);

        etGetPlusID.setText(Fungsi.getStringFromSharedPref(getApplicationContext(),
                Preference.PrefGetPlusID));

        cekPointPresenter = new CekPointPresenter(this, this);
    }

    @OnClick(R.id.iv_camera)
    void scanCameraClick() {
        Fungsi.storeToSharedPref(this, 2, Preference.PrefActiveMenu);
        Intent GetPlusID = new Intent(this, ScanQR.class);
        startActivity(GetPlusID);
    }

    @OnClick(R.id.btnLanjutEarn)
    void nextBtn() {
        cekPointPresenter.loadCekPointData(PosLinkGenerator.createService(this),
                GetPlusSession.getInstance(this).getTokenSession(), etGetPlusID.getText().toString());
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void setCekPoint(ResponsePojo cekPoint) {
        if (cekPoint.getAFaultCode().matches("0")) {
            Intent cekPoints = new Intent(this, KonfirmasiTukar.class);
            cekPoints.putExtra("GetPlusID", etGetPlusID.getText().toString());
            cekPoints.putExtra("ReffID", etNoReff.getText().toString());
            cekPoints.putExtra("Amount", etAmount.getText().toString());
            cekPoints.putExtra("Nama Merchant", etMerchantName.getText().toString());
            startActivity(cekPoints);
        } else
            Toast.makeText(getApplicationContext(), cekPoint.getAFaultDescription(),
                    Toast.LENGTH_SHORT).show();
    }
}
