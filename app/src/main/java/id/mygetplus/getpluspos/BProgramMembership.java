package id.mygetplus.getpluspos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BProgramMembership
{
	@SerializedName("b:BranchName")
	@Expose
	private String bBranchName;
	@SerializedName("b:CancelReason")
	@Expose
	private String bCancelReason;
	@SerializedName("b:DisplayValue")
	@Expose
	private String bDisplayValue;
	@SerializedName("b:EndDate")
	@Expose
	private String bEndDate;
	@SerializedName("b:HomeBranch_Organisation_RSN")
	@Expose
	private String bHomeBranchOrganisationRSN;
	@SerializedName("b:JoiningKeyCode")
	@Expose
	private String bJoiningKeyCode;
	@SerializedName("b:MemberRSN")
	@Expose
	private String bMemberRSN;
	@SerializedName("b:MemberType")
	@Expose
	private String bMemberType;
	@SerializedName("b:OriginalStartDate")
	@Expose
	private String bOriginalStartDate;
	@SerializedName("b:PendingPointsBalance")
	@Expose
	private String bPendingPointsBalance;
	@SerializedName("b:PointsBalance")
	@Expose
	private String bPointsBalance;
	@SerializedName("b:PointsCalculatedForEverySpend")
	@Expose
	private String bPointsCalculatedForEverySpend;
	@SerializedName("b:PointsConversionFactor")
	@Expose
	private String bPointsConversionFactor;
	@SerializedName("b:PointsConversionFactorActive")
	@Expose
	private String bPointsConversionFactorActive;
	@SerializedName("b:PointsConversionFactorAllocationSequence")
	@Expose
	private String bPointsConversionFactorAllocationSequence;
	@SerializedName("b:PointsConversionRoundingMethod")
	@Expose
	private String bPointsConversionRoundingMethod;
	@SerializedName("b:PointsExpiredExemption")
	@Expose
	private String bPointsExpiredExemption;
	@SerializedName("b:PointsExpiring")
	@Expose
	private String bPointsExpiring;
	@SerializedName("b:PointsExpiryPeriod")
	@Expose
	private String bPointsExpiryPeriod;
	@SerializedName("b:Points_ProcessNegativeSaleTransactionItemsFromDate")
	@Expose
	private String bPointsProcessNegativeSaleTransactionItemsFromDate;
	@SerializedName("b:Points_SocialBalance")
	@Expose
	private String bPointsSocialBalance;
	@SerializedName("b:Points_SocialExpiring")
	@Expose
	private String bPointsSocialExpiring;
	@SerializedName("b:ProgramCode")
	@Expose
	private String bProgramCode;
	@SerializedName("b:ProgramDisplayValue")
	@Expose
	private String bProgramDisplayValue;
	@SerializedName("b:ProgramRSN")
	@Expose
	private String bProgramRSN;
	@SerializedName("b:PromotionalPointsConversionFactor")
	@Expose
	private String bPromotionalPointsConversionFactor;
	@SerializedName("b:PromotionalPointsConversionFactorActive")
	@Expose
	private String bPromotionalPointsConversionFactorActive;
	@SerializedName("b:PromotionalPointsConversionFactorAllocationSequence")
	@Expose
	private String bPromotionalPointsConversionFactorAllocationSequence;
	@SerializedName("b:RSN")
	@Expose
	private String bRSN;
	@SerializedName("b:RedemptionOnHold")
	@Expose
	private String bRedemptionOnHold;
	@SerializedName("b:StartDate")
	@Expose
	private String bStartDate;
	@SerializedName("b:TierName")
	@Expose
	private String bTierName;

	public String getBBranchName() {
		return bBranchName;
	}

	public void setBBranchName(String bBranchName) {
		this.bBranchName = bBranchName;
	}

	public String getBCancelReason() {
		return bCancelReason;
	}

	public void setBCancelReason(String bCancelReason) {
		this.bCancelReason = bCancelReason;
	}

	public String getBDisplayValue() {
		return bDisplayValue;
	}

	public void setBDisplayValue(String bDisplayValue) {
		this.bDisplayValue = bDisplayValue;
	}

	public String getBEndDate() {
		return bEndDate;
	}

	public void setBEndDate(String bEndDate) {
		this.bEndDate = bEndDate;
	}

	public String getBHomeBranchOrganisationRSN() {
		return bHomeBranchOrganisationRSN;
	}

	public void setBHomeBranchOrganisationRSN(String bHomeBranchOrganisationRSN) {
		this.bHomeBranchOrganisationRSN = bHomeBranchOrganisationRSN;
	}

	public String getBJoiningKeyCode() {
		return bJoiningKeyCode;
	}

	public void setBJoiningKeyCode(String bJoiningKeyCode) {
		this.bJoiningKeyCode = bJoiningKeyCode;
	}

	public String getBMemberRSN() {
		return bMemberRSN;
	}

	public void setBMemberRSN(String bMemberRSN) {
		this.bMemberRSN = bMemberRSN;
	}

	public String getBMemberType() {
		return bMemberType;
	}

	public void setBMemberType(String bMemberType) {
		this.bMemberType = bMemberType;
	}

	public String getBOriginalStartDate() {
		return bOriginalStartDate;
	}

	public void setBOriginalStartDate(String bOriginalStartDate) {
		this.bOriginalStartDate = bOriginalStartDate;
	}

	public String getBPendingPointsBalance() {
		return bPendingPointsBalance;
	}

	public void setBPendingPointsBalance(String bPendingPointsBalance) {
		this.bPendingPointsBalance = bPendingPointsBalance;
	}

	public String getBPointsBalance() {
		return bPointsBalance;
	}

	public void setBPointsBalance(String bPointsBalance) {
		this.bPointsBalance = bPointsBalance;
	}

	public String getBPointsCalculatedForEverySpend() {
		return bPointsCalculatedForEverySpend;
	}

	public void setBPointsCalculatedForEverySpend(String bPointsCalculatedForEverySpend) {
		this.bPointsCalculatedForEverySpend = bPointsCalculatedForEverySpend;
	}

	public String getBPointsConversionFactor() {
		return bPointsConversionFactor;
	}

	public void setBPointsConversionFactor(String bPointsConversionFactor) {
		this.bPointsConversionFactor = bPointsConversionFactor;
	}

	public String getBPointsConversionFactorActive() {
		return bPointsConversionFactorActive;
	}

	public void setBPointsConversionFactorActive(String bPointsConversionFactorActive) {
		this.bPointsConversionFactorActive = bPointsConversionFactorActive;
	}

	public String getBPointsConversionFactorAllocationSequence() {
		return bPointsConversionFactorAllocationSequence;
	}

	public void setBPointsConversionFactorAllocationSequence(String bPointsConversionFactorAllocationSequence) {
		this.bPointsConversionFactorAllocationSequence = bPointsConversionFactorAllocationSequence;
	}

	public String getBPointsConversionRoundingMethod() {
		return bPointsConversionRoundingMethod;
	}

	public void setBPointsConversionRoundingMethod(String bPointsConversionRoundingMethod) {
		this.bPointsConversionRoundingMethod = bPointsConversionRoundingMethod;
	}

	public String getBPointsExpiredExemption() {
		return bPointsExpiredExemption;
	}

	public void setBPointsExpiredExemption(String bPointsExpiredExemption) {
		this.bPointsExpiredExemption = bPointsExpiredExemption;
	}

	public String getBPointsExpiring() {
		return bPointsExpiring;
	}

	public void setBPointsExpiring(String bPointsExpiring) {
		this.bPointsExpiring = bPointsExpiring;
	}

	public String getBPointsExpiryPeriod() {
		return bPointsExpiryPeriod;
	}

	public void setBPointsExpiryPeriod(String bPointsExpiryPeriod) {
		this.bPointsExpiryPeriod = bPointsExpiryPeriod;
	}

	public String getBPointsProcessNegativeSaleTransactionItemsFromDate() {
		return bPointsProcessNegativeSaleTransactionItemsFromDate;
	}

	public void setBPointsProcessNegativeSaleTransactionItemsFromDate(String bPointsProcessNegativeSaleTransactionItemsFromDate) {
		this.bPointsProcessNegativeSaleTransactionItemsFromDate = bPointsProcessNegativeSaleTransactionItemsFromDate;
	}

	public String getBPointsSocialBalance() {
		return bPointsSocialBalance;
	}

	public void setBPointsSocialBalance(String bPointsSocialBalance) {
		this.bPointsSocialBalance = bPointsSocialBalance;
	}

	public String getBPointsSocialExpiring() {
		return bPointsSocialExpiring;
	}

	public void setBPointsSocialExpiring(String bPointsSocialExpiring) {
		this.bPointsSocialExpiring = bPointsSocialExpiring;
	}

	public String getBProgramCode() {
		return bProgramCode;
	}

	public void setBProgramCode(String bProgramCode) {
		this.bProgramCode = bProgramCode;
	}

	public String getBProgramDisplayValue() {
		return bProgramDisplayValue;
	}

	public void setBProgramDisplayValue(String bProgramDisplayValue) {
		this.bProgramDisplayValue = bProgramDisplayValue;
	}

	public String getBProgramRSN() {
		return bProgramRSN;
	}

	public void setBProgramRSN(String bProgramRSN) {
		this.bProgramRSN = bProgramRSN;
	}

	public String getBPromotionalPointsConversionFactor() {
		return bPromotionalPointsConversionFactor;
	}

	public void setBPromotionalPointsConversionFactor(String bPromotionalPointsConversionFactor) {
		this.bPromotionalPointsConversionFactor = bPromotionalPointsConversionFactor;
	}

	public String getBPromotionalPointsConversionFactorActive() {
		return bPromotionalPointsConversionFactorActive;
	}

	public void setBPromotionalPointsConversionFactorActive(String bPromotionalPointsConversionFactorActive) {
		this.bPromotionalPointsConversionFactorActive = bPromotionalPointsConversionFactorActive;
	}

	public String getBPromotionalPointsConversionFactorAllocationSequence() {
		return bPromotionalPointsConversionFactorAllocationSequence;
	}

	public void setBPromotionalPointsConversionFactorAllocationSequence(String bPromotionalPointsConversionFactorAllocationSequence) {
		this.bPromotionalPointsConversionFactorAllocationSequence = bPromotionalPointsConversionFactorAllocationSequence;
	}

	public String getBRSN() {
		return bRSN;
	}

	public void setBRSN(String bRSN) {
		this.bRSN = bRSN;
	}

	public String getBRedemptionOnHold() {
		return bRedemptionOnHold;
	}

	public void setBRedemptionOnHold(String bRedemptionOnHold) {
		this.bRedemptionOnHold = bRedemptionOnHold;
	}

	public String getBStartDate() {
		return bStartDate;
	}

	public void setBStartDate(String bStartDate) {
		this.bStartDate = bStartDate;
	}

	public String getBTierName() {
		return bTierName;
	}

	public void setBTierName(String bTierName) {
		this.bTierName = bTierName;
	}
}
