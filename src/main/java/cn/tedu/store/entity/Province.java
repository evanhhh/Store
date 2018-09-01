package cn.tedu.store.entity;

import java.io.Serializable;

public class Province implements Serializable{

	private static final long serialVersionUID = -227799805324456184L;
	private Integer id ;
	private String code ;
	private String name;
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
	@Override
	public String toString() {
		return "Province [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
	public Province() {
		super();
		
	}
	public Province(Integer id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
}
