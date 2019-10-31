package com.weisen.www.code.yjf.merchant.web.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 *  敏感词过滤 工具类   -- 【匹配度高，可以使用】
 *  《高效精准》敏感字&词过滤：http://blog.csdn.net/hubiao_0618/article/details/45076871
 * @author hubiao
 * @version 0.1
 * @CreateDate 2015年4月16日 15:28:32
 */
public class SensitiveWord {
    private StringBuilder replaceAll;//初始化
    private String encoding = "UTF-8";
    private String replceStr = "*";
    private int replceSize = 500;
    private String fileName = "CensorWords.txt";
    private List<String> arrayList;
    public Set<String> sensitiveWordSet;//包含的敏感词列表，过滤掉重复项
    public List<String> sensitiveWordList;//包含的敏感词列表，包括重复项，统计次数

    /**
     * 文件要求路径在src或resource下，默认文件名为CensorWords.txt
     * @param fileName 词库文件名(含后缀)
     */
    public SensitiveWord(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * @param replceStr 敏感词被转换的字符
     * @param replceSize 初始转义容量
     */
    public SensitiveWord(String replceStr, int replceSize)
    {
        this.replceStr = fileName;
        this.replceSize = replceSize;
    }

    public SensitiveWord()
    {
    }

    /**
     * @param str 将要被过滤信息
     * @return 过滤后的信息
     */
    public String filterInfo(String str)
    {  	sensitiveWordSet = new HashSet<String>();
    	sensitiveWordList= new ArrayList<>();
        StringBuilder buffer = new StringBuilder(str);
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(arrayList.size());
        String temp;
        for(int x = 0; x < arrayList.size();x++)
        {
            temp = arrayList.get(x);
            int findIndexSize = 0;
            for(int start = -1;(start=buffer.indexOf(temp,findIndexSize)) > -1;)
            {
            	//System.out.println("###replace="+temp);
                findIndexSize = start+temp.length();//从已找到的后面开始找
                Integer mapStart = hash.get(start);//起始位置
                if(mapStart == null || (mapStart != null && findIndexSize > mapStart))//满足1个，即可更新map
                {
                    hash.put(start, findIndexSize);
                    //System.out.println("###敏感词："+buffer.substring(start, findIndexSize));
                }
            }
        }
        Collection<Integer> values = hash.keySet();
        for(Integer startIndex : values)
        {
            Integer endIndex = hash.get(startIndex);
            //获取敏感词，并加入列表，用来统计数量
            String sensitive = buffer.substring(startIndex, endIndex);
            //System.out.println("###敏感词："+sensitive);
            if (!sensitive.contains("*")) {//添加敏感词到集合
            	sensitiveWordSet.add(sensitive);
            	sensitiveWordList.add(sensitive);
			}
            buffer.replace(startIndex, endIndex, replaceAll.substring(0,endIndex-startIndex));
        }
        hash.clear();
        return buffer.toString();
    }
    /**
     *   初始化敏感词库
     */
    public void InitializationWork()
    {
        replaceAll = new StringBuilder(replceSize);
        for(int x=0;x < replceSize;x++)
        {
            replaceAll.append(replceStr);
        }
        //加载词库
        arrayList = new ArrayList<String>();
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            read = new InputStreamReader(SensitiveWord.class.getClassLoader().getResourceAsStream(fileName),encoding);
            bufferedReader = new BufferedReader(read);
            for(String txt = null;(txt = bufferedReader.readLine()) != null;){
                if(!arrayList.contains(txt))
                    arrayList.add(txt);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(null != bufferedReader)
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(null != read)
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public StringBuilder getReplaceAll() {
        return replaceAll;
    }
    public void setReplaceAll(StringBuilder replaceAll) {
        this.replaceAll = replaceAll;
    }
    public String getReplceStr() {
        return replceStr;
    }
    public void setReplceStr(String replceStr) {
        this.replceStr = replceStr;
    }
    public int getReplceSize() {
        return replceSize;
    }
    public void setReplceSize(int replceSize) {
        this.replceSize = replceSize;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public List<String> getArrayList() {
        return arrayList;
    }
    public void setArrayList(List<String> arrayList) {
        this.arrayList = arrayList;
    }
    public String getEncoding() {
        return encoding;
    }
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public static void main(String[] args){
    	long startNumer = System.currentTimeMillis();
		SensitiveWord sw = new SensitiveWord("CensorWords.txt");
	    sw.InitializationWork();
	    //System.out.println("敏感词的数量：" + arrayList.size());
	    String str = "毛泽东，习近平，h,<醉迷药>>醉迷药,.挨了一炮,ji挨了一炮fa";
	    System.out.println("被检测字符串长度:"+str.length());
	    str = sw.filterInfo(str);
	    long endNumber = System.currentTimeMillis();
	    //System.out.println("语句中包含敏感词的个数为：" + sensitiveWordSet.size() + "。包含：" + sensitiveWordSet);
	    //System.out.println("语句中包含敏感词的个数为：" + sensitiveWordList.size() + "。包含：" + sensitiveWordList);
	    System.out.println("总共耗时:"+(endNumber-startNumer)+"ms");
	    System.out.println("替换后的字符串为:\n"+str);
        System.out.println("................................");
	    String a = "爱液,\n" +
            "按摩棒,\n" +
            "拔出来,\n" +
            "爆草,\n" +
            "包二奶,\n" +
            "暴干,\n" +
            "暴奸,\n" +
            "暴乳,\n" +
            "爆乳,\n" +
            "暴淫,\n" +
            "被操,\n" +
            "被插,\n" +
            "被干,\n" +
            "逼奸,\n" +
            "仓井空,\n" +
            "插暴,\n" +
            "操逼,\n" +
            "操黑,\n" +
            "操烂,\n" +
            "肏你,\n" +
            "肏死,\n" +
            "操死,\n" +
            "操我,\n" +
            "厕奴,\n" +
            "插比,\n" +
            "插b,\n" +
            "插逼,\n" +
            "插进,\n" +
            "插你,\n" +
            "插我,\n" +
            "插阴,\n" +
            "潮吹,\n" +
            "潮喷,\n" +
            "成人电影,\n" +
            "成人论坛,\n" +
            "成人色情,\n" +
            "成人网站,\n" +
            "成人文学,\n" +
            "成人小说,\n" +
            "艳情小说,\n" +
            "成人游戏,\n" +
            "吃精,\n" +
            "抽插,\n" +
            "春药,\n" +
            "大波,\n" +
            "大力抽送,\n" +
            "大乳,\n" +
            "荡妇,\n" +
            "荡女,\n" +
            "盗撮,\n" +
            "发浪,\n" +
            "放尿,\n" +
            "肥逼,\n" +
            "粉穴,\n" +
            "风月大陆,\n" +
            "干死你,\n" +
            "干穴,\n" +
            "肛交,\n" +
            "肛门,\n" +
            "龟头,\n" +
            "裹本,\n" +
            "国产av,\n" +
            "好嫩,\n" +
            "豪乳,\n" +
            "黑逼,\n" +
            "后庭,\n" +
            "后穴,\n" +
            "虎骑,\n" +
            "换妻俱乐部,\n" +
            "黄片,\n" +
            "几吧,\n" +
            "鸡吧,\n" +
            "鸡巴,\n" +
            "鸡奸,\n" +
            "妓女,\n" +
            "奸情,\n" +
            "叫床,\n" +
            "脚交,\n" +
            "精液,\n" +
            "就去日,\n" +
            "巨屌,\n" +
            "菊花洞,\n" +
            "菊门,\n" +
            "巨奶,\n" +
            "巨乳,\n" +
            "菊穴,\n" +
            "开苞,\n" +
            "口爆,\n" +
            "口活,\n" +
            "口交,\n" +
            "口射,\n" +
            "口淫,\n" +
            "裤袜,\n" +
            "狂操,\n" +
            "狂插,\n" +
            "浪逼,\n" +
            "浪妇,\n" +
            "浪叫,\n" +
            "浪女,\n" +
            "狼友,\n" +
            "聊性,\n" +
            "凌辱,\n" +
            "漏乳,\n" +
            "露b,\n" +
            "乱交,\n" +
            "乱伦,\n" +
            "轮暴,\n" +
            "轮操,\n" +
            "轮奸,\n" +
            "裸陪,\n" +
            "买春,\n" +
            "美逼,\n" +
            "美少妇,\n" +
            "美乳,\n" +
            "美腿,\n" +
            "美穴,\n" +
            "美幼,\n" +
            "秘唇,\n" +
            "迷奸,\n" +
            "密穴,\n" +
            "蜜穴,\n" +
            "蜜液,\n" +
            "摸奶,\n" +
            "摸胸,\n" +
            "母奸,\n" +
            "奈美,\n" +
            "奶子,\n" +
            "男奴,\n" +
            "内射,\n" +
            "嫩逼,\n" +
            "嫩女,\n" +
            "嫩穴,\n" +
            "捏弄,\n" +
            "女优,\n" +
            "炮友,\n" +
            "砲友,\n" +
            "喷精,\n" +
            "屁眼,\n" +
            "前凸后翘,\n" +
            "强jian,\n" +
            "强暴,\n" +
            "强奸处女,\n" +
            "情趣用品,\n" +
            "情色,\n" +
            "拳交,\n" +
            "全裸,\n" +
            "群交,\n" +
            "人妻,\n" +
            "人兽,\n" +
            "日逼,\n" +
            "日烂,\n" +
            "肉棒,\n" +
            "肉逼,\n" +
            "肉唇,\n" +
            "肉洞,\n" +
            "肉缝,\n" +
            "肉棍,\n" +
            "肉茎,\n" +
            "肉具,\n" +
            "揉乳,\n" +
            "肉穴,\n" +
            "肉欲,\n" +
            "乳爆,\n" +
            "乳房,\n" +
            "乳沟,\n" +
            "乳交,\n" +
            "乳头,\n" +
            "骚逼,\n" +
            "骚比,\n" +
            "骚女,\n" +
            "骚水,\n" +
            "骚穴,\n" +
            "色逼,\n" +
            "色界,\n" +
            "色猫,\n" +
            "色盟,\n" +
            "色情网站,\n" +
            "色区,\n" +
            "色色,\n" +
            "色诱,\n" +
            "色欲,\n" +
            "色b,\n" +
            "少年阿宾,\n" +
            "射爽,\n" +
            "射颜,\n" +
            "食精,\n" +
            "释欲,\n" +
            "兽奸,\n" +
            "兽交,\n" +
            "手淫,\n" +
            "兽欲,\n" +
            "熟妇,\n" +
            "熟母,\n" +
            "熟女,\n" +
            "爽片,\n" +
            "双臀,\n" +
            "死逼,\n" +
            "丝袜,\n" +
            "丝诱,\n" +
            "松岛枫,\n" +
            "酥痒,\n" +
            "汤加丽,\n" +
            "套弄,\n" +
            "体奸,\n" +
            "体位,\n" +
            "舔脚,\n" +
            "舔阴,\n" +
            "调教,\n" +
            "偷欢,\n" +
            "推油,\n" +
            "脱内裤,\n" +
            "文做,\n" +
            "舞女,\n" +
            "无修正,\n" +
            "吸精,\n" +
            "夏川纯,\n" +
            "相奸,\n" +
            "小逼,\n" +
            "校鸡,\n" +
            "小穴,\n" +
            "小xue,\n" +
            "性感妖娆,\n" +
            "性感诱惑,\n" +
            "性虎,\n" +
            "性饥渴,\n" +
            "性技巧,\n" +
            "性交,\n" +
            "性奴,\n" +
            "性虐,\n" +
            "性息,\n" +
            "性欲,\n" +
            "胸推,\n" +
            "穴口,\n" +
            "穴图,\n" +
            "亚情,\n" +
            "颜射,\n" +
            "阳具,\n" +
            "杨思敏,\n" +
            "要射了,\n" +
            "夜勤病栋,\n" +
            "一本道,\n" +
            "一夜欢,\n" +
            "一夜情,\n" +
            "一ye情,\n" +
            "阴部,\n" +
            "淫虫,\n" +
            "阴唇,\n" +
            "淫荡,\n" +
            "阴道,\n" +
            "淫电影,\n" +
            "阴阜,\n" +
            "淫妇,\n" +
            "淫河,\n" +
            "阴核,\n" +
            "阴户,\n" +
            "淫贱,\n" +
            "淫叫,\n" +
            "淫教师,\n" +
            "阴茎,\n" +
            "阴精,\n" +
            "淫浪,\n" +
            "淫媚,\n" +
            "淫糜,\n" +
            "淫魔,\n" +
            "淫母,\n" +
            "淫女,\n" +
            "淫虐,\n" +
            "淫妻,\n" +
            "淫情,\n" +
            "淫色,\n" +
            "淫声浪语,\n" +
            "淫兽学园,\n" +
            "淫书,\n" +
            "淫术炼金士,\n" +
            "淫水,\n" +
            "淫娃,\n" +
            "淫威,\n" +
            "淫亵,\n" +
            "淫样,\n" +
            "淫液,\n" +
            "淫照,\n" +
            "阴b,\n" +
            "应召,\n" +
            "幼交,\n" +
            "欲火,\n" +
            "欲女,\n" +
            "玉乳,\n" +
            "玉穴,\n" +
            "援交,\n" +
            "原味内衣,\n" +
            "援助交际,\n" +
            "招鸡,\n" +
            "招妓,\n" +
            "抓胸,\n" +
            "自慰,\n" +
            "作爱,\n" +
            "a片,\n" +
            "fuck,\n" +
            "gay片,\n" +
            "g点,\n" +
            "h动画,\n" +
            "h动漫,\n" +
            "失身粉,\n" +
            "淫荡自慰器";
            String[] split = a.split(",");
        for (int i = 0; i < split.length; i++) {
            System.out.print(split[i]);
        }

    }

	public static Boolean check(String s){
        SensitiveWord sb = new SensitiveWord("CensorWords.txt");
        sb.InitializationWork();
        String sw = sb.filterInfo(s);
        boolean equals = s.equals(sw);
        return equals;
    }
}

