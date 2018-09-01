package cn.tedu.store.entity;

import java.io.Serializable;

public class City implements Serializable{

	private static final long serialVersionUID = -6684863588801459899L;
	private Integer id;
	private String code ;
	private String name;
	private String provinceCode;
	
	
	
	public City() {
		super();
		
	}
	public City(Integer id, String code, String name, String provinceCode) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.provinceCode = provinceCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", code=" + code + ", name=" + name + ", provinceCode=" + provinceCode + "]";
	}
	
}
