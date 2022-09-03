package BookTown;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartSubmit
 */
@WebServlet("/CartSubmit")
public class CartSubmit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartSubmit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//用户获取
		HttpSession se = request.getSession();
		
		//未登录
		if(se.getAttribute("loginTag")==null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		String select = request.getParameter("select");
		if(select==""||select==null) {
			response.sendRedirect("MyCart");
			return;
		}
		
		
		//书籍Id获取
		String[] bookids = select.split("\\*");
		
		Connection conn=DBConn.getConn();
		PreparedStatement pst=null;
		String sql =null;
		ResultSet rs = null;
		
		
		//用户id获取
		int userid = -1;
		try {
			sql = "select *from user where username = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, (String)se.getAttribute("loginTag"));
			rs = pst.executeQuery();
			rs.next();
			userid = rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(String bookid :bookids) {
			Goods good = new Goods();
			good.bookid = Integer.valueOf(bookid);
			good.userid = userid;
			double price = 0;
			//查询购物车的数量
			try {
				sql = "select * from cart A join booklist B on A.bookid=B.bookid "
						+ " where A.bookid = ? and userid = ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, bookid);
				pst.setInt(2, userid);
				rs = pst.executeQuery();
				rs.next();
				good.num = rs.getInt("num");
				price = rs.getDouble("price");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//执行数据库的提交
			try {
				Timestamp time = new Timestamp(System.currentTimeMillis());
				sql="insert into goods value(null,?,?,?,?,?,?)";
				pst = conn.prepareStatement(sql);
				pst.setInt(1, good.bookid);
				pst.setInt(2, good.userid);
				pst.setInt(3, good.num);
				pst.setString(4, "已提交");
				pst.setTimestamp(5, time);
				pst.setDouble(6, good.num*price);
				pst.execute();
				
				//删除购物车
				sql = "delete from cart where bookid = ? and userid = ?";
				pst = conn.prepareStatement(sql);
				pst.setInt(1,good.bookid);
				pst.setInt(2,good.userid);
				pst.execute();
				
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		try {
			rs.close();
			pst.close();
			conn.close();
			response.sendRedirect("MyCart");
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
