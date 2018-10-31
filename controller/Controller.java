
/*
 * [data structure] IS Program [��ġ��]
 * 
 *  
 * 2. DB ����
 *  2.1 [app-java] data structure : array
 *  
 *    - ���� ���� (����/SC/����/��ü��/���)
 *    - ���� ���� ���� (�ܸ�/����)
 *    - ���� �Ⱓ (1/2/3) [����:��]
 *    - �� ���Ա� (1-100) [����:��]
 *    - ����(1.00-3.00) [�Ҽ��� 2�ڸ�]
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
// InstallmentSavings = ���� (Installment ����/�Һ� ,saving ����)
public class Controller {			
	
	
	// application���� VIEW, MODEL�� �������� �޾ƾ���
	// Model (����Ͻ� ����, DAO) Ŭ������ �з��ؼ� Application Ŭ������ ���� �� ��ü ����
	
	// �ϳ��� �ǹ���? : ��� ��ü�� application Ŭ�������� ���� �� ��� ��ü ������Ʈ ���ۿ� � ������ ��ġ������
	// DB ���̺��� �ڹٿ� �����ϴ� Ŭ������ �����ϴ� ��θ� ������ �ʿ��� ������ ������
	// ������ ��ģ�ٸ� ��� �����ؾ��ϴ��� �ľ��� �ʿ�

	
	
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
	
	
	// �����Ѿ��� Ȯ���ϱ� ���� ���͸� �� ��� //
	public String totalFilter(String tfBank, String tfSavingType, String tfYear, String tfMoney, String tfInterestRate) {
		
		// [����] ���ǹ��� �ܰ������� �˻��ؾ� �ش� ��ü �޼ҵ忡�ִ� ���̾� �α� ��µ�
		
		if(scSavingInfo.bank(tfBank)) {										// bank : �Է¹��� �����̸��� �������� �迭�� �����ϱ����� ���ڷ� ����
			if(scSavingInfo.savingType(tfSavingType)) {						// savingType : (����)
				if(scSavingInfo.year(tfYear)) {								// year : (����)
					if(scSavingInfo.money(tfMoney)) {						// money : (����)
						if(scSavingInfo.interestRate(tfInterestRate)) {		// interestRate : (����)

							interestCal.filter(scSavingInfo.saving); 		// ����ݾ� ��������� ���������� ����� �迭�� ����Ű�� ������ ������ ��������			
						}
					}
				}
			}
		}
		
	return interestCal.resultStr;			// ���� ���� �Ѿ��� '����� �Ѿ�TextField' ���
		
	} // totalFilter() END

	
	// LoginŬ������ ��ü �޼ҵ� Ȱ���Ͽ� login filtering	 //
	public Boolean loginFilter(String tfId, String tfPw) {
		
		return login.filter(tfId, tfPw);
	}
	
	
	public void savingInfoLogin() {
		changePanel.savingInfoLogin(Login.primaryKeyStr,"savinginfo");	
		
		// [UI] �α��ν� SavingInfoCard�� ��ü TextField�� �ʱ�ȭ �ϱ����� ����
		// ������
		
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
	
	// ID �ߺ�Ȯ�� // 
	public void idOverlap(String idStr) {
		
		// [DB] user table�� �ߺ����� �ִ��� Ȯ��
		userJoin.idOverlap(idStr);
	
	} // idOverlap()
	

	// ȸ�����Խ�û�� ���� ���͸� �� DB table���� // 
	// [����] user���̺��� ��ü ���ڵ带 ���ڷ� ���޹޾ƾ��� 
	public void userJoinFilter(UserDTO userDto) {
		
		
		
		// [����] ���ǹ��� �ܰ������� �˻��ؾ� �ش� ��ü �޼ҵ忡�ִ� ���̾� �α� ��µ�
		// [upgrade] �ϳ��� Ŭ������ ��ü �޼ҵ�� ���� �Է°��� DTO�� ��Ƽ����� 
		// -> �� �޼ҵ��� ������� ��ü ��������� ���� ����
		
		if(userJoin.idFilter( userDto.getId() )) {				
			
			if( !(userDto.getPw().equals("")) ) {						// pw : ����x
				
				if(userJoin.nameFilter( userDto.getName() )) {	
					
					if(userJoin.birthdayFilter( userDto.getBirthday() )) {
						
						if( !(userDto.getSex().equals("") )) {			// sex : ����x
							
							if( !(userDto.getJob().equals("")) ) {		// job : ���� x
								
								// DAO�� �����ϴ� model ���� // 
								userJoin.userSave(userDto);
								
								// loginCard panel���� ��ȯ //
								mainFrame.cardLayout.show(mainFrame.contentPane, "loginCard");
								
								JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�!");
								
							}else {
								JOptionPane.showMessageDialog(null, "������ �������ּ���.");
							}
						}else {
							JOptionPane.showMessageDialog(null, "������ �������ּ���.");
						}
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
			}
		}
	} // userJoinFilter()
	
	
	public void UserLeave() {
		userLeave.allDelete(Login.primaryKeyStr);	// �α��ν� login ��ü���� ����� static ���� 'primaryKeyStr' �������� 
	} // UserLeave() END
	
	
}// class InstallmentSavings END


