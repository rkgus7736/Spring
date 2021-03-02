import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;
public class WeatherTodayMain {

	public static void main(String[] args) {
		//오늘 날씨 정보를 전부 조회 (온도, 최대온도,최저온도,강수확률)
			//json이 아니라 XML로 받을거라면 dataType은 받지않아도 됨
		String nx, ny, baseTime, serviceKey, dataType, baseDate, pageNo, numOfRows, url;
		nx = "57"; //경도
		ny = "127"; //위도
		url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
		serviceKey = "ysZQwJOYJcm4JwGt8aivwpYj0JsSsx3s7Qvi8LM7JNezqsBwIPAOuP93idLfBqsY%2Bcc5ciXm0dO3%2FdMh3fy4vA%3D%3D";
		dataType = "json";
		pageNo ="1";
		numOfRows="88";
		baseTime = "0200";
		//년월일 뽑는 방법
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		//전날 날짜 출력
		baseDate = sdf.format(cal.getTime());
		System.out.println(baseDate + " " + baseTime);
		String apiUrl = url + "?serviceKey="+serviceKey+"&base_date="+baseDate+"&base_time="+baseTime+"&nx="+nx+"&ny="+ny+"&numOfRows="+numOfRows+"&pageNo=1&dataType="+dataType;
		System.out.println(apiUrl);
		try {
			URL u = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			//보내주기
			conn.setRequestMethod("GET");
			//응답
			String result = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			while(true) {
				String str = br.readLine();
				if(str==null) break;
				result += str;
			}
			System.out.println(result);
			JSONObject json = new JSONObject(result); 
			JSONArray arr = json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
			for(int i=0;i<arr.length();i++) {
				String str = arr.getJSONObject(i).getString("category");
					switch(str) {
					case "T3H":
						System.out.println("T3H : "+ arr.getJSONObject(i).getString("fcstValue") + " " + arr.getJSONObject(i).getString("fcstTime"));
						break;
					case "TMX":
						System.out.println("TMX : "+ arr.getJSONObject(i).getString("fcstValue") + " " + arr.getJSONObject(i).getString("fcstTime"));
						break;
					case "TMN":
						System.out.println("TMN : "+ arr.getJSONObject(i).getString("fcstValue") + " " + arr.getJSONObject(i).getString("fcstTime"));
						break;
					}
					
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
