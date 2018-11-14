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
 * Servlet implementation class userUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		// 確認用：idをコンソールに出力
		System.out.println("ユーザID:" + id);

		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect("LoginServlet");
			return;
		} else {

			// TODO  未実装：idを引数にして、idに紐づくユーザ情報を出力する       ★

			// ユーザ一覧情報を取得
			UserDao userDao = new UserDao();
			User userDetail = userDao.UserDetail(id);


			// セッションスコープにユーザ一覧情報をセット
			session.setAttribute("userDetail", userDetail);

			// ユーザ詳細情報更新のjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
			dispatcher.forward(request, response);
		}
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得

		try {
				String id = request.getParameter("id");
				String loginId   = request.getParameter("loginId");
				String password = request.getParameter("password");
				String rePassword = request.getParameter("rePassword");
				String name = request.getParameter("name");
				String birthDate = request.getParameter("birthDate");
				String hidPassword = request.getParameter("hidPassword");
				String hidRePassword = request.getParameter("hidRePassword");

				System.out.println(loginId);
				System.out.println(birthDate);


				if(birthDate.contains(" ") || name.contains(" ") ||password.contains(" ") ||rePassword.contains(" ")){
					// リクエストスコープにエラーメッセージをセット
					request.setAttribute("errMsg", "不正な入力です、入力欄にスペースを含まないでください");

					// ユーザ詳細情報更新のjspにフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
					dispatcher.forward(request, response);
					return;
				}

				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");

				Date birthData = new Date(sdFormat.parse(birthDate).getTime());

				if (name == null || name.isEmpty() ||birthDate == null) {
					// リクエストスコープにエラーメッセージをセット
					request.setAttribute("errMsg", "未入力の部分があります");

					// ユーザ詳細情報更新のjspにフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
					dispatcher.forward(request, response);
					return;

				}else if(!(password.equals(rePassword))) {
					// リクエストスコープにエラーメッセージをセット
					request.setAttribute("errMsg", "パスワードと確認用パスワードが一致しません");

					// ユーザ詳細情報更新のjspにフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
					dispatcher.forward(request, response);
					return;


				}else if((password == null && rePassword == null )|| (password.isEmpty() &&rePassword.isEmpty())
					||(password.isEmpty() && rePassword == null)|| (password == null && rePassword.isEmpty())) {
					// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
					UserDao userDao = new UserDao();
					userDao.HidInfoUpdate(id,loginId,hidPassword,hidRePassword,name,birthData);
						response.sendRedirect("UserListServlet");
						return;

				}else {

					// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
					UserDao userDao = new UserDao();
					userDao.InfoUpdate(id,loginId,password,rePassword,name,birthData);
					response.sendRedirect("UserListServlet");
					return;
				}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
