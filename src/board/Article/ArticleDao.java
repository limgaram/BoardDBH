package board.Article;

import java.util.ArrayList;

import borad.DBUtil;

public class ArticleDao {

	private DBUtil db = new DBUtil();

	public ArrayList<Article> getArticles() {
		String sql = "select a.*, m.nickname nickname from article a inner join `member` m on a.id = m.id";
		return db.getRows(sql, new ArticleRowMapper());
	}

	public int updateArticle(String title, String body, int aid) {
		String sql = "update article set title = ? , body = ? , where id = ?";
		return db.updateQuery(sql, title, body, aid);
	}

	public int insertArticle(String title, String body, int mid) {
		String sql = "insert into article set title = ? , body = ?, mid = ?, nickname = '익명', regDate = NOW(), hit = 0";
		return db.updateQuery(sql, title, body, mid);
	}

	public int deleteArticle(int aid) {
		String sql = "delete from article where id = ?";
		return db.updateQuery(sql, aid);

	}

	public Article getArticleById(int aid) {
		String sql = "select a.*, m.nickname nickname from article a inner join `member` m on a.id = m.id where a.id = ?";
		return db.getRow(sql, new ArticleRowMapper(), aid);
	}

	public ArrayList<Reply> getRepliesByArticleId(int id) {
		String sql = "select * from reply where aid = ?";
		return db.getRows(sql, new ReplyRowMapper(), id);
	}

	public int insertReply(int id, String body) {
		String sql = "insert into reply set aid = ?, body = ?, nickname = '익명', regDate = NOW()";
		return db.updateQuery(sql, id, body);

	}

	public int increaseHitByArticleId(int id) {
		String sql = "update article set hit = hit + 1 where id = ? ";
		return db.updateQuery(sql, id);
	}

	public Article getArticleByTitle(String title) {
		String sql = "select * from article where title like concat_ws(? , '%', '%')";
		return db.getRow(sql, new ArticleRowMapper(), title);
	}

	public Article getArticleByBody(String body) {
		String sql = "select * from article where body like concat_ws(? , '%', '%')";
		return db.getRow(sql, new ArticleRowMapper(), body);

	}

	public Article getArticleBynickname(String nickname) {
		String sql = "select * from article where nickname like concat_ws(? , '%', '%')";
		return db.getRow(sql, new ArticleRowMapper(), nickname);

	}

	public Article getArticleByTitleAndBody(String title, String body) {
		String sql = "select * from article where title = ?, body = ?";
		return db.getRow(sql, new ArticleRowMapper(), title, body);
	}

	public ArrayList<Article> getSortedArticles(String sortFlag, String sortType) {
		
		String sql1 = "SELECT a.*, m.nickname FROM article a INNER JOIN `member` m ON a.mid = m.id ORDER BY ";
		String sql2 = sortFlag + " " + sortType;
		
		String sql = sql1 + sql2;
		
		return db.getRows(sql, new ArticleRowMapper());
		
	}

	public Like getLike(int aid, int mid) {
		
		String sql = "select * from `like` where aid = ? and `mid` = ?";
		return db.getRow(sql, new LikeRowMapper(), aid, mid); 
	}

	public void deleteLike(int aid, int mid) {
		String sql = "delete from `like` where aid = ? and `mid` = ?";
		db.updateQuery(sql, aid, mid);
	}
	
	public void insertLike(int aid, int mid) {
		String sql = "insert into `like` set aid = ?, `mid` = ?, regDate = NOW()";
		db.updateQuery(sql, aid, mid);
	}

}
