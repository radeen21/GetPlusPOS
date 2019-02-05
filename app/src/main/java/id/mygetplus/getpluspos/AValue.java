package id.mygetplus.getpluspos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AValue
{
	// POS Account Services
	@SerializedName("b:AccountEmailAddress")
	@Expose
	private String bAccountEmailAddress;
	@SerializedName("b:AccountID")
	@Expose
	private String bAccountID;
	@SerializedName("b:AccountName")
	@Expose
	private String bAccountName;
	@SerializedName("b:AccountOwnerDisplayValue")
	@Expose
	private String bAccountOwnerDisplayValue;
	@SerializedName("b:AccountOwnerRSN")
	@Expose
	private String bAccountOwnerRSN;
	@SerializedName("b:AccountOwnerType")
	@Expose
	private String bAccountOwnerType;
	@SerializedName("b:AccountRSN")
	@Expose
	private String bAccountRSN;
	@SerializedName("b:ControlGroups")
	@Expose
	private String bControlGroups;
	@SerializedName("b:Roles")
	@Expose
	private String bRoles;
	@SerializedName("b:Token")
	@Expose
	private String bToken;
	@SerializedName("b:TokenExpiry")
	@Expose
	private String bTokenExpiry;
	// Akhir POS Account Services

	// Awal POS Loyalty Services
	@SerializedName("b:DisplayValue")
	@Expose
	private String bDisplayValue;
	@SerializedName("b:LanguagePreference_KeyCode")
	@Expose
	private String bLanguagePreferenceKeyCode;
	@SerializedName("b:Member_RSN")
	@Expose
	private String bMemberRSN;
	@SerializedName("b:Member_Type")
	@Expose
	private String bMemberType;
	@SerializedName("b:Phone_Status_KeyCode")
	@Expose
	private String bPhoneStatusKeyCode;
	@SerializedName("b:SequenceNumber")
	@Expose
	private String bSequenceNumber;
	@SerializedName("b:ProgramMemberships")
	@Expose
	private BProgramMemberships bProgramMemberships;

	@SerializedName("b:LoyaltyPointsBalance")
	@Expose
	private String bLoyaltyPointsBalance;
	@SerializedName("b:TransactionID")
	@Expose
	private String bTransactionID;
	@SerializedName("b:Transaction_RSN")
	@Expose
	private String bTransactionRSN;
	@SerializedName("b:Status")
	@Expose
	private String bStatus;
	@SerializedName("b:ValidFrom")
	@Expose
	private String bValidFrom;
	@SerializedName("b:ValidTo")
	@Expose
	private String bValidTo;
	@SerializedName("b:Value")
	@Expose
	private String bValue;
	@SerializedName("b:ProductBrand")
	@Expose
	private String bProductBrand;
	@SerializedName("b:BrandName")
	@Expose
	private String bBrandName;
	// Akhir POS Loyalty Services

	public String getBAccountEmailAddress() {
		return bAccountEmailAddress;
	}

	public void setBAccountEmailAddress(String bAccountEmailAddress) {
		this.bAccountEmailAddress = bAccountEmailAddress;
	}

	public String getBAccountID() {
		return bAccountID;
	}

	public void setBAccountID(String bAccountID) {
		this.bAccountID = bAccountID;
	}

	public String getBAccountName() {
		return bAccountName;
	}

	public void setBAccountName(String bAccountName) {
		this.bAccountName = bAccountName;
	}

	public String getBAccountOwnerDisplayValue() {
		return bAccountOwnerDisplayValue;
	}

	public void setBAccountOwnerDisplayValue(String bAccountOwnerDisplayValue) {
		this.bAccountOwnerDisplayValue = bAccountOwnerDisplayValue;
	}

	public String getBAccountOwnerRSN() {
		return bAccountOwnerRSN;
	}

	public void setBAccountOwnerRSN(String bAccountOwnerRSN) {
		this.bAccountOwnerRSN = bAccountOwnerRSN;
	}

	public String getBAccountOwnerType() {
		return bAccountOwnerType;
	}

	public void setBAccountOwnerType(String bAccountOwnerType) {
		this.bAccountOwnerType = bAccountOwnerType;
	}

	public String getBAccountRSN() {
		return bAccountRSN;
	}

	public void setBAccountRSN(String bAccountRSN) {
		this.bAccountRSN = bAccountRSN;
	}

	public String getBControlGroups() {
		return bControlGroups;
	}

	public void setBControlGroups(String bControlGroups) {
		this.bControlGroups = bControlGroups;
	}

	public String getBRoles() {
		return bRoles;
	}

	public void setBRoles(String bRoles) {
		this.bRoles = bRoles;
	}

	public String getBToken() {
		return bToken;
	}

	public void setBToken(String bToken) {
		this.bToken = bToken;
	}

	public String getBTokenExpiry() {
		return bTokenExpiry;
	}

	public void setBTokenExpiry(String bTokenExpiry) {
		this.bTokenExpiry = bTokenExpiry;
	}

	public String getBDisplayValue() {
		return bDisplayValue;
	}

	public void setBDisplayValue(String bDisplayValue) {
		this.bDisplayValue = bDisplayValue;
	}

	public String getBLanguagePreferenceKeyCode() {
		return bLanguagePreferenceKeyCode;
	}

	public void setBLanguagePreferenceKeyCode(String bLanguagePreferenceKeyCode) {
		this.bLanguagePreferenceKeyCode = bLanguagePreferenceKeyCode;
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

	public String getBPhoneStatusKeyCode() {
		return bPhoneStatusKeyCode;
	}

	public void setBPhoneStatusKeyCode(String bPhoneStatusKeyCode) {
		this.bPhoneStatusKeyCode = bPhoneStatusKeyCode;
	}

	public String getBSequenceNumber() {
		return bSequenceNumber;
	}

	public void setBSequenceNumber(String bSequenceNumber) {
		this.bSequenceNumber = bSequenceNumber;
	}

	public BProgramMemberships getBProgramMemberships() {
		return bProgramMemberships;
	}

	public void setBProgramMemberships(BProgramMemberships bProgramMemberships) {
		this.bProgramMemberships = bProgramMemberships;
	}

	public String getBLoyaltyPointsBalance() {
		return bLoyaltyPointsBalance;
	}

	public void setBLoyaltyPointsBalance(String bLoyaltyPointsBalance) {
		this.bLoyaltyPointsBalance = bLoyaltyPointsBalance;
	}

	public String getBTransactionID() {
		return bTransactionID;
	}

	public void setBTransactionID(String bTransactionID) {
		this.bTransactionID = bTransactionID;
	}

	public String getBTransactionRSN() {
		return bTransactionRSN;
	}

	public void setBTransactionRSN(String bTransactionRSN) {
		this.bTransactionRSN = bTransactionRSN;
	}

	public String getBStatus() {
		return bStatus;
	}

	public void setBStatus(String bStatus) {
		this.bStatus = bStatus;
	}

	public String getBValidFrom() {
		return bValidFrom;
	}

	public void setBValidFrom(String bValidFrom) {
		this.bValidFrom = bValidFrom;
	}

	public String getBValidTo() {
		return bValidTo;
	}

	public void setBValidTo(String bValidTo) {
		this.bValidTo = bValidTo;
	}

	public String getBValue() {
		return bValue;
	}

	public void setBValue(String bValue) {
		this.bValue = bValue;
	}

	public String getbProductBrand()
	{
		return bProductBrand;
	}

	public void setbProductBrand(String bProductBrand)
	{
		this.bProductBrand = bProductBrand;
	}

	public String getbBrandName()
	{
		return bBrandName;
	}

	public void setbBrandName(String bBrandName)
	{
		this.bBrandName = bBrandName;
	}
}
