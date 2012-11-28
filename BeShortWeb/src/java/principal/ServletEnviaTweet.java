/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pedro
 */
public class ServletEnviaTweet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    private static final long serialVersionUID = 1L;

    public ServletEnviaTweet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("**** Enviando Tweet ****");
        String tweet = request.getParameter("tweet");
        if (tweet.equals("")) {
            PrintWriter out;
            response.setContentType("text/plain; charset=utf-8");
            out = response.getWriter();
            out.print("<FONT COLOR=\"#FF0000\"><b>Tweet sem conteúdo!</b></FONT>");
            return;
        }
        String user = request.getParameter("user");
        System.out.println(user + " || " + tweet);

        String tw = "";
        String[] msgAux = tweet.split(" ");
        for (int i = 0; i < msgAux.length; i++) {
            if (IsURL(msgAux[i], 1) || IsURL(msgAux[i], 0)) {
                Encurta enc = new Encurta(msgAux[i]);

                tw = tw.concat(" " + enc.getURLCurta());
            } else {
                tw = tw + " " + msgAux[i];
            }
        }

        if (tw.length() > 140) {
            PrintWriter out;
            response.setContentType("text/plain; charset=utf-8");
            out = response.getWriter();
            out.print("<FONT COLOR=\"#FF0000\">Tweet com mas de 141 caracteres!</FONT>");
            return;
        }

        EnviaTweet et = new EnviaTweet(user, tw);
        
        PrintWriter out;
        response.setContentType("text/plain; charset=utf-8");
        out = response.getWriter();
        //out.print(a);
    }

    private static boolean IsURL(String s, int mode) {
        String pattern;
        if (mode == 1) {
            pattern = "\\b(https|http|ftp|gopher|telnet|file|notes|ms-help)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        } else {
            pattern = "^(www|ftp).[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        }

        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }
}
