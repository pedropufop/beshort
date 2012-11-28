/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 *
 * @author Pedro
 */
public final class GetTimeline {

    List<Status> userT;
    List<Status> friendsT;

    public GetTimeline(String name) {
        TwitterFactory factory = new TwitterFactory();
        AccessToken accessToken = loadAccessToken(name);
        Twitter twitter = factory.getInstance();
        twitter.setOAuthConsumer("a1xbFj8tmdeTWcqNV6rg",
                "Q94uXILhACGHR5glCPYpERMHj6FQDjTHCNLgzaalpI");
        twitter.setOAuthAccessToken(accessToken);

        try {
            userT = twitter.getUserTimeline();
        } catch (TwitterException te) {
            JOptionPane.showMessageDialog(null, te.getMessage(),
                    "Funcionamento BeShort", JOptionPane.ERROR_MESSAGE);
        }
        try {
            friendsT = twitter.getFriendsTimeline();
        } catch (TwitterException te) {
            JOptionPane.showMessageDialog(null, te.getMessage(),
                    "Funcionamento BeShort", JOptionPane.ERROR_MESSAGE);
        }
    }

    public SortedMap<Integer, String> getTweets(int modo) {
        SortedMap<Integer, String> tweet = new TreeMap<Integer, String>();
        for (Status status : userT) {
            String tw = status.getText();
            int date = date(status.getCreatedAt().toString());
            if (modo == 1) {
                String[] msgAux = tw.split(" ");
                tw = "";
                for (int i = 0; i < msgAux.length; i++) {
                    if (msgAux[i].indexOf("b://") != -1) {
                        Desencurta desenc = new Desencurta(msgAux[i]);
                        tw = tw.concat(desenc.getURLLonga() + " ");
                    } else {
                        tw = tw + msgAux[i] + " ";
                    }
                }
                tweet.put(date, tw);
            } else {
                tweet.put(date, status.getText());

            }
        }

        for (Status status : friendsT) {
            String tw = status.getText();
            int date = date(status.getCreatedAt().toString());
            if (modo == 1) {
                String[] msgAux = tw.split(" ");
                tw = "";
                for (int i = 0; i < msgAux.length; i++) {
                    if (msgAux[i].indexOf("b://") != -1) {
                        Desencurta desenc = new Desencurta(msgAux[i]);
                        tw = tw.concat(desenc.getURLLonga() + " ");
                    } else {
                        tw = tw + msgAux[i] + " ";
                    }
                }
                tweet.put(date, tw);
            } else {
                tweet.put(date, status.getText());
            }
        }
        return tweet;
    }

    public SortedMap<Integer, String> getImgName() {
        SortedMap<Integer, String> img = new TreeMap<Integer, String>();
        for (Status status : userT) {
            int date = date(status.getCreatedAt().toString());
            img.put(date, status.getUser().getProfileImageURL().toString()+" @"+status.getUser().getScreenName().toString());
        }
        for (Status status : friendsT) {
            int date = date(status.getCreatedAt().toString());
            img.put(date, status.getUser().getProfileImageURL().toString()+" @"+status.getUser().getScreenName().toString());
        }
        return img;
    }

    public AccessToken loadAccessToken(String name) {
        String token, tokenSecret;
        if (name.equals("pedropufop")) {
            token = "195827785-pmegqRNoYUrIlAoEJiiMgLi0c1cKxBqF0ACPSxLY";
            tokenSecret = "ISZjbMJQnDBDhUuHTDAtdy8OqJW14CUMvWDTc00GZw";
        } else {
            if (name.equals("BeShort2012_1")) {
                token = "582982025-xoRfDloGbypvlSpxwTGEXmyee1qw8z2wczax2IoU";
                tokenSecret = "nkUAWjEQ5qQBougDcDbUUkfYQgG5HFsYaXS4asxLo";
            } else {
                token = "713582869-N4CmW37qnznTr9KPqMIxuK8QUJjWbkN0lgcJ0N34";
                tokenSecret = "vG4gJLx4rmxkyvFGPTZjdcdz4Ma7LrJPZACdLJE4mSc";

            }
        }
        return new AccessToken(token, tokenSecret);
    }

    public int date(String date) {

        String[] dateCompleta = date.split(" ");
        System.out.println("## "+ date);
        String[] hora = dateCompleta[3].split(":");
        int tempo1 = Integer.parseInt(hora[2]) + Integer.parseInt(hora[1]) * 60 + Integer.parseInt(hora[0]) * 60 * 60;
        int tempo2 = Integer.parseInt(dateCompleta[2]) * 24 * 60 * 60;
        int tempo3 = 30 * 24 * 60 * 60;

        if (dateCompleta[1].equals("Jan")) {
            tempo3 = tempo3 * 1;
        } else if (dateCompleta[1].equals("Feb")) {
            tempo3 = tempo3 * 2;
        } else if (dateCompleta[1].equals("Mar")) {
            tempo3 = tempo3 * 3;
        } else if (dateCompleta[1].equals("Apr")) {
            tempo3 = tempo3 * 4;
        } else if (dateCompleta[1].equals("May")) {
            tempo3 = tempo3 * 5;
        } else if (dateCompleta[1].equals("Jun")) {
            tempo3 = tempo3 * 6;
        } else if (dateCompleta[1].equals("Jul")) {
            tempo3 = tempo3 * 7;
        } else if (dateCompleta[1].equals("Aug")) {
            tempo3 = tempo3 * 8;
        } else if (dateCompleta[1].equals("Sep")) {
            tempo3 = tempo3 * 9;
        } else if (dateCompleta[1].equals("Oct")) {
            tempo3 = tempo3 * 10;
        } else if (dateCompleta[1].equals("Nov")) {
            tempo3 = tempo3 * 11;
        } else if (dateCompleta[1].equals("Dec")) {
            tempo3 = tempo3 * 12;
        }
        System.out.println((tempo1 + tempo2 + tempo3 + Integer.parseInt(dateCompleta[5])));
        return (-(tempo1 + tempo2 + tempo3 + Integer.parseInt(dateCompleta[5])));
    }
}
