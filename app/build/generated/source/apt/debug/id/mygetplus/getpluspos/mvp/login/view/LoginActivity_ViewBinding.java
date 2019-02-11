// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos.mvp.login.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import id.mygetplus.getpluspos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131230765;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.etMail = Utils.findRequiredViewAsType(source, R.id.et_email, "field 'etMail'", TextInputEditText.class);
    target.etPass = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'etPass'", TextInputEditText.class);
    view = Utils.findRequiredView(source, R.id.btn_login, "method 'onClickLogin'");
    view2131230765 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickLogin();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etMail = null;
    target.etPass = null;

    view2131230765.setOnClickListener(null);
    view2131230765 = null;
  }
}
