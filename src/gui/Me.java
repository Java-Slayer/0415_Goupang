package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.MemberDAO;

public class Me extends JFrame implements ActionListener {
	MemberDAO mdao = new MemberDAO();

	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();

	JLabel l1 = new JLabel("나의 정보");
	JLabel l2 = new JLabel();

	JButton b1 = new JButton("충전");
	JButton b2 = new JButton("뒤로");

	JTextField tf1 = new JTextField(10);

	JTable t1 = new JTable(mdao.meList(mdao.getP()));
	JScrollPane scrollpane;

	ImageIcon i1 = new ImageIcon("image/logo2.png");
	ImageIcon i2 = new ImageIcon("image/plus.png");
	ImageIcon i3 = new ImageIcon("image/back.png");

	Me() {
		this.setLayout(new GridLayout(5, 1));

		p4.add(l2);
		l2.setIcon(i1);

		p1.add(l1);

		scrollpane = new JScrollPane(t1);
		scrollpane.setPreferredSize(new Dimension(300, 100));

		p2.add(scrollpane);

		p3.add(tf1);
		p3.add(b1);
		b1.setIcon(i2);

		p5.add(b2);
		b2.setIcon(i3);

		b1.addActionListener(this);
		b2.addActionListener(this);

		this.add(p4);
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p5);
		this.setBounds(1300, 200, 500, 700);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	public void showBox() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String point = tf1.getText();
		if (e.getSource().equals(b1)) {
			mdao.addPoint(point, mdao.getP());
		} else if (e.getSource().equals(b2)) {
			this.setVisible(false);
			Menu mLink = new Menu();
			mLink.showBox();
		}
	}

}
