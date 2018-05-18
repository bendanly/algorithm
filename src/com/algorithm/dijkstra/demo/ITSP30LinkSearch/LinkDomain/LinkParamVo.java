/**
 * @(#)com.ztesoft.resmaster.module.linkEx.vo.params.LinkParamVo.java
 * 中兴软创科技股份有限公司
 * @date 2014�?�?1�?
 */
package com.algorithm.dijkstra.demo.ITSP30LinkSearch.LinkDomain;

import java.io.Serializable;

/**
 * 链路入参对象VO
 * 
 * @author <a href="li.yang8@zte.com.cn">李杨</a>
 * @version 1.0
 */

public class LinkParamVo implements Serializable{
	
	private static final long serialVersionUID = 2712204491141317104L;
	private String id = null;
	private String aPortId = null;//A端端口ID
	private String zPortId = null;//Z端端口ID
	/**
	 * 
	 * 
	 * @param id
	 *            必填
	 * @param aPortId
	 *            必填
	 * @param zPortId
	 */
	public LinkParamVo(String id, String aPortId, String zPortId) {
		this.id=id;
		this.aPortId=aPortId;
		this.zPortId=zPortId;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the aPortId
	 */
	public String getaPortId() {
		return aPortId;
	}
	/**
	 * @param aPortId the aPortId to set
	 */
	public void setaPortId(String aPortId) {
		this.aPortId = aPortId;
	}
	/**
	 * @return the zPortId
	 */
	public String getzPortId() {
		return zPortId;
	}
	/**
	 * @param zPortId the zPortId to set
	 */
	public void setzPortId(String zPortId) {
		this.zPortId = zPortId;
	}

}
