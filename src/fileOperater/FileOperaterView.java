package fileOperater;


import java.io.File;
public class FileOperaterView {
	
	//@Overload
	public void showFiles(File f){
		showFiles(f, 0);
	}
	
	//把含子資料夾的全部檔案print
	private void showFiles(File f, int level){
		File[] files = f.listFiles();
		//有檔案/資料夾存在
		if(files.length > 0){
			for (int i = 0 ; i < files.length ; i++){
				for(int j = 0 ; j < level ; j++){
					System.out.print("  ");
				}
				//目標是資料夾
				if (files[i].isDirectory()) {
					System.out.println(files[i].getName());
					//遞迴
					showFiles(files[i], level + 1);
				//目標是檔案
				}else{
					System.out.println(files[i].getName());
				} 
			}
		}else{
			for(int j = 0 ; j < level ; j++){
					System.out.print("  ");
			}
			System.out.println("無檔案");
		}
	}
	
}