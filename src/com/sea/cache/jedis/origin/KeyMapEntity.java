package com.sea.cache.jedis.origin;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({ "unused", "rawtypes" })
public class KeyMapEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String _key;
	private Map _orgin;

	public String getKey() {
		return this._key;
	}

	public void setKey(String key) {
		this._key = key;
	}

	public void setMap(Map origin) {
		this._orgin = origin;
	}

	public Map<String, String> getMapData() {
		if (this._orgin == null)
			return null;
		Map<String, String> dataMap = new HashMap<String, String>();
		try {
			for (Object key : this._orgin.keySet()) {
				Object val = this._orgin.get(key);
				if (val == null)
					continue;
				dataMap.put(key.toString(), val.toString());
			}
		} catch (Exception e) {
			dataMap = null;
			return null;
		}
		return dataMap;
	}

	private KeyMapEntity() {
	}

	public KeyMapEntity(String key, Map orign) {
		this._key = key;
		this._orgin = orign;
	}
}
