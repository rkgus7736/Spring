package model;

public class ModelAndView {
	private String page;//이동할 페이지 경로
	private boolean sendRedirect;//페이지 이동 방법
	
	public ModelAndView() {
		super();
	}
	public ModelAndView(String page, boolean sendRedirect) {
		super();
		this.page = page;
		this.sendRedirect = sendRedirect;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public boolean isSendRedirect() {
		return sendRedirect;
	}
	public void setSendRedirect(boolean sendRedirect) {
		this.sendRedirect = sendRedirect;
	}
	
}
