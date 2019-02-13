package id.mygetplus.getpluspos.mvp.earnpoint.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.BrandsRsp;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.ScanQR;
import id.mygetplus.getpluspos.mvp.earnpoint.presenter.EarnPointContract;
import id.mygetplus.getpluspos.mvp.earnpoint.presenter.EarnPointPresenter;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;

public class EarnPointActivity extends AppCompatActivity implements EarnPointContract.View
{

  EarnPointPresenter earnPointPresenter;

  @BindView(R.id.etGetPlusId)
  TextInputEditText etGetPlusID;

  @BindView(R.id.etMemberId)
  TextInputEditText etMemberId;
  @BindView(R.id.etMechantName)
  TextInputEditText etMechantName;
  @BindView(R.id.etNoReff)
  TextInputEditText etNoReff;
  @BindView(R.id.etAmount)
  TextInputEditText etAmount;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_earn_point);
    ButterKnife.bind(this);

    etGetPlusID.setText(Fungsi.getStringFromSharedPref(getApplicationContext(),
      Preference.PrefGetPlusID));

    earnPointPresenter = new EarnPointPresenter(this, this);
  }


  @Override
  public void setEarnPoint(ResponsePojo cekPoint)
  {

  }

  @OnClick({R.id.iv_camera, R.id.btnLanjutEarn})
  public void onViewClicked(View view)
  {
    switch (view.getId())
    {
      case R.id.iv_camera:
        Fungsi.storeToSharedPref(this, 2, Preference.PrefActiveMenu);
        Intent GetPlusID = new Intent(this, ScanQR.class);
        startActivity(GetPlusID);
      break;
      case R.id.btnLanjutEarn:
//        earnPointPresenter.loadEarnPointData(PosLinkGenerator.createService(this), token, cardId, dateTrans, transactionId, saleValue);
      break;
    }
  }

  private void BackHomeProcess()
  {
    Intent BackHome = new Intent(this, HomeActivity.class);
    startActivity(BackHome);
  }

  @Override
  public void onBackPressed()
  {
    BackHomeProcess();
  }
}
