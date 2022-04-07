package fileOperater;

import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
public class FileOperater {
	public String getDirPath(String filePath){
		return filePath.substring(0,filePath.lastIndexOf("\\"));
	}
	public String getFileName(String filePath){
		return filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.lastIndexOf("."));
	}
	public String getExtension(String filePath){
		return filePath.substring(filePath.lastIndexOf(".") + 1);
	}

	//�ȮɨS��,�d�۳ƥ�
	public void renameFileInDir(String dir, String conditionStr, String extension) {
		
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
						File newName = new File(files[i].getAbsolutePath().substring(0,index2) + extension);
						//�ɦW���Ĭ�,���R�W
						if (newName.exists()){
							System.out.println(newName.getName() + " �ɦW�w�s�b,�O�d");
						}else{
							files[i].renameTo(newName);
							System.out.println(newName.getName() + " ���R�W����");
						}
					}
				}
			}
		}
	}
	
	//@Overload
	public void collectFiles(String dir) {
		File f = new File(dir);
		collectFiles(f);
	}
	
	//��l��Ƨ����Ҧ��ɮץ������X��coll
	public void collectFiles(File f){
		String path = f.getAbsolutePath();
		File[] files = f.listFiles();
		//���ɮ�/��Ƨ��s�b
		if(files.length > 0){
			//�s�y�����θ�Ƨ�
			String collDirName = "coll";
			File collectDir = new File(path + "/" + collDirName);
			if (!collectDir.exists()){
				collectDir.mkdirs();
			}
			for (int i = 0 ; i < files.length ; i++){
				//���ƫh������
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


	//@Overload
	public void collectFilesLink(String path){
		File f = new File(path);
		collectFilesLink(f);	
	}
	
	//��l��Ƨ����Ҧ��ɮץ����s�y���|��collLink
	public void collectFilesLink(File f){
		String path = f.getAbsolutePath();
		File[] files = f.listFiles();
		//���ɮ�/��Ƨ��s�b
		if(files.length > 0){
			//�s�y�����θ�Ƨ�
			String collDirName = "collLink";
			File collectDir = new File(path + "/" + collDirName);
			if (!collectDir.exists()){
				collectDir.mkdirs();
			}
			for (int i = 0 ; i < files.length ; i++){
				//���ƫh������
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
	public void numberAdd(String path, int num){
		File f = new File(path);
		numberAdd(f, num);
	}

	//�ɮ׽s���վ�(+.-)
	public void numberAdd(File f, int num){
		String path = f.getAbsolutePath();
		File[] files = f.listFiles();
		for(int i = 0 ; i < files.length ; i++){
			//�ؼЬO��Ƨ�
			if (files[i].isDirectory()) {
				numberAdd(path + "/" + files[i].getName(), num);
			//�ؼЬO�ɮ�
			}else{
				String oldFileName = getFileName(files[i].getAbsolutePath());
				//�p�G�ɦW�O�¼Ʀr
				boolean isNumeric =  oldFileName.matches("[+-]?\\d*(\\.\\d+)?");
				if(isNumeric){
					int tempNum = Integer.parseInt(oldFileName);
					tempNum += num;
					String tempStr = Integer.toString(tempNum);
					//���s��
					renameFile(files[i].getAbsolutePath(),tempStr);
				}else{
					System.out.println(oldFileName + ":�������");
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
	public void renameFileByTxt(String oldFilePath, String txtPath){
		File doc = new File(txtPath);
		File oldFile = null;
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
	

	//@Overload
	public void showFiles(String dir){
		File f = new File(dir);
		showFiles(f, 0);
	}
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