import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class BlogSearchMain {

	public static void main(String[] args) {
		String txt = JOptionPane.showInputDialog("블로그 검색어 입력");
		String clientId = "PWbtPqmChTr4FWM7DX8G";
		String clientSecret = "vGqvzkyqxq";

		try {
			txt = URLEncoder.encode(txt, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
			//sort=date : 최신 날짜순으로 정렬 
		String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + txt + "&sort=date";
		URL url;
		try {
			url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			BufferedReader br;
			int responseCode = con.getResponseCode();
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				System.out.println(responseCode);
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String result = "";
			while(true) {
				String str = br.readLine();
				if(str==null)break;
				result += str;
			}
			System.out.println(result);
			JSONObject json = new JSONObject(result);
			JSONArray arr = new JSONArray(json.getJSONArray("items"));
			for(int i=0;i<arr.length();i++) {
				System.out.println(arr.getJSONObject(i).getString("title"));
				System.out.println(arr.getJSONObject(i).getString("link"));
				System.out.println(arr.getJSONObject(i).getString("description"));
			}
				
			br.close();
			con.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
