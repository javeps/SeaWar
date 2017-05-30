package com.sea.json.recordError;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sea.tools.ReadWriteString;
import com.sea.tools.UtileTools;

public class RecordByte implements Serializable{
	private static final long serialVersionUID = 1L;
	
	static Log log = LogFactory.getLog(RecordByte.class);
	
	static final String Error_Path_Bytes = "error/error_bytes";
	
	private static RecordByte _self;
	
	private File file;
	private RandomAccessFile randomFile;
	private RecordByte(){
		file = ReadWriteString.createFile(Error_Path_Bytes);
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			randomFile =new RandomAccessFile(file, "rw");
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
	}
	public static RecordByte getInstanse(){
		if(_self == null){
			_self = new RecordByte();
		}
		return _self;
	}
	
	public void write(byte[] bytes){
		long len;
		try {
			len = randomFile.length();
			randomFile.seek(len);
			randomFile.writeInt(bytes.length);
			randomFile.write(bytes);
		} catch (IOException e) {
			log.info(UtileTools.ex2s(e));
		}
		
	}
	public List<byte[]> readAll(){
		List<byte[]> result=new ArrayList<byte[]>();
		long sumLen;
		try {
			sumLen = randomFile.length();
			randomFile.seek(0);
			while(randomFile.getFilePointer()<sumLen){
				int len = randomFile.readInt();
				byte[] bytes=new byte[len];
				randomFile.readFully(bytes);
				result.add(bytes);
			}
		} catch (IOException e) {
			log.info(UtileTools.ex2s(e));
		}
		return result;
	}
}
