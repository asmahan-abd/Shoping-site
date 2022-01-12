import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

//@WebServlet(name = "shopingcart", value = "/shopingcart")

public class Shoppingservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        response.setHeader("pragma", "no-cookies");
        PrintWriter out = response.getWriter();
        out.print("<HTML><HEAD><TITLE>Electronic Shop</TITLE>"+
                "</HEAD><BODY><FORM METHOD=POST ACTION=");
        out.print(response.encodeRedirectURL(request.getRequestURI()));
        out.print("><INPUT TYPE=SUBMIT NAME=book VALUE="+
                "\"Put a BOOK into the shopping cart\">"+
                "<INPUT TYPE=SUBMIT NAME=bar VALUE="+
                "\"Put a LINE into the shopping cart\">"+
                "<INPUT TYPE=SUBMIT NAME=view VALUE="+
                "\"See the shopping cart contents\">"+
                "<INPUT TYPE=SUBMIT NAME=buy VALUE="+
                "\"Buy the shopping cart contents\">"+
                "</FORM></BODY></HTML>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String texts;

        HttpSession session = request.getSession(true);
        if(session.isNew())
        {
            session.setAttribute("book", new int[] { 0 });
            session.setAttribute("line", new int[] { 0 });
        }

        int[] book = (int[])session.getAttribute("book");
        int[] lin = (int[])session.getAttribute("line");

        if(request.getParameter("book") != null)
        {
            book[0]++;
            texts = "Bought a BOOK. You now have "+book[0]+".";
        }
        else if(request.getParameter("book") != null)
        {
            lin[0]++;
            texts = "Bought a LINE. You now have "+lin[0]+".";
        }
        else if(request.getParameter("buy") != null)
        {
            session.invalidate();
            texts = "Your order for "+book[0]+" BOOKs and "+lin[0]+
                    " BARs has been accepted. Your shopping cart is empty now.";
        }
        else
        {
            texts = "You have "+book[0]+" FOOs and "+lin[0]+
                    " BARs in your shopping cart.";
        }

        response.setContentType("text/html");
        response.setHeader("pragma", "no-ccookie");
        PrintWriter out = response.getWriter();
        out.print("<HTML><HEAD><TITLE>Shopping Cart</TITLE></HEAD><BODY>");
        out.print(texts);
        out.print("<HR><A HREF=\"");
        out.print(response.encodeRedirectURL(request.getRequestURI()));
        out.print("\">Back to the shop</A></BODY></HTML>");
        out.close();
    }

    @Override
    public String getServletInfo()
    {
        return "ShoppingCartServlet from Lab2 servlet tasks";
    }
}
