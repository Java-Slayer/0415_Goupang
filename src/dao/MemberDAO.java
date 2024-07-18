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

	public MemberDAO() { // �ε�
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			System.out.println("MemberDAO �ε� ����");
			getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("MemberDAO �ε� ����");
		}
	}

	public Connection getConnection() { // ����
		try {
			conn = DriverManager.getConnection(url, "system", "1111");
			System.out.println("MemberDAO ���� ����");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("MemberDAO ���� ����");
		}
		return conn;
	}

	public void insert(MemberDTO m) { // ȸ������
		// TODO Auto-generated method stub
		String sql = "insert into member1 (id, pw, point) values (?, ?, 100000)";
		PreparedStatement psmt = null;
		if (getConnection() != null) {
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getId());
				psmt.setString(2, m.getPw());
				int cnt = psmt.executeUpdate();
				System.out.println(cnt + "�� ȸ������ ����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ȸ������ ����");
			}
		}
	}

	public String idChk(MemberDTO m) { // �α���
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
						System.out.println("�α��� ����");
						nowP = rs.getString("id");
					} else {
						System.out.println("�α��� ����");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�α��� ����");
			}
		}
		return nowP;
	}

	public boolean idDupliChk(String id) { // �α���
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
						System.out.println("���̵� �ߺ�");
						return true;
					} else {
						System.out.println("���̵� �ߺ� �ƴ�");
						return false;
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("���̵� �ߺ� üũ ����");
			}
		}
		return false;
	}

	public String showP(String nowP) { // ����Ʈ ���
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
				System.out.println("����Ʈ ��� ����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("����Ʈ ��� ����");
			}
		}
		return showP;
	}

	public int showPInt(String nowP) { // ����Ʈ ���
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
				System.out.println("����Ʈ ��� ����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("����Ʈ ��� ����");
			}
		}
		return showP;
	}

	public void point(Integer sum, String name) { // ����Ʈ ����
		// TODO Auto-generated method stub
		if (getConnection() != null) {
			try {
				String sql = "update member1 set point = point - (?) where id = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, sum);
				psmt.setString(2, name);
				int cnt = psmt.executeUpdate();
				System.out.println(cnt + "�� ����Ʈ ���� ����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("����Ʈ ���� ����");
			}
		}
	}

	public DefaultTableModel meList(String id) { // ȸ�� ���� ���
		// TODO Auto-generated method stub
		String header[] = { "���̵�", "����Ʈ" };
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
				System.out.println("���� ���� �ҷ����� ����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("���� ���� �ҷ����� ����");
			}
		}
		return model;
	}

	public void addPoint(String point, String id) { // ����Ʈ ����
		// TODO Auto-generated method stub
		if (getConnection() != null) {
			try {
				String sql = "update member1 set point = point + (?) where id = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, Integer.parseInt(point));
				psmt.setString(2, id);
				int cnt = psmt.executeUpdate();
				System.out.println(cnt + "�� ����Ʈ ���� ����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("����Ʈ ���� ����");
			}
		}
	}

	public void nowP(String nowP) { //���� �������� ���̵� ����
		// TODO Auto-generated method stub
		if (getConnection() != null) {
			try {
				String sql = "update nowp set id = (?) where test = test";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, nowP);
				int cnt = psmt.executeUpdate();
				System.out.println(cnt + "�� ������̵� �Է� ����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("���� ���̵� �Է� ����");
			}
		}
	}

	public String getP() { //���� �������� ���̵� ���
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
				System.out.println("���̵� ��� ����");
			}
		}
		return nowP;
	}
}
