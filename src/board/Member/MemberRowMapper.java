package board.Member;
import java.sql.ResultSet;
import java.sql.SQLException;

import borad.RowMapper;

public class MemberRowMapper implements RowMapper<Member> {

	public MemberRowMapper() {
		System.out.println("");
	}
	
	@Override
	public Member getRow(ResultSet rs) throws SQLException {
		
		Member member = new Member();
		
		member.setId(rs.getInt("id"));
		member.setLoginId(rs.getString("loginId"));
		member.setLoginPw(rs.getString("loginPw"));
		member.setNickname(rs.getString("nickname"));
		member.setRegDate(rs.getString("regDate"));
		
		
		return member;
	}

}
