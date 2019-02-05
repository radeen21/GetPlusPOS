// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScanQR_ViewBinding implements Unbinder {
  private ScanQR target;

  private View view2131230847;

  private View view2131230766;

  @UiThread
  public ScanQR_ViewBinding(ScanQR target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ScanQR_ViewBinding(final ScanQR target, View source) {
    this.target = target;

    View view;
    target.txt_flash = Utils.findRequiredViewAsType(source, R.id.txt_flash, "field 'txt_flash'", TextView.class);
    target.cameraPreview = Utils.findRequiredViewAsType(source, R.id.cameraPreview, "field 'cameraPreview'", SurfaceView.class);
    view = Utils.findRequiredView(source, R.id.ivBackQR, "method 'onViewClicked'");
    view2131230847 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_flash, "method 'onViewClicked'");
    view2131230766 = view;
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
    ScanQR target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.txt_flash = null;
    target.cameraPreview = null;

    view2131230847.setOnClickListener(null);
    view2131230847 = null;
    view2131230766.setOnClickListener(null);
    view2131230766 = null;
  }
}
