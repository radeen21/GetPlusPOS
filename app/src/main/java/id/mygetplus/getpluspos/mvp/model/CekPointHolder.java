package id.mygetplus.getpluspos.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
