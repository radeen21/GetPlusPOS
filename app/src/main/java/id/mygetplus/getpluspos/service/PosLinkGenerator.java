package id.mygetplus.getpluspos.service;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import id.mygetplus.getpluspos.BuildConfig;
import id.mygetplus.getpluspos.POSLink;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PosLinkGenerator {

    public POSLink provideManisApi(Context context) {
        return createService(context);
    }

//    public static <S> POSLink createServiceWithToken(Context context) {
//        return retroBuilder(okBuilderToken(context).build()).create(POSLink.class);
//    }

    public static <S> POSLink createService(Context context) {
        return retroBuilder(okBuilder(context).build()).create(POSLink.class);
    }

    private static Retrofit retroBuilder(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://mygetplus-development.azurewebsites.net/mobile/v1/201812/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient.Builder okBuilder(Context context) {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS);
    }
}
