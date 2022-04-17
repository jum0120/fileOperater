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
	
	//**以下為model**
	
	//把子資料夾的所有檔案全部移出到coll
	public void collectFiles(){
		model.collectFiles();
	}
	
	//把子資料夾的所有檔案全部製造捷徑到collLink
	public void collectFilesLink(){
		model.collectFilesLink();
	}
	
	//檔案編號調整(+.-)
	public void numberAdd(int num){
		model.numberAdd(num);
	}
	
	//@Overload
	public void renameFile(String oldFilePath, String newFileName){
		model.renameFile(oldFilePath, newFileName);
	}
		
	//檔案重命名
	public void renameFile(File oldFile, String newFileName){
		model.renameFile(oldFile, newFileName);
	}
	
	//批量改名ByTxt
	//txtFormat:oldFileName \t  newFileName
	public void renameFileByTxt(String txtPath){
		model.renameFileByTxt(txtPath);
	}
	//暫時沒用,留著備用
	public void renameFileInDir(String dir, String conditionStr) {
		model.renameFileInDir(dir, conditionStr);
		
	}
	//**以下為view**
	//把含子資料夾的全部檔案print
	public void showFiles(){
		view.showFiles(model.getFile());
	}
	
	
	
	
}