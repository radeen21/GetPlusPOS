// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos.mvp.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import id.mygetplus.getpluspos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivity_ViewBinding implements Unbinder {
  private HomeActivity target;

  private View view2131230771;

  private View view2131230765;

  private View view2131230764;

  private View view2131230770;

  private View view2131230724;

  private View view2131230723;

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivity_ViewBinding(final HomeActivity target, View source) {
    this.target = target;

    View view;
    target.recHome = Utils.findRequiredViewAsType(source, R.id.rec_home, "field 'recHome'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.btn_JumPoin, "method 'onViewClicked'");
    view2131230771 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnBeriPoin, "method 'onViewClicked'");
    view2131230765 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnBayarPoin, "method 'onViewClicked'");
    view2131230764 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnVoucherPoin, "method 'onViewClicked'");
    view2131230770 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.Iv_logout, "method 'onViewClicked'");
    view2131230724 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.Iv_Settles, "method 'onViewClicked'");
    view2131230723 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recHome = null;

    view2131230771.setOnClickListener(null);
    view2131230771 = null;
    view2131230765.setOnClickListener(null);
    view2131230765 = null;
    view2131230764.setOnClickListener(null);
    view2131230764 = null;
    view2131230770.setOnClickListener(null);
    view2131230770 = null;
    view2131230724.setOnClickListener(null);
    view2131230724 = null;
    view2131230723.setOnClickListener(null);
    view2131230723 = null;
  }
}
