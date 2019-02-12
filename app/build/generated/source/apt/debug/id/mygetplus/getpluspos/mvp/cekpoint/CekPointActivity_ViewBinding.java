// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos.mvp.cekpoint;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import id.mygetplus.getpluspos.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CekPointActivity_ViewBinding implements Unbinder {
  private CekPointActivity target;

  private View view2131230775;

  @UiThread
  public CekPointActivity_ViewBinding(CekPointActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CekPointActivity_ViewBinding(final CekPointActivity target, View source) {
    this.target = target;

    View view;
    target.tvId = Utils.findRequiredViewAsType(source, R.id.tv_id, "field 'tvId'", TextView.class);
    target.tvJumlah = Utils.findRequiredViewAsType(source, R.id.tv_jumlah, "field 'tvJumlah'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_ok_points, "method 'onViewClicked'");
    view2131230775 = view;
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
    CekPointActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvId = null;
    target.tvJumlah = null;

    view2131230775.setOnClickListener(null);
    view2131230775 = null;
  }
}
