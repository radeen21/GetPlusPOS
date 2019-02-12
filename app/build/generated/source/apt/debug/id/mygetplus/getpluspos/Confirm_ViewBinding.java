// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Confirm_ViewBinding implements Unbinder {
  private Confirm target;

  private View view2131231043;

  private View view2131230934;

  private View view2131230935;

  private View view2131230936;

  private View view2131230937;

  private View view2131230933;

  private View view2131230869;

  private View view2131230867;

  private View view2131230766;

  private View view2131230866;

  private View view2131230868;

  @UiThread
  public Confirm_ViewBinding(Confirm target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Confirm_ViewBinding(final Confirm target, View source) {
    this.target = target;

    View view;
    target.confirmtoolbar = Utils.findRequiredViewAsType(source, R.id.confirmtoolbar, "field 'confirmtoolbar'", Toolbar.class);
    target.tvConfirmDate = Utils.findRequiredViewAsType(source, R.id.tvConfirmDate, "field 'tvConfirmDate'", TextView.class);
    target.tvConfirmTime = Utils.findRequiredViewAsType(source, R.id.tvConfirmTime, "field 'tvConfirmTime'", TextView.class);
    target.tvConfirmRef = Utils.findRequiredViewAsType(source, R.id.tvConfirmRef, "field 'tvConfirmRef'", TextView.class);
    target.tvIsiTotal = Utils.findRequiredViewAsType(source, R.id.tvIsiTotal, "field 'tvIsiTotal'", TextView.class);
    target.tvIsiBayar = Utils.findRequiredViewAsType(source, R.id.tvIsiBayar, "field 'tvIsiBayar'", TextView.class);
    target.tvIsiTipe = Utils.findRequiredViewAsType(source, R.id.tvIsiTipe, "field 'tvIsiTipe'", TextView.class);
    target.tvDetailBrand = Utils.findRequiredViewAsType(source, R.id.tvDetailBrand, "field 'tvDetailBrand'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tvConfirmInvoice, "field 'tvConfirmInvoice' and method 'onViewClicked'");
    target.tvConfirmInvoice = Utils.castView(view, R.id.tvConfirmInvoice, "field 'tvConfirmInvoice'", TextView.class);
    view2131231043 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvMerchantName = Utils.findRequiredViewAsType(source, R.id.tvMerchantName, "field 'tvMerchantName'", TextView.class);
    target.tvPoin = Utils.findRequiredViewAsType(source, R.id.tvPoin, "field 'tvPoin'", TextView.class);
    target.tvIsiPoin = Utils.findRequiredViewAsType(source, R.id.tvIsiPoin, "field 'tvIsiPoin'", TextView.class);
    target.etCardNumber = Utils.findRequiredViewAsType(source, R.id.etCardNumber, "field 'etCardNumber'", EditText.class);
    target.etNoVoucher = Utils.findRequiredViewAsType(source, R.id.etNoVoucher, "field 'etNoVoucher'", EditText.class);
    target.etConfirmAdjust = Utils.findRequiredViewAsType(source, R.id.etConfirmAdjust, "field 'etConfirmAdjust'", EditText.class);
    target.llIsiVoucher = Utils.findRequiredViewAsType(source, R.id.llIsiVoucher, "field 'llIsiVoucher'", LinearLayout.class);
    target.llConfirmAdjust = Utils.findRequiredViewAsType(source, R.id.llConfirmAdjust, "field 'llConfirmAdjust'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.rbConfirmCash, "field 'rbConfirmCash' and method 'onViewClicked'");
    target.rbConfirmCash = Utils.castView(view, R.id.rbConfirmCash, "field 'rbConfirmCash'", RadioButton.class);
    view2131230934 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbConfirmElectronic, "field 'rbConfirmElectronic' and method 'onViewClicked'");
    target.rbConfirmElectronic = Utils.castView(view, R.id.rbConfirmElectronic, "field 'rbConfirmElectronic'", RadioButton.class);
    view2131230935 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbConfirmPoin, "field 'rbConfirmPoin' and method 'onViewClicked'");
    target.rbConfirmPoin = Utils.castView(view, R.id.rbConfirmPoin, "field 'rbConfirmPoin'", RadioButton.class);
    view2131230936 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbConfirmVoucher, "field 'rbConfirmVoucher' and method 'onViewClicked'");
    target.rbConfirmVoucher = Utils.castView(view, R.id.rbConfirmVoucher, "field 'rbConfirmVoucher'", RadioButton.class);
    view2131230937 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rbConfirmAdjust, "field 'rbConfirmAdjust' and method 'onViewClicked'");
    target.rbConfirmAdjust = Utils.castView(view, R.id.rbConfirmAdjust, "field 'rbConfirmAdjust'", RadioButton.class);
    view2131230933 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivSearchCardNumber, "method 'onViewClicked'");
    view2131230869 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivScanCardNumber, "method 'onViewClicked'");
    view2131230867 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnConfirm, "method 'onViewClicked'");
    view2131230766 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivNoVoucher, "method 'onViewClicked'");
    view2131230866 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivScanNoVoucher, "method 'onViewClicked'");
    view2131230868 = view;
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
    Confirm target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.confirmtoolbar = null;
    target.tvConfirmDate = null;
    target.tvConfirmTime = null;
    target.tvConfirmRef = null;
    target.tvIsiTotal = null;
    target.tvIsiBayar = null;
    target.tvIsiTipe = null;
    target.tvDetailBrand = null;
    target.tvConfirmInvoice = null;
    target.tvMerchantName = null;
    target.tvPoin = null;
    target.tvIsiPoin = null;
    target.etCardNumber = null;
    target.etNoVoucher = null;
    target.etConfirmAdjust = null;
    target.llIsiVoucher = null;
    target.llConfirmAdjust = null;
    target.rbConfirmCash = null;
    target.rbConfirmElectronic = null;
    target.rbConfirmPoin = null;
    target.rbConfirmVoucher = null;
    target.rbConfirmAdjust = null;

    view2131231043.setOnClickListener(null);
    view2131231043 = null;
    view2131230934.setOnClickListener(null);
    view2131230934 = null;
    view2131230935.setOnClickListener(null);
    view2131230935 = null;
    view2131230936.setOnClickListener(null);
    view2131230936 = null;
    view2131230937.setOnClickListener(null);
    view2131230937 = null;
    view2131230933.setOnClickListener(null);
    view2131230933 = null;
    view2131230869.setOnClickListener(null);
    view2131230869 = null;
    view2131230867.setOnClickListener(null);
    view2131230867 = null;
    view2131230766.setOnClickListener(null);
    view2131230766 = null;
    view2131230866.setOnClickListener(null);
    view2131230866 = null;
    view2131230868.setOnClickListener(null);
    view2131230868 = null;
  }
}
