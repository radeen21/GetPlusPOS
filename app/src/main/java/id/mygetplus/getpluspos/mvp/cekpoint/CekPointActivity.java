package id.mygetplus.getpluspos.mvp.cekpoint;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

import butterknife.BindView;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointContract;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointPresenter;
import id.mygetplus.getpluspos.mvp.login.model.UserData;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class CekPointActivity extends AppCompatActivity  implements CekPointContract.View {

    CekPointPresenter cekPointPresenter;

    @BindView(R.id.tv_id)
    TextView tvId;

    @BindView(R.id.tv_jumlah)
    TextView tvJumlah;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_point);
        initPopUp();

        cekPointPresenter = new CekPointPresenter(this);
        cekPointPresenter.loadCekPointData(PosLinkGenerator.createService(this));
    }

    void initPopUp() {

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.6));
    }

    @Override
    public void setCekPoint(UserData userData) {

    }
}
