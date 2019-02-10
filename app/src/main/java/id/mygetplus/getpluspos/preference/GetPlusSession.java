package id.mygetplus.getpluspos.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import id.mygetplus.getpluspos.ResponsePojo;
import id.mygetplus.getpluspos.helper.ConfigManager;

public class GetPlusSession {

//    static final String KEY_USER_NAME = "user";
//    static final String KEY_PASS = "pass";
//    static final String KEY_USERNAME_LOGIN = "username_logged_in";
//    static final String KEY_STILL_LOGIN ="status_logged_in";
//    public static final String SESSION_IN_PREF = "session_pref";
//    public static final String TOKEN_PREF = "token_pref";

    private SharedPreferences preferencesGetPlus;
    private static GetPlusSession sSharedPrefs;

    public GetPlusSession (Context context) {
        preferencesGetPlus = context.getSharedPreferences(
                ConfigManager.Preference.KEY, Context.MODE_PRIVATE);

    }

    public static GetPlusSession getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new GetPlusSession(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public void setSession(ResponsePojo session) {
        SharedPreferences.Editor editor = preferencesGetPlus.edit();
        editor.putString(ConfigManager.AccountSession.TOKEN, session.getAValue().getBToken());
        editor.apply();
    }


    public void setToken(String token) {
        SharedPreferences.Editor editor = preferencesGetPlus.edit();
        editor.putString(ConfigManager.AccountSession.TOKEN, token);
        editor.apply();
    }

    public String getTokenSession() {
        return preferencesGetPlus.getString(ConfigManager.AccountSession.TOKEN, "");
    }

    public void clearSession() {
        SharedPreferences.Editor editor = preferencesGetPlus.edit();
        editor.remove(ConfigManager.AccountSession.TOKEN);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return !getTokenSession().isEmpty();
    }

    public boolean isFirstLogin() {
        return preferencesGetPlus.getBoolean(ConfigManager.AccountSession.FIRST_LOGIN, false);
    }


    public  static SharedPreferences sharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
