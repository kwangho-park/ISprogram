package model;
import javax.swing.JOptionPane;

import controller.Controller;


// Model  - business service //
//�������͸� �Է¹޴� Ŭ����//
// ScClientData : scanner �Է� + client �� + data �ڷ�
public class ScSavingInfo {
	

	public String[] saving = new String[5];		// [DB] ���� ��������

/*
	saving[0];		// 1. ��������				bank
	saving[1];		// 2. �������� (�ܸ�/����)		savingType
	saving[2];		// 3. ���ԱⰣ 				year
	saving[3];		// 4. �� ���Ա�				money
	saving[4];		// 5. �� ����					interestRate
*/

	private Controller controller;
	
	
	public ScSavingInfo() {}

	public ScSavingInfo(Controller controller){
		this.controller = controller;
	}
	
	// 1.���������� �Է� �޴� �޼ҵ� //
	public boolean bank(String bank) {
		
		boolean result = false;
		
		// ���� �Է��� ������� �����ϴ� ����
		if (bank.equals("����") || bank.equals("sc") || bank.equals("����") || bank.equals("��ü��")
				|| bank.equals("���")) {
			saving[0] = bank;
			result = true;
		} else {
			JOptionPane.showMessageDialog(null, "������� �߸� �Է��Ͽ����ϴ�. (����/sc/����/��ü��/���)");	
		}
		return result;
	}
	

	// 2.���� ������ �Է¹޴� �޼ҵ� //
	public boolean savingType(String savingType) {

		boolean result = false;
		
		// �Է��� �������� �����ϴ� ����
		if (savingType.equals("�ܸ�") || savingType.equals("����")) {
			saving[1] = savingType;
			result = true;
		} else {
			JOptionPane.showMessageDialog(null, "���������� �߸� �Է��Ͽ����ϴ�. (�ܸ�/����)");	
		}
		return result;
	}

	
	// 3.���ԱⰣ�� �Է� �޴� �޼ҵ� //
	public boolean year(String yearStr) {
		
		int yearInt; 		// �Է°� Ȯ�ο�
		boolean result = false;
		
		// �Է��� ���ԱⰣ�� ���� && 1-3 ���θ� �����ϴ� ����
		try {
			yearInt = Integer.parseInt(yearStr); 	// [�߿�] �Է°��� int Ÿ�Կ� �������� ���ϴ� ��� NumberFormatEeception ���� �߻� (=double,
												 	// string �Էµ� ���)
			
			// 1,2,3 ������ Ȯ���ϴ� ����
			if (yearInt >= 1 && yearInt <= 3) {
				saving[2] = yearStr;
				result = true;
			} else {
				JOptionPane.showMessageDialog(null, "���ԱⰣ�� �߸� �Է��Ͽ����ϴ�. (1~3�� ����)");	
			}
		} catch (NumberFormatException e) {
			// ����(int)�� �ƴ� ���� �Է�������� ���ܹ߻�
			JOptionPane.showMessageDialog(null, "���ԱⰣ�� �߸� �Է��Ͽ����ϴ�. (1~3�� ����)");	
		}
		return result;
	}

	

	// 4.�� ���Ա��� �Է� �޴� �޼ҵ� //
	public boolean money(String moneyStr) {

		int moneyInt;			 // �Է°� Ȯ�ο�
		
		boolean result = false;
	
		// �Է°��� ���� �׸��� �� ���Ա�(1,000�̻�-1,000,000����) ��ġ���θ� Ȯ���ϴ� ����
		try {
			moneyInt = Integer.parseInt(moneyStr);
			if (moneyInt >= 1000 && moneyInt <= 1000000) {
				saving[3] = moneyStr;
				result = true;
			} else {
				JOptionPane.showMessageDialog(null, "�� ���Ա��� �߸� �Է��Ͽ����ϴ�. (1,000 ~ 1,000,000)");	
			}
		} catch (NumberFormatException e) {
			// ����(int)�� �ƴ� ���� �Է�������� ���ܹ߻�
			JOptionPane.showMessageDialog(null, "�� ���Ա��� �߸� �Է��Ͽ����ϴ�. (1,000 ~ 1,000,000)");	
		}
		return result;
	}

	
	// 5. �� ������ �Է¹޴� �޼ҵ� //
	// interest ����/��� + rate ���� = ����
	public boolean interestRate(String interestRateStr) {

		double interestRateDou; // �Է°� Ȯ�ο�

		boolean result = false;
		
		// �Է°��� �Ǽ� �׸��� ����(1.00-3.00) ��ġ���θ� Ȯ���ϴ� ����
		try {
			interestRateDou = Double.parseDouble(interestRateStr); // �Է°��� double Ÿ�Կ� �������� ���ϴ� ���
																	// NumberFormatEeception ���ܹ߻�
																	// (=string �Էµ� ���)
			if (interestRateDou >= 1.00 && interestRateDou <= 3.00) {
				saving[4] = interestRateStr;
				result = true;
			} else {
				JOptionPane.showMessageDialog(null, "�� ������ �߸� �Է��Ͽ����ϴ�. (1.00-3.00)");	
			}
		} catch (NumberFormatException e) {
			// �Ǽ�(double)�� �ƴ� ���� �Է�������� ���ܹ߻�
			JOptionPane.showMessageDialog(null, "�� ������ �߸� �Է��Ͽ����ϴ�. (1.00-3.00)");	
		}
		return result;
	}
}// class ScClientData END
