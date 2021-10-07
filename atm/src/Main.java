import controller.BankManager;
import models.Bank;

public class Main {

	public static void main(String[] args) {
		
		/*
		 * atm
		 * 
		 * 1. 회원가입/탈퇴
		 * 2. 로그인
		 * 3. 계좌개설/철회 (회원당 3개 제한)
		 * 4. 뱅킹기능(입금, 출금, 이체, 조회)
		 * 5. 파일처리(저장/로드)
		 * 6. 관리자모드(admin/0000) -> 전체유저조회/전체계좌조회
		 * 
		 */
		
		/*
		 * models 패키지안에 객체화될 클래스들을 정의
		 * ㄴUser
		 * ㄴAccount
		 * ㄴBank
		 */
		
		/*
		 * controller 패키지 안에 객체처리를 할 기능(메소드)
		 * ㄴ UserManager - 가입/탈퇴 (User타입의 객체배열) & 관리자
		 * ㄴAccountManager - 계좌개설/철회 (Account타입의 객체배열)
		 * ㄴBankManager - 뱅킹기능
		 * ㄴFileManager - 파일처리
		 */
		
		Bank.setName("GREEN");
		BankManager.instance.run();
		
	}

}
