package generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class GeneratorSerial implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException {
		// String filePath =
		// "F:/workspace_sdk/sdk_parent/hytx_sdk_model/src/main/java/com/hytx/model";
		// String filePath =
		// "F:/workspace_sdk/sdk_parent/hytx_sdk_biz/src/main/java/com/hytx/dto";
		generator2();
	}

	public static void generator2() {
		String filePath = "E:/heyouworkspace/generate/src/com/hytx/model";
		File file = new File(filePath);
		try {
			generatFolderFileSerail(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void generatFolderFileSerail(File file) throws IOException {
		if (file.isFile()) {
			return;
		}
		File files[] = file.listFiles();
		if (files.length > 0) {
			for (File f : files) {
				if (f.isDirectory()) {
					generatFolderFileSerail(f);
				} else if (f.getName().indexOf("Example.") < 1) {
					generatSeril(f);
				}
			}
		}
	}

	public static void generatSeril(File file) throws IOException {
		if (file == null || !file.getName().endsWith(".java")) {
			return;
		}
		List<String> list = new LinkedList<String>();
		InputStream in = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(in,
				Charset.forName("utf-8")));
		String line = null;
		String lastLine = null;
		boolean isOk = false;
		while ((line = br.readLine()) != null) {
			if (isOk) {
				list.add(line);
				continue;
			}
			if (lastLine != null && StringUtils.isBlank(lastLine)
					&& line.startsWith("public class")) {
				if (line.indexOf(" Serializable ") < 0) {
					if (line.indexOf("implements") < 0) {
						line = line.replace("{", " implements Serializable {");
					} else {
						line = line.replace("{", " , Serializable {");
					}
					list.add(2, "import java.io.Serializable;");
					list.add(line);
					list.add("	private static final long serialVersionUID = 1L;");
					list.add("");
				} else {
					list.add(line);
				}
				isOk = true;
			} else {
				list.add(line);
			}
			lastLine = line;
		}
		br.close();
		in.close();
		PrintWriter pw = new PrintWriter(file, "utf-8");
		for (String s : list) {
			pw.println(s);
		}
		pw.flush();
		pw.close();
		System.out.println(file.getPath() + "文件添加序列化结束");
	}

}
