/*
 * Copyright (c) 2016 oleh Agustinus Ignat Deswanto
 *
 *  Dilarang menyalah gunakan aplikasi ini terutama untuk tindak kejahatan.
 *  Jika ada pertanyaan seputar aplikasi ini silakan menghubungi :
 *
 *  Agustinus Ignat Deswanto
 *  Permata Depok Nilam F5a No. 5
 *  Citayam - Depok 16430
 *  Jawa Barat - Indonesia
 *  HP/WA : 085770706777
 *  Email : aignatd@gmail.com
 *
 */

package id.mygetplus.getpluspos;


import id.mygetplus.getpluspos.mvp.model.CekPointHolder;
import id.mygetplus.getpluspos.mvp.model.LoginHolder;
import id.mygetplus.getpluspos.service.response.WrapperLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Dibuat oleh : ignat
 * Tanggal : 15-Nov-2018
 * HP/WA : 0857 7070 6 777
 */
public interface POSLink
{
  @POST(FixValue.RestfulAccountPOS)
  Call<ResponsePojo> AccountPOSService(@Body AccountHolder accountHolder);

  @POST(FixValue.RestfulSelectMember)
  Call<ResponsePojo> SelectMemberService(@Body RequestHolder requestHolder);

  @POST(FixValue.RestTransaksiCash)
  Call<ResponsePojo> TransaksiCashService(@Body RequestHolder requestHolder);

  @POST(FixValue.RestVoucherStatus)
  Call<ResponsePojo> CheckVoucherService(@Body RequestHolder requestHolder);

	@POST(FixValue.RestVoucherRedeem)
	Call<ResponsePojo> VoucherRedeemService(@Body RequestHolder requestHolder);

	@POST(FixValue.RestTransaksiPoin)
	Call<ResponsePojo> TransaksiPoinService(@Body RequestHolder requestHolder);

	@POST(FixValue.RestTransaksiAdjust)
	Call<ResponsePojo> TransaksiAdjustService(@Body RequestHolder requestHolder);

	@POST(FixValue.RestLogin)
	Observable<WrapperLogin> getLogin(@Body AccountHolder accountHolder);

	@POST(FixValue.RestLogin)
	Observable<ResponsePojo> getUserLogin(@Body LoginHolder loginHolder);

	@POST(FixValue.RestfulSelectMember)
	Observable<ResponsePojo> getPoints(@Body CekPointHolder cekPointHolder);

	@POST(FixValue.RestLogout)
	Observable<ResponsePojo> getUserLogout(@Body LoginHolder loginHolder);

	@POST(FixValue.RestVoucher)
	Observable<ResponsePojo> VoucherService(@Body CekPointHolder cekPointHolder);

	@POST(FixValue.TukarPoint)
	Observable<ResponsePojo> getEarnPoint(@Body CekPointHolder requestHolder);

	@POST(FixValue.TukarPoint)
	Observable<ResponsePojo> TukarPoint(@Body CekPointHolder cekPointHolder);
}

