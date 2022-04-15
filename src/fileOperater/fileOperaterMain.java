package fileOperater;


import java.util.Scanner;
import java.io.File;
public class fileOperaterMain {
	public static String enterDir(Scanner sc){
		System.out.println("請輸入目標資料夾路徑：");
		String dir = "";
		try {
			dir = sc.nextLine();
			File f = new File(dir);
			if (!f.isDirectory()) {
				System.out.println("輸入錯誤，請重新輸入。");
				dir = enterDir(sc);
			}
		}catch(Exception e){
			System.out.println("輸入錯誤，請重新輸入。");
			dir = enterDir(sc);
		}
		return dir;
	}
	
	public static void funMsg(){
		System.out.println("功能1：showFiles");
		System.out.println("功能2：collectFiles");
		System.out.println("功能3：collectFilesLink（需有系統管理員權限）");
		System.out.println("功能4：numberAdd");
		System.out.println("功能5：renameFileByTxt");
	}
	public static int enterFunNumber(Scanner sc){
		System.out.println("請輸入編號：");
		int funNum = -1;
		try{
			funNum = Integer.parseInt(sc.nextLine());
			
			//輸入錯誤編號
			//合理數據:1~5
			if (funNum > 5 || funNum < 1){
				System.out.println("輸入錯誤，請重新輸入。");
				funNum = enterFunNumber(sc);
			}	
		//輸入錯誤格式
		//合理數據:1~5
		}catch(Exception e){
			System.out.println("輸入錯誤，請重新輸入。");
			funNum = enterFunNumber(sc);
		}
		
		return funNum;
	}
	public static void funSelect(Scanner sc, String dir, int num){
		FileOperaterController fior = new FileOperaterController(dir);
		//showFiles
		if (num == 1){
			fior.showFiles();
		//collectFiles
		}else if (num == 2){
			fior.collectFiles();
		//collectFilesLink
		}else if (num == 3){
			fior.collectFilesLink();
		//numberAdd
		}else if (num == 4){
			System.out.println("請輸入增加數字：");
			int maximumNum = 100000000;
			int tempNum = sc.nextInt();
			fior.numberAdd(maximumNum + tempNum);
			fior.numberAdd(-1 * maximumNum);
		//renameFileByTxt
		}else if (num == 5){
			System.out.println("請輸入txt路徑：");
			String txtPath = sc.nextLine();
			fior.renameFileByTxt(txtPath);
		}
	}
	public static void main(String[] args){
		try {
			Scanner sc = new Scanner(System.in);
			String dir = null;
			int funNum = -1;
			//輸入目標路徑
			dir = enterDir(sc);
			//輸出功能訊息
			funMsg();
			//輸入功能編號
			funNum = enterFunNumber(sc);
			//執行指定功能
			funSelect(sc, dir, funNum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}