package id.mygetplus.getpluspos.service;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import id.mygetplus.getpluspos.BuildConfig;
import id.mygetplus.getpluspos.Fungsi;
import id.mygetplus.getpluspos.POSLink;
import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.helper.ConfigManager;
import id.mygetplus.getpluspos.preference.GetPlusSession;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static id.mygetplus.getpluspos.FixValue.POS_URL;

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
                .baseUrl(POS_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient.Builder okBuilder(Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Response response = chain.proceed(request);

                    if(response.code() != 200)
                    {
                        try
                        {
                            Gson gson = new Gson();
                            JSONObject json = new JSONObject(response.body().string());
                            ResponsePojo responsePojo = gson.fromJson(json.toString(), ResponsePojo.class);
                            Fungsi.storeObjectToSharedPref(context, responsePojo, ConfigManager.AccountSession.MSG_RESPONSE);
                        }
                        catch (JSONException e)
                        {
                            ResponsePojo responsePojo = new ResponsePojo();
                            responsePojo.setAFaultCode("-1");
                            responsePojo.setAFaultDescription("Internal unknown error");
                            Fungsi.storeObjectToSharedPref(context, responsePojo, ConfigManager.AccountSession.MSG_RESPONSE);
                        }
                    }

                    return response;
                });
    }

    private static Request requestBuilderToken(Interceptor.Chain chain, Context context) {
        Request.Builder requestBuilder = chain.request().newBuilder();
//        RequestBuilderHeader requestBuilderHeader = new RequestBuilderHeader(requestBuilder, context);
//        requestBuilderHeader.addToken();
//        requestBuilderHeader.addCoordinate();
//        requestBuilderHeader.addLang();
//        requestBuilderHeader.addManisVer();
//        requestBuilderHeader.addManisBuild();
        return requestBuilder.build();
    }
}
