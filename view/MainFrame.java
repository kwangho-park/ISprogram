package view;

import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JFrame;

import controller.Controller;

// VIEW 1 //
// LoginCard, SavingInfoCard ��ü init �� Setting
public class MainFrame extends JFrame{

	public Container contentPane = getContentPane();			// getContentPane() : Frame�� �����Ǿ��ִ� ContentPane��ü�� ������(�ּҰ�)�� ��ȯ�ϴ� �޼ҵ�
	
	public CardLayout cardLayout = new CardLayout();			// mainCardPanel�� layout (Panel�� ������ �� �ִ� ��ü �޼ҵ忡 ������ ����)

	
	// [�߿�] Card Panel �� controller�� ���ٽ� mainFrame�� ����� controller ���������� ������
	Controller controller;
	
	
	// Card Panel ��ü�� �������� �����ϴ� ���� //
	public LoginCard loginCard; 
	public SavingInfoCard savingInfoCard;
	public UserCard userCard;
	
	public MainFrame(){}
	
	
	public MainFrame(Controller controller){
		
		this.controller = controller;
		
		// contentPanel setting // 
		contentPane.setLayout(cardLayout);		// JFrame������ Frame�� �����Ǿ��ִ� Panel
		
		
		// CardPanel ��ü ���� // 
		loginCard = new LoginCard(this);
		savingInfoCard = new SavingInfoCard(this);
		userCard = new UserCard(this);
		
		
		// JFrame ����
		setTitle("Installment Saving Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// �������� �����ư�� Ŭ���� ���α׷��� ����
		setSize(300, 400);		// (�������������� ���ᰡ �ƴ� ȭ�鿡���� ������ �ʰԵ�)
		setLocationRelativeTo(null);	// ȭ�� �߾ӿ� ǥ��
		setVisible(true);				// [����] �ݵ�� �������� ȣ���ؾ��� (=������???)
		
		
	} // MainFrame() END
	
	
} // MyFrame END