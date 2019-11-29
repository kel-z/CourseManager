package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WebMessage {
    public WebMessage(){

    }
    // EFFECTS: prints out string from web
    public void welcome() throws IOException {
        // as instructed, code quoted from: http://zetcode.com/articles/javareadwebpage/
        BufferedReader br = null;

        try {
            String theURL = "https://www.students.cs.ubc.ca/~cs-210/2018w1/welcomemsg.html"; //this can point to any URL
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb.substring(0, sb.length() - 14));
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}