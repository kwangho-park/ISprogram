package model;
import javax.swing.JOptionPane;

import controller.Controller;


// Model  - business service //
//고객데이터를 입력받는 클래스//
// ScClientData : scanner 입력 + client 고객 + data 자료
public class ScSavingInfo {
	

	public String[] saving = new String[5];		// [DB] 고객의 적금정보

/*
	saving[0];		// 1. 은행종류				bank
	saving[1];		// 2. 적금종류 (단리/복리)		savingType
	saving[2];		// 3. 납입기간 				year
	saving[3];		// 4. 월 납입금				money
	saving[4];		// 5. 연 이율					interestRate
*/

	private Controller controller;
	
	
	public ScSavingInfo() {}

	public ScSavingInfo(Controller controller){
		this.controller = controller;
	}
	
	// 1.은행종류를 입력 받는 메소드 //
	public boolean bank(String bank) {
		
		boolean result = false;
		
		// 고객이 입력한 은행명을 구분하는 로직
		if (bank.equals("신한") || bank.equals("sc") || bank.equals("농협") || bank.equals("우체국")
				|| bank.equals("기업")) {
			saving[0] = bank;
			result = true;
		} else {
			JOptionPane.showMessageDialog(null, "은행명을 잘못 입력하였습니다. (신한/sc/농협/우체국/기업)");	
		}
		return result;
	}
	

	// 2.적금 종류를 입력받는 메소드 //
	public boolean savingType(String savingType) {

		boolean result = false;
		
		// 입력한 적금종를 구분하는 로직
		if (savingType.equals("단리") || savingType.equals("복리")) {
			saving[1] = savingType;
			result = true;
		} else {
			JOptionPane.showMessageDialog(null, "적금종류를 잘못 입력하였습니다. (단리/복리)");	
		}
		return result;
	}

	
	// 3.납입기간을 입력 받는 메소드 //
	public boolean year(String yearStr) {
		
		int yearInt; 		// 입력값 확인용
		boolean result = false;
		
		// 입력한 납입기간이 정수 && 1-3 여부를 학인하는 로직
		try {
			yearInt = Integer.parseInt(yearStr); 	// [중요] 입력값을 int 타입에 저장하지 못하는 경우 NumberFormatEeception 예외 발생 (=double,
												 	// string 입력될 경우)
			
			// 1,2,3 정수를 확인하는 로직
			if (yearInt >= 1 && yearInt <= 3) {
				saving[2] = yearStr;
				result = true;
			} else {
				JOptionPane.showMessageDialog(null, "납입기간을 잘못 입력하였습니다. (1~3의 정수)");	
			}
		} catch (NumberFormatException e) {
			// 정수(int)가 아닌 값을 입력했을경우 예외발생
			JOptionPane.showMessageDialog(null, "납입기간을 잘못 입력하였습니다. (1~3의 정수)");	
		}
		return result;
	}

	

	// 4.월 납입금을 입력 받는 메소드 //
	public boolean money(String moneyStr) {

		int moneyInt;			 // 입력값 확인용
		
		boolean result = false;
	
		// 입력값이 정수 그리고 월 납입금(1,000이상-1,000,000이하) 일치여부를 확인하는 로직
		try {
			moneyInt = Integer.parseInt(moneyStr);
			if (moneyInt >= 1000 && moneyInt <= 1000000) {
				saving[3] = moneyStr;
				result = true;
			} else {
				JOptionPane.showMessageDialog(null, "월 납입금을 잘못 입력하였습니다. (1,000 ~ 1,000,000)");	
			}
		} catch (NumberFormatException e) {
			// 정수(int)가 아닌 값을 입력했을경우 예외발생
			JOptionPane.showMessageDialog(null, "월 납입금을 잘못 입력하였습니다. (1,000 ~ 1,000,000)");	
		}
		return result;
	}

	
	// 5. 연 이율을 입력받는 메소드 //
	// interest 이자/흥미 + rate 비율 = 이율
	public boolean interestRate(String interestRateStr) {

		double interestRateDou; // 입력값 확인용

		boolean result = false;
		
		// 입력값이 실수 그리고 이율(1.00-3.00) 일치여부를 확인하는 로직
		try {
			interestRateDou = Double.parseDouble(interestRateStr); // 입력값을 double 타입에 저장하지 못하는 경우
																	// NumberFormatEeception 예외발생
																	// (=string 입력될 경우)
			if (interestRateDou >= 1.00 && interestRateDou <= 3.00) {
				saving[4] = interestRateStr;
				result = true;
			} else {
				JOptionPane.showMessageDialog(null, "연 이율을 잘못 입력하였습니다. (1.00-3.00)");	
			}
		} catch (NumberFormatException e) {
			// 실수(double)가 아닌 값을 입력했을경우 예외발생
			JOptionPane.showMessageDialog(null, "연 이율을 잘못 입력하였습니다. (1.00-3.00)");	
		}
		return result;
	}
}// class ScClientData END
