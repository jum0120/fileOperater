package fileOperater;

import java.io.File;

public class FileOperaterController {
	private FileOperater model;
	private FileOperaterView view;
	
	//constructor
	public FileOperaterController(FileOperater model, FileOperaterView view){
	      this.model = model;
	      this.view = view;
	}
	
	//**�H�U��model**
	
	//��l��Ƨ����Ҧ��ɮץ������X��coll
	public void collectFiles(){
		model.collectFiles();
	}
	
	//��l��Ƨ����Ҧ��ɮץ����s�y���|��collLink
	public void collectFilesLink(){
		model.collectFilesLink();
	}
	
	//�ɮ׽s���վ�(+.-)
	public void numberAdd(int num){
		model.numberAdd(num);
	}
	
	//@Overload
	public void renameFile(String oldFilePath, String newFileName){
		model.renameFile(oldFilePath, newFileName);
	}
		
	//�ɮ׭��R�W
	public void renameFile(File oldFile, String newFileName){
		model.renameFile(oldFile, newFileName);
	}
	
	//��q��WByTxt
	//txtFormat:oldFileName \t  newFileName
	public void renameFileByTxt(String txtPath){
		model.renameFileByTxt(txtPath);
	}
	//�ȮɨS��,�d�۳ƥ�
	public void renameFileInDir(String dir, String conditionStr) {
		model.renameFileInDir(dir, conditionStr);
		
	}
	//**�H�U��view**
	//��t�l��Ƨ��������ɮ�print
	public void showFiles(){
		view.showFiles(model.getFile());
	}
	
	
	
	
}