/**
 * @(#)com.algorithm.dijkstra.DijkstraBaseNode.java
 * 中兴软创科技股份有限公司
 * @date 2015-1-30
 */
package com.algorithm.dijkstra.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author <a href="li.yang8@zte.com.cn">李杨</a>
 * @version 1.0
 * @since 2015-1-30 上午9:06:21
 */

public class DijkstraBaseNode<T extends DijkstraBaseNode<T>> implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4992067826686552527L;

	private String nodeTypeId; // 节点类型
	private String nodeId; // 节点ID
	private float length;// 路径权重（长度）
	private int routeCount;// 当前路由层数
	private boolean isProcessed = false;
	private List<T> routeList = null;// 到达自己的路由

	public DijkstraBaseNode() {
	}

	public DijkstraBaseNode(String nodeTypeId, String nodeId) {
		this.nodeTypeId = nodeTypeId;
		this.nodeId = nodeId;
		this.length = 0;
		this.routeCount = 0;
		this.isProcessed = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		result = prime * result + ((nodeTypeId == null) ? 0 : nodeTypeId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		DijkstraBaseNode<?> other = (DijkstraBaseNode<?>) obj;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		} else if (!nodeId.equals(other.nodeId))
			return false;
		if (nodeTypeId == null) {
			if (other.nodeTypeId != null)
				return false;
		} else if (!nodeTypeId.equals(other.nodeTypeId))
			return false;
		return true;
	}

	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException ex) {
			throw new InternalError();
		}
	}

	/**
	 * public T clone() { try { ObjectOutputStream oo = null; ObjectInputStream
	 * oi = null; ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
	 * oo = new ObjectOutputStream(byteOut); oo.writeObject(this); oi = new
	 * ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray())); T
	 * node = (T) oi.readObject(); return node; } catch (IOException e) {
	 * e.printStackTrace(); } catch (ClassNotFoundException e) {
	 * e.printStackTrace(); }
	 * 
	 * return null; }
	 */

	/**
	 * @return the nodeTypeId
	 */
	public String getNodeTypeId() {
		return nodeTypeId;
	}

	/**
	 * @param nodeTypeId
	 *            the nodeTypeId to set
	 */
	public void setNodeTypeId(String nodeTypeId) {
		this.nodeTypeId = nodeTypeId;
	}

	/**
	 * @return the nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId
	 *            the nodeId to set
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return the length
	 */
	public float getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(float length) {
		this.length = length;
	}

	/**
	 * @return the routeCount
	 */
	public int getRouteCount() {
		return routeCount;
	}

	/**
	 * @return the isProcessed
	 */
	public boolean isProcessed() {
		return isProcessed;
	}

	/**
	 * @param isProcessed
	 *            the isProcessed to set
	 */
	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	/**
	 * @param routeCount
	 *            the routeCount to set
	 */
	public void setRouteCount(int routeCount) {
		this.routeCount = routeCount;
	}

	/**
	 * @return the routeList
	 */
	public List<T> getRouteList() {
		return routeList;
	}

	/**
	 * @param routeList
	 *            the routeList to set
	 */
	public void setRouteList(List<T> routeList) {
		this.routeList = routeList;
	}

	public void putRouteNode(T baseNode) {
		if (routeList == null) {
			routeList = new ArrayList<T>();
		}
		routeList.add(baseNode);
	}
}
