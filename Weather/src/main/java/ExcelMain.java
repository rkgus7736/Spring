import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelMain {

	public static void main(String[] args) {
		//엑셀 파일 용량이 크면 프로그램이 다운될수도 있음
		ZipSecureFile.setMinInflateRatio(0);
		try {
			FileInputStream fis = new FileInputStream("excel.xlsx");
				//엑셀 파일 읽어오는 부분
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
				//엑셀은 sheet기준이라서 sheet로 가져옴, getSheetAt -> 엑셀 시트 번호로 읽어옴
			XSSFSheet sheet = workbook.getSheetAt(0);
				//전체 행 개수
			System.out.println("전체 행 개수 : " + sheet.getPhysicalNumberOfRows());
				//한줄 당 몇개인지
			System.out.println("해당 라인의 컬럼 수 : " + sheet.getRow(0).getPhysicalNumberOfCells());
				// 인덱스의 4번째 줄 6번째 에 있는 셀 값
			System.out.println("4행 6열에 있는 셀 값 : "+sheet.getRow(3).getCell(5));
				//2차원 배열로 접근하면 됨 (행과 열)
			for(int i=0;i<sheet.getPhysicalNumberOfRows();i++) { //첫행부터 마지막행까지
				String row = "";
				for(int j=0;j<sheet.getRow(i).getPhysicalNumberOfCells();j++) {//첫번째 칸부터 마지막 칸까지
					row += sheet.getRow(i).getCell(j)+"\t";
				}
			System.out.println(row);
			}
			//서울시 마포구, 강원도 화천군 -->nx ny 좌표값으로 검색해서 출력
			String address = JOptionPane.showInputDialog("시도 시군구 형식으로 입력하세요");
				//한번에 시도 시군구 전부 뽑을수 없어서 split으로 나눠줌
			String addr[] = address.split(" ");
				//문자열 부분 일치
			//정규 표현식을 사용해서 추출해낸것
/*			for(int i=0;i<sheet.getPhysicalNumberOfRows();i++) {
				System.out.println(
						//addr[0].substring(0,1) -> 앞 두글자 추출
						sheet.getRow(i).getCell(2).getStringCellValue().indexOf(addr[0].substring(0,1)));
				System.out.println(sheet.getRow(i).getCell(2).getStringCellValue().matches(".*제주.*"));
*/			
			for(int i=0;i<sheet.getPhysicalNumberOfRows();i++) {
				if(!sheet.getRow(i).getCell(4).getStringCellValue().equals("")) continue;
					if(sheet.getRow(i).getCell(2).getStringCellValue().equals(addr[0])) {
					if(sheet.getRow(i).getCell(3).getStringCellValue().equals(addr[1]))
					System.out.println(sheet.getRow(i).getCell(2)+ " "+sheet.getRow(i).getCell(3)+ " " + sheet.getRow(i).getCell(5)+ " "+sheet.getRow(i).getCell(6));
				}
					
		}
			//원하는 주소 입력후 날씨정보 출력
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
