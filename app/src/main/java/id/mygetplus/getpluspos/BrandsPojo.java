package id.mygetplus.getpluspos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Dibuat oleh : ignat
 * Tanggal : 25-Jul-17
 * HP/WA : 0857 7070 6 777
 */
public class BrandsPojo
{
  @SerializedName("StatusRsp")
  @Expose
  private StatusRsp statusRsp;
  @SerializedName("MerchantBrandsRsp")
  @Expose
  private List<BrandsRsp> brandsRsps;

  public StatusRsp getStatusRsp() {
  	return statusRsp;
  }

  public void setStatusRsp(StatusRsp statusRsp) {
  	this.statusRsp = statusRsp;
  }

  public List<BrandsRsp> getBrandsRsps() {
  	return brandsRsps;
  }

  public void setBrandsRsps(List<BrandsRsp> brandsRsps) {
  	this.brandsRsps = brandsRsps;
  }
}
