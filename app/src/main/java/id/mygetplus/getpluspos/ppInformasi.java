package id.mygetplus.getpluspos;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ppInformasi extends Dialog
{
	@BindView(R.id.tvPoinBalance)
	TextView tvPoinBalance;
	@BindView(R.id.tvTransaksiID)
	TextView tvTransaksiID;

	private Activity ParentAct;
	String PoinBalance;
	String TransaksiID;

	public ppInformasi(Activity parentAct, String PoinBalance, String TransaksiID)
	{
		super(parentAct);
		this.PoinBalance = PoinBalance;
		this.TransaksiID = TransaksiID;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.informasi_lay);
		ButterKnife.bind(this);

		double d = Double.parseDouble(PoinBalance);
		tvPoinBalance.setText(getContext().getString(R.string.strPointsBalance, Fungsi.FormatDesimal((int) d)));
		tvTransaksiID.setText(getContext().getString(R.string.strTransactionID, TransaksiID));
	}

	@OnClick({R.id.btnInformasi})
	public void onViewClicked(View view)
	{
		switch(view.getId())
		{
			case R.id.btnInformasi:
				cancel();
			break;
		}
	}
}
