package com.example.app;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.plaf.DimensionUIResource;

import com.example.app.view.JoinView;
import com.example.app.view.LoginView;
import com.example.app.view.MainView;
import com.example.app.view.MainViewForMember;

public class CarManager extends JFrame {

	private LoginView loginView;
	private JoinView joinView;
	private MainViewForMember mainViewForMember;
	private MainView mainView;
	
	public CarManager() {
		super("Car Summary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(false);
		setSize(1200, 800);
		setPreferredSize(new DimensionUIResource(1200, 800));

		setupView();

		setResizable(false);
		setVisible(true);
	}

	private void setupView() {
		Container c = getContentPane();

		CardLayout cardLayout = new CardLayout();
		c.setLayout(cardLayout);

		loginView = new LoginView(cardLayout, c, this);
		c.add(loginView.getView(), LoginView.VIEW_NAME);

		joinView = new JoinView(cardLayout, c, this);
		c.add(joinView.getView(), JoinView.VIEW_NAME);

		mainViewForMember = new MainViewForMember(cardLayout, c, this);
		c.add(mainViewForMember.getView(), MainViewForMember.VIEW_NAME);

		mainView = new MainView(cardLayout, c, this);
		c.add(mainView.getView(), MainView.VIEW_NAME);

		cardLayout.show(c, LoginView.VIEW_NAME);
		this.pack();
	} // setupView

	public static void main(String[] args) {
		new CarManager();
	}

}
