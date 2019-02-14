package id.mygetplus.getpluspos;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.mygetplus.getpluspos.mvp.cekpoint.CekPointActivity;
import id.mygetplus.getpluspos.mvp.earnpoint.view.EarnPointActivity;
import id.mygetplus.getpluspos.mvp.evoucher.view.EVoucher;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;
import id.mygetplus.getpluspos.mvp.payment.view.InformasiPayment;
import id.mygetplus.getpluspos.mvp.payment.view.PaymentActivity;
import id.mygetplus.getpluspos.mvp.tukarpoin.view.TukarPoint;

public class ScanQR extends AppCompatActivity implements SurfaceHolder.Callback {
  @BindView(R.id.cameraPreview)
  SurfaceView cameraPreview;

  private String TAG = "[Scan QR]";
  private CameraManager cameraManager;
  private Context context = this;
  private ImageScanner mScanner;
  private boolean hasSurface;
  private boolean isInitCameraProcess = false;

  int intActiveMenu = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.scanqrkartu_lay);
    ButterKnife.bind(this);

    cameraManager = new CameraManager(this, barcodeCallback);
    hasSurface = false;
    intActiveMenu = Fungsi.getIntFromSharedPref(context, Preference.PrefActiveMenu);
  }

  @Override
  public void surfaceCreated(SurfaceHolder surfaceHolder)
  {
    if(!hasSurface) hasSurface = true;
  }

  @Override
  public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2)
  {

  }

  @Override
  public void surfaceDestroyed(SurfaceHolder surfaceHolder)
  {
    hasSurface = false;
  }

  private Camera.PreviewCallback barcodeCallback = new Camera.PreviewCallback()
  {
    public void onPreviewFrame(byte[] data, Camera camera)
    {
      Camera.Parameters parameters = camera.getParameters();
      Camera.Size size = parameters.getPreviewSize();

      Image barcode = new Image(size.width, size.height, "Y800");
      barcode.setData(data);

      if(mScanner == null)
      {
        return;
      }

      int result = mScanner.scanImage(barcode);

      if(result != 0)
      {
        onPause();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(200);

        String barcodeText = "";

        SymbolSet symbols = mScanner.getResults();
        for(Symbol sym : symbols)
        {
          barcodeText = sym.getData().trim();
        }

        Intent mainIntent = null;

        if(intActiveMenu == 1)
        {
          Fungsi.storeToSharedPref(getApplicationContext(), barcodeText, Preference.PrefScanQRConfirm);
          mainIntent = new Intent(ScanQR.this, CekPointActivity.class);
        }
        else
        if(intActiveMenu == 2)
        {
          Fungsi.storeToSharedPref(getApplicationContext(), barcodeText, Preference.PrefGetPlusIDEarn);
          mainIntent = new Intent(ScanQR.this, EarnPointActivity.class);
        }
        else
        if(intActiveMenu == 3)
        {
          Fungsi.storeToSharedPref(getApplicationContext(), barcodeText, Preference.PrefGetPlusID);
          mainIntent = new Intent(ScanQR.this, TukarPoint.class);
        }
        else
        if(intActiveMenu == 41)
        {
          Fungsi.storeToSharedPref(getApplicationContext(), barcodeText, Preference.PrefGetPlusID);
          mainIntent = new Intent(ScanQR.this, EVoucher.class);
        }
        else
        if(intActiveMenu == 42)
        {
          Fungsi.storeToSharedPref(getApplicationContext(), barcodeText, Preference.PrefEVoucher);
          mainIntent = new Intent(ScanQR.this, EVoucher.class);
        }
        else
        if(intActiveMenu == 5)
        {
          Fungsi.storeToSharedPref(getApplicationContext(), barcodeText, Preference.PrefGetPlusID);
          mainIntent = new Intent(ScanQR.this, InformasiPayment.class);
        }

        startActivity(mainIntent);
        finish();
      }
    }
  };

  @Override
  protected void onResume()
  {
    super.onResume();
    isInitCameraProcess = true;
    Timer timer = new Timer();

    timer.schedule(new TimerTask()
    {
      @Override
      public void run()
      {
        runOnUiThread(() -> {
          SurfaceHolder surfaceHolder = cameraPreview.getHolder();
          if(hasSurface)
          {
            // The activity was paused but not stopped, so the surface still exists.
            // Therefore surfaceCreated() won't be called, so init the camera here.
            initCamera(cameraPreview, true);
          }
          else
          {
            // Install the callback and wait for surfaceCreated() to init the camera.
            surfaceHolder.addCallback(ScanQR.this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            initCamera(cameraPreview, true);
          }
        });
      }
    }, 500);
  }

  @Override
  protected void onPause()
  {
    super.onPause();
    try
    {
      cameraManager.stopPreview();
      cameraManager.closeDriver();

      if(!hasSurface)
      {
        SurfaceHolder surfaceHolder = cameraPreview.getHolder();
        surfaceHolder.removeCallback(this);
      }
    }
    catch(RuntimeException e)
    {
      // Can be already released
    }
  }

  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    cleanCameraInstance();
  }

  private void cleanCameraInstance()
  {
    if(cameraManager.isOpen())
    {
      cameraManager.stopPreview();
      cameraManager.closeDriver();
      if(!hasSurface)
      {
        SurfaceHolder surfaceHolder = cameraPreview.getHolder();
        surfaceHolder.removeCallback(this);
      }
    }
  }

  private void initCamera(SurfaceView surfaceView, boolean oneTime)
  {
    if(surfaceView == null || surfaceView.getHolder() == null)
      throw new IllegalStateException("No SurfaceHolder provided");

    if(cameraManager.isOpen()) return;

    try
    {
      surfaceView.setBackgroundColor(getResources().getColor(android.R.color.transparent));

      cameraManager.openDriver(surfaceView);
      // Creating the handler starts the preview, which can also throw a
      // RuntimeException.
      cameraManager.startPreview();
    }
    catch(IOException ioe)
    {
      ioe.printStackTrace();
    }
    catch(RuntimeException e)
    {
      if(null != cameraManager) cameraManager.closeDriver();

      if(oneTime) initCamera(surfaceView, false);
      else return;
    }

    mScanner = new ImageScanner();
    mScanner.setConfig(Symbol.NONE, Config.ENABLE, 0);
    mScanner.setConfig(Symbol.QRCODE, Config.ENABLE, 1);
    mScanner.setConfig(Symbol.NONE, Config.X_DENSITY, 3);
    mScanner.setConfig(Symbol.NONE, Config.Y_DENSITY, 3);

    surfaceView.setBackgroundColor(Color.TRANSPARENT);
    isInitCameraProcess = false;
  }

  private void BackActivity()
  {
    Intent BackIntent = null;

    if(intActiveMenu == 1)
      BackIntent = new Intent(ScanQR.this, HomeActivity.class);
    else
    if(intActiveMenu == 2)
      BackIntent = new Intent(ScanQR.this, EarnPointActivity.class);
    else
    if(intActiveMenu == 3)
      BackIntent = new Intent(ScanQR.this, TukarPoint.class);
    else
    if((intActiveMenu == 41) || (intActiveMenu == 42))
      BackIntent = new Intent(ScanQR.this, EVoucher.class);
    else
    if(intActiveMenu == 5)
      BackIntent = new Intent(ScanQR.this, PaymentActivity.class);

    startActivity(BackIntent);
    finish();
  }

  @Override
  public void onBackPressed()
  {
    BackActivity();
  }
}
