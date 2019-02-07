package id.mygetplus.getpluspos.mvp.login.presenter;

import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.IBaseViewPresenter;
import id.mygetplus.getpluspos.mvp.login.model.UserData;

public interface LoginContract {

    interface Presenter extends IBaseViewPresenter {
        void loadLoginData(POSLink posLink);
    }

    interface View {
        void setLogin(UserData userData);
    }
}
