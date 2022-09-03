package BookTown;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangeCart
 */
@WebServlet("/CartUp")
public class CartUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String bookId = request.getParameter("bookId");//获取书籍信息
		String loginTag = (String)((request.getSession()).getAttribute("loginTag"));//获取登陆用户名
		//未登录
		if(loginTag==null) {
			response.sendRedirect("login.jsp");
			
			return;
		}
		
		Connection conn=DBConn.getConn();
		PreparedStatement pst=null;
		String sql =null;
		ResultSet rs = null;
		int userid=0;
		//获取登陆人id
		{
			sql="select * from User where username = ?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, loginTag);
				rs = pst.executeQuery();
				rs.next();
				userid = rs.getInt("id");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		//新增购物车或商品+1
			sql = "select * from Cart where bookId= ? and userid =?";
			try {
				pst= conn.prepareStatement(sql);
				pst.setString(1, bookId);
				pst.setInt(2,userid);
				rs = pst.executeQuery();
				//该用户存在该商品
				if(rs.next()) {
					int num = rs.getInt("num");
					sql = "update Cart set num = ? where bookId= ? and userid =?";
					pst= conn.prepareStatement(sql);
					pst.setInt(1, ++num);
					pst.setString(2, bookId);
					pst.setInt(3,userid);
					pst.execute();
				}else {
					//不存在
					sql = "insert into Cart values(?,?,1)";
					pst = conn.prepareStatement(sql);
					pst.setString(1, bookId);
					pst.setInt(2,userid );
					pst.execute();
				}
				rs.close();
				pst.close();
				conn.close();
				
				
				String source = request.getParameter("source");
			
				source= source.equals("BookMsg")?"BookMsg?bookId="+bookId:source;
			
				response.sendRedirect(source);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
