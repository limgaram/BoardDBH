package borad;

import java.util.ArrayList;
import java.util.Scanner;

import board.Article.Article;
import board.Article.ArticleDao;
import board.Article.Reply;
import board.Member.Member;
import board.Member.MemberDao;

public class APP {

	private ArticleDao articleDao = new ArticleDao();
	private MemberDao memberDao = new MemberDao();
	private Scanner sc = new Scanner(System.in);
	private Member loginedMember = null;
	private String cmd = "";

	public void start() {

		while (true) {
			inputCommand();

			if (cmd.equals("list")) {
				list();
			} else if (cmd.equals("add")) {
				if (islogin()) {
					addArticle();

				}
			} else if (cmd.equals("update")) {
				if(islogin()) {
					updateArticle();
					
				}
			} else if (cmd.equals("delete")) {
				if(islogin()) {
					deleteArticle();
					
				}
			} else if (cmd.equals("read")) {
				readArticle();
			} else if (cmd.equals("signup")) {
				signup();
			} else if (cmd.equals("signin")) {
				login();
			} else if (cmd.equals("search")) {
				articleSearch();
			} else if (cmd.equals("sort")) {
				articleSort();
			} else {
				notACommand();
			}

		}
	}

	public void articleSort() {

		System.out.println("정렬 대상을 선택해주세요. (like : 좋아요, hit : 조회수)");
		String sortFlag = sc.nextLine();
		System.out.println("정렬 방법을 선택해주세요. (asc : 오름차순, desc : 내림차순)");
		String sortType = sc.nextLine();
		
		ArrayList<Article> sortedArticles = articleDao.getsortedArticles(sortFlag, sortType);
	}

	public boolean islogin() {
		if (loginedMember == null) {
			System.out.println("로그인이 필요한 기능입니다.");
			return false;
		} else {
			return true;
		}

	}

	public void list() {
		ArrayList<Article> articles = articleDao.getArticles();
		printArticles(articles);
	}

	private void inputCommand() {
		if (loginedMember == null) {
			System.out.println("명령어를 입력해주세요 : ");
		} else {
			String loginedUserInfo = String.format("[%s(%s)]", loginedMember.getLoginId(), loginedMember.getNickname());
			System.out.println("명령어를 입력해주세요." + loginedUserInfo);
		}
		cmd = sc.nextLine();

	}

	public void notACommand() {
		System.out.println("올바른 명령어가 아닙니다.");
	}

	public void addArticle() {
		System.out.println("제목 : ");
		String title = sc.nextLine();
		System.out.println("내용 : ");
		String body = sc.nextLine();

		articleDao.insertArticle(title, body, loginedMember.getId());
	}

	public void updateArticle() {
		System.out.println("수정할 게시물 번호 : ");
		int aid = Integer.parseInt(sc.nextLine());

		System.out.println("제목 : ");
		String title = sc.nextLine();
		System.out.println("내용 : ");
		String body = sc.nextLine();

		articleDao.updateArticle(title, body, aid);
	}

	public void deleteArticle() {
		System.out.println("삭제할 게시물 번호 : ");
		int aid = Integer.parseInt(sc.nextLine());
		articleDao.deleteArticle(aid);
	}

	public void readArticle() {
		System.out.println("상세보기 할 게시물 번호 : ");
		int aid = Integer.parseInt(sc.nextLine());

		//상세보기 할 게시물 가져오기.
		Article article = articleDao.getArticleById(aid);
		
		if (article == null) {
			System.out.println("없는 게시물입니다.");
		} else {
			//아이디에 따른 댓글들 가져오기.
			ArrayList<Reply> replies = articleDao.getRepliesByArticleId(article.getId());
			article = articleDao.getArticleById(aid);

			increaseHitByArticleId(article.getId());
			printArticle(article, replies);

			while (true) {
				System.out.println("상세보기 기능을 선택해주세요");
				System.out.println("(1. 댓글 등록, 2. 좋아요, 3. 수정, 4. 삭제, 5. 목록으로) : ");
				int dcmd = Integer.parseInt(sc.nextLine());
				
				if (dcmd == 1) {
					System.out.println("내용을 입력해주세요 : ");
					String body = sc.nextLine();
					articleDao.insertReply(article.getId(), body);
					// Q.replies2
					ArrayList<Reply> replies2 = articleDao.getRepliesByArticleId(article.getId());
					printArticle(article, replies2);
				} else if(dcmd == 2) {
					
				}
				else {
					break;
				}
			}
		}
	}

	private void increaseHitByArticleId(int id) {

	}

	public void signup() {
		System.out.println("아이디 : ");
		String id = sc.nextLine();
		System.out.println("비밀번호 : ");
		String pw = sc.nextLine();
		System.out.println("닉네임 : ");
		String nm = sc.nextLine();

		memberDao.insertMember(id, pw, nm);
	}

	public void login() {
		System.out.println("아이디 : ");
		String id = sc.nextLine();
		System.out.println("비밀번호 : ");
		String pw = sc.nextLine();

		Member target = memberDao.getMemberByLoginIdAndLoginPw(id, pw);

		if (target == null) {
			System.out.println("잘못된 회원정보입니다.");
		} else {
			System.out.println(target.getNickname() + "님! 반갑습니다.");
			loginedMember = target;
		}
	}

	public void articleSearch() {
		System.out.println("검색 항목을 선택해주세요 : ");
		System.out.println("1. 제목, 2. 내용, 3. 제목 + 내용, 4. 작성자");
		int ch = Integer.parseInt(sc.nextLine());

		if (ch == 1) {

			System.out.println("검색할 제목 키워드를 입력해주세요 : ");
			String title = sc.nextLine();

			printArticleByTitle(title);

		} else if (ch == 2) {

			System.out.println("검색할 내용 키워드를 입력해주세요 : ");
			String body = sc.nextLine();

			printArticleByBody(body);

		} else if (ch == 3) {

			System.out.println("검색할 제목과 내용 키워드를 입력해주세요 : ");
			String title = sc.nextLine();
			String body = sc.nextLine();

			printArticleByBody(body);
			articleDao.getArticleByTitleAndBody(title, body);

		} else if (ch == 4) {

			System.out.println("검색할 작성자 키워드를 입력해주세요 : ");
			String nickname = sc.nextLine();

			printArticleByBody(nickname);
			articleDao.getArticleBynickname(nickname);
		}

	}

	public void printArticleByTitle(String title) {
		Article article = articleDao.getArticleByTitle(title);

		System.out.println("번호 : " + article.getId());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("작성자 : " + article.getNickname());
		System.out.println("등록날짜 : " + article.getRegDate());
		System.out.println("조회수 : " + article.getHit());
	}

	public void printArticleByBody(String body) {
		Article article = articleDao.getArticleByBody(body);

		System.out.println("번호 : " + article.getId());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("작성자 : " + article.getNickname());
		System.out.println("등록날짜 : " + article.getRegDate());
		System.out.println("조회수 : " + article.getHit());
	}

	public void getArticleBynickname(String nickname) {
		Article article = articleDao.getArticleBynickname(nickname);

		System.out.println("번호 : " + article.getId());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("작성자 : " + article.getNickname());
		System.out.println("등록날짜 : " + article.getRegDate());
		System.out.println("조회수 : " + article.getHit());
	}

	private void printArticle(Article article, ArrayList<Reply> replies) {
		System.out.println("번호 : " + article.getId());
		System.out.println("제목 : " + article.getTitle());
		System.out.println("내용 : " + article.getBody());
		System.out.println("작성자 : " + article.getNickname());
		System.out.println("등록날짜 : " + article.getRegDate());
		System.out.println("조회수 : " + article.getHit());
		System.out.println("====댓글====");
		for (int i = 0; i < replies.size(); i++) {
			System.out.println("내용 : " + replies.get(i).getBody());
			System.out.println("작성자 : " + replies.get(i).getnickname());
			System.out.println("작성날짜 : " + replies.get(i).getRegDate());
		}

	}

	public void printArticles(ArrayList<Article> articles) {
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);

			System.out.println("번호 : " + article.getId());
			System.out.println("제목 : " + article.getTitle());
			System.out.println("작성자 : " + article.getNickname());
			System.out.println("등록날짜 : " + article.getRegDate());
			System.out.println("조회수 : " + article.getHit());
			System.out.println("==================");
		}
	}
}
