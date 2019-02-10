package id.mygetplus.getpluspos.mvp.cekpoint.presenter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;

import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.PoinRequest;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.BaseViewPresenter;
import id.mygetplus.getpluspos.base.ResponseSubscriber;
import id.mygetplus.getpluspos.mvp.cekpoint.model.CekPointHolder;
import id.mygetplus.getpluspos.mvp.cekpoint.model.PointRequestNew;
import id.mygetplus.getpluspos.mvp.cekpoint.model.SimValues;
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
    public void loadCekPointData(POSLink posLink) {
        AValue aValue = Fungsi.getObjectFromSharedPref(context, AValue.class,
                Preference.PrefMerchantInfo);

        PoinRequest poinRequest = new PoinRequest();
        poinRequest.setSimToken(aValue.getBToken());

        SimValues simValues = new SimValues();
        simValues.setSim1Cardnumber(simValues.toString());
        simValues.setSim1ReturnAllMemberData("true");

        PointRequestNew pointRequestNew = new PointRequestNew();
        pointRequestNew.setSimToken(aValue.getBToken());

        CekPointHolder cekPointHolder = new CekPointHolder();

        posLink.getPoints(cekPointHolder).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<ResponsePojo>() {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                    }

                    @Override
                    public void onNext(ResponsePojo responsePojo) {
                        super.onNext(responsePojo);
                        responsePojo.getAValue().getBToken();
                        view.setCekPoint(responsePojo);
                    }
                });
    }
}
