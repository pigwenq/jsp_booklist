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
 * Servlet implementation class MyCart
 */
@WebServlet("/MyCart")
public class MyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCart() {
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
		
		//Î´µÇÂ¼
		if(se.getAttribute("loginTag")==null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		double sum_price=0;
		
		//²éÕÒ
		Connection conn = DBConn.getConn();
		String sql = "select * from booklist A "
				+ "join cart B on A.`bookid` = B.`bookid` "
				+ "where B.`userid` = ( select id from user where username = ?)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, (String)se.getAttribute("loginTag"));
			ResultSet rs = pst.executeQuery();
			
			List<Cart> carts =new ArrayList<Cart>();
			while(rs.next()) {
				
				Book book = new Book();
				book.bookId = rs.getInt("bookid");
				book.bookname = rs.getString("bookname");
				book.price = rs.getDouble("price");
				book.photo = rs.getString("photo");
				
				Cart cart = new Cart();
				cart.book=book;
				cart.num = rs.getInt("num");
				sum_price+=book.price;
				carts.add(cart);
			}
			
			request.setAttribute("carts", carts);
			rs.close();
			pst.close();
			conn.close();
			request.setAttribute("sum_price", sum_price);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
			
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
