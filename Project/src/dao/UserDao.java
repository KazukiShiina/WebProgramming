package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * ユーザテーブル用のDao
 * @author takano
 *
 */
public class UserDao {

    /**
     * ログインIDとパスワードに紐づくユーザ情報を返す
     * @param loginId
     * @param password
     * @return
     */
    public User findByLoginInfo(String loginId, String password) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, password);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }
            int idData	= rs.getInt("id");
            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            Date birthData = rs.getDate("birth_date");

            return new User(idData,loginIdData, nameData,birthData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }


    /**
     * 全てのユーザ情報を取得する
     * @return
     */
    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user where id >= 2";

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            // rs.next() = レコードを次に進める
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }


    public void createUser(String loginId, String password, String rePassword, String name, Date birthData ) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // INSERT文を準備
            String sql = "INSERT INTO user(login_id, name, birth_date, password,create_date,update_date)"
            		+ "VALUES(?,?,?,?,now(),now())";

             // INSERTを実行
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, name);
            pStmt.setDate(3, birthData);
            pStmt.setString(4, password);

            int result = pStmt.executeUpdate();

            //追加された行数を出力
            System.out.println("INSERT");
            System.out.println(result);
            pStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public User UserDetail(String id) {

        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE id = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            int idData = rs.getInt("id");
            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            Date   birthData = rs.getDate("birth_date");
            String createData = rs.getString("create_date");
            String updateData = rs.getString("update_date");
            String passData = rs.getString("password");
            return new User(idData,loginIdData, nameData,birthData,passData,createData,updateData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
    public void InfoUpdate(String id,String loginId, String password, String rePassword, String name, Date birthData ) {
    	 Connection conn = null;
         try {
             // データベースへ接続
             conn = DBManager.getConnection();

             System.out.println("ID:"+id);
             System.out.println("ログインID:"+loginId);
             System.out.println("パスワード:"+password);
             System.out.println("パスワード(確認):"+rePassword);
             System.out.println("名前:"+name);
             System.out.println("生年月日:"+birthData);
             // UPDATE文を準備
             String sql = "UPDATE user SET password=?, name=?, birth_date=? WHERE id=?";

             // UPDATEを実行
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, password);
            pStmt.setString(2, name);
            pStmt.setDate(3, birthData);
            pStmt.setString(4,id);

            int result = pStmt.executeUpdate();

            //更新された行数を出力
            System.out.println("UPDATE");
            System.out.println(result);
            pStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void HidInfoUpdate(String id,String loginId, String hidPassword, String hidRePassword, String name, Date birthData ) {
   	 Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            System.out.println("ID:"+id);
            System.out.println("ログインID:"+loginId);
            System.out.println("隠しパスワード:"+hidPassword);
            System.out.println("隠しパスワード(確認):"+hidRePassword);
            System.out.println("名前:"+name);
            System.out.println("生年月日:"+birthData);
            // UPDATE文を準備
            String sql = "UPDATE user SET password=?, name=?, birth_date=? WHERE id=?";

            // UPDATEを実行
           PreparedStatement pStmt = conn.prepareStatement(sql);
           pStmt.setString(1, hidPassword);
           pStmt.setString(2, name);
           pStmt.setDate(3, birthData);
           pStmt.setString(4,id);

           int result = pStmt.executeUpdate();

           //更新された行数を出力
           System.out.println("UPDATE");
           System.out.println(result);
           pStmt.close();
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           // データベース切断
           if (conn != null) {
               try {
                   conn.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       }
   }

    public void InfoDelete(String id) {

    		Connection conn = null;

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // DELETE文を準備
            String sql = "DELETE FROM user WHERE id = ?";

             // DELETEを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            int result = pStmt.executeUpdate();
            System.out.println(result);


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
    }
    public User findLoginId(String loginId) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }
            String loginIdData = rs.getString("login_id");

            return new User(loginIdData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    public List<User> findUser() {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id LIKE '%文字列%'";

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            // rs.next() = レコードを次に進める
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }
}