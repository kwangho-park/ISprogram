
/*
 * �ذ��ؾ��ϴ� �̽� [��ġ��]
 *
 * savingInfoCard���� textField�� ������ �������� ���鿡�� �α׾ƿ� �� ��� ���ܹ߻� 
 *  - db table savinginfo �� �Ӽ��� null �������� ����
 *    = ����� or ���������� �ذ�Ǿ����� �������� ���������� ���ӵ�
 *    
 * */


package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import controller.Controller;
import dto.UserDTO;
import dto.SavingInfoDTO;


// Model - DAO //
// data access object
// DB(IS program)  class //
public class DAO {
	
	// DBMS connection�� ���� ��� //

	private Connection conn;		// [DB] peparedStatement ��ü ������ �ʿ��� reference variable				
	

	PreparedStatement pstmt;		// DB�� SQL���� �۽�/��ȯ���� �����ϴ� ��ü�� ����Ű������ reference variable ����
	ResultSet rs;					// SQL��ȯ���� �����Ͽ� �����ϴ� ��ü�� ����Ű�� ���� reference variable ����
	
	
	private final String JDBC_DRIBER = "com.mysql.cj.jdbc.Driver";		// JDBC Driver
	
	// DB ���� ���, DB��
	private final String DB_URL = "jdbc:mysql://localhost:3306/isprogram ? useSSL=false & characterEncoding=UTF-8 & serverTimezone=UTC " ;	
		
	private final String USER_NAME = "root";		// DBMS id
	private final String PASSWORD = "peterrabbit";	// DBMS pw
	
	 // DB URL ����
	 // 1. useSSL=false 				: SSL���� ��� ����
	 // 2. characterEncoding=UTF-8 		: ���ڿ� ���ڵ� UTF-8 (=�����ڵ�)
	 // 3. serverTimezone=UTC 			: serverTimezone�� UTC(���������ð�)���� ���� 
	 //	[����] mysql-connector-java 5.1 ���� �������� KST(�ѱ�ǥ�ؽð�) timezone�� �ν��������Ԥ̤�... (6�ð��Ѱ� �����Ͽ� �ذ�)

	
	private Controller controller;
	
	public DAO(){}

	public DAO(Controller controller){
		this.controller = controller;
	}
	

	// �α��� ������ȸ //
	// DB user table ��ȸ�ϴ� �޼ҵ�
	// id = primitive key
	public void userSelect(String id, UserDTO userDto) {

		try {
			
			// 1. JDBC driver load //
			Class.forName(JDBC_DRIBER);
			
			// 2. DB connection //
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);		
			System.out.println("[DB open] Success from DAO loginSelect()");
			
			
			// 3. ���� query �� ����
			String query = "SELECT * FROM user WHERE id = ?";			// login record�� ������ȸ�ϱ����� ���� SQL, ?�ǹ� : parameter

			// [upgrade] parameter(=?)�� Į���� ����� ������ �߻��ϴ� ������????
			// String LoginSql = "SELECT * FROM login WHERE  ? = ?";
			

			// 4. PreparedStatement ��ü ���� //
			pstmt = conn.prepareStatement(query);

			// 5. SQL�� ����(=��û) //
			pstmt.setString(1, id);					// ù��° �Ű�����(Parameters)�� �����ϱ����� ����
	
			// 6. query ��ȯ ��(=����) ���� //
			rs = pstmt.executeQuery();			// SQL ������ �����ϰ� ������ ���� ��ȯ�� ���� ResultSet ��ü�� ����

			// 7. login table���� ResultSet ��ü���� quary�� ��ȸ����� ������ ���� //
			// ����� ������� Ž���ϸ� ���� ���� �� ��� True return, �������� ���� ��� false return
			while(rs.next()) {								// [upgrade] �������̽� ResultSet�� �߻� �޼ҵ��ε� ������ ��� �����Ǿ����?
																
				userDto.setId(rs.getString("id"));			// login table�� id column�� �� ��ȯ �� LoginDTO ��ü�� ����
				userDto.setPw(rs.getString("pw"));							
			}
			
		}catch(Exception e) {
			System.out.println("[Exception] DAO userSelect()");
			e.printStackTrace();
			
		}finally {
			try {
				// 8.close //
				rs.close();			
				pstmt.close();			

				conn.close();				// DB connection close
				System.out.println("[DB close] Success from DAO userSelect()");

			}catch(Exception e) {
				System.out.println("[Exception] DAO userSelect()");
				e.printStackTrace();
			}
		}
	} // userSelect() END
	
	
	
	
	// DB savingInfo table�� ��ȸ //
	public void savingInfoSelect(String pk, String table, SavingInfoDTO savingInfoDTO) {		// login table PK, ���̺��

		String[] getSavingInfo = new String[6];

		try {
			
			// 1. JDBC driver load //
			Class.forName(JDBC_DRIBER);
			
			// 2. DB connection //
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);		
			System.out.println("[DB open] Success from DAO savingInfoSelect()");
			
			
			// 3. ���� query ���� //
			String query = "SELECT * FROM " + table +" WHERE id = ?";	
			
			// 4. PreparedStatement ��ü ���� //
			pstmt = conn.prepareStatement(query);

			// 5. SQL�� ����(=��û) //
			pstmt.setString(1,pk);
			
			// 6. query ��ȯ ��(=����) ���� //
			rs = pstmt.executeQuery();		// SQL�� ����(=��û) + SQL��ȯ ��(=����) ����
			
			
			
			// 7. [DB] savinginfo table �� record�� sting���� ��ȯ�Ͽ� �迭�� ��ҿ� ����
			while(rs.next()) {
				getSavingInfo[0] = rs.getString("id");
				getSavingInfo[1] = rs.getString("bank");
				getSavingInfo[2] = rs.getString("savingtype");
				getSavingInfo[3] = rs.getString("year");
				getSavingInfo[4] = rs.getString("money");
				getSavingInfo[5] = rs.getString("interestrate");
			}	
			
			// savingInfo ��ü�� �����ϱ� ���� DTO�� setting // 
			savingInfoDTO.setAll(getSavingInfo[0],getSavingInfo[1], getSavingInfo[2], getSavingInfo[3], getSavingInfo[4], getSavingInfo[5]);

			
		}catch(Exception e) {
			System.out.println("[Exception] DAO savingInfoSelect()");
			e.printStackTrace();
			
		}finally {
			try {
				// 8. DB close //
				rs.close();	
				pstmt.close();
				
				conn.close();							// DB connection close
				System.out.println("[DB close] Success from DAO savingInfoSelect()");

			}catch(Exception e) {
				System.out.println("[Exception] DAO savingInfoSelect()");
				e.printStackTrace();
			}
		}

	} // savingInfoSelect() END
	
	
	// DB savingInfo table�� ���� ������ ������Ʈ //
	public void savingInfoUpdate(String table, SavingInfoDTO savingInfoDTO) {
		
		
		try {

			// 1. JDBC driver load //
			Class.forName(JDBC_DRIBER);
			
			// 2. DB connection //
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);		
			System.out.println("[DB open] Success from DAO savingInfoSelect()");


			// 3 //
			String savingInfoSql = "UPDATE "+ table +
					" SET bank = ?, savingtype = ?,year = ?,money = ?,interestrate = ? "
					+"WHERE id = ?";
			

			pstmt = conn.prepareStatement(savingInfoSql);

																	
 			pstmt.setString(1,savingInfoDTO.getBank());						
			pstmt.setString(2,savingInfoDTO.getSavingType());			
			pstmt.setString(3,savingInfoDTO.getYear());							
			pstmt.setString(4,savingInfoDTO.getMoney());					
			pstmt.setString(5,savingInfoDTO.getInterestRate());						
			pstmt.setString(6,savingInfoDTO.getId());	

			// �Է°��� ���� ��� ""(���ڿ�) -> 0 (����) �� �־����
			
			

			// SQL(UPDATE)��  ���� = ��ȯ�� ����
			pstmt.executeUpdate();					// ���ͳݼҽ������� INSERT���� ��κ� �̰����� ��� 
													// [??] ���� �� executeQuery()�� ������ �ľ�

			System.out.println("[DB work] data save in savingInfo table");


		}catch(Exception e) {
			System.out.println("[Exception] from DAO savingInfoUpdate()");
			e.printStackTrace();
			
		// [upgrade] close�� finally�� �����ؾ��ϴ� ������??	
		}finally {		
			try {
				pstmt.close();
				conn.close();							// DB connection close
				
				System.out.println("[DB close] Success from DAO savingInfoUpdate()");

			}catch(Exception e) {
				System.out.println("[Exception] DAO savingInfoUpdate()");
				e.printStackTrace();
			}
		}
		
	} // savingInfoUpdate() END
	
	public void userInsert(UserDTO userDto) {
		
		try {

			// 1. JDBC driver load //
			Class.forName(JDBC_DRIBER);
			
			
			// 2. DB connection //
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);		
			System.out.println("[DB open] Success from DAO userInsert()");


			// 3 //
			// user table�� new record INSERT
			String userSql = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(userSql);

			
			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, userDto.getPw());
			pstmt.setString(3, userDto.getName());
			pstmt.setString(4, userDto.getBirthday());
			pstmt.setString(5, userDto.getSex());
			pstmt.setString(6, userDto.getJob());
			pstmt.setString(7, userDto.getAddress());		
			
			// SQL(INSERT)��  ���� = ��ȯ�� ���� // 
			pstmt.executeUpdate();			
			
			

			
			// 4 // 
			// savinginfo table�� new record(=id) INSERT
			String savinginfoSql = "INSERT INTO savinginfo (id) VALUES (?)";
			
			
			pstmt = conn.prepareStatement(savinginfoSql);
			
			pstmt.setString(1, userDto.getId());

			
			// SQL(INSERT)��  ���� = ��ȯ�� ���� // 
			pstmt.executeUpdate();		
		
			
			
			
			System.out.println("[DB work] all data INSERT in user table");
			
			System.out.println("[DB work] id INSERT in savinginfo table");
			
						
		}catch(Exception e){
			System.out.println("[Exception] from DAO userInsert()");
			e.printStackTrace();
			
		}finally{
			
			try {
				pstmt.close();
				conn.close();							// DB connection close
				
				System.out.println("[DB close] Success from DAO userInsert()");

			}catch(Exception e) {
				System.out.println("[Exception] DAO userInsert()");
				e.printStackTrace();
			}
		}
		
	} // userInsert() END
	
	
	// user�� savinginfo table ��ü ���ڵ带 ���� // 
	public void allDelete(String pk) {
		try {

			// 1. JDBC driver load //
			Class.forName(JDBC_DRIBER);
			
			
			// 2. DB connection //
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);	
			
			System.out.println("[DB open] Success from DAO allDelete()");

			
			// savinginfo + user table ���ÿ� ����
			// DELETE FROM savinginfo WHERE  id = 'test2'; 		���ڵ带 �����ϴ� SQL ��ɾ�
			// DELETE FROM user WHERE  id = 'test2'
			

			// 3 //
			// user table record DELETE
			String userSql = "DELETE FROM user WHERE id = ?";

			pstmt = conn.prepareStatement(userSql);
			pstmt.setString(1, pk);
			pstmt.executeUpdate();			
			
			
			// 4 //
			// savinginfo table record DELETE
			String savinginfoSql = "DELETE FROM savinginfo WHERE id = ?";

			pstmt = conn.prepareStatement(savinginfoSql);
			pstmt.setString(1, pk);
			pstmt.executeUpdate();						
			
			
			System.out.println("[DB work] All DELETE in user/savinginfo table");
			
			
		}catch(Exception e){
			System.out.println("[Exception] from DAO allDelete()");
			e.printStackTrace();
			
		}finally{
			
			try {
				pstmt.close();
				conn.close();							// DB connection close
				
				System.out.println("[DB close] Success from DAO allDelete()");

			}catch(Exception e) {
				System.out.println("[Exception] DAO allDelete()");
				e.printStackTrace();
			}
		}	
		
		
	} // allDelete() END
	
	
}// DAO END
