package id.mygetplus.getpluspos.mvp.cekpoint.presenter;

import android.content.Context;

import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.BaseViewPresenter;
import id.mygetplus.getpluspos.base.ResponseSubscriber;
import id.mygetplus.getpluspos.helper.ConfigManager;
import id.mygetplus.getpluspos.mvp.model.CekPointHolder;
import id.mygetplus.getpluspos.mvp.model.PointRequestNew;
import id.mygetplus.getpluspos.mvp.model.SimValues;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CekPointPresenter extends BaseViewPresenter implements CekPointContract.Presenter {

    private Context context;
    private CekPointContract.View view;

    public CekPointPresenter(Context context, CekPointContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadCekPointData(POSLink posLink, String token, String cardNumber) {
        SimValues simValues = new SimValues();
        simValues.setSim1Cardnumber(cardNumber);
        simValues.setSim1ReturnAllMemberData("true");

        PointRequestNew pointRequestNew = new PointRequestNew();
        pointRequestNew.setSimToken(token);
        pointRequestNew.setSimValue(simValues);

        CekPointHolder cekPointHolder = new CekPointHolder();
        cekPointHolder.setPoinRequest(pointRequestNew);

        posLink.getPoints(cekPointHolder).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<ResponsePojo>() {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                      view.setCekPoint(Fungsi.getObjectFromSharedPref(context, ResponsePojo.class, ConfigManager.AccountSession.MSG_RESPONSE));
                    }

                    @Override
                    public void onNext(ResponsePojo responsePojo) {
                        super.onNext(responsePojo);
                        view.setCekPoint(responsePojo);
                    }
                });
    }
}
