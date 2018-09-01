package cn.tedu.store.entity;

import java.io.Serializable;

public class Area implements Serializable{

	private static final long serialVersionUID = -6235332657446030173L;
	private Integer id ;
	private String code ;
	private String name;
	private String cityCode;
	
	
	public Area() {
		super();
		
	}
	public Area(Integer id, String code, String name, String cityCode) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.cityCode = cityCode;
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
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	@Override
	public String toString() {
		return "Area [id=" + id + ", code=" + code + ", name=" + name + ", cityCode=" + cityCode + "]";
	}
	
}
