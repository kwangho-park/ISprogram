package model;

import controller.Controller;
import dao.DAO;
import dto.UserDTO;


// ȸ�� Ż�� // 
public class UserLeave {

	DAO dao = new DAO();					// userSelect �޼ҵ� ������ ���� ��ü ����
	UserDTO userDto = new UserDTO();
	
	
	private Controller controller;			// defult init = null (������)
	
	UserLeave(){}
	
	public UserLeave(Controller controller){
		this.controller = controller;
	}
	
	
	public void allDelete(String pk) {
		dao.allDelete(pk);
	}
	
}// UserLeave END
