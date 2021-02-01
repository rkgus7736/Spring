import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NaverMain {

	public static void main(String[] args) {
		//문자열 하나 콘솔에서 입력받음 --> 검색할 이름 일부분 입력
	//HTTP로 데이터를 요청
		//1. 데이터를 요청할 API 주소를 문자열로 선언
		//2. 전달할 파라메터를 인코딩 작업
			//변수 이름은 상관없음. 데이터를 주고 받는게 중요
		try {
		//3. URL 완성 주소랑 파라메터(쿼리 스트링)을 조합
			String apiUrl = "https://www.naver.com";
			URL url = new URL(apiUrl);
		//4. open connection 요청 (연결)
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//5. inputstream 초기화해서 읽음 (네이버의 홈화면을 받아온것)
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		//6. 데이터가 json이면 json을 파싱해서 원하는 데이터만 추출한 후 출력
			String result = "";
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				result += str;
			}
			System.out.println(result);
			br.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
