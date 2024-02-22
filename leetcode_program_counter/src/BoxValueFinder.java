
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BoxValueFinder {
    private String result="";

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void formatProfileId(String profileLink){
        try{
            int[] value;
                int index = profileLink.indexOf("https://leetcode.com/");
                if(index == -1) // user name
                {
                    String url = "https://leetcode.com/" + profileLink + "/";
                    String lineValue = lineFinder(url);
                    if(!lineValue.isEmpty())
                    {
                        value = valueFinder(lineValue);
                    }
                    else{
                        InvalidInput invalidInput = new InvalidInput();
                        invalidInput.invalidUserName();
                        return ;
                    }
                }
                // profile link
                else{
                    String lineValue = lineFinder(profileLink);

                    if(!lineValue.isEmpty())
                    {
                        value = valueFinder(lineValue);
                    }
                    else{

                        InvalidInput invalidInput = new InvalidInput();
                        invalidInput.invalidProfileLink();

                        return ;
                    }
                    value = valueFinder(lineValue);
                }
            displayValue(value);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String lineFinder(String urll){
        try{
            @SuppressWarnings("deprecation")
            URL url = new URL(urll);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Setting User-Agent in header
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = connection.getResponseCode();
            //System.out.println(responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Reading the response
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line;

                while ((line = reader.readLine()) != null) {

                    if(line.contains("{\"acSubmissionNum"))
                    {
                        return line;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public void displayValue(int [] value){
        try{
            String format = String.format("Easy     : %s,Medium : %s,Hard     : %s,Total     : %s",value[1],value[2],value[3],value[0]);

            setResult(format);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int[] valueFinder(String str){
        int index = str.indexOf("{\"acSubmissionNum");
        int start = index;
        int []count = new int[4];
        Stack <Character> st = new Stack<>();

        if(index == -1) return null;
        st.push(str.charAt(index++));

        while(!st.isEmpty()){
            if(str.charAt(index) == '{')
                st.push(str.charAt(index));
            else if (str.charAt(index) == '}')
                st.pop();
            index++;
        }

        int end = index;

        String solvedCount = str.substring(start,end);
        int k=0;

        for(int i=0;i<solvedCount.length();i++){
            if(Character.isDigit(solvedCount.charAt(i))){
                int val=0;
                while(Character.isDigit(solvedCount.charAt(i))){
                    String stringInteger = String.valueOf(solvedCount.charAt(i));
                    val = val * 10 + Integer.parseInt(stringInteger);
                    i++;
                }
                count[k++] = val;
            }
        }

        return count;
    }
}