package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dto.UserDTO;

public class UserCard extends JPanel{
	
	UserDTO userDto = new UserDTO();
	
	// component 준비//
	JLabel lbId 		= new JLabel(" 1. ID : ");
	JLabel lbPw	 		= new JLabel(" 2. PW : ");
	JLabel lbName		= new JLabel(" 3. 이름 : ");
	JLabel lbBirthday 	= new JLabel(" 4. 생년월일 : ");
	JLabel lbSex 		= new JLabel(" 5. 성별 : ");
	JLabel lbJob		= new JLabel(" 6. 직업 : ");
	JLabel lbAddress 	= new JLabel(" 7. 주소 [선택] : ");
	
	JButton btnIdCheck	= new JButton("중복");
	JButton btnMember 	= new JButton("회원가입신청");
	JButton btnCancel	= new JButton("취소");
	
	public JTextField tfId 		= new JTextField();	
	public JTextField tfPw			= new JTextField();
	public JTextField tfName		= new JTextField();
	public JTextField tfBirthday 	= new JTextField();
	public JTextField tfAddress	= new JTextField();
	
	JRadioButton rbSexMan 	= new JRadioButton("남성");
	JRadioButton rbSexWoman	= new JRadioButton("여성");

	// combo box 준비
	JComboBox<String> cbJob = new JComboBox<String>();		// 제네릭 타입이 어떤 역활을 하는지 복습이 필요한 부분!!
	
	// buttonGroup 준비 (radioButton)
	ButtonGroup  rbSexGroup = new ButtonGroup();
	
	// panel 준비//
	JPanel idPanel 	= new JPanel();
	JPanel sexPanel = new JPanel();
	

	UserCard(){}
	
	UserCard(MainFrame mainFrame) {
		
		// UserCard setting // 
		setLayout(new GridLayout(8,2,10,10));			// 8*2 의 Grid
		setBackground(Color.CYAN);
		
		
		// idPanel setting // 
		idPanel.setLayout(new GridLayout(1,2));
		idPanel.add(tfId);
		idPanel.add(btnIdCheck);
		
		// sexPanel setting // 
		// 남성 or 여성 둘 중 하나만 선택가능하도록 Grouping
		rbSexGroup.add(rbSexMan);
		rbSexGroup.add(rbSexWoman);
		
		sexPanel.setLayout(new GridLayout(1,2));
		sexPanel.add(rbSexMan);
		sexPanel.add(rbSexWoman);
	
		
		// job comboBox add //
		cbJob.addItem("사무직");		cbJob.addItem("생산직");		cbJob.addItem("서비스직");
		cbJob.addItem("연구/개발직");	cbJob.addItem("기획직");		cbJob.addItem("영업직");
		cbJob.addItem("CEO");		cbJob.addItem("교육직");		cbJob.addItem("학생");
		cbJob.addItem("무직");
		
		
		
		// UserCard add // 
		add(lbId);			add(idPanel);
		add(lbPw);			add(tfPw);
		add(lbName);		add(tfName);
		add(lbBirthday);	add(tfBirthday);
		add(lbSex);			add(sexPanel);
		add(lbJob);			add(cbJob);
		add(lbAddress);		add(tfAddress);
		add(btnMember);		add(btnCancel);
		
		
		// contentPane에 userCard Panel add (=부착) // 
		mainFrame.contentPane.add(this,"userCard");				

		
		// 'ID 중복조회' //
		// btnCancel actionListener 
		btnIdCheck.addActionListener(new ActionListener () {	
			@Override
			public void actionPerformed(ActionEvent e) {			
				
				// userCard의 ID textField 입력된 값을 읽음
				mainFrame.controller.idOverlap(mainFrame.userCard.tfId.getText());
				
			}
		});
		
		
		// '회원가입신청' //
		// btnMember actionListener
		btnMember.addActionListener(new ActionListener () {	
			@Override
			public void actionPerformed(ActionEvent e) {			
				
				
				String id = tfId.getText();
				String pw = tfPw.getText();
				String name = tfName.getText();
				String birthday = tfBirthday.getText();
				
				String job = cbJob.getSelectedItem().toString();
				String address = tfAddress.getText();
				
				String sex = "";
				
				
				// sex radio button filter
				// [upgrade 필요 : controller 또는 Model으로 이동]
				if( rbSexMan.isSelected() ) {		// 남자를 선택할 경우 true
					sex = "남자";
				}else if(rbSexWoman.isSelected()){	// 여자를 선택할 경우 true
					sex = "여자";
				}
				
				userDto.setAll(id, pw, name, birthday, sex, job, address);

				mainFrame.controller.userJoinFilter(userDto);

			}
		});
		
		
		
		// '취소' //
		// btnCancel actionListener 
		btnCancel.addActionListener(new ActionListener () {	
			@Override
			public void actionPerformed(ActionEvent e) {		
				
				JOptionPane.showMessageDialog(null, "로그인 창으로 전환합니다.");	
				
				mainFrame.cardLayout.show(mainFrame.contentPane, "loginCard");

			}
		});
		
		
	} // UserCard() END
	
}// UserCard END
