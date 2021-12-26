package cn.intv.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetAllFileFromFolder {

	public static void main(String[] args) {
		String filePath = "C:\\FanBinSpace\\softWare\\gitee";
		List<File> files = new ArrayList<File>();
		getAllFiles(new File(filePath), files);
		for (File file : files) {
			System.out.println(file.getName());
		}
	}

	// 递归遍历文件夹中所有文件
	public static void getAllFiles(File file, List<File> files) {
		if (!file.exists()) {
			System.out.println("file path is not exist!");
			return;
		}

		if (file.isDirectory()) {
			File[] fs = file.listFiles();
			for (File f : fs) {
				getAllFiles(f, files);
			}
		} else {
			files.add(file);
		}
	}
}
