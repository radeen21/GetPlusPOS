package id.mygetplus.getpluspos.mvp.cekpoint.presenter;

import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.base.IBaseViewPresenter;

public interface CekPointContract {

    interface Presenter extends IBaseViewPresenter {
        void loadCekPointData(POSLink posLink, String token, String cardNumber);
        void loadjumlahCekPointData(POSLink posLink, String token, String cardNumber);
    }

    interface View {
        void setCekPoint(ResponsePojo cekPoint);
        void setAmountEarn(ResponsePojo cekJumlah);
    }
}
