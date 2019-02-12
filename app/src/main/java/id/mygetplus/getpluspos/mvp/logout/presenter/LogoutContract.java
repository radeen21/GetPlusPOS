package id.mygetplus.getpluspos.mvp.logout.presenter;

import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.IBaseViewPresenter;

public interface LogoutContract {

    interface Presenter extends IBaseViewPresenter {
        void loadLogoutData(POSLink posLink, String username);
    }

    interface View {
        void getData(ResponsePojo responsePojo);
    }
}
