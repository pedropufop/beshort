package principal;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import java.io.FileNotFoundException;
import twitter4j.*;
import twitter4j.auth.AccessToken;

/**
 *
 * @author Pedro
 */
public final class EnviaTweet {

    static final Object LOCK = new Object();

    public EnviaTweet(String name, String msg) throws FileNotFoundException {
        //The factory instance is re-useable and thread safe.
        //System.out.println("fim1");
        //TwitterFactory factory = new TwitterFactory();

        AccessToken accessToken = loadAccessToken(name);
        AsyncTwitterFactory factory = new AsyncTwitterFactory();
        AsyncTwitter twitter = factory.getInstance();
        twitter.setOAuthConsumer("a1xbFj8tmdeTWcqNV6rg",
                "Q94uXILhACGHR5glCPYpERMHj6FQDjTHCNLgzaalpI");
        twitter.setOAuthAccessToken(accessToken);

        twitter.addListener(new TwitterAdapter() {

            @Override
            public void updatedStatus(Status status) {
//                JOptionPane.showMessageDialog(null,
//                        "Tweet enviado com sucesso!",
//                        "Funcionamento BeShort", 1);
                synchronized (LOCK) {
                    LOCK.notify();
                }
            }

            @Override
            public void onException(TwitterException e, TwitterMethod method) {
                if (method == UPDATE_STATUS) {
                    // System.out.println(e.getErrorMessage());
//                    JOptionPane.showMessageDialog(null, e.getErrorMessage(),
//                            "Funcionamento BeShort", JOptionPane.ERROR_MESSAGE);

                    synchronized (LOCK) {
                        LOCK.notify();
                        // System.out.println(LOCK.toString());
                    }
                } else {
                    synchronized (LOCK) {
                        LOCK.notify();
                    }
                    throw new AssertionError("Should not happen");
                }
            }
        });

        twitter.updateStatus(msg);
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
}
