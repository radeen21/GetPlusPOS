// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.SurfaceView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScanQR_ViewBinding implements Unbinder {
  private ScanQR target;

  @UiThread
  public ScanQR_ViewBinding(ScanQR target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ScanQR_ViewBinding(ScanQR target, View source) {
    this.target = target;

    target.cameraPreview = Utils.findRequiredViewAsType(source, R.id.cameraPreview, "field 'cameraPreview'", SurfaceView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ScanQR target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cameraPreview = null;
  }
}
