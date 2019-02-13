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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;

public class InformasiTukar extends AppCompatActivity
{
  Dialog myDialog;
  private Context context = this;

  @BindView(R.id.tv_PoinTukar)
  TextView tvPoinTukar;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.informasitukar);
    ButterKnife.bind(this);

    Intent intent = getIntent();
    double d = Double.parseDouble(intent.getStringExtra("JumlahPoin"));
    tvPoinTukar.setText(Fungsi.FormatDesimal((int) d));

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

  @OnClick({R.id.btnTukarDone})
  public void onViewClicked(View view)
  {
    switch (view.getId())
    {
      case R.id.btnTukarDone:
        ToHome();
        break;
    }
  }

  private void ToHome()
  {
    Intent intent = new Intent(this, HomeActivity.class);
    startActivity(intent);
    finish();
  }

  @Override
  public void onBackPressed()
  {
    ToHome();
  }
}
