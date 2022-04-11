package fileOperater;


import java.util.Scanner;
public class fileOperaterMain {
	public static String enterDir(Scanner sc){
		System.out.println("請輸入目標資料夾路徑：");
		String dir = sc.nextLine();
		return dir;
	}
	
	public static void funMsg(){
		System.out.println("功能1：showFiles");
		System.out.println("功能2：collectFiles");
		System.out.println("功能3：collectFilesLink（需有系統管理員權限）");
		System.out.println("功能4：numberAdd");
		System.out.println("功能5：renameFileByTxt");
	}
	public static String enterFunNumber(Scanner sc){
		funMsg();
		System.out.println("請輸入編號：");
		String funNum = sc.nextLine();
		return funNum;
	}
	public static void funSelect(Scanner sc, String dir, String num){
		FileOperater fior = new FileOperater(dir);
		//showFiles
		if (num.equals("1")){
			fior.showFiles();
		//collectFiles
		}else if (num.equals("2")){
			fior.collectFiles();
		//collectFilesLink
		}else if (num.equals("3")){
			fior.collectFilesLink();
		//numberAdd
		}else if (num.equals("4")){
			System.out.println("請輸入增加數字：");
			int maximumNum = 100000000;
			int tempNum = sc.nextInt();
			fior.numberAdd(maximumNum + tempNum);
			fior.numberAdd(-1 * maximumNum);
		//renameFileByTxt
		}else if (num.equals("5")){
			System.out.println("請輸入txt路徑：");
			String txtPath = sc.nextLine();
			fior.renameFileByTxt(txtPath);
		//輸入錯誤格式、編號
		}else {
			System.out.println("輸入錯誤，請重新輸入：");
			funSelect(sc, dir, num);
		}
	}
	public static void main(String[] args){
		try {
			Scanner sc = new Scanner(System.in);
			String dir = null;
			String funNum = null;
			//目標路徑
			dir = enterDir(sc);
			//輸入功能編號
			funNum = enterFunNumber(sc);
			//執行指定功能
			funSelect(sc, dir, funNum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}