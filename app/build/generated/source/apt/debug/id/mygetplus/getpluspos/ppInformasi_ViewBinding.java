// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ppInformasi_ViewBinding implements Unbinder {
  private ppInformasi target;

  private View view2131230768;

  @UiThread
  public ppInformasi_ViewBinding(ppInformasi target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ppInformasi_ViewBinding(final ppInformasi target, View source) {
    this.target = target;

    View view;
    target.tvPoinBalance = Utils.findRequiredViewAsType(source, R.id.tv_jumlah, "field 'tvPoinBalance'", TextView.class);
    target.tvTransaksiID = Utils.findRequiredViewAsType(source, R.id.tv_id, "field 'tvTransaksiID'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnInformasi, "method 'onViewClicked'");
    view2131230768 = view;
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
    ppInformasi target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvPoinBalance = null;
    target.tvTransaksiID = null;

    view2131230768.setOnClickListener(null);
    view2131230768 = null;
  }
}
