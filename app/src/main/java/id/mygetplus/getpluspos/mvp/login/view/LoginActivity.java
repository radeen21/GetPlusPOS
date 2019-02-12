package id.mygetplus.getpluspos.mvp.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import id.mygetplus.getpluspos.mvp.login.presenter.LoginContract;
import id.mygetplus.getpluspos.mvp.login.presenter.LoginPresenter;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class LoginActivity extends AppCompatActivity implements LoginContract.View
{

	LoginPresenter loginPresenter;
	private PopupMessege popupMessege = new PopupMessege();
	private Context context = this;

	@BindView(R.id.et_email)
	TextInputEditText etMail;

	@BindView(R.id.et_password)
	TextInputEditText etPass;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);

		Fungsi.CheckPermission(LoginActivity.this, context);
		loginPresenter = new LoginPresenter(this, this);
	}

	@Override
	public void getData(ResponsePojo responsePojo)
	{
		if (responsePojo.getAFaultCode().matches("0"))
		{
			if (TextUtils.isEmpty(responsePojo.getAValue().getBToken()))
				Toast.makeText(getApplicationContext(), context.getString(R.string.msgLoginFailed), Toast.LENGTH_SHORT).show();
			else
			{
				GetPlusSession.getInstance(this).setToken(responsePojo.getAValue().getBToken());

				Intent intent = new Intent(this, HomeActivity.class);
				startActivity(intent);
			}
		}
		else
			Toast.makeText(getApplicationContext(), responsePojo.getAFaultDescription(), Toast.LENGTH_SHORT).show();
	}

	@OnClick(R.id.btn_login)
	public void onViewClicked(View view)
	{
		switch(view.getId())
		{
			case R.id.btn_login:
				if (TextUtils.isEmpty(etMail.getText()))
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgUsernameEmpty));
				else if (TextUtils.isEmpty(etPass.getText()))
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgPasswordEmpty));
				else
				{
					GetPlusSession.getInstance(this).setUserEmail(etMail.getText().toString());
					loginPresenter.loadLoginData(PosLinkGenerator.createService(context), etMail.getText().toString(), etPass.getText().toString());
				}
			break;
		}
	}

	@Override
	public void onBackPressed()
	{
		moveTaskToBack(true);
	}
}
