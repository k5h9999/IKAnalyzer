
package org.wltea.analyzerd.util;

import org.wltea.analyzerd.cfg.DefaultConfig;
import org.wltea.analyzerd.core.IKSegmenter;
import org.wltea.analyzerd.core.Lexeme;
import org.wltea.analyzerd.dic.DictionaryPool;

import java.io.IOException;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class IKAnalzyerUtil {
	
	private static String[] SINGLE_WORD_ARRAY = {"电","床","油","盐","锡","煤","梅","桃","柿","桐","卡","墙","席","座","李","杏","枣","柚","柱","桔","梁","梨","槽","水","江","汽","沙","泵","漆","灯","球","瓦","盆","石","砂","砖","砼","碱","竹","管","线","胶","脊","草","菰","菱","蓆","象","钉","钛","钼","铅","铜","铝","铬","锁","锉","锌","镁","镉","镍","镜","门","葛","苇","芦","荷"};
	private static String[] Alphabet_BRAND_ARRAY = {"3COM","ABBA","ABRABETA","Acewell","ACME","ACTOP","ADKTECH","ADSE","AEBELL","AEGLE","AFOREVER","AHBQ","AIEPN","AIPHONE","AIRMIND","ALBATROS","ALCAN","ALLE","ALPS","ALTEA","ANCHICABLE","ANCTION","ANDES","ANNET","AODORO","AOKPA","AOLIN","AORISE","APEX","APISTE","APOLLO","APPLE","AQUA","ARCAM","ARCNODE","ARKADIA","ARMAFLEXR","ARMSTRONG","ARROPUS","ARTE","ARTIGO","ARTTOO","ASAHI","ASCO","ASHLY","ASSOMA","ATEIS","ATEN","Atrun","Aukit","AUTONICS","AVAYA","BAHN","BAIDUN","BAOSTEEL","BARCO","BASF","BDQQ","BDSPA","BDXPA","BEHRINGER","Belimo","BELL","BENJAMINMOORE","BERTOS","BEST","BESTGOAL","BESTPA","BETATHREE","BETTER","BIAMP","BINGXI","BITZER","BJSL","BLCH","BLITAR","BLOSSOM","BOBO","BOCK","BOCT","BOEN","BOGE","BOGEL","BOHLER","BOMA","BOND","BOSE","BOSSFITNESS","BOSUN","BOTER","BOTOL","Botta","BRASTONE","BROTHER","BRTLED","BSCA","BSPH","BULGIN","CALEFFI","CALIOU","CANBO","CAOC","CARBOWELD","CAREA","CARKEY","CASALI","CASERO","CATE","CBHT","CDKPA","CERIS","CHDL","CHEFLINE","CHJISY","CISCO","CLEANTOOLS","COLOYS","COMLAMP","COMPTUAR","Computar","CONI","COOPERBUSSMANN","COPACA","COPAR","COPELAND","CORONA","COTTO","CREATIVE","CREATOR","CRESTRON","CRESUN","CROWN","CXJS","Cybervision","CYTECH","DAHA","DALLAS","DALLES","Danaher","DANFOSS","DANNY","DAQO","DDYG","DEKO","DELL","DELTA","DENON","DENVER","DESFINE","DESSO","DEVON","DGMDQ","DIASE","DICK","Digisound","DIGISPIDER","Digisynthetic","DIGMEX","DIHOUR","DINK","DIXON","DLHKDZ","DLINK","DNAKE","DOMUS","DOOPA","DORMA","DORY","DOSATRON","DRUCKS","DSPPA","DTDQTQ","DURKFLEX","DVONU","DVSONY","Dynacord","DYNASTY","EACOME","EAONA","EASE","EASTCATO","ECHU","ECOM","EDSUN","EHORSE","EIKI","EKOM","ELAC","ELAN","ELANDIOT","Elanst","ELASTOPAVE","ELECTRIC","ELECTRONICS","EMMETI","ENDURA","EOdEXO","EOSD","EPES","EPSOM","EPSON","EPSONR330","ESPA","EVERPURE","EXICON","EXTRA","EXTRON","FARBELL","FATO","FELDS","FESTO","FIBEROM","FIREANGEL","FLANKA","FLOWCON","FOREVERTECH","FOREX","fvgia","FWULONG","GALA","GALMON","GAMRAT","GANZ","GARMIN","GCCPA","GDHW","GEADENCO","GEBEIGI","GELE","GELON","GETTON","GIACOMINI","GIMARE","GLONILK","GNAZ","GONSIN","GOOPOP","GOSATO","GREAT","GREENLEE","GREENMAN","GREENWAVE","GREENYA","GRET","GSYUASA","GUGI","GXOED","GZCF","HACH","HAGOO","HAILIN","HAISEN","HAKKO","HANIL","HANN","HAQUN","HarborHouse","HARMAN","HARVIA","HAYWARD","HBFS","HDID","HDWORLD","HEMAN","HEMPEL","HENGMIN","HERNMAII","HHSN","HIDGLOBAL","HIDRY","HIETECH","HIKVISION","HISENPA","Hitevision","HIVI","HJMOSAIC","HNBS","HNGTNG","HNTD","Holyshine","HONE","HONEYWELL","HONGTONG","HORM","HORN","HRBA","HSEPS","HTDZ","HUAHO","Hunter","HYDROMAT","HYTNZN","IBDN","ICON","ICOT","IDEC","IDEESEN","IFCS","IGRANT","IMPACT","INBIT","INDACO","INFINOVA","INLU","INNOGREEN","INOUT","INTECH","INTELLISENSE","INTL","IPAV","iSonicavct","ISOVER","ITRON","JACKLIN","JENODE","Jflor","JHYME","JIAZHENG","JIEBA","JIEBEST","JINGUANGDAO","JIUFU","JIWINS","JOTON","JSMG","JUELONG","JUJO","JUNC","JUSTA","JUSTOR","JUTEKS","KABA","KACON","KARCHER","KARO","KEDACOM","KELIWA","KENAIFU","KERAMAG","KERAMER","Keyking","KEYSTONE","KILOVIEW","KINDLE","KING","KINGHONG","KINGKANG","KINGO","KINGROW","KITZ","KLCC","KOCA","KOSHIN","KRAMER","KROHNE","KRST","KULEDY","KUMA","KYORITSU","lacme","LANB","LANBE","LANDWELL","LANGBOONE","LANSTAR","LEDE","LEEGO","LEEMC","Lerbol","LGDD","Liana","LIANGZU","lifesize","LIMONTA","LINGYING","LINKSYS","LION","LISTS","LLSH","LONCOMIP","LONGHORN","LONGSHENG","LONON","LQHQH","LROSS","LUDA","LUOGE","LYSAGHT","MACELIAN","MACKIE","MAENTIC","MAGIC","MAINGO","Manitowoc","MARANTZ","MASLAND","Matchplay","MAXIFLO","MEATON","MEHOME","MEICHENG","MEIENZA","MEIKO","MENFED","METASYS","MICC","MICROVIEW","MIONGR","MIPRO","MISENSOR","MISSION","MITSUBISHI","MLGY","MONDO","MONITORY","MOXA","MRLOCK","MSKF","MTAI","MUELLER","MYKENG","NACHI","NANBARL","NANOR","NANZ","NATIONAL","NDAB","NECTERE","NETGEAR","NETLINE","NETLINK","NEUFUSION","NEWINTRTEST","NEWJL","NEXANS","NHTD","NINOTURF","NOKIAN","NOLY","NORTON","NOVOTRACK","NUMark","OBTE","OEALY","OFAN","OHSUNG","OMEGA","OMENIINTERNATIONA","OMSTEEL","ONEKK","ONETOP","OPTFOCUS","OPTICFIBER","OPTONE","OPTOSTAR","opudg","ORIDYER","ORION","OSRAM","OTEWA","OTOL","OURCOM","OUSITE","OVENTROP","OYEAPA","PACOM","PANASONIC","PANDIAN","PARADOX","PARAOOX","PAVLN","PEAKE","PEKINS","PENTAIR","PEOPLE","PERFLORF","Performer","PERKINS","PETERPAUL","PHILIPS","PIONEER","piooneer","PISCO","PIZZATO","PLAQUE","POLYFLOR","POLYPENCO","PORIS","Powersoft","PRESONUS","PROCE","PROSPER","PROTECH","PTTL","QHXF","QJVE","QLIGHT","QUSHUEN","RALID","RANE","RATIONAL","RCIDE","REALSD","RECOMB","REGIN","RELIABLE","RELL","REMP","RMJT","ROCA","ROKONET","ROLAND","Rongli","ROTO","ROWLETT","RUIXINDA","RUOAUN","RYOKA","SABINE","SafeEye","SAKO","SAMRT","SANELY","SANKING","SANKN","SAOOG","SAWO","SCEN","SCHMERSAL","SCHUCO","SCHULTZE","SCHURTER","SECRUA","SEEFAR","SEEK","seifz","SEKO","SELION","semtong","SENAO","SENET","SENNHEISER","SENSOCON","SETON","SHAW","SHEALLO","SHGF","SHGGG","SHHY","SHIP","SHNAIGER","SHQJ","SHURE","SHYASHI","SHZG","SICO","SIEMENS","SIKA","SIMON","SIMONEIE","SINCHUN","SINKO","SINMAG","SINO","SINONE","SIPURUI","SIRE","SITREN","SIUI","SIUKONDA","SJHD","SJMC","SKAONY","SKYER","SLEC","SLEIK","SOCA","SONEG","SONT","SONY","SOULE","SOUNDCARFT","SOUNDCRAFT","SOUNDSTANDARD","SOVOTEK","SPANAV","SPDPA","STAN","STANDARD","STANLEY","SToneU","SUFA","SUITTC","SUNFOR","SUNON","SUNS","SUNYENT","SUPCON","SUPOR","SURLINK","SYMONA","SYRIS","SYSTIMAX","SYYT","SZREC","SZSIEMINS","TACAM","TAMRON","TANMI","TANREX","TASCAM","TATA","TCELECTRONIC","TCLAA","TCSLA","TECHCON","Technics","TEKRONIX","Telasia","TENDA","TENET","TENNISLIFE","TENON","TESHOW","TEUCO","THOMSON","THORN","THREEDA","TIETUO","TIMKEN","TITEBOND","TJSDADIANQI","TOGER","TONGBOS","TOPLIGHTS","TOPMAX","TOPSEC","TORO","TOTO","TOYO","TOZEN","TPKO","TPLINK","TRANCE","TRION","TROX","TRUSTRON","TRYWIN","TSOTC","TSUBAKI","TYCO","TYCOSUN","UBNT","UCFC","ULIEL","UNINM","UNION","USHIO","UTOOL","VAKA","VANGUARD","VARIZONE","VATTEN","VCOM","VERM","VESS","VICHY","VIDEOTREC","VIDEX","VIEWGOD","VIEWYE","VIMAR","VINGCARD","VINIAV","VIVA","VOCAL","WARCO","WARLD","Watec","WATERLINK","WAVK","WEIDMULLER","WFHK","WIENER","WILLSTRONG","WILO","WIRESCONN","WONDER","WOOMI","worldvision","WOTESL","WTOPA","XBPA","XINMAO","XIZIOTIS","XPERTPRO","XYLEM","YAAMM","YAMAHA","YAOAN","YHOLD","YINDA","YKKap","Ymioo","YNYL","Yolan","YONHON","YORK","YUEGUO","ZCTB","ZERO","Zilmet","ZION","ZOHA","ZOMA","ZPSOUND"};
	private static String[] STEEL_ARRAY= {"万钢","三鑫特钢","三钢","上海星钢","上钢","东上钢业","东华钢铁","东方特钢","中钢","临钢","乌钢","亿佰钢","信钢","八钢","兰鑫钢铁","冷钢","凌钢","凌钢重特","凤钢","劳钢","包钢","华钢","南京钢铁","南昌钢","南钢","博钢联和","友钢","发钢","台湾中钢","吉钢","呈钢","咸阳宝石钢丝绳","唐钢","四方如钢","夏钢","大明钢铁","天兴彩钢瓦","天钢","太钢","太钢不锈钢","太钢不锈钢","威钢","宁夏钢铁","宁钢","安钢","安钢永通","宏钢","宜钢","宝丰钢业","宝山钢管","宝钢","实钢","宣钢","富钢","山钢","川钢","广钢","庚钢","徐钢","德钢","成实钢管","成钢","承钢","抚钢","攀成钢","攀钢","攀钢钢城","攀钢钢城","新余钢厂","新冶特钢","新冶钢","新抚钢","新钢","日本特钢","日本神钢","日钢","昆钢","星钢","晋钢","本钢","本钢浦项","杭钢","林钢","枣钢","柳钢","桂万钢","梅钢","武钢","水钢","永钢","汇钢","汉钢","江钢","沙钢","沪钢","河北中钢","河北钢管","河北钢铁","河钢","河钢宣钢","河钢宣钢","泰钢","济钢","涟钢","涟钢振兴","淮钢","渝西钢","湘钢","熠钢","燕钢","特钢","玉昆钢铁","玉钢","珠钢","申银特钢","略钢","神钢","粤北钢铁","粤钢","联钢","舞钢","苏钢","荆钢","荣钢","莱钢","营钢","西宁钢","西钢","贵钢","辽钢","达钢","通凌钢","通钢","邯钢","邱钢","邵钢","鄂钢","酒钢","重钢","金钢狼","金钢联","鑫涌特钢","鑫钢","钛钢","钢力","钢大师","钢建","钢旭","钢松","钢正","钢汇联","钢玉","钢王","钢益","钢盛","钢盾","钢美","钢联","钢道","钢锋","铭钢","锡钢","锦钢","长钢","阿钢","青钢","首钢","首钢京唐","首钢华禹","首钢长治","首钢顺义","马钢","高钢","黄石宝钢","齐钢","龙钢","龙门钢","韶钢","天瑞玛钢","太谷玛钢","槽钢","玛钢","萍钢","钢电","陕西龙钢","珠海粤钢","鞍钢","粤韶"};
	public static void main(String[] args) {

		System.out.println(IKAnalzyerUtil.IKAnalyse("钢轨包钢"));
		System.out.println(IKAnalzyerUtil.IKBrandAnalyse("钢轨包钢"));
		
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
	 * 材料词库IK分词
	 */
	public static List<String> IKAnalyse(String str) {

		DefaultConfig.ininDic(1);
		StringReader input = new StringReader(str.trim());
		IKSegmenter ikSeg = new IKSegmenter(input, true, "mainDic"); // true
		List<String> list = new ArrayList<String>();// 用智能分词
		try { // ，false细粒度
			System.out.print("分词1结果：");
			for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {

//				if (hasName(str) || lexeme.getLexemeText().equals("门") || lexeme.getLexemeText().equals("砖")
//						|| lexeme.getLexemeText().equals("石") || lexeme.getLexemeText().equals("草")
//						|| lexeme.getLexemeText().equals("水") || lexeme.getLexemeText().equals("钛")) {
//					list.add(lexeme.getLexemeText());
//				}
				if (hasName(str) || hasSingleWord(lexeme.getLexemeText())) {
					System.out.println(str);
					list.add(lexeme.getLexemeText());
				}
				if (lexeme.getLexemeText().length() == 1 
						|| isNumeric(lexeme.getLexemeText())
						|| isStr(lexeme.getLexemeText()) 
						|| isLetterDigit(lexeme.getLexemeText())
				) {
					continue;
				}
				list.add(lexeme.getLexemeText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		DictionaryPool.getDictionary().clear();//清空词库减少内存占用
		return list;
	}
	/**
	 * 区分单字
	 */
	private static boolean hasSingleWord(String str){
		for(String sw : SINGLE_WORD_ARRAY){
			if(str.equals(sw)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 区分英文字母（非品牌名字）*/
	private static boolean hasAlphabetBrand(String str){
		for(String ab : Alphabet_BRAND_ARRAY){
			if(str.equalsIgnoreCase(ab)){
				return true;
			}
		}
		return false;
	}
	
	private static boolean hasName(String str) {
		if(str.equals("钢")){
			for(String st : STEEL_ARRAY){
				if(!str.equals(st)){
					return true;
				}
			}
		}
		return false;
		
//		return (   str.equals("钢") && !str.equals("韶钢") 
//				|| str.equals("钢") && !str.equals("武钢")
//				|| str.equals("钢") && !str.equals("柳钢") 
//				|| str.equals("钢") && !str.equals("马钢")
//				|| str.equals("钢") && !str.equals("攀钢") 
//				|| str.equals("钢") && !str.equals("唐钢")
//				|| str.equals("钢") && !str.equals("鞍钢") 
//				|| str.equals("钢") && !str.equals("粤钢")
//				|| str.equals("钢") && !str.equals("宝钢") 
//				|| str.equals("钢") && !str.equals("钢正")
//				|| str.equals("钢") && !str.equals("冷钢") 
//				|| str.equals("钢") && !str.equals("锦钢")
//				|| str.equals("钢") && !str.equals("珠海粤钢") 
//				|| str.equals("钢") && !str.equals("万钢")
//				|| str.equals("钢") && !str.equals("粤韶") 
//				|| str.equals("钢") && !str.equals("广钢")
//				|| str.equals("钢") && !str.equals("湘钢") 
//				|| str.equals("钢") && !str.equals("特钢"));

	}

	/**
	 * 品牌词库，采用IK分词
	 */
	public static List<String> IKBrandAnalyse(String str) {
		DefaultConfig.ininDic(2);
		StringReader input = new StringReader(str.trim());
		IKSegmenter ikSeg = new IKSegmenter(input, true, "brandDic"); // true
		List<String> list = new ArrayList<String>();// 用智能分词
		try { // ，false细粒度
			System.out.print("品牌分词1结果...：");
			for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
				if (hasAlphabetBrand(lexeme.getLexemeText())) {
					list.add(lexeme.getLexemeText());
				}
				if (lexeme.getLexemeText().length() == 1 
						|| isNumeric(lexeme.getLexemeText())
						|| isStr(lexeme.getLexemeText())
						|| isLetterDigit(lexeme.getLexemeText())
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
