package id.mygetplus.getpluspos.mvp.earnpoint.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.common.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.mygetplus.getpluspos.AValue;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.PopupMessege;
import id.mygetplus.getpluspos.Preference;
import id.mygetplus.getpluspos.R;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.ScanQR;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointContract;
import id.mygetplus.getpluspos.mvp.cekpoint.presenter.CekPointPresenter;
import id.mygetplus.getpluspos.mvp.earnpoint.adapter.EarnTransactionAdapter;
import id.mygetplus.getpluspos.mvp.earnpoint.model.EarnListModel;
import id.mygetplus.getpluspos.mvp.main.HomeActivity;
import id.mygetplus.getpluspos.mvp.tukarpoin.view.InformasiTukar;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import id.mygetplus.getpluspos.service.PosLinkGenerator;

public class EarnPointActivity extends AppCompatActivity implements CekPointContract.View,
		EarnTransactionAdapter.EventClickListenr
{
	public static final String EARN_ACTIVTY = "EARNACTIVITY";
	private final int REQUEST_CODE_CONFIRMATION = 110;

	CekPointPresenter cekPointPresenter;
	private PopupMessege popupMessege = new PopupMessege();
	private Context context = this;

	@BindView(R.id.etGetPlusId)
	TextInputEditText etGetPlusID;
	@BindView(R.id.tv_toolbar)
	TextView tvToolbar;
	@BindView(R.id.ivBeriThumb1)
	ImageView ivBeriThumb1;
	@BindView(R.id.ivBeriStruk1)
	ImageView ivBeriStruk1;
	@BindView(R.id.tvBeriStruk1)
	TextView tvBeriStruk1;
	@BindView(R.id.ivBeriThumb2)
	ImageView ivBeriThumb2;
	@BindView(R.id.ivBeriStruk2)
	ImageView ivBeriStruk2;
	@BindView(R.id.tvBeriStruk2)
	TextView tvBeriStruk2;
	@BindView(R.id.lin_main_earn_activity)
	LinearLayout linMainEarnActivty;
	@BindView(R.id.constraintList)
	ConstraintLayout constrainList;

	@BindView(R.id.etNoReff)
	TextInputEditText etNoReff;
	@BindView(R.id.etMemberId)
	TextInputEditText etMemberId;
	@BindView(R.id.rec_earn_transaction)
	RecyclerView recEarnTransaction;

	@BindView(R.id.etAmount)
	TextInputEditText etAmount;

	@BindView(R.id.etMechantName)
	TextInputEditText etMerchantName;

	private EarnTransactionAdapter earnTransactionAdapter;

	private static final int TAKE_PICTURE = 1;
	private static final String PICTURE_EXT = ".jpg";

	private Bitmap mImageBitmap;
	private String mCurrentPhotoPath;
	private String mCurrentPhotoPath1;
	private String mCurrentPhotoPath2;
	int Thumb=0;

	String imgStruk1 = "";
	String imgStruk2 = "";

	String merchant;
	String amount;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_earn_point);
		ButterKnife.bind(this);

		tvToolbar.setText("BERI POINT");
		etGetPlusID.setText(Fungsi.getStringFromSharedPref(getApplicationContext(),
			Preference.PrefGetPlusIDEarn));

		cekPointPresenter = new CekPointPresenter(this, this);

		initAdapter();
	}

	void initAdapter() {
		earnTransactionAdapter = new EarnTransactionAdapter(this);

		recEarnTransaction.setHasFixedSize(true);
		recEarnTransaction.setLayoutManager(new LinearLayoutManager(this));
		recEarnTransaction.setAdapter(earnTransactionAdapter);
	}

	@Override
	public void onBackPressed()
	{
		goHome();
	}

	@Override
	public void setCekPoint(ResponsePojo cekPoint)
	{
		if (cekPoint.getAFaultCode().matches("0"))
		{
			 merchant = etMerchantName.getText().toString();
			 amount = etAmount.getText().toString();
			Intent cekPoints = new Intent(this, EarnConfirmPopUp.class);
			cekPoints.putExtra("GetPlusID", etGetPlusID.getText().toString());
			cekPoints.putExtra("ReffID", etNoReff.getText().toString());
			cekPoints.putExtra("Amount", etAmount.getText().toString());
			cekPoints.putExtra("Nama Merchant", etMerchantName.getText().toString());
			cekPoints.putExtra("Nama", cekPoint.getAValue().getBDisplayValue());
			startActivityForResult(cekPoints, REQUEST_CODE_CONFIRMATION);
		}
		else
			Toast.makeText(getApplicationContext(), cekPoint.getAFaultDescription(),
				Toast.LENGTH_SHORT).show();
	}

	@OnClick({R.id.btn_back, R.id.btnLanjutEarn, R.id.iv_camera, R.id.llBeriStruk1, R.id.llBeriStruk2,
		R.id.ivBeriStruk1, R.id.tvBeriStruk1, R.id.ivBeriThumb1, R.id.ivBeriStruk2,
		R.id.tvBeriStruk2, R.id.ivBeriThumb2})
	public void onViewClicked(View view)
	{
		switch (view.getId())
		{
			case R.id.iv_camera:
				Fungsi.storeToSharedPref(this, 2, Preference.PrefActiveMenu);
				Intent GetPlusID = new Intent(this, ScanQR.class);
				startActivity(GetPlusID);
				break;
			case R.id.btn_back:
				goHome();
				break;
			case R.id.btnLanjutEarn:
				if (TextUtils.isEmpty(etGetPlusID.getText()))
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgGetPlusIDEmpty));
				else if (TextUtils.isEmpty(etNoReff.getText()))
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgNoReffEmpty));
				else if (TextUtils.isEmpty(etMemberId.getText()))
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgMerchantIDEmpty));
				else
					cekPointPresenter.loadCekPointData(PosLinkGenerator.createService(this),
						GetPlusSession.getInstance(this).getTokenSession(), etGetPlusID.getText().toString());
				break;
			case R.id.llBeriStruk1:
			case R.id.ivBeriStruk1:
			case R.id.tvBeriStruk1:
				if (TextUtils.isEmpty(etGetPlusID.getText()))
				{
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgGetPlusIDEmpty));
					return;
				}
				else
				if (TextUtils.isEmpty(etNoReff.getText()))
				{
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgNoReffEmpty));
					return;
				}
				else if (TextUtils.isEmpty(etMemberId.getText()))
				{
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgMerchantIDEmpty));
					return;
				}

				Thumb = 1;
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
						cameraIntent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
						startActivityForResult(cameraIntent1, TAKE_PICTURE);
					}
				}
				break;
			case R.id.llBeriStruk2:
			case R.id.ivBeriStruk2:
			case R.id.tvBeriStruk2:
				if (TextUtils.isEmpty(etGetPlusID.getText()))
				{
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgGetPlusIDEmpty));
					return;
				}
				else
				if (TextUtils.isEmpty(etNoReff.getText()))
				{
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgNoReffEmpty));
					return;
				}
				else if (TextUtils.isEmpty(etMemberId.getText()))
				{
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgMerchantIDEmpty));
					return;
				}

				Thumb = 2;
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
						cameraIntent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
						startActivityForResult(cameraIntent2, TAKE_PICTURE);
					}
				}
				break;
		}
	}

	private void goHome()
	{
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

	private File createImageFile() throws IOException
	{
		String imageFileName = "BERI_" + String.valueOf(Thumb) + "_" + etGetPlusID.getText().toString().trim() +
			"_" + etMemberId.getText().toString().trim() + "_" + etNoReff.getText().toString().trim() + PICTURE_EXT;
		File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File image = new File(storageDir, imageFileName);
		mCurrentPhotoPath = "file:" + image.getAbsolutePath();

		if(image.exists())
			image.delete();

		if(Thumb == 1)
		{
			imgStruk1 = imageFileName;
			mCurrentPhotoPath1 = "file:" + image.getAbsolutePath();
		}
		else
		if(Thumb == 2)
		{
			imgStruk2 = imageFileName;
			mCurrentPhotoPath2 = "file:" + image.getAbsolutePath();
		}

		return image;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == REQUEST_CODE_CONFIRMATION && resultCode == RESULT_OK) {
			// condition resul ok from confirmation

			earnTransactionAdapter.addAdapter(new EarnListModel(merchant, amount));
			etNoReff.setText("");
			etMemberId.setText("");
			etGetPlusID.setText("");
			etMerchantName.setText("");
			etAmount.setText("");

			constrainList.setVisibility(View.VISIBLE);
			linMainEarnActivty.setVisibility(View.GONE);
		} else if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {
			try
			{
				mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
				Bitmap resized = Bitmap.createScaledBitmap(mImageBitmap, 480, 640, true);

				if(Thumb == 1)
				{
					ivBeriThumb1.setVisibility(View.VISIBLE);
					ivBeriThumb1.setImageBitmap(resized);
				}
				else
				if(Thumb == 2)
				{
					ivBeriThumb2.setVisibility(View.VISIBLE);
					ivBeriThumb2.setImageBitmap(resized);
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	@OnClick(R.id.btn_tambah)
	void onClickTambah() {
		linMainEarnActivty.setVisibility(View.VISIBLE);
		constrainList.setVisibility(View.GONE);
	}

	@Override
	public void setRemove(EarnListModel earnListModel, int position) {
		earnTransactionAdapter.removeItemAdapter(earnListModel);
	}

	@OnClick(R.id.btn_next)
	void onClickNext() {
			// To Do
	}
}
