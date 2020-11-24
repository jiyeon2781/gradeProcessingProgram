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

class windowAdd extends JFrame { //�����ڸ� �߰��ϴ� Ŭ����

	JFrame frame;
	String message;
	private static JTextField name, join_year, num, mileage;

	private JButton Addwin;
	private String Wnumber, Wname, Wmileage, Wjoin_year;

	windowAdd() {
		setTitle("������ �߰� ����");
		GridLayout grid = new GridLayout(9, 1);
		grid.setVgap(3);
		setLayout(grid);

		add(new JLabel("������ȣ : "));
		num = new JTextField("");
		add(num);
		add(new JLabel("�̸� : "));
		name = new JTextField("");
		add(name);
		add(new JLabel("���ϸ��� : "));
		mileage = new JTextField("");
		add(mileage);
		add(new JLabel("���Կ��� : "));
		join_year = new JTextField("");
		add(join_year);
		Addwin = new JButton("�߰��ϱ�");
		add(Addwin);
		Addwin.addActionListener(new ActionListener() { //�߰��ϱ� ��ư�� ������
			public void actionPerformed(ActionEvent e) {
				Wnumber = numtext();
				Wname = nametext();
				Wmileage = mileagetext();
				Wjoin_year = join_yeartext(); //textfield�� �ִ� ������ �޾�
				if (Wnumber.equals("")) {
					showMessage("������ȣ�� �Է����ּ���.");
				}
				if (Wname.equals("")) {
					showMessage("�̸��� �Է����ּ���.");
				}
				if (Wmileage.equals("")) {
					showMessage("���ϸ����� �Է����ּ���.");
				}
				if (Wjoin_year.equals("")) {
					showMessage("���Կ����� �Է����ּ���.");
				} else {
					try {
						FileWriter fw = new FileWriter("waitingList.txt", true); //���Ͽ� �̾
						BufferedWriter bf = new BufferedWriter(fw);
						bf.write(Wnumber + "\t" + Wname + "\t" + Wmileage + "\t" + Wjoin_year + "\r\n");
						showMessage("�߰��Ǿ����ϴ�. ���Ź�ư�� �����ּ���!");
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
		JOptionPane.showMessageDialog(frame, message, "�޼���", JOptionPane.INFORMATION_MESSAGE);

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
