package dao;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectData;
import entity.SinhVien_entity;

public class SinhVien_dao {
	public boolean create(SinhVien_entity sv) {
		Connection con = ConnectData.accessDataBase();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into SinhVien values(?,?,?,?,?,?,?)");
			stmt.setString(1, sv.getMaSV());
			stmt.setString(2, sv.getHoSV());
			stmt.setString(3, sv.getTenSV());
			stmt.setInt(4, sv.getTuoi());
			stmt.setBoolean(5, sv.isGioiTinh());
			stmt.setString(6, sv.getChuyenNganh());
			stmt.setString(7, sv.getMaChuyenNganh());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			 try {
	             stmt.close();
	         } catch (Exception e) {
	              e.printStackTrace();
	         }
		}
		return n>0;
	}
	
	public ArrayList<SinhVien_entity> getDSSinhVien() {
		ArrayList<SinhVien_entity> dssv = new ArrayList<SinhVien_entity>();
		Connection con = ConnectData.accessDataBase();
		try {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from SinhVien");
			while(rs.next()) {
				SinhVien_entity sv = new SinhVien_entity(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6), rs.getString(7));
				dssv.add(sv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dssv;
	}
	
	public ArrayList<SinhVien_entity> getSinhVienFindByID(String maSV) {
		ArrayList<SinhVien_entity> dssv = new ArrayList<SinhVien_entity>();
		Connection con = ConnectData.accessDataBase();
		PreparedStatement s = null;
		try {
			s = con.prepareStatement("select * from SinhVien where maSV = ?");
			s.setString(1, maSV);
			try (ResultSet rs = s.executeQuery();) {
				while(rs.next()) {
				SinhVien_entity sv = new SinhVien_entity(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6), rs.getString(7));
				dssv.add(sv);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dssv;
	}
	
	public ArrayList<SinhVien_entity> getSinhVienFindByChuyenNganh(String chuyenNganh) {
		ArrayList<SinhVien_entity> dssv = new ArrayList<SinhVien_entity>();
		Connection con = ConnectData.accessDataBase();
		PreparedStatement s = null;
		try {
			s = con.prepareStatement("select * from SinhVien where chuyenNganh = ?");
			s.setString(1, chuyenNganh);
			try (ResultSet rs = s.executeQuery();) {
				while(rs.next()) {
				SinhVien_entity sv = new SinhVien_entity(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6), rs.getString(7));
				dssv.add(sv);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dssv;
	}
	
	public ArrayList<SinhVien_entity> getSinhVienFindByMaChuyenNganh(String maChuyenNganh) {
		ArrayList<SinhVien_entity> dssv = new ArrayList<SinhVien_entity>();
		Connection con = ConnectData.accessDataBase();
		PreparedStatement s = null;
		try {
			s = con.prepareStatement("select * from SinhVien where maChuyenNganh = ?");
			s.setString(1, maChuyenNganh);
			try (ResultSet rs = s.executeQuery();) {
				while(rs.next()) {
				SinhVien_entity sv = new SinhVien_entity(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getString(6), rs.getString(7));
				dssv.add(sv);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return dssv;
	}
	
	public boolean update(SinhVien_entity sv) {
		Connection con = ConnectData.accessDataBase();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update SinhVien set hoSV=?, tenSV=?, tuoi=?, gioiTinh=?, chuyenNganh=?, maChuyenNganh=? where maSV=?");
			stmt.setString(7, sv.getMaSV());
			stmt.setString(1, sv.getHoSV());
			stmt.setString(2, sv.getTenSV());
			stmt.setInt(3, sv.getTuoi());
			stmt.setBoolean(4, sv.isGioiTinh());
			stmt.setString(5, sv.getChuyenNganh());
			stmt.setString(6, sv.getMaChuyenNganh());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			 try {
	             stmt.close();
	         } catch (Exception e) {
	              e.printStackTrace();
	         }
		}
		return n>0;
	}
	
	public boolean delete(String maSV) {
		Connection con = ConnectData.accessDataBase();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from SinhVien where maSV=?");
			stmt.setString(1, maSV);
			int rowsAffected = stmt.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return false;
	}

}
