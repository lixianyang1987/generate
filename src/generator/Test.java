package generator;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 基本类型：byte 二进制位数：8 包装类：java.lang.Byte 最小值：Byte.MIN_VALUE=-128
 * 最大值：Byte.MAX_VALUE=127 基本类型：short 二进制位数：16 包装类：java.lang.Short
 * 最小值：Short.MIN_VALUE=-32768 最大值：Short.MAX_VALUE=32767 基本类型：int 二进制位数：32
 * 包装类：java.lang.Integer 最小值：Integer.MIN_VALUE=-2147483648
 * 最大值：Integer.MAX_VALUE=2147483647 基本类型：long 二进制位数：64 包装类：java.lang.Long
 * 最小值：Long.MIN_VALUE=-9223372036854775808
 * 最大值：Long.MAX_VALUE=9223372036854775807 基本类型：float 二进制位数：32
 * 包装类：java.lang.Float 最小值：Float.MIN_VALUE=1.4E-45
 * 最大值：Float.MAX_VALUE=3.4028235E38 基本类型：double 二进制位数：64 包装类：java.lang.Double
 * 最小值：Double.MIN_VALUE=4.9E-324 最大值：Double.MAX_VALUE=1.7976931348623157E308
 * 基本类型：char 二进制位数：16 包装类：java.lang.Character 最小值：Character.MIN_VALUE=0
 * 最大值：Character.MAX_VALUE=65535
 * 
 * @author Administrator
 *
 */
public class Test {
	// 如果模板解析不正常将抛出异常，需要的使用的地方捕捉异常并处理
	public static void main(String[] args) throws Exception {
		System.out.println(URLDecoder.decode(
				"%e6%9c%aa%e7%9f%a5%2c%e6%9c%aa%e7%9f%a5", "utf-8"));

	}

	public static List<String> getMatchContent(String regex, String smsContent) {
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(smsContent);
		if (m.matches()) {
			int count = m.groupCount();
			for (int i = 1; i <= count; i++) {
				list.add(m.group(i));
			}
		}
		return list;
	}
}
