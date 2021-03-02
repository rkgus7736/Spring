

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

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class NaverSearchRun {
	private static NaverSearchRun instance = new NaverSearchRun();
	
	private NaverSearchRun() {
		
	}
	
	public static NaverSearchRun getInstance() {
		if(instance == null)
			instance = new NaverSearchRun();
		return instance;
	}
	
	public String NaverSearchRun(String txt) {
	String text = JOptionPane.showInputDialog("블로그 검색어 입력");
	String clientId = "PD_ovGcoK3dWZtuqWYq0";
	String clientSecret = "NKKyAlKUYK";
	String result = "";

	try {
		txt = URLEncoder.encode(txt, "utf-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	String apiURL = "https://openapi.naver.com/v1/search/blog?query="+txt+"&sort=date";
	URL url;
	try {
		url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
		BufferedReader br;
		int responseCode = con.getResponseCode();
		if (responseCode == 200) { 
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { 
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
			System.out.println(arr.getJSONObject(i).getString("bloggername"));
		}
			
		br.close();
		con.disconnect();
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}

}

	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("exception.txt"); 
			BufferedReader br = new BufferedReader(fr);
			
			while (true) {
				String str = br.readLine(); 
				if(str == null) break;
				sendLog(str.split("\t")); 
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
