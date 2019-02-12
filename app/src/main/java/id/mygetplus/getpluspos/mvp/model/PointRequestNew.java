package id.mygetplus.getpluspos.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PointRequestNew {

    @SerializedName("sim:Token")
    @Expose
    private String simToken;
    @SerializedName("sim:Value")
    @Expose
    private SimValues simValues;

    public String getSimToken() {
        return simToken;
    }

    public void setSimToken(String simToken) {
        this.simToken = simToken;
    }

    public SimValues getSimValue() {
        return simValues;
    }

    public void setSimValue(SimValues simValues) {
        this.simValues = simValues;
    }
}
