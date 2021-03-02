import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class BookSearchMain {
	public static void main(String[] args) {
		String txt = JOptionPane.showInputDialog("검색할 책 정보 입력");
		String clientId = "Ixr7J9as6Lig1rXFlWoi";
		String clientSecret = "ZUHNzc5F4n";

		try {
			txt = URLEncoder.encode(txt, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
			//sort=date : 최신 날짜순으로 정렬 
		String apiURL = "https://openapi.naver.com/v1/search/book?query=" + txt + "&sort=date";
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
				System.out.println(arr.getJSONObject(i).getString("image"));
				String str[]= arr.getJSONObject(i).getString("link").split("=");
				downloadImage(arr.getJSONObject(i).getString("image"),str[1]);
				
			}
				
			br.close();
			con.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	//static 에서 static을 받아서 static으로 지정해줘야함
	//웹에 있는 이미지 					 다운로드 경로,	 책제목 			책제목으로 파일명을 만듬
	public static void downloadImage(String url,String title) {
		try {
			URL imgUrl = new URL(url);
			URLConnection conn = imgUrl.openConnection(); //이미지 파일과 연결
			
			InputStream is = conn.getInputStream();
			FileOutputStream fos = new FileOutputStream(title + ".jpg");
			
			//is에서 받은 내용을 fos를 통해서 출력
			byte[] arr = new byte[1024];
			while(true) {
				int count = is.read(arr);//웹에서 읽어옴
				if(count == -1) break; //적어도 1byte는 읽어왔다.
				fos.write(arr,0,count);//읽어온 내용을 파일로 출력
			}
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
