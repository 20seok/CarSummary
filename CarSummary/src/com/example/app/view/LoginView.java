package com.example.app.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.DimensionUIResource;

import com.example.app.CarManager;
import com.example.repository.Member2DAO;
import com.example.repository.SharedData;

public class LoginView implements Viewable {

	public static final String VIEW_NAME = "로그인 화면";

	Member2DAO member2DAO = new Member2DAO();

	// 화면 이동시 cardLayout, container 필요함
	private CardLayout cardLayout;
	private Container container;
	private CarManager frame;

	private JPanel panelLogin;
	private JLabel lblNewLabel;
	private JTextField tfId;
	private JLabel lblNewLabel_1;
	private JPasswordField pfPwd;
	private JButton btnLogin;
	private JButton btnGoJoin;
	private JLabel lblNewLabel_2;

	public LoginView(CardLayout cardLayout, Container container, CarManager frame) {
		this.cardLayout = cardLayout;
		this.container = container;
		this.frame = frame;
		

		init();
		setup();
	}

	private void init() {
		panelLogin = new JPanel();
		lblNewLabel = new JLabel("아이디");
		tfId = new JTextField();
		lblNewLabel_1 = new JLabel("비밀번호");
		pfPwd = new JPasswordField();
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verifyInput();
			}
		});
		btnGoJoin = new JButton("회원가입");
		btnGoJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(container, JoinView.VIEW_NAME);
			}
		});
	}

	private void setup() {
		panelLogin.setLayout(null);
		container.add(panelLogin, BorderLayout.CENTER);

		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 19));
		lblNewLabel.setBounds(458, 217, 75, 35);
		panelLogin.add(lblNewLabel);

		tfId.setColumns(10);
		tfId.setBounds(556, 220, 175, 32);
		panelLogin.add(tfId);

		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(458, 265, 75, 33);
		panelLogin.add(lblNewLabel_1);

		pfPwd.setBounds(556, 273, 175, 25);
		panelLogin.add(pfPwd);

		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 19));
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setBounds(481, 337, 234, 53);
		panelLogin.add(btnLogin);

		btnGoJoin.setForeground(Color.WHITE);
		btnGoJoin.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 19));
		btnGoJoin.setBackground(Color.BLACK);
		btnGoJoin.setBounds(481, 400, 234, 53);
		panelLogin.add(btnGoJoin);
		
		lblNewLabel_2 = new JLabel("Car Summary");
		lblNewLabel_2.setFont(new Font("D2Coding", Font.BOLD, 56));
		lblNewLabel_2.setBounds(444, 37, 312, 113);
		panelLogin.add(lblNewLabel_2);
	} // setup

	private void verifyInput() {
		String id = tfId.getText().trim();
		if (id.length() == 0) {
			JOptionPane.showMessageDialog(frame, "아이디를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String pwd = String.valueOf(pfPwd.getPassword());
		if (pwd.length() == 0) {
			JOptionPane.showMessageDialog(frame, "비밀번호를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		boolean check = false;

		check = member2DAO.member2Login(id, pwd);

		if (check == true) {
			if (id.equals("admin")) {
				JOptionPane.showMessageDialog(frame, "관리자 계정입니다.", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
				cardLayout.show(container, MainView.VIEW_NAME);
			} else {

				JOptionPane.showMessageDialog(frame, "[ " + id + " ] 님 반갑습니다.", "로그인 성공",
						JOptionPane.INFORMATION_MESSAGE);

				SharedData.MAP.put("loginId", id);
				
				cardLayout.show(container, MainViewForMember.VIEW_NAME);
			}
		} else {
			JOptionPane.showMessageDialog(frame, "아이디와 비밀번호를 확인해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		tfId.setText("");
		pfPwd.setText("");
	}

	@Override
	public JPanel getView() {
		return panelLogin;
	}
}
