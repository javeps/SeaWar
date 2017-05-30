package com.sea.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NameService implements Serializable{
	private static final long serialVersionUID = 1L;
	static Log log = LogFactory.getLog(NameService.class);
	private static NameService _self = null;
	public static NameService getInstance(){
		if(_self == null){
			_self = new NameService(); 
		}
		return _self;
	}
	static List<String> xing;
	static List<String> ming1;
	static List<String> ming2;
	static boolean _isInitEnd = false;
	private NameService(){
		xing = new ArrayList<String>();
		ming1 = new ArrayList<String>();
		ming2 = new ArrayList<String>();
		_isInitEnd = false;
		initDate();
	}
	public static void initNameService(){
		if(!_isInitEnd){
			getInstance();
		}
	}
	private static void initDate(){
		try{
			System.out.println("======名字加载 begin=======");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("names.txt"),"UTF-8"));
	        String line = br.readLine();
	        while (line != null && !"".equals(line)){
	            String[] s=line.split("\t");
	            if(s.length != 3){
	            	line = br.readLine();
	            	continue;
	            }
	            xing.add(s[0]);
	            ming1.add(s[1]);
	            ming2.add(s[2]);
	            line = br.readLine();
	        }
	        br.close();
	        _isInitEnd = true;
	        System.out.println("======名字加载 end=======");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public  String newName(){
		int size = xing.size();
		if(size <= 0) return "";
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int x = r.nextInt(size);
		sb.append(xing.get(x));
		boolean shortName=r.nextInt(2) ==0 ? true:false;
		if(shortName){
			if(r.nextBoolean()){
				sb.append(ming1.get(r.nextInt(ming1.size())));
			}else{
				sb.append(ming2.get(r.nextInt(ming2.size())));
			}
		}else{
			if(r.nextBoolean()){
				sb.append(ming1.get(r.nextInt(ming1.size())));
				sb.append(ming2.get(r.nextInt(ming2.size())));
			}else{
				sb.append(ming2.get(r.nextInt(ming2.size())));
				sb.append(ming1.get(r.nextInt(ming1.size())));
			}
		}
		return sb.toString();
	}
	public static void main(String[] args){
		NameService.initNameService();
		Set<String> set=new HashSet<String>();
		while(true){
			if(NameService._isInitEnd){
				String name=NameService.getInstance().newName();
				set.add(name);
				System.out.println(set.size()+":"+name);
				if(set.size()>3000){
					break;
				}
			}
		}
	}
}