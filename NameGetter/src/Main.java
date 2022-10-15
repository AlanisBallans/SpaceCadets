import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(getName(getNameLine())); // gets the name from the name line

    }
    private static String getEmailID() { // gets the email ID from user input
        System.out.println("Enter email ID: ");
        BufferedReader idreader = new BufferedReader(new InputStreamReader(System.in)); // creates a reader for the user's input stream
        String emailID = null;
        try {
            emailID = idreader.readLine(); // gets the user's input from the buffer
        } catch (IOException e) { // catches any input errors
            System.out.println("Input error, please try again");
            getEmailID();
        }
        return emailID;

    }

    private static String getNameLine() {
        try {
            String emailID = getEmailID();
            URL url = new URL("https://www.ecs.soton.ac.uk/people/" + emailID);
            URLConnection urlConnection = url.openConnection();
            BufferedReader urlReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            for(int i = 0; i < 13; i++) {
                urlReader.readLine();

            }
            String nameLine = urlReader.readLine();
            urlReader.close();
            return nameLine;



        } catch (Exception e){
            System.out.println("Connection error, please try again later");
            return null;
        }

    }

    private static String getName(String nameLine) {
        try {
            int lastIndex = nameLine.lastIndexOf("\"");
            String name = nameLine.substring(35,lastIndex);
            if (name.length() > 30) {
                System.out.println("Invalid ID, please try again");
                getNameLine();
            }
            return name;
        } catch (Exception e) {
            System.out.println("Something went wrong. Please shout at Alanis.");
            return null;
        }
    }

}