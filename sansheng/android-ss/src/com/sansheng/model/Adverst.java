package com.sansheng.model;


/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-26 下午12:29:09 declare:广告
 * 
 */
public class Adverst {

	private int id;
	private String type;
	private String name;
	private String img;
	private String info;
	private  String  title;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Adverst [id=" + id + ", type=" + type + ", name=" + name
				+ ", img=" + img + ", info=" + info + "]";
	}


	
	
}
