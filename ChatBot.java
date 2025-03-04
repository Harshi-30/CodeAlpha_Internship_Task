//this chat bot has been made using the groq api which uses llama3 Ai model. 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class ChatBot{

    public static void main(String[] args) {

        System.out.println("                                      welcome to my AI CHATBOT made by Harshita(intern at CODE-ALPHA)\n");
        while(true){
            try{
            Scanner sc = new Scanner(System.in);
            System.out.print("You: ");
            String command = sc.nextLine();
            System.out.println();
            System.out.print("Bot: ");
            System.out.println(chatbot(command));
            System.out.println();
            }
            catch(Exception e){
                System.out.println("An Exception Occured Please Try Again!");
            }
        }
        // Prints out a response to the question.
    }

    public static String chatbot(String message) {
        String url = "https://api.groq.com/openai/v1/chat/completions";
        String apiKey = "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"; // API key goes here
        String model = "llama3-8b-8192"; 

        try {
            // Create the HTTP POST request
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            // Build the request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // returns the extracted contents of the response.
            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This method extracts the response expected from chatgpt and returns it.
    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content")+10; // Marker for where the content starts.
        int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
        return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
    }
}