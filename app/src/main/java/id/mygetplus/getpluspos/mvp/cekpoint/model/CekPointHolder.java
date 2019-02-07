package id.mygetplus.getpluspos.mvp.cekpoint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.mygetplus.getpluspos.PoinRequest;
import id.mygetplus.getpluspos.RequestHolder;
import id.mygetplus.getpluspos.mvp.login.model.DeviceData;
import id.mygetplus.getpluspos.mvp.login.model.UserData;

public class CekPointHolder {

    @SerializedName("poin:request")
    @Expose
    private PointRequestNew poinRequest;

    public PointRequestNew getPoinRequest() {
        return poinRequest;
    }

    public void setPoinRequest(PointRequestNew poinRequest) {
        this.poinRequest = poinRequest;
    }
}
