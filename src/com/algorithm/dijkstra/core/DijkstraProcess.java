/**
 * @(#)com.algorithm.dijkstra.DijkstraProcess.java
 * 南京中兴软创科技有限责任公司
 * @date 2015-1-30 上午9:06:21
 */
package com.algorithm.dijkstra.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.algorithm.util.CommonUtils;
import com.algorithm.util.Constant;
import com.algorithm.util.Constant.SearchType;
import com.sun.xml.internal.stream.Entity;

/**
 * 迪杰斯特拉算法主要实现类
 * 
 * @author liyang
 * @version 1.0
 * @since 2011-7-8 下午01:59:13
 */

public class DijkstraProcess<T extends DijkstraBaseNode<T>> {
	/**
	 * 最大搜索层次，超过此层次则停止搜索。小于等于0时，将无层数限制
	 */
	private int maxRoute = 0;
	/**
	 * 最大长度限制，超过此长度则停止搜索。小于等于0时，将无限制。通常用于带有长度的资源对象搜索，如各种线资源
	 */
	private float maxLength = 0;
	// 搜索规则
	private DijkstraRuleInterface<T> ruleInterface;

	// 缓存
	private Map<T, List<T>> cacheNodeMap = new HashMap<T, List<T>>();

	public DijkstraProcess(DijkstraRuleInterface<T> ruleInterface) {
		this.ruleInterface = ruleInterface;
	}

	public int getMaxRoute() {
		return maxRoute;
	}

	public void setMaxRoute(int maxRoute) {
		this.maxRoute = maxRoute;
	}

	public float getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(float maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * 搜索网络中所有可通达的节点。每个节点只保留最优路径，返回所有通达节点。20140226 liyang add
	 * 
	 * @param beginNode
	 *            起始节点
	 * @return
	 * @throws Exception
	 */
	public List<T> routeSearchBestOne(T beginNode, Constant.SearchType searchType) throws Exception {
		if (beginNode == null) {
			throw new Exception("起始节点不能为空！");
		}
		// 初始化待处理列表
		List<T> needProcessList = new ArrayList<T>();
		beginNode.putRouteNode(beginNode);
		needProcessList.add(beginNode);
		T currentProcessNode = null;

		do {
			// 得到待处理列表中的下一个节点
			currentProcessNode = null;
			for (T baseNode : needProcessList) {
				if (!this.needToProcessed(baseNode)) {
					baseNode.setProcessed(true);
				} else {
					currentProcessNode = baseNode;
					break;
				}
			}
			// 如果还有待处理节点
			if (currentProcessNode != null) {
				List<T> nextList = this.getNextNodes(currentProcessNode);
				if (nextList != null) {
					for (T nextNode : nextList) {
						// 刷新待处理列表
						refreshProcessList(needProcessList, nextNode,searchType);
					}
				}
				currentProcessNode.setProcessed(true);
			}
		} while (currentProcessNode != null);

		return needProcessList;
	};

	/**
	 * 每个节点只保留最优路径，然后只返回最优匹配的一个节点。如果目标节点明确（知道了ID），则搜出到达此节点的最优路径。如果目标节点不明确，
	 * 只知道目标类型（知道了resTypeId），则搜索出此resTypeId最优的一个目标类型节点，并含路径）
	 * 
	 * @param searchType
	 *            搜索类型 1 路径最短 2 节点数最短
	 * @return
	 * @throws Exception
	 */

	public T routeSearchBestOne(T beginNode, T destNode, Constant.SearchType searchType) throws Exception {
		if (beginNode == null || destNode == null) {
			throw new Exception("起止节点不能为空！");
		}
		if (!CommonUtils.isVailable(destNode.getNodeId()) && !CommonUtils.isVailable(destNode.getNodeTypeId())) {
			throw new Exception("目标节点必须有类型ID或者ID！");
		}
		// 初始化待处理列表
		List<T> needProcessList = new ArrayList<T>();
		beginNode.putRouteNode(beginNode);
		needProcessList.add(beginNode);
		T currentProcessNode = null;

		do {
			// 得到待处理列表中的下一个节点
			currentProcessNode = null;
			for (T baseNode : needProcessList) {
				if (!this.needToProcessed(baseNode)) {
					baseNode.setProcessed(true);
				} else {
					currentProcessNode = baseNode;
					break;
				}
			}

			if (currentProcessNode != null) {

				// 如果节点数优先的情况下，且目标节点是明确的，那最早找到满足条件的即是节点最优的，立即返回
				if (searchType == Constant.SearchType.nodeLevelFirst) {
					if (CommonUtils.isVailable(destNode.getNodeTypeId())
							&& CommonUtils.isVailable(destNode.getNodeId())
							&& currentProcessNode.getNodeTypeId().equals(destNode.getNodeTypeId())
							&& currentProcessNode.getNodeId().equals(destNode.getNodeId())) {
						return currentProcessNode;
					} else if (CommonUtils.isVailable(destNode.getNodeTypeId())
							&& !CommonUtils.isVailable(destNode.getNodeId())
							&& currentProcessNode.getNodeTypeId().equals(destNode.getNodeTypeId())) {
						return currentProcessNode;
					}
				} else {
					List<T> nextList = this.getNextNodes(currentProcessNode);
					if (nextList != null) {
						for (T nextNode : nextList) {
							refreshProcessList(needProcessList, nextNode,searchType);
						}
					}
				}
				currentProcessNode.setProcessed(true);

			}
		} while (currentProcessNode != null);
		// 根据目标节点属性的完整度输出最终节点
		// ID和类型ID都有
		if (CommonUtils.isVailable(destNode.getNodeId()) && CommonUtils.isVailable(destNode.getNodeTypeId())) {
			for (T baseNode : needProcessList) {
				if (baseNode.getNodeTypeId().equals(destNode.getNodeTypeId())
						&& baseNode.getNodeId().equals(destNode.getNodeId())) {
					return baseNode;
				}
			}
			// 只有类型ID，即只知道要找什么类型的，不知道具体对象
		} else if (!CommonUtils.isVailable(destNode.getNodeId()) && CommonUtils.isVailable(destNode.getNodeTypeId())) {
			T retNode = null;
			for (T baseNode : needProcessList) {
				if (baseNode.getNodeTypeId().equals(destNode.getNodeTypeId())) {
					if (retNode == null) {
						retNode = baseNode;
					} else {
						if (baseNode.getLength() < retNode.getLength()) {
							retNode = baseNode;
						}
					}
				}
			}
			return retNode;
		}

		return null;
	}

	/**
	 * 每个节点保留所有路径，返回目标节点的所有路径，若没有目标节点，则返回所有路径
	 * 
	 * @param beginNode
	 *            起始节点
	 * @param destNode
	 *            终止节点
	 * @param searchType
	 *            搜索类型。lengthFirst 路径最短 ，nodeLevelFirst 节点数最短
	 * @return
	 * @throws Exception
	 */
	public List<T> routeSearch(T beginNode, T destNode, Constant.SearchType searchType) throws Exception {
		if (beginNode == null) {
			throw new Exception("起始节点不可为空！");
		}
		// 初始化待处理列表
		List<T> needProcessList = new ArrayList<T>();
		beginNode.putRouteNode(beginNode);
		needProcessList.add(beginNode);

		T currentProcessNode = null;

		do {
			// 得到待处理列表中的下一个节点
			currentProcessNode = null;
			for (T baseNode : needProcessList) {
				if (!this.needToProcessed(baseNode)) {
					baseNode.setProcessed(true);
				} else {
					currentProcessNode = baseNode;
					break;
				}
			}

			if (currentProcessNode != null) {

				if (destNode != null && CommonUtils.isVailable(destNode.getNodeTypeId())
						&& CommonUtils.isVailable(destNode.getNodeId())) {
					if (currentProcessNode.getNodeTypeId().equals(destNode.getNodeTypeId())
							&& currentProcessNode.getNodeId().equals(destNode.getNodeId())) {
						currentProcessNode.setProcessed(true);
						continue;
					}
				}

				// 得到此节点的所有下联节点，并逐个加入
				List<T> nextList = this.getNextNodes(currentProcessNode);
				// 有下个节点的话，此节点不需要了
				if (nextList != null && nextList.size() > 0) {

					// needProcessList.remove(currentProcessNode);

					for (T nextNode : nextList) {
						needProcessList.add(nextNode);
					}
				}

				currentProcessNode.setProcessed(true);

			}
		} while (currentProcessNode != null);

		List<T> resultList = new ArrayList<T>();
		// 根据是否有目标节点，来分类处理
		if (destNode != null && CommonUtils.isVailable(destNode.getNodeTypeId())
				&& CommonUtils.isVailable(destNode.getNodeId())) {
			for (T baseNode : needProcessList) {
				if (baseNode.getNodeTypeId().equals(destNode.getNodeTypeId())
						&& baseNode.getNodeId().equals(destNode.getNodeId())) {
					if (resultList.size() == 0) {
						resultList.add(baseNode);
					} else {
						boolean isAdd = false;
						if (searchType == Constant.SearchType.nodeLevelFirst) {
							for (int i = 0; i < resultList.size(); i++) {
								T tempNode = resultList.get(i);
								if (baseNode.getRouteCount() < tempNode.getRouteCount()) {
									resultList.add(i, baseNode);
									isAdd = true;
									break;
								}
							}
						} else {
							for (int i = 0; i < resultList.size(); i++) {
								T tempNode = resultList.get(i);
								if (baseNode.getLength() < tempNode.getLength()) {
									resultList.add(i, baseNode);
									isAdd = true;
									break;
								}
							}
						}
						if (!isAdd) {
							resultList.add(baseNode);
						}
					}
				}
			}
			// 只有类型ID，即只知道要找什么类型的，不知道具体对象
		} else if (destNode != null && CommonUtils.isVailable(destNode.getNodeTypeId())
				&& !CommonUtils.isVailable(destNode.getNodeId())) {
			for (T baseNode : needProcessList) {
				if (baseNode.getNodeTypeId().equals(destNode.getNodeTypeId())) {
					if (resultList.size() == 0) {
						resultList.add(baseNode);
					} else {
						boolean isAdd = false;
						for (int i = 0; i < resultList.size(); i++) {
							T tempNode = resultList.get(i);
							if (baseNode.getLength() < tempNode.getLength()) {
								resultList.add(i, baseNode);
								isAdd = true;
								break;
							}
						}
						if (!isAdd) {
							resultList.add(baseNode);
						}
					}
				}
			}
		} else {
			for (T baseNode : needProcessList) {
				if (resultList.size() == 0) {
					resultList.add(baseNode);
				} else {
					boolean isAdd = false;
					for (int i = 0; i < resultList.size(); i++) {
						T tempNode = resultList.get(i);
						if (baseNode.getLength() < tempNode.getLength()) {
							resultList.add(i, baseNode);
							isAdd = true;
							break;
						}
					}
					if (!isAdd) {
						resultList.add(baseNode);
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * 刷新待处理列表（本算法的标志性思想，也是区别于普通广度遍历算法的地方）。
	 * <p>
	 * 思想说明：对下一个节点进行分析，如果此节点已经存在于待处理列表中，且到达此节点的路径更优，则用更优路径替换老路径。 同时将此下级节点标记为未处理
	 * ，目的是下一次从待处理列表中处理到此节点时，会重新处理此节点。并且调用此函数将所有从此节点到达的下一级节点的旧路径，替换为更优路径。
	 * <p>
	 * 简单描述为：点A通过若干点路径到了点B，然后到了点C。如果这时发现点A有更好的路到点B，那说明到达点C也有更好的路径。本函数的nextNode，
	 * 即为可能包含更优路径的点B
	 * <p>
	 * 白话描述为：如果起始点走到我这有更好的路，那原来从我这走到的其他地方，也有更好的路
	 * 
	 * @param needProcessList
	 *            待处理列表
	 * @param nextNode
	 *            下一个节点
	 * @return
	 * @throws Exception
	 */
	private int refreshProcessList(List<T> needProcessList, T nextNode, Constant.SearchType searchType) throws Exception {
		boolean isFind = false;
		if (nextNode == null) {
			return 0;
		}
		List<T> tempList = new ArrayList<T>();
		tempList.addAll(needProcessList);
		for (T baseNode : tempList) {
			// 如果节点已经存在于待处理列表中，则说明不是第一次到达，要判断一下路径是不是更优，这里即是判断getLenth的大小
			if (baseNode.getNodeTypeId().equals(nextNode.getNodeTypeId())
					&& baseNode.getNodeId().equals(nextNode.getNodeId())) {
				
				if(SearchType.lengthFirst == searchType)
				{
					if (baseNode.getLength() > nextNode.getLength()) {
						needProcessList.remove(baseNode);// 从待处理列表中移掉旧节点（包含了旧路径）
						needProcessList.add(nextNode);// 从待处理列表中增加新节点（包含了新路径，同时isProcessed为false）
					}
				}else if(SearchType.nodeLevelFirst == searchType)
				{
					if(baseNode.getRouteCount()>nextNode.getRouteCount())
					{
						needProcessList.remove(baseNode);// 从待处理列表中移掉旧节点（包含了旧路径）
						needProcessList.add(nextNode);// 从待处理列表中增加新节点（包含了新路径，同时isProcessed为false）
					}
				}

				isFind = true;
			}
		}
		if (!isFind) {// 如果第一次到达的新节点，则直接加入待处理列表
			needProcessList.add(nextNode);
		}
		return 1;
	}

	/**
	 * 得到下一批节点。此方法主要做了三件事。
	 * <p>
	 * 1、调用接口找出当前节点紧挨着的所有的附近节点。（这里究竟是找紧挨节点，还是其他逻辑找出的节点。一切根据业务需要来，以接口的具体实现为准，）
	 * <p>
	 * 2、从所有节点中去掉已经走过的节点。找到的这些节点中，如果有的已经存在当前节点的路由中，说明已经走过。为了避免走回头路无限绕下去，
	 * 必须要去掉这些节点。
	 * <p>
	 * 3、对剩下的所有节点进行处理。将当前节点的路径复制给剩下的所有节点，并且将路径中加上当前结点。节点属性累计上当前结点（长度、跳接层数等）
	 * 
	 * @param currentNode
	 *            当前结点
	 * @return 当前节点相邻的下一批节点
	 * @throws Exception
	 */
	private List<T> getNextNodes(T currentNode) throws Exception {
		List<T> nearNodeList,tempList;
		// 先从缓存中取
		if (cacheNodeMap.containsKey(currentNode)) {
			tempList = cacheNodeMap.get(currentNode);			
			nearNodeList = new ArrayList<T>();
			if(tempList!=null&&tempList.size()>0){
				for (T t : tempList) {
					T newNode = (T)t.clone();
					nearNodeList.add(newNode);
				}
			}
			//System.out.println(currentNode.getNodeId()+"[read cache]"+tempList.size());
		} else {
			nearNodeList = ruleInterface.getNextNodes(currentNode);	
			tempList = new ArrayList<T>();
			if(nearNodeList!=null&&nearNodeList.size()>0){
				for (T t : nearNodeList) {
					T newNode = (T)t.clone();
					tempList.add(newNode);
				}
			}
			cacheNodeMap.put(currentNode, tempList);
			//System.out.println(currentNode.getNodeId()+"[read db]"+tempList.size());
		}
		if (nearNodeList != null && nearNodeList.size() > 0) {
			nearNodeList = new ArrayList<T>(new HashSet<T>(nearNodeList));

			if (currentNode.getRouteList() != null && currentNode.getRouteList().size() > 0) {
				for (int i = nearNodeList.size() - 1; i >= 0; i--) {
					T nearNode = nearNodeList.get(i);
					if (currentNode.getRouteList().contains(nearNode)) {
						nearNodeList.remove(nearNode);
					}
				}
			}
			for (T node : nearNodeList) {
				List<T> routeList = new ArrayList<T>();
				if (currentNode.getRouteList() != null) {
					routeList.addAll(currentNode.getRouteList());
				}
				node.setLength(currentNode.getLength() + node.getLength());
				node.setRouteCount(currentNode.getRouteCount() + 1);
				routeList.add(node);
				node.setRouteList(routeList);
			}
		}
		return nearNodeList;
	}

	/**
	 * 判断搜索到此节点时，还有没有必要继续搜索下去了。
	 * <p>
	 * 这里有几个固定死的逻辑。1、节点已经被处理过，则停止。2、搜索层数已经超出最大限制，则停止。3、搜索路径的长度已超过最大限制，则停止。
	 * <p>
	 * 当以上条件都不满足，则按照接口的实现来判断是否停止。
	 * 
	 * @param baseNode
	 *            当前待判断节点
	 * @return 返回true为继续搜索，返回false为停止搜索
	 * @throws Exception
	 */
	private boolean needToProcessed(T baseNode) throws Exception {
		if (baseNode.isProcessed()) {// 被处理过
			return false;
		}
		if (maxRoute > 0 && baseNode.getRouteCount() >= maxRoute) {// 超出限制的层�?
			return false;
		}
		if (maxLength > 0 && baseNode.getLength() >= maxLength) {// 超出长度限制
			return false;
		}		
		return ruleInterface.toContinue(baseNode);
	}
}
