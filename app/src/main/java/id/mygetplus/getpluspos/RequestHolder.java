package id.mygetplus.getpluspos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestHolder
{
	@SerializedName("poin:request")
	@Expose
	private PoinRequest poinRequest;

	public PoinRequest getPoinRequest() {
		return poinRequest;
	}

	public void setPoinRequest(PoinRequest poinRequest) {
		this.poinRequest = poinRequest;
	}
}
