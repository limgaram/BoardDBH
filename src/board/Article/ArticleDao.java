package board.Article;
import java.util.ArrayList;

import borad.DBUtil;

public class ArticleDao {
	
	private DBUtil db = new DBUtil();
	
	public ArrayList<Article> getArticles(){
		String sql = "select a.*, m.nickname nickname from article a innser join `member` m on a.mid = m.id";
		return db.getRows(sql, new ArticleRowMapper());
	}
	
	public int updateArticle(String title ,String body, int aid) {
		String sql ="update article set title = ? , body = ? , where id = ?";
		return db.updateQuery(sql, title, body, aid);
	}
	
	public int insertArticle(String title, String body) {
		String sql = "insert into article set title = ? , body = ?, nickname = '익명', regDate = NOW(), hit = 0";
		return db.updateQuery(sql, title, body);
	}
	
	public int deleteArticle(int aid) {
		String sql = "delete from article where id = ?";
		return db.updateQuery(sql, aid);
		
	}
	
	public Article getArticleById(int aid) {
		String sql = "select * from article where id = ?";
		return db.getRow(sql, new ArticleRowMapper(), aid);
	}
	
	public ArrayList<Reply> getRepliesByArticleId(int id) {
		String sql = "select * from reply where aid = ?";
		return db.getRows(sql, new ReplyRowMapper(), id);
	}
	public void insertReply(int id, String body) {
		String sql = "insert into reply set aid = ?, body = ?, nickname = '익명', regDate = NOW()";
		return db.updateQuery(sql, aid, body);
		
	}
	
	public Article getArticleByTitle(String title) {
		String sql = "select * from article where title like concat_ws('%', ?, '%')";
		return db.getRow(sql, new ArticleRowMapper(), title);
	}

	public Article getArticleByBody(String body) {
		String sql = "select * from article where body like concat_ws('%', ?, '%')";
		return db.getRow(sql, new ArticleRowMapper(), body);

	}

	public Article getArticleBynickname(String nickname) {
		String sql = "select * from article where nickname like concat_ws('%', ?, '%')";
		return db.getRow(sql, new ArticleRowMapper(), nickname);

	}

	public Article getArticleByTitleAndBody(String title, String body) {
		String sql = "select * from article where title = ?, body = ?";
		return db.getRow(sql, new ArticleRowMapper(), title, body);
	}
	
}
