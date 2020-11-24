package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import function.CustomerVector;
import gui.ListGui;

class windowAdd extends JFrame { //예약자를 추가하는 클래스

	JFrame frame;
	String message;
	private static JTextField name, join_year, num, mileage;

	private JButton Addwin;
	private String Wnumber, Wname, Wmileage, Wjoin_year;

	windowAdd() {
		setTitle("예약자 추가 정보");
		GridLayout grid = new GridLayout(9, 1);
		grid.setVgap(3);
		setLayout(grid);

		add(new JLabel("접수번호 : "));
		num = new JTextField("");
		add(num);
		add(new JLabel("이름 : "));
		name = new JTextField("");
		add(name);
		add(new JLabel("마일리지 : "));
		mileage = new JTextField("");
		add(mileage);
		add(new JLabel("가입연도 : "));
		join_year = new JTextField("");
		add(join_year);
		Addwin = new JButton("추가하기");
		add(Addwin);
		Addwin.addActionListener(new ActionListener() { //추가하기 버튼을 누르면
			public void actionPerformed(ActionEvent e) {
				Wnumber = numtext();
				Wname = nametext();
				Wmileage = mileagetext();
				Wjoin_year = join_yeartext(); //textfield에 있는 정보를 받아
				if (Wnumber.equals("")) {
					showMessage("접수번호를 입력해주세요.");
				}
				if (Wname.equals("")) {
					showMessage("이름을 입력해주세요.");
				}
				if (Wmileage.equals("")) {
					showMessage("마일리지를 입력해주세요.");
				}
				if (Wjoin_year.equals("")) {
					showMessage("가입연도를 입력해주세요.");
				} else {
					try {
						FileWriter fw = new FileWriter("waitingList.txt", true); //파일에 이어씀
						BufferedWriter bf = new BufferedWriter(fw);
						bf.write(Wnumber + "\t" + Wname + "\t" + Wmileage + "\t" + Wjoin_year + "\r\n");
						showMessage("추가되었습니다. 갱신버튼을 눌러주세요!");
						bf.close();
					} catch (Exception e2) {
						System.out.println(e2);
					}
					dispose();

				}

			}
		});

		setSize(250, 300);
		setResizable(false);
		setVisible(true);

	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(frame, message, "메세지", JOptionPane.INFORMATION_MESSAGE);

	}

	public static String numtext() {
		return num.getText();
	}

	public static String nametext() {
		return name.getText();
	}

	public static String mileagetext() {
		return mileage.getText().toString();
	}

	public static String join_yeartext() {
		return join_year.getText().toString();
	}
}
