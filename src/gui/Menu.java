package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.MemberDAO;

public class Menu extends JFrame implements ActionListener {
	MemberDAO mdao = new MemberDAO();

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();

	JLabel l3 = new JLabel("아이디 : " + mdao.getP());
	JLabel l1 = new JLabel("| 포인트 : " + mdao.showP(mdao.getP()));
	JLabel l4 = new JLabel();

	JButton b1 = new JButton("쇼핑하기");
	JButton b2 = new JButton("장바구니");
	JButton b3 = new JButton("나의정보");
	JButton b4 = new JButton("로그아웃");

	ImageIcon i1 = new ImageIcon("image/logo2.png");
	ImageIcon i2 = new ImageIcon("image/shopping.png");
	ImageIcon i3 = new ImageIcon("image/kart.png");
	ImageIcon i4 = new ImageIcon("image/me.png");
	ImageIcon i5 = new ImageIcon("image/logout.png");

	Font f1 = new Font("맑은 고딕", Font.BOLD, 20);

	public void showBox() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

	Menu() {
		this.setLayout(new GridLayout(7, 1));
		p6.add(l4);
		l4.setIcon(i1);

		p1.setBackground(Color.orange);
		p1.add(l3);
		p1.add(l1);
		l1.setFont(f1);
		l3.setFont(f1);

		p2.add(b1);
		b1.setIcon(i2);

		p3.add(b2);
		b2.setIcon(i3);

		p4.add(b3);
		b3.setIcon(i4);

		p5.add(b4);
		b4.setIcon(i5);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		this.add(p6);
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
		this.add(p5);
		this.setBounds(1300, 200, 500, 700);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(b1)) {
			this.setVisible(false);
			List lLink = new List();
			lLink.showBox();
		} else if (e.getSource().equals(b2)) {
			this.setVisible(false);
			Kart kLink = new Kart();
			kLink.showBox();
		} else if (e.getSource().equals(b3)) {
			this.setVisible(false);
			Me mLink = new Me();
			mLink.showBox();
		} else if (e.getSource().equals(b4)) {
			this.setVisible(false);
			Login lLink = new Login();
			lLink.showBox();
		}
	}

}
