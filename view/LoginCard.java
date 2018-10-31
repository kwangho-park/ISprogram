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


// View 2 // 
// login Card Panel //
//���� �α��������� �Է¹޴� �г�
//(MainFrame�� contentPane�� ����)
public class LoginCard extends JPanel{
	
	// component ��ü ���� �� ���������� �Ҵ�
	JButton btnLogin = new JButton("�α���");
	JButton btnJoin = new JButton("ȸ������");

	JTextField tfId = new JTextField();
	JTextField tfPw = new JTextField();
	
	JLabel lbId = new JLabel("    ID (����) : ");
	JLabel lbPw = new JLabel("    PW (����) : ");
	
	
	
	public LoginCard(MainFrame mainFrame){

		// panel setting
		setLayout(new GridLayout(5,3,10,10));
		setBackground(Color.LIGHT_GRAY);
		
		// component add
		add(lbId);
		add(tfId);
		add(btnLogin);
		add(lbPw);
		add(tfPw);
		add(btnJoin);

		// ���� add (�ð����� ����)
		// [���� ����] ������ ���� ����� �͸�Ŭ������ ���������� �����Ϸ��������� layout�� ������ �߻�... ����?
		add(new JLabel(""));	add(new JLabel(""));	add(new JLabel(""));
		add(new JLabel(""));	add(new JLabel(""));	add(new JLabel(""));
		add(new JLabel(""));	add(new JLabel(""));	add(new JLabel(""));


		mainFrame.contentPane.add(this,"loginCard");		// ���� �����ؼ� �ʱ⿡ ǥ��� : ������ panel�� , "��Ī"


		// '�α���' button // 
		// btnLogin Action Listener setting
		btnLogin.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {							

				if(mainFrame.controller.loginFilter(tfId.getText(), tfPw.getText())) {	

					mainFrame.controller.savingInfoLogin();					// SavingInfoCard Panel���� ��� �� DB savinginfo table �غ�
					
					// panel ��ȯ //
					mainFrame.cardLayout.show(mainFrame.contentPane, "savingInfoCard");
					
					tfId.setText("");			tfPw.setText("");			// login panel�� ��ȯ�ϱ� �� TextField ����

										
				}
			}
		});	
		
		
		// 'ȸ������' // 
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "ȸ������ â���� ��ȯ�մϴ�.");	
				mainFrame.cardLayout.show(mainFrame.contentPane, "userCard");
			}
		});
		
		
		
	} //LoginCard() END
} // LoginCard END
