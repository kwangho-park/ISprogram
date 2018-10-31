package dto;
import controller.Controller;

public class SavingInfoDTO {
	
	String[] savingInfo = new String[6];
/*
*	savingInfo[0] : ID					primary key
*	savingInfo[1] : 은행종류				bank
*	savingInfo[2] : 적금종류 (단리/복리)	savingType
*	savingInfo[3] :	납입기간 				year
*	savingInfo[4] :	월 납입금				money
*	savingInfo[5] : 연 이율				interestRate
*/	
	
	private Controller controller;

	
	public SavingInfoDTO(){
	}
	
	public SavingInfoDTO(Controller controller){
		this.controller = controller;
	}

	// setter //
	public void setAll(String id, String bank, String savingType, String year, String money, String interestRate){
		this.savingInfo[0] = id;		
		this.savingInfo[1] = bank;
		this.savingInfo[2] = savingType;
		this.savingInfo[3] = year;
		this.savingInfo[4] = money;
		this.savingInfo[5] = interestRate;
	}
	
	public void setId(String id) {
		this.savingInfo[0] = id;
	}
	
	public void setBank(String bank) {
		this.savingInfo[1] = bank;
	}
	
	public void setSavingType(String savingType) {
		this.savingInfo[2] = savingType;
	}
	
	public void setYear(String year) {
		this.savingInfo[3] = year;
	}
	
	void setMoney(String money) {
		this.savingInfo[4] = money;
	}
	
	void setInterestRate(String interestRate) {
		this.savingInfo[5] = interestRate;
	}
	
	// getter //
	public String getId() {
		return this.savingInfo[0];
	}
	public String getBank() {
		return this.savingInfo[1];
	}
	
	public String getSavingType() {
		return this.savingInfo[2];
	}
	
	public String getYear() {
		return this.savingInfo[3];
	}
	
	public String getMoney() {
		return this.savingInfo[4];
	}
	
	public String getInterestRate() {
		return this.savingInfo[5];
	}
	
	
} // SavingInfoDTO END
