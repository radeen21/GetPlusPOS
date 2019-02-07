// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos.mvp.main.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import id.mygetplus.getpluspos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeAdapter$MainHolder_ViewBinding implements Unbinder {
  private HomeAdapter.MainHolder target;

  @UiThread
  public HomeAdapter$MainHolder_ViewBinding(HomeAdapter.MainHolder target, View source) {
    this.target = target;

    target.txtItemView = Utils.findRequiredViewAsType(source, R.id.tv_view_main, "field 'txtItemView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeAdapter.MainHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txtItemView = null;
  }
}
