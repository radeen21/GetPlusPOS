package id.mygetplus.getpluspos.mvp.earnpoint.presenter;

import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.IBaseViewPresenter;

public interface EarnPointContract {

    interface Presenter extends IBaseViewPresenter {
        void loadEarnPointData(POSLink posLink, String token, String cardNumber, String date,
                               String transactionId, int saleValue);
    }

    interface View {
        void setEarnPoint(ResponsePojo cekPoint);
    }
}
