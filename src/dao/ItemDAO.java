package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class ItemDAO {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";

	public ItemDAO() { // �ε�
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			System.out.println("ItemDAO �ε� ����");
			getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ItemDAO �ε� ����");
		}
	}

	public Connection getConnection() { // ����
		try {
			conn = DriverManager.getConnection(url, "system", "1111");
			System.out.println("ItemDAO ���� ����");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ItemDAO ���� ����");
		}
		return conn;
	}

	public DefaultTableModel listAll() { // ��ǰ ���
		// TODO Auto-generated method stub
		String header[] = { "��ǰ��", "����", "���" };
		DefaultTableModel model = new DefaultTableModel(header, 0);
		if (getConnection() != null) {
			try {
				String sql = "select * from item order by price";
				Statement stmt = null;
				stmt = conn.createStatement();
				ResultSet rs = null;
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					model.addRow(new Object[] { rs.getString("name"), rs.getInt("price"), rs.getInt("stock") });
				}
				System.out.println("��ǰ �ҷ����� ����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��ǰ �ҷ����� ����");
			}
		}
		return model;
	}

	public void pickItem(String pick, String nowP) { // ��ٱ��� ���
		// TODO Auto-generated method stub
		if (getConnection() != null) {
			try {
				String sql = "insert into kart values (?, ?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, pick);
				psmt.setString(2, nowP);
				int cnt = psmt.executeUpdate();
				System.out.println(cnt + "�� ��ٱ��� �Է� �Ϸ�");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��ٱ��� �Է� ����");
			}
		}
	}

	public void stock(ArrayList<String> iList) { // ��� ����
		// TODO Auto-generated method stub
		if (getConnection() != null) {
			try {
				String sql = "update item set stock = stock -1 where name = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				for (int i = 0; i < iList.size(); i++) {
					psmt.setString(1, iList.get(i));
					int cnt = psmt.executeUpdate();
					System.out.println(cnt + "�� ������� �Ϸ�");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��� ���� ����");
			}
		}
	}

	public int stockChk(String pick) {
		// TODO Auto-generated method stub
		Integer stock = null;
		if (getConnection() != null) {
			try {
				String sql = "select stock from item where name = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, pick);
				ResultSet rs = null;
				rs = psmt.executeQuery();
				while(rs.next()) {
					stock = rs.getInt("stock");
					return stock;					
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��� ��� ����");
			}
		}
		return stock;
	}

	public String stockNullChk(String pick) {
		// TODO Auto-generated method stub
		String stock = null;
		if (getConnection() != null) {
			try {
				String sql = "select stock from item where name = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, pick);
				ResultSet rs = null;
				rs = psmt.executeQuery();
				while(rs.next()) {
					stock = rs.getString("stock"+"");
					return stock;					
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��� ��� ����");
			}
		}
		return stock;
	}
}
