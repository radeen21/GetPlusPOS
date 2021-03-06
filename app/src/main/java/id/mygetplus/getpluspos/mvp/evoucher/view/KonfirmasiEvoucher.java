package id.mygetplus.getpluspos.mvp.evoucher.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.mvp.evoucher.presenter.EVoucherContract;
import id.mygetplus.getpluspos.mvp.evoucher.presenter.EVoucherPresenter;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class KonfirmasiEvoucher extends AppCompatActivity implements EVoucherContract.View
{
  EVoucherPresenter eVoucherPresenter;
  Dialog myDialog;
  private Context context = this;
  String GetPlusID;
  String VoucherID;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.konfirmasievoucher);
    ButterKnife.bind(this);

    Intent intent = getIntent();
    GetPlusID = intent.getStringExtra("GetPlusID");
    VoucherID = intent.getStringExtra("VoucherID");

    initPopUp();
    myDialog = new Dialog(this);
    eVoucherPresenter = new EVoucherPresenter(this, this);
  }

  void initPopUp()
  {
    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);
    getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    int width = dm.widthPixels;
    int height = dm.heightPixels;

    getWindow().setLayout((int) (width * .8), (int) (height * .6));
  }

  @OnClick({R.id.btnBatalVoucher, R.id.btnLanjutVoucher})
  public void onViewClicked(View view)
  {
    switch (view.getId())
    {
      case R.id.btnBatalVoucher:
        Intent BackVoucher = new Intent(this, EVoucher.class);
        startActivity(BackVoucher);
        finish();
        break;
      case R.id.btnLanjutVoucher:
        AValue aValue = Fungsi.getObjectFromSharedPref(context, AValue.class, Preference.PrefResponsePojo);
        eVoucherPresenter.loadEVoucherData(PosLinkGenerator.createService(context), aValue, GetPlusID, VoucherID);
        break;
    }
  }

  @Override
  public void getDataVoucher(ResponsePojo responsePojo)
  {
    if (responsePojo.getAFaultCode().matches("0"))
    {
      Intent intent = new Intent(this, InformasiEvoucher.class);
      startActivity(intent);
      finish();
    }
    else
      Toast.makeText(getApplicationContext(), responsePojo.getAFaultDescription(), Toast.LENGTH_SHORT).show();
  }
}
