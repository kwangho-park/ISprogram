
/*
 * [data structure] IS Program [펼치기]
 * 
 *  
 * 2. DB 참고
 *  2.1 [app-java] data structure : array
 *  
 *    - 은행 종류 (신한/SC/농협/우체국/기업)
 *    - 정기 적금 종류 (단리/복리)
 *    - 납입 기간 (1/2/3) [단위:년]
 *    - 월 납입금 (1-100) [단위:원]
 *    - 이율(1.00-3.00) [소수점 2자리]
 *    
 *  2.2 [DB] login/clientdata table
 *  
 */


package controller;

import javax.swing.JOptionPane;

import dao.DAO;
import dto.UserDTO;
import dto.SavingInfoDTO;
import model.ChangePanel;
import model.InterestCal;
import model.Login;
import model.ScSavingInfo;
import model.UserJoin;
import model.UserLeave;

import view.MainFrame;


// Controller //
// InstallmentSavings = 적금 (Installment 분할/할부 ,saving 저축)
public class Controller {			
	
	
	// application에서 VIEW, MODEL의 참조값을 받아야함
	// Model (비즈니스 서비스, DAO) 클래스를 분류해서 Application 클래스에 선언 및 객체 생성
	
	// 하나의 의문점? : 모든 객체를 application 클래스에서 생성 할 경우 전체 프로젝트 동작에 어떤 영향을 미치는지와
	// DB 테이블을 자바에 저장하는 클래스에 접근하는 경로만 변경이 필요할 것으로 추정됨
	// 영향을 미친다면 어떻게 변경해야하는지 파악이 필요

	
	
	// DAO, DTO //
	DAO dao = new DAO(this);

	
	UserDTO userDTO 			= new UserDTO(this);
	SavingInfoDTO savingInfoDTO = new SavingInfoDTO(this);
	
	
	// Model //
	Login login 				= new Login(this);
	ChangePanel changePanel	 	= new ChangePanel(this);
	InterestCal interestCal 	= new InterestCal(this);
	ScSavingInfo scSavingInfo 	= new ScSavingInfo(this);
	UserJoin userJoin 			= new UserJoin(this);
	UserLeave userLeave			= new UserLeave(this);

	// VIEW // 
	MainFrame mainFrame = new MainFrame(this);

	
	public Controller(){}
	

	public String getSavingInfo(String calumn) {
		
		return changePanel.getSavingInfo(calumn);
	}
	
	
	// 만기총액을 확인하기 위한 필터링 및 계산 //
	public String totalFilter(String tfBank, String tfSavingType, String tfYear, String tfMoney, String tfInterestRate) {
		
		// [참고] 조건문을 단계적으로 검사해야 해당 객체 메소드에있는 다이얼 로그 출력됨
		
		if(scSavingInfo.bank(tfBank)) {										// bank : 입력받은 은행이름을 적금정보 배열에 저장하기위해 인자로 전달
			if(scSavingInfo.savingType(tfSavingType)) {						// savingType : (동일)
				if(scSavingInfo.year(tfYear)) {								// year : (동일)
					if(scSavingInfo.money(tfMoney)) {						// money : (동일)
						if(scSavingInfo.interestRate(tfInterestRate)) {		// interestRate : (동일)

							interestCal.filter(scSavingInfo.saving); 		// 만기금액 계산을위해 적금정보가 저장된 배열을 가리키는 참조형 변수를 인자전달			
						}
					}
				}
			}
		}
		
	return interestCal.resultStr;			// 계산된 만기 총액을 '만기시 총액TextField' 출력
		
	} // totalFilter() END

	
	// Login클래스의 객체 메소드 활용하여 login filtering	 //
	public Boolean loginFilter(String tfId, String tfPw) {
		
		return login.filter(tfId, tfPw);
	}
	
	
	public void savingInfoLogin() {
		changePanel.savingInfoLogin(Login.primaryKeyStr,"savinginfo");	
		
		// [UI] 로그인시 SavingInfoCard의 전체 TextField를 초기화 하기위한 로직
		// 덮어씌우기
		
		mainFrame.savingInfoCard.tfBank.setText("");
		mainFrame.savingInfoCard.tfSavingType.setText("");
		mainFrame.savingInfoCard.tfYear.setText("");
		mainFrame.savingInfoCard.tfMoney.setText("");
		mainFrame.savingInfoCard.tfInterestRate.setText("");
		mainFrame.savingInfoCard.tfResult.setText("");
	}


	public void setSavingInfo(String calumn, String value) {
		
		changePanel.setSavingInfo(calumn, value);
	}
	
	
	public void savingInfoUpdate(String table)  {
		
		changePanel.savingInfoUpdate(table);
	}
	
	// ID 중복확인 // 
	public void idOverlap(String idStr) {
		
		// [DB] user table에 중복값이 있는지 확인
		userJoin.idOverlap(idStr);
	
	} // idOverlap()
	

	// 회원가입신청을 위한 필터링 및 DB table저장 // 
	// [변경] user테이블의 전체 레코드를 인자로 전달받아야함 
	public void userJoinFilter(UserDTO userDto) {
		
		
		
		// [참고] 조건문을 단계적으로 검사해야 해당 객체 메소드에있는 다이얼 로그 출력됨
		// [upgrade] 하나의 클래스의 객체 메소드로 만들어서 입력값을 DTO에 담아서전달 
		// -> 각 메소드의 결과값은 객체 멤버변수로 저장 관리
		
		if(userJoin.idFilter( userDto.getId() )) {				
			
			if( !(userDto.getPw().equals("")) ) {						// pw : 공백x
				
				if(userJoin.nameFilter( userDto.getName() )) {	
					
					if(userJoin.birthdayFilter( userDto.getBirthday() )) {
						
						if( !(userDto.getSex().equals("") )) {			// sex : 공백x
							
							if( !(userDto.getJob().equals("")) ) {		// job : 공백 x
								
								// DAO와 연결하는 model 생성 // 
								userJoin.userSave(userDto);
								
								// loginCard panel으로 전환 //
								mainFrame.cardLayout.show(mainFrame.contentPane, "loginCard");
								
								JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!");
								
							}else {
								JOptionPane.showMessageDialog(null, "직업을 선택해주세요.");
							}
						}else {
							JOptionPane.showMessageDialog(null, "성별을 선택해주세요.");
						}
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
			}
		}
	} // userJoinFilter()
	
	
	public void UserLeave() {
		userLeave.allDelete(Login.primaryKeyStr);	// 로그인시 login 객체에서 저장된 static 변수 'primaryKeyStr' 인자전달 
	} // UserLeave() END
	
	
}// class InstallmentSavings END


