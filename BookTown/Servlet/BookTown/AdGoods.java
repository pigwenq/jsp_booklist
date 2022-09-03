package BookTown;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class AdUser
 */
@WebServlet("/AdGoods")
public class AdGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdGoods() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		
		HttpSession se = request.getSession();
		
		//未登录
		if(se.getAttribute("adlogintag")==null) {
			response.sendRedirect("adlogin.jsp");
			return;
		}
		
	
		//获取搜索文本框
		String username = request.getParameter("findOfName");	
		username=username==null?"":username;
		
		//获取状态
		String state = request.getParameter("state");
		state=state==null?"":state;
		
		//保存状态
		request.setAttribute("findOfName", username);
		request.setAttribute("state", state);
		
		Connection conn = DBConn.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		
		
		//查询管理员
		try {
			sql = "select * from goods A join booklist B on A.bookid = B.bookid "
					+ "join user C on A.userid = C.id "
					+ "where C.username like ? and A.state like ? order by time desc";
			pst = conn.prepareStatement(sql);
			pst.setString(1, '%'+username +'%');
			pst.setString(2, '%'+state+'%');
			rs = pst.executeQuery();
			
			List<Goods> goodslist = new ArrayList<Goods>();
			
			while(rs.next()) {
				Goods goods = new Goods();
				goods.id = rs.getInt("id");
				goods.bookid = rs.getInt("bookid");
				goods.num = rs.getInt("num");
				goods.state = rs.getString("state");
				goods.bookname = rs.getString("bookname");
				goods.sumprice = Double.valueOf( String.format("%.2f", rs.getDouble("sumprice")));;
				goods.price = rs.getDouble("price");
				goods.time = rs.getTimestamp("time");
				goods.username = rs.getString("username");
				goods.userid = rs.getInt("id");
				
				goodslist.add(goods);
			}
			
			rs.close();
			pst.close();
			conn.close();
			
			

			request.setAttribute("goodslist", goodslist);
			request.getRequestDispatcher("adgoodsList.jsp").forward(request, response);
			
		} catch (SQLException e) {
			
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
