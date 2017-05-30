package com.sea.cache.jedis.origin;

import java.io.Serializable;

public class KeyValEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String _key;
	private String _field;
	private String _val;

	public String getKey() {
		return this._key;
	}

	public void setKey(String key) {
		this._key = key;
	}

	public String getVal() {
		return this._val;
	}

	public void setVal(String val) {
		this._val = val;
	}

	public String getField() {
		return this._field;
	}

	public void setField(String field) {
		this._field = field;
	}

	public KeyValEntity() {
	}

	public KeyValEntity(String key, String val) {
		this._key = key;
		this._val = val;
	}

	public KeyValEntity(String key, String field, String val) {
		this._key = key;
		this._field = field;
		this._val = val;
	}
}
