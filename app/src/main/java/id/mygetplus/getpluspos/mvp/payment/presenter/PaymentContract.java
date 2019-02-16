package id.mygetplus.getpluspos.mvp.payment.presenter;

import java.util.ArrayList;
import java.util.List;

import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.BrandsRsp;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.IBaseViewPresenter;

public interface PaymentContract {
    interface Presenter extends IBaseViewPresenter {
        void loadPaymentData(POSLink posLink, AValue aValue, String GetPlusID, Integer Amount);
        void loadListPayment(POSLink posLink, String accountBrs);
        void loadList (POSLink posLink);
    }

    interface View {
        void setPaymentPoint(ResponsePojo responsePojo);
        void setListPayment(List<BrandsRsp> brandsRsps);
    }
}
