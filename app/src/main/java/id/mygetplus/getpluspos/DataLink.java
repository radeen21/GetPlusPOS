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


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Dibuat oleh : ignat
 * Tanggal : 15-Nov-2018
 * HP/WA : 0857 7070 6 777
 */
public interface DataLink
{
  @GET(FixValue.RestfulWarehouse)
  Call<ResponseBrandsPojo> MerchantBrandService(@Path("AccountRSN") String AccountRSN);
}

