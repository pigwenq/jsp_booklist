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

/**
 * Servlet implementation class Find
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
		
		Connection conn = DBConn.getConn();
		String sql=null;
		
		//获取类型列表
		try {
			sql = "select * from booktype";
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
		
		
		//获取书籍信息
		
		
		List<Book> books = new ArrayList<Book>();
		try {
			String name = request.getParameter("findOfName");
			name=name==null?"":name;
			
			
			String typename = request.getParameter("booktype");
			typename=typename==null?"":typename;
			
			//保存搜索内容，返回时依旧显示
			request.setAttribute("findOfName", name);
			request.setAttribute("booktype", typename);
			
			sql = "select * from booklist A join booktype B "
					+ "on A.typeid = B.id where A.bookname like ? and B.name like ?";
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,"%" + name + "%");
			pst.setString(2,"%" + typename + "%");
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				Book book = new Book();
				book.bookId = rs.getInt("bookId");
				book.bookname = rs.getString("bookname");
				book.price = rs.getDouble("price");
				book.typename = rs.getString("name");
				book.photo = rs.getString("photo");
				book.bookmsg = rs.getString("bookmsg");
				books.add(book);

			}
			request.setAttribute("books", books);
			//转发到成功的页面
			request.getRequestDispatcher("main.jsp").forward(request, response);
			
			
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
