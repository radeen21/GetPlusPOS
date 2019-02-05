package id.mygetplus.getpluspos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Dibuat oleh : ignat
 * Tanggal : 15-Nov-2018
 * HP/WA : 0857 7070 6 777
 */
public class StatusRsp {
  @SerializedName("Code")
  @Expose
  private Integer Code;
  @SerializedName("Msg")
  @Expose
  private String Msg;

  public Integer getCode() {
    return Code;
  }

  public void setCode(Integer code) {
    Code = code;
  }

  public String getMsg() {
    return Msg;
  }

  public void setMsg(String msg) {
    Msg = msg;
  }
}