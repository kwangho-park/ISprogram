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
//고객(client)의 적금정보(savingInfo)를 입력받는 패널
//(MainFrame의 contentPane에 부착)
public class SavingInfoCard extends JPanel{
	
	
	// component 객체 생성
	JButton btnSavingInfo 	= new JButton("적금정보 조회");
	JButton btnLogout 		= new JButton("로그아웃");
	JButton btnResult 		= new JButton("만기시 총액 확인");
	JButton btnLeave 		= new JButton("회원탈퇴");
	
	JLabel lbBank 			= new JLabel("1. 은행명");
	JLabel lbSavingType 	= new JLabel("2. 적금종류 (단리/복리)");
	JLabel lbYear 			= new JLabel("3. 납입기간 [단위:년]");
	JLabel lbMoney 			= new JLabel("4. 월납입금 [단위:원]");
	JLabel lbInterestRate	= new JLabel("5. 연이율 [소수 2자리]");
	JLabel lbResult 		= new JLabel("만기시 총액");		

	
	public JTextField tfBank = new JTextField();				// 1. 은행종류
	public JTextField tfSavingType = new JTextField();			// 2. 적금종류
	public JTextField tfYear = new JTextField();				// 3. 납입기간
	public JTextField tfMoney = new JTextField();				// 4. 월납입금
	public JTextField tfInterestRate = new JTextField();		// 5. 연이율
	public JTextField tfResult = new JTextField();				// 만기시 총액

			
	public SavingInfoCard(MainFrame mainFrame){
		
		// panel 설정
		setLayout(new GridLayout(8,2,10,10));			// 8*2 의 Grid
		setBackground(Color.YELLOW);
			
		
		// component 부착
		add(btnLeave);			add(btnLogout);			
		add(lbBank);			add(tfBank);
		add(lbSavingType);		add(tfSavingType);
		add(lbYear);			add(tfYear);
		add(lbMoney);			add(tfMoney);
		add(lbInterestRate);	add(tfInterestRate);
		add(lbResult);			add(tfResult);
		add(btnSavingInfo);		add(btnResult);
		
	    
		mainFrame.contentPane.add(this,"savingInfoCard");						// contentPane에 savingInfoCard Panel부착
				

		// '적금정보 조회' button //
		btnSavingInfo.addActionListener(new ActionListener () {	
			
			@Override
			public void actionPerformed(ActionEvent e) {			
				
				// DB의 savingInfo table에 정보를 호출하는 로직 // 
				tfBank.setText(mainFrame.controller.getSavingInfo("bank"));					// SavingInfo 객체의 methode
				tfSavingType.setText(mainFrame.controller.getSavingInfo("savingtype"));
				tfYear.setText(mainFrame.controller.getSavingInfo("savingyear"));
				tfMoney.setText(mainFrame.controller.getSavingInfo("money"));
				tfInterestRate.setText(mainFrame.controller.getSavingInfo("interestrate"));

			}
		});
		
		
		

		// '로그아웃' button //
		// btnInfo Action Listener setting
		btnLogout.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {

				// confirm dialog button : YES = 0, NO = 1, CENCEL= 2
				if(0==JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?","로그아웃",JOptionPane.OK_CANCEL_OPTION))
				{
					// 각 TextField에 저장된 값을 읽어와서 SavingInfo 객체에 배열에 저장

					mainFrame.controller.setSavingInfo("bank", tfBank.getText());
					mainFrame.controller.setSavingInfo("savingtype", tfSavingType.getText());
					mainFrame.controller.setSavingInfo("savingyear", tfYear.getText());		
					mainFrame.controller.setSavingInfo("money", tfMoney.getText());
					mainFrame.controller.setSavingInfo("interestrate", tfInterestRate.getText());	
					
			
					mainFrame.controller.savingInfoUpdate("savinginfo");

					// Panel 전환
					mainFrame.cardLayout.show(mainFrame.contentPane, "loginCard");				
				}
			}
		});



		// '만기시 총액 확인' button
		// btnResult Action Listener setting
		btnResult.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				String result;
				
				// 적금정보 일치여부확인 및 총액 계산 // 
				result = mainFrame.controller.totalFilter(tfBank.getText(), tfSavingType.getText(),
						tfYear.getText(), tfMoney.getText(), tfInterestRate.getText());

				
				tfResult.setText(result);
	

			} // actionPerformed() END
		});	// addActionListener() END
		
		
		
		// '회원탈퇴' //
		// btnResult Action Listener setting
		btnLeave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				
				// 다이얼로그로 탈퇴여부를 확인 (탈퇴하시겠습니까?)
				// confirm dialog button : YES = 0, NO = 1, CENCEL= 2
				if(0==JOptionPane.showConfirmDialog(null, "탈퇴하시겠습니까?","회원탈퇴",JOptionPane.OK_CANCEL_OPTION))
				{
					// DB에 해당 ID의 login + savingInfo table의 record를 DELETE
					mainFrame.controller.UserLeave();
					
					// Panel 전환
					mainFrame.cardLayout.show(mainFrame.contentPane, "loginCard");					
				}

			} // actionPerformed() END
		});	// addActionListener() END
		
		
	} // SavingInfoCard() END ()
	
} // SavingInfoCard END