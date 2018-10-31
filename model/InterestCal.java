package model;
import controller.Controller;

// Model  - business service //
// simple||compound 연산클래스 (연산부) //
// intrest (이자) + calculator (계산기)
public class InterestCal {

	public String resultStr;
	
	InterestCal(){}
	
	private Controller controller;
	
	
	public InterestCal(Controller controller){
		this.controller = controller;
	}
	
	
	// 6. simple || compound 필터링 메소드 //
	public void filter(String[] saving) {
		int resultInt = 0;		// simple || compound 연산의 결과 (만기시 총액)
		
		if(saving[1].equals("단리")) {
			resultInt = simpleInterestCal(saving);		// 납입기간, 월 납입금, 연 이율
			
		}else if(saving[1].equals("복리")){
			resultInt = compoundInterestCal(saving);	// 납입기간, 월 납입금, 연 이율
			
		}	
		resultStr = Integer.toString(resultInt);		//  TextField 출력을 위한 변환
	}
	
	// 7. 단리 이율을 연산하는 메소드 //
	// simpleInterestCal : simple interest 단리(간단한 이자) + calculation 계산
	public int simpleInterestCal(String[] saving) {		
		int yearCal = 0;
		int moneyCal = 0;
		double interestRateCal = 0.0;
		
		int resultInt = 0;
		double resultDou = 0.0;
		
		
		// 연산을 위한 data type의 변환 (Sting -> int)
		yearCal = Integer.parseInt(saving[2]);
		moneyCal = Integer.parseInt(saving[3]);
		interestRateCal = Double.parseDouble(saving[4]);
		
		
		// 단리적금 연산 logic = 원금 * (1 + 이율 * 기간) [연단위]
		if (yearCal == 1) {
			resultDou = (moneyCal * 12) + ((moneyCal * 12) * interestRateCal * 0.01);
		} else if (yearCal == 2) {
			resultDou = (moneyCal * 24) + ((moneyCal * 24) * interestRateCal * 0.01);
		} else
			resultDou = (moneyCal * 36) + ((moneyCal * 36) * interestRateCal * 0.01);
			
		resultInt = (int) resultDou;	
		return resultInt;
	}
	
	
	
	// 8. 복리 이율을 연산하는 메소드 //
	// Compound Interest cal : compound interest 복리(복합의 이자) + calculation 계산
	public int compoundInterestCal(String[] saving) {		
		int yearCal = 0;
		int moneyCal = 0;
		double interestRateCal = 0.0;
		
		int resultInt = 0;
		double resultDou = 0.0;
		
		// 연산을 위한 데이터 타입의 변환
		yearCal = Integer.parseInt(saving[2]);
		moneyCal = Integer.parseInt(saving[3]);
		interestRateCal = Double.parseDouble(saving[4]);
		
		// 복리적금 연산 logic = 원금 * (1+이율)^기간 [연단위]
		// 추후 월단위 구현


		if (yearCal == 1) {
			resultDou = (moneyCal * 12) * Math.pow(1+interestRateCal*0.01, 1);		// Math.pow : 제곱근 메소드 (java.lang 클래스)
		} else if (yearCal == 2) {
			resultDou = (moneyCal * 24) * Math.pow(1+interestRateCal*0.01, 2);
		} else
			resultDou = (moneyCal * 36) * Math.pow(1+interestRateCal*0.01, 3);

		resultInt = (int) resultDou;	
		return resultInt;
	}
}// class InterestCal END
