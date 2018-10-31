package dto;
import controller.Controller;

public class UserDTO {
	
	// [upgrade] 변수이외의 data structure으로 시인성을 높이는 방법은 ?
	// array = 배열의 element를 번호로 표기하기 때문에 시인성이 좋지 않음

	String[] user = new String[7];
/*
 * user[0] : id 		(아이디)
 * user[1] : pw			(비밀번호)
 * user[2] : name		(이름)
 * user[3] : birthday 	(생년월일)
 * user[4] : sex		(성별)
 * user[5] : job		(직업)
 * user[6] : address 	(주소)
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
	
	// 전체 값을 반환하기 위한 메소드 (호출하는 메소드에서 원하는 요소번호로 요청 = 반복문)
	public String getAll(int number) {
		return this.user[number];
	}
	
} // UserDTO END
