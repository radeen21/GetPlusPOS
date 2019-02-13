package id.mygetplus.getpluspos.mvp.tukarpoin.presenter;

import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.IBaseViewPresenter;

public interface TukarPointContract {

    interface Presenter extends IBaseViewPresenter {
        void loadTukarPointData(POSLink posLink, AValue aValue, String GetPlusID, String Reff, String Amount);
    }

    interface View {
        void setTukarPoint(ResponsePojo responsePojo);
    }
}
