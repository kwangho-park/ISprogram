
/*
 * 해결해야하는 이슈 [펼치기]
 *
 * savingInfoCard에서 textField를 제외한 나머지가 공백에서 로그아웃 할 경우 예외발생 
 *  - db table savinginfo 의 속성을 null 가능으로 변경
 *    = 은행명 or 적금종류는 해결되었으나 나머지는 동일현상이 지속됨
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
	
	// DBMS connection을 위한 상수 //

	private Connection conn;		// [DB] peparedStatement 객체 생성시 필요한 reference variable				
	

	PreparedStatement pstmt;		// DB에 SQL문을 송신/반환값을 리턴하는 객체를 가리키기위한 reference variable 선언
	ResultSet rs;					// SQL반환값을 저장하여 관리하는 객체를 가리키기 위한 reference variable 선언
	
	
	private final String JDBC_DRIBER = "com.mysql.cj.jdbc.Driver";		// JDBC Driver
	
	// DB 접속 경로, DB명
	private final String DB_URL = "jdbc:mysql://localhost:3306/isprogram ? useSSL=false & characterEncoding=UTF-8 & serverTimezone=UTC " ;	
		
	private final String USER_NAME = "root";		// DBMS id
	private final String PASSWORD = "peterrabbit";	// DBMS pw
	
	 // DB URL 세부
	 // 1. useSSL=false 				: SSL관련 경고 제거
	 // 2. characterEncoding=UTF-8 		: 문자열 인코딩 UTF-8 (=유니코드)
	 // 3. serverTimezone=UTC 			: serverTimezone을 UTC(세계협정시간)으로 설정 
	 //	[참고] mysql-connector-java 5.1 이후 버전부터 KST(한국표준시간) timezone을 인식하지못함ㅜㅜ... (6시간넘게 투자하여 해결)

	
	private Controller controller;
	
	public DAO(){}

	public DAO(Controller controller){
		this.controller = controller;
	}
	

	// 로그인 정보조회 //
	// DB user table 조회하는 메소드
	// id = primitive key
	public void userSelect(String id, UserDTO userDto) {

		try {
			
			// 1. JDBC driver load //
			Class.forName(JDBC_DRIBER);
			
			// 2. DB connection //
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);		
			System.out.println("[DB open] Success from DAO loginSelect()");
			
			
			// 3. 동적 query 문 생성
			String query = "SELECT * FROM user WHERE id = ?";			// login record를 선택조회하기위한 동적 SQL, ?의미 : parameter

			// [upgrade] parameter(=?)를 칼럼명에 적용시 에러가 발생하는 이유는????
			// String LoginSql = "SELECT * FROM login WHERE  ? = ?";
			

			// 4. PreparedStatement 객체 생성 //
			pstmt = conn.prepareStatement(query);

			// 5. SQL문 전송(=요청) //
			pstmt.setString(1, id);					// 첫번째 매개변수(Parameters)에 전달하기위한 인자
	
			// 6. query 반환 값(=응답) 저장 //
			rs = pstmt.executeQuery();			// SQL 쿼리를 실행하고 쿼리에 의해 반환된 값은 ResultSet 객체에 저장

			// 7. login table에서 ResultSet 객체에서 quary의 조회결과를 꺼내는 로직 //
			// 결과를 행단위로 탐색하며 행이 존재 할 경우 True return, 존재하지 않을 경우 false return
			while(rs.next()) {								// [upgrade] 인터페이스 ResultSet의 추상 메소드인데 로직은 어디에 구현되어는지?
																
				userDto.setId(rs.getString("id"));			// login table의 id column의 값 반환 후 LoginDTO 객체에 저장
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
	
	
	
	
	// DB savingInfo table을 조회 //
	public void savingInfoSelect(String pk, String table, SavingInfoDTO savingInfoDTO) {		// login table PK, 테이블명

		String[] getSavingInfo = new String[6];

		try {
			
			// 1. JDBC driver load //
			Class.forName(JDBC_DRIBER);
			
			// 2. DB connection //
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);		
			System.out.println("[DB open] Success from DAO savingInfoSelect()");
			
			
			// 3. 동적 query 생성 //
			String query = "SELECT * FROM " + table +" WHERE id = ?";	
			
			// 4. PreparedStatement 객체 생성 //
			pstmt = conn.prepareStatement(query);

			// 5. SQL문 전송(=요청) //
			pstmt.setString(1,pk);
			
			// 6. query 반환 값(=응답) 저장 //
			rs = pstmt.executeQuery();		// SQL문 전송(=요청) + SQL반환 값(=응답) 저장
			
			
			
			// 7. [DB] savinginfo table 의 record를 sting으로 변환하여 배열의 요소에 저장
			while(rs.next()) {
				getSavingInfo[0] = rs.getString("id");
				getSavingInfo[1] = rs.getString("bank");
				getSavingInfo[2] = rs.getString("savingtype");
				getSavingInfo[3] = rs.getString("year");
				getSavingInfo[4] = rs.getString("money");
				getSavingInfo[5] = rs.getString("interestrate");
			}	
			
			// savingInfo 객체에 전달하기 위해 DTO에 setting // 
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
	
	
	// DB savingInfo table에 적금 정보를 업데이트 //
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

			// 입력값이 없을 경우 ""(문자열) -> 0 (정수) 을 넣어야함
			
			

			// SQL(UPDATE)문  전송 = 반환값 없음
			pstmt.executeUpdate();					// 인터넷소스에서는 INSERT문은 대부분 이것으로 사용 
													// [??] 동작 및 executeQuery()과 차이점 파악

			System.out.println("[DB work] data save in savingInfo table");


		}catch(Exception e) {
			System.out.println("[Exception] from DAO savingInfoUpdate()");
			e.printStackTrace();
			
		// [upgrade] close를 finally로 선언해야하는 이유는??	
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
			// user table에 new record INSERT
			String userSql = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(userSql);

			
			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, userDto.getPw());
			pstmt.setString(3, userDto.getName());
			pstmt.setString(4, userDto.getBirthday());
			pstmt.setString(5, userDto.getSex());
			pstmt.setString(6, userDto.getJob());
			pstmt.setString(7, userDto.getAddress());		
			
			// SQL(INSERT)문  전송 = 반환값 없음 // 
			pstmt.executeUpdate();			
			
			

			
			// 4 // 
			// savinginfo table에 new record(=id) INSERT
			String savinginfoSql = "INSERT INTO savinginfo (id) VALUES (?)";
			
			
			pstmt = conn.prepareStatement(savinginfoSql);
			
			pstmt.setString(1, userDto.getId());

			
			// SQL(INSERT)문  전송 = 반환값 없음 // 
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
	
	
	// user와 savinginfo table 전체 레코드를 제거 // 
	public void allDelete(String pk) {
		try {

			// 1. JDBC driver load //
			Class.forName(JDBC_DRIBER);
			
			
			// 2. DB connection //
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);	
			
			System.out.println("[DB open] Success from DAO allDelete()");

			
			// savinginfo + user table 동시에 제거
			// DELETE FROM savinginfo WHERE  id = 'test2'; 		레코드를 삭제하는 SQL 명령어
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
