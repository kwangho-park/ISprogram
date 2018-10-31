package model;

/*
 * login Algorithm [펼치기]
 * 
 * 1. 개요
 *  - 다양한 collection framework의 경험을 위해 적금 만기 프로그램의 기능의 추가가 필요함
 *  (list, stack, queue, map, set)
 * 
 * 2. 기능
 *  - 로그인
 *    = [ok] 고객정보는 코딩을 통해 사전에 입력 (ISprogramDB login table)
 *    = [ok] 고객에게 입력받은 ID, PW를 ISprogramDB의 login table과 비교하여 필터링    
 *    = [ok] 일치 : TextField "" 저장 후 panel 변환, 불일치 : cosole을 통해 메세지 출력
 * 
 *  - 적금정보
 *   - 
 *  
 *  
 * 
 * 3. [DB] data structure : array
 *  - "admin", 1234
 *  - "administrator",1234
 *  - "박광호", 19910410
 *  
 *  = 초기 HashMap 으로 직접 코딩...난이도가 높아져서 array로 변경 (추후 재 적용예정...)
 *  
 * */

////

import javax.swing.JOptionPane;		// swing dialog (=대화창)

import controller.Controller;
import dao.DAO;
import dto.UserDTO;


// Model - business service//
// 고객에게 입력받은 Login 정보 (ID, PW)를 체크하기 위한 클래스 //
public class Login {
	
	public static String primaryKeyStr = "";					// login table의 PK공유 (레코드인식을 위해 필요성이 생김...)

	DAO dao = new DAO();			// LoginSelect 메소드 접근을 위한 객체 생성
	
	UserDTO userDto = new UserDTO();
	
	
	private Controller controller;
	
	
	public Login(){}

	public Login(Controller controller){
		this.controller = controller;
	}
	
	// 고객에게 ID를 입력받고 일치여부 비교 //	
	// 사용자가 입력한 id가 hash map에 존재 할 경우 True 반환
	public Boolean filter(String idStr,String pwStr) {

		Boolean result = false;		
		
		String loginTableId = null;
		String loginTablePw = null;
		
		
		dao.userSelect(idStr, userDto);			 	// login table에서 데이터 조회를 위한 PK와 조회된 결과값을 받기위한 그릇(=DTO)을 인자로 전달
		
		loginTableId = userDto.getId();			// loginSelect 의 결과값을 LoginDTO 객체에서 가져옴
		loginTablePw = userDto.getPw();	

		
		if(idStr.equals(loginTableId)){					// 사용자가 입력한 ID를 DB 테이블에서 조회한 ID와 비교
			if(pwStr.equals(loginTablePw)) {			// 사용자가 입력한 PW를 DB 테이블에서 조회한 PW와 비교

					JOptionPane.showMessageDialog(null, "입력하신 ID,PW가 일치합니다.");				
					Login.primaryKeyStr = idStr;				// login table의 primaryKey공유를 위해 저장
					result = true;
					
				}else {
					JOptionPane.showMessageDialog(null, "입력하신 PW가 일치하지 않습니다.");						
				}
			}else {
				JOptionPane.showMessageDialog(null, "입력하신 ID가 일치하지 않습니다.");	
			}

		return result;		// 사용자 입력 로그인정보 vs DB table 비교결과 반환
		
	} // loginFilter() END
	
} // Login END