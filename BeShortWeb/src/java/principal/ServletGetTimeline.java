/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pedro
 */
public class ServletGetTimeline extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String PATH;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGetTimeline() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name;
        name = request.getParameter("user");
        GetTimeline gu = new GetTimeline(name);
        SortedMap<Integer,String> lista;
        SortedMap<Integer,String> img;
        System.out.println("Inicio1");
        lista = gu.getTweets(1);
        img = gu.getImgName();
            
        String [] vetImg = new String[img.size()];
        
        int i=0;
        for (String im : img.values()) {
            String imA[] = im.split(" ");
            vetImg[i] = "<td align=\"center\"><img src=\""+imA[0]+"\" alt=\"Foto antiga do ICMC\"><font size=\"2\"><b>"+imA[1]+"</b></td>";
            //vetImg[i] = "a";
            i++;
        }
        int a = 0;
        String tabela = "<table border=\"4\" id=\"tab1\" cellspacing=\"10\" cellpadding=\"5\">"
                + "<tr id=\"col1\"><td align=\"center\"><b> User</b></td><td align=\"center\"><b>Tweet Com utilização do BeShort</b></td><td align=\"center\"><b>Lenght</b></td>";
        for (String tw : lista.values()) {
            
            tabela = tabela + "<tr style=\"cursor:default\" onMouseOver=\"javascript:this.style.backgroundColor='#ffffff'\" onMouseOut=\"javascript:this.style.backgroundColor=''\">"
                    +vetImg[a]+"<td>" + (a+1) + "# " + tw + "</td><td align=\"center\">" + (tw.length() - 1) + "</td></tr>";
            a++;
        }
        tabela = tabela + "</table>";

        a = 0;
        System.out.println("Inicio2");
        lista = gu.getTweets(2);
        

        String tabela2 = "<table border=\"4\" id=\"tab2\" cellspacing=\"10\" cellpadding=\"5\">"
                + "<tr id=\"col1\"><td align=\"center\"><b> User</b></td><td align=\"center\"><b>Tweet Sem utilização do BeShort</b></td><td align=\"center\"><b>Lenght</b></td>";
        for (String tw : lista.values()) {
            tabela2 = tabela2 + "<tr style=\"cursor:default\" onMouseOver=\"javascript:this.style.backgroundColor='#ffffff'\" onMouseOut=\"javascript:this.style.backgroundColor=''\">"
                    + vetImg[a]+"<td>" + (a+1) + "# " + tw + "</td><td align=\"center\">" + tw.length() + "</td></tr>";
            a++;
        }
        tabela2 = tabela2 + "</table>";
        
        PrintWriter out;
        response.setContentType("text/plain; charset=utf-8");
        out = response.getWriter();
        out.print(tabela + tabela2);

    }

}
