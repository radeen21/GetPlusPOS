package id.mygetplus.getpluspos.mvp.earnpoint.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

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
import id.mygetplus.getpluspos.mvp.earnpoint.presenter.EarnPointContract;
import id.mygetplus.getpluspos.mvp.earnpoint.presenter.EarnPointPresenter;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class EarnPointActivity extends AppCompatActivity implements EarnPointContract.View {

    EarnPointPresenter earnPointPresenter;

    @BindView(R.id.etGetPlusId)
    TextInputEditText etGetPlusID;

    AValue aValue;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat dfTrans;
    String dateTrans = "";
    BrandsRsp brandsRsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn_point);
        ButterKnife.bind(this);

        etGetPlusID.setText(Fungsi.getStringFromSharedPref(getApplicationContext(),
                Preference.PrefGetPlusID));

        Fungsi.getStringFromSharedPref(this, Preference.PrefScanQRConfirm);
        String token = GetPlusSession.getInstance(this).getTokenSession();

        SimValue simValues = new SimValue();
        String cardId = Fungsi.getStringFromSharedPref(this, Preference.PrefScanQRConfirm);
        simValues.setSim1CardNumber(cardId);

        dfTrans = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateTrans = dfTrans.format(c.getTime());
        dateTrans = Fungsi.getDate(dateTrans, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd") + "T" +
                Fungsi.getTime(dateTrans, "yyyy-MM-dd HH:mm:ss", "HH:mm:ss");

        brandsRsp = new BrandsRsp();
        brandsRsp = Fungsi.getObjectFromSharedPref(this, BrandsRsp.class, Preference.PrefBrandItem);
        String transactionId = String.valueOf(brandsRsp.getIntInvoice());
        int saleValue = brandsRsp.getPrice();

        earnPointPresenter = new EarnPointPresenter(this, this);
        earnPointPresenter.loadEarnPointData(PosLinkGenerator.createService(this),
                token, cardId, dateTrans, transactionId, saleValue);
    }


    @Override
    public void setEarnPoint(ResponsePojo cekPoint) {

    }

    @OnClick(R.id.iv_camera)
    void scanCameraClick() {
        Fungsi.storeToSharedPref(this, 2, Preference.PrefActiveMenu);
        Intent GetPlusID = new Intent(this, ScanQR.class);
        startActivity(GetPlusID);
    }
}
