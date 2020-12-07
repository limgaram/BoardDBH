package board.Article;

public class Reply {
	int id;
	int parentId;
	String body;
	String nickname;
	String regDate;

	public Reply() {

	}

	public Reply(int id, int parentId, String body, String nickname, String regDate) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.body = body;
		this.nickname = nickname;
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getnickname() {
		return nickname;
	}

	public void setnickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
