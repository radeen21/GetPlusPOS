package id.mygetplus.getpluspos.mvp.payment.presenter;

import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.IBaseViewPresenter;

public interface PaymentContract {
    interface Presenter extends IBaseViewPresenter {
        void loadPaymentData(POSLink posLink, AValue aValue, String GetPlusID, Integer Amount);
    }

    interface View {
        void setPaymentPoint(ResponsePojo responsePojo);
    }
}
