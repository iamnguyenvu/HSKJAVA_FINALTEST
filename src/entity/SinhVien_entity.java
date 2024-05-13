package entity;

import java.io.Serializable;
import java.util.Objects;

public class SinhVien_entity implements Serializable {
	private String maSV;
	private String hoSV;
	private String tenSV;
	private int tuoi;
	private Boolean gioiTinh;
	private String chuyenNganh;
	private String maChuyenNganh;
	
	public SinhVien_entity() {
		// TODO Auto-generated constructor stub
	}
	
	
	public SinhVien_entity(String maSV) {
		super();
		this.maSV = maSV;
	}

	public SinhVien_entity(String maSV, String hoSV, String tenSV, int tuoi, Boolean gioiTinh, String chuyenNganh,
			String maChuyenNganh) {
		super();
		this.maSV = maSV;
		this.hoSV = hoSV;
		this.tenSV = tenSV;
		this.tuoi = tuoi;
		this.gioiTinh = gioiTinh;
		this.chuyenNganh = chuyenNganh;
		this.maChuyenNganh = maChuyenNganh;
	}


	public String getMaSV() {
		return maSV;
	}


	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}


	public String getHoSV() {
		return hoSV;
	}


	public void setHoSV(String hoSV) {
		this.hoSV = hoSV;
	}


	public String getTenSV() {
		return tenSV;
	}


	public void setTenSV(String tenSV) {
		this.tenSV = tenSV;
	}


	public int getTuoi() {
		return tuoi;
	}


	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}


	public Boolean isGioiTinh() {
		return gioiTinh;
	}


	public void setGioiTinh(Boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}


	public String getChuyenNganh() {
		return chuyenNganh;
	}


	public void setChuyenNganh(String chuyenNganh) {
		this.chuyenNganh = chuyenNganh;
	}
	

	@Override
	public String toString() {
		return "SinhVien [maSV=" + maSV + ", hoSV=" + hoSV + ", tenSV=" + tenSV + ", tuoi=" + tuoi + ", gioiTinh="
				+ gioiTinh + ", chuyenNganh=" + chuyenNganh + ", maChuyenNganh=" + maChuyenNganh + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(maSV);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinhVien_entity other = (SinhVien_entity) obj;
		return Objects.equals(maSV, other.maSV);
	}


	public String getMaChuyenNganh() {
		return maChuyenNganh;
	}


	public void setMaChuyenNganh(String maChuyenNganh) {
		this.maChuyenNganh = maChuyenNganh;
	}

}

