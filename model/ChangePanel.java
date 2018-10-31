package model;
import controller.Controller;
import dao.DAO;
import dto.SavingInfoDTO;

// Model  - business service //
// [�α��� �� ���] DB savingInfo table ���� �α��� ID�� ��ġ�ϴ� ������ ��ȸ�Ͽ� �迭�� ���� 
// [�α׾ƿ� �� ���] ���� ���� panel�� textField�� ����� ���� DB savingInfo table�� �����͸� ����
public class ChangePanel {

	private String[] savingInfo = new String[6];			// isprogram DB table�� ���� �����ϱ� ���� �迭

/*
	savingInfo[0]		// 0. ID				primary key
	savingInfo[1];		// 1. ��������			bank
	savingInfo[2];		// 2. �������� (�ܸ�/����)	savingType
	savingInfo[3];		// 3. ���ԱⰣ 			year
	savingInfo[4];		// 4. �� ���Ա�			money
	savingInfo[5];		// 5. �� ����				interestRate
*/	
	
	DAO dao = new DAO();
	
	SavingInfoDTO savingInfoDTO = new SavingInfoDTO();
	
	private Controller controller;
	
	
	public ChangePanel(){}
	
	public ChangePanel(Controller controller){
		this.controller = controller;
	}

	
	// �������� ��ȸ ���� //
	// login �� ȣ��Ǵ� method
	// DB savingInfo table���� �����͸� ��ȸ�Ͽ� �ش� Ŭ������ �迭�� ����
	public void savingInfoLogin(String pk, String table) {		// login table PK, ���̺��
		
		
		// DB savinginfo table ���� ��ȯ���� record�� sting���� ��ȯ�Ͽ� �迭�� ��ҿ� ����

		// [query ����] DB savinginfo table���� id�� ��ġ�ϴ� ���������� ��ȸ�ϱ����� DAO mothode ȣ�� 
		// (��ȸ�� ��� ���� savingInfoDTO ��ü�� �����)
	
		dao.savingInfoSelect(pk, table,savingInfoDTO);
		
		// [query ���]
		savingInfo[0] = savingInfoDTO.getId();
		savingInfo[1] = savingInfoDTO.getBank();
		savingInfo[2] = savingInfoDTO.getSavingType();
		savingInfo[3] = savingInfoDTO.getYear();
		savingInfo[4] = savingInfoDTO.getMoney();
		savingInfo[5] = savingInfoDTO.getInterestRate();
		
		
		System.out.println("[DB work] savingInfo table SELECT and SAVE in java");
		
			
	}// savingInfoSelect() END

	
	
	// savingInfo �迭 ���� ��ȯ�ϴ� method //
	// savingInfo �迭 == �α��� �� DB savinginfo table���� ��ȸ�� ��
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
	
	
	// savinginfo �迭�� ���� �����ϴ� method == setter //
	// == DB savinginfo table�� �����͸� ���������� �غ�
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

	
	// �α׾ƿ� �� DB savingInfo table �� �����͸� ���� // 
	public void savingInfoUpdate(String table)  {

		// DTO ���� (ID + �� �Ӽ��� 5��) // 
		savingInfoDTO.setAll(savingInfo[0], savingInfo[1], savingInfo[2], savingInfo[3], savingInfo[4], savingInfo[5]);
		
		dao.savingInfoUpdate(table, savingInfoDTO);		// DAO ��ü�� ���̺��, DTO(�׸�) ����
		
	} // savingInfoLogout() END

} // ChangePanel END
