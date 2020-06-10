package edu.escuelaing.arsw.networking;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author vashi
 */
public class Browser {

    public static void main(String[] args) throws Exception {
        URL google;
        if (args.length == 0 || "".equals(args[0])) {
            google = new URL("http://www.google.com/");
        } else {
            google = new URL(args[0]);
        }
        try (BufferedReader reader
                = new BufferedReader(new InputStreamReader(google.openStream()))) {
            String inputLine = null;
            FileWriter myWriter = new FileWriter("resultado.html");
            while ((inputLine = reader.readLine()) != null) {
                myWriter.write(inputLine);
            }
            myWriter.close();
        } catch (IOException x) {
            System.err.println(x);
        }
    }

}
