package id.mygetplus.getpluspos.mvp.tukarpoin.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.ScanQR;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointContract;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointPresenter;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class TukarPoint extends AppCompatActivity implements CekPointContract.View
{
  CekPointPresenter cekPointPresenter;
  @BindView(R.id.ivThumbTukar1)
  ImageView ivThumbTukar1;
  @BindView(R.id.ivThumbTukar2)
  ImageView ivThumbTukar2;
  private Uri UrlGambar;
  private static final int TAKE_PICTURE = 1;
  private Uri imageUri;
  private Bitmap mImageBitmap;
  private String mCurrentPhotoPath;
  private ImageView mImageView;
  int Thumb=0;

  @BindView(R.id.etGetPlusId)
  TextInputEditText etGetPlusID;
  @BindView(R.id.etNoReff)
  TextInputEditText etNoReff;
  @BindView(R.id.etAmount)
  TextInputEditText etAmount;
  @BindView(R.id.tv_toolbar)
  TextView tvToolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tukar_point);
    ButterKnife.bind(this);

    etGetPlusID.setText(Fungsi.getStringFromSharedPref(getApplicationContext(),
      Preference.PrefGetPlusID));

    cekPointPresenter = new CekPointPresenter(this, this);

    tvToolbar.setText("TUKAR POINT");

    StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
    StrictMode.setVmPolicy(builder.build());
  }


  @OnClick({R.id.iv_camera, R.id.btnLanjutTukar, R.id.btn_back, R.id.llStrukTukar1,
    R.id.tvStrukTukar1, R.id.ivStrukTukar1, R.id.llStrukTukar2, R.id.tvStrukTukar2,
    R.id.ivStrukTukar2})
  public void onViewClicked(View view)
  {
    switch (view.getId())
    {
      case R.id.iv_camera:
        Fungsi.storeToSharedPref(this, 3, Preference.PrefActiveMenu);
        Intent GetPlusID = new Intent(this, ScanQR.class);
        startActivity(GetPlusID);
        break;
      case R.id.btnLanjutTukar:
        cekPointPresenter.loadCekPointData(PosLinkGenerator.createService(this),
          GetPlusSession.getInstance(this).getTokenSession(), etGetPlusID.getText().toString());
        break;
      case R.id.btn_back:
        BackHomeProcess();
        break;
      case R.id.llStrukTukar1:
      case R.id.tvStrukTukar1:
      case R.id.ivStrukTukar1:
        Intent cameraIntent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent1.resolveActivity(getPackageManager()) != null)
        {
          File photoFile = null;

          try
          {
            photoFile = createImageFile();
          } catch (IOException ex)
          {
            Log.i("Grab", "IOException");
          }

          if (photoFile != null)
          {
            Thumb = 1;
            cameraIntent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            startActivityForResult(cameraIntent1, TAKE_PICTURE);
          }
        }
        break;
      case R.id.llStrukTukar2:
      case R.id.tvStrukTukar2:
      case R.id.ivStrukTukar2:
        Intent cameraIntent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent2.resolveActivity(getPackageManager()) != null)
        {
          File photoFile = null;

          try
          {
            photoFile = createImageFile();
          } catch (IOException ex)
          {
            Log.i("Grab", "IOException");
          }

          if (photoFile != null)
          {
            Thumb = 2;
            cameraIntent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            startActivityForResult(cameraIntent2, TAKE_PICTURE);
          }
        }
        break;
    }
  }

  private void BackHomeProcess()
  {
    Intent BackHome = new Intent(this, HomeActivity.class);
    startActivity(BackHome);
  }

  @Override
  public void onBackPressed()
  {
    BackHomeProcess();
  }

  @Override
  public void setCekPoint(ResponsePojo responsePojo)
  {
    if (responsePojo.getAFaultCode().matches("0"))
    {
      Intent KonfirmasiTukar = new Intent(this, KonfirmasiTukar.class);
      KonfirmasiTukar.putExtra("GetPlusID", etGetPlusID.getText().toString());
      KonfirmasiTukar.putExtra("ReffID", etNoReff.getText().toString());
      KonfirmasiTukar.putExtra("Amount", etAmount.getText().toString());
      KonfirmasiTukar.putExtra("Nama", responsePojo.getAValue().getBDisplayValue());
      startActivity(KonfirmasiTukar);
    } else
      Toast.makeText(getApplicationContext(), responsePojo.getAFaultDescription(), Toast.LENGTH_SHORT).show();
  }

  private File createImageFile() throws IOException
  {
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "JPEG_Tukar_" + String.valueOf(Thumb) + "_" + timeStamp + "_";
    File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    File image = File.createTempFile(imageFileName, ".jpg", storageDir);
    mCurrentPhotoPath = "file:" + image.getAbsolutePath();
    return image;
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK)
    {
      try
      {
        mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
        Bitmap resized = Bitmap.createScaledBitmap(mImageBitmap, 480, 640, true);

        if(Thumb == 1)
        {
          ivThumbTukar1.setVisibility(View.VISIBLE);
          ivThumbTukar1.setImageBitmap(resized);
        }
        else
        if(Thumb == 2)
        {
          ivThumbTukar2.setVisibility(View.VISIBLE);
          ivThumbTukar2.setImageBitmap(resized);
        }
      } catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
