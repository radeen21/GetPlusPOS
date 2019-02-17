package id.mygetplus.getpluspos.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MerchantData
{
	@SerializedName("MerchantID")
	@Expose
	private String merchantID;
	@SerializedName("MerchantName")
	@Expose
	private String merchantName;

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
}
