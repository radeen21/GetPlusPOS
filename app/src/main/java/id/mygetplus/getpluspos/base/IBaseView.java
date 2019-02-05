package id.mygetplus.getpluspos.base;

import android.content.Context;
/**
 * Created by Ebizu-User on 13/07/2017.
 */

public interface IBaseView {

    void createView(Context context);

    void attachPresenter(IBaseViewPresenter iBaseViewPresenter);

}
