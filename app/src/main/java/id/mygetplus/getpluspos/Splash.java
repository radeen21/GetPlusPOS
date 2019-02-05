package id.mygetplus.getpluspos;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Splash extends AppCompatActivity
{
  private TextView tvVersion;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.splash_lay);

    tvVersion = findViewById(R.id.tvVersion);
    String myVersionName = "Unknown";
    Context context = getApplicationContext(); // or activity.getApplicationContext()
    PackageManager packageManager = context.getPackageManager();
    String packageName = context.getPackageName();

    try
    {
      myVersionName = packageManager.getPackageInfo(packageName, 0).versionName;
    }
    catch (PackageManager.NameNotFoundException e)
    {
      e.printStackTrace();
    }

    tvVersion.setText("Versi " + myVersionName);

    new Handler().postDelayed(new Runnable()
    {
      @Override
      public void run()
      {
        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(FixValue.strNamePref, null);
        Fungsi.storeToSharedPref(getApplicationContext(),"", Preference.PrefScanQR);
        Fungsi.storeToSharedPref(getApplicationContext(),"", Preference.PrefScanQRConfirm);
        Fungsi.storeToSharedPref(getApplicationContext(),0, Preference.PrefMerchantOwner);
        Intent mainIntent = new Intent(Splash.this, CashierHome.class);
        startActivity(mainIntent);
        finish();
      }
    }, FixValue.SPLASH_DISPLAY_LENGHT);
  }
}