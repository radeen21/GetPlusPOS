package id.mygetplus.getpluspos.mvp.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.mvp.login.presenter.LoginContract;
import id.mygetplus.getpluspos.mvp.login.presenter.LoginPresenter;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    LoginPresenter loginPresenter;
    POSLink posLink;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);
        loginPresenter.loadLoginData(PosLinkGenerator.createService(this));
    }


    @Override
    public void setLoginView() {

    }
}
