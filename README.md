此ik为客户端基于lucene6.6分词改进版，兼容jdk1.7,1.8,1.9，属于轻量级的命名实体识别功能分词器，支持动态指定不同行业的词库进行语句语义分析。此包含品牌、建筑行业材料等几个词库，可参考源码进行动态的扩展新增不同行业的词调整和改动

调用方法：

```java
//brand.dic品牌词库  
//main2012.dic 建筑材料词库
	public static void main(String[] args) {

		System.out.println(IKAnalzyerUtil.IKAnalyse("钢轨韶钢"));
		System.out.println(IKAnalzyerUtil.IKBrandAnalyse("钢轨韶钢"));
		
	}
```