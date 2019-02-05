package id.mygetplus.getpluspos.mvp.login.presenter;

import android.content.Context;

import id.mygetplus.getpluspos.AccountHolder;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.base.BaseView;
import id.mygetplus.getpluspos.base.BaseViewPresenter;
import id.mygetplus.getpluspos.base.ResponseSubscriber;
import id.mygetplus.getpluspos.service.response.WrapperLogin;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter extends BaseViewPresenter implements LoginContract.Presenter {

    private Subscription subLogin;
    private Context context;

    public LoginPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void attachView(BaseView baseView) {
        super.attachView(baseView);
    }

    @Override
    public void loadLoginData(POSLink posLink) {
        AccountHolder accountHolder = new AccountHolder();
        posLink.getLogin(accountHolder).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<WrapperLogin>() {
                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                    }

                    @Override
                    public void onNext(WrapperLogin wrapperLogin) {
                        super.onNext(wrapperLogin);
                    }
                });
    }
}
