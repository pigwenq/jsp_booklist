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

/**
 * Servlet implementation class CartDown
 */
@WebServlet("/CartDown")
public class CartDown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartDown() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String bookId = request.getParameter("bookId");//��ȡ�鼮��Ϣ
		String loginTag = (String)((request.getSession()).getAttribute("loginTag"));//��ȡ��½�û���
		
		//δ��¼
		if(loginTag==null) {
			response.sendRedirect("login.jsp");
			
			return;
		}
		
		//û����Ϣ
		if(bookId==null) {
			response.sendRedirect("main.jsp");
			
			return;
		}
		
		Connection conn=DBConn.getConn();
		PreparedStatement pst=null;
		String sql =null;
		ResultSet rs = null;
		int userid=0;
		//��ȡ��½��id
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
		
		//������Ʒ����-1
		
		try {
				sql = "select * from Cart where bookId= ? and userid =?";
				pst= conn.prepareStatement(sql);
				pst.setString(1, bookId);
				pst.setInt(2,userid);
				rs = pst.executeQuery();
				
				rs.next();
				int num = rs.getInt("num");
				if(num<=1) {
					sql = "delete from Cart where bookid=? and userid = ?";
					pst = conn.prepareStatement(sql);
					pst.setString(1,bookId);
					pst.setInt(2, userid);
					pst.execute();
				}else {
					
					sql = "update Cart set num = ? where bookId= ? and userid =?";
					pst= conn.prepareStatement(sql);
					pst.setInt(1, --num);
					pst.setString(2, bookId);
					pst.setInt(3,userid);
					pst.execute();
					
					
				}
				rs.close();
				pst.close();
				conn.close();
				response.sendRedirect("MyCart");
				return;
			}catch (Exception e) {
				// TODO: handle exception
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
