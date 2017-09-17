package DBLesson02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WordDAO {

	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public int registWords(ArrayList<Word> words) {
		int result = 0;
		try {
			String SQL = "INSERT INTO dictionary VALUES (?, ?)";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useUnicode=true&characterEncoding=utf8", "root", "");

			//日本語と英単語を登録する文
			for(Word tmp : words){
				stmt = con.prepareStatement(SQL);
				stmt.setString(1, tmp.getEnglish());
				stmt.setString(2, tmp.getJapanese());
				stmt.executeUpdate();
				result++;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if ( stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;	// 結果を返す
	}
	public ArrayList<Word> getWords(){
		ArrayList<Word> words = new ArrayList<>();
		try{
			String SQL = "SELECT english, japanese FROM dictionary";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/testdb?useUnicode=true&characterEncoding=utf8", "root", "");

			stmt = con.prepareStatement(SQL);
			rs = stmt.executeQuery();

			while(rs.next()){
				Word wd = new Word(rs.getString("english"), rs.getString("japanese"));
				words.add(wd);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if ( stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if ( con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return words;	// 結果を返す
		}
}