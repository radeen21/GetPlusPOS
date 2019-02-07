package id.mygetplus.getpluspos.base;

import android.view.View;

import id.mygetplus.getpluspos.mvp.login.presenter.LoginContract;

/**
 * Created by Ebizu-User on 13/07/2017.
 */

public abstract class BaseViewPresenter implements IBaseViewPresenter {

    private View view;

    @Override
    public void attachView(View view) {
        this.view = view;
    }

}
