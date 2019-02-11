// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CashierHome_ViewBinding implements Unbinder {
  private CashierHome target;

  private View view2131231075;

  private View view2131230821;

  private View view2131230855;

  @UiThread
  public CashierHome_ViewBinding(CashierHome target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CashierHome_ViewBinding(final CashierHome target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.your_points, "field 'your_points' and method 'onViewClicked'");
    target.your_points = Utils.castView(view, R.id.your_points, "field 'your_points'", TextView.class);
    view2131231075 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tv_user_name = Utils.findRequiredViewAsType(source, R.id.tv_user_name, "field 'tv_user_name'", TextView.class);
    target.rv = Utils.findRequiredViewAsType(source, R.id.rv, "field 'rv'", RecyclerView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.fab, "field 'fab' and method 'onViewClicked'");
    target.fab = Utils.castView(view, R.id.fab, "field 'fab'", FloatingActionButton.class);
    view2131230821 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.et_your_number = Utils.findRequiredViewAsType(source, R.id.et_your_number, "field 'et_your_number'", EditText.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeRefreshLayout, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.ivMerchant, "field 'ivMerchant' and method 'onViewClicked'");
    target.ivMerchant = Utils.castView(view, R.id.ivMerchant, "field 'ivMerchant'", ImageView.class);
    view2131230855 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.img_profile = Utils.findRequiredViewAsType(source, R.id.img_profile, "field 'img_profile'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CashierHome target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.your_points = null;
    target.tv_user_name = null;
    target.rv = null;
    target.toolbar = null;
    target.fab = null;
    target.et_your_number = null;
    target.swipeRefreshLayout = null;
    target.ivMerchant = null;
    target.img_profile = null;

    view2131231075.setOnClickListener(null);
    view2131231075 = null;
    view2131230821.setOnClickListener(null);
    view2131230821 = null;
    view2131230855.setOnClickListener(null);
    view2131230855 = null;
  }
}
