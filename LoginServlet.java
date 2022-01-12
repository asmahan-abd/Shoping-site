import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
class LoginServlet extends HttpServlet {
    private void sendLog(HttpServletResponse response,boolean withErrorTEXT) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Login</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");

        if (withErrorTEXT)
            out.println("Login failed. Please try again.<BR>");

        out.println("<BR>");
        out.println("<BR>Please enter your user name and password.");
        out.println("<BR><FORM METHOD=POST>");
        out.println("<BR>User Name: <INPUT TYPE=TEXT NAME=userName>");
        out.println("<BR>Password: <INPUT TYPE=PASSWORD NAME=password>");
        out.println("<BR><INPUT TYPE=SUBMIT VALUE=Submit>");
        out.println("</FORM>");
        out.println("</BODY>");
        out.println("</HTML>");

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendLog(response, false);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String custName = request.getParameter("custName");
        String pass = request.getParameter("pass");
        Cookie c1 = new Cookie("custName",custName);
        Cookie c2 = new Cookie ("pass", pass);
        response.addCookie(c1);
        response.addCookie(c2);

        if (custName!=null && pass!=null &&
                custName.equals("ismahan") && pass.equals("customer")) {
            response.sendRedirect("/Lab2/ViewServlet");
        }
        else {
            sendLog(response, true);
        }
    }
}

