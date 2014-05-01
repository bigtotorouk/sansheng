package com.push;
/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-10-27 下午5:34:36
 * declare:
 */
public class Opration {
	private String opra;
	private String number;

	public String getOpra() {
		return opra;
	}

	public void setOpra(String opra) {  
		this.opra = opra;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void parse(String s) {
		String[] content = s.split("\\|");
		opra = content[0];
		number = content[1];
	}

}