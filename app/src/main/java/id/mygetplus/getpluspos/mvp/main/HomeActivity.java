package id.mygetplus.getpluspos.mvp.main;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.PopupMessege;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.ScanQR;
import id.mygetplus.getpluspos.mvp.earnpoint.view.EarnPointActivity;
import id.mygetplus.getpluspos.mvp.evoucher.view.EVoucher;
import id.mygetplus.getpluspos.mvp.login.view.LoginActivity;
import id.mygetplus.getpluspos.mvp.logout.presenter.LogoutContract;
import id.mygetplus.getpluspos.mvp.logout.presenter.LogoutPresenter;
import id.mygetplus.getpluspos.mvp.main.adapter.HomeAdapter;
import id.mygetplus.getpluspos.mvp.payment.view.PaymentActivity;
import id.mygetplus.getpluspos.mvp.tukarpoin.view.TukarPoint;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import id.mygetplus.getpluspos.service.PosLinkGenerator;


public class HomeActivity extends AppCompatActivity implements LogoutContract.View {

	LogoutPresenter logoutPresenter;
	private PopupMessege popupMessege = new PopupMessege();
	private Context context = this;

	@BindView(R.id.rec_home)
	RecyclerView recHome;

	private String titleMain[] = {
		"Cek Jumlah Point", "Tukar Point", "Beri Poin",
		"Pembayaran poin", "eVoucher", "Settlement"
	};

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ButterKnife.bind(this);

		logoutPresenter = new LogoutPresenter(this, this);
//		init();
	}

	void init()
	{
//		int[] ATTRS = new int[]{android.R.attr.listDivider};
//
//		TypedArray a = this.obtainStyledAttributes(ATTRS);
//		Drawable divider = a.getDrawable(0);
//		int inset = getResources().getDimensionPixelSize(R.dimen.nav_header_vertical_spacing);
//		InsetDrawable insetDivider = new InsetDrawable(divider, inset,
//			10, inset, 10);
//		a.recycle();
//
//		DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
//			DividerItemDecoration.VERTICAL);
//		itemDecoration.setDrawable(insetDivider);
//		recHome.addItemDecoration(itemDecoration);
//		recHome.setHasFixedSize(true);
//		recHome.setLayoutManager(new LinearLayoutManager(this));
//		HomeAdapter mainAdapter = new HomeAdapter(this, titleMain);
//		mainAdapter.setClickListener(this);
//		recHome.setAdapter(mainAdapter);
	}

//	@Override
//	public void onItemClick(View view, int position)
//	{
//		Intent intent = new Intent();
//		switch (position)
//		{
//			case 0:
//				intent = new Intent(this, ScanQR.class);
//				break;
//			case 1:
//				intent = new Intent(this, ScanQR.class);
//				break;
//			case 2:
//				Toast.makeText(this, "click oii 2", Toast.LENGTH_SHORT).show();
////                intent =  new Intent(this, SecondActivity.class);
//				break;
//			case 3:
//				Toast.makeText(this, "click oii 3", Toast.LENGTH_SHORT).show();
////                intent =  new Intent(this, SecondActivity.class);
//				break;
//			case 4:
//				Toast.makeText(this, "click oii 4", Toast.LENGTH_SHORT).show();
////                intent =  new Intent(this, SecondActivity.class);
//				break;
//			case 5:
//				Toast.makeText(this, "click oii 5", Toast.LENGTH_SHORT).show();
////                intent =  new Intent(this, SecondActivity.class);
//				break;
//		}
//		this.startActivity(intent);
//	}

	@Override
	public void getData(ResponsePojo responsePojo)
	{
		if (responsePojo.getAFaultCode().matches("0"))
		{
			GetPlusSession.getInstance(this).clearSession();
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
		else
			Toast.makeText(getApplicationContext(), responsePojo.getAFaultDescription(),
					Toast.LENGTH_SHORT).show();
	}

	private void LogoutProcess()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder
			.setTitle(R.string.titleMessege)
			.setMessage(context.getString(R.string.msgLogoutApp))
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setCancelable(false)
			.setPositiveButton(R.string.strBtnOK, (dialog, which) -> logoutPresenter
					.loadLogoutData(PosLinkGenerator.createService(context),
							GetPlusSession.getInstance(context).getUserEmail()))
			.setNegativeButton(R.string.strBtnCancel, (dialog, id) -> dialog.cancel());

		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	public void onBackPressed()
	{
		LogoutProcess();
	}

	@OnClick({R.id.btn_JumPoin, R.id.btnBeriPoin, R.id.btnBayarPoin, R.id.btnVoucherPoin,
		R.id.btnTukarPoin, R.id.Iv_logout, R.id.Iv_Settles})
	public void onViewClicked(View view)
	{
		Fungsi.storeToSharedPref(getApplicationContext(), "", Preference.PrefGetPlusID);
		Fungsi.storeToSharedPref(getApplicationContext(), "", Preference.PrefEVoucher);

		switch (view.getId())
		{
			case R.id.btn_JumPoin:
				Fungsi.storeToSharedPref(context, 1, Preference.PrefActiveMenu);
				Intent intent = new Intent(this, ScanQR.class);
				startActivity(intent);
				break;
			case R.id.btnTukarPoin:
				Intent tukarIntent = new Intent(this, TukarPoint.class);
				startActivity(tukarIntent);
				finish();
				break;
			case R.id.btnBeriPoin:
				Fungsi.storeToSharedPref(context, 2, Preference.PrefActiveMenu);
				Fungsi.storeToSharedPref(getApplicationContext(), "", Preference.PrefGetPlusID);
				Intent earnIntent = new Intent(this, EarnPointActivity.class);
				startActivity(earnIntent);
				finish();
				break;
			case R.id.btnBayarPoin:
				Intent payIntent = new Intent(this, PaymentActivity.class);
				startActivity(payIntent);
				finish();
				break;
			case R.id.btnVoucherPoin:
				Intent EVoucherintent = new Intent(this, EVoucher.class);
				startActivity(EVoucherintent);
				finish();
				break;
			case R.id.Iv_logout:
				LogoutProcess();
				break;
			case R.id.Iv_Settles:
				break;
		}
	}
}
