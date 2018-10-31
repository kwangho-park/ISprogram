package model;

import javax.swing.JOptionPane;

import controller.Controller;
import dao.DAO;
import dto.UserDTO;

public class UserJoin {
	
	DAO dao = new DAO();					// userSelect �޼ҵ� ������ ���� ��ü ����
	UserDTO userDto = new UserDTO();
	
	
	private Controller controller;			// defult init = null (������)
	

	
	public UserJoin(){}

	public UserJoin(Controller controller){
		this.controller = controller;
	}
	
	// ȸ������ ��û�� ID �ߺ�Ȯ�� //
	public void idOverlap(String idStr) {
		
		Boolean result = false;
		String loginTableId = null;
		
		dao.userSelect(idStr, userDto);
	
		loginTableId = userDto.getId();
		

		// ID filter logic //
		// ���� : DB table �ߺ� or ����x
		if( !(idStr.equals("")) ) {					// [����] "" != null : �����Է� ���͸�
			if(idStr.equals(loginTableId)) {		// DB login table�� ID ��
				result = true;
				JOptionPane.showMessageDialog(null, "�ߺ��� ���̵� �����մϴ�. �ٸ� ���̵� �Է����ּ���.");				
			}else {
				JOptionPane.showMessageDialog(null, "��� ������ ���̵��Դϴ�.");	
			}			
		}else {
			JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.");	
		}

	} // idOverlap()
	
	
	
	// 1. ����ڰ� �Է��� id�� ���͸��ϴ� method //
	public Boolean idFilter(String idStr) {

		Boolean idResult = false;
		
		String loginTableId = null;

		dao.userSelect(idStr, userDto);
		
		loginTableId = userDto.getId();

		// '�ߺ�Ȯ��' ��ư�� ������ ���� //
		// ID filter logic //
		// ���� : DB table �ߺ� or ����x
		if( !(idStr.equals("")) ) {					// [����] "" != null : �����Է� ���͸�
			if(idStr.equals(loginTableId)) {		// DB login table�� ID ��

				JOptionPane.showMessageDialog(null, "�ߺ��� ���̵� �����մϴ�. �ٸ� ���̵� �Է����ּ���.");				
			}else {
				idResult = true;
			}			
		}else {
			JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.");	
		}
	
		return idResult;
		
	} // idFilter() END
	
	
	// 2. ����ڰ� �Է��� name�� ���͸��ϴ� method //
	public Boolean nameFilter(String nameStr) {

		Boolean nameResult = false;
		
		int nameInt = 0;			// name�� ������ Ÿ���� string ���� Ȯ�ο� ����	
		double nameDouble = 0.0;	
		
		
		// name filter logic //
		// ���� : ���� or ���� x , �ѱ� or ���� o
		if( !(nameStr.equals("")) ) {
			try {
				nameInt = Integer.parseInt(nameStr); 
				JOptionPane.showMessageDialog(null, "�̸��� �ѱ� �Ǵ� �������� �Է����ּ���.");
				
			}catch(NumberFormatException e) {
				nameResult = true;				// int�� �ƴҰ�� ���� (=�� ���� �Ǵ� �Ǽ�;;)
			}	
		}else {
			JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.");	
		}
		
		// [upgrade] int�� double�� ��ο� ���͸��ϴ� �����? �ΰ��� try - catch ���??
		
		return nameResult;
		
	} // nameFiler() END
	
	
	
	
	// 3. ����ڰ� �Է��� ��������� ���͸��ϴ� method //
	public Boolean birthdayFilter(String birthdayStr) {
		
		Boolean birthdayResult = false;
		
		int birthdayInt = 0;
		
		// ���� : ���� or ����x , ���� and 8�ڸ�o
		if( !(birthdayStr.equals("")) ) {
			try {
				birthdayInt = Integer.parseInt(birthdayStr); 
				
				if( (10000000 < birthdayInt) && (birthdayInt < 100000000) ) {	// 8�ڸ��� ���� ���͸� ex) 19910410
					birthdayResult = true;
				}else {
					JOptionPane.showMessageDialog(null, "��������� 8�ڸ��� ������ �Է����ּ���. ex) 19910410");	
				}
				
			}catch(NumberFormatException e){		// int�� �ƴҰ�� ����
				// controller ���� ���͸� ����� Ȯ���ϸ鼭 �ѹ��� ���
				JOptionPane.showMessageDialog(null, "��������� 8�ڸ��� ������ �Է����ּ���. ex) 19910410");
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "��������� �Է����ּ���.");	
		}

		return birthdayResult;
		
	} // birthdayFilter() END
	
	
	// 3. ����ڰ� �Է��� ȸ������ ������ DB user table�� ���� // 
	public void userSave(UserDTO userDto) {
		dao.userInsert(userDto);
	} // userSave() END
	
} // UserJoin END
