package model;
import controller.Controller;
import dao.DAO;
import dto.SavingInfoDTO;

// Model  - business service //
// [로그인 할 경우] DB savingInfo table 에서 로그인 ID과 일치하는 정보를 조회하여 배열에 저장 
// [로그아웃 할 경우] 적금 정보 panel의 textField에 저장된 값을 DB savingInfo table에 데이터를 저장
public class ChangePanel {

	private String[] savingInfo = new String[6];			// isprogram DB table의 값을 저장하기 위한 배열

/*
	savingInfo[0]		// 0. ID				primary key
	savingInfo[1];		// 1. 은행종류			bank
	savingInfo[2];		// 2. 적금종류 (단리/복리)	savingType
	savingInfo[3];		// 3. 납입기간 			year
	savingInfo[4];		// 4. 월 납입금			money
	savingInfo[5];		// 5. 연 이율				interestRate
*/	
	
	DAO dao = new DAO();
	
	SavingInfoDTO savingInfoDTO = new SavingInfoDTO();
	
	private Controller controller;
	
	
	public ChangePanel(){}
	
	public ChangePanel(Controller controller){
		this.controller = controller;
	}

	
	// 적금정보 조회 로직 //
	// login 시 호출되는 method
	// DB savingInfo table에서 데이터를 조회하여 해당 클래스의 배열에 저장
	public void savingInfoLogin(String pk, String table) {		// login table PK, 테이블명
		
		
		// DB savinginfo table 에서 반환받은 record를 sting으로 변환하여 배열의 요소에 저장

		// [query 전송] DB savinginfo table에서 id와 일치하는 적금정보를 조회하기위한 DAO mothode 호출 
		// (조회된 결과 값은 savingInfoDTO 객체에 저장됨)
	
		dao.savingInfoSelect(pk, table,savingInfoDTO);
		
		// [query 결과]
		savingInfo[0] = savingInfoDTO.getId();
		savingInfo[1] = savingInfoDTO.getBank();
		savingInfo[2] = savingInfoDTO.getSavingType();
		savingInfo[3] = savingInfoDTO.getYear();
		savingInfo[4] = savingInfoDTO.getMoney();
		savingInfo[5] = savingInfoDTO.getInterestRate();
		
		
		System.out.println("[DB work] savingInfo table SELECT and SAVE in java");
		
			
	}// savingInfoSelect() END

	
	
	// savingInfo 배열 값을 반환하는 method //
	// savingInfo 배열 == 로그인 시 DB savinginfo table에서 조회한 값
	public String getSavingInfo(String calumn) {				
		
		String value = "";
		
		switch(calumn) {
		
		case "id"			: value = savingInfo[0];		break;	
		case "bank"			: value = savingInfo[1];		break;
		case "savingtype" 	: value = savingInfo[2];		break;		
		case "savingyear" 	: value = savingInfo[3];		break;		
		case "money"     	: value = savingInfo[4];		break;		
		case "interestrate" : value = savingInfo[5];		break;
		
		}
		return value;
	}// getSavingInfo() END
	
	
	// savinginfo 배열에 값을 저장하는 method == setter //
	// == DB savinginfo table에 데이터를 보내기위한 준비
	public void setSavingInfo(String calumn, String value) {
		
		switch(calumn) {
		
		case "id"			: savingInfo[0] = value;	break;	
		case "bank"			: savingInfo[1] = value;	break;
		case "savingtype" 	: savingInfo[2]	= value;	break;		
		case "savingyear" 	: savingInfo[3] = value;	break;		
		case "money" 		: savingInfo[4] = value;	break;		
		case "interestrate" : savingInfo[5] = value;	break;
		}			
		
	}

	
	// 로그아웃 시 DB savingInfo table 에 데이터를 저장 // 
	public void savingInfoUpdate(String table)  {

		// DTO 셋팅 (ID + 각 속성값 5개) // 
		savingInfoDTO.setAll(savingInfo[0], savingInfo[1], savingInfo[2], savingInfo[3], savingInfo[4], savingInfo[5]);
		
		dao.savingInfoUpdate(table, savingInfoDTO);		// DAO 객체에 테이블명, DTO(그릇) 전달
		
	} // savingInfoLogout() END

} // ChangePanel END
