package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.ItemDAO;
import dao.MemberDAO;

public class List extends JFrame implements ActionListener {
	ItemDAO idao = new ItemDAO();
	MemberDAO mdao = new MemberDAO();

	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();

	JLabel l2 = new JLabel("포인트 : " + mdao.showP(mdao.getP()));
	JLabel l3 = new JLabel();

	JButton b1 = new JButton("담기");
	JButton b2 = new JButton("뒤로");

	JTextField tf1 = new JTextField(10);

	JTable t1 = new JTable(idao.listAll());
	JScrollPane scrollpane;

	ImageIcon i1 = new ImageIcon("image/logo2.png");
	ImageIcon i2 = new ImageIcon("image/inKart.png");
	ImageIcon i3 = new ImageIcon("image/back.png");

	Font f1 = new Font("맑은 고딕", Font.BOLD, 20);

	public void showBox() {
		this.setVisible(true);
	}

	List() {
		this.setLayout(new GridLayout(3, 1));
		p4.add(l3);
		l3.setIcon(i1);

		p2.setLayout(new BorderLayout());
		scrollpane = new JScrollPane(t1);
		scrollpane.setPreferredSize(new Dimension(400, 150));
		p2.add(scrollpane, "North");
		p2.add(l2, "South");
		l2.setFont(f1);
		l2.setBackground(Color.orange);

		p3.add(tf1);
		p3.add(b1);
		b1.setIcon(i2);
		p3.add(b2);
		b2.setIcon(i3);

		b1.addActionListener(this);
		b2.addActionListener(this);

		this.add(p4);
		this.add(p2);
		this.add(p3);
		this.setBounds(1300, 200, 500, 700);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(b1)) {
			String pick = tf1.getText();
			if (idao.stockNullChk(pick) == null) {
				JOptionPane.showMessageDialog(null, "없는 상품입니다.");
			} else {
				if (idao.stockChk(pick) <= 0) {
					JOptionPane.showMessageDialog(null, "재고가 부족합니다.");
				} else {
					idao.pickItem(pick, mdao.getP());
				}
			}
		} else if (e.getSource().equals(b2)) {
			this.setVisible(false);
			Menu mLink = new Menu();
			mLink.showBox();
		}
	}

}
