package id.mygetplus.getpluspos.mvp.earnpoint.presenter;

import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.IBaseViewPresenter;

public interface EarnPointContract {

    interface Presenter extends IBaseViewPresenter {
        void loadEarnPointData(POSLink posLink, AValue aValue, String GetPlusID, String Reff,
                               String Amount);
        void loadAmountEarnPoint(POSLink posLink, AValue aValue, String GetPlusID, String Reff,
                                 String Amount);
    }

    interface View {
        void setEarnPoint(ResponsePojo cekPoint);

    }
}
