package model;

import controller.Controller;
import dao.DAO;
import dto.UserDTO;


// 회원 탈퇴 // 
public class UserLeave {

	DAO dao = new DAO();					// userSelect 메소드 접근을 위한 객체 생성
	UserDTO userDto = new UserDTO();
	
	
	private Controller controller;			// defult init = null (생성자)
	
	UserLeave(){}
	
	public UserLeave(Controller controller){
		this.controller = controller;
	}
	
	
	public void allDelete(String pk) {
		dao.allDelete(pk);
	}
	
}// UserLeave END
