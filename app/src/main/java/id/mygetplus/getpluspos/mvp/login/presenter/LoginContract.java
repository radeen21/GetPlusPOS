package id.mygetplus.getpluspos.mvp.login.presenter;

import id.mygetplus.getpluspos.FixValue;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.base.IBaseViewPresenter;

public interface LoginContract {

    interface Presenter extends IBaseViewPresenter {
        void loadLoginData(POSLink posLink);
    }

    interface View {
        void setLoginView();
    }
}
