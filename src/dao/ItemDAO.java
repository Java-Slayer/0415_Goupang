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

	public ItemDAO() { // 로드
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			System.out.println("ItemDAO 로드 성공");
			getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ItemDAO 로드 실패");
		}
	}

	public Connection getConnection() { // 연결
		try {
			conn = DriverManager.getConnection(url, "system", "1111");
			System.out.println("ItemDAO 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ItemDAO 연결 실패");
		}
		return conn;
	}

	public DefaultTableModel listAll() { // 상품 출력
		// TODO Auto-generated method stub
		String header[] = { "상품명", "가격", "재고" };
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
				System.out.println("상품 불러오기 성공");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("상품 불러오기 실패");
			}
		}
		return model;
	}

	public void pickItem(String pick, String nowP) { // 장바구니 담기
		// TODO Auto-generated method stub
		if (getConnection() != null) {
			try {
				String sql = "insert into kart values (?, ?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, pick);
				psmt.setString(2, nowP);
				int cnt = psmt.executeUpdate();
				System.out.println(cnt + "건 장바구니 입력 완료");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("장바구니 입력 실패");
			}
		}
	}

	public void stock(ArrayList<String> iList) { // 재고 차감
		// TODO Auto-generated method stub
		if (getConnection() != null) {
			try {
				String sql = "update item set stock = stock -1 where name = (?)";
				PreparedStatement psmt = null;
				psmt = conn.prepareStatement(sql);
				for (int i = 0; i < iList.size(); i++) {
					psmt.setString(1, iList.get(i));
					int cnt = psmt.executeUpdate();
					System.out.println(cnt + "건 재고조정 완료");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("재고 조정 실패");
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
				System.out.println("재고량 출력 실패");
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
				System.out.println("재고량 출력 실패");
			}
		}
		return stock;
	}
}
