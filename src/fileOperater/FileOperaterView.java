package fileOperater;


import java.io.File;
public class FileOperaterView {
	
	//@Overload
	public void showFiles(File f){
		showFiles(f, 0);
	}
	
	//��t�l��Ƨ��������ɮ�print
	private void showFiles(File f, int level){
		File[] files = f.listFiles();
		//���ɮ�/��Ƨ��s�b
		if(files.length > 0){
			for (int i = 0 ; i < files.length ; i++){
				for(int j = 0 ; j < level ; j++){
					System.out.print("  ");
				}
				//�ؼЬO��Ƨ�
				if (files[i].isDirectory()) {
					System.out.println(files[i].getName());
					//���j
					showFiles(files[i], level + 1);
				//�ؼЬO�ɮ�
				}else{
					System.out.println(files[i].getName());
				} 
			}
		}else{
			for(int j = 0 ; j < level ; j++){
					System.out.print("  ");
			}
			System.out.println("�L�ɮ�");
		}
	}
	
}