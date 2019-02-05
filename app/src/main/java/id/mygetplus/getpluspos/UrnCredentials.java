package id.mygetplus.getpluspos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UrnCredentials
{
	@SerializedName("sim:AccountName")
	@Expose
	private String simAccountName;
	@SerializedName("sim:ManagementGroup")
	@Expose
	private String simManagementGroup;
	@SerializedName("sim:SecurityCode")
	@Expose
	private String simSecurityCode;

	public String getSimAccountName() {
		return simAccountName;
	}

	public void setSimAccountName(String simAccountName) {
		this.simAccountName = simAccountName;
	}

	public String getSimManagementGroup() {
		return simManagementGroup;
	}

	public void setSimManagementGroup(String simManagementGroup) {
		this.simManagementGroup = simManagementGroup;
	}

	public String getSimSecurityCode() {
		return simSecurityCode;
	}

	public void setSimSecurityCode(String simSecurityCode) {
		this.simSecurityCode = simSecurityCode;
	}
}
