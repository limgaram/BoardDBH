package board.Article;

public class Article {
	private int id;
	private String title;
	private String nickname;
	private String body;
	private String regDate;
	private int hit;
	private int LikeCnt;

	public Article() {

	}

	public Article(int id, String title, String nickname, String body, String regDate, int hit, int LikeCnt) {
		super();
		this.id = id;
		this.title = title;
		this.nickname = nickname;
		this.body = body;
		this.regDate = regDate;
		this.hit = hit;
		this.LikeCnt = LikeCnt;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setLikeCnt(int LikeCnt) {
		this.hit = hit;
	}
	
	public int getLikeCnt() {
		return LikeCnt;
	}

}
