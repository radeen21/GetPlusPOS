package id.mygetplus.getpluspos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountHolder
{
	@SerializedName("urn:credentials")
	@Expose
	private UrnCredentials urnCredentials;

	public UrnCredentials getUrnCredentials() {
		return urnCredentials;
	}

	public void setUrnCredentials(UrnCredentials urnCredentials) {
		this.urnCredentials = urnCredentials;
	}
}
