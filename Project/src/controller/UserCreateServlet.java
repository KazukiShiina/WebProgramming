package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserCreateServlet")
public class UserCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCreateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 未実装：ログインセッションがない場合、ログイン画面にリダイレクトさせる
		HttpSession session = request.getSession(false);

		if(session == null) {
			response.sendRedirect("LoginServlet");
			return;
		}else {

		// ユーザ新規作成のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userCreate.jsp");
		dispatcher.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");



		// リクエストパラメータの入力項目を取得

		try {
				String loginId = request.getParameter("loginId");
				String password = request.getParameter("password");
				String rePassword = request.getParameter("rePassword");
				String name = request.getParameter("name");
				String birthDate = request.getParameter("birthDate");

				if(birthDate.contains(" ") || name.contains(" ") ||password.contains(" ")
						||rePassword.contains(" ") ||loginId.contains(" ")){
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("errMsg", "不正な入力です、入力欄にスペースを含まないでください");
				// ユーザ新規作成のjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userCreate.jsp");
				dispatcher.forward(request, response);
				return;
				}

				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");

				Date birthData = new Date(sdFormat.parse(birthDate).getTime());


				if (name == null || name.isEmpty() ||birthDate == null || loginId == null
						||loginId.isEmpty() || password == null || password.isEmpty()
						||rePassword == null || rePassword.isEmpty()) {
					// リクエストスコープにエラーメッセージをセット
					request.setAttribute("errMsg", "未入力の部分があります。");

					// ユーザ新規作成のjspにフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userCreate.jsp");
					dispatcher.forward(request, response);
					return;

				}else if(!(password.equals(rePassword))) {
					// リクエストスコープにエラーメッセージをセット
					request.setAttribute("errMsg", "パスワードと確認用パスワードが一致しません。");

					// ユーザ新規作成のjspにフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userCreate.jsp");
					dispatcher.forward(request, response);
					return;

				}

		// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
				UserDao userDao = new UserDao();
				User user = userDao.findLoginId(loginId);
				if (user != null) {
					// リクエストスコープにエラーメッセージをセット
					request.setAttribute("errMsg", "そのログインIDは既に使用されています。");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userCreate.jsp");
					dispatcher.forward(request, response);
					return;
				}

				System.out.println(user);

				userDao.createUser(loginId, password,rePassword,name,birthData);


		} catch (ParseException e) {
		e.printStackTrace();
		}
		response.sendRedirect("UserListServlet");


	}
}
