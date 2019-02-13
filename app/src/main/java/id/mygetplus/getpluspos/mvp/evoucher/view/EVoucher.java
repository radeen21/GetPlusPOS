package id.mygetplus.getpluspos.mvp.evoucher.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.PopupMessege;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ScanQR;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;

public class EVoucher extends AppCompatActivity
{
//	LogoutPresenter logoutPresenter;
	private PopupMessege popupMessege = new PopupMessege();
	private Context context = this;

	@BindView(R.id.etGetPlusID)
	TextInputEditText etGetPlusID;
	@BindView(R.id.etEVoucher)
	TextInputEditText etEVoucher;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evoucher);
		ButterKnife.bind(this);

		etGetPlusID.setText(Fungsi.getStringFromSharedPref(getApplicationContext(), Preference.PrefGetPlusID));
		etEVoucher.setText(Fungsi.getStringFromSharedPref(getApplicationContext(), Preference.PrefEVoucher));
	}

	@OnClick({R.id.etGetPlusID, R.id.etEVoucher, R.id.btnEVoucher})
	public void onViewClicked(View view)
	{
		switch (view.getId())
		{
			case R.id.etGetPlusID:
				Fungsi.storeToSharedPref(context, 41, Preference.PrefActiveMenu);
				Intent GetPlusID = new Intent(this, ScanQR.class);
				startActivity(GetPlusID);
				break;
			case R.id.etEVoucher:
				Fungsi.storeToSharedPref(context, 42, Preference.PrefActiveMenu);
				Intent EVoucher = new Intent(this, ScanQR.class);
				startActivity(EVoucher);
				break;
			case R.id.btnEVoucher:
				if (TextUtils.isEmpty(etGetPlusID.getText()))
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgGetPlusIDEmpty));
				else if (TextUtils.isEmpty(etEVoucher.getText()))
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgEVoucherEmpty));
				else
				{
					Intent KonfirmasiVoucher = new Intent(this, KonfirmasiEvoucher.class);
					KonfirmasiVoucher.putExtra("GetPlusID", etGetPlusID.getText().toString());
					KonfirmasiVoucher.putExtra("VoucherID", etEVoucher.getText().toString());
					startActivity(KonfirmasiVoucher);
				}
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
