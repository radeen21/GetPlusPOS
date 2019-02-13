package id.mygetplus.getpluspos.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.mygetplus.getpluspos.AValue;

public class LoadResponsePojo
{
	@SerializedName("a:FaultCode")
	@Expose
	private String aFaultCode;
	@SerializedName("a:FaultDescription")
	@Expose
	private String aFaultDescription;
	@SerializedName("a:Value")
	@Expose
	private AValue aValue;

	public String getAFaultCode() {
		return aFaultCode;
	}

	public void setAFaultCode(String aFaultCode) {
		this.aFaultCode = aFaultCode;
	}

	public String getAFaultDescription() {
		return aFaultDescription;
	}

	public void setAFaultDescription(String aFaultDescription) {
		this.aFaultDescription = aFaultDescription;
	}

	public AValue getAValue() {
		return aValue;
	}

	public void setAValue(AValue aValue) {
		this.aValue = aValue;
	}
}
