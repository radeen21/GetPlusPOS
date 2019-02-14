package id.mygetplus.getpluspos.mvp.earnpoint.view;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.ScanQR;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointContract;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointPresenter;

import id.mygetplus.getpluspos.mvp.main.HomeActivity;
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

    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn_point);
        ButterKnife.bind(this);

        tvToolbar.setText("BERI POINT");
        etGetPlusID.setText(Fungsi.getStringFromSharedPref(getApplicationContext(),
                Preference.PrefGetPlusIDEarn));

        cekPointPresenter = new CekPointPresenter(this, this);
    }

    @Override
    public void onBackPressed() {
        goHome();
    }

    @Override
    public void setCekPoint(ResponsePojo cekPoint) {
        if (cekPoint.getAFaultCode().matches("0")) {
            Intent cekPoints = new Intent(this, EarnConfirmPopUp.class);
            cekPoints.putExtra("GetPlusID", etGetPlusID.getText().toString());
            cekPoints.putExtra("ReffID", etNoReff.getText().toString());
            cekPoints.putExtra("Amount", etAmount.getText().toString());
            cekPoints.putExtra("Nama Merchant", etMerchantName.getText().toString());
            cekPoints.putExtra("Nama", cekPoint.getAValue().getBDisplayValue());
            startActivity(cekPoints);
        } else
            Toast.makeText(getApplicationContext(), cekPoint.getAFaultDescription(),
                    Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_back, R.id.btnLanjutEarn, R.id.iv_camera, R.id.llBeriStruk1, R.id.llBeriStruk2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_camera:
                Fungsi.storeToSharedPref(this, 2, Preference.PrefActiveMenu);
                Intent GetPlusID = new Intent(this, ScanQR.class);
                startActivity(GetPlusID);
                break;
            case R.id.btn_back:
                goHome();
                break;
            case R.id.btnLanjutEarn:
                cekPointPresenter.loadCekPointData(PosLinkGenerator.createService(this),
                        GetPlusSession.getInstance(this).getTokenSession(), etGetPlusID.getText().toString());
                break;
            case R.id.llBeriStruk1:
                break;
            case R.id.llBeriStruk2:
                break;
        }
    }

    private void goHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
