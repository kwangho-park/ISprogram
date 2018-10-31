package model;

import javax.swing.JOptionPane;

import controller.Controller;
import dao.DAO;
import dto.UserDTO;

public class UserJoin {
	
	DAO dao = new DAO();					// userSelect 메소드 접근을 위한 객체 생성
	UserDTO userDto = new UserDTO();
	
	
	private Controller controller;			// defult init = null (생성자)
	

	
	public UserJoin(){}

	public UserJoin(Controller controller){
		this.controller = controller;
	}
	
	// 회원가입 신청시 ID 중복확인 //
	public void idOverlap(String idStr) {
		
		Boolean result = false;
		String loginTableId = null;
		
		dao.userSelect(idStr, userDto);
	
		loginTableId = userDto.getId();
		

		// ID filter logic //
		// 조건 : DB table 중복 or 공백x
		if( !(idStr.equals("")) ) {					// [주의] "" != null : 공백입력 필터링
			if(idStr.equals(loginTableId)) {		// DB login table의 ID 비교
				result = true;
				JOptionPane.showMessageDialog(null, "중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요.");				
			}else {
				JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");	
			}			
		}else {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");	
		}

	} // idOverlap()
	
	
	
	// 1. 사용자가 입력한 id를 필터링하는 method //
	public Boolean idFilter(String idStr) {

		Boolean idResult = false;
		
		String loginTableId = null;

		dao.userSelect(idStr, userDto);
		
		loginTableId = userDto.getId();

		// '중복확인' 버튼과 동일한 동작 //
		// ID filter logic //
		// 조건 : DB table 중복 or 공백x
		if( !(idStr.equals("")) ) {					// [주의] "" != null : 공백입력 필터링
			if(idStr.equals(loginTableId)) {		// DB login table의 ID 비교

				JOptionPane.showMessageDialog(null, "중복된 아이디가 존재합니다. 다른 아이디를 입력해주세요.");				
			}else {
				idResult = true;
			}			
		}else {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");	
		}
	
		return idResult;
		
	} // idFilter() END
	
	
	// 2. 사용자가 입력한 name를 필터링하는 method //
	public Boolean nameFilter(String nameStr) {

		Boolean nameResult = false;
		
		int nameInt = 0;			// name의 데이터 타입이 string 인지 확인용 변수	
		double nameDouble = 0.0;	
		
		
		// name filter logic //
		// 조건 : 공백 or 숫자 x , 한글 or 영어 o
		if( !(nameStr.equals("")) ) {
			try {
				nameInt = Integer.parseInt(nameStr); 
				JOptionPane.showMessageDialog(null, "이름을 한글 또는 영문으로 입력해주세요.");
				
			}catch(NumberFormatException e) {
				nameResult = true;				// int가 아닐경우 실행 (=즉 문자 또는 실수;;)
			}	
		}else {
			JOptionPane.showMessageDialog(null, "이름를 입력해주세요.");	
		}
		
		// [upgrade] int와 double를 모두에 필터링하는 방법은? 두개의 try - catch 사용??
		
		return nameResult;
		
	} // nameFiler() END
	
	
	
	
	// 3. 사용자가 입력한 생년월일을 필터링하는 method //
	public Boolean birthdayFilter(String birthdayStr) {
		
		Boolean birthdayResult = false;
		
		int birthdayInt = 0;
		
		// 조건 : 공백 or 문자x , 숫자 and 8자리o
		if( !(birthdayStr.equals("")) ) {
			try {
				birthdayInt = Integer.parseInt(birthdayStr); 
				
				if( (10000000 < birthdayInt) && (birthdayInt < 100000000) ) {	// 8자리의 정수 필터링 ex) 19910410
					birthdayResult = true;
				}else {
					JOptionPane.showMessageDialog(null, "생년월일을 8자리의 정수로 입력해주세요. ex) 19910410");	
				}
				
			}catch(NumberFormatException e){		// int가 아닐경우 실행
				// controller 에서 필터링 결과를 확인하면서 한번에 출력
				JOptionPane.showMessageDialog(null, "생년월일을 8자리의 정수로 입력해주세요. ex) 19910410");
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "생년월일을 입력해주세요.");	
		}

		return birthdayResult;
		
	} // birthdayFilter() END
	
	
	// 3. 사용자가 입력한 회원가입 정보를 DB user table에 저장 // 
	public void userSave(UserDTO userDto) {
		dao.userInsert(userDto);
	} // userSave() END
	
} // UserJoin END
