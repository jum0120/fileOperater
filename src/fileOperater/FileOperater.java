package fileOperater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
public class FileOperater {
	private File f = null;
	private String path = null;
	
	//constructor
	FileOperater(String path){
		File f = new File(path);
		this.path = path;
		this.f = f;
	}
	FileOperater(File f){
		this.path = f.getAbsolutePath();
		this.f = f;
	}
	public File getFile() {
		return f;
	}
	public String getPath() {
		return path;
	}
	
	private String getDirPath(String path){
		return path.substring(0, path.lastIndexOf("\\"));
	}
	private String getFileName(String path){
		int dotIndex = path.lastIndexOf(".");
		int fileNameStartIndex = path.lastIndexOf("\\") + 1;
		//path不包含extension
		if(fileNameStartIndex >= dotIndex) {
			return path.substring(fileNameStartIndex);
		}
		//path包含extension
		return path.substring(fileNameStartIndex, dotIndex);
	}
	private String getExtension(String path){
		int dotIndex = path.lastIndexOf(".");
		int fileNameStartIndex = path.lastIndexOf("\\") + 1;
		//path不包含extension
		if(fileNameStartIndex >= dotIndex) {
			return "";
		}
		//path包含extension
		return path.substring(dotIndex + 1);
	}
	
	//把子資料夾的所有檔案全部移出到coll
	public void collectFiles(){
		String collDirName = "coll";
		File[] files = f.listFiles();
		//有檔案/資料夾存在
		if(files.length > 0){
			//製造收集用資料夾
			File collectDir = new File(path + "/" + collDirName);
			if (!collectDir.exists()){
				collectDir.mkdirs();
			}
			for (int i = 0 ; i < files.length ; i++){
				//不重複則收集
				if(!files[i].getName().equals(collDirName)){
					//遞迴下層資料夾
					collectFilesRecursive(collectDir, files[i], files[i].getName() + "_");
				}
			}
			System.out.println("檔案收集完成 資料夾:" + collDirName);
		}else{
			System.out.println("無檔案");
		}
	}
	
	
	//collectFile的遞迴用
	private void collectFilesRecursive(File collectDir, File f, String path){		
		//目標是資料夾
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			if(files.length > 0){
				for (int i = 0 ; i < files.length ; i++){
					collectFilesRecursive(collectDir, files[i], path + files[i].getName() + "_");
				}
			}
		//目標是檔案
		}else{
			try{
				String tempStr = collectDir.getAbsolutePath() + "/" + path;
				//去除最尾"_"
				String fileName = tempStr.substring(0,tempStr.length() -1);
				File ftest = new File(fileName);
				//檔案不重複
				if(!ftest.exists()){
					//複製並搬移檔案
					Files.copy(f.toPath(), ftest.toPath());
					System.out.println(ftest.getName() + " 複製完成");
				}else{
					System.out.println(ftest.getName() + " 已存在");
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		} 
	}
	
	//把子資料夾的所有檔案全部製造捷徑到collLink
	public void collectFilesLink(){
		String collDirName = "collLink";
		File[] files = f.listFiles();
		//有檔案/資料夾存在
		if(files.length > 0){
			//製造收集用資料夾			
			File collectDir = new File(path + "/" + collDirName);
			if (!collectDir.exists()){
				collectDir.mkdirs();
			}
			for (int i = 0 ; i < files.length ; i++){
				//不重複則收集
				if(!files[i].getName().equals(collDirName)){
					//遞迴下層資料夾
					collectFileLinksRecursive(collectDir, files[i], files[i].getName() + "_");
				}
			}
			System.out.println("檔案收集捷徑完成 資料夾:" + collDirName);
		}else{
			System.out.println("無檔案");
		}
	}
	
	//collectFileLink的遞迴用
	private void collectFileLinksRecursive(File collectDir, File f, String path){		
		//目標是資料夾
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			if(files.length > 0){
				for (int i = 0 ; i < files.length ; i++){
					collectFileLinksRecursive(collectDir, files[i], path + files[i].getName() + "_");
				}
			}
		//目標是檔案
		}else{
			try{
				String tempStr = collectDir.getAbsolutePath() + "/" + path;
				//去除最尾"_"
				String fileName = tempStr.substring(0,tempStr.length() -1);
				File ftest = new File(fileName);
				//檔案不重複
				if(!ftest.exists()){
					//複製並搬移檔案
					//從f搬移到ftest
					Files.createSymbolicLink(ftest.toPath(), f.toPath());
					System.out.println(ftest.getName() + " 捷徑製造完成");
				}else{
					System.out.println(ftest.getName() + " 已存在");
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		} 
	}
	
	//@Overload
	public void numberAdd(int num){
		numberAdd(f, num);
	}
	
	//檔案編號調整(+.-)
	private void numberAdd(File f, int num){
		File[] files = f.listFiles();
		for(int i = 0 ; i < files.length ; i++){
			//目標是資料夾
			if (files[i].isDirectory()) {
				numberAdd(new File(path + "/" + files[i].getName()), num);
			//目標是檔案
			}else{
				String oldFileName = getFileName(files[i].getAbsolutePath());
				//如果檔名是純數字
				boolean isNumeric =  oldFileName.matches("[+-]?\\d*(\\.\\d+)?");
				if(isNumeric){
					int oldNum = Integer.parseInt(oldFileName);
					int newNum = oldNum += num;
					String tempStr = Integer.toString(newNum);
					//更改編號
					renameFile(files[i].getAbsolutePath(),tempStr);
				}else{
					System.out.println(oldFileName + ":非編號不予改動");
				}
			} 
		}

	}
	
	//@Overload
	public void renameFile(String oldFilePath, String newFileName){
		File oldFile = new File(oldFilePath);
		renameFile(oldFile, newFileName);
	}
		
	//檔案重命名
	public void renameFile(File oldFile, String newFileName){
		String oldFilePath = oldFile.getAbsolutePath();
		String dirPath = getDirPath(oldFilePath);
		String oldFileName = getFileName(oldFilePath);
		String extension = getExtension(oldFilePath);
		File newFile = null;
		
		if (!oldFile.exists()){
			System.out.println(oldFile + ",檔案不存在");
			return;
		}
		//新檔案名稱
		newFile = new File(dirPath + "/" + newFileName + "." + extension);
		if (newFile.exists()){
			System.out.println(newFileName + ",檔案已存在");
		}else{
			oldFile.renameTo(newFile);
			System.out.println("檔案已修改:" + oldFileName + "." + extension + " -> " + newFileName + "." + extension);
		}
		
	}
	
	//txtFormat:oldFileName \t  newFileName
	public void renameFileByTxt(String txtPath){
		File doc = new File(txtPath);
		File oldFile = null;
		String oldFilePath = f.getAbsolutePath();
		String[] data = {};
		String dataLine = "";
		try(BufferedReader br = new BufferedReader(
								new InputStreamReader(
								new FileInputStream(doc)))){
			dataLine = br.readLine();
			while (dataLine != null){
				data = dataLine.split("\t");
				oldFile = new File(oldFilePath + "/" + data[0]);
				renameFile(oldFile, data[1]);
				dataLine = br.readLine();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//暫時沒用,留著備用
	public void renameFileInDir(String dir, String conditionStr) {
		String extension = getExtension(dir);
		File f = new File(dir);
		File[] files = f.listFiles();
		for (int i = 0; i < files.length; i++) {
			//目標是檔案
			if (files[i].isFile()) {
				//files[i].renameTo()
				String fileName = files[i].getName();
				int index = fileName.length() - conditionStr.length();
				//夠長 可能含有conditionStr
				if(index > 0){
					String checkStr = fileName.substring(index);
					//符合要找的條件
					if (checkStr.equals(conditionStr)){
						int index2 = files[i].getAbsolutePath().length() - conditionStr.length();
						String newName = files[i].getAbsolutePath().substring(0,index2) + extension;
							
						renameFile(files[i], newName);
					}
				}
			}
		}
		
	}
	

	
	
}