import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class WeatherTestMain {

	public static void main(String[] args) {
		//오늘 서울 날씨, 14시 기준으로 조회
			//json이 아니라 XML로 받을거라면 dataType은 받지않아도 됨
		String nx, ny, baseTime, serviceKey, dataType, baseDate, pageNo, numOfRows, url;
		nx = "57"; //경도
		ny = "127"; //위도
		url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
		serviceKey = "ysZQwJOYJcm4JwGt8aivwpYj0JsSsx3s7Qvi8LM7JNezqsBwIPAOuP93idLfBqsY%2Bcc5ciXm0dO3%2FdMh3fy4vA%3D%3D";
		dataType = "json";
		pageNo = "1";
		numOfRows = "88";
		
			//시간 뽑는 법
		int time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			//년월일 뽑는 방법
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		int[] arr = new int[] {2,5,8,11,14,17,20,23};
			//결과에서 1을 빼야해서 int i를 따로 선언
		int i;
		for(i=0;i<arr.length;i++) {
			if(arr[i] > time) break;
		}		
		//시간이 자정일 때 하루 전으로 처리
		if(i > 0) { //23시 이전
			i = i-1;
		}else { //02시 미만 
			i = arr.length-1;
			cal.add(Calendar.DATE, -1);
		}
		//전날 날짜 출력
		baseDate = sdf.format(cal.getTime());
		baseTime = arr[i] + "00";
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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
