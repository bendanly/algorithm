package com.algorithm.dijkstra.demo.routeSearch.domain;

import java.io.Serializable;

public class LineVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8140429997079656312L;

	private String aId;
	private String zId;
	private int len;

	/**
	 * @return the aId
	 */
	public String getaId() {
		return aId;
	}

	/**
	 * @param aId
	 *            the aId to set
	 */
	public void setaId(String aId) {
		this.aId = aId;
	}

	/**
	 * @return the zId
	 */
	public String getzId() {
		return zId;
	}

	/**
	 * @param zId
	 *            the zId to set
	 */
	public void setzId(String zId) {
		this.zId = zId;
	}

	/**
	 * @return the len
	 */
	public int getLen() {
		return len;
	}

	/**
	 * @param len
	 *            the len to set
	 */
	public void setLen(int len) {
		this.len = len;
	}

	public LineVo(String aId, String zId, int len) {
		this.aId = aId;
		this.zId = zId;
		this.len = len;
	}

}
