package view;

import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JFrame;

import controller.Controller;

// VIEW 1 //
// LoginCard, SavingInfoCard 객체 init 및 Setting
public class MainFrame extends JFrame{

	public Container contentPane = getContentPane();			// getContentPane() : Frame에 부착되어있는 ContentPane객체의 참조값(주소값)을 반환하는 메소드
	
	public CardLayout cardLayout = new CardLayout();			// mainCardPanel의 layout (Panel을 선택할 수 있는 객체 메소드에 접근을 위함)

	
	// [중요] Card Panel 은 controller에 접근시 mainFrame에 선언된 controller 참조변수로 접근함
	Controller controller;
	
	
	// Card Panel 객체의 참조값을 저장하는 변수 //
	public LoginCard loginCard; 
	public SavingInfoCard savingInfoCard;
	public UserCard userCard;
	
	public MainFrame(){}
	
	
	public MainFrame(Controller controller){
		
		this.controller = controller;
		
		// contentPanel setting // 
		contentPane.setLayout(cardLayout);		// JFrame생성시 Frame에 부착되어있는 Panel
		
		
		// CardPanel 객체 생성 // 
		loginCard = new LoginCard(this);
		savingInfoCard = new SavingInfoCard(this);
		userCard = new UserCard(this);
		
		
		// JFrame 설정
		setTitle("Installment Saving Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// 프레임의 종료버튼을 클릭시 프로그램을 종료
		setSize(300, 400);		// (설정하지않으면 종료가 아닌 화면에서만 보이지 않게됨)
		setLocationRelativeTo(null);	// 화면 중앙에 표시
		setVisible(true);				// [질문] 반드시 마지막에 호출해야함 (=이유는???)
		
		
	} // MainFrame() END
	
	
} // MyFrame END