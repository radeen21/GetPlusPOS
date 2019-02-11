package id.mygetplus.getpluspos.mvp.login.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;

import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.BaseViewPresenter;
import id.mygetplus.getpluspos.base.ResponseSubscriber;
import id.mygetplus.getpluspos.mvp.login.model.DeviceData;
import id.mygetplus.getpluspos.mvp.login.model.LoginHolder;
import id.mygetplus.getpluspos.mvp.login.model.UserData;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter extends BaseViewPresenter implements LoginContract.Presenter {

    private Context context;
    private LoginContract.View view;

    public LoginPresenter(Context context, LoginContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void attachView(View view) {
        super.attachView(view);
    }

    @Override
    public void loadLoginData(POSLink posLink, String username, String password) {
        UserData userData = new UserData();
        userData.setUsername(username);
        userData.setPassword(password);

        TelephonyManager telephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        String deviceId = telephonyManager.getDeviceId();
        String serial = telephonyManager.getSimSerialNumber();
        String imei = telephonyManager.getImei();
        DeviceData deviceData = new DeviceData();
        deviceData.setBrand("Samsung Galaxy S9");
        deviceData.setImei(imei);
        deviceData.setSerial(serial);
        deviceData.setDeviceID(deviceId);
        deviceData.setOS("Android Nougat 8.1");

        LoginHolder loginHolder = new LoginHolder();
        loginHolder.setUserData(userData);
        loginHolder.setDeviceData(deviceData);


        posLink.getUserLogin(loginHolder).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<ResponsePojo>() {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                    }

                    @Override
                    public void onNext(ResponsePojo responsePojo) {
                        super.onNext(responsePojo);
                        String tokenSession = responsePojo.getAValue().getBToken();
                        GetPlusSession.getInstance(context).setToken(tokenSession);
                        view.getData(responsePojo);
                    }
                });
    }
}
