package id.mygetplus.getpluspos.mvp.evoucher.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.SimValue;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointContract;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointPresenter;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class KonfirmasiEvoucher extends AppCompatActivity
{
	Dialog myDialog;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.konfirmasievoucher);
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
}
