package cn.yq.oa.vo;

public class MessageObject {
	private Integer id;
	private String message;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageObject(Integer id, String message) {
		super();
		this.id = id;
		this.message = message;
	}
	
}
