package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class KartDAO {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";

	public KartDAO() { // �ε�
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			System.out.println("KartDAO �ε� ����");
			getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("KartDAO �ε� ����");
		}
	}

	public Connection getConnection() { // ����
		try {
			conn = DriverManager.getConnection(url, "system", "1111");
			System.out.println("KartDAO ���� ����");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("KartDAO ���� ����");
		}
		return conn;
	}

	public DefaultTableModel allList(String nowP) { // ��ü����
		String header[] = { "��ǰ��", "����", "���" };
		DefaultTableModel model = new DefaultTableModel(header, 0);
		if (getConnection() != null) {
			try {
				String sql = "select item.name, item.price, item.stock from item inner join kart on kart.name = item.name where kart.id = (?) order by item.price";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, nowP);
				ResultSet rs = null;
				rs = psmt.executeQuery();
				while (rs.next()) {
					model.addRow(new Object[] { rs.getString("name"), rs.getInt("price"), rs.getInt("stock") });
					System.out.println("��ٱ��� �ҷ����� ����");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��ٱ��� �ҷ����� ����");
			}
		}
		return model;
	}

	public void delete(String name, String nowP) { // ����
		// TODO Auto-generated method stub
		if (getConnection() != null) {
			try {
				String sql = "delete from kart where name = (?) and id = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, name);
				psmt.setString(2, nowP);
				int cnt = psmt.executeUpdate();
				System.out.println(cnt + "�� ���� �Ϸ�");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("���� ����");
			}
		}
	}

	public ArrayList<String> nameList(String nowP) { // �̸� ��������
		// TODO Auto-generated method stub
		ArrayList<String> name = new ArrayList<>();
		if (getConnection() != null) {
			try {
				String sql = "select * from kart where id = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, nowP);
				ResultSet rs = null;
				rs = psmt.executeQuery();
				while (rs.next()) {
					name.add(rs.getString("name"));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return name;
	}
	
	
	public Integer allPrice(String nowP) { // ���� ���� ��������
		// TODO Auto-generated method stub
		String sql = "select price from item inner join kart on kart.name = item.name where kart.id = (?)";
		int sum = 0;
		if (getConnection() != null) {
			try {
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, nowP);
				ResultSet rs = null;
				rs = psmt.executeQuery();
				while (rs.next()) {
					sum = sum + rs.getInt("price");
				}
				System.out.println("���� �ҷ����� ����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("���� �ҷ����� ����");
			}
		}
		return sum;
	}

	public void deleteAll(String nowP) { // ��ٱ��� ��ü ����
		// TODO Auto-generated method stub
		if (getConnection() != null) {
			try {
				String sql = "delete from kart where id = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, nowP);
				psmt.executeQuery();
				System.out.println("���� ����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("���� ����");
			}
		}
	}

	public String nullChk(String name) {
		// TODO Auto-generated method stub
		String temp = null;
		if (getConnection() != null) {
			try {
				String sql = "select name from kart where name = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, name);
				ResultSet rs = null;
				rs = psmt.executeQuery();
				while (rs.next()) {
					temp = rs.getString("name");
					return temp;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�̸� ��� ����");
			}
		}
		return temp;
	}
}
