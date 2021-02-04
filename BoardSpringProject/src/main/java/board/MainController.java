package board;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.RedirectView;

import board.dto.BoardDTO;
import board.dto.CommentDTO;
import board.dto.FileDTO;
import board.dto.MemberDTO;
import board.dto.QnaDTO;
import board.service.BoardService;
import board.service.MemberService;
import board.service.QnAService;
import board.vo.PaggingVO;

@Controller
public class MainController {
	private MemberService memberService;
	private BoardService boardService;
	private QnAService qnaService;
	public MainController(MemberService memberService, BoardService boardService,QnAService qnaService) {
		super();
		this.memberService = memberService;
		this.boardService = boardService;
		this.qnaService = qnaService;
	}

	@RequestMapping("/")
	public String main(HttpServletRequest request) {
		return index(request);
	}
	
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request) {
		int page = 1;
		//페이지 셋팅
		if(request.getParameter("pageNo") != null)
			page = Integer.parseInt(request.getParameter("pageNo"));
		List<BoardDTO> list = boardService.selectBoardList(page);//글목록 읽어옴
		int count = boardService.selectCount();
		PaggingVO vo = new PaggingVO(count, page);
		request.setAttribute("list", list);
		request.setAttribute("pagging", vo);
		System.out.println(list.toString());
		return "main";
	}
	
	
	@RequestMapping("logout.do")
	public String logout(HttpServletRequest request, HttpSession session) {
		session.invalidate();
		return main(request);
	}
	

	@RequestMapping("loginView.do")
	public String loginView() {
		return "login";
	}
	
	@RequestMapping("login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String id = request.getParameter("id");
		String pass =request.getParameter("pass");
		MemberDTO dto = memberService.login(id,pass);
		//HttpSession session = request.getSession();
		if(dto != null) {
			session.setAttribute("login", true);
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", dto.getName());
			session.setAttribute("grade", dto.getGrade());
			session.setMaxInactiveInterval(10*60);
			System.out.println("로그인 성공");
			return index(request);
		}else {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('아이디 비번을 확인하세요');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	@RequestMapping("/updateMemberView.do")
	public String updateMemberView(HttpServletRequest request , HttpSession session) {
	//아이디는 session에 있는걸 받아오면 됨
		String id = (String) session.getAttribute("id");
		//세션시간을 30분으로 설정했기때문에 세션정보가 유효한지 체크
		if(id == null)
			return "login";
		
		//회원정보조회
		MemberDTO dto = memberService.selectMember(id);
		//dto에서 받아와서 "dto"를 만들겠다
		request.setAttribute("dto", dto);
		
		return "member_update_view";
	}
	
	@RequestMapping("/updateMemberAction.do")
	public String updateMemberAction(HttpServletRequest request, HttpServletResponse response) {
		//아이디를 session으로 받지않아도 되지만,session으로 받으면 수정하기 전에 세션이 유효한지 확인 가능 
		//아이디 값을 session으로 받아올거면 HttpServletResponse도 불러와줘야함
		String id= (String) request.getSession().getAttribute("id");
		String pass= request.getParameter("pass");
		String name= request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		if(id == null) {
			//세션이 만료된 경우
			try {
				//글자 인코딩
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().write("<script>alert('세션이 만료되었습니다."
						+ " 다시 로그인해 주세요');"
						+ "location.href='login.do';</script>");
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		memberService.updateMember(new MemberDTO(id, pass, name, age));
		
		return "main";
		
	}
	
	@RequestMapping("/memberAdminMain.do")
	public String memberAdminMain(HttpServletRequest request) {
		List<MemberDTO> list = memberService.selectAllMember();
		request.setAttribute("list", list);
		return "member_manage_main";
	}
	
	@RequestMapping("/memberSearch.do")
	public String memberAdminSearch(HttpServletRequest request, HttpServletResponse response) {
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		//System.out.println(kind + " "+search);
		List<MemberDTO> list = memberService.selectSearchMember(kind, search);
		response.setContentType("text/html;charset=utf-8");
		JSONArray array = new JSONArray(list);
		JSONObject obj = new JSONObject();
		obj.put("result",array);
		if(list.size()>0) {
			obj.put("responseCode", 200); //성공
			obj.put("responseMessage", "SearchSuccess"); 
		}else { 
			obj.put("responseCode", 500); //실패
			obj.put("responseMessage", "SearchFailed"); 
		}
			
		try {
			response.getWriter().write(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@RequestMapping("/memberAjaxUpdate.do")
	public String memberAjaxUpdate(HttpServletRequest request, HttpServletResponse response) {
		String id= request.getParameter("id");
		String name= request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String grade =request.getParameter("grade");
		
		MemberDTO dto = new MemberDTO(id, null, name, age, grade);
		int count = memberService.updateMemberAdmin(dto);
			try {
				if(count == 1)
					response.getWriter().write("true");
				else
					response.getWriter().write("false");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		return null;
		
	}
	
	@RequestMapping("/memberAjaxDelete.do")
	public String memberAjaxDelete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int count = memberService.deleteMemberAdmin(id);
		try {
			response.getWriter().write(count + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@RequestMapping("/sendLog.do")
	public String sendLog(HttpServletRequest request, HttpServletResponse response) {
		String log_date = request.getParameter("log_date");
		int code_number = Integer.parseInt(request.getParameter("code_number"));
		String message = request.getParameter("message");
		System.out.println(log_date + " " + code_number + " " + message);
		int count = memberService.insertLog(log_date,code_number,message);
		System.out.println(count);
		
		try {
			response.getWriter().write("add count +" + count);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
		
	@RequestMapping("/boardView.do")
	public String boardView(HttpServletRequest request) {
		//게시글 하나 읽음
			//1.request에서 게시글 번호 읽어옴
		int bno = 0;
		if(request.getParameter("bno") != null)
			bno = Integer.parseInt(request.getParameter("bno"));
		else
			bno = (int)request.getAttribute("bno");
			
			//1-1. 해당 게시글 조회수 증가
		boardService.addCount(bno);
			//2.DB 해당 게시글 정보 읽어옴
		BoardDTO dto = boardService.selectBoard(bno);
			//2-1.댓글 로드 부분
		List<CommentDTO> list = boardService.selectBoardComment(bno);
			//2-2.첨부파일 로드 부분
		List<FileDTO> fList = boardService.selectFileList(bno);
			//3.request에 BoardDTO에 저장
		request.setAttribute("board", dto);
		request.setAttribute("comment", list);
		request.setAttribute("file", list);
		
		return "board_detail_view";
	}

	
	@RequestMapping("/insertComment.do")
	public String insertComment(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		boardService.insertComment(new CommentDTO(bno, content, writer));
		
		return null;
	}
	
	@RequestMapping("/commentLike.do")
	public String commentLike(HttpServletRequest request) {
		int cno = Integer.parseInt(request.getParameter("cno"));
		boardService.updateCommentLike(cno);
		return boardView(request);
	}
	
	@RequestMapping("/commentHate.do")
	public String commentHate(HttpServletRequest request) {
		int cno = Integer.parseInt(request.getParameter("cno"));
		boardService.updateCommentHate(cno);
		return boardView(request);
	}
	
	@RequestMapping("/plusLikeHate.do")
	public String plusLikeHate(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt((String)request.getParameter("bno"));
		int mode = Integer.parseInt((String)request.getParameter("mode"));

		int count = 0;
		
		count = boardService.addBoardLikeHate(mode,bno);
		//두가지 방식중 하나로 문자열처리를 해주면 됨, (count+"")가 더 편함
		//response.getWriter().write(String.valueOf(count));
		try {
			response.getWriter().write(count + "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/boardWriteView.do")
	public String boardWriteView() {
		return "board_write_view";
	}
	
	@RequestMapping("/boardWriteAction.do")
	public String boardWriteAction(MultipartHttpServletRequest request) {
		//1.글번호 먼저 발급
		int bno = boardService.newBno();
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		boardService.insertBoard(new BoardDTO(bno, title, writer, content));
		request.setAttribute("bno", bno);
		
		List<MultipartFile> fileList = request.getFiles("file"); 
		System.out.println(fileList.size());
		String path = "c:\\fileupload\\"+writer+"\\";
		ArrayList<FileDTO> fList = new ArrayList<FileDTO>();
		//정보 담아주기
		for(MultipartFile mf : fileList) {
			String originalFileName = mf.getOriginalFilename();
			long fileSize = mf.getSize();
			if(fileSize == 0) continue;
			System.out.println("originalFileName :" + originalFileName);
			System.out.println("fileSize : "+ fileSize);
			System.out.println(mf.getContentType());
			
			try {
				//파일 업로드
				String safeFile = path + originalFileName;
				fList.add(new FileDTO(bno, writer, originalFileName));
				File parentPath = new File(path);
				if(!parentPath.exists()) parentPath.mkdirs();//경로 생성
					mf.transferTo(new File(safeFile));	
				
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			boardService.insertFileList(fList);
			return boardView(request);
		}
	
	@RequestMapping("/fileDownload.do")
	public String fileDownload(HttpServletRequest request, HttpServletResponse response) {
		String fileName = request.getParameter("file");
		String writer = request.getParameter("writer");
		//파일 경로
		String path = "c:\\fileupload\\"+writer+"\\"+fileName;
		//파일 읽어와야해서 파일 생성. 
		//이미 서버에 올라가있는 상태라 굳이 안써줘도 되지만, 서버쪽에서 다운로드를 해올땐 꼭 써야함
		File file = new File(path);
		 
		try {
			FileInputStream fis = new FileInputStream(file);
			String encodingName = URLEncoder.encode(path,"utf-8");
			fileName = URLEncoder.encode(fileName,"utf-8");
			//header 세팅
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);
			response.setHeader("Content-Transfer-Encode", "binary");
			//file의 기본형이 int가 아니기때문에 int로 변경
			response.setContentLength((int)file.length());
			
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			//buffer 생성
			byte[] buffer = new byte[1024*1024];
			while(true) {
					//buffer가 가지고 있는 개수만큼 읽어오겠다
				int count = fis.read(buffer);
					//대부분 null이 아닐때까지(0)이라고 생각하지만 파일을 하나도 읽어오지않았을때 기본이 -1임
				if(count == -1) break;
				bos.write(buffer, 0, count);
					//보낸후에 꼭 flush를 해서 데이터를 내보내줘야함
				bos.flush();
			}
			fis.close();
			bos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
		
	@RequestMapping("/imageLoad.do")
	public String imageLoad(HttpServletRequest request, HttpServletResponse response) {
		String fileName = request.getParameter("file");
		String writer = request.getParameter("writer");
		//파일 경로
		String path = "c:\\fileupload\\"+writer+"\\"+fileName;
		
		//파일 읽어와야해서 파일 생성. 
		//이미 서버에 올라가있는 상태라 굳이 안써줘도 되지만, 서버쪽에서 다운로드를 해올땐 꼭 써야함
		File file = new File(path);
		try {
			FileInputStream fis = new FileInputStream(file);
			String encodingName = URLEncoder.encode(path,"utf-8");
			fileName = URLEncoder.encode(fileName,"utf-8");

			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			//buffer 생성
			byte[] buffer = new byte[1024*1024];
			while(true) {
					//buffer가 가지고 있는 개수만큼 읽어오겠다
				int count = fis.read(buffer);
					//대부분 null이 아닐때까지(0)이라고 생각하지만 파일을 하나도 읽어오지않았을때 기본이 -1임
				if(count == -1) break;
				bos.write(buffer, 0, count);
					//보낸후에 꼭 flush를 해서 데이터를 내보내줘야함
				bos.flush();
			}
			fis.close();
			bos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	@RequestMapping("/qnaView.do")
	public String qnaView(HttpServletRequest request) {
		int pageNo = 1;
		String id = (String) request.getSession().getAttribute("id");
		String grade = (String) request.getSession().getAttribute("grade");
		List<QnaDTO> list = qnaService.selectQnaList(id,pageNo,grade);
		System.out.println(list.toString());
		request.setAttribute("list", list);
		return "qna";
	}
	
	@RequestMapping("/qnaAdminView.do")
	public String qnaAdminView(HttpServletRequest request) {
		String id = (String) request.getSession().getAttribute("id");
		String grade = (String) request.getSession().getAttribute("grade");
		int pageNo = 1;
		if(request.getParameter("pageNo") != null)
			 pageNo = Integer.parseInt(request.getParameter("pageNo"));
		List<QnaDTO> list = qnaService.selectQnaList(id, pageNo, grade);
		int count = qnaService.selectCount();
		PaggingVO vo = new PaggingVO(count, pageNo);
		System.out.println(vo);
		request.getSession().setAttribute("page", vo);
		request.getSession().setAttribute("list", list);
		return "admin_qna";
	}
	
	@RequestMapping("/sendQnA.do")
	public String sendQnA(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = (String) request.getSession().getAttribute("content");
		int count = qnaService.insertQnA(new QnaDTO(title, content, writer));
		
		if(count == 0) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append("<script>alert('문의 등록중 문제가 생겼습니다');history.back();</script>");				response.getWriter().append("<script>alert('문의 등록중 문제가 생겼습니다');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			return qnaView(request);
		}
		return null;
		
	}
	
	@RequestMapping("/adminQnaDetailView.do")
	public String adminQnaDetailView(HttpServletRequest request, HttpServletResponse response) {
		int qno = Integer.parseInt(request.getParameter("qno"));
		QnaDTO dto = qnaService.selectQna(qno); 
		if(dto == null) {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<script>alert('문의글이 없습니다');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println(dto);
			request.setAttribute("dto", dto);
			return "admin_qna_view";
		}
		return null;
	}
	
	@RequestMapping("/answer.do")
	public String answer(HttpServletRequest request, HttpServletResponse response) {
		int qno = Integer.parseInt(request.getParameter("qno"));
			// 메소드명과 변수명이 겹치면 안됨. str을 answer로 쓰는건 자바에서만 가능
		String str = request.getParameter("response");
		//String answer = request.getParameter("response");
		qnaService.updateResponse(qno,str);
		
		return adminQnaDetailView(request, response);
	}

}
