package model;
import controller.Controller;

// Model  - business service //
// simple||compound ����Ŭ���� (�����) //
// intrest (����) + calculator (����)
public class InterestCal {

	public String resultStr;
	
	InterestCal(){}
	
	private Controller controller;
	
	
	public InterestCal(Controller controller){
		this.controller = controller;
	}
	
	
	// 6. simple || compound ���͸� �޼ҵ� //
	public void filter(String[] saving) {
		int resultInt = 0;		// simple || compound ������ ��� (����� �Ѿ�)
		
		if(saving[1].equals("�ܸ�")) {
			resultInt = simpleInterestCal(saving);		// ���ԱⰣ, �� ���Ա�, �� ����
			
		}else if(saving[1].equals("����")){
			resultInt = compoundInterestCal(saving);	// ���ԱⰣ, �� ���Ա�, �� ����
			
		}	
		resultStr = Integer.toString(resultInt);		//  TextField ����� ���� ��ȯ
	}
	
	// 7. �ܸ� ������ �����ϴ� �޼ҵ� //
	// simpleInterestCal : simple interest �ܸ�(������ ����) + calculation ���
	public int simpleInterestCal(String[] saving) {		
		int yearCal = 0;
		int moneyCal = 0;
		double interestRateCal = 0.0;
		
		int resultInt = 0;
		double resultDou = 0.0;
		
		
		// ������ ���� data type�� ��ȯ (Sting -> int)
		yearCal = Integer.parseInt(saving[2]);
		moneyCal = Integer.parseInt(saving[3]);
		interestRateCal = Double.parseDouble(saving[4]);
		
		
		// �ܸ����� ���� logic = ���� * (1 + ���� * �Ⱓ) [������]
		if (yearCal == 1) {
			resultDou = (moneyCal * 12) + ((moneyCal * 12) * interestRateCal * 0.01);
		} else if (yearCal == 2) {
			resultDou = (moneyCal * 24) + ((moneyCal * 24) * interestRateCal * 0.01);
		} else
			resultDou = (moneyCal * 36) + ((moneyCal * 36) * interestRateCal * 0.01);
			
		resultInt = (int) resultDou;	
		return resultInt;
	}
	
	
	
	// 8. ���� ������ �����ϴ� �޼ҵ� //
	// Compound Interest cal : compound interest ����(������ ����) + calculation ���
	public int compoundInterestCal(String[] saving) {		
		int yearCal = 0;
		int moneyCal = 0;
		double interestRateCal = 0.0;
		
		int resultInt = 0;
		double resultDou = 0.0;
		
		// ������ ���� ������ Ÿ���� ��ȯ
		yearCal = Integer.parseInt(saving[2]);
		moneyCal = Integer.parseInt(saving[3]);
		interestRateCal = Double.parseDouble(saving[4]);
		
		// �������� ���� logic = ���� * (1+����)^�Ⱓ [������]
		// ���� ������ ����


		if (yearCal == 1) {
			resultDou = (moneyCal * 12) * Math.pow(1+interestRateCal*0.01, 1);		// Math.pow : ������ �޼ҵ� (java.lang Ŭ����)
		} else if (yearCal == 2) {
			resultDou = (moneyCal * 24) * Math.pow(1+interestRateCal*0.01, 2);
		} else
			resultDou = (moneyCal * 36) * Math.pow(1+interestRateCal*0.01, 3);

		resultInt = (int) resultDou;	
		return resultInt;
	}
}// class InterestCal END
