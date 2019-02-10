// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ppMerchant_ViewBinding implements Unbinder {
  private ppMerchant target;

  private View view2131230928;

  private View view2131230919;

  private View view2131230927;

  private View view2131230929;

  private View view2131230920;

  private View view2131230918;

  private View view2131230917;

  private View view2131230930;

  private View view2131230926;

  @UiThread
  public ppMerchant_ViewBinding(ppMerchant target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ppMerchant_ViewBinding(final ppMerchant target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.rbPertamina, "field 'rbPertamina' and method 'onViewClicked'");
    target.rbPertamina = Utils.castView(view, R.id.rbPertamina, "field 'rbPertamina'", RadioButton.class);
    view2131230928 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbCGV, "field 'rbCGV' and method 'onViewClicked'");
    target.rbCGV = Utils.castView(view, R.id.rbCGV, "field 'rbCGV'", RadioButton.class);
    view2131230919 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbKFC, "field 'rbKFC' and method 'onViewClicked'");
    target.rbKFC = Utils.castView(view, R.id.rbKFC, "field 'rbKFC'", RadioButton.class);
    view2131230927 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbRanch, "field 'rbRanch' and method 'onViewClicked'");
    target.rbRanch = Utils.castView(view, R.id.rbRanch, "field 'rbRanch'", RadioButton.class);
    view2131230929 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbCentral, "field 'rbCentral' and method 'onViewClicked'");
    target.rbCentral = Utils.castView(view, R.id.rbCentral, "field 'rbCentral'", RadioButton.class);
    view2131230920 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbBliBli, "field 'rbBliBli' and method 'onViewClicked'");
    target.rbBliBli = Utils.castView(view, R.id.rbBliBli, "field 'rbBliBli'", RadioButton.class);
    view2131230918 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbBCA, "field 'rbBCA' and method 'onViewClicked'");
    target.rbBCA = Utils.castView(view, R.id.rbBCA, "field 'rbBCA'", RadioButton.class);
    view2131230917 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbTiket, "field 'rbTiket' and method 'onViewClicked'");
    target.rbTiket = Utils.castView(view, R.id.rbTiket, "field 'rbTiket'", RadioButton.class);
    view2131230930 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbHero, "method 'onViewClicked'");
    view2131230926 = view;
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
    ppMerchant target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rbPertamina = null;
    target.rbCGV = null;
    target.rbKFC = null;
    target.rbRanch = null;
    target.rbCentral = null;
    target.rbBliBli = null;
    target.rbBCA = null;
    target.rbTiket = null;

    view2131230928.setOnClickListener(null);
    view2131230928 = null;
    view2131230919.setOnClickListener(null);
    view2131230919 = null;
    view2131230927.setOnClickListener(null);
    view2131230927 = null;
    view2131230929.setOnClickListener(null);
    view2131230929 = null;
    view2131230920.setOnClickListener(null);
    view2131230920 = null;
    view2131230918.setOnClickListener(null);
    view2131230918 = null;
    view2131230917.setOnClickListener(null);
    view2131230917 = null;
    view2131230930.setOnClickListener(null);
    view2131230930 = null;
    view2131230926.setOnClickListener(null);
    view2131230926 = null;
  }
}
