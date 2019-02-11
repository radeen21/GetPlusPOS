package id.mygetplus.getpluspos.mvp.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.mvp.login.presenter.LoginContract;
import id.mygetplus.getpluspos.mvp.login.presenter.LoginPresenter;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    LoginPresenter loginPresenter;

    @BindView(R.id.et_email)
    TextInputEditText etMail;

    @BindView(R.id.et_password)
    TextInputEditText etPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this, this);
    }

    @OnClick(R.id.btn_login)
    void onClickLogin() {
            loginPresenter.loadLoginData(PosLinkGenerator.createService(this),
                    etMail.getText().toString(), etPass.getText().toString());
    }

    @Override
    public void getData(ResponsePojo responsePojo) {
        GetPlusSession.getInstance(this).setToken(responsePojo.getAValue().getBToken());

        if (responsePojo.getAValue().getBToken().isEmpty()) {
            Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_SHORT).show();
        } else {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
        }
    }

}
