

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class NaverSearchMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(sc);
		String text = null;
		System.out.println("검색어를 입력하세요 : ");
		text = sc.nextLine();
		String result = "";
		try {
		result = NaverSearchRun.getInstance().searchNaverBlog(text);
		JSONObject json = new JSONObject(result);
		JSONArray array = new JSONArray("items");
		if(json.getInt("total")==0)
			throw new Exception("검색 결과가 없습니다");
		FileWriter fw = new FileWriter("blog_search.txt");
		PrintWriter pw = new PrintWriter(fw);
		
		for(int i=0;i<array.length();i++) {
			JSONObject j = array.getJSONObject(i);
			System.out.println(j.get("title"));
			System.out.println(j.get("link"));
			System.out.println(j.get("bloggername"));
			String str = j.get("title")+"\t"+j.get("link")+"\t"+j.get("bloggername");
		}
		}catch(Exception e) {
			FileWriter fw = new FileWriter("error.txt");
			PrintWriter pw = new PrintWriter(fw);
			pw.println(e.getMessage());
			pw.close();
		}
			
		}
	
	public static void recordInfo(String url,String blog_search) {
			try {
				URL BlogUrl = new URL(url);
				URLConnection conn = BlogUrl.openConnection(); 
				InputStream is = conn.getInputStream();
				FileOutputStream fos = new FileOutputStream(blog_search + ".txt");
				byte[] arr = new byte[1024];
				while(true) {
					int count = is.read(arr);
					if(count == -1) break; 
					fos.write(arr,0,count);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		

	}
