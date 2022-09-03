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
 * Servlet implementation class GoodList
 */
@WebServlet("/GoodsList")
public class GoodsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsList() {
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
		
		//δ��¼
		if(se.getAttribute("loginTag")==null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		//����
		Connection conn=DBConn.getConn();
		PreparedStatement pst=null;
		String sql =null;
		ResultSet rs = null;
		
		//�û�id��ȡ
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
		
		//��ȡ�����ı���
		String name = request.getParameter("findOfName");	
		name=name==null?"":name;
		
		//��ȡ״̬
		String state = request.getParameter("state");
		state=state==null?"":state;
		
		//�����������ݣ�����ʱ������ʾ
		request.setAttribute("findOfName", name);
		request.setAttribute("state", state);
		
		
		//��ѯ����
		
		
		try {
			sql = "select * from goods A "
					+ "join booklist B on A.`bookid` = B.`bookid` "
					+ "where A.userid = ? and B.bookname like ? and A.state like ? "
					+ "order by time desc";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userid);
			pst.setString(2, "%"+name+"%");
			pst.setString(3, "%"+state+"%");
			
			rs = pst.executeQuery();
			
			List<Goods> goodslist =new ArrayList<Goods>();
			while(rs.next()) {
				
				Goods goods = new Goods();
				
				goods.id=rs.getInt("id");
				goods.bookname = rs.getString("bookname");
				goods.num = rs.getInt("num");
				goods.photo=rs.getString("photo");
				goods.state = rs.getString("state");
				goods.time = rs.getTimestamp("time");
				goods.sumprice = Double.valueOf( String.format("%.2f", rs.getDouble("sumprice")));
				goodslist.add(goods);
				
			}
			
			request.setAttribute("goodslist", goodslist);
			rs.close();
			pst.close();
			conn.close();
			
			request.getRequestDispatcher("goodsList.jsp").forward(request, response);
			
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
