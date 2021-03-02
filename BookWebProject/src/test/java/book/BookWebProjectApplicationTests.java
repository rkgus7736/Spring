package book;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
class BookWebProjectApplicationTests {
	
	@Autowired
	BookMapper mapper;
	
	@Test
	void contextLoads() {
		System.out.println(mapper.selectAllBook());

	}
	
	@DisplayName("도서 정보 등록 테스트")
	@Test
	void testInsertBook() {
		System.out.println("도서 정보 등록 테스트 시작");
		String bno = "891245671234";
		String title = "자바 프로그래밍";
		String writer = "홍길동";
		String publisher = "J테스트";
		String wdate = "2020-02-19";

		BookDTO dto = new BookDTO(bno,title,writer,publisher,wdate);
		int count = mapper.insertBook(dto);
		if(count == 0) {
			fail("등록에 실패하였습니다.");
		}else {
			System.out.println("도서 정보 등록 테스트 성공");
		}
	}
	
	@DisplayName("도서 정보 조회 테스트")
	@Test
	void testselectBook() {
		System.out.println("도서 정보 조회 테스트 시작");
		BookDTO dto = new BookDTO("891245671234", " 자바 프로그래밍", "홍길동", "J테스트", "2020-02-19");
		assertEquals(dto, mapper.selectBook("자바"));
		System.out.println("사원 검색 테스트 시작");
		String title = "자바";
		List<BookDTO> list = mapper.selectBook(title);
		assertNotEquals("테스트 실패", 0, mapper.selectBook(title));
		if(0 == mapper.selectBook(title).size()) {
			fail("학생정보 검색 테스트 실패");
		}else {
			System.out.println("도서 정보 조회 테스트 성공");
		}
		
	}
	
	@DisplayName("도서 정보 삭제 테스트")
	@Test
	void testDeleteBook() {
		System.out.println("도서 정보 삭제 테스트 시작");
		String bno = "891245671234";
		int count = mapper.deleteBook(bno);
		if(count == 0) {
			fail("삭제할 데이터가 없습니다.");
		}else {
			System.out.println("도서 정보 삭제 테스트 성공");
		}
	}

	@AfterAll
	public static void done() {
		System.out.println("테스트 종료");
	}
	
}
