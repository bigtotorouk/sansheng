package com.sansheng.model;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

public class Contact implements Serializable {
	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private String name;
	@DatabaseField
	private String cellphone1;
	@DatabaseField
	private String cellphone2;
	@DatabaseField
	private String telephone;
	@DatabaseField
	private String email;
	@DatabaseField
	private String todoOne;
	@DatabaseField
	private String todoTwo;
	@DatabaseField
	private String todoThree;
	private String nickName;

	private String pingying;

	private String cardNum;

	private String addtimer;
	private String mobilephone;
	private String[] homephone;
	private String address;
	private String sex;
	private String birthday;
	private String work;
	private String mark;
	private File avatar;
	private String position;

	public File getAvatar() {
		return avatar;
	}

	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellphone1() {
		return cellphone1;
	}

	public void setCellphone1(String cellphone1) {
		this.cellphone1 = cellphone1;
	}

	public String getCellphone2() {
		return cellphone2;
	}

	public void setCellphone2(String cellphone2) {
		this.cellphone2 = cellphone2;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTodoOne() {
		return todoOne;
	}

	public void setTodoOne(String todoOne) {
		this.todoOne = todoOne;
	}

	public String getTodoTwo() {
		return todoTwo;
	}

	public void setTodoTwo(String todoTwo) {
		this.todoTwo = todoTwo;
	}

	public String getTodoThree() {
		return todoThree;
	}

	public void setTodoThree(String todoThree) {
		this.todoThree = todoThree;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPingying() {
		return pingying;
	}

	public void setPingying(String pingying) {
		this.pingying = pingying;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getAddtimer() {
		return addtimer;
	}

	public void setAddtimer(String addtimer) {
		this.addtimer = addtimer;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String[] getHomephone() {
		return homephone;
	}

	public void setHomephone(String[] homephone) {
		this.homephone = homephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", cellphone1="
				+ cellphone1 + ", cellphone2=" + cellphone2 + ", telephone="
				+ telephone + ", email=" + email + ", todoOne=" + todoOne
				+ ", todoTwo=" + todoTwo + ", todoThree=" + todoThree
				+ ", nickName=" + nickName + ", pingying=" + pingying
				+ ", cardNum=" + cardNum + ", addtimer=" + addtimer
				+ ", mobilephone=" + mobilephone + ", homephone="
				+ Arrays.toString(homephone) + ", address=" + address
				+ ", sex=" + sex + ", birthday=" + birthday + ", work=" + work
				+ ", mark=" + mark + ", position=" + position + "]";
	}
}
