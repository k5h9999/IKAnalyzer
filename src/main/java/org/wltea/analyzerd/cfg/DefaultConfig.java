/**
 * IK 中文分词  版本 5.0
 * IK Analyzer release 5.0
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 源代码由林良益(linliangyi2005@gmail.com)提供
 * 版权声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 
 * 
 */
package org.wltea.analyzerd.cfg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.wltea.analyzerd.dic.Dictionary;
import org.wltea.analyzerd.dic.DictionaryPool;

/**
 * Configuration 默认实现 2012-5-8
 *
 */
public class DefaultConfig implements Configuration {

	/*
	 * 分词器默认字典路径
	 */
	private static String PATH_DIC_MAIN = "org/wltea/analyzerd/dic/main2012.dic";
	private static final String PATH_DIC_QUANTIFIER = "org/wltea/analyzerd/dic/quantifier.dic";

	/*
	 * 分词器配置文件路径
	 */
	private static final String FILE_NAME = "IKAnalyzer.cfg.xml";
	// 配置属性——扩展字典
	private static final String EXT_DICT = "ext_dict";
	// 配置属性——扩展停止词典
	private static final String EXT_STOP = "ext_stopwords";

	private Properties props;

	/*
	 * 是否使用smart方式分词
	 */
	private boolean useSmart;
	private String dicName = "default_dic";

	private static String brand_dict = "brand_dict";

	private static String main_dict = "main_dict";

	private static String addr_dict = "addr_dict";

	private static String com_dict = "com_dict";

	private static String spec_dict = "spec_dict";
    private static List<String> keywordList = new ArrayList<>();
    private static List<String> brandList = new ArrayList<>();
    private static List<String> addrList = new ArrayList<>();
    
	public static List<String> getKeywordList() {
		return keywordList;
	}

	public static void setKeywordList(List<String> keywordList) {
		DefaultConfig.keywordList = keywordList;
	}

	public static List<String> getBrandList() {
		return brandList;
	}

	public static void setBrandList(List<String> brandList) {
		DefaultConfig.brandList = brandList;
	}

	public static List<String> getAddrList() {
		return addrList;
	}

	public static void setAddrList(List<String> addrList) {
		DefaultConfig.addrList = addrList;
	}

	/**
	 * 返回单例
	 * 
	 * @return Configuration单例
	 */
	public static Configuration getInstance() {
		return new DefaultConfig();
	}

	/*
	 * 初始化配置文件
	 */
	private DefaultConfig() {
		props = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		if (input != null) {
			try {
				props.loadFromXML(input);
			} catch (InvalidPropertiesFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 返回useSmart标志位 useSmart =true ，分词器使用智能切分策略， =false则使用细粒度切分
	 * 
	 * @return useSmart
	 */
	public boolean useSmart() {
		return useSmart;
	}

	/**
	 * 设置useSmart标志位 useSmart =true ，分词器使用智能切分策略， =false则使用细粒度切分
	 * 
	 * @param useSmart
	 */
	public void setUseSmart(boolean useSmart) {
		this.useSmart = useSmart;
	}

	/**
	 * 获取主词典路径
	 * 
	 * @return String 主词典路径
	 */
	public String getMainDictionary() {
		return PATH_DIC_MAIN;
	}

	/**
	 * 获取量词词典路径
	 * 
	 * @return String 量词词典路径
	 */
	public String getQuantifierDicionary() {
		return PATH_DIC_QUANTIFIER;
	}

	/**
	 * 获取扩展字典配置路径
	 * 
	 * @return List<String> 相对类加载器的路径
	 */
	public List<String> getExtDictionarys() {
		List<String> extDictFiles = new ArrayList<String>(2);
		String extDictCfg = props.getProperty(EXT_DICT);
		if (extDictCfg != null) {
			// 使用;分割多个扩展字典配置
			String[] filePaths = extDictCfg.split(";");
			if (filePaths != null) {
				for (String filePath : filePaths) {
					if (filePath != null && !"".equals(filePath.trim())) {
						extDictFiles.add(filePath.trim());
					}
				}
			}
		}
		return extDictFiles;
	}

	/**
	 * 获取扩展停止词典配置路径
	 * 
	 * @return List<String> 相对类加载器的路径
	 */
	public List<String> getExtStopWordDictionarys() {
		List<String> extStopWordDictFiles = new ArrayList<String>(2);
		String extStopWordDictCfg = props.getProperty(EXT_STOP);
		if (extStopWordDictCfg != null) {
			// 使用;分割多个扩展字典配置
			String[] filePaths = extStopWordDictCfg.split(";");
			if (filePaths != null) {
				for (String filePath : filePaths) {
					if (filePath != null && !"".equals(filePath.trim())) {
						extStopWordDictFiles.add(filePath.trim());
					}
				}
			}
		}
		return extStopWordDictFiles;
	}

	public void setDicName(String dicName) {
		this.dicName = dicName;
	}

	public String getDicName() {
		return this.dicName;
	}

	public static void ininDic() {
		System.out.println("正在初始化自定义品牌词");
		List<String> brandDic = DefaultConfig.getInstance().getBrands(null);
		DictionaryPool.create("brandDic", brandDic);
		System.out.println("正在初始化自定义主词");
		List<String> mainDic = DefaultConfig.getInstance().getMains(null);
		DictionaryPool.create("mainDic", mainDic);

		List<String> addrDic = DefaultConfig.getInstance().getAddrs(null);
		DictionaryPool.create("addrDic", addrDic);
	}

	public static void ininDic(int flag) {
		/*if (flag == 1) {
			List<String> mainDic = DefaultConfig.getInstance().getMains(null);
			DictionaryPool.create("mainDic", mainDic);

		}
		if (flag == 2) {
			List<String> brandDic = DefaultConfig.getInstance().getBrands(null);
			DictionaryPool.create("brandDic", brandDic);

		}
		if (flag == 3) {
			List<String> addrDic = DefaultConfig.getInstance().getAddrs(null);
			DictionaryPool.create("addrDic", addrDic);

		}
*/
 		if (flag == 1) {
			if(keywordList==null||keywordList.size()<1){
				keywordList = DefaultConfig.getInstance().getMains(null);
				
			}
			DictionaryPool.create("mainDic", keywordList);

		}
		if (flag == 2) {
			if(brandList==null||brandList.size()<1){
				brandList = DefaultConfig.getInstance().getBrands(null);
				
			}
			DictionaryPool.create("brandDic", brandList);
		}
		if (flag == 3) {
			if(addrList==null||addrList.size()<1){
				addrList = DefaultConfig.getInstance().getAddrs(null);
				
			}
			DictionaryPool.create("addrDic", addrList);
		}
	}

	/**
	 * 获取扩展字典配置路径
	 * 
	 * @return List<String> 相对类加载器的路径
	 */
	public List<String> getBrandDictionarys() {
		List<String> brandDictFiles = new ArrayList<String>();
		String brandDictCfg = props.getProperty(brand_dict);
		if (brandDictCfg != null) {
			// 使用;分割多个扩展字典配置
			String[] filePaths = brandDictCfg.split(";");
			if (filePaths != null) {
				for (String filePath : filePaths) {
					if (filePath != null && !"".equals(filePath.trim())) {
						System.out.println("filePath=" + filePath);
						brandDictFiles.add(filePath.trim());
					}
				}
			}
		}
		return brandDictFiles;
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public List<String> getBrands(String fileName) {
		List<String> files = DefaultConfig.getInstance().getBrandDictionarys();
		InputStream is = null;
		BufferedReader read = null;
		List<String> list = new ArrayList<>();
		if (files != null) {
			try {
				is = this.getClass().getClassLoader().getResourceAsStream(files.get(0));
				read = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
				String temp = null;
				// 一次读入一行，直到读入null为文件结束
				while ((temp = read.readLine()) != null) {
					// 显示行号
					list.add(temp.trim());
				}
				// read.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
						is = null;
					} catch (IOException e) {
					}
				}
			}
		}
		return list;
	}

	public List<String> getMainDictionarys() {
		List<String> mainDictFiles = new ArrayList<String>();
		String mainDictCfg = props.getProperty(main_dict);
		if (mainDictCfg != null) {
			// 使用;分割多个扩展字典配置
			String[] filePaths = mainDictCfg.split(";");
			if (filePaths != null) {
				for (String filePath : filePaths) {
					if (filePath != null && !"".equals(filePath.trim())) {
						System.out.println("filePath=" + filePath);
						mainDictFiles.add(filePath.trim());
					}
				}
			}
		}
		return mainDictFiles;
	}

	public List<String> getMains(String fileName) {
		List<String> files = DefaultConfig.getInstance().getMainDictionarys();
		InputStream is = null;
		BufferedReader read = null;
		List<String> list = new ArrayList<>();
		if (files != null) {
			try {
				is = this.getClass().getClassLoader().getResourceAsStream(files.get(0));
				read = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
				String temp = null;
				// 一次读入一行，直到读入null为文件结束
				while ((temp = read.readLine()) != null) {
					// 显示行号
					list.add(temp.trim());
				}
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
						is = null;
					} catch (IOException e) {
					}
				}
			}
		}
		return list;
	}

	public List<String> getaddrDictionarys() {
		List<String> addrDictFiles = new ArrayList<String>();
		String addrDictCfg = props.getProperty(addr_dict);
		if (addrDictCfg != null) {
			// 使用;分割多个扩展字典配置
			String[] filePaths = addrDictCfg.split(";");
			if (filePaths != null) {
				for (String filePath : filePaths) {
					if (filePath != null && !"".equals(filePath.trim())) {
						System.out.println("filePath=" + filePath);
						addrDictFiles.add(filePath.trim());
					}
				}
			}
		}
		return addrDictFiles;
	}

	public List<String> getAddrs(String fileName) {
		List<String> files = DefaultConfig.getInstance().getaddrDictionarys();
		InputStream is = null;
		BufferedReader read = null;
		List<String> list = new ArrayList<>();
		if (files != null) {
			try {
				is = this.getClass().getClassLoader().getResourceAsStream(files.get(0));
				read = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
				String temp = null;
				while ((temp = read.readLine()) != null) {
					list.add(temp.trim());
				}
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
						is = null;
					} catch (IOException e) {
					}
				}
			}
		}
		return list;
	}
}
