package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.SinhVien_dao;
import entity.SinhVien_entity;

public class main_GUI extends JFrame implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private JTextField tfMaSV;
	private JTextField tfHoSV;
	private JTextField tfTenSV;
	private JTextField tfTuoi;
	private JTextField tfMess;
	private JComboBox<String> cbChuyenNganh;
	private JComboBox<String> cbGioiTinh;
	private JTextField tfMaChuyenNganh;
	private JButton btAdd;
	private JButton btDelete;
	private JButton btClear;
	private JButton btEdit;
	private JButton btSave;
	private JComboBox<String> cbTimTheoChuyenNganh;
	private JButton btFind;
//	SinhVienTableModel model;
	private JTable table;
	private DefaultTableModel model;
	//HashMap để ánh xạ chuyên ngành và mã chuyên ngành tương ứng
	private HashMap<String, String> maChuyenNganhMap;
	private SinhVien_dao sinhVien_dao;
	private List<SinhVien_entity> dsSV;
	
	public main_GUI() {
		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(900, 600));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponent();
		readDBToTable();
		CapNhapComboboxChuyenNganh();
	}
	
	private void initComponent() {
		JPanel pnNorth;
		add(pnNorth = new JPanel(), BorderLayout.NORTH);
		pnNorth.setPreferredSize(new Dimension(0, 150));
		pnNorth.setBorder(BorderFactory.createTitledBorder("Record Editors"));
		pnNorth.setLayout(null);
		
		JLabel lbMaSV, lbHoSV, lbTenSV, lbTuoi, lbGioiTinh, lbChuyenNganh, lbMaChuyenNganh;
		pnNorth.add(lbMaSV = new JLabel("Mã Sinh Viên: "));
		pnNorth.add(lbHoSV = new JLabel(" Họ: "));
		pnNorth.add(lbTenSV = new JLabel("Tên Sinh Viên: "));
		pnNorth.add(lbTuoi = new JLabel("Tuổi: "));
		pnNorth.add(lbGioiTinh = new JLabel("Giới tính: "));
		pnNorth.add(lbChuyenNganh = new JLabel("Chuyên Ngành: "));
		pnNorth.add(lbMaChuyenNganh = new JLabel("Mã chuyên ngành"));
		
		String[] gioiTinh = {"Nam", "Nữ"};
		String[] chuyenNganh = {"Kỹ thuật phần mềm", "Khoa học máy tính", "Khoa học dữ liệu", "Hệ thống thông tin", "Công nghệ thông tin"};
		// Khởi tạo HashMap và thêm các ánh xạ chuyên ngành và mã chuyên ngành tương ứng
        maChuyenNganhMap = new HashMap<>();
        maChuyenNganhMap.put("Kỹ thuật phần mềm", "DHKTPM");
        maChuyenNganhMap.put("Khoa học máy tính", "DHKHMT");
        maChuyenNganhMap.put("Khoa học dữ liệu", "DHKHDL");
        maChuyenNganhMap.put("Hệ thống thông tin", "DHHTTT");
        maChuyenNganhMap.put("Công nghệ thông tin", "DHCNTT");
		
		pnNorth.add(tfMaSV = new JTextField());
		pnNorth.add(tfHoSV = new JTextField());
		pnNorth.add(tfTenSV = new JTextField());
		pnNorth.add(tfTuoi = new JTextField());
		pnNorth.add(cbGioiTinh = new JComboBox<String>(gioiTinh));
		pnNorth.add(cbChuyenNganh = new JComboBox<String>(chuyenNganh));
		pnNorth.add(tfMaChuyenNganh = new JTextField());
		
		pnNorth.add(tfMess = new JTextField());
		tfMess.setEditable(false);
		tfMess.setBorder(null);
		tfMess.setFont(new Font("Time New Roman", Font.ITALIC, 12));
		tfMess.setForeground(Color.red);
		
		int w1 = 120, w2 = 240, h = 20;
//		lbMaSV.setBounds(20, 20, w1, h); tfMaSV.setBounds(140, 20, w2, h);
		lbHoSV.setBounds(20, 20, w1, h); tfHoSV.setBounds(140, 20, w2, h);
		lbTenSV.setBounds(20, 45, w1, h); tfTenSV.setBounds(140, 45, w2, h);
		lbMaChuyenNganh.setBounds(20, 70, w1, h); tfMaChuyenNganh.setBounds(140, 70, w2, h);
		lbTuoi.setBounds(470, 20, w1, h); tfTuoi.setBounds(570, 20, w2, h);
		lbGioiTinh.setBounds(470, 45, w1, h); cbGioiTinh.setBounds(570, 45, w2, h);
		lbChuyenNganh.setBounds(470, 70, w1, h); cbChuyenNganh.setBounds(570, 70, w2, h);
		tfMess.setBounds(20, 120, 850, h);
		
		//Center
		JPanel pnCenter;
		add(pnCenter = new JPanel(), BorderLayout.CENTER);
		pnCenter.add(btAdd = new JButton("Thêm"));
		pnCenter.add(btClear = new JButton("Xóa trắng"));
		pnCenter.add(btDelete = new JButton("Xóa"));
		pnCenter.add(btEdit = new JButton("Sửa"));
		pnCenter.add(btSave = new JButton("Lưu"));
		pnCenter.add(new JLabel("Tìm theo chuyên ngành: "));
		pnCenter.add(cbTimTheoChuyenNganh = new JComboBox<String>());
		cbTimTheoChuyenNganh.setPreferredSize(new Dimension(180, 25));
		pnCenter.add(btFind = new JButton("Tìm theo mã chuyên ngành"));
		
		//South
		JScrollPane spSouth;
		String[] headers = {"Mã Sinh Viên", "Họ Sinh Viên", "Tên Sinh Viên", "Tuổi", "Giới Tính", "Chuyên Ngành", "Mã Chuyên Ngành"};
		model = new DefaultTableModel(headers, 0);
		add(spSouth = new JScrollPane(table = new JTable(model), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		spSouth.setBorder(BorderFactory.createTitledBorder("Danh sách sinh viên"));
		table.setRowHeight(20);
		spSouth.setPreferredSize(new Dimension(0, 350));
		
		sinhVien_dao = new SinhVien_dao();
		dsSV = sinhVien_dao.getDSSinhVien();
		
		btAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addAction();
			}
		});
		btClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearText();
				
			}
		});
		btDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteAction();
			}
		});
		btEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateAction();
			}
		});
		btSave.addActionListener(this);
		btFind.addActionListener(this);
		table.addMouseListener(this);
		cbChuyenNganh.addActionListener(this);
		pack();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new main_GUI().setVisible(true);
	}
	
	public static String generateCode() {
		Calendar now = Calendar.getInstance();
		String currentCourse = String.valueOf(now.get(Calendar.YEAR) - 2002);
	    Random random = new Random();
	    int randomDigits = 10000000 + random.nextInt(90000000); // Generate 8 random digits
	    String maSV = currentCourse + String.format("%06d", randomDigits); // Format the random digits to ensure it's 6 digits
	    return maSV.substring(0, 8); // Return only the first 8 digits
	}
	
	private void readDBToTable() {
		sinhVien_dao = new SinhVien_dao();
		dsSV = sinhVien_dao.getDSSinhVien();
		for (SinhVien_entity sv : dsSV) {
			model.addRow(new Object[] {sv.getMaSV(), sv.getHoSV(), sv.getTenSV(),sv.getTuoi(), sv.isGioiTinh() ? "Nam" : "Nữ", sv.getChuyenNganh(), sv.getMaChuyenNganh()});
		}
	}
	
	private void CapNhapComboboxChuyenNganh() {
		ArrayList<SinhVien_entity> dsSV = sinhVien_dao.getDSSinhVien();
		ArrayList<String> list = new ArrayList<String>();
		for (SinhVien_entity sv : dsSV) {
			if(!list.contains(sv.getChuyenNganh())) list.add(sv.getChuyenNganh());
		}
		String []items = list.toArray(new String[0]);
		cbTimTheoChuyenNganh.setModel(new DefaultComboBoxModel<String>(items));
	}
	
	public void clearText() {
		tfHoSV.setText("");
		tfTenSV.setText("");
		tfTuoi.setText("");
		cbGioiTinh.setSelectedIndex(0);
		cbChuyenNganh.setSelectedIndex(0);
		tfMaChuyenNganh.setText("");
	}
	
	private void addAction() {
		if(isValidData()) {
			String maSV = generateCode();
			String hoSV = tfHoSV.getText().trim();
			String tenSV = tfTenSV.getText().trim();
			int tuoi = Integer.parseInt(tfTuoi.getText().trim());
			Boolean gioiTinh;
			gioiTinh = (cbGioiTinh.getSelectedIndex() == 0) ? true : false;
			String chuyenNganh = (String) cbChuyenNganh.getSelectedItem(); 
			String maChuyenNganh = tfMaChuyenNganh.getText().trim();
			SinhVien_entity sv = new SinhVien_entity(maSV, hoSV, tenSV, tuoi, gioiTinh, chuyenNganh, maChuyenNganh);
			try {
				if(sinhVien_dao.create(sv)) {
					model.addRow(new Object[] {sv.getMaSV(), sv.getHoSV(), sv.getTenSV(),sv.getTuoi(), sv.isGioiTinh() ? "Nam" : "Nữ", sv.getChuyenNganh(), sv.getMaChuyenNganh()});
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	
	private void deleteAction() {
		int row = table.getSelectedRow();
		if(row != -1) {
			if(JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if(sinhVien_dao.delete(model.getValueAt(row, 0).toString())) {
					model.setRowCount(0);
					readDBToTable();
					CapNhapComboboxChuyenNganh();
					clearText();
				}
			}
		}
	}
	
	private void updateAction() {
		int row = table.getSelectedRow();
		if(row != -1) {
			if(isValidData()) {
				String maSV = model.getValueAt(row, 0).toString();
				String hoSV = tfHoSV.getText().trim();
				String tenSV = tfTenSV.getText().trim();
				int tuoi = Integer.parseInt(tfTuoi.getText().trim());
				Boolean gioiTinh;
				gioiTinh = (cbGioiTinh.getSelectedIndex() == 0) ? true : false;
				String chuyenNganh = (String) cbChuyenNganh.getSelectedItem(); 
				String maChuyenNganh = tfMaChuyenNganh.getText().trim();
				SinhVien_entity sv = new SinhVien_entity(maSV, hoSV, tenSV, tuoi, gioiTinh, chuyenNganh, maChuyenNganh);
				try {
					if(sinhVien_dao.update(sv)) {
						model.setRowCount(0);
						readDBToTable();
						CapNhapComboboxChuyenNganh();
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
//		tfMaSV.setText(model.getValueAt(row, 0).toString());
		tfHoSV.setText(model.getValueAt(row, 1).toString());
		tfTenSV.setText(model.getValueAt(row, 2).toString());
		tfTuoi.setText(model.getValueAt(row, 3).toString());
		cbGioiTinh.setSelectedIndex(model.getValueAt(row, 4).toString().equals("Nam") ? 0 : 1);
		cbChuyenNganh.setSelectedItem(model.getValueAt(row, 5).toString());
		tfMaChuyenNganh.setText(model.getValueAt(row, 6).toString());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isValidData() {
		String maSV = tfMaSV.getText().trim();
		String hoSV = tfHoSV.getText().trim();
		String tenSV = tfTenSV.getText().trim();
		String tuoi = tfTuoi.getText().trim();
		String maChuyenNganh = tfMaChuyenNganh.getText().trim();
		
		if(hoSV.length() < 1) {
			tfMess.setText("Chưa nhập Họ Sinh Viên!");
			tfHoSV.requestFocus();
			return false;
		} else {
			if(hoSV.matches("^[A-Z][a-z]*(?: [A-Z][a-z]*)*$")) {
				tfMess.setText("");
			} else {
				tfMess.setText("Họ Sinh Viên gồm các từ là kí tự chữ, "
						+ "mỗi từ bắt đầu bằng chữ in hoa, giữa mỗi từ ngăn cách bằng 1 khoảng trắng!");
				tfHoSV.setText("");
				return false;
			}
		}
		
		if(tenSV.length() < 1) {
			tfMess.setText("Chưa nhập Tên Sinh Viên!");
			tfTenSV.requestFocus();
			return false;
		} else {
			if(tenSV.matches("^[A-Z][a-z]+$")) {
				tfMess.setText("");
			} else {
				tfMess.setText("Tên Sinh Viên là một từ gồm các kí tự chữ"
						+ " bắt đầu bằng chữ in hoa!");
				tfTenSV.setText("");
				return false;
			}
		}
		
		if(tuoi.length() < 1) {
			tfMess.setText("Chưa nhập Tuổi Sinh Viên!");
			tfTuoi.requestFocus();
			return false;
		} else {
			int tuoiSV = Integer.parseInt(tuoi);
			if(tuoiSV >= 18 && tuoiSV <= 26) {
				tfMess.setText("");
			} else {
				tfMess.setText("Tuổi Sinh Viên từ 18 đến 26 tuổi!");
				tfTuoi.setText("");
				return false;
			}
		}
		
		if(maChuyenNganh.length() < 1) {
			tfMess.setText("Chưa nhập Mã Chuyên Ngành!");
			tfMaChuyenNganh.requestFocus();
			return false;
		} else {
			String chuyenNganh = (String) cbChuyenNganh.getSelectedItem();
            String maCN = tfMaChuyenNganh.getText().toUpperCase();
            if (!maChuyenNganhMap.containsKey(chuyenNganh) || !maCN.startsWith(maChuyenNganhMap.get(chuyenNganh))) {
                // Nếu không tương ứng, thông báo lỗi
                tfMess.setText("Mã Chuyên Ngành không tương ứng với Chuyên Ngành đã chọn!");
                tfMaChuyenNganh.setText("");
                return false;
            } else {
                // Nếu tương ứng, xóa thông báo lỗi
            	if (maChuyenNganh.matches("^DH(KTPM|KHMT|KHDL|HTTT|CNTT)(14|15|16|17|18|19)(A|B|C)(TT)?$")) {
                    tfMess.setText("");
                } else {
                    tfMess.setText("Mã Chuyên Ngành không hợp lệ!");
                    tfMaChuyenNganh.setText("");
                    return false;
                }
            }
		}
		
		return true;
	}
	

}
