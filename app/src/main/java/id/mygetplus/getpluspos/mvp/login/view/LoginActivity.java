package id.mygetplus.getpluspos.mvp.login.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.mvp.login.model.DeviceData;
import id.mygetplus.getpluspos.mvp.login.model.UserData;
import id.mygetplus.getpluspos.mvp.login.presenter.LoginContract;
import id.mygetplus.getpluspos.mvp.login.presenter.LoginPresenter;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    LoginPresenter loginPresenter;
    GetPlusSession getPlusSession;

    @BindView(R.id.et_email)
    TextInputEditText etMail;

    @BindView(R.id.et_password)
    TextInputEditText etPass;

    @BindView(R.id.btn_login)
    Button btnLogin;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this, this);
        loginPresenter.loadLoginData(PosLinkGenerator.createService(this));


    }

    @Override
    public void setLogin(UserData userData) {
        etMail.setText(userData.getUsername());
        etPass.setText(userData.getPassword());

        btnLogin.setOnClickListener(v -> {
            if(etMail.getText().toString().equals(userData.getUsername()) &&
                    etPass.getText().toString().equals(userData.getPassword())) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getData(ResponsePojo responsePojo) {
        getPlusSession.setSession(responsePojo);
    }
}
