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
		//path���]�textension
		if(fileNameStartIndex >= dotIndex) {
			return path.substring(fileNameStartIndex);
		}
		//path�]�textension
		return path.substring(fileNameStartIndex, dotIndex);
	}
	private String getExtension(String path){
		int dotIndex = path.lastIndexOf(".");
		int fileNameStartIndex = path.lastIndexOf("\\") + 1;
		//path���]�textension
		if(fileNameStartIndex >= dotIndex) {
			return "";
		}
		//path�]�textension
		return path.substring(dotIndex + 1);
	}
	
	//��l��Ƨ����Ҧ��ɮץ������X��coll
	public void collectFiles(){
		String collDirName = "coll";
		File[] files = f.listFiles();
		//���ɮ�/��Ƨ��s�b
		if(files.length > 0){
			//�s�y�����θ�Ƨ�
			File collectDir = new File(path + "/" + collDirName);
			if (!collectDir.exists()){
				collectDir.mkdirs();
			}
			for (int i = 0 ; i < files.length ; i++){
				//�����ƫh����
				if(!files[i].getName().equals(collDirName)){
					//���j�U�h��Ƨ�
					collectFilesRecursive(collectDir, files[i], files[i].getName() + "_");
				}
			}
			System.out.println("�ɮצ������� ��Ƨ�:" + collDirName);
		}else{
			System.out.println("�L�ɮ�");
		}
	}
	
	
	//collectFile�����j��
	private void collectFilesRecursive(File collectDir, File f, String path){		
		//�ؼЬO��Ƨ�
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			if(files.length > 0){
				for (int i = 0 ; i < files.length ; i++){
					collectFilesRecursive(collectDir, files[i], path + files[i].getName() + "_");
				}
			}
		//�ؼЬO�ɮ�
		}else{
			try{
				String tempStr = collectDir.getAbsolutePath() + "/" + path;
				//�h���̧�"_"
				String fileName = tempStr.substring(0,tempStr.length() -1);
				File ftest = new File(fileName);
				//�ɮפ�����
				if(!ftest.exists()){
					//�ƻs�÷h���ɮ�
					Files.copy(f.toPath(), ftest.toPath());
					System.out.println(ftest.getName() + " �ƻs����");
				}else{
					System.out.println(ftest.getName() + " �w�s�b");
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		} 
	}
	
	//��l��Ƨ����Ҧ��ɮץ����s�y���|��collLink
	public void collectFilesLink(){
		String collDirName = "collLink";
		File[] files = f.listFiles();
		//���ɮ�/��Ƨ��s�b
		if(files.length > 0){
			//�s�y�����θ�Ƨ�			
			File collectDir = new File(path + "/" + collDirName);
			if (!collectDir.exists()){
				collectDir.mkdirs();
			}
			for (int i = 0 ; i < files.length ; i++){
				//�����ƫh����
				if(!files[i].getName().equals(collDirName)){
					//���j�U�h��Ƨ�
					collectFileLinksRecursive(collectDir, files[i], files[i].getName() + "_");
				}
			}
			System.out.println("�ɮצ������|���� ��Ƨ�:" + collDirName);
		}else{
			System.out.println("�L�ɮ�");
		}
	}
	
	//collectFileLink�����j��
	private void collectFileLinksRecursive(File collectDir, File f, String path){		
		//�ؼЬO��Ƨ�
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			if(files.length > 0){
				for (int i = 0 ; i < files.length ; i++){
					collectFileLinksRecursive(collectDir, files[i], path + files[i].getName() + "_");
				}
			}
		//�ؼЬO�ɮ�
		}else{
			try{
				String tempStr = collectDir.getAbsolutePath() + "/" + path;
				//�h���̧�"_"
				String fileName = tempStr.substring(0,tempStr.length() -1);
				File ftest = new File(fileName);
				//�ɮפ�����
				if(!ftest.exists()){
					//�ƻs�÷h���ɮ�
					//�qf�h����ftest
					Files.createSymbolicLink(ftest.toPath(), f.toPath());
					System.out.println(ftest.getName() + " ���|�s�y����");
				}else{
					System.out.println(ftest.getName() + " �w�s�b");
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
	
	//�ɮ׽s���վ�(+.-)
	private void numberAdd(File f, int num){
		File[] files = f.listFiles();
		for(int i = 0 ; i < files.length ; i++){
			//�ؼЬO��Ƨ�
			if (files[i].isDirectory()) {
				numberAdd(new File(path + "/" + files[i].getName()), num);
			//�ؼЬO�ɮ�
			}else{
				String oldFileName = getFileName(files[i].getAbsolutePath());
				//�p�G�ɦW�O�¼Ʀr
				boolean isNumeric =  oldFileName.matches("[+-]?\\d*(\\.\\d+)?");
				if(isNumeric){
					int oldNum = Integer.parseInt(oldFileName);
					int newNum = oldNum += num;
					String tempStr = Integer.toString(newNum);
					//���s��
					renameFile(files[i].getAbsolutePath(),tempStr);
				}else{
					System.out.println(oldFileName + ":�D�s���������");
				}
			} 
		}

	}
	
	//@Overload
	public void renameFile(String oldFilePath, String newFileName){
		File oldFile = new File(oldFilePath);
		renameFile(oldFile, newFileName);
	}
		
	//�ɮ׭��R�W
	public void renameFile(File oldFile, String newFileName){
		String oldFilePath = oldFile.getAbsolutePath();
		String dirPath = getDirPath(oldFilePath);
		String oldFileName = getFileName(oldFilePath);
		String extension = getExtension(oldFilePath);
		File newFile = null;
		
		if (!oldFile.exists()){
			System.out.println(oldFile + ",�ɮפ��s�b");
			return;
		}
		//�s�ɮצW��
		newFile = new File(dirPath + "/" + newFileName + "." + extension);
		if (newFile.exists()){
			System.out.println(newFileName + ",�ɮפw�s�b");
		}else{
			oldFile.renameTo(newFile);
			System.out.println("�ɮפw�ק�:" + oldFileName + "." + extension + " -> " + newFileName + "." + extension);
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
	//�ȮɨS��,�d�۳ƥ�
	public void renameFileInDir(String dir, String conditionStr) {
		String extension = getExtension(dir);
		File f = new File(dir);
		File[] files = f.listFiles();
		for (int i = 0; i < files.length; i++) {
			//�ؼЬO�ɮ�
			if (files[i].isFile()) {
				//files[i].renameTo()
				String fileName = files[i].getName();
				int index = fileName.length() - conditionStr.length();
				//���� �i��t��conditionStr
				if(index > 0){
					String checkStr = fileName.substring(index);
					//�ŦX�n�䪺����
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