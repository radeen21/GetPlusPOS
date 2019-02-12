// Generated code from Butter Knife. Do not modify!
package id.mygetplus.getpluspos.mvp.earnpoint.view;

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

public class EarnPointActivity_ViewBinding implements Unbinder {
  private EarnPointActivity target;

  private View view2131230871;

  @UiThread
  public EarnPointActivity_ViewBinding(EarnPointActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EarnPointActivity_ViewBinding(final EarnPointActivity target, View source) {
    this.target = target;

    View view;
    target.etGetPlusID = Utils.findRequiredViewAsType(source, R.id.etGetPlusId, "field 'etGetPlusID'", TextInputEditText.class);
    view = Utils.findRequiredView(source, R.id.iv_camera, "method 'scanCameraClick'");
    view2131230871 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.scanCameraClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    EarnPointActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etGetPlusID = null;

    view2131230871.setOnClickListener(null);
    view2131230871 = null;
  }
}
