package view;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// View 3 //
// SavingInfopanel //
//��(client)�� ��������(savingInfo)�� �Է¹޴� �г�
//(MainFrame�� contentPane�� ����)
public class SavingInfoCard extends JPanel{
	
	
	// component ��ü ����
	JButton btnSavingInfo 	= new JButton("�������� ��ȸ");
	JButton btnLogout 		= new JButton("�α׾ƿ�");
	JButton btnResult 		= new JButton("����� �Ѿ� Ȯ��");
	JButton btnLeave 		= new JButton("ȸ��Ż��");
	
	JLabel lbBank 			= new JLabel("1. �����");
	JLabel lbSavingType 	= new JLabel("2. �������� (�ܸ�/����)");
	JLabel lbYear 			= new JLabel("3. ���ԱⰣ [����:��]");
	JLabel lbMoney 			= new JLabel("4. �����Ա� [����:��]");
	JLabel lbInterestRate	= new JLabel("5. ������ [�Ҽ� 2�ڸ�]");
	JLabel lbResult 		= new JLabel("����� �Ѿ�");		

	
	public JTextField tfBank = new JTextField();				// 1. ��������
	public JTextField tfSavingType = new JTextField();			// 2. ��������
	public JTextField tfYear = new JTextField();				// 3. ���ԱⰣ
	public JTextField tfMoney = new JTextField();				// 4. �����Ա�
	public JTextField tfInterestRate = new JTextField();		// 5. ������
	public JTextField tfResult = new JTextField();				// ����� �Ѿ�

			
	public SavingInfoCard(MainFrame mainFrame){
		
		// panel ����
		setLayout(new GridLayout(8,2,10,10));			// 8*2 �� Grid
		setBackground(Color.YELLOW);
			
		
		// component ����
		add(btnLeave);			add(btnLogout);			
		add(lbBank);			add(tfBank);
		add(lbSavingType);		add(tfSavingType);
		add(lbYear);			add(tfYear);
		add(lbMoney);			add(tfMoney);
		add(lbInterestRate);	add(tfInterestRate);
		add(lbResult);			add(tfResult);
		add(btnSavingInfo);		add(btnResult);
		
	    
		mainFrame.contentPane.add(this,"savingInfoCard");						// contentPane�� savingInfoCard Panel����
				

		// '�������� ��ȸ' button //
		btnSavingInfo.addActionListener(new ActionListener () {	
			
			@Override
			public void actionPerformed(ActionEvent e) {			
				
				// DB�� savingInfo table�� ������ ȣ���ϴ� ���� // 
				tfBank.setText(mainFrame.controller.getSavingInfo("bank"));					// SavingInfo ��ü�� methode
				tfSavingType.setText(mainFrame.controller.getSavingInfo("savingtype"));
				tfYear.setText(mainFrame.controller.getSavingInfo("savingyear"));
				tfMoney.setText(mainFrame.controller.getSavingInfo("money"));
				tfInterestRate.setText(mainFrame.controller.getSavingInfo("interestrate"));

			}
		});
		
		
		

		// '�α׾ƿ�' button //
		// btnInfo Action Listener setting
		btnLogout.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {

				// confirm dialog button : YES = 0, NO = 1, CENCEL= 2
				if(0==JOptionPane.showConfirmDialog(null, "�α׾ƿ� �Ͻðڽ��ϱ�?","�α׾ƿ�",JOptionPane.OK_CANCEL_OPTION))
				{
					// �� TextField�� ����� ���� �о�ͼ� SavingInfo ��ü�� �迭�� ����

					mainFrame.controller.setSavingInfo("bank", tfBank.getText());
					mainFrame.controller.setSavingInfo("savingtype", tfSavingType.getText());
					mainFrame.controller.setSavingInfo("savingyear", tfYear.getText());		
					mainFrame.controller.setSavingInfo("money", tfMoney.getText());
					mainFrame.controller.setSavingInfo("interestrate", tfInterestRate.getText());	
					
			
					mainFrame.controller.savingInfoUpdate("savinginfo");

					// Panel ��ȯ
					mainFrame.cardLayout.show(mainFrame.contentPane, "loginCard");				
				}
			}
		});



		// '����� �Ѿ� Ȯ��' button
		// btnResult Action Listener setting
		btnResult.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				String result;
				
				// �������� ��ġ����Ȯ�� �� �Ѿ� ��� // 
				result = mainFrame.controller.totalFilter(tfBank.getText(), tfSavingType.getText(),
						tfYear.getText(), tfMoney.getText(), tfInterestRate.getText());

				
				tfResult.setText(result);
	

			} // actionPerformed() END
		});	// addActionListener() END
		
		
		
		// 'ȸ��Ż��' //
		// btnResult Action Listener setting
		btnLeave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				
				// ���̾�α׷� Ż�𿩺θ� Ȯ�� (Ż���Ͻðڽ��ϱ�?)
				// confirm dialog button : YES = 0, NO = 1, CENCEL= 2
				if(0==JOptionPane.showConfirmDialog(null, "Ż���Ͻðڽ��ϱ�?","ȸ��Ż��",JOptionPane.OK_CANCEL_OPTION))
				{
					// DB�� �ش� ID�� login + savingInfo table�� record�� DELETE
					mainFrame.controller.UserLeave();
					
					// Panel ��ȯ
					mainFrame.cardLayout.show(mainFrame.contentPane, "loginCard");					
				}

			} // actionPerformed() END
		});	// addActionListener() END
		
		
	} // SavingInfoCard() END ()
	
} // SavingInfoCard END