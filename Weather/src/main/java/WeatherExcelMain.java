import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;


public class WeatherExcelMain {
	
	public static void main(String[] args) {
		FileInputStream fis;
		try {
			ZipSecureFile.setMinInflateRatio(0);
			fis = new FileInputStream("excel.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Scanner sc = new Scanner(System.in);
			System.out.println("조회할 주소를 정확하게 서울특별시 강서구  <--- 형식으로 입력하세요");
			String address = sc.nextLine();			
			//띄어쓰기로 끊어줌
			String addr[] = address.split(" ");
			String nx = null, ny = null;
			for(int i=0;i<sheet.getPhysicalNumberOfRows();i++) {
				if(sheet.getRow(i).getCell(2).getStringCellValue().equals(addr[0])) {
					if(sheet.getRow(i).getCell(3).getStringCellValue().equals(addr[1])) {
						nx = sheet.getRow(i).getCell(5).getStringCellValue();
						ny = sheet.getRow(i).getCell(6).getStringCellValue();
					}
				}
			}
			System.out.println(nx + " "+ ny);
			String baseTime, serviceKey, dataType, baseDate, pageNo, numOfRows, url;
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
			
			baseDate = sdf.format(cal.getTime());
			baseTime = arr[i] + "00";
			numOfRows = "10";
			serviceKey = "ysZQwJOYJcm4JwGt8aivwpYj0JsSsx3s7Qvi8LM7JNezqsBwIPAOuP93idLfBqsY%2Bcc5ciXm0dO3%2FdMh3fy4vA%3D%3D";
			pageNo = "1";
			dataType = "json";
			url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst"+"?serviceKey="+serviceKey+"&base_date="+baseDate+"&base_time="+baseTime+"&nx="+nx+"&ny="+ny+"&numOfRows="+numOfRows+"&pageNo=1&dataType="+dataType;
			
			URL u = new URL(url);
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
			JSONArray jarr = json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");
			for(int j=0;j<jarr.length();j++) {
				String str = jarr.getJSONObject(i).getString("category");
				System.out.println(jarr.getJSONObject(i).getString("category"));
					switch(str) {
					case "T3H":
						System.out.println("현재온도 : "+ jarr.getJSONObject(i).getString("fcstValue"));						
						break;
					case "TMX":
						System.out.println("최대기온 : "+ jarr.getJSONObject(i).getString("fcstValue"));
						break;
					case "TMN":
						System.out.println("최저기온 : "+ jarr.getJSONObject(i).getString("fcstValue"));
						break;
					case "SKY":
						System.out.println("하늘상태 : "+ jarr.getJSONObject(i).getString("fcstValue"));
						break;
						
					}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
