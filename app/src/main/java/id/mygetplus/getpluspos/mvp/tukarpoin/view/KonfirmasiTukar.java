package id.mygetplus.getpluspos.mvp.tukarpoin.view;

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
import id.mygetplus.getpluspos.mvp.evoucher.view.InformasiEvoucher;
import id.mygetplus.getpluspos.mvp.tukarpoin.presenter.TukarPointContract;
import id.mygetplus.getpluspos.mvp.tukarpoin.presenter.TukarPointPresenter;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class KonfirmasiTukar extends AppCompatActivity implements TukarPointContract.View
{
  TukarPointPresenter tukarPointPresenter;
  Dialog myDialog;
  @BindView(R.id.tv_id)
  TextView tvId;
  @BindView(R.id.tv_nama)
  TextView tvNama;
  @BindView(R.id.tv_reff)
  TextView tvReff;
  @BindView(R.id.tv_jumlah)
  TextView tvJumlah;
  private Context context = this;

  String GetPlusID;
  String ReffID;
  String Amount;
  String Nama;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.konfirmasitukar);
    ButterKnife.bind(this);

    Intent intent = getIntent();
    GetPlusID = intent.getStringExtra("GetPlusID");
    ReffID = intent.getStringExtra("ReffID");
    Amount = intent.getStringExtra("Amount");
    Nama = intent.getStringExtra("Nama");

    tvId.setText(GetPlusID);
    tvNama.setText(Nama);
    tvReff.setText(ReffID);
    tvJumlah.setText("Rp " + Fungsi.FormatDesimal(Integer.valueOf(Amount)));

    initPopUp();
    myDialog = new Dialog(this);
    tukarPointPresenter = new TukarPointPresenter(this, this);
  }

  void initPopUp()
  {
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    int width = dm.widthPixels;
    int height = dm.heightPixels;

    getWindow().setLayout((int) (width * .8), (int) (height * .7));
  }

  @OnClick({R.id.btnBatalTukar, R.id.btnLanjutConfirm})
  public void onViewClicked(View view)
  {
    switch (view.getId())
    {
      case R.id.btnBatalTukar:
        Intent BackVoucher = new Intent(this, TukarPoint.class);
        startActivity(BackVoucher);
        finish();
        break;
      case R.id.btnLanjutConfirm:
        AValue aValue = Fungsi.getObjectFromSharedPref(context, AValue.class, Preference.PrefResponsePojo);
        tukarPointPresenter.loadTukarPointData(PosLinkGenerator.createService(this), aValue,
          GetPlusID, ReffID, Amount);
        break;
    }
  }

  @Override
  public void setTukarPoint(ResponsePojo responsePojo)
  {
    if (responsePojo.getAFaultCode().matches("0"))
    {
      Intent InformasiTukar = new Intent(this, InformasiTukar.class);
      InformasiTukar.putExtra("JumlahPoin", responsePojo.getAValue().getBLoyaltyPointsBalance());
      startActivity(InformasiTukar);
      finish();
    }
    else
      Toast.makeText(getApplicationContext(), responsePojo.getAFaultDescription(), Toast.LENGTH_SHORT).show();
  }
}
