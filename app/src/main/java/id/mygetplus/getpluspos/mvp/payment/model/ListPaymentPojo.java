package id.mygetplus.getpluspos.mvp.payment.model;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import id.mygetplus.getpluspos.BrandsRsp;

public class ListPaymentPojo {

    @SerializedName("a:FaultCode")
    @Expose
    private int aFaultCode;
    @SerializedName("a:FaultDescription")
    @Expose
    private String aFaultDescription;
    @SerializedName("a:Value")
    @Expose
    private List<AvalueList> avalueLists;

    public int getaFaultCode() {
        return aFaultCode;
    }

    public void setaFaultCode(int aFaultCode) {
        this.aFaultCode = aFaultCode;
    }

    public String getaFaultDescription() {
        return aFaultDescription;
    }

    public void setaFaultDescription(String aFaultDescription) {
        this.aFaultDescription = aFaultDescription;
    }

    public List<AvalueList> getAvalueLists() {
        return avalueLists;
    }

    public void setAvalueLists(List<AvalueList> avalueLists) {
        this.avalueLists = avalueLists;
    }
}
