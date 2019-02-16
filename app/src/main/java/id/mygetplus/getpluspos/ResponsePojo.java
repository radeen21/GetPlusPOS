package id.mygetplus.getpluspos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.mygetplus.getpluspos.mvp.payment.model.AvalueList;

public class ResponsePojo
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
	@SerializedName("AvalueList")
	@Expose
	private List<AvalueList> avalueLists = null;

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

	public List<AvalueList> getAvalueLists()
	{
		return avalueLists;
	}

	public void setAvalueLists(List<AvalueList> avalueLists)
	{
		this.avalueLists = avalueLists;
	}
}
