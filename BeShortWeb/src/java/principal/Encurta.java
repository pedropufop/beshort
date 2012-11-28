/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro
 */
public class Encurta {

    String urlCurta = "b://";

    public Encurta(String urlLonga) {

        //Carregando dicionairo na hash
        try {

            //Reader reader = new InputStreamReader(new FileInputStream("/var/lib/tomcat6/webapps/bs/dicionario.txt"), "UTF-8");
            Reader reader = new InputStreamReader(new FileInputStream("/dicionario.txt"),"UTF-8");
            BufferedReader bf = new BufferedReader(reader);
            Map<String, String> hash = new HashMap<String, String>();


            String line;
            while ((line = bf.readLine()) != null) {
                String a[];
                a = line.split(" ");
                a[1] = a[1].replace("\n", "");
                hash.put(a[0], a[1]);
            }

            //hash.put("http://", "\u0000");
            //hash.put("www.", "\u611c");
            int i = 15;
            int casamento;
            String sub;


//        JOptionPane.showMessageDialog(null,
//                "Certo, Arquivo encontrado",
//                "Funcionamento BeShort", 1);


            while (i > 0 && urlLonga.length() > 0) {

                if (i > urlLonga.length()) {
                    i = urlLonga.length();
                }
                //System.out.println("i1: " + i);
                sub = urlLonga.substring(0, i);
                //System.out.println("Sub: " + sub);
                casamento = 0;
                if (hash.containsKey(sub)) {
                    urlLonga = urlLonga.substring(i, urlLonga.length());
                    urlCurta = urlCurta + hash.get(sub);
                    casamento = 1;
                    i = 16;
                }

                if (i == 2 && casamento == 0) {
                    //System.out.println("i é 2");
                    //System.out.println(urlLonga);
                    urlLonga = urlLonga.substring(1, urlLonga.length());
                    urlCurta = urlCurta + sub.substring(0, 1);
                    i = 16;
                }
                if (i == 1) {
                    //System.out.println("i é 1");
                    if (urlLonga.length() == 1) {
                        urlLonga = "";
                        urlCurta = urlCurta + sub;
                    }
                    //urlLonga = urlLonga.replace(sub[0:1],"");
                    //urlCurta = urlCurta+sub.substring(0,1);
                    //i = tam+1;

                }
                i = i - 1;
                //System.out.println("Longa " + urlLonga);
                //System.out.println("Curta " + urlCurta + "\n");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro, Arquivo não encontrado",
                    "Funcionamento BeShort", 1);
        }
    }

    public String getURLCurta() {
        return urlCurta;
    }
}
