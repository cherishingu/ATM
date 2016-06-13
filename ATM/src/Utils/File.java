package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 文件读写工具类
 */

public class File {
	private  String rootPath = "d:\\ATM";
	private Customer customer;
	private String path;
	 java.io.File newfile;
	public File(Customer customer){
		path = customer.id;
		 java.io.File file1 = new java.io.File(rootPath);
	       if(!file1.exists()){
			
			file1.mkdir();
	       }
		
		
		
	}
	public boolean exists(){
		if(newfile.exists()){
			return true;
		}
			return false;
		
	}
	public  java.io.File creatNewFile(String path2) {
		
		
      
		java.io.File file2 = new java.io.File(rootPath+"\\"+path2+".txt");
		if(!file2.exists())	{
			try {
				file2.createNewFile();
				System.out.println("文件"+path2+"创建成功!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("文件"+path2+"创建失败!");
				e.printStackTrace();
			}
		}
		return file2;
	}
	//文件写入
	public   void writeFile(String msg){
		newfile= creatNewFile(path);
	       try {
	    	   byte b[]= msg.getBytes();
			FileOutputStream fos = new FileOutputStream(newfile);
			fos.write(b);
			fos.flush();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("要写入的文件不存在！");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("写入失败");
			e.printStackTrace();
		}
		
		
	}
	public  String  readFile(){
		byte b[] = new byte[1024];
		StringBuilder sb = new StringBuilder();
		try {
			FileInputStream fin = new FileInputStream(newfile);
			
			int hasRead=0;
			
			while ((hasRead=fin.read(b))>0) {
				sb.append(new String(b,0,hasRead));
				
			}
			if(fin!=null){
				fin.close();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
		
		
		
	}
	

}
