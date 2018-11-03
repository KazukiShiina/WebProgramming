package controller;

import java.io.IOException;

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
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// URLからGETパラメータとしてIDを受け取る
				String id = request.getParameter("id");

				// 確認用：idをコンソールに出力
				System.out.println("ユーザID:"+id);


				// TODO  未実装：idを引数にして、idに紐づくユーザ情報を出力する


						HttpSession session = request.getSession(false);

						if(session == null) {
							response.sendRedirect("LoginServlet");
							return;
						}else {

						// ユーザ一覧情報を取得
						UserDao userDao = new UserDao();
						User userDetail = userDao.UserDetail(id);


				// TODO  未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード ★

						// リクエストスコープにユーザ一覧情報をセット
						request.setAttribute("userDetail", userDetail);

						// ユーザ一覧のjspにフォワード
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userDelete.jsp");
						dispatcher.forward(request, response);
					}
			}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");


			String id = request.getParameter("id");

			// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
			UserDao userDao = new UserDao();
			userDao.InfoDelete(id);



			// ユーザ一覧のページにリダイレクト
			response.sendRedirect("UserListServlet");
			return;


			}
		}

