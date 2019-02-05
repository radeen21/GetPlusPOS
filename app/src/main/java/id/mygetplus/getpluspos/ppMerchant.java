package id.mygetplus.getpluspos;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ppMerchant extends Dialog
{
	@BindView(R.id.rbPertamina)
	RadioButton rbPertamina;
	@BindView(R.id.rbCGV)
	RadioButton rbCGV;
	@BindView(R.id.rbKFC)
	RadioButton rbKFC;
	@BindView(R.id.rbRanch)
	RadioButton rbRanch;
	@BindView(R.id.rbCentral)
	RadioButton rbCentral;
	@BindView(R.id.rbBliBli)
	RadioButton rbBliBli;
	@BindView(R.id.rbBCA)
	RadioButton rbBCA;
	@BindView(R.id.rbTiket)
	RadioButton rbTiket;

	private Activity ParentAct;

	public ppMerchant(Activity parentAct)
	{
		super(parentAct);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.merchant_lay);
		ButterKnife.bind(this);
	}

	@OnClick({R.id.rbPertamina, R.id.rbCGV, R.id.rbKFC, R.id.rbRanch, R.id.rbCentral, R.id.rbHero,
						R.id.rbBliBli, R.id.rbTiket, R.id.rbBCA})
	public void onViewClicked(View view)
	{
		switch(view.getId())
		{
			case R.id.rbPertamina:
				Fungsi.storeToSharedPref(getContext(), 1, Preference.PrefMerchantOwner);
				dismiss();
			break;
			case R.id.rbCGV:
				Fungsi.storeToSharedPref(getContext(), 2, Preference.PrefMerchantOwner);
				dismiss();
				break;
			case R.id.rbKFC:
				Fungsi.storeToSharedPref(getContext(), 3, Preference.PrefMerchantOwner);
				dismiss();
				break;
			case R.id.rbRanch:
				Fungsi.storeToSharedPref(getContext(), 4, Preference.PrefMerchantOwner);
				dismiss();
				break;
			case R.id.rbCentral:
				Fungsi.storeToSharedPref(getContext(), 5, Preference.PrefMerchantOwner);
				dismiss();
				break;
			case R.id.rbHero:
				Fungsi.storeToSharedPref(getContext(), 6, Preference.PrefMerchantOwner);
				dismiss();
				break;
			case R.id.rbBliBli:
				Fungsi.storeToSharedPref(getContext(), 7, Preference.PrefMerchantOwner);
				dismiss();
				break;
			case R.id.rbBCA:
				Fungsi.storeToSharedPref(getContext(), 8, Preference.PrefMerchantOwner);
				dismiss();
				break;
			case R.id.rbTiket:
				Fungsi.storeToSharedPref(getContext(), 9, Preference.PrefMerchantOwner);
				dismiss();
				break;
		}
	}
}
