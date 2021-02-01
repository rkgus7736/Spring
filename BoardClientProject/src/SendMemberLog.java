import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

//쿼츠 적용해서 매일 몇시에 서버로 전송
public class SendMemberLog {

	public static void main(String[] args) {
		
		//FileInputStream도 써도 되지만 불러오기만 할땐 FileReader가 손이 덜 감
		try {
			FileReader fr = new FileReader("error.txt"); 
			BufferedReader br = new BufferedReader(fr);
			
			while (true) {
				String str = br.readLine(); //파일에서 로그 한줄 읽어옴
				if(str == null) break;
				sendLog(str.split("\t")); //서버로 전송
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void sendLog(String[] arr) {
		try {
			String queryString = "";
			String[] paramArr = {"log_date","code_number","message"};
			for(int i=0;i<arr.length;i++) {
				queryString += paramArr[i] + "=" + URLEncoder.encode(arr[i],"utf-8") + "&";
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		try {
			String apiUrl = "http://localhost:9999/sendLog.do?";
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = br.readLine();
			System.out.println(result);
			br.close();
			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}
