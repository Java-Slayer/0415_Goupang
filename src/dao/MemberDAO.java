package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import dto.MemberDTO;

public class MemberDAO {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";

	public MemberDAO() { // 로드
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			System.out.println("MemberDAO 로드 성공");
			getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("MemberDAO 로드 실패");
		}
	}

	public Connection getConnection() { // 연결
		try {
			conn = DriverManager.getConnection(url, "system", "1111");
			System.out.println("MemberDAO 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("MemberDAO 연결 실패");
		}
		return conn;
	}

	public void insert(MemberDTO m) { // 회원가입
		// TODO Auto-generated method stub
		String sql = "insert into member1 (id, pw, point) values (?, ?, 100000)";
		PreparedStatement psmt = null;
		if (getConnection() != null) {
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getId());
				psmt.setString(2, m.getPw());
				int cnt = psmt.executeUpdate();
				System.out.println(cnt + "건 회원가입 성공");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("회원가입 실패");
			}
		}
	}

	public String idChk(MemberDTO m) { // 로그인
		// TODO Auto-generated method stub
		String sql = "select * from member1 where id = ? and pw = ?";
		String nowP = null;
		PreparedStatement psmt = null;
		if (getConnection() != null) {
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getId());
				psmt.setString(2, m.getPw());
				ResultSet rs = null;
				rs = psmt.executeQuery();
				while (rs.next()) {
					if (m.getId().equals(rs.getString("id")) && m.getPw().equals(rs.getString("pw"))) {
						System.out.println("로그인 성공");
						nowP = rs.getString("id");
					} else {
						System.out.println("로그인 실패");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("로그인 실패");
			}
		}
		return nowP;
	}

	public boolean idDupliChk(String id) { // 로그인
		// TODO Auto-generated method stub
		String sql = "select * from member1 where id = ?";
		PreparedStatement psmt = null;
		if (getConnection() != null) {
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				ResultSet rs = null;
				rs = psmt.executeQuery();
				while (rs.next()) {
					if (id.equals(rs.getString("id"))) {
						System.out.println("아이디 중복");
						return true;
					} else {
						System.out.println("아이디 중복 아님");
						return false;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("아이디 중복 체크 실패");
			}
		}
		return false;
	}

	public String showP(String nowP) { // 포인트 출력
		// TODO Auto-generated method stub
		String sql = "select point from member1 where id = ?";
		String showP = null;
		PreparedStatement psmt = null;
		if (getConnection() != null) {
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, nowP);
				ResultSet rs = null;
				rs = psmt.executeQuery();
				while (rs.next()) {
					showP = rs.getString("point" + "");
				}
				System.out.println("포인트 출력 성공");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("포인트 출력 실패");
			}
		}
		return showP;
	}

	public int showPInt(String nowP) { // 포인트 출력
		// TODO Auto-generated method stub
		String sql = "select point from member1 where id = ?";
		int showP = 0;
		PreparedStatement psmt = null;
		if (getConnection() != null) {
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, nowP);
				ResultSet rs = null;
				rs = psmt.executeQuery();
				while (rs.next()) {
					showP = rs.getInt("point");
				}
				System.out.println("포인트 출력 성공");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("포인트 출력 실패");
			}
		}
		return showP;
	}

	public void point(Integer sum, String name) { // 포인트 차감
		// TODO Auto-generated method stub
		if (getConnection() != null) {
			try {
				String sql = "update member1 set point = point - (?) where id = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, sum);
				psmt.setString(2, name);
				int cnt = psmt.executeUpdate();
				System.out.println(cnt + "건 포인트 차감 성공");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("포인트 차감 실패");
			}
		}
	}

	public DefaultTableModel meList(String id) { // 회원 정보 출력
		// TODO Auto-generated method stub
		String header[] = { "아이디", "포인트" };
		DefaultTableModel model = new DefaultTableModel(header, 0);
		if (getConnection() != null) {
			try {
				String sql = "select id, point from member1 where id = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				ResultSet rs = null;
				rs = psmt.executeQuery();
				while (rs.next()) {
					model.addRow(new Object[] { rs.getString("id"), rs.getInt("point") });
				}
				System.out.println("나의 정보 불러오기 성공");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("나의 정보 불러오기 실패");
			}
		}
		return model;
	}

	public void addPoint(String point, String id) { // 포인트 충전
		// TODO Auto-generated method stub
		if (getConnection() != null) {
			try {
				String sql = "update member1 set point = point + (?) where id = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, Integer.parseInt(point));
				psmt.setString(2, id);
				int cnt = psmt.executeUpdate();
				System.out.println(cnt + "건 포인트 충전 성공");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("포인트 충전 실패");
			}
		}
	}

	public void nowP(String nowP) { //현재 접속중인 아이디 저장
		// TODO Auto-generated method stub
		if (getConnection() != null) {
			try {
				String sql = "update nowp set id = (?) where test = test";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, nowP);
				int cnt = psmt.executeUpdate();
				System.out.println(cnt + "건 현재아이디 입력 성공");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("현재 아이디 입력 실패");
			}
		}
	}

	public String getP() { //현재 접속중인 아이디 출력
		// TODO Auto-generated method stub
		String nowP = null;
		if (getConnection() != null) {
			try {
				String sql = "select id from nowp where test = test";
				Statement stmt = null;
				stmt = conn.createStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					nowP = rs.getString("id");
					return nowP;					
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("아이디 출력 실패");
			}
		}
		return nowP;
	}
}
