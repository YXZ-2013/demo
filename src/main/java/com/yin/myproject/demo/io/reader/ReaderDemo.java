package com.yin.myproject.demo.io.reader;

import java.io.CharArrayReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

public class ReaderDemo {
	final String classPath = ReaderDemo.class.getResource("/").getPath();

	public static void main(String[] args) throws Exception {
		// new ReaderDemo().fileReaderDemo();
		// new ReaderDemo().stringReader();
		new ReaderDemo().charArrayReaderDemo();
	}

	public void fileReaderDemo() throws Exception {
		Reader reader = new FileReader(classPath + "test.txt");
		char cbuf[] = new char[1024];
		int len = 0;
		while ((len = reader.read(cbuf)) > -1) {
			reader.read(cbuf, 0, len);
			System.out.println(new String(cbuf));
		}
		reader.close();
	}

	public void stringReader() throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("保护农用地不被侵占是国土资源管理部门的使命。然而，在广东省阳江市，一宗");
		builder.append("数万平方米农用土地在被非法征用过程中，8年来当地国土部门连下4道土地行政处罚");
		builder.append("决定书，非法征地行为不仅未得到阻止，相反占地面积却越来越大。");
		builder.append("记者调查发现，面对行政处罚，当事人一边认罚一边继续违法，当地国土部门却一直按每平方米1元钱予以罚款。如此荒唐的执法囧事为何会发生？");
		builder.append("记者调查发现，面对行政处罚，当事人一边认罚一边继续违法，当地国土部门却一直按每平方米1元钱予以罚款。如此荒唐的执法囧事为何会发生？");
		builder.append(
				"由于涉事土地为农用地，根据我国《土地管理法》等相关规定，征用土地必须经相关政府部门批准，取得农用地转为建设用地批准文号后才能对土地实施征用。土地的征用必须由市、县人民政府组织实施。这意味着洪万来与东城房地产公司的代征地协议一开始就是非法的。");
		builder.append("就在洪万来开始推土时，广东省国土厅通过卫片执法发现了这一问题，并要求阳东县国土局跟进处理。2008年10月25日，东阳县国土局同一天出具了两份土地行政处罚决定书。");
		builder.append(
				"面对处罚，洪万来按要求交了罚款，也复耕复绿，但他并未停止非法征地行为。数据显示，洪万来在被处罚之后依然将征地赔偿款支付给东城房地产公司。如2009年2月20日支付了135万元、2009年4月28日支付了144094元。");
		builder.append(
				"面对处罚，洪万来按要求交了罚款，也复耕复绿，但他并未停止非法征地行为。数据显示，洪万来在被处罚之后依然将征地赔偿款支付给东城房地产公司。如2009年2月20日支付了135万元、2009年4月28日支付了144094元.");
		Reader reader = new StringReader(builder.toString());
		char cbuf[] = new char[1024];
		int len = 0;
		while ((len = reader.read(cbuf)) > -1) {
			reader.read(cbuf, 0, len);
			System.out.println(new String(cbuf));
		}
		reader.close();
	}

	public void charArrayReaderDemo() throws Exception{
		char[] buf = new char[]{'1','2','3','4','5','6'};
		Reader reader = new CharArrayReader(buf);
		//read()读取单个字符
		int single = 0;
		while((single = reader.read()) > -1){
			System.out.println((char)single);
		}
		reader.close();
		
		//读取多个字符
		reader = new CharArrayReader(buf);
		char[] cbuf = new char[1024];
		int len = 0;
		while((len = reader.read(cbuf)) > -1){
			System.out.println(new String(cbuf,0,len));
		}
		reader.close();
	}
	
	
	public void pipedReaderDemo() throws Exception{
	}
}
