package id.mygetplus.getpluspos.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {

    public static final String SESSION_IN_PREF = "session_pref";
    public static final String TOKEN_PREF = "token_pref";

    private static SaveSharedPreference sSharedPrefs;
    private static  mPref;

	private SaveSharedPreference (Context context) {
		mPref = context.getSharedPreferences(SESSION_IN_PREF, Context.MODE_PRIVATE);
	}

	public static SaveSharedPreference getInstance(Context context) {
		if (sSharedPrefs == null) {
			sSharedPrefs = new SaveSharedPreference(context.getApplicationContext());
		}
		return sSharedPrefs;
	}

    /**
     * Set the Login Status
     * @param context
     * @param token
     */
    public void setToken(String token) {
        mPref.putString(TOKEN_PREF, token);
        mPref.apply();
    }

    /**
     * Get the Login Status
     * @param context
     * @return String: token
     */
    public String getToken() {
        return mPref.getString(TOKEN_PREF, "");
    }

    public boolean anyToken() {
        return mPref.getString(TOKEN_PREF, "").isEmpty();
    }

}