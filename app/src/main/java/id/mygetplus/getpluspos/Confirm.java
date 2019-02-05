package id.mygetplus.getpluspos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;

public class Confirm extends AppCompatActivity
{
  @BindView(R.id.confirmtoolbar)
  Toolbar confirmtoolbar;
  @BindView(R.id.tvConfirmDate)
  TextView tvConfirmDate;
  @BindView(R.id.tvConfirmTime)
  TextView tvConfirmTime;
  @BindView(R.id.tvConfirmRef)
  TextView tvConfirmRef;
  @BindView(R.id.tvIsiTotal)
  TextView tvIsiTotal;
  @BindView(R.id.tvIsiBayar)
  TextView tvIsiBayar;
  @BindView(R.id.tvIsiTipe)
  TextView tvIsiTipe;
  @BindView(R.id.tvDetailBrand)
  TextView tvDetailBrand;
  @BindView(R.id.tvConfirmInvoice)
  TextView tvConfirmInvoice;
  @BindView(R.id.tvMerchantName)
  TextView tvMerchantName;
	@BindView(R.id.tvPoin)
	TextView tvPoin;
	@BindView(R.id.tvIsiPoin)
	TextView tvIsiPoin;

  @BindView(R.id.etCardNumber)
  EditText etCardNumber;
	@BindView(R.id.etNoVoucher)
	EditText etNoVoucher;
	@BindView(R.id.etConfirmAdjust)
	EditText etConfirmAdjust;

	@BindView(R.id.llIsiVoucher)
	LinearLayout llIsiVoucher;
	@BindView(R.id.llConfirmAdjust)
	LinearLayout llConfirmAdjust;

  @BindView(R.id.rbConfirmCash)
  RadioButton rbConfirmCash;  // 0
  @BindView(R.id.rbConfirmElectronic)
  RadioButton rbConfirmElectronic;    // 1
  @BindView(R.id.rbConfirmPoin)
  RadioButton rbConfirmPoin;  // 2
  @BindView(R.id.rbConfirmVoucher)
  RadioButton rbConfirmVoucher;   // 3
	@BindView(R.id.rbConfirmAdjust)
	RadioButton rbConfirmAdjust;   // 3

  private PopupMessege popupMessege = new PopupMessege();
  private Context context = this;
  private Activity activity = this;

  private String TAG = "[Confirmation]";
  String strNumber="";
  boolean boolPilih=false;
	String strVoucher="";

  static ProgressDialog progressDialog;
  AValue aValue;
  Calendar c = Calendar.getInstance();
  SimpleDateFormat dfTrans;
  String dateTrans = "";
  BrandsRsp brandsRsp;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.confirm_lay);
    ButterKnife.bind(this);

    dfTrans = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    setSupportActionBar(confirmtoolbar);

    aValue = Fungsi.getObjectFromSharedPref(context, AValue.class, Preference.PrefMerchantInfo);
    tvMerchantName.setText(aValue.getBAccountOwnerDisplayValue());

    brandsRsp = Fungsi.getObjectFromSharedPref(context, BrandsRsp.class, Preference.PrefBrandItem);
    tvIsiTotal.setText(getString(R.string.strConfirmNilai, " " + Fungsi.FormatDesimal(brandsRsp.getPrice())));
    tvDetailBrand.setText(brandsRsp.getDetail());
    tvConfirmDate.setText(getString(R.string.strConfirmDate, " " + brandsRsp.getStrDate()));
    tvConfirmTime.setText(getString(R.string.strConfirmTime, " " + brandsRsp.getStrTime()));
    tvConfirmInvoice.setText(getString(R.string.strConfirmInvoice, String.valueOf(brandsRsp.getIntInvoice())));

    tvIsiBayar.setText(getString(R.string.strConfirmNilai, "0"));

    strNumber = Fungsi.getStringFromSharedPref(context, Preference.PrefScanQR);
	  etCardNumber.setText(strNumber);
	  tvConfirmRef.setText(getString(R.string.strConfirmRef, strNumber));

    if((!TextUtils.isEmpty(strNumber)) && (Fungsi.getIntFromSharedPref(context, Preference.PrefActiveMenu) == 1))
      AmbilDataMember();

	  strVoucher = Fungsi.getStringFromSharedPref(context, Preference.PrefScanQRConfirm);
    etNoVoucher.setText(strVoucher);

	  if((!TextUtils.isEmpty(strVoucher)) && (Fungsi.getIntFromSharedPref(context, Preference.PrefActiveMenu) == 2))
		  CheckVoucher(0);
  }

  @OnClick({R.id.rbConfirmCash, R.id.rbConfirmElectronic, R.id.rbConfirmPoin, R.id.rbConfirmVoucher, R.id.tvConfirmInvoice,
    R.id.ivSearchCardNumber, R.id.ivScanCardNumber, R.id.btnConfirm, R.id.ivNoVoucher, R.id.ivScanNoVoucher, R.id.rbConfirmAdjust})
  public void onViewClicked(View view)
  {
    switch(view.getId())
    {
	    case R.id.tvConfirmInvoice:
		    Random random = new Random();
		    Integer intRandom = random.nextInt(10000000) + 1;
		    tvConfirmInvoice.setText(getString(R.string.strConfirmInvoice, String.valueOf(intRandom)));
		    brandsRsp.setIntInvoice(intRandom);
		    break;
      case R.id.rbConfirmCash:
        initPilihPayment(0);
        boolPilih = false;
        tvIsiBayar.setText(getString(R.string.strConfirmNilai, " " + Fungsi.FormatDesimal(brandsRsp.getPrice())));
        break;
      case R.id.rbConfirmElectronic:
        initPilihPayment(1);
        boolPilih = true;
        break;
      case R.id.rbConfirmPoin:
        initPilihPayment(2);
        boolPilih = true;
        break;
      case R.id.rbConfirmVoucher:
        initPilihPayment(3);
        boolPilih = true;
        break;
	    case R.id.rbConfirmAdjust:
		    initPilihPayment(4);
		    boolPilih = true;
		    break;
      case R.id.ivScanCardNumber:
	      Fungsi.storeToSharedPref(context, etCardNumber.getText().toString(), Preference.PrefScanQR);
	      Fungsi.storeToSharedPref(context, etNoVoucher.getText().toString(), Preference.PrefScanQRConfirm);
        Fungsi.storeToSharedPref(context, 1, Preference.PrefActiveMenu);
        Intent QRCodeIntent = new Intent(Confirm.this, ScanQR.class);
        startActivity(QRCodeIntent);
        finish();
        break;
	    case R.id.ivScanNoVoucher:
		    Fungsi.storeToSharedPref(context, etCardNumber.getText().toString(), Preference.PrefScanQR);
		    Fungsi.storeToSharedPref(context, etNoVoucher.getText().toString(), Preference.PrefScanQRConfirm);
		    Fungsi.storeToSharedPref(context, 2, Preference.PrefActiveMenu);
		    Intent QRVoucherIntent = new Intent(Confirm.this, ScanQR.class);
		    startActivity(QRVoucherIntent);
		    finish();
		    break;
      case R.id.btnConfirm:
        if(TextUtils.isEmpty(etCardNumber.getText().toString()))
        {
          if(rbConfirmVoucher.isChecked())
            popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgDataRefVoucher));
          else
            popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgDataRefKartu));
        }
        else
        {
	        if((rbConfirmAdjust.isChecked()) && (!TextUtils.isEmpty(etConfirmAdjust.getText().toString())))
		        TransAdjustPoin();
	        else
          if((rbConfirmCash.isChecked()) && (!TextUtils.isEmpty(etCardNumber.getText().toString())))
	          TransCashGetPoin();
          else
          if((rbConfirmPoin.isChecked()) && (!TextUtils.isEmpty(etCardNumber.getText().toString())))
	          TransPoinGetPoin();
          else
	        if((rbConfirmVoucher.isChecked()) && (!TextUtils.isEmpty(etCardNumber.getText().toString())) &&
		         (!TextUtils.isEmpty(etNoVoucher.getText().toString())))
		        CheckVoucher(1);
        }
        break;
      case R.id.ivSearchCardNumber:
        if(!TextUtils.isEmpty(etCardNumber.getText().toString()))
        {
          tvConfirmRef.setText(getString(R.string.strConfirmRef, " " + etCardNumber.getText().toString()));
          AmbilDataMember();
        }
        break;
	    case R.id.ivNoVoucher:
		    if(!TextUtils.isEmpty(etNoVoucher.getText().toString()))
			    CheckVoucher(0);
		    break;
    }
  }

  @Override
  public void onBackPressed()
  {
    Intent mainIntent = new Intent(Confirm.this, CashierHome.class);
    startActivity(mainIntent);
    finish();
  }

  private void initPilihPayment(int index)
  {
    rbConfirmCash.setChecked(false);
    rbConfirmElectronic.setChecked(false);
    rbConfirmPoin.setChecked(false);
    rbConfirmVoucher.setChecked(false);
	  rbConfirmAdjust.setChecked(false);
	  llIsiVoucher.setVisibility(View.GONE);
	  etConfirmAdjust.setVisibility(View.GONE);

    switch(index)
    {
      case 0:
        rbConfirmCash.setChecked(true);
        tvIsiTipe.setText(getString(R.string.strConfirmCash));
        break;
      case 1:
        rbConfirmElectronic.setChecked(true);
        tvIsiTipe.setText(getString(R.string.strConfirmElectronic));
        break;
      case 2:
        rbConfirmPoin.setChecked(true);
        tvIsiTipe.setText(getString(R.string.strConfirmPoin));
        break;
      case 3:
        rbConfirmVoucher.setChecked(true);
        tvIsiTipe.setText(getString(R.string.strConfirmVoucher));
	      llIsiVoucher.setVisibility(View.VISIBLE);
        break;
	    case 4:
		    rbConfirmAdjust.setChecked(true);
		    tvIsiTipe.setText(getString(R.string.strConfirmAdjust));
		    etConfirmAdjust.setVisibility(View.VISIBLE);
		    break;
    }
  }

  private void AmbilDataMember()
  {
    if(Fungsi.isNetworkAvailable(context) == FixValue.TYPE_NONE)
    {
      popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgConnectionError));
      return;
    }

    POSLink posLink = Fungsi.BindingPOS();

    SimValue simValue = new SimValue();
    simValue.setSim1CardNumber(etCardNumber.getText().toString());
    simValue.setSim1ReturnAllMemberData("true");

    PoinRequest poinRequest = new PoinRequest();
    poinRequest.setSimToken(aValue.getBToken());
    poinRequest.setSimValue(simValue);

    RequestHolder requestHolder = new RequestHolder();
    requestHolder.setPoinRequest(poinRequest);

    final Call<ResponsePojo> ReceivePojo = posLink.SelectMemberService(requestHolder);

	  Log.d(TAG, "AmbilDataMember: Data Member");

    ReceivePojo.enqueue(new Callback<ResponsePojo>()
    {
      @Override
      public void onResponse(Call<ResponsePojo> call, retrofit2.Response<ResponsePojo> response)
      {
        if(response.isSuccessful())
        {
          if (response.body().getAFaultCode().matches("0"))
          {
            double d = Double.parseDouble(response.body().getAValue().getBProgramMemberships().getBProgramMembership().getBPointsBalance());
            tvIsiPoin.setText(Fungsi.FormatDesimal((int) d));
          }
          else
            popupMessege.ShowMessege1(context, response.body().getAFaultDescription());
        }
        else
          popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgUnknownError));
      }

      @Override
      public void onFailure(Call<ResponsePojo> call, Throwable t)
      {
	      Log.d(TAG, "AmbilDataMember: " + t.getMessage());
        popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgServerFailure));
      }
    });
  }

  private void TransCashGetPoin()
  {
    progressDialog = ProgressDialog.show(context, getResources().getString(R.string.hintHarapTunggu),
      context.getResources().getString(R.string.msgTransaksiCash));
    progressDialog.setCancelable(false);

    if(Fungsi.isNetworkAvailable(context) == FixValue.TYPE_NONE)
    {
      progressDialog.dismiss();
      popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgConnectionError));
      return;
    }

    POSLink posLink = Fungsi.BindingPOS();

    dateTrans = dfTrans.format(c.getTime());
    dateTrans = Fungsi.getDate(dateTrans, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd") + "T" +
                Fungsi.getTime(dateTrans, "yyyy-MM-dd HH:mm:ss", "HH:mm:ss");

    AValue aValue = Fungsi.getObjectFromSharedPref(context, AValue.class, Preference.PrefMerchantInfo);
    tvMerchantName.setText(aValue.getBAccountOwnerDisplayValue());

    Sim1SaleTransactionLine sim1SaleTransactionLine = new Sim1SaleTransactionLine();
    sim1SaleTransactionLine.setSim1LineValue(brandsRsp.getPrice());
    sim1SaleTransactionLine.setSim1ProductBrand(aValue.getbProductBrand());
    sim1SaleTransactionLine.setSim1Quantity(1);

    Sim1TransactionLines sim1TransactionLines = new Sim1TransactionLines();
    sim1TransactionLines.setSim1SaleTransactionLine(sim1SaleTransactionLine);

    SimValue simValue = new SimValue();
    simValue.setSim1CardNumber(etCardNumber.getText().toString());
    simValue.setSim1CashierCode(aValue.getBAccountRSN());
    simValue.setSim1ClientVersion("1");
    simValue.setSim1CustomerAccountID(aValue.getBAccountID());
    simValue.setSim1PublishedByDisplayValue(aValue.getBDisplayValue());
    simValue.setSim1PublishedByRSN(aValue.getBAccountOwnerRSN());
    simValue.setSim1TerminalID("123456");
    simValue.setSim1TransactionDate(dateTrans);
    simValue.setSim1TransactionID(String.valueOf(brandsRsp.getIntInvoice()));
    simValue.setSim1TransactionNotes("Transaksi ini hanya untuk demo");
    simValue.setSim1TransactionSaleValue(brandsRsp.getPrice());
    simValue.setSim1TransactionLines(sim1TransactionLines);

    PoinRequest poinRequest = new PoinRequest();
    poinRequest.setSimToken(aValue.getBToken());
    poinRequest.setSimValue(simValue);

    RequestHolder requestHolder = new RequestHolder();
    requestHolder.setPoinRequest(poinRequest);

    final Call<ResponsePojo> ReceivePojo = posLink.TransaksiCashService(requestHolder);

    ReceivePojo.enqueue(new Callback<ResponsePojo>()
    {
      @Override
      public void onResponse(Call<ResponsePojo> call, retrofit2.Response<ResponsePojo> response)
      {
        if(response.isSuccessful())
        {
	        progressDialog.dismiss();

          if (response.body().getAFaultCode().matches("0"))
          {
            ppInformasi ppInformasi = new ppInformasi(Confirm.this,
              response.body().getAValue().getBLoyaltyPointsBalance(),
              response.body().getAValue().getBTransactionID());

            ppInformasi.show();
          }
          else
          {
	          progressDialog.dismiss();
	          popupMessege.ShowMessege1(context, response.body().getAFaultDescription());
          }
        }
        else
        {
	        progressDialog.dismiss();
	        popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgUnknownError));
        }
      }

      @Override
      public void onFailure(Call<ResponsePojo> call, Throwable t)
      {
	      progressDialog.dismiss();
        popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgServerFailure));
      }
    });
  }

	private void TransVoucherGetPoin()
	{
		progressDialog = ProgressDialog.show(context, getResources().getString(R.string.hintHarapTunggu),
			context.getResources().getString(R.string.msgTransaksiVoucher));
		progressDialog.setCancelable(false);

		if(Fungsi.isNetworkAvailable(context) == FixValue.TYPE_NONE)
		{
			progressDialog.dismiss();
			popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgConnectionError));
			return;
		}

		POSLink posLink = Fungsi.BindingPOS();

		dateTrans = dfTrans.format(c.getTime());
		dateTrans = Fungsi.getDate(dateTrans, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd") + "T" +
			Fungsi.getTime(dateTrans, "yyyy-MM-dd HH:mm:ss", "HH:mm:ss");

		AValue aValue = Fungsi.getObjectFromSharedPref(context, AValue.class, Preference.PrefMerchantInfo);
		tvMerchantName.setText(aValue.getBAccountOwnerDisplayValue());

		Sim1SaleTransactionLine sim1SaleTransactionLine = new Sim1SaleTransactionLine();
		sim1SaleTransactionLine.setSim1LineValue(brandsRsp.getPrice());
		sim1SaleTransactionLine.setSim1ProductBrand(aValue.getbProductBrand());
		sim1SaleTransactionLine.setSim1Quantity(1);

		Sim1TransactionLines sim1TransactionLines = new Sim1TransactionLines();
		sim1TransactionLines.setSim1SaleTransactionLine(sim1SaleTransactionLine);

		SimValue simValue = new SimValue();
		simValue.setSim1CardNumber(etCardNumber.getText().toString());
		simValue.setSim1CashierCode(aValue.getBAccountRSN());
		simValue.setSim1ClientVersion("1");
		simValue.setSim1CustomerAccountID(aValue.getBAccountID());
		simValue.setSim1ItemDescription("Isi dari item description");
		simValue.setSim1PublishedByDisplayValue(aValue.getBDisplayValue());
		simValue.setSim1PublishedByRSN(aValue.getBAccountOwnerRSN());
		simValue.setSim1Quantity("1");
		simValue.setSim1TerminalID("123456");
		simValue.setSim1TransactionDate(dateTrans);
		simValue.setSim1TransactionID(String.valueOf(brandsRsp.getIntInvoice()));
		simValue.setSim1TransactionNotes("Transaksi ini hanya untuk demo");
		simValue.setSim1TransactionVoucherCode(etNoVoucher.getText().toString());
		simValue.setSim1TransactionLines(sim1TransactionLines);

		PoinRequest poinRequest = new PoinRequest();
		poinRequest.setSimToken(aValue.getBToken());
		poinRequest.setSimValue(simValue);

		RequestHolder requestHolder = new RequestHolder();
		requestHolder.setPoinRequest(poinRequest);

		final Call<ResponsePojo> ReceivePojo = posLink.VoucherRedeemService(requestHolder);

		ReceivePojo.enqueue(new Callback<ResponsePojo>()
		{
			@Override
			public void onResponse(Call<ResponsePojo> call, retrofit2.Response<ResponsePojo> response)
			{
				if(response.isSuccessful())
				{
					progressDialog.dismiss();

					if (response.body().getAFaultCode().matches("0"))
					{
						ppInformasi ppInformasi = new ppInformasi(Confirm.this,
							response.body().getAValue().getBLoyaltyPointsBalance(),
							response.body().getAValue().getBTransactionID());

						ppInformasi.show();
					}
					else
					{
						progressDialog.dismiss();
						popupMessege.ShowMessege1(context, response.body().getAFaultDescription());
					}
				}
				else
				{
					progressDialog.dismiss();
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgUnknownError));
				}
			}

			@Override
			public void onFailure(Call<ResponsePojo> call, Throwable t)
			{
				progressDialog.dismiss();
				popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgServerFailure));
			}
		});
	}

	private void TransPoinGetPoin()
	{
		progressDialog = ProgressDialog.show(context, getResources().getString(R.string.hintHarapTunggu),
			context.getResources().getString(R.string.msgTransaksiPoin));
		progressDialog.setCancelable(false);

		if(Fungsi.isNetworkAvailable(context) == FixValue.TYPE_NONE)
		{
			progressDialog.dismiss();
			popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgConnectionError));
			return;
		}

		POSLink posLink = Fungsi.BindingPOS();

		dateTrans = dfTrans.format(c.getTime());
		dateTrans = Fungsi.getDate(dateTrans, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd") + "T" +
			Fungsi.getTime(dateTrans, "yyyy-MM-dd HH:mm:ss", "HH:mm:ss");

		AValue aValue = Fungsi.getObjectFromSharedPref(context, AValue.class, Preference.PrefMerchantInfo);
		tvMerchantName.setText(aValue.getBAccountOwnerDisplayValue());

		Sim1SaleTransactionLine sim1SaleTransactionLine = new Sim1SaleTransactionLine();
		sim1SaleTransactionLine.setSim1LineValue(brandsRsp.getPrice());
		sim1SaleTransactionLine.setSim1ProductBrand(aValue.getbProductBrand());
		sim1SaleTransactionLine.setSim1Quantity(1);

		Sim1TransactionLines sim1TransactionLines = new Sim1TransactionLines();
		sim1TransactionLines.setSim1SaleTransactionLine(sim1SaleTransactionLine);

		SimValue simValue = new SimValue();
		simValue.setSim1CardNumber(etCardNumber.getText().toString());
		simValue.setSim1CashierCode(aValue.getBAccountRSN());
		simValue.setSim1ClientVersion("1");
		simValue.setSim1CustomerAccountID(aValue.getBAccountID());
		simValue.setSim1ItemDescription("Isi dari item description");
		simValue.setSim1PublishedByDisplayValue(aValue.getBDisplayValue());
		simValue.setSim1PublishedByRSN(aValue.getBAccountOwnerRSN());
		simValue.setSim1Quantity("1");
		simValue.setSim1Reference("9876");
		simValue.setSim1TerminalID("123456");
		simValue.setSim1TransactionDate(dateTrans);
		simValue.setSim1TransactionID(String.valueOf(brandsRsp.getIntInvoice()));
		simValue.setSim1TransactionNotes("Transaksi ini hanya untuk demo");
		simValue.setSim1TransactionRedemptionValue(brandsRsp.getPrice());
		simValue.setSim1TransactionLines(sim1TransactionLines);

		PoinRequest poinRequest = new PoinRequest();
		poinRequest.setSimToken(aValue.getBToken());
		poinRequest.setSimValue(simValue);

		RequestHolder requestHolder = new RequestHolder();
		requestHolder.setPoinRequest(poinRequest);

		final Call<ResponsePojo> ReceivePojo = posLink.TransaksiPoinService(requestHolder);

		ReceivePojo.enqueue(new Callback<ResponsePojo>()
		{
			@Override
			public void onResponse(Call<ResponsePojo> call, retrofit2.Response<ResponsePojo> response)
			{
				if(response.isSuccessful())
				{
					progressDialog.dismiss();

					if (response.body().getAFaultCode().matches("0"))
					{
						ppInformasi ppInformasi = new ppInformasi(Confirm.this,
							response.body().getAValue().getBLoyaltyPointsBalance(),
							response.body().getAValue().getBTransactionID());

						ppInformasi.show();
					}
					else
					{
						progressDialog.dismiss();
						popupMessege.ShowMessege1(context, response.body().getAFaultDescription());
					}
				}
				else
				{
					progressDialog.dismiss();
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgUnknownError));
				}
			}

			@Override
			public void onFailure(Call<ResponsePojo> call, Throwable t)
			{
				progressDialog.dismiss();
				popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgServerFailure));
			}
		});
	}

	private void TransAdjustPoin()
	{
		progressDialog = ProgressDialog.show(context, getResources().getString(R.string.hintHarapTunggu),
			context.getResources().getString(R.string.msgTransaksiAdjust));
		progressDialog.setCancelable(false);

		if(Fungsi.isNetworkAvailable(context) == FixValue.TYPE_NONE)
		{
			progressDialog.dismiss();
			popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgConnectionError));
			return;
		}

		POSLink posLink = Fungsi.BindingPOS();

		dateTrans = dfTrans.format(c.getTime());
		dateTrans = Fungsi.getDate(dateTrans, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd") + "T" +
			Fungsi.getTime(dateTrans, "yyyy-MM-dd HH:mm:ss", "HH:mm:ss");

		AValue aValue = Fungsi.getObjectFromSharedPref(context, AValue.class, Preference.PrefMerchantInfo);
		tvMerchantName.setText(aValue.getBAccountOwnerDisplayValue());

		SimValue simValue = new SimValue();
		simValue.setSim1CardNumber(etCardNumber.getText().toString());
		simValue.setSim1CashierCode(aValue.getBAccountRSN());
		simValue.setSim1ClientVersion("1");
		simValue.setSim1CustomerAccountID(aValue.getBAccountID());
		simValue.setSim1PublishedByDisplayValue(aValue.getBDisplayValue());
		simValue.setSim1PublishedByRSN(aValue.getBAccountOwnerRSN());
		simValue.setSim1TerminalID("123456");
		simValue.setSim1TransactionDate(dateTrans);
		simValue.setSim1TransactionID(String.valueOf(brandsRsp.getIntInvoice()));
		simValue.setSim1TransactionNotes("Transaksi ini hanya untuk demo");
		simValue.setSim1TransactionAdjustmentValue(Integer.valueOf(etConfirmAdjust.getText().toString()));
		simValue.setSim1AdjustmentReason("AdjustmentReason");
		simValue.setSim1AdjustmentReasonNote("AdjustmentReasonNote");
		simValue.setSim1AdjustmentType("AdjustmentType");

		PoinRequest poinRequest = new PoinRequest();
		poinRequest.setSimToken(aValue.getBToken());
		poinRequest.setSimValue(simValue);

		RequestHolder requestHolder = new RequestHolder();
		requestHolder.setPoinRequest(poinRequest);

		final Call<ResponsePojo> ReceivePojo = posLink.TransaksiAdjustService(requestHolder);

		ReceivePojo.enqueue(new Callback<ResponsePojo>()
		{
			@Override
			public void onResponse(Call<ResponsePojo> call, retrofit2.Response<ResponsePojo> response)
			{
				if(response.isSuccessful())
				{
					progressDialog.dismiss();

					if (response.body().getAFaultCode().matches("0"))
					{
						ppInformasi ppInformasi = new ppInformasi(Confirm.this,
							response.body().getAValue().getBLoyaltyPointsBalance(),
							response.body().getAValue().getBTransactionID());

						ppInformasi.show();
					}
					else
					{
						progressDialog.dismiss();
						popupMessege.ShowMessege1(context, response.body().getAFaultDescription());
					}
				}
				else
				{
					progressDialog.dismiss();
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgUnknownError));
				}
			}

			@Override
			public void onFailure(Call<ResponsePojo> call, Throwable t)
			{
				progressDialog.dismiss();
				popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgServerFailure));
			}
		});
	}

	private void CheckVoucher(final int check)
	{
		if(Fungsi.isNetworkAvailable(context) == FixValue.TYPE_NONE)
		{
			progressDialog.dismiss();
			popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgConnectionError));
			return;
		}

		POSLink posLink = Fungsi.BindingPOS();

		dateTrans = dfTrans.format(c.getTime());
		dateTrans = Fungsi.getDate(dateTrans, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd") + "T" +
			Fungsi.getTime(dateTrans, "yyyy-MM-dd HH:mm:ss", "HH:mm:ss");

		AValue aValue = Fungsi.getObjectFromSharedPref(context, AValue.class, Preference.PrefMerchantInfo);
		tvMerchantName.setText(aValue.getBAccountOwnerDisplayValue());

		SimValue simValue = new SimValue();
		simValue.setSim1CardNumber(etCardNumber.getText().toString());
		simValue.setSim1VoucherCode(etNoVoucher.getText().toString());

		PoinRequest poinRequest = new PoinRequest();
		poinRequest.setSimToken(aValue.getBToken());
		poinRequest.setSimValue(simValue);

		RequestHolder requestHolder = new RequestHolder();
		requestHolder.setPoinRequest(poinRequest);

		final Call<ResponsePojo> ReceivePojo = posLink.CheckVoucherService(requestHolder);

		ReceivePojo.enqueue(new Callback<ResponsePojo>()
		{
			@Override
			public void onResponse(Call<ResponsePojo> call, retrofit2.Response<ResponsePojo> response)
			{
				if(response.isSuccessful())
				{
					if (response.body().getAFaultCode().matches("0"))
					{
						if((check == 1) && (response.body().getAValue().getBStatus().matches("VALID")))
							TransVoucherGetPoin();
						else
							popupMessege.ShowMessege1(context, response.body().getAValue().getBStatus() +
								"\n" + response.body().getAValue().getBValidFrom() + "\n" + response.body().getAValue().getBValidTo() + "\n" +
								response.body().getAValue().getBValue());
					}
					else
						popupMessege.ShowMessege1(context, response.body().getAFaultDescription());
				}
				else
					popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgUnknownError));
			}

			@Override
			public void onFailure(Call<ResponsePojo> call, Throwable t)
			{
				popupMessege.ShowMessege1(context, context.getResources().getString(R.string.msgServerFailure));
			}
		});
	}
}
