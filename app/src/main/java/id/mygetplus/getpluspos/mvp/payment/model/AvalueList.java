package id.mygetplus.getpluspos.mvp.payment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AvalueList {

    @SerializedName("id")
    @Expose
    private int Id;
    @SerializedName("BrandName")
    @Expose
    private String BrandName;
    @SerializedName("Detail")
    @Expose
    private String Detail;
    @SerializedName("Price")
    @Expose
    private int Price;
    @SerializedName("FileName")
    @Expose
    private String FileName;
    @SerializedName("ImageURL")
    @Expose
    private String ImageURL;
    @SerializedName("AccountRSN")
    @Expose
    private String AccountRSN;
    @SerializedName("PosID")
    @Expose
    private int PosID;
    @SerializedName("MinQty")
    @Expose
    private int MinQty;
    @SerializedName("MaxQty")
    @Expose
    private int MaxQty;
    @SerializedName("Created")
    @Expose
    private String Created;
    @SerializedName("Notes")
    @Expose
    private String Notes;

    private String strDate;
    private String strTime;
    private Integer intInvoice;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getAccountRSN() {
        return AccountRSN;
    }

    public void setAccountRSN(String accountRSN) {
        AccountRSN = accountRSN;
    }

    public int getPosID() {
        return PosID;
    }

    public void setPosID(int posID) {
        PosID = posID;
    }

    public int getMinQty() {
        return MinQty;
    }

    public void setMinQty(int minQty) {
        MinQty = minQty;
    }

    public int getMaxQty() {
        return MaxQty;
    }

    public void setMaxQty(int maxQty) {
        MaxQty = maxQty;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String created) {
        Created = created;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public Integer getIntInvoice() {
        return intInvoice;
    }

    public void setIntInvoice(Integer intInvoice) {
        this.intInvoice = intInvoice;
    }
}
