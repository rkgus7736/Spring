package member;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MemberDeleteMain {
	public static void main(String[] args) {
		String id = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 회원 이름 일부분을 입력하세요");
		id = sc.nextLine();
		
		try {
			id = URLEncoder.encode(id,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			String apiUrl = "http://localhost:9999/delete.do?id="+id;
			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = "";
			while(true) {
				String str = br.readLine();
				if(str == null) break;
				result += str;
			}
			System.out.println(result);
			
			JSONObject json = new JSONObject(result);
			if(json.getInt("responseCode") == 500) {
				throw new Exception(json.getString("responseMessage"));
			}
			
			JSONArray arr = json.getJSONArray("result"); 
			for(int i=0;i<arr.length();i++) {
			JSONObject obj = arr.getJSONObject(i);
			System.out.println(obj.getString("id"));
			System.out.println(obj.getString("name"));
			System.out.println(obj.getInt("age"));
			System.out.println(obj.getString("grade")); 
			 }
			br.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			try {
				FileOutputStream fos = new FileOutputStream("error.txt",true);
				PrintWriter pw = new PrintWriter(fos);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
				Calendar today = Calendar.getInstance();
				String str = sdf.format(today.getTime())+ "\t" + e.getMessage();
				System.out.println(str);
				pw.write(str);
				pw.flush();
				pw.close();
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
		
	}

}
