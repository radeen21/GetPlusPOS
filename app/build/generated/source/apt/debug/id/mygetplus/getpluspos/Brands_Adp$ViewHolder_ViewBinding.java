// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Brands_Adp$ViewHolder_ViewBinding implements Unbinder {
  private Brands_Adp.ViewHolder target;

  private View view2131230863;

  private View view2131230884;

  private View view2131231040;

  @UiThread
  public Brands_Adp$ViewHolder_ViewBinding(final Brands_Adp.ViewHolder target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ivBrandImage, "field 'ivBrandImage' and method 'onViewClicked'");
    target.ivBrandImage = Utils.castView(view, R.id.ivBrandImage, "field 'ivBrandImage'", ImageView.class);
    view2131230863 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.llBrandGroup, "field 'llBrandGroup' and method 'onViewClicked'");
    target.llBrandGroup = Utils.castView(view, R.id.llBrandGroup, "field 'llBrandGroup'", LinearLayout.class);
    view2131230884 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvBrandName = Utils.findRequiredViewAsType(source, R.id.tvBrandName, "field 'tvBrandName'", TextView.class);
    target.tvBrandDetail = Utils.findRequiredViewAsType(source, R.id.tvBrandDetail, "field 'tvBrandDetail'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tvBrandPrice, "field 'tvBrandPrice' and method 'onViewClicked'");
    target.tvBrandPrice = Utils.castView(view, R.id.tvBrandPrice, "field 'tvBrandPrice'", TextView.class);
    view2131231040 = view;
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
    Brands_Adp.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBrandImage = null;
    target.llBrandGroup = null;
    target.tvBrandName = null;
    target.tvBrandDetail = null;
    target.tvBrandPrice = null;

    view2131230863.setOnClickListener(null);
    view2131230863 = null;
    view2131230884.setOnClickListener(null);
    view2131230884 = null;
    view2131231040.setOnClickListener(null);
    view2131231040 = null;
  }
}
