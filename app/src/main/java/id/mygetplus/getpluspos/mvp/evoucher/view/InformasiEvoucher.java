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
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.mvp.evoucher.presenter.EVoucherContract;
import id.mygetplus.getpluspos.mvp.evoucher.presenter.EVoucherPresenter;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class InformasiEvoucher extends AppCompatActivity
{
  Dialog myDialog;
  private Context context = this;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.informasievoucher);
    ButterKnife.bind(this);

    initPopUp();
    myDialog = new Dialog(this);
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

  @OnClick({R.id.btnEVoucherDone})
  public void onViewClicked(View view)
  {
    switch (view.getId())
    {
      case R.id.btnEVoucherDone:
        BackHomeProcess();
      break;
    }
  }

  private void BackHomeProcess()
  {
    Intent BackVoucher = new Intent(this, HomeActivity.class);
    startActivity(BackVoucher);
    finish();
  }

  @Override
  public void onBackPressed()
  {
    BackHomeProcess();
  }
}
