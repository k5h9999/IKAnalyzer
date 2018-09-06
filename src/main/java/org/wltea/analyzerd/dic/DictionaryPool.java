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
package org.wltea.analyzerd.dic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.wltea.analyzerd.cfg.Configuration;
import org.wltea.analyzerd.cfg.DefaultConfig;



/**
 * 字典池
 * @author admin
 *
 */
public class DictionaryPool {
	
	private static  Map<String, Dictionary> dictionaryMap = new HashMap<String, Dictionary>();
	
	private static class SingletonHolder{
		private static DictionaryPool instance = new DictionaryPool();
		private static Configuration cfg = DefaultConfig.getInstance();
	}
	
	public static DictionaryPool getInstance(){
		return SingletonHolder.instance;
	}
	
	public static Configuration getCfgInstance() {
		return SingletonHolder.cfg;
	}
	
	public static Dictionary create(String dicName) {
		return create(dicName, null);
	}
	public static Map<String, Dictionary>  getDictionary(){
		return dictionaryMap;
	}
	public static Dictionary create(String dicName, Collection<String> words) {
		Configuration cfg = getCfgInstance(); 
		cfg.setDicName(dicName);
		Dictionary dic = Dictionary.initial(cfg);
		if (words != null && !words.isEmpty()) {
			dic.reAddWords(words);
		}
		dictionaryMap.put(dicName, dic);
		return dic;
	}
	
	public static Dictionary getDictionary(String dicName) {
		Dictionary dic = dictionaryMap.get(dicName);
		if (dic == null) {
			throw new RuntimeException("Dictionary[" + dicName + "] not found!!!");
		}
		return dic;
	}

}
