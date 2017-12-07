package com.gubang.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class FileUtil {
	
	public static final String CHARSET_UTF8 = "utf-8";
	
	public static final String CHARSET_GBK = "gbk";
	
	public static final String CHARSET_GB2312 = "gb2312";
	
	static public List<String> readALineFromLocalFile(String filePath, String charset, boolean isLinkedList) {
		List<String> resultList = null;
		if (isLinkedList) {
			resultList = new LinkedList<String>();
		} else {
			resultList = new ArrayList<String>();
		}
		File f = new File(filePath);
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(f);
			isr = new InputStreamReader(fis,charset);
			br = new BufferedReader(isr);
			String remp;
			while ((remp = br.readLine()) != null) {
				resultList.add(remp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultList;
	}
	
	static public void writeToFile(String filePath, Collection<String> toWriteList, boolean append) {
		File fout = new File(filePath);
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		if (fout.exists() && !append) {
			fout.delete();
		}
		try {
			fos = new FileOutputStream(fout, append);
			osw = new OutputStreamWriter(fos, "UTF-8");
			for (String str : toWriteList) {
				osw.write(str +  "\r\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static public void writeToFile(String filePath, String line, boolean append) {
		if (line == null) {
			return;
		}
		File fout = new File(filePath);
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		if (fout.exists() && !append) {
			fout.delete();
		}
		try {
			fos = new FileOutputStream(fout, append);
			osw = new OutputStreamWriter(fos);
			osw.write(line +  "\r\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				osw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static public void writeToFile(String filePath, byte toWrite[] ) {
		File fout = new File(filePath);
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try {
			fos = new FileOutputStream(fout);
			dos = new DataOutputStream(fos);
			dos.write(toWrite);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	static public String readFromLocalFile(String filePath, String charset) {
		List<String> resultList = readALineFromLocalFile(filePath, charset, true);
		StringBuffer buff = new StringBuffer();
		for (String line : resultList) {
			buff.append(line);
		}
		return buff.toString();
	}

	public static void mkdir(String directory) {
		File f = new File(directory);
		if (!f.exists()) {
			f.mkdirs();
		}
	}
	
	public static void mkAlphdirs(String directory) {
		File f = new File(directory);
		for (char c = 'a'; c <= 'z'; c++) {
			mkdir(directory + c);
		}
	}
}
