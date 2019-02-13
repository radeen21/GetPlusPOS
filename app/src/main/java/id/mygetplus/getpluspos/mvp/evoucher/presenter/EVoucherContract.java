package id.mygetplus.getpluspos.mvp.evoucher.presenter;

import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.IBaseViewPresenter;

public interface EVoucherContract {

    interface Presenter extends IBaseViewPresenter {
        void loadEVoucherData(POSLink posLink, AValue aValue, String GetPlusID, String VoucherID);
    }

    interface View {
        void getDataVoucher(ResponsePojo responsePojo);
    }
}
