package id.mygetplus.getpluspos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Dibuat oleh : ignat
 * Tanggal : 27-Aug-17
 * HP/WA : 0857 7070 6 777
 */
public class BrandsRsp
{
  @SerializedName("uid")
  @Expose
  private Integer Id;
  @SerializedName("BrandName")
  @Expose
  private String BrandName;
  @SerializedName("Detail")
  @Expose
  private String Detail;
  @SerializedName("Price")
  @Expose
  private Integer Price;
  @SerializedName("FileName")
  @Expose
  private String FileName;
  @SerializedName("ImageURL")
  @Expose
  private String ImageURL;

  private String strDate;
  private String strTime;
  private Integer intInvoice;

  public Integer getId() {
    return Id;
  }

  public void setId(Integer id) {
    Id = id;
  }

  public String getImageURL() {
    return ImageURL;
  }

  public void setImageURL(String imageURL) {
    ImageURL = imageURL;
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

  public String getFileName() {
    return FileName;
  }

  public void setFileName(String fileName) {
    FileName = fileName;
  }

  public Integer getPrice() {
    return Price;
  }

  public void setPrice(Integer price) {
    Price = price;
  }

  public String getStrDate()
  {
    return strDate;
  }

  public void setStrDate(String strDate)
  {
    this.strDate = strDate;
  }

  public String getStrTime()
  {
    return strTime;
  }

  public void setStrTime(String strTime)
  {
    this.strTime = strTime;
  }

  public Integer getIntInvoice()
  {
    return intInvoice;
  }

  public void setIntInvoice(Integer intInvoice)
  {
    this.intInvoice = intInvoice;
  }
}
