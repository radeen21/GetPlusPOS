package id.mygetplus.getpluspos.mvp.earnpoint.model;

public class EarnListModel {

    private String merchantName;
    private String jumlah;

    public EarnListModel(String merchantName, String jumlah) {
        this.merchantName = merchantName;
        this.jumlah = jumlah;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
