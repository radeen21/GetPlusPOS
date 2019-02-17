package id.mygetplus.getpluspos.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CekPointHolder {
    @SerializedName("poin:request")
    @Expose
    private PointRequestNew poinRequest;
    @SerializedName("TransactionData")
    @Expose
    private TransactionData transactionData;
    @SerializedName("MerchantData")
    @Expose
    private MerchantData merchantData;

    public PointRequestNew getPoinRequest() {
        return poinRequest;
    }

    public void setPoinRequest(PointRequestNew poinRequest) {
        this.poinRequest = poinRequest;
    }

    public TransactionData getTransactionData()
    {
        return transactionData;
    }

    public void setTransactionData(TransactionData transactionData)
    {
        this.transactionData = transactionData;
    }

    public MerchantData getMerchantData()
    {
        return merchantData;
    }

    public void setMerchantData(MerchantData merchantData)
    {
        this.merchantData = merchantData;
    }
}
