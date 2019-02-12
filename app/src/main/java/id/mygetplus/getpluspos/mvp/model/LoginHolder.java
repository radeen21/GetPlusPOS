package id.mygetplus.getpluspos.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginHolder
{
	@SerializedName("UserData")
	@Expose
	private UserData userData;
	@SerializedName("DeviceData")
	@Expose
	private DeviceData deviceData;

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public DeviceData getDeviceData()
	{
		return deviceData;
	}

	public void setDeviceData(DeviceData deviceData)
	{
		this.deviceData = deviceData;
	}
}
