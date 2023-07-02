package fileOperater;


import java.util.Scanner;
import java.io.File;
public class fileOperaterMain {
	public static String enterDir(Scanner sc){
		System.out.println("�п�J�ؼи�Ƨ����|�G");
		String dir = "";
		try {
			dir = sc.nextLine();
			File f = new File(dir);
			if (!f.isDirectory()) {
				System.out.println("��J���~�A�Э��s��J�C");
				dir = enterDir(sc);
			}
		}catch(Exception e){
			System.out.println("��J���~�A�Э��s��J�C");
			dir = enterDir(sc);
		}
		return dir;
	}
	
	public static void funMsg(){
		System.out.println("�\��1�GshowFiles");
		System.out.println("�\��2�GcollectFiles");
		System.out.println("�\��3�GcollectFilesLink�]�ݦ��t�κ޲z���v���^");
		System.out.println("�\��4�GnumberAdd");
		System.out.println("�\��5�GrenameFileByTxt");
		System.out.println("�\��6�GimageReEncode");
	}
	public static int enterFunNumber(Scanner sc){
		System.out.println("�п�J�s���G");
		int funNum = -1;
		try{
			funNum = Integer.parseInt(sc.nextLine());
			
			//��J���~�s��
			//�X�z�ƾ�:1~6
			if (funNum > 6 || funNum < 1){
				System.out.println("��J���~�A�Э��s��J�C");
				funNum = enterFunNumber(sc);
			}	
		//��J���~�榡
		//�X�z�ƾ�:1~6
		}catch(Exception e){
			System.out.println("��J���~�A�Э��s��J�C");
			funNum = enterFunNumber(sc);
		}
		
		return funNum;
	}
	public static void funSelect(Scanner sc, String dir, int num){
		FileOperater fior = new FileOperater(dir);
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
			System.out.println("�п�J�W�[�Ʀr�G");
			int maximumNum = 100000000;
			int tempNum = sc.nextInt();
			fior.numberAdd(maximumNum + tempNum);
			fior.numberAdd(-1 * maximumNum);
		//renameFileByTxt
		}else if (num == 5){
			System.out.println("�п�Jtxt���|�G");
			String txtPath = sc.nextLine();
			fior.renameFileByTxt(txtPath);
		} else if (num == 6) {
			fior.imageReEncode();
		}
	}
	public static void main(String[] args){
		try {
			Scanner sc = new Scanner(System.in);
			String dir = null;
			int funNum = -1;
			//��J�ؼи��|
			dir = enterDir(sc);
			//��X�\��T��
			funMsg();
			//��J�\��s��
			funNum = enterFunNumber(sc);
			//������w�\��
			funSelect(sc, dir, funNum);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}