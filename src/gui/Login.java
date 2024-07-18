package gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.MemberDAO;
import dto.MemberDTO;

public class Login extends JFrame implements ActionListener {
	MemberDAO mdao = new MemberDAO();
	Menu mLink = new Menu();
	
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JPanel p5 = new JPanel();
	JPanel p6 = new JPanel();

	JLabel l1 = new JLabel("로그인");
	JLabel l2 = new JLabel("ID");
	JLabel l3 = new JLabel("PW");
	JLabel l4 = new JLabel();

	JTextField tf1 = new JTextField(10);
	JTextField tf2 = new JTextField(10);

	JButton b1 = new JButton("입장");
	JButton b2 = new JButton("가입");

	Font f1 = new Font("맑은 고딕", Font.BOLD, 30);

	ImageIcon i1 = new ImageIcon("image/logo2.png");
	ImageIcon i2 = new ImageIcon("image/in.png");
	ImageIcon i3 = new ImageIcon("image/register.png");

	public Login() {
		this.setLayout(new GridLayout(5, 1));

		p5.add(l4);
		l4.setIcon(i1);

		p1.add(l1);
		l1.setFont(f1);

		p2.add(l2);
		p2.add(tf1);

		p3.add(l3);
		p3.add(tf2);

		p4.add(b1);
		p4.add(b2);
		b1.setIcon(i2);
		b2.setIcon(i3);
		
		b1.addActionListener(this);
		b2.addActionListener(this);

		this.add(p5);
		this.add(p1);
		this.add(p2);
		this.add(p3);
		this.add(p4);
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
		if (e.getSource().equals(b1)) {
			String getid = tf1.getText();
			String getpw = tf2.getText();
			MemberDTO m = new MemberDTO();
			m.setId(getid);
			m.setPw(getpw);
			String nowP = mdao.idChk(m);
			if (nowP == null) {
				l1.setText("잘못된 아이디 또는 비밀번호");
			} else {
				this.setVisible(false);
				mdao.nowP(nowP);
				mLink.showBox();
			}
		} else if (e.getSource().equals(b2)) {
			String getid = tf1.getText();
			String getpw = tf2.getText();
			MemberDTO m = new MemberDTO();
			m.setId(getid);
			m.setPw(getpw);
			if(mdao.idDupliChk(getid)) {
				l1.setText("중복된 아이디");
			} else {
				mdao.insert(m);				
			}
		}
	}
}
