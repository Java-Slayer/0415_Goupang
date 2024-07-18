package gui;

import java.awt.BorderLayout;
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
import dao.KartDAO;
import dao.MemberDAO;

public class Kart extends JFrame implements ActionListener {
	KartDAO kdao = new KartDAO();
	MemberDAO mdao = new MemberDAO();
	ItemDAO idao = new ItemDAO();

	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();

	JLabel l2 = new JLabel("����Ʈ : " + mdao.showP(mdao.getP()));
	JLabel l3 = new JLabel("�Ѿ� : " + kdao.allPrice(mdao.getP()) + "");
	JLabel l4 = new JLabel();

	JButton b1 = new JButton("����");
	JButton b2 = new JButton("�ڷ�");
	JButton b3 = new JButton("����");

	JTextField tf1 = new JTextField(10);

	JTable t1 = new JTable(kdao.allList(mdao.getP()));
	JScrollPane scrollpane;

	ImageIcon i1 = new ImageIcon("image/logo2.png");
	ImageIcon i2 = new ImageIcon("image/purchase.png");
	ImageIcon i3 = new ImageIcon("image/delete.png");
	ImageIcon i4 = new ImageIcon("image/back.png");

	Font f1 = new Font("���� ���", Font.BOLD, 20);

	Kart() {
		this.setLayout(new GridLayout(5, 1));
		p5.add(l4);
		l4.setIcon(i1);

		p2.setLayout(new BorderLayout());
		scrollpane = new JScrollPane(t1);
		scrollpane.setPreferredSize(new Dimension(300, 100));
		p2.add(scrollpane, "North");
		p2.add(l2, "South");
		l2.setFont(f1);

		p4.add(l3);

		p3.add(tf1);
		p3.add(b3);
		b3.setIcon(i3);

		p6.add(b1);
		b1.setIcon(i2);
		p6.add(b2);
		b2.setIcon(i4);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

		this.add(p5);
		this.add(p2);
		this.add(p4);
		this.add(p3);
		this.add(p6);
		this.setBounds(1300, 200, 500, 700);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	public void showBox() {
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(b1)) { // ��� ����, ����Ʈ ����, ��ٱ��Ͽ��� ����
			if (mdao.showPInt(mdao.getP()) - kdao.allPrice(mdao.getP()) < 0) {
				JOptionPane.showMessageDialog(null, "����Ʈ�� �����մϴ�.");
			} else {
				mdao.point(kdao.allPrice(mdao.getP()), mdao.getP());
				idao.stock(kdao.nameList(mdao.getP()));
				kdao.deleteAll(mdao.getP());
			}
		} else if (e.getSource().equals(b2)) {
			this.setVisible(false);
			Menu mLink = new Menu();
			mLink.showBox();
		} else if (e.getSource().equals(b3)) {
			String name = tf1.getText();
			if (kdao.nullChk(name) == null) {
				JOptionPane.showMessageDialog(null, "���� ��ǰ�Դϴ�.");
			} else {
				kdao.delete(name, mdao.getP());
			}
		}
	}

}
