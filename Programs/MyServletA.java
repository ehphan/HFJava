import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
//Besides io, we need to import two of the servlet packages. Remember, these two packages are NOT part of the Java standard libraries -- you have to download them separately.

public class MyServletA extends HttpServlet {
	//Most 'normal' servlets will extend HttpServlet, then override one or more methods.

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Override the doGet for simple HTTP GET messages.
		//The web server calls this method, handing you the client's request (you can get data out of it) and a 'response' object that you'll use to send back a response (a page).

		response.setContentType("text/html");
		//This tells the server (and browser) what kind of 'thing' is coming back from the server as a result of this servlet running.

		PrintWriter out = response.getWriter();
		//The response object gives us an output stream to 'write' information back out to the server.

		String message = "If you're reading this, it worked!";

		out.println("<HTML><BODY>");
		out.println("<H1>" + message + "</H1>");
		out.println("</BODY></HTML>");
		out.close();
		//What we 'write' is an HTML page. THe page gets delivered through the server back to the browser, just like any other HTML page, even though this is a page that never existed until now. In other words, tehre's no .html file somewhere with this stuff in it.
	}

}