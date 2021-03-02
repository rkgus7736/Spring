package news;

import java.io.FileWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class NaverNewsSearchMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String text = null;
		System.out.println("뉴스 검색어를 입력하세요");
		text = sc.nextLine();

		String result = "";
		try {
			result = NaverNewsRun.getInstance().searchNaverNews(text);
			JSONObject json = new JSONObject(result);
			JSONArray arr = new JSONArray(json.getJSONArray("items"));
			if(json.getInt("total")==0)
				throw new Exception("검색 결과가 없습니다.");
			FileWriter fw = new FileWriter("news_검색어.html",true);
			PrintWriter pw = new PrintWriter(fw);
			String str = "";
			for(int i=0;i<arr.length();i++) {
				JSONObject obj = (JSONObject) arr.get(i);
				System.out.println(obj.get("title"));
				System.out.println(obj.get("link"));
				System.out.println(obj.get("description"));
				
				str = "<p>"+obj.get("title") + "\t" + obj.get("description")+ "\t<a href='"+ obj.get("link")+"'>해당 뉴스로 이동</a></p>";
				pw.println(str);
				pw.flush();
			}
			pw.close();
			fw.close();
		} catch (Exception e) {
			FileWriter fw;
			try {
				fw = new FileWriter("exception.txt",true);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(e.getMessage());
				pw.close();
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}

	}

}
