package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import function.AddCal;
import function.GradeCal;
import function.SaveStuInfo;
import function.Sort;
import function.StuInfoVector;

public class ListGui extends JFrame implements ActionListener {

	private JPanel panel;
	private DefaultTableModel dtm;
	private JTable table;
	private JScrollPane scrollPane;
	private JMenuBar menuB;
	private JMenu file, sort;
	private JMenuItem save, open, refresh, number, name, average;
	private JButton add, delete, search, exit;
	private JLabel label;
	private JFileChooser jfc;
	private int output;
	private File of;
	private static Vector<StuInfoVector> siv = new Vector<StuInfoVector>();
	private static Vector<StuInfoVector> saveVec = new Vector<StuInfoVector>();
	private Vector<StuInfoVector> sv;
	private StuInfoVector std;
	private String[] data = new String[8];
	private int exitsave;
	private double midAver, finAver, homeAver, totalAver, aver;
	private DecimalFormat fm = new DecimalFormat(".##");
	private boolean openstart;
	private boolean fileRange = true;

	public ListGui() {
		super("���� ó�� ���α׷�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 657);

		menuB = new JMenuBar();
		file = new JMenu("����");
		file.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
		sort = new JMenu("����");
		sort.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
		menuB.add(file);
		menuB.add(sort);

		open = new JMenuItem("�ҷ�����");
		open.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
		open.addActionListener(this);
		save = new JMenuItem("�����ϱ�");
		save.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
		save.addActionListener(this);
		refresh = new JMenuItem("���ΰ�ħ");
		refresh.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
		refresh.addActionListener(this);
		number = new JMenuItem("�й�");
		number.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
		number.addActionListener(this);
		name = new JMenuItem("�̸�");
		name.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
		name.addActionListener(this);
		average = new JMenuItem("���");
		average.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
		average.addActionListener(this);

		file.add(open);
		file.addSeparator();
		file.add(save);
		file.addSeparator();
		file.add(refresh);
		sort.add(number);
		sort.addSeparator();
		sort.add(name);
		sort.addSeparator();
		sort.add(average);

		setJMenuBar(menuB);

		panel = new JPanel();
		panel.setLayout(null);
		setContentPane(panel);

		add = new JButton("�߰�");
		add.setFont(new Font("���� ��� Semilight", Font.BOLD, 15));
		add.setBounds(88, 63, 73, 35);
		add.addActionListener(this);
		panel.add(add);

		delete = new JButton("����");
		delete.setFont(new Font("���� ��� Semilight", Font.BOLD, 15));
		delete.setBounds(188, 63, 73, 35);
		delete.addActionListener(this);
		panel.add(delete);

		search = new JButton("�˻�");
		search.setFont(new Font("���� ��� Semilight", Font.BOLD, 15));
		search.setBounds(288, 63, 73, 35);
		search.addActionListener(this);
		panel.add(search);

		exit = new JButton("����");
		exit.setFont(new Font("���� ��� Semilight", Font.BOLD, 15));
		exit.setBounds(388, 63, 73, 35);
		exit.addActionListener(this);
		panel.add(exit);

		label = new JLabel("�ݿ��������б� ���� ó�� �ý���");
		label.setFont(new Font("�޸ո���T", Font.PLAIN, 26));
		label.setBounds(103, 10, 341, 43);
		panel.add(label);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 112, 530, 469);
		getContentPane().add(scrollPane);

		dtm = new DefaultTableModel(0, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		table = new JTable(dtm);
		scrollPane.setViewportView(table);

		dtm.addColumn("�й�");
		dtm.addColumn("�̸�");
		dtm.addColumn("�߰�����");
		dtm.addColumn("�⸻����");
		dtm.addColumn("��������");
		dtm.addColumn("����");
		dtm.addColumn("���");
		dtm.addColumn("����");
		row();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o == open) {
			openstart = true;
			while(openstart) {
				jfc = new JFileChooser();
				jfc.setDialogTitle("Please select a excel file to import");
				output = jfc.showOpenDialog(this);
				

				if (output == JFileChooser.APPROVE_OPTION) {
					siv.removeAllElements();
					of = jfc.getSelectedFile();
					siv = GradeCal.gradecal(of);
					if (siv.size() < 10) {
						JOptionPane.showMessageDialog(this, "�л� ���� 10�� �̸��Դϴ�. �� �߰����ּ���!", "Warning!", JOptionPane.INFORMATION_MESSAGE);
						continue;
					}
					for(int i = 0; i < siv.size();i++) {
						if(siv.elementAt(i).midScore > 100 || siv.elementAt(i).midScore < 0 ||siv.elementAt(i).finScore > 100 || siv.elementAt(i).finScore < 0 ||
								siv.elementAt(i).homeScore > 100 || siv.elementAt(i).homeScore < 0) {
							JOptionPane.showMessageDialog(this, "���� ������ �Ѿ���ϴ�. ������ �����Ͻðų� �ٸ������� �Է����ּ���!", "Warning!", JOptionPane.INFORMATION_MESSAGE);
							fileRange = false;
							break;
						}
					}
					if(fileRange == false) {
						fileRange = true;
						continue;
						
					}
					Collections.sort(siv, new Sort.numberSort());
					row();
					averCal();
					openstart = false;
				}
				else if(output == JFileChooser.CANCEL_OPTION) {
					openstart = false;
				}
			}

		}

		else if (o == save) {
			jfc = new JFileChooser();
			jfc.setDialogTitle("Please select a excel file to export");
			output = jfc.showSaveDialog(this);
			if (output == JFileChooser.APPROVE_OPTION) {
				of = jfc.getSelectedFile();
				String mainMid = fm.format(midAver / siv.size());
				String mainFin = fm.format(finAver / siv.size());
				String mainHome = fm.format(homeAver / siv.size());
				String mainTo = fm.format(totalAver / siv.size());
				String mainAver = fm.format(aver / siv.size());
				std = new StuInfoVector("","",Double.parseDouble(mainMid),Double.parseDouble(mainFin),Double.parseDouble(mainHome),Double.parseDouble(mainTo),Double.parseDouble(mainAver),"");
				saveVec.add(std);
				SaveStuInfo.save(of, siv,saveVec);
				saveVec.remove(saveVec.size()-1);
				JOptionPane.showMessageDialog(this, "����Ǿ����ϴ�!", "Save successful!", JOptionPane.INFORMATION_MESSAGE);
			}

		} else if (o == number) {
			Collections.sort(siv, new Sort.numberSort());
			row();
			averCal();
		} else if (o == name) {
			Collections.sort(siv, new Sort.nameSort());
			row();
			averCal();
		} else if (o == average) {
			Collections.sort(siv, new Sort.averageSort());
			row();
			averCal();
		}

		else if (o == refresh) {
			row();
			averCal();
		}

		else if (o == add) {
			new AddGui();
			row();
			averCal();

		} else if (o == delete) {
			delete();
		} else if (o == search) {
			search();
		} else if (o == exit) {
			exitsave = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?", "Do you want save?", JOptionPane.YES_NO_OPTION);

			if (exitsave == JOptionPane.YES_OPTION) {
				jfc = new JFileChooser();
				jfc.setDialogTitle("Please select a text file to export");
				output = jfc.showSaveDialog(this);
				if (output == JFileChooser.APPROVE_OPTION) {
					of = jfc.getSelectedFile();
					String mainMid = fm.format(midAver / siv.size());
					String mainFin = fm.format(finAver / siv.size());
					String mainHome = fm.format(homeAver / siv.size());
					String mainTo = fm.format(totalAver / siv.size());
					String mainAver = fm.format(aver / siv.size());
					std = new StuInfoVector("","",Double.parseDouble(mainMid),Double.parseDouble(mainFin),Double.parseDouble(mainHome),Double.parseDouble(mainTo),Double.parseDouble(mainAver),"");
					saveVec.add(std);
					SaveStuInfo.save(of, siv,saveVec);
					saveVec.remove(saveVec.size()-1);
					JOptionPane.showMessageDialog(this, "����Ǿ����ϴ�! �����ϰڽ��ϴ�.", "Save successful!",
							JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
			} else if (exitsave == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(this, "�����ϰڽ��ϴ�", "Bye", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}

	}

	public void delete() {
		int index = table.getSelectedRow();
		if (index < 0) {
			JOptionPane.showMessageDialog(this, "������ ���� �������ּ���!", "Warning!", JOptionPane.INFORMATION_MESSAGE);
		}

		else if (index == siv.size()) {
			JOptionPane.showMessageDialog(this, "����� ������ �� �����ϴ�!", "Warning!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			siv.remove(index);
			AddCal.addcal(siv);
			Collections.sort(siv, new Sort.numberSort());
			row();
			averCal();

		}

	}

	public void search() {
		String name = JOptionPane.showInputDialog("ã�� �̸��� �Է����ּ���");
		sv = new Vector<StuInfoVector>();
		if (name != null) {
			for (int i = 0; i < siv.size(); i++) {
				if (name.equals(siv.elementAt(i).name)) {
					std = new StuInfoVector(siv.elementAt(i).num, siv.elementAt(i).name, siv.elementAt(i).midScore,
							siv.elementAt(i).finScore, siv.elementAt(i).homeScore, siv.elementAt(i).total,
							siv.elementAt(i).average, siv.elementAt(i).grade);
					sv.add(std);
				}
			}
			if (sv.size() == 0) {
				JOptionPane.showMessageDialog(this, "ã���ô� �л��� �����ϴ�!", "Warning!", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "�л��� �����մϴ�. ��ü����� ������ ���ΰ�ħ�� �����ּ���!", "Complete!",
						JOptionPane.INFORMATION_MESSAGE);
				dtm.setNumRows(0);
				for (int i = 0; i < sv.size(); i++) {
					data[0] = Integer.toString(sv.elementAt(i).num);
					data[1] = sv.elementAt(i).name;
					data[2] = Integer.toString(sv.elementAt(i).midScore);
					data[3] = Integer.toString(sv.elementAt(i).finScore);
					data[4] = Integer.toString(sv.elementAt(i).homeScore);
					data[5] = Integer.toString(sv.elementAt(i).total);
					data[6] = Double.toString(sv.elementAt(i).average);
					data[7] = sv.elementAt(i).grade;
					dtm.addRow(data);
				}
			}

		}
	}

	public void row() {
		dtm.setNumRows(0);
		for (int i = 0; i < siv.size(); i++) {
			data[0] = Integer.toString(siv.elementAt(i).num);
			data[1] = siv.elementAt(i).name;
			data[2] = Integer.toString(siv.elementAt(i).midScore);
			data[3] = Integer.toString(siv.elementAt(i).finScore);
			data[4] = Integer.toString(siv.elementAt(i).homeScore);
			data[5] = Integer.toString(siv.elementAt(i).total);
			data[6] = Double.toString(siv.elementAt(i).average);
			data[7] = siv.elementAt(i).grade;
			dtm.addRow(data);
		}

	}

	public void averCal() {
		midAver = 0;
		finAver = 0;
		homeAver = 0;
		totalAver = 0;
		aver = 0;
		if(siv.size() == 0) {
			return;
		}
		for (int i = 0; i < siv.size(); i++) {
			midAver += Double.valueOf((String) table.getValueAt(i, 2));
			finAver += Double.valueOf((String) table.getValueAt(i, 3));
			homeAver += Double.valueOf((String) table.getValueAt(i, 4));
			totalAver += Double.valueOf((String) table.getValueAt(i, 5));
			aver += Double.valueOf((String) table.getValueAt(i, 6));
		}
		String mainMid = fm.format(midAver / siv.size());
		String mainFin = fm.format(finAver / siv.size());
		String mainHome = fm.format(homeAver / siv.size());
		String mainTo = fm.format(totalAver / siv.size());
		String mainAver = fm.format(aver / siv.size());
		data[0] = "���";
		data[1] = " ";
		data[2] = mainMid;
		data[3] = mainFin;
		data[4] = mainHome;
		data[5] = mainTo;
		data[6] = mainAver;
		data[7] = " ";
		dtm.addRow(data);

	}

	public static Vector<StuInfoVector> getVec() {
		return siv;
	}
}

class AddGui extends JFrame {
	private JPanel panel;
	private JLabel info, number, name, mid, fin, home;
	private JButton button;
	private JTextField numinfo, nameinfo, midinfo, fininfo, homeinfo;
	private String wname;
	private int wnum, wmid, wfin, whome, wtotal;
	private double waverage;
	private Vector<StuInfoVector> siv;
	private StuInfoVector std;
	private String[] winfo = new String[5];

	public AddGui() {
		super("�л� �߰�");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setBounds(100, 100, 321, 380);
		panel = new JPanel();
		panel.setLayout(null);
		setContentPane(panel);

		info = new JLabel("�߰��� �л��� ������ �Է����ּ���.");
		info.setFont(new Font("���� ���", Font.PLAIN, 14));
		info.setBounds(12, 21, 250, 15);
		panel.add(info);

		number = new JLabel("�й�");
		number.setFont(new Font("���� ��� Semilight", Font.PLAIN, 14));
		number.setBounds(68, 70, 31, 15);
		panel.add(number);

		name = new JLabel("�̸�");
		name.setFont(new Font("���� ��� Semilight", Font.PLAIN, 14));
		name.setBounds(68, 110, 31, 15);
		panel.add(name);

		mid = new JLabel("�߰� ����");
		mid.setFont(new Font("���� ��� Semilight", Font.PLAIN, 14));
		mid.setBounds(39, 150, 60, 15);
		panel.add(mid);

		fin = new JLabel("�⸻ ����");
		fin.setFont(new Font("���� ��� Semilight", Font.PLAIN, 14));
		fin.setBounds(39, 190, 60, 15);
		panel.add(fin);

		home = new JLabel("���� ����");
		home.setFont(new Font("���� ��� Semilight", Font.PLAIN, 14));
		home.setBounds(39, 230, 60, 15);
		panel.add(home);

		button = new JButton("Ȯ��");
		button.setFont(new Font("����ü", Font.PLAIN, 17));
		button.setBounds(102, 273, 97, 50);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				winfo[0] = numinfo.getText();
				winfo[1] = nameinfo.getText();
				winfo[2] = midinfo.getText();
				winfo[3] = fininfo.getText();
				winfo[4] = homeinfo.getText();

				if (winfo[0].equals("") || winfo[1].equals("") || winfo[2].equals("") || winfo[3].equals("")
						|| winfo[4].equals("")) {
					showMessage("��ĭ�� �ֽ��ϴ�. �� ä���ֽʽÿ�.", "Warning!");
				} else if (Integer.parseInt(winfo[2]) > 100 || Integer.parseInt(winfo[2]) < 0
						|| Integer.parseInt(winfo[3]) > 100 || Integer.parseInt(winfo[3]) < 0
						|| Integer.parseInt(winfo[4]) > 100 || Integer.parseInt(winfo[4]) < 0) {
					showMessage("�� ������ ������ 0~100�� �Դϴ�! �ٽ� �Է����ּ���.", "Warning!");
				} else {
					siv = ListGui.getVec();
					wnum = Integer.parseInt(winfo[0]);
					wname = winfo[1];
					wmid = Integer.parseInt(winfo[2]);
					wfin = Integer.parseInt(winfo[3]);
					whome = Integer.parseInt(winfo[4]);
					wtotal = wmid + wfin + whome;
					waverage = (double) wtotal / 3;
					DecimalFormat fm = new DecimalFormat(".##");
					String st = fm.format(waverage);
					std = new StuInfoVector(Integer.parseInt(winfo[0]), wname, wmid, wfin, whome, wtotal,
							Double.parseDouble(st), "NULL");
					siv.add(std);
					siv = AddCal.addcal(siv);
					Collections.sort(siv, new Sort.numberSort());
					showMessage("����Ǿ����ϴ�! ���ΰ�ħ ��ư�� �����ּ���!", "Complete!");
					dispose();
				}
			}
		});

		numinfo = new JTextField();
		numinfo.setBounds(127, 69, 135, 21);
		panel.add(numinfo);
		numinfo.setColumns(10);

		nameinfo = new JTextField();
		nameinfo.setColumns(10);
		nameinfo.setBounds(127, 109, 135, 21);
		panel.add(nameinfo);

		midinfo = new JTextField();
		midinfo.setColumns(10);
		midinfo.setBounds(127, 149, 135, 21);
		panel.add(midinfo);

		fininfo = new JTextField();
		fininfo.setColumns(10);
		fininfo.setBounds(127, 189, 135, 21);
		panel.add(fininfo);

		homeinfo = new JTextField();
		homeinfo.setColumns(10);
		homeinfo.setBounds(127, 229, 135, 21);
		panel.add(homeinfo);
		
		
		setVisible(true);
	}

	public void showMessage(String message, String mes) {
		JOptionPane.showMessageDialog(this, message, mes, JOptionPane.INFORMATION_MESSAGE);

	}

	public String[] getData() {
		return winfo;
	}

}
