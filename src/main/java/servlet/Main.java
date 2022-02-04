// リスト10-8の状態
package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AddMutterListLogic;
import model.GetMutterListLogic;
import model.Mutter;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// つぶやきリストをアプリケーションスコープから取得
//    ServletContext application = this.getServletContext();
//    List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");
		// 取得できなかった場合は、つぶやきリストを新規作成して
		// アプリケーションスコープに保存
//    if (mutterList == null) {要らなくなった記述。
//      mutterList = new ArrayList<>();
//      application.setAttribute("mutterList", mutterList);
//    }

		// つぶやきリストを取得して、リクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();// sql実行してid,name,textをMutterList型のmutterに格納までやってくれる。
		HttpSession session = request.getSession();
		session.setAttribute("mutterList", mutterList);// sesstionスコープにsqlの実行内容をmutterListという名前で保存しておく。

		// ログインしているか確認するため
		// セッションスコープからユーザー情報を取得
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) { // ログインしていない場合

			System.out.println("ifがnullになってる");
			// リダイレクト
			response.sendRedirect("index.jsp");// ここがhtmlで間違ってる。

		} else { // ログイン済みの場合
			// フォワード
			System.out.println("ifがelseでmain.jspに飛ばせている。成功。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);

		}
	}
	
	// つぶやきを入力されたときの処理。
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("dopostの実行");
		AddMutterListLogic addMutterListLogic = new AddMutterListLogic();
		boolean createmutterList = addMutterListLogic.execute();
//		String setList = (String)createmutterList.setAttribute();
		boolean mutterList = addMutterListLogic.execute();// sql実行してid,name,textをMutterList型のmutterに格納までやってくれる。
		HttpSession session = request.getSession();
		
		session.setAttribute("mutterList", mutterList);// sesstionスコープにsqlの実行内容をmutterListという名前で保存しておく。
		
		
		request.setCharacterEncoding("UTF-8");
		// リクエストパラメータの取得
		String text = request.getParameter("text");
//		int id = Integer.parseInt(request.getParameter("id"));
		// 入力値チェック
		if (text != null && text.length() != 0) {
			// アプリケーションスコープに保存されたつぶやきリストを取得
//	      ServletContext application = this.getServletContext();要らなくなった記述。
//			List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

			// セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			System.out.println("sessionからloginuserを取得");
//			 つぶやきをつぶやきリストに追加 db使うのに改変。
//			Mutter mutter = new Mutter(loginUser.getName(), text);
			// addMutterListLogicをよびだし、executeメソッドを実行して
//			AddMutterListLogic addmutterList = new AddMutterListLogic();
//			List<Mutter> add = addmutterList.execute();
			
			
			List<Mutter> mutterList = (List<Mutter>) session.getAttribute("mutterList");
			List<Mutter> addmutter = ((List<Mutter>) mutterList);
			System.out.println(addmutter);
			
			String gettext = (String) session.getAttribute("text");
//			System.out.println("Mutterの生成");
//			create.add

			// createメソッドを実行する。あとで有効化する
//			List<Mutter> mutterList = (List<Mutter>) session.getAttribute("mutterList");
//			System.out.println("Mutterの生成");
//			((AddMutterListLogic) mutterList).execute();
//			
//			PostMutterLogic postMutterLogic = new PostMutterLogic();// listの先頭につぶやきを配置する
//			System.out.println("postMutterLogicの下");

//			postMutterLogic.execute(addmutter, mutterList);

			System.out.println("execute(mutter,)の下");
		} else {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
		}
		// つぶやきリストを取得して、リクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);

		// メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);

	}

}
