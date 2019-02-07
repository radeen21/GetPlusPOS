package id.mygetplus.getpluspos.mvp.cekpoint.presenter;

import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.base.IBaseViewPresenter;
import id.mygetplus.getpluspos.mvp.login.model.UserData;

public interface CekPointContract {

    interface Presenter extends IBaseViewPresenter {
        void loadCekPointData(POSLink posLink);
    }

    interface View {
        void setCekPoint(UserData userData);
    }
}
