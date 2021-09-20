package com.example.app.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.example.app.CarManager;
import com.example.domain.CarVO;
import com.example.repository.CarDAO;
import com.example.repository.SharedData;

public class MainViewForMember implements Viewable {

	public static final String VIEW_NAME = "회원용 메인 화면";

	CarDAO carDAO = new CarDAO();

	private Vector<String> colName = new Vector<>();

	private JPanel panelMainForMember;
	private JSplitPane splitPane;
	private JPanel panel2;
	private JSplitPane splitPane_1;
	private JPanel panelSearch;
	private JComboBox comboBox;
	private JTextField tfSearch;
	private JButton btnSearch;
	private JPanel panel;
	private JSplitPane splitPane_2;
	private JPanel panelView;
	private JPanel panelBottom;
	private JPanel panelMember;
	private JLabel lblLoginId;
	private JButton btnLogout;
	private JButton btnTotal;
	private JTable table;
	private ImageIcon image;
	private JLabel lblImg;

	// 화면 이동시 cardLayout, container 필요함
	private CardLayout cardLayout;
	private Container container;
	private CarManager frame;

	public MainViewForMember(CardLayout cardLayout, Container container, CarManager frame) {
		this.cardLayout = cardLayout;
		this.container = container;
		this.frame = frame;
		
		init();
		setup();
	}

	private void init() {
		panelMainForMember = new JPanel();
		splitPane = new JSplitPane();
		panel2 = new JPanel();
		splitPane_1 = new JSplitPane();
		panel = new JPanel();
		splitPane_2 = new JSplitPane();
		panelView = new JPanel();
		panelView.setLayout(new BorderLayout(0, 0));
		panelBottom = new JPanel();
		panelMember = new JPanel();
		lblLoginId = new JLabel("");
		btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		btnTotal = new JButton("전체보기");
		btnTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				total();
			}
		});
		panelSearch = new JPanel();
		comboBox = new JComboBox();
		tfSearch = new JTextField();
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CarSearch();
			}
		});
	} // init

	private void setup() {
		container.add(panelMainForMember, BorderLayout.CENTER);
		panelMainForMember.setLayout(new BorderLayout(0, 0));

		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(30);
		panelMainForMember.add(splitPane, BorderLayout.CENTER);

		splitPane.setRightComponent(panel2);
		panel2.setLayout(new BorderLayout(0, 0));

		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setDividerLocation(680);
		panel2.add(splitPane_1, BorderLayout.CENTER);

		splitPane_1.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_2.setDividerLocation(250);
		panel.add(splitPane_2, BorderLayout.CENTER);

		splitPane_2.setLeftComponent(panelView);

		splitPane_2.setRightComponent(panelBottom);
		panelBottom.setLayout(new BorderLayout(0, 0));

		splitPane_1.setRightComponent(panelMember);
		panelMember.setLayout(null);

		lblLoginId.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 17));
		lblLoginId.setBounds(12, 9, 182, 18);
		panelMember.add(lblLoginId);

		btnLogout.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 17));
		btnLogout.setBounds(1050, 4, 120, 27);
		panelMember.add(btnLogout);

		btnTotal.setForeground(Color.WHITE);
		btnTotal.setBackground(Color.BLACK);
		btnTotal.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 17));
		btnTotal.setBounds(250, 4, 120, 27);
		panelMember.add(btnTotal);

		splitPane.setLeftComponent(panelSearch);
		panelSearch.setLayout(null);

		comboBox.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "카테고리..", "브랜드", "모델명" }));
		comboBox.setBounds(12, 2, 93, 23);
		panelSearch.add(comboBox);
		
		tfSearch.setBounds(106, 2, 955, 25);
		panelSearch.add(tfSearch);
		tfSearch.setColumns(10);

		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(Color.BLACK);
		btnSearch.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 17));
		btnSearch.setBounds(1065, 0, 111, 26);
		panelSearch.add(btnSearch);

		// 열 세팅
		colName.add("브랜드");
		colName.add("종류");
		colName.add("연식");
		colName.add("모델명");
		colName.add("배기량");
		colName.add("연료");
		colName.add("가격");
		colName.add("차량사진");
	} // setup

	ImageIcon imageSetSize(ImageIcon icon, int i, int j) {
		Image xImg = icon.getImage();
		Image yImg = xImg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyImg = new ImageIcon(yImg);
		return xyImg;
	}

	private void total() {
		panelView.removeAll();

		List<CarVO> list = carDAO.getCar();

		Vector<Vector<Object>> vector = getVectorFromList(list);

		table = new JTable(vector, colName);
		table.setRowHeight(50);

		table.getTableHeader().setReorderingAllowed(false); // 열 제목 위치 안바뀌게 설정

		// 셀 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}

		// 열을 숨김.
		table.getColumn("차량사진").setWidth(0);
		table.getColumn("차량사진").setMinWidth(0);
		table.getColumn("차량사진").setMaxWidth(0);

		panelView.add(new JScrollPane(table), BorderLayout.CENTER);

		frame.setVisible(true);

		table.addMouseListener(new MyMouseListener());

		String loginId = (String) SharedData.MAP.get("loginId");
		lblLoginId.setText(loginId + " 님 반갑습니다.");

		frame.setVisible(true);
	} // total

	private void CarSearch() {
		panelBottom.removeAll();

		int selectedIndex = comboBox.getSelectedIndex();
		if (selectedIndex == 0) {
			JOptionPane.showMessageDialog(frame, "검색항목을 선택하세요.", "검색 에러", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String word = tfSearch.getText();
		if (word.isEmpty()) { // word.equals("")
			JOptionPane.showMessageDialog(frame, "검색어를 입력하세요.", "검색 에러", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String category = ""; // "brand", "name"
		switch (selectedIndex) {
		case 1:
			category = "brand";
			break;
		case 2:
			category = "name";
			break;
		}

		panelView.removeAll();

		List<CarVO> list = carDAO.search(category, word);

		if (list.size() > 0) {
			Vector<Vector<Object>> vector = getVectorFromList(list);
			table = new JTable(vector, colName);
			table.setRowHeight(50);

			table.getTableHeader().setReorderingAllowed(false); // 열 제목 위치 안바뀌게 설정

			// 가운데 정렬
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(SwingConstants.CENTER);

			TableColumnModel tcm = table.getColumnModel();
			for (int i = 0; i < tcm.getColumnCount(); i++) {
				tcm.getColumn(i).setCellRenderer(dtcr);
			}

			// 열을 숨김.
			table.getColumn("차량사진").setWidth(0);
			table.getColumn("차량사진").setMinWidth(0);
			table.getColumn("차량사진").setMaxWidth(0);

			panelView.add(new JScrollPane(table), BorderLayout.CENTER);
			table.addMouseListener(new MyMouseListener());
			frame.setVisible(true);

			tfSearch.setText("");
		} else {
			JOptionPane.showMessageDialog(frame, "검색 결과가 없습니다.", "검색 에러", JOptionPane.ERROR_MESSAGE);
		}
	}

	private class MyMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn() + 4;
			String tableValue = (String) table.getValueAt(row, col); // 해당 셀의 값을 스트링으로 리턴받음
			panelBottom.removeAll(); // 다음 클릭한 사진을 불러오기 위해 패널 초기화
			
			image = new ImageIcon(tableValue);
			image = imageSetSize(image, 850, 350);
			lblImg = new JLabel(image);
			panelBottom.add(lblImg, BorderLayout.CENTER);

			frame.setVisible(true);
		}
	} // MyMouseListener

	private Vector<Vector<Object>> getVectorFromList(List<CarVO> list) {
		Vector<Vector<Object>> vector = new Vector<>();

		for (CarVO car : list) {
			Vector<Object> rowVector = new Vector<>();
			rowVector.add(car.getBrand());
			rowVector.add(car.getUtil());
			rowVector.add(car.getYear());
			rowVector.add(car.getName());
			rowVector.add(car.getCc() + " cc");
			rowVector.add(car.getOil());
			rowVector.add(car.getPrice());
			rowVector.add(car.getCarImg());

			vector.add(rowVector);
		}
		return vector;
	} // getVectorFromList
	
	private void logout() {
		String loginId = (String) SharedData.MAP.get("loginId");
		if(loginId == null) {
			System.out.println("로그인 상태가 아닙니다.");
			return;
		}		
		JOptionPane.showMessageDialog(frame, loginId + " 님 로그아웃 되었습니다.", "로그아웃", JOptionPane.INFORMATION_MESSAGE);
		SharedData.MAP.remove(loginId);
		
		cardLayout.show(container, LoginView.VIEW_NAME);
	}

	@Override
	public JPanel getView() {
		return panelMainForMember;
	}

}
