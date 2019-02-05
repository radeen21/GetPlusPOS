package id.mygetplus.getpluspos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BProgramMemberships
{
	@SerializedName("b:ProgramMembership")
	@Expose
	private BProgramMembership bProgramMembership;

	public BProgramMembership getBProgramMembership() {
		return bProgramMembership;
	}

	public void setBProgramMembership(BProgramMembership bProgramMembership) {
		this.bProgramMembership = bProgramMembership;
	}
}
