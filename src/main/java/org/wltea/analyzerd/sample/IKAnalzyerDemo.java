/**
 * IK 中文分词  版本 5.0.1
 * IK Analyzer release 5.0.1
 * <p>
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * 源代码由林良益(linliangyi2005@gmail.com)提供
 * 版权声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 */
package org.wltea.analyzerd.sample;

import org.wltea.analyzerd.cfg.DefaultConfig;
import org.wltea.analyzerd.core.IKSegmenter;
import org.wltea.analyzerd.core.Lexeme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用IKAnalyzer进行分词的演示 2012-10-22
 *
 */
public class IKAnalzyerDemo {

	public static void main(String[] args) {

		List<String> ikList = IKBrandAnalyse("北京东宏东升45°管道有限公司");
		// List<String> ikList = IKAnalyse("圆钢钛白粉");
		// List<String> ikList = IKAddrAnalyse("圆钢钛白粉Q235");

		String ik = "";
		for (int i = 0; i < ikList.size(); i++) {
			ik = ikList.get(i) + "," + ik;

		}
		 String str = "45°伺服电机";
		System.out.println(ik + "..." + ikList.size() + "..." + isSpecialChar(str));

		
		// DFASFSADF阿德斯防守对方asdfs°adf37《？：？@%#￥%#￥%@/#$%#@$%^><?1234";

		// result.contains("°") || result.contains("δ") || result.contains("°")
		// || result.contains("×")
		// || result.contains("Ⅱ") || result.contains("#") ||
		// result.contains("Ⅲ") || result.contains("½")
		// || result.contains("¼") || result.contains("/") ||
		// result.contains("-") || result.contains(".")
		// || result.contains(":") || result.contains("Ф")
		// || (result.contains("(") && result.contains(")") ||
		// result.contains("≤") || result.contains("²"))
		str = str.replaceAll("[^a-z^A-Z^0-9^°^δ^×^#^Ф^Ⅱ^¼^Ⅲ^½^(^)^≤^²^.^/^~^$^%^&^*^-^~^:]", "");
		System.out.println(str);
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isStr(String str) {
		Pattern pattern = Pattern.compile("[a-z]+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 采用IK分词
	 */
	public static List<String> IKAnalyse(String str) {

		DefaultConfig.ininDic(1);
		StringReader input = new StringReader(str.trim());
		IKSegmenter ikSeg = new IKSegmenter(input, true, "mainDic"); // true
		List<String> list = new ArrayList<String>();// 用智能分词
		try { // ，false细粒度
			System.out.print("分词1结果：");
			for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {

				if (hasName(str) || lexeme.getLexemeText().equals("门") || lexeme.getLexemeText().equals("砖")
						|| lexeme.getLexemeText().equals("石") || lexeme.getLexemeText().equals("草")
						|| lexeme.getLexemeText().equals("水") || lexeme.getLexemeText().equals("钛")) {
					list.add(lexeme.getLexemeText());
				}
				if (lexeme.getLexemeText().length() == 1 || isNumeric(lexeme.getLexemeText())
						|| isStr(lexeme.getLexemeText()) || isLetterDigit(lexeme.getLexemeText())
				// || lexeme.getLexemeText().equals("韶钢")
				) {
					continue;
				}
				list.add(lexeme.getLexemeText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static boolean hasName(String str) {
		return (str.equals("钢") && !str.equals("韶钢") || str.equals("钢") && !str.equals("武钢")
				|| str.equals("钢") && !str.equals("柳钢") || str.equals("钢") && !str.equals("马钢")
				|| str.equals("钢") && !str.equals("攀钢") || str.equals("钢") && !str.equals("唐钢")
				|| str.equals("钢") && !str.equals("鞍钢") || str.equals("钢") && !str.equals("粤钢")
				|| str.equals("钢") && !str.equals("宝钢") || str.equals("钢") && !str.equals("钢正")
				|| str.equals("钢") && !str.equals("冷钢") || str.equals("钢") && !str.equals("锦钢")
				|| str.equals("钢") && !str.equals("珠海粤钢") || str.equals("钢") && !str.equals("万钢")
				|| str.equals("钢") && !str.equals("粤韶") || str.equals("钢") && !str.equals("广钢")
				|| str.equals("钢") && !str.equals("湘钢") || str.equals("钢") && !str.equals("特钢"));

	}

	/**
	 * 采用IK分词
	 */
	public static List<String> IKBrandAnalyse(String str) {
		DefaultConfig.ininDic(2);
		StringReader input = new StringReader(str.trim());
		IKSegmenter ikSeg = new IKSegmenter(input, true, "brandDic"); // true
		List<String> list = new ArrayList<String>();// 用智能分词
		try { // ，false细粒度
			System.out.print("品牌分词1结果...：");
			for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {

				if (lexeme.getLexemeText().length() == 1 || isNumeric(lexeme.getLexemeText())
						|| isStr(lexeme.getLexemeText()) || isLetterDigit(lexeme.getLexemeText())
						|| isSpecialChar(lexeme.getLexemeText())) {
					continue;
				}
				list.add(lexeme.getLexemeText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 采用IK分词
	 */
	public static List<String> IKAddrAnalyse(String str) {

		DefaultConfig.ininDic(3);
		StringReader input = new StringReader(str.trim());
		IKSegmenter ikSeg = new IKSegmenter(input, true, "addrDic"); // true
		List<String> list = new ArrayList<String>();// 用智能分词
		try { // ，false细粒度
			System.out.print("分词1结果：");
			for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {

				if (lexeme.getLexemeText().length() == 1 || isNumeric(lexeme.getLexemeText())
						|| isStr(lexeme.getLexemeText()) || isLetterDigit(lexeme.getLexemeText())) {
					continue;
				}
				list.add(lexeme.getLexemeText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static boolean isLetterDigit(String str) {
		String regex = "^[a-z0-9A-Z]+$";// 其他需要，直接修改正则表达式就好
		return str.matches(regex);
	}
	/**
     * 判断是否含有特殊字符
     *
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%¼½ⅢⅡ#×δФ&*（）+|{}【】‘；：”“’。，、？°]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
}
