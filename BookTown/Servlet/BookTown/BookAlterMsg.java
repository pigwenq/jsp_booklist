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
 * Servlet implementation class BookAlter
 */
@WebServlet("/BookAlterMsg")
public class BookAlterMsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookAlterMsg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		
		//未登录
		HttpSession se = request.getSession();
		if(se.getAttribute("adlogintag")==null) {
			response.sendRedirect("adlogin.jsp");
			return;
		}

		String bookid = request.getParameter("bookId");
		
		//获取类型列表
		try {
			Connection conn = DBConn.getConn();
			String sql = "select * from booktype";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			List<BookType> booktypes = new ArrayList<BookType>();
			while(rs.next()) {
				BookType booktype = new BookType();
				booktype.id=rs.getInt("id");
				booktype.name=rs.getString("name");
				booktype.msg = rs.getString("msg");
				booktypes.add(booktype);
			}
			request.setAttribute("booktypes", booktypes);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			Connection conn = DBConn.getConn();
			String sql = "select * from booklist A join booktype B on"
					+ " A.typeid = B.id where bookid = ? ";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, bookid);
			ResultSet rs = pst.executeQuery();
			rs.next();
			Book book = new Book();
			book.bookname = rs.getString("bookname");
			book.price = rs.getDouble("price");
			book.bookmsg =  rs.getString("bookmsg");
			book.typename = rs.getString("name");
			book.photo = rs.getString("photo");
			request.setAttribute("book", book);
			
			rs.next();
			pst.close();
			conn.close();
			request.setAttribute("bookid", bookid);
			request.getRequestDispatcher("bookalter.jsp").forward(request, response);
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
