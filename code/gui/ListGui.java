package gui;

import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import function.CustomerVector;
import function.FileRenewal;
import function.Fileopen;
import function.PriorityCal;
import function.Sort;

public class ListGui extends JFrame implements ActionListener {

	private JFrame frame;
	private JTable table;

	private DefaultTableModel dtm;
	private JButton addButton, deleteButton, searchButton,renewButton;
	private JRadioButton number_radio, name_radio, mileage_radio, joinYear_radio, priority_radio;
	private JButton sortbutton;
	private JButton sortbutton2;

	private Vector<CustomerVector> vector;
	private Vector<CustomerVector> openVector;
	private Vector<CustomerVector> SearchVector;
	private CustomerVector vec;
	private String[] str = new String[4];
	private File file = new File("waitingList.txt");
	private int radioindex = 4;  //����Ʈ ���ڸ� �켱������ ��

	public ListGui() {
		setTitle("����� ��� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(180, 52, 634, 469);
		getContentPane().add(scrollPane);

		dtm = new DefaultTableModel(0, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		table = new JTable(dtm);
		scrollPane.setViewportView(table);

		addButton = new JButton("�߰�"); //������ �߰� ��ư
		addButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 14));
		addButton.addActionListener(this);
		addButton.setBounds(127, 539, 80, 39);
		getContentPane().add(addButton);

		deleteButton = new JButton("����"); //������ ���� ��ư
		deleteButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		deleteButton.addActionListener(this);
		deleteButton.setBounds(237, 539, 80, 39);
		getContentPane().add(deleteButton);

		searchButton = new JButton("�˻�"); //������ �˻� ��ư
		searchButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		searchButton.addActionListener(this);
		searchButton.setBounds(347, 539, 80, 39);
		getContentPane().add(searchButton);

		number_radio = new JRadioButton("������ȣ"); 
		number_radio.setFont(new Font("���� ���", Font.BOLD, 16));
		number_radio.addActionListener(this);
		number_radio.setBounds(40, 147, 108, 23);
		getContentPane().add(number_radio);

		name_radio = new JRadioButton("�̸�");
		name_radio.setFont(new Font("���� ���", Font.BOLD, 16));
		name_radio.addActionListener(this);
		name_radio.setBounds(40, 197, 108, 23);
		getContentPane().add(name_radio);
		
		renewButton = new JButton("����");
		renewButton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		renewButton.addActionListener(this);
		renewButton.setBounds(17, 539, 80, 39);
		getContentPane().add(renewButton);

		mileage_radio = new JRadioButton("���ϸ���");
		mileage_radio.setFont(new Font("���� ���", Font.BOLD, 16));
		mileage_radio.addActionListener(this);
		mileage_radio.setBounds(40, 247, 108, 23);
		getContentPane().add(mileage_radio);

		joinYear_radio = new JRadioButton("���Կ���");
		joinYear_radio.setFont(new Font("���� ���", Font.BOLD, 16));
		joinYear_radio.addActionListener(this);
		joinYear_radio.setBounds(40, 297, 108, 23);
		getContentPane().add(joinYear_radio);

		priority_radio = new JRadioButton("�켱����");
		priority_radio.setFont(new Font("���� ���", Font.BOLD, 16));
		priority_radio.addActionListener(this);
		priority_radio.setBounds(40, 343, 108, 23);
		getContentPane().add(priority_radio);

		ButtonGroup listgroup = new ButtonGroup();
		listgroup.add(number_radio);
		listgroup.add(name_radio);
		listgroup.add(mileage_radio);
		listgroup.add(joinYear_radio);
		listgroup.add(priority_radio);

		sortbutton = new JButton("��������"); //�������� ��ư
		sortbutton.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		sortbutton.addActionListener(this);
		sortbutton.setBounds(560, 539, 108, 39);
		getContentPane().add(sortbutton);


		sortbutton2 = new JButton("��������"); //���� ���� ��ư
		sortbutton2.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		sortbutton2.addActionListener(this);
		sortbutton2.setBounds(680, 539, 108, 39);
		getContentPane().add(sortbutton2);

		JLabel lblNewLabel = new JLabel("�ݿ� �װ��� ����� ���");
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 30));
		lblNewLabel.setBounds(228, 7, 395, 39);
		getContentPane().add(lblNewLabel);
		


		openVector = Fileopen.fileopen(file);

		dtm.addColumn("������ȣ");
		dtm.addColumn("�̸�");
		dtm.addColumn("���ϸ���");
		dtm.addColumn("���Կ���");

		vector = PriorityCal.PriorityCalculation(openVector);
		Collections.sort(vector, new Sort.prioritycompare());

		outputvector();

		FileRenewal.renewal(file, vector);

		setSize(848, 635);
		setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) { //��ư�� ������ư�� �������� �ൿ

		Object o = e.getSource();
		if (o == addButton) { //�߰���ư
			new windowAdd(); 
			
		} else if (o == deleteButton) { //������ư
			delete();
		} else if (o == searchButton) {  //�˻���ư
			search();
		}
		else if (o ==renewButton) {  //���Ź�ư
			renew();
		}

		if (o == number_radio) {
			radioindex = 0;
		}if (o == name_radio) {
			radioindex = 1;
		}if (o == mileage_radio) {
			radioindex = 2;
		}if (o == joinYear_radio) {
			radioindex = 3;
		}if (o == priority_radio) {
			radioindex = 4;
		}if (o == sortbutton) {
			if (radioindex == 0) {
				Collections.sort(vector, new Sort.numberascend());
				outputvector();
			} else if (radioindex == 1) {
				Collections.sort(vector, new Sort.nameascend());
				outputvector();
			} else if (radioindex == 2) {
				Collections.sort(vector, new Sort.mileageascend());
				outputvector();
			} else if (radioindex == 3) {
				Collections.sort(vector, new Sort.joinyearascend());
				outputvector();
			} else if (radioindex == 4) {
				vector = PriorityCal.PriorityCalculation(vector);
				Collections.sort(vector, new Sort.prioritycompare2());
				outputvector();
			}
			FileRenewal.renewal(file, vector);

		}  if (o == sortbutton2) {
			if (radioindex == 0) {
				Collections.sort(vector, new Sort.numberdescend());
				outputvector();
			} if (radioindex == 1) {
				Collections.sort(vector, new Sort.nameadescend());
				outputvector();
			}if (radioindex == 2) {
				Collections.sort(vector, new Sort.mileagedescend());
				outputvector();
			}  if (radioindex == 3) {
				Collections.sort(vector, new Sort.joinyeardescend());
				outputvector();
			} if (radioindex == 4) {
				vector = PriorityCal.PriorityCalculation(vector);
				Collections.sort(vector, new Sort.prioritycompare());
				outputvector();
			}
			FileRenewal.renewal(file, vector); //������ �ٽ� ���Ž��� ȭ�鿡 �߰���
		}

	}

	public void delete() { //���� �޼ҵ�
		int index = table.getSelectedRow(); //���� ����
		if (index < 0) { //���� ���� ��
			showMessage("������ ���� �������ּ���!"); //â�� ��
		} else {
			vector.remove(index); //����
			outputvector();  //ȭ�� ���
			FileRenewal.renewal(file, vector); //���� ����
		}

	}

	public void search() {
		String name = JOptionPane.showInputDialog("ã�� �̸��� �Է����ּ���"); //�̸� �Է� â
		SearchVector = new Vector<CustomerVector>();
		if (name != null) { //�̸� �Է��� �Ǹ�
			for (int i = 0; i < vector.size(); i++) { 
				if (name.equals(vector.elementAt(i).name)) { //�̸��� ���Ͱ� ������
					vec = new CustomerVector(vector.elementAt(i).number, vector.elementAt(i).name,
							vector.elementAt(i).mileage, vector.elementAt(i).join_year,
							vector.elementAt(i).priority_customer);
					SearchVector.add(vec); //Search���Ϳ� �ִ´�
				}
			}
			if(SearchVector.size() == 0) { //������
				showMessage("ã���ô� �����ڰ� �����ϴ�!");  //â�� ��
			}
			dtm.setNumRows(0);
			for (int i = 0; i < SearchVector.size(); i++) {
				str[0] = Integer.toString(SearchVector.elementAt(i).number);
				str[1] = SearchVector.elementAt(i).name;
				str[2] = Integer.toString(SearchVector.elementAt(i).mileage);
				str[3] = SearchVector.elementAt(i).join_year;
				dtm.addRow(str); //ã�� ������ ȭ�鿡 ���
			}
		} 
	}

	public void showMessage(String message) { //â�̶߰��ϴ� �޼ҵ�
		JOptionPane.showMessageDialog(frame, message, "�޼���", JOptionPane.INFORMATION_MESSAGE);
	}

	public void outputvector() { //ȭ�鿡 ����ϴ� �޼ҵ�
		dtm.setNumRows(0);
		for (int i = 0; i < vector.size(); i++) {
			str[0] = Integer.toString(vector.elementAt(i).number);
			str[1] = vector.elementAt(i).name;
			str[2] = Integer.toString(vector.elementAt(i).mileage);
			str[3] = vector.elementAt(i).join_year;

			dtm.addRow(str);
		}
	}
	
	public void renew() { //����
		vector.removeAllElements();
		vector = Fileopen.fileopen(file);
		outputvector();
	}
	
}

