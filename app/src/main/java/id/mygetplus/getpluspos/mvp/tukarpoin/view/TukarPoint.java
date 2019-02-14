package id.mygetplus.getpluspos.mvp.tukarpoin.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import id.mygetplus.getpluspos.preference.GetPlusSession;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class TukarPoint extends AppCompatActivity implements CekPointContract.View {
    CekPointPresenter cekPointPresenter;

    @BindView(R.id.etGetPlusId)
    TextInputEditText etGetPlusID;
    @BindView(R.id.etNoReff)
    TextInputEditText etNoReff;
    @BindView(R.id.etAmount)
    TextInputEditText etAmount;
    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tukar_point);
        ButterKnife.bind(this);

        etGetPlusID.setText(Fungsi.getStringFromSharedPref(getApplicationContext(),
                Preference.PrefGetPlusID));

        cekPointPresenter = new CekPointPresenter(this, this);

        tvToolbar.setText("TUKAR POINT");
    }


    @OnClick({R.id.iv_camera, R.id.btnLanjutTukar, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_camera:
                Fungsi.storeToSharedPref(this, 3, Preference.PrefActiveMenu);
                Intent GetPlusID = new Intent(this, ScanQR.class);
                startActivity(GetPlusID);
                break;
            case R.id.btnLanjutTukar:
                cekPointPresenter.loadCekPointData(PosLinkGenerator.createService(this),
                        GetPlusSession.getInstance(this).getTokenSession(), etGetPlusID.getText().toString());
                break;
            case R.id.btn_back:
                BackHomeProcess();
                break;
        }
    }

    private void BackHomeProcess() {
        Intent BackHome = new Intent(this, HomeActivity.class);
        startActivity(BackHome);
    }

    @Override
    public void onBackPressed() {
        BackHomeProcess();
    }

    @Override
    public void setCekPoint(ResponsePojo responsePojo) {
        if (responsePojo.getAFaultCode().matches("0")) {
            Intent KonfirmasiTukar = new Intent(this, KonfirmasiTukar.class);
            KonfirmasiTukar.putExtra("GetPlusID", etGetPlusID.getText().toString());
            KonfirmasiTukar.putExtra("ReffID", etNoReff.getText().toString());
            KonfirmasiTukar.putExtra("Amount", etAmount.getText().toString());
            KonfirmasiTukar.putExtra("Nama", responsePojo.getAValue().getBDisplayValue());
            startActivity(KonfirmasiTukar);
        } else
            Toast.makeText(getApplicationContext(), responsePojo.getAFaultDescription(), Toast.LENGTH_SHORT).show();
    }

    @OnClick()
    public void onViewClicked() {
    }
}
