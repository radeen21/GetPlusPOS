package id.mygetplus.getpluspos.mvp.cekpoint;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.SimValue;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointContract;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointPresenter;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class CekPointActivity extends AppCompatActivity  implements CekPointContract.View {

    CekPointPresenter cekPointPresenter;

    @BindView(R.id.tv_id)
    TextView tvId;

    @BindView(R.id.tv_jumlah)
    TextView tvJumlah;

    Dialog myDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_point);
        ButterKnife.bind(this);

        Fungsi.CheckPermission(CekPointActivity.this, this);
        initPopUp();
        myDialog = new Dialog(this);

        Fungsi.getStringFromSharedPref(this, Preference.PrefScanQRConfirm);
        String token = GetPlusSession.getInstance(this).getTokenSession();

        SimValue simValues = new SimValue();
        String cardId = Fungsi.getStringFromSharedPref(this, Preference.PrefScanQRConfirm);
        simValues.setSim1CardNumber(cardId);

        cekPointPresenter = new CekPointPresenter(this, this);
        cekPointPresenter.loadCekPointData(PosLinkGenerator.createService(this),
                token, cardId);
    }

    void initPopUp() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.6));
    }

    @Override
    public void setCekPoint(ResponsePojo userData) {
        double d = Double.parseDouble(userData.getAValue()
                .getBProgramMemberships().getBProgramMembership().getBPointsBalance());
        tvId.setText(Fungsi.getStringFromSharedPref(this, Preference.PrefScanQRConfirm));
        tvJumlah.setText(Fungsi.FormatDesimal((int) d));
    }
}
