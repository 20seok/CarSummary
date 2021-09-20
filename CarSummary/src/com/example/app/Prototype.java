package com.example.app;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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

import com.example.domain.CarVO;
import com.example.repository.CarDAO;
import com.example.repository.FileTypeFilter;
import java.awt.Color;
import javax.swing.UIManager;

public class Prototype extends JFrame {

	CarDAO carDAO = new CarDAO();

	private Vector<String> colName = new Vector<>();
	
	private JSplitPane splitPane;
	private JPanel panelLeft;
	private JLabel lblBrand;
	private JTextField tfBrand;
	private JLabel lblName;
	private JTextField tfName;
	private JLabel lblYear;
	private JTextField tfYear;
	private JLabel lblCc;
	private JTextField tfCc;
	private JLabel lblPrice;
	private JTextField tfPrice;
	private JLabel lblOil;
	private JComboBox cbOil;
	private JLabel lblUtil;
	private JComboBox cbUtil;
	private JButton btnTotal;
	private JButton btnAdd;
	private JPanel panel;
	private JSplitPane splitPane_1;
	private JPanel panelMain;
	private JPanel panelBottom;
	private JLabel lblImg;
	private JTable table;
	private JButton btnModify;
	private JButton btnRemove;
	private ImageIcon image;
	private JButton btnImgLoad;
	private JTextField tfImgLoad;
	private JSplitPane splitPane_2;
	private JComboBox cbSearch;
	private JPanel panel_1;
	private JTextField tfSearch;
	private JButton btnSearch;

	ImageIcon imageSetSize(ImageIcon icon, int i, int j) {
		Image xImg = icon.getImage();
		Image yImg = xImg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyImg = new ImageIcon(yImg);
		return xyImg;
	}

	public Prototype() {
		super("Car Summary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(false);
		setSize(1200, 800);

		Container c = getContentPane();

		splitPane = new JSplitPane();

		c.add(splitPane, BorderLayout.CENTER);

		init();
		setup();
		total();

		setVisible(true);
	}

	public void init() {
		panelLeft = new JPanel();
		lblBrand = new JLabel("브랜드");
		tfBrand = new JTextField();
		lblName = new JLabel("모델");
		tfName = new JTextField();
		lblYear = new JLabel("연식");
		tfYear = new JTextField();
		lblCc = new JLabel("배기량");
		tfCc = new JTextField();
		lblPrice = new JLabel("가격");
		tfPrice = new JTextField();
		lblOil = new JLabel("연료");
		cbOil = new JComboBox();
		lblUtil = new JLabel("종류");
		cbUtil = new JComboBox();
		btnTotal = new JButton("전체보기");
		btnTotal.setBackground(Color.BLACK);
		btnTotal.setForeground(Color.WHITE);
		btnAdd = new JButton("등록");
		btnAdd.setBackground(Color.BLACK);
		btnAdd.setForeground(Color.WHITE);
		btnModify = new JButton("수정");
		btnModify.setBackground(Color.BLACK);
		btnModify.setForeground(Color.WHITE);
		btnRemove = new JButton("삭제");
		btnRemove.setBackground(Color.BLACK);
		btnRemove.setForeground(Color.WHITE);
		btnImgLoad = new JButton("이미지..");
		btnImgLoad.setBackground(UIManager.getColor("Button.background"));
		btnImgLoad.setForeground(Color.BLACK);
		splitPane_1 = new JSplitPane();
		panelMain = new JPanel();
		panelBottom = new JPanel();
		splitPane_2 = new JSplitPane();
		cbSearch = new JComboBox();
		panel_1 = new JPanel();
		tfSearch = new JTextField();
		btnSearch = new JButton("검색");
		btnSearch.setBackground(Color.BLACK);
		btnSearch.setForeground(Color.WHITE);
		tfImgLoad = new JTextField();
		panel = new JPanel();
	} // init

	public void setup() {
		splitPane.setLeftComponent(panelLeft);
		splitPane.setDividerLocation(250);
		panelLeft.setLayout(null);

		lblBrand.setBounds(12, 29, 53, 21);
		lblBrand.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		panelLeft.add(lblBrand);

		tfBrand.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		tfBrand.setBounds(12, 50, 225, 36);
		panelLeft.add(tfBrand);
		tfBrand.setColumns(10);

		lblName.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		lblName.setBounds(12, 109, 53, 21);
		panelLeft.add(lblName);

		tfName.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		tfName.setColumns(10);
		tfName.setBounds(12, 130, 225, 36);
		panelLeft.add(tfName);

		lblYear.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		lblYear.setBounds(12, 188, 53, 21);
		panelLeft.add(lblYear);

		tfYear.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		tfYear.setColumns(10);
		tfYear.setBounds(12, 209, 225, 36);
		panelLeft.add(tfYear);

		lblCc.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		lblCc.setBounds(12, 272, 53, 21);
		panelLeft.add(lblCc);

		tfCc.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		tfCc.setColumns(10);
		tfCc.setBounds(12, 293, 225, 36);
		panelLeft.add(tfCc);

		lblPrice.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		lblPrice.setBounds(12, 461, 53, 21);
		panelLeft.add(lblPrice);

		tfPrice.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		tfPrice.setColumns(10);
		tfPrice.setBounds(12, 482, 225, 36);
		panelLeft.add(tfPrice);

		lblOil.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		lblOil.setBounds(12, 402, 53, 21);
		panelLeft.add(lblOil);

		cbOil.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		cbOil.setModel(new DefaultComboBoxModel(new String[] { "연료선택..", "가솔린", "디젤", "전기" }));
		cbOil.setBounds(12, 422, 225, 27);
		panelLeft.add(cbOil);

		lblUtil.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		lblUtil.setBounds(12, 341, 53, 21);
		panelLeft.add(lblUtil);

		cbUtil.setModel(new DefaultComboBoxModel(new String[] { "종류선택..", "세단", "SUV", "스포츠카" }));
		cbUtil.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		cbUtil.setBounds(12, 363, 225, 27);
		panelLeft.add(cbUtil);

		btnTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				total();
			}
		});
		btnTotal.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		btnTotal.setBounds(12, 566, 225, 36);
		panelLeft.add(btnTotal);

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		btnAdd.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		btnAdd.setBounds(12, 614, 225, 36);
		panelLeft.add(btnAdd);

		btnModify.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modify();
			}
		});
		btnModify.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		btnModify.setBounds(12, 662, 225, 36);
		panelLeft.add(btnModify);

		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		btnRemove.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
		btnRemove.setBounds(12, 710, 225, 36);
		panelLeft.add(btnRemove);

		btnImgLoad.setFont(new Font("Dialog", Font.PLAIN, 13));

		// JFileChooser 코드
		btnImgLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileChooser();
			}
		});
		btnImgLoad.setBounds(158, 526, 79, 28);
		panelLeft.add(btnImgLoad);

		tfImgLoad.setBounds(12, 530, 146, 22);
		panelLeft.add(tfImgLoad);
		tfImgLoad.setColumns(10);

		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setDividerLocation(400);
		panel.add(splitPane_1, BorderLayout.CENTER);

		splitPane_1.setLeftComponent(panelMain);
		panelMain.setLayout(new BorderLayout(0, 0));

		splitPane_1.setRightComponent(panelBottom);
		panelBottom.setLayout(new BorderLayout(0, 0));

		panel.add(splitPane_2, BorderLayout.NORTH);

		cbSearch.setFont(new Font("Dialog", Font.PLAIN, 16));
		cbSearch.setModel(new DefaultComboBoxModel(new String[] { "카테고리..", "브랜드", "모델명" }));
		splitPane_2.setLeftComponent(cbSearch);

		splitPane_2.setRightComponent(panel_1);
		panel_1.setLayout(null);

		tfSearch.setBounds(0, 0, 702, 32);
		panel_1.add(tfSearch);
		tfSearch.setColumns(10);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CarSearch();
			}
		});
		btnSearch.setFont(new Font("Dialog", Font.PLAIN, 17));
		btnSearch.setBounds(701, 2, 110, 28);
		panel_1.add(btnSearch);

		colName.add("브랜드");
		colName.add("종류");
		colName.add("연식");
		colName.add("모델명");
		colName.add("배기량");
		colName.add("연료");
		colName.add("가격");
		colName.add("차량사진");
	} // setup

	// 이벤트 후 텍스트 필드의 입력값을 초기화
	private void tfClear() {
		tfBrand.setText("");
		tfName.setText("");
		tfYear.setText("");
		tfCc.setText("");
		tfPrice.setText("");
		tfImgLoad.setText("");
		tfSearch.setText("");
		cbUtil.setSelectedIndex(0);
		cbOil.setSelectedIndex(0);
		cbSearch.setSelectedIndex(0);
	} // tfClear();

	private void total() {
		panelMain.removeAll();

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

		panelMain.add(new JScrollPane(table), BorderLayout.CENTER);

		setVisible(true);

		table.addMouseListener(new MyMouseListener());

		setVisible(true);
	} // total

	private class MyMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn() + 4;
			String tableValue = (String) table.getValueAt(row, col); // 해당 셀의 값을 스트링으로 리턴받음
			panelBottom.removeAll(); // 다음 클릭한 사진을 불러오기 위해 패널 초기화
			image = new ImageIcon(tableValue);

			image = imageSetSize(image, 950, 400);
			lblImg = new JLabel(image);
			panelBottom.add(lblImg, BorderLayout.CENTER);

			setVisible(true);
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

	private void add() {
		int selectedIndexUtil = cbUtil.getSelectedIndex();

		String fieldUtil = "";
		switch (selectedIndexUtil) {
		case 1:
			fieldUtil = "세단";
			break;
		case 2:
			fieldUtil = "SUV";
			break;
		case 3:
			fieldUtil = "스포츠카";
			break;
		}

		int selectedIndexOil = cbOil.getSelectedIndex();

		String fieldOil = "";
		switch (selectedIndexOil) {
		case 1:
			fieldOil = "가솔린";
			break;
		case 2:
			fieldOil = "디젤";
			break;
		case 3:
			fieldOil = "전기";
			break;
		}

		String inputName = tfName.getText();
		int count = carDAO.getCountByName(inputName);
		if (count > 0) {
			JOptionPane.showMessageDialog(Prototype.this, "[ " + inputName + " ] 는 이미 등록되어있는 모델입니다.", "등록 에러",
					JOptionPane.ERROR_MESSAGE);
			tfName.setText("");
			tfName.requestFocus(true);
		} else {

			CarVO carVO = new CarVO();

			carVO.setBrand(tfBrand.getText());
			carVO.setName(tfName.getText());
			carVO.setUtil(fieldUtil);
			carVO.setYear(Integer.valueOf(tfYear.getText()));
			carVO.setCc(Integer.valueOf(tfCc.getText()));
			carVO.setOil(fieldOil);
			carVO.setPrice(tfPrice.getText());
			carVO.setCarImg(tfImgLoad.getText());

			carDAO.insertCar(carVO);

			JOptionPane.showMessageDialog(Prototype.this, "[ " + inputName + " ] 가 등록되었습니다.", "등록 성공",
					JOptionPane.INFORMATION_MESSAGE);

			total();
			tfClear();
		}
	} // add

	private void modify() {

		panelBottom.removeAll();

		int selectedIndexUtil = cbUtil.getSelectedIndex();

		String fieldUtil = "";
		switch (selectedIndexUtil) {
		case 1:
			fieldUtil = "세단";
			break;
		case 2:
			fieldUtil = "SUV";
			break;
		case 3:
			fieldUtil = "스포츠카";
			break;
		}

		int selectedIndexOil = cbOil.getSelectedIndex();

		String fieldOil = "";
		switch (selectedIndexOil) {
		case 1:
			fieldOil = "가솔린";
			break;
		case 2:
			fieldOil = "디젤";
			break;
		}

		CarVO carVO = new CarVO();

		carVO.setBrand(tfBrand.getText());
		carVO.setUtil(fieldUtil);
		carVO.setYear(Integer.valueOf(tfYear.getText()));
		carVO.setCc(Integer.valueOf(tfCc.getText()));
		carVO.setOil(fieldOil);
		carVO.setPrice(tfPrice.getText());
		carVO.setCarImg(tfImgLoad.getText());
		carVO.setName(tfName.getText());

		String inputName = tfName.getText();
		int result = JOptionPane.showConfirmDialog(Prototype.this, "[ " + inputName + " ] 를 수정하시겠습니까?", "수정",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.NO_OPTION || result == JOptionPane.CANCEL_OPTION) {
			tfClear();
			return;
		} else {
			carDAO.updateCar(carVO);

			JOptionPane.showMessageDialog(Prototype.this, "[ " + inputName + " ] 수정 성공!!", "수정 성공",
					JOptionPane.INFORMATION_MESSAGE);

			tfClear();
			btnTotal.doClick();
		}
	} // modify

	private void remove() {
		String inputName = tfName.getText();
		int result = JOptionPane.showConfirmDialog(Prototype.this, "[ " + inputName + " ] 를 삭제하시겠습니까?", "삭제",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.NO_OPTION || result == JOptionPane.CANCEL_OPTION) {
			tfClear();
			return;
		} else {
			String name = tfName.getText();
			int year = Integer.valueOf(tfYear.getText());

			carDAO.removeCar(name, year); // 모델명과 연식을 기준으로 데이터 삭제

			JOptionPane.showMessageDialog(Prototype.this, "[ " + inputName + " ] 삭제 성공!!", "삭제 성공",
					JOptionPane.INFORMATION_MESSAGE);

			tfClear();
			btnTotal.doClick();
		}

		total();
		tfClear();
	} // remove

	private void CarSearch() {
		panelBottom.removeAll();

		int selectedIndex = cbSearch.getSelectedIndex();
		if (selectedIndex == 0) {
			JOptionPane.showMessageDialog(Prototype.this, "검색항목을 선택하세요.", "검색 에러", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String word = tfSearch.getText();
		if (word.isEmpty()) { // word.equals("")
			JOptionPane.showMessageDialog(Prototype.this, "검색어를 입력하세요.", "검색 에러", JOptionPane.ERROR_MESSAGE);
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

		panelMain.removeAll();
		tfClear();
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

			panelMain.add(new JScrollPane(table), BorderLayout.CENTER);
			table.addMouseListener(new MyMouseListener());
			setVisible(true);
			tfClear();
		} else {
			JOptionPane.showMessageDialog(Prototype.this, "검색 결과가 없습니다.", "검색 에러", JOptionPane.ERROR_MESSAGE);
		}
	} // CarSearch

	private void FileChooser() {
		JFileChooser fs = new JFileChooser(new File("C:\\Users\\S. H. LEE\\Desktop\\imgCar"));
		fs.setDialogTitle("Open a File");
		fs.setFileFilter(new FileTypeFilter(".jpg", "JPG File"));
		int result = fs.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fs.getSelectedFile();
			tfImgLoad.setText(file.getPath()); // tfImgLoad에 파일 경로를 세팅
		}
	} // FileChooser

	public static void main(String[] args) {
		new Prototype();
	}

}
