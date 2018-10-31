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
//고객의 로그인정보를 입력받는 패널
//(MainFrame의 contentPane에 부착)
public class LoginCard extends JPanel{
	
	// component 객체 생성 및 참조형변수 할당
	JButton btnLogin = new JButton("로그인");
	JButton btnJoin = new JButton("회원가입");

	JTextField tfId = new JTextField();
	JTextField tfPw = new JTextField();
	
	JLabel lbId = new JLabel("    ID (문자) : ");
	JLabel lbPw = new JLabel("    PW (숫자) : ");
	
	
	
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

		// 공백 add (시각적인 개선)
		// [추후 도전] 공백을 위해 사용한 익명클래스를 참조변수로 변경하려고했으나 layout에 문제가 발생... 뭐여?
		add(new JLabel(""));	add(new JLabel(""));	add(new JLabel(""));
		add(new JLabel(""));	add(new JLabel(""));	add(new JLabel(""));
		add(new JLabel(""));	add(new JLabel(""));	add(new JLabel(""));


		mainFrame.contentPane.add(this,"loginCard");		// 먼저 부착해서 초기에 표출됨 : 부착용 panel명 , "별칭"


		// '로그인' button // 
		// btnLogin Action Listener setting
		btnLogin.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {							

				if(mainFrame.controller.loginFilter(tfId.getText(), tfPw.getText())) {	

					mainFrame.controller.savingInfoLogin();					// SavingInfoCard Panel에서 사용 할 DB savinginfo table 준비
					
					// panel 전환 //
					mainFrame.cardLayout.show(mainFrame.contentPane, "savingInfoCard");
					
					tfId.setText("");			tfPw.setText("");			// login panel을 전환하기 전 TextField 비우기

										
				}
			}
		});	
		
		
		// '회원가입' // 
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "회원가입 창으로 전환합니다.");	
				mainFrame.cardLayout.show(mainFrame.contentPane, "userCard");
			}
		});
		
		
		
	} //LoginCard() END
} // LoginCard END
