package com.example.app.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.example.app.CarManager;
import com.example.domain.Member2VO;
import com.example.repository.Member2DAO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class JoinView implements Viewable {
	
	public static final String VIEW_NAME = "회원가입 화면";
	
	Member2DAO member2DAO = new Member2DAO();
	
	private CardLayout cardLayout;
	private Container container;
	private CarManager frame;
	
	private JPanel panelJoin;
	private JLabel lblNewLabel;
	private JTextField tfId;
	private JLabel lblNewLabel_1;
	private JPasswordField pfPwd;
	private JLabel lblNewLabel_1_1;
	private JPasswordField pfPwdCheck;
	private JLabel lblNewLabel_2;
	private JTextField tfName;
	private JButton btnJoin;
	private JLabel lblIdCheck;
	private JLabel lblPwdRight;
	private JLabel lblNewLabel_3;
	
	
	public JoinView(CardLayout cardLayout, Container container, CarManager frame) {
		this.cardLayout = cardLayout;
		this.container = container;
		this.frame = frame;

		init();
		setup();

		frame.setVisible(true);
	}
	private void init() {
		panelJoin = new JPanel();
		lblNewLabel = new JLabel("아이디");
		tfId = new JTextField();
		tfId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				idCheck();
			}
		});
		lblNewLabel_1 = new JLabel("비밀번호");
		pfPwd = new JPasswordField();
		lblNewLabel_1_1 = new JLabel("비밀번호 재확인");
		pfPwdCheck = new JPasswordField();
		pfPwdCheck.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				pwdCheck();
			}
		});
		lblNewLabel_2 = new JLabel("이름");
		tfName = new JTextField();
		btnJoin = new JButton("가입하기");
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				join();
			}
		});
		lblIdCheck = new JLabel("");
		lblPwdRight = new JLabel("");
	}
	private void setup() {
		panelJoin.setLayout(null);
		container.add(panelJoin, BorderLayout.CENTER);
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 17));
		lblNewLabel.setBounds(471, 193, 75, 35);
		panelJoin.add(lblNewLabel);
		
		tfId.setColumns(10);
		tfId.setBounds(569, 196, 175, 32);
		panelJoin.add(tfId);
		
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(471, 240, 75, 33);
		panelJoin.add(lblNewLabel_1);
		
		pfPwd.setBounds(569, 248, 175, 25);
		panelJoin.add(pfPwd);
		
		lblNewLabel_1_1.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(440, 284, 112, 25);
		panelJoin.add(lblNewLabel_1_1);
		
		pfPwdCheck.setBounds(569, 285, 175, 25);
		panelJoin.add(pfPwdCheck);
		
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(469, 322, 75, 32);
		panelJoin.add(lblNewLabel_2);
		
		tfName.setColumns(10);
		tfName.setBounds(567, 320, 175, 34);
		panelJoin.add(tfName);
		
		btnJoin.setForeground(Color.WHITE);
		btnJoin.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 18));
		btnJoin.setBackground(Color.BLACK);
		btnJoin.setBounds(494, 373, 248, 35);
		panelJoin.add(btnJoin);
		
		lblIdCheck.setBounds(569, 175, 175, 18);
		panelJoin.add(lblIdCheck);
		
		
		lblPwdRight.setFont(new Font("Dialog", Font.BOLD, 19));
		lblPwdRight.setBounds(746, 285, 40, 18);
		panelJoin.add(lblPwdRight);
		
		lblNewLabel_3 = new JLabel("Car Summary");
		lblNewLabel_3.setFont(new Font("D2Coding", Font.BOLD, 56));
		lblNewLabel_3.setBounds(471, 29, 320, 134);
		panelJoin.add(lblNewLabel_3);
	} // setup
	
	private void join() {
		String id = tfId.getText().trim();
		// 아이디 입력값 없을때
		if(id.length() == 0) {
			JOptionPane.showMessageDialog(frame, "아이디를 입력해주세요.", "에러", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Member2VO member2 = new Member2VO();
		member2.setId(tfId.getText());
		member2.setPwd(String.valueOf(pfPwd.getPassword()));
		member2.setName(tfName.getText());
		member2.setRegDate(new Timestamp(System.currentTimeMillis()));
		
		member2DAO.member2Insert(member2);
		
		JOptionPane.showMessageDialog(frame, "[ " + tfId.getText() + " ] 님 회원가입 완료되었습니다.", "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
		
		cardLayout.show(container, LoginView.VIEW_NAME);
	}
	private void idCheck() {
		String joinId = tfId.getText().trim();
		int count = member2DAO.getCountById(joinId);
		if(count > 0) {
			lblIdCheck.setText("이미 사용중인 아이디입니다.");
			lblIdCheck.setForeground(Color.RED);
			btnJoin.setEnabled(false);
		} else {
			lblIdCheck.setText("사용 가능한 아이디입니다.");
			lblIdCheck.setForeground(Color.GREEN);
			btnJoin.setEnabled(true);
		}
		
	} // idCheck
	
	private void pwdCheck() {
		String joinPwd = String.valueOf(pfPwd.getPassword());
		String joinPwdCheck = String.valueOf(pfPwdCheck.getPassword());
		if(joinPwdCheck.equals(joinPwd)) {
			lblPwdRight.setText("O");
			lblPwdRight.setForeground(Color.GREEN);
			btnJoin.setEnabled(true);
		} else {
			lblPwdRight.setText("X");
			lblPwdRight.setForeground(Color.RED);
			btnJoin.setEnabled(false);	
		}
	} // pwdCheck
	
	@Override
	public JPanel getView() {
		return panelJoin;
	}
}
