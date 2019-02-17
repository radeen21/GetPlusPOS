package id.mygetplus.getpluspos.mvp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionData
{
	@SerializedName("ReffNo")
	@Expose
	private Integer reffNo;
	@SerializedName("Amount")
	@Expose
	private Integer amount;
	@SerializedName("TransactionRSN")
	@Expose
	private String transactionRSN;
	@SerializedName("Image1")
	@Expose
	private String image1;
	@SerializedName("Image2")
	@Expose
	private String image2;

	public Integer getReffNo() {
		return reffNo;
	}

	public void setReffNo(Integer reffNo) {
		this.reffNo = reffNo;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getTransactionRSN() {
		return transactionRSN;
	}

	public void setTransactionRSN(String transactionRSN) {
		this.transactionRSN = transactionRSN;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}
}
