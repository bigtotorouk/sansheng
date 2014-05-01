package com.sansheng.model;

import java.util.Arrays;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sansheng.dao.impl.LocalInfoDaoImpl;

@DatabaseTable(daoClass = LocalInfoDaoImpl.class)
public class LocalInfo {

	public enum InfoType {
		announce, news, sales, introduce, history, honor, culture, brand, chariman, industry, quality, community, produce, honouryear
	}

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private String infoId;

	@DatabaseField
	private String title;
	@DatabaseField
	private String SubTitle;
	@DatabaseField
	private String Content;

	@DatabaseField
	private String data;

	@DatabaseField
	private int type;

	@DatabaseField
	private String bImg;

	@DatabaseField
	private String sImg;

	@DatabaseField
	private String url;

	@DatabaseField
	private String status;

	private String list[];

	private String news_simg;
	private String news_bimg;

	private String news_bimgshow;
	private String news_simgshow;

	private String info;

	private String img;

	private String[] contenList;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String[] getList() {
		return list;
	}

	public void setList(String[] list) {
		this.list = list;
	}

	public String[] getContenList() {
		return contenList;
	}

	public void setContenList(String[] contenList) {
		this.contenList = contenList;
	}

	public String getNews_bimgshow() {
		return news_bimgshow;
	}

	public void setNews_bimgshow(String news_bimgshow) {
		this.news_bimgshow = news_bimgshow;
	}

	public String getNews_simgshow() {
		return news_simgshow;
	}

	public void setNews_simgshow(String news_simgshow) {
		this.news_simgshow = news_simgshow;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return SubTitle;
	}

	public void setSubTitle(String subTitle) {
		SubTitle = subTitle;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getType() {
		return type;
	}

	public void setType(InfoType infoType) {
		type = getInfoType(infoType);
	}

	public static int getInfoType(InfoType infoType) {
		int type = 0;
		if (infoType == InfoType.announce) {
			type = 0;
		} else if (infoType == InfoType.news) {
			type = 1;
		} else if (infoType == InfoType.sales) {
			type = 2;
		} else if (infoType == InfoType.introduce) {
			type = 3;
		} else if (infoType == InfoType.history) {
			type = 4;
		} else if (infoType == InfoType.honor) {
			type = 5;
		} else if (infoType == InfoType.culture) {
			type = 6;
		} else if (infoType == InfoType.brand) {
			type = 7;
		} else if (infoType == InfoType.chariman) {
			type = 8;
		} else if (infoType == InfoType.industry) {
			type = 9;
		} else if (infoType == InfoType.quality) {
			type = 10;
		} else if (infoType == InfoType.community) {
			type = 11;
		} else if (infoType == InfoType.produce) {
			type = 12;
		} else if (infoType == InfoType.honouryear) {
			type = 13;
		}

		return type;
	}

	public String getbImg() {
		return bImg;
	}

	public void setbImg(String bImg) {
		this.bImg = bImg;
	}

	public String getsImg() {
		return sImg;
	}

	public void setsImg(String sImg) {
		this.sImg = sImg;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNews_simg() {
		return news_simg;
	}

	public void setNews_simg(String news_simg) {
		this.news_simg = news_simg;
	}

	public String getNews_bimg() {
		return news_bimg;
	}

	public void setNews_bimg(String news_bimg) {
		this.news_bimg = news_bimg;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "LocalInfo [id=" + id + ", infoId=" + infoId + ", title="
				+ title + ", SubTitle=" + SubTitle + ", Content=" + Content
				+ ", data=" + data + ", type=" + type + ", bImg=" + bImg
				+ ", sImg=" + sImg + ", url=" + url + ", status=" + status
				+ ", list=" + Arrays.toString(list) + ", news_simg="
				+ news_simg + ", news_bimg=" + news_bimg + ", news_bimgshow="
				+ news_bimgshow + ", news_simgshow=" + news_simgshow
				+ ", info=" + info + ", img=" + img + ", contenList="
				+ Arrays.toString(contenList) + "]";
	}

	 

}
