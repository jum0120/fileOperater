package fileOperater;


import java.util.Scanner;
public class fileOperaterMain {
	public static String enterDir(Scanner sc){
		System.out.println("�п�J�ؼи�Ƨ����|�G");
		String dir = sc.nextLine();
		return dir;
	}
	
	public static void funMsg(){
		System.out.println("�\��1�GshowFiles");
		System.out.println("�\��2�GcollectFiles");
		System.out.println("�\��3�GcollectFilesLink(�ݦ��u�@�޲z���v��)");
		System.out.println("�\��4�GnumberAdd");
		System.out.println("�\��5�GrenameFileByTxt");
	}
	public static String enterFunNumber(Scanner sc){
		funMsg();
		System.out.println("�п�J�s���G");
		String funNum = sc.nextLine();
		return funNum;
	}
	public static void funSelect(Scanner sc, String dir, String num){
		FileOperater fior = new FileOperater();
		//showFiles
		if (num.equals("1")){
			fior.showFiles(dir);
		//collectFiles
		}else if (num.equals("2")){
			fior.collectFiles(dir);
		//collectFilesLink
		}else if (num.equals("3")){
			fior.collectFilesLink(dir);
		//numberAdd
		}else if (num.equals("4")){
			System.out.println("�п�J�W�[�Ʀr�G");
			int tempNum = sc.nextInt();
			fior.numberAdd(dir, tempNum);
		//renameFileByTxt
		}else if (num.equals("5")){
			System.out.println("�п�Jtxt���|�G");
			String txtPath = sc.nextLine();
			fior.renameFileByTxt(dir, txtPath);
		//��J���~�榡�B�s��
		}else {
			System.out.println("��J���~�A�Э��s��J�G");
			funSelect(sc, dir, num);
		}
	}
	public static void main(String[] args){
		try {
			Scanner sc = new Scanner(System.in);
			String dir = null;
			String funNum = null;
			//��������
			while(true) {
				//�ؼи��|
				dir = enterDir(sc);
				//��J�\��s��
				funNum = enterFunNumber(sc);
				//������w�\��
				funSelect(sc, dir, funNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}