package model;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-8-31 下午8:47:34 declare:
 */
public class Evaluate {

	private String number;
	private String userlevel;
	private String time;
	private String content;
	private String commentcon;
	private String   qualityint;
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUserlevel() {
		return userlevel;
	}

	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCommentcon() {
		return commentcon;
	}

	public void setCommentcon(String commentcon) {
		this.commentcon = commentcon;
	}
	
	 

	public String getQualityint() {
		return qualityint;
	}

	public void setQualityint(String qualityint) {
		this.qualityint = qualityint;
	}

	@Override
	public String toString() {
		return "Evaluate [number=" + number + ", userlevel=" + userlevel
				+ ", time=" + time + ", content=" + content + ", commentcon="
				+ commentcon + "]";
	}
	

}
