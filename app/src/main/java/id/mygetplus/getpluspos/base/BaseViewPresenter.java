package id.mygetplus.getpluspos.base;

/**
 * Created by Ebizu-User on 13/07/2017.
 */

public abstract class BaseViewPresenter implements IBaseViewPresenter {

    private BaseView baseView;


    @Override
    public void attachView(BaseView baseView) {
        this.baseView = baseView;
    }

}
