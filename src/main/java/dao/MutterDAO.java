package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;

public class MutterDAO {
	// データベース接続に使用する情報
//  private final String JDBC_URL ="jdbc:h2:tcp://localhost/~/docoTsubu";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/mutter";
	private final String DB_USER = "suzukikohya";
	private final String DB_PASS = "eraSUIZJGNTIH3uEtL$B";
	Connection conn = null;

	public List<Mutter> findAll() {
		List<Mutter> mutterList = new ArrayList<Mutter>();

		// JDBCドライバの読み込み
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT ID,NAME,TEXT FROM MUTTER ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				Mutter mutter = new Mutter(userName, text);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}

	
	
	//sqlで取得し、（?,?)に当てはめたStringが存在していればtrueを返すという内容。
	public boolean create(Mutter mutter) {
		// JDBCドライバの読み込み
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			String sql = "INSERT INTO MUTTER(NAME, TEXT) VALUES(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getText());

			// INSERT文を実行
			int result = pStmt.executeUpdate();
			//sqlで取得し、（?,?)に当てはめたStringが存在していればtrueを返すという内容。
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}}

//	public List<Mutter> add(Mutter mutter) {
//		List<Mutter> mutterList = new ArrayList<Mutter>();
//
//		// JDBCドライバの読み込み
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e1) {
//			// TODO 自動生成された catch ブロック
//			e1.printStackTrace();
//		}
//
//		// データベース接続
//		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
//
//			// INSERT文の準備(idは自動連番なので指定しなくてよい）
//			String sql = "INSERT INTO MUTTER(NAME, TEXT) VALUES(?, ?)";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			// INSERT文中の「?」に使用する値を設定しSQLを完成
//			pStmt.setString(1, mutter.getUserName());
//			pStmt.setString(2, mutter.getText());
//			
			
//			
////			executeUpdateの戻り値は「更新した行数」であるため、これを受け入れる左辺は数字型でなければ受け取れない
//			int rsup = pStmt.executeUpdate();
//			
//			Object request;
//			HttpSession session = request.getSession();
////			int id = result.getInt("ID");addするだけだから、IDは要らない。
//			// とりあえずexecuteだけさせてみる。
////			String userName = rsup.getString("NAME");
////			String text = rsup.getString("TEXT");
//			String userName = (String) session.getAttribute("NAME");
//			String text = (String) session.getAttribute("TEXT");
//			Mutter mutter = new Mutter(userName, text);
//
//			// INSERT文中の「?」に使用する値を設定しSQLを完成
//			pStmt.setString(1, mutter.getUserName());
//			pStmt.setString(2, mutter.getText());
//			
//			// SELECT文の結果をArrayListに格納
//			while (rsup.next()) {
//				mutterList.add(mutter);
//			}
//
//			// INSERT文中の「?」に使用する値を設定しSQLを完成
//			pStmt.setString(1, mutter.getUserName());
//			pStmt.setString(2, mutter.getText());
//
//			// INSERT文を実行
//			result.add(mutter);
//			// SELECT文の結果をArrayListに格納
////			while (rs.next()) {
//			mutterList.add(mutter);
//		}
//	}catch(
//			SQLException e)
//			{
//					e.printStackTrace();
//					return null;
//				}return mutterList;
//}

