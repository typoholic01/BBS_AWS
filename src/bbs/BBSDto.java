package bbs;

import java.io.Serializable;


public class BBSDto implements Serializable
{
	private static final long serialVersionUID = -4761819531600979014L;
	
	private int seq;
	private String category;
	private String user_id;
	private String title;
	private String content;
	private String status;
	private int count;
	private String create_at;
	private String update_at;
	private int ancestor;
	private String reply;
	
	public BBSDto() {
		
	}

	public BBSDto(String category,String user_id, String title, String content, String status, int ancestor) {
		super();
		this.category = category;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.status = status;
		this.ancestor = ancestor;
	}	

	public BBSDto(int seq, String category, String user_id, String title, String content, String status, int count,
			String create_at, String update_at, int ancestor, String reply) {
		super();
		this.seq = seq;
		this.category = category;
		this.user_id = user_id;
		this.title = title;
		this.content = content;
		this.status = status;
		this.count = count;
		this.create_at = create_at;
		this.update_at = update_at;
		this.ancestor = ancestor;
		this.reply = reply;
	}	

	@Override
	public String toString() {
		return "BBSDto [seq=" + seq + ", category=" + category + ", user_id=" + user_id + ", title=" + title
				+ ", content=" + content + ", status=" + status + ", count=" + count + ", create_at=" + create_at
				+ ", update_at=" + update_at + ", ancestor=" + ancestor + ", reply=" + reply + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCreate_at() {
		return create_at;
	}

	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}

	public String getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}

	public int getAncestor() {
		return ancestor;
	}

	public void setAncestor(int ancestor) {
		this.ancestor = ancestor;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}	
	
	
}

