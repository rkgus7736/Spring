package news;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class NaverNewsRun {
	private static NaverNewsRun instance = new NaverNewsRun();
	
	private NaverNewsRun() {
		super();
	}

	public static NaverNewsRun getInstance() {
		if(instance==null)
			instance = new NaverNewsRun();
			return instance;
	}

	public String searchNaverNews(String text) {
		String clientId = "Ixr7J9as6Lig1rXFlWoi";
		String clientSecret = "ZUHNzc5F4n"; 
		try {
			text = URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text + "&sort=date&start=1&display=20";
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseMessage = "";
		System.out.println(apiURL);
		URL url;
		try {
			url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				responseMessage += str;
			}
			System.out.println(responseMessage);
			con.disconnect();
		} catch (Exception e) {
			FileWriter fw;
			try {
				fw = new FileWriter("exception.txt",true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(e.getMessage());
				System.out.println(e.getMessage());
				pw.close();
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return responseMessage;
	}

}
