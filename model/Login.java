package model;

/*
 * login Algorithm [��ġ��]
 * 
 * 1. ����
 *  - �پ��� collection framework�� ������ ���� ���� ���� ���α׷��� ����� �߰��� �ʿ���
 *  (list, stack, queue, map, set)
 * 
 * 2. ���
 *  - �α���
 *    = [ok] �������� �ڵ��� ���� ������ �Է� (ISprogramDB login table)
 *    = [ok] ������ �Է¹��� ID, PW�� ISprogramDB�� login table�� ���Ͽ� ���͸�    
 *    = [ok] ��ġ : TextField "" ���� �� panel ��ȯ, ����ġ : cosole�� ���� �޼��� ���
 * 
 *  - ��������
 *   - 
 *  
 *  
 * 
 * 3. [DB] data structure : array
 *  - "admin", 1234
 *  - "administrator",1234
 *  - "�ڱ�ȣ", 19910410
 *  
 *  = �ʱ� HashMap ���� ���� �ڵ�...���̵��� �������� array�� ���� (���� �� ���뿹��...)
 *  
 * */

////

import javax.swing.JOptionPane;		// swing dialog (=��ȭâ)

import controller.Controller;
import dao.DAO;
import dto.UserDTO;


// Model - business service//
// ������ �Է¹��� Login ���� (ID, PW)�� üũ�ϱ� ���� Ŭ���� //
public class Login {
	
	public static String primaryKeyStr = "";					// login table�� PK���� (���ڵ��ν��� ���� �ʿ伺�� ����...)

	DAO dao = new DAO();			// LoginSelect �޼ҵ� ������ ���� ��ü ����
	
	UserDTO userDto = new UserDTO();
	
	
	private Controller controller;
	
	
	public Login(){}

	public Login(Controller controller){
		this.controller = controller;
	}
	
	// ������ ID�� �Է¹ް� ��ġ���� �� //	
	// ����ڰ� �Է��� id�� hash map�� ���� �� ��� True ��ȯ
	public Boolean filter(String idStr,String pwStr) {

		Boolean result = false;		
		
		String loginTableId = null;
		String loginTablePw = null;
		
		
		dao.userSelect(idStr, userDto);			 	// login table���� ������ ��ȸ�� ���� PK�� ��ȸ�� ������� �ޱ����� �׸�(=DTO)�� ���ڷ� ����
		
		loginTableId = userDto.getId();			// loginSelect �� ������� LoginDTO ��ü���� ������
		loginTablePw = userDto.getPw();	

		
		if(idStr.equals(loginTableId)){					// ����ڰ� �Է��� ID�� DB ���̺��� ��ȸ�� ID�� ��
			if(pwStr.equals(loginTablePw)) {			// ����ڰ� �Է��� PW�� DB ���̺��� ��ȸ�� PW�� ��

					JOptionPane.showMessageDialog(null, "�Է��Ͻ� ID,PW�� ��ġ�մϴ�.");				
					Login.primaryKeyStr = idStr;				// login table�� primaryKey������ ���� ����
					result = true;
					
				}else {
					JOptionPane.showMessageDialog(null, "�Է��Ͻ� PW�� ��ġ���� �ʽ��ϴ�.");						
				}
			}else {
				JOptionPane.showMessageDialog(null, "�Է��Ͻ� ID�� ��ġ���� �ʽ��ϴ�.");	
			}

		return result;		// ����� �Է� �α������� vs DB table �񱳰�� ��ȯ
		
	} // loginFilter() END
	
} // Login END