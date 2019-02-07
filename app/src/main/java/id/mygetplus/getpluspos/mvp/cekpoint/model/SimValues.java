package id.mygetplus.getpluspos.mvp.cekpoint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SimValues {

    @SerializedName("sim1:CardNumber")
    @Expose
    private String sim1Cardnumber;

    @SerializedName("sim1:ReturnAllMemberData")
    @Expose
    private String sim1ReturnAllMemberData;

    public String getSim1Cardnumber() {
        return sim1Cardnumber;
    }

    public void setSim1Cardnumber(String sim1Cardnumber) {
        this.sim1Cardnumber = sim1Cardnumber;
    }

    public String getSim1ReturnAllMemberData() {
        return sim1ReturnAllMemberData;
    }

    public void setSim1ReturnAllMemberData(String sim1ReturnAllMemberData) {
        this.sim1ReturnAllMemberData = sim1ReturnAllMemberData;
    }
}
