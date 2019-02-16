package id.mygetplus.getpluspos.mvp.logout.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.BaseViewPresenter;
import id.mygetplus.getpluspos.base.ResponseSubscriber;
import id.mygetplus.getpluspos.helper.ConfigManager;
import id.mygetplus.getpluspos.mvp.model.DeviceData;
import id.mygetplus.getpluspos.mvp.model.LoginHolder;
import id.mygetplus.getpluspos.mvp.model.UserData;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LogoutPresenter extends BaseViewPresenter implements LogoutContract.Presenter {

    private Context context;
    private LogoutContract.View view;

    public LogoutPresenter(Context context, LogoutContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void attachView(View view) {
        super.attachView(view);
    }

    @Override
    public void loadLogoutData(POSLink posLink, String username) {
        UserData userData = new UserData();
        userData.setUsername(username);

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
        deviceData.setBrand(Fungsi.DeviceName());
        deviceData.setImei(imei);
        deviceData.setSerial(serial);
        deviceData.setDeviceID(deviceId);
        deviceData.setOS(Fungsi.AndroidVersion());

        LoginHolder loginHolder = new LoginHolder();
        loginHolder.setUserData(userData);
        loginHolder.setDeviceData(deviceData);


        posLink.getUserLogout(loginHolder).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<ResponsePojo>() {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        view.getData(Fungsi.getObjectFromSharedPref(context, ResponsePojo.class, ConfigManager.AccountSession.MSG_RESPONSE));
                    }

                    @Override
                    public void onNext(ResponsePojo responsePojo) {
                        super.onNext(responsePojo);
                        view.getData(Fungsi.getObjectFromSharedPref(context, ResponsePojo.class, ConfigManager.AccountSession.MSG_RESPONSE));
                    }
                });
    }
}
