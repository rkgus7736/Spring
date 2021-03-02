package papago;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import org.json.JSONObject;

public class PaPaGoMain {

	public static void main(String[] args) {

        String clientId = "82iJi0ioIjI0fiRsysnw";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "A6cdSpMPSF";//애플리케이션 클라이언트 시크릿값";
        try {
        	String str = JOptionPane.showInputDialog("번역할 문장 입력");
        	String text = URLEncoder.encode(str, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=ko&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
	       	//에러코드 출력해보는것. 에러코드도 로그에 저장을 해주는게 좋음
            	System.out.println(responseCode);
            	br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            //String이랑 buffer의 차이점 : 
            	//String은 원본 값을 바꾸지 못함
            	//buffer는 해당메모리에서 원본 값을 바꿔가며 작업 가능
            System.out.println(response.toString());
            
            if(responseCode != 200) {
            	//true를 넣는 이유는 기존에 있던 에러기록이 사라지면 안되서
				//fos는 순수한 데이터이나 파일을 원초적으로 출력하는 것(byte 기준으로 출력하는것)
            	//writer는 간단하게 텍스트만 찍어내는 것(character기준으로 출력하는것)
            	FileWriter fw = new FileWriter("error.txt",true);
            	PrintWriter pw = new PrintWriter(fw);
					//json 객체로 에러 코드를 받아옴
            	JSONObject obj = new JSONObject(response.toString());
            		//로그에 에러난 날짜 기록
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            	String date = sdf.format(Calendar.getInstance().getTime());
	            	//에러 메세지 기록
            	String msg = date + "\t" + responseCode + "\t" + obj.getString("errorCode")
            	+ "\t" + obj.getString("errorMessage");
            	pw.println(msg);
            	pw.flush();
            	pw.close();
            	fw.close();
            	
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
