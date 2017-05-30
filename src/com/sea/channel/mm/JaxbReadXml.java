package com.sea.channel.mm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.MessageFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sea.tools.ReadWriteString;

@SuppressWarnings({ "unchecked" })
public class JaxbReadXml {

	public static <T> T readXmlPath(Class<T> clazz, String path)
			throws JAXBException {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			return (T) u.unmarshal(new File(path));
		} catch (JAXBException e) {
			// logger.trace(e);
			throw e;
		}
	}

	public static <T> T readXmlString(Class<T> clazz, String context)
			throws JAXBException {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			return (T) u.unmarshal(new StringReader(context));
		} catch (JAXBException e) {
			// logger.trace(e);
			throw e;
		}
	}

	public static <T> T readConfig(Class<T> clazz, String config,
			Object... arguments) throws IOException, JAXBException {
		InputStream is = null;
		try {
			if (arguments.length > 0) {
				config = MessageFormat.format(config, arguments);
			}
			// logger.trace("read configFileName=" + config);
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			is = new FileInputStream(config);
			return (T) u.unmarshal(is);
		} catch (IOException e) {
			// logger.trace(config, e);
			throw e;
		} catch (JAXBException e) {
			// logger.trace(config, e);
			throw e;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	public static <T> T readConfigFromStream(Class<T> clazz,
			InputStream dataStream) throws JAXBException {
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			return (T) u.unmarshal(dataStream);
		} catch (JAXBException e) {
			// logger.trace(e);
			throw e;
		}
	}

	public static <T> byte[] getBytes(Class<T> clazz, T obj)
			throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Marshaller marshaller = jc.createMarshaller();
		File f = new File("json/tmp.xml");
		marshaller.marshal(obj, f);
		byte[] bes = ReadWriteString.readBytes(f);
		if (bes == null)
			bes = new byte[0];
		return bes;
	}

	public static <T> String getString(Class<T> clazz, T obj)
			throws JAXBException {
		byte[] bes = getBytes(clazz, obj);
		return new String(bes);
	}
}