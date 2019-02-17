package id.mygetplus.getpluspos.mvp.login.presenter;

import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.IBaseViewPresenter;

public interface LoginContract {

    interface Presenter extends IBaseViewPresenter {
        void loadLoginData(POSLink posLink, String username, String password);
    }

    interface View {
        void getData(ResponsePojo responsePojo);
        void failedConnected();
    }
}
