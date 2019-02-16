package id.mygetplus.getpluspos.mvp.payment.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponseBrandsPojo;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.Sim1SaleTransactionLine;
import id.mygetplus.getpluspos.Sim1TransactionLines;
import id.mygetplus.getpluspos.base.BaseViewPresenter;
import id.mygetplus.getpluspos.base.ResponseListPaymentSubcriber;
import id.mygetplus.getpluspos.base.ResponsePaymentSubscriber;
import id.mygetplus.getpluspos.base.ResponseSubscriber;
import id.mygetplus.getpluspos.helper.ConfigManager;
import id.mygetplus.getpluspos.mvp.model.CekPointHolder;
import id.mygetplus.getpluspos.mvp.model.PointRequestNew;
import id.mygetplus.getpluspos.mvp.model.SimValues;
import id.mygetplus.getpluspos.mvp.payment.model.ListPaymentPojo;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PaymentPresenter extends BaseViewPresenter implements PaymentContract.Presenter {
    private Context context;
    private PaymentContract.View view;

    public PaymentPresenter(Context context, PaymentContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadPaymentData(POSLink posLink, AValue aValue, String GetPlusID, Integer Amount) {
        TelephonyManager telephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Calendar c = Calendar.getInstance();

        SimpleDateFormat dfTrans = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTrans = dfTrans.format(c.getTime());
        dateTrans = Fungsi.getDate(dateTrans, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd") + "T" +
                Fungsi.getTime(dateTrans, "yyyy-MM-dd HH:mm:ss", "HH:mm:ss");

        Random random = new Random();
        String formatted = String.format("%08d", random.nextInt(10000000) + 1);

        Sim1SaleTransactionLine sim1SaleTransactionLine = new Sim1SaleTransactionLine();
        sim1SaleTransactionLine.setSim1ProductBrand(aValue.getBDisplayValue());
        sim1SaleTransactionLine.setSim1LineValue(Integer.valueOf(Amount));

        Sim1TransactionLines sim1TransactionLines = new Sim1TransactionLines();
        sim1TransactionLines.setSim1SaleTransactionLine(sim1SaleTransactionLine);

        SimValues simValues = new SimValues();
        simValues.setSim1Cardnumber(GetPlusID);
        simValues.setSim1CashierCode(aValue.getBAccountRSN());
        simValues.setSim1CustomerAccountID(aValue.getBAccountID());
        simValues.setSim1PublishedByDisplayValue(aValue.getBDisplayValue());
        simValues.setSim1PublishedByRSN(aValue.getBAccountOwnerRSN());
        simValues.setSim1TerminalID(telephonyManager.getDeviceId());
        simValues.setSim1TransactionDate(dateTrans);
        simValues.setSim1TransactionID(formatted);
        simValues.setSim1TransactionRedemptionValue(Integer.valueOf(Amount));
        simValues.setSim1TransactionLines(sim1TransactionLines);

        PointRequestNew pointRequestNew = new PointRequestNew();
        pointRequestNew.setSimToken(aValue.getBToken());
        pointRequestNew.setSimValue(simValues);

        CekPointHolder cekPointHolder = new CekPointHolder();
        cekPointHolder.setPoinRequest(pointRequestNew);

        posLink.PaymentPoint(cekPointHolder).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<ResponsePojo>() {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                        view.setPaymentPoint(Fungsi.getObjectFromSharedPref(context,
                                ResponsePojo.class, ConfigManager.AccountSession.MSG_RESPONSE));
                    }

                    @Override
                    public void onNext(ResponsePojo responsePojo) {
                        super.onNext(responsePojo);
                        view.setPaymentPoint(responsePojo);
                    }
                });
    }

    @Override
    public void loadListPayment(POSLink posLink, String accountBrs) {
        AValue aValue = new AValue();
        aValue.setBAccountRSN(accountBrs);

        posLink.getPaymentList(accountBrs).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<ResponsePojo>(){
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                      view.setPaymentPoint(Fungsi.getObjectFromSharedPref(context, ResponsePojo.class, ConfigManager.AccountSession.MSG_RESPONSE));
                    }

                    @Override
                    public void onNext(ResponsePojo responsePojo) {
                        super.onNext(responsePojo);
                        view.setPaymentPoint(Fungsi.getObjectFromSharedPref(context, ResponsePojo.class, ConfigManager.AccountSession.MSG_RESPONSE));
                    }
                });
    }
}
