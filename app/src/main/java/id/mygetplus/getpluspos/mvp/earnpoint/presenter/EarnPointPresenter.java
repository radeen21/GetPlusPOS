package id.mygetplus.getpluspos.mvp.earnpoint.presenter;

import android.content.Context;

import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.PoinRequest;
import id.mygetplus.getpluspos.RequestHolder;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.SimValue;
import id.mygetplus.getpluspos.base.BaseViewPresenter;
import id.mygetplus.getpluspos.base.ResponseSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EarnPointPresenter  extends BaseViewPresenter implements EarnPointContract.Presenter {

    private Context context;
    private EarnPointContract.View view;

    public EarnPointPresenter(Context context, EarnPointContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadEarnPointData(POSLink posLink, String token, String cardNumber, String date) {
        AValue aValue = new AValue();

        SimValue simValues = new SimValue();
        simValues.setSim1CardNumber(cardNumber);
        simValues.setSim1CashierCode(aValue.getBAccountRSN());
        simValues.setSim1ClientVersion("1");
        simValues.setSim1CustomerAccountID(aValue.getBAccountID());
        simValues.setSim1PublishedByDisplayValue(aValue.getBDisplayValue());
        simValues.setSim1PublishedByRSN(aValue.getBAccountOwnerRSN());
        simValues.setSim1TerminalID("123456");
        simValues.setSim1TransactionDate(date);
//        simValues.setSim1TransactionID(String.valueOf(transactionId));
        simValues.setSim1TransactionNotes("Pembelian DI hero Supermarket");
//        simValues.setSim1TransactionSaleValue(saleValue);

        PoinRequest poinRequest = new PoinRequest();
        poinRequest.setSimToken(token);
        poinRequest.setSimValue(simValues);

        RequestHolder requestHolder = new RequestHolder();
        requestHolder.setPoinRequest(poinRequest);

        posLink.getEarnPoint(requestHolder).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<ResponsePojo>() {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                    }

                    @Override
                    public void onNext(ResponsePojo responsePojo) {
                        super.onNext(responsePojo);
                        view.setEarnPoint(responsePojo);
                    }
                });
    }
}
