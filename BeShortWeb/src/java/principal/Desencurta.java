/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.*;

/**
 *
 * @author Pedro
 */
public class Desencurta {

    String URLLonga = "";

    public Desencurta(String URLCurta) {
        URLCurta = URLCurta.replace("b://", "");
        
        try {
            //int i = 0;
            String termo[];
            //String sub;
            //Reader reader = new InputStreamReader(new FileInputStream("/var/lib/tomcat6/webapps/bs/dicionario.txt"),"UTF-8");
            Reader reader = new InputStreamReader(new FileInputStream("/dicionario.txt"),"UTF-8");
            BufferedReader bf = new BufferedReader(reader);
            String line;
            while ((line = bf.readLine()) != null){
                termo = line.split(" ");
                termo[1] = (String) termo[1].replace("\n", "");
                if (URLCurta.indexOf(termo[1]) != -1) {
                    URLCurta = URLCurta.replace(termo[1], termo[0]);
                }
            }
            bf.close();
            URLLonga = URLCurta;
            //System.out.println("URL: "+URLLonga);

        } catch (IOException e) {
            URLLonga = e.getMessage();
//            JOptionPane.showMessageDialog(null,
//                    "Erro, Arquivo não encontrado",
//                    "Funcionamento BeShort", 1);
        }
    }

    public String getURLLonga() {
        return URLLonga;
    }
}
