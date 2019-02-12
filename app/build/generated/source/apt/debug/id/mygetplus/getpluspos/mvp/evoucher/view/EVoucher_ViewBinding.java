// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos.mvp.evoucher.view;

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

public class EVoucher_ViewBinding implements Unbinder {
  private EVoucher target;

  private View view2131230819;

  private View view2131230818;

  private View view2131230767;

  @UiThread
  public EVoucher_ViewBinding(EVoucher target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EVoucher_ViewBinding(final EVoucher target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.etGetPlusID, "field 'etGetPlusID' and method 'onViewClicked'");
    target.etGetPlusID = Utils.castView(view, R.id.etGetPlusID, "field 'etGetPlusID'", TextInputEditText.class);
    view2131230819 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.etEVoucher, "field 'etEVoucher' and method 'onViewClicked'");
    target.etEVoucher = Utils.castView(view, R.id.etEVoucher, "field 'etEVoucher'", TextInputEditText.class);
    view2131230818 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnEVoucher, "method 'onViewClicked'");
    view2131230767 = view;
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
    EVoucher target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etGetPlusID = null;
    target.etEVoucher = null;

    view2131230819.setOnClickListener(null);
    view2131230819 = null;
    view2131230818.setOnClickListener(null);
    view2131230818 = null;
    view2131230767.setOnClickListener(null);
    view2131230767 = null;
  }
}
