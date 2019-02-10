package id.mygetplus.getpluspos.mvp.cekpoint;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.BrandsRsp;
import id.mygetplus.getpluspos.CashierHome;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointContract;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointPresenter;
import id.mygetplus.getpluspos.mvp.login.model.UserData;
import id.mygetplus.getpluspos.service.PosLinkGenerator;
import retrofit2.Callback;

public class CekPointActivity extends AppCompatActivity  implements CekPointContract.View {

    CekPointPresenter cekPointPresenter;

    @BindView(R.id.tv_id)
    TextView tvId;

    @BindView(R.id.tv_jumlah)
    TextView tvJumlah;
    String PoinBalance;
    String TransaksiID;
    private Activity ParentAct;

    Dialog myDialog;
    String strVoucher="";
    BrandsRsp brandsRsp;
    AValue aValue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_point);

        Fungsi.CheckPermission(CekPointActivity.this, this);
        initPopUp();
        myDialog = new Dialog(this);

        Fungsi.getStringFromSharedPref(this, Preference.PrefScanQRConfirm);

        cekPointPresenter = new CekPointPresenter(this, this);
        cekPointPresenter.loadCekPointData(PosLinkGenerator.createService(this));

//        double d = Double.parseDouble(PoinBalance);
//        tvJumlah.setText(this.getString(R.string.strPointsBalance, Fungsi.FormatDesimal((int) d)));
//        tvId.setText(this.getString(R.string.strTransactionID, TransaksiID));
//        myDialog.setContentView(R.layout.popup_point);

//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
    }

    void initPopUp() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.6));
    }

    @Override
    public void setCekPoint(ResponsePojo userData) {
        Toast.makeText(this, userData.getAValue().getBTransactionID(), Toast.LENGTH_SHORT).show();
    }
}
