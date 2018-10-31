package dto;
import controller.Controller;

public class UserDTO {
	
	// [upgrade] �����̿��� data structure���� ���μ��� ���̴� ����� ?
	// array = �迭�� element�� ��ȣ�� ǥ���ϱ� ������ ���μ��� ���� ����

	String[] user = new String[7];
/*
 * user[0] : id 		(���̵�)
 * user[1] : pw			(��й�ȣ)
 * user[2] : name		(�̸�)
 * user[3] : birthday 	(�������)
 * user[4] : sex		(����)
 * user[5] : job		(����)
 * user[6] : address 	(�ּ�)
 */
	

	
	private Controller controller;

	
	public UserDTO(){}

	public UserDTO(Controller controller){
		this.controller = controller;
	}
	
	
	public void setAll(String id, String pw, String name, 
			String birthday, String sex, String job, String address) {
		
		this.user[0] = id;		this.user[1] = pw;
		this.user[2] = name;	this.user[3] = birthday;
		this.user[4] = sex;		this.user[5] = job;				
		this.user[6] = address;
	}
	
	// setter // 
	public void setId(String id) {
		this.user[0] = id;
	} 
	public void setPw(String pw) {
		this.user[1] = pw;
	} 
	public void setName(String name) {
		this.user[2] = name;
	} 
	public void setBirthday(String birthday) {
		this.user[3] = birthday;
	} 
	public void setSex(String sex) {
		this.user[4] = sex;
	} 
	public void setJob(String job) {
		this.user[5] = job;
	} 
	public void setAddress(String address) {
		this.user[6] = address;
	} 
	
	
	
	// getter // 
	public String getId() {
		return this.user[0];
	}
	
	public String getPw() {
		return this.user[1];
	}
	public String getName() {
		return this.user[2];
	} 
	public String getBirthday() {
		return this.user[3];
	} 
	public String getSex() {
		return this.user[4];
	} 
	public String getJob() {
		return this.user[5];
	} 
	public String getAddress() {
		return this.user[6];
	} 
	
	// ��ü ���� ��ȯ�ϱ� ���� �޼ҵ� (ȣ���ϴ� �޼ҵ忡�� ���ϴ� ��ҹ�ȣ�� ��û = �ݺ���)
	public String getAll(int number) {
		return this.user[number];
	}
	
} // UserDTO END
