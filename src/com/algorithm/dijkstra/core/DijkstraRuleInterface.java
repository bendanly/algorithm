/**
 * @(#)com.algorithm.dijkstra.DijkstraRuleInterface.java
 * 南京中兴软创科技有限责任公司
 * @date 2015-1-30
 */
package com.algorithm.dijkstra.core;

import java.util.List;

/**
 * 待业务模块实现的接口。<p>这个接口需要告诉搜索算法两个最基本的要素：<p>1、怎么去找相邻的下一个节点？<p>2、找到什么程度算结束？
 * 
 * @author liyang
 * @version 1.0
 * @since 2011-7-8 下午01:51:36
 */
public interface DijkstraRuleInterface<T extends DijkstraBaseNode<T>> {
	/**
	 * 怎么去找相邻的下一个节点？这个接口需要实现的逻辑就是告诉搜索算法，怎么去找下一个相邻节点？
	 * <p>
	 * 例子1：端子全程路由搜索情况。如果当前节点资源是端子，那找下一节点的方式是从成端表中查找下一个资源（纤芯/线对）。如果当前资源是纤芯/线对，那找下一节点的方式是从接续/成端表中找到下一个资源（纤芯/线对/端子）
	 * <p>
	 * 例子2：光缆段故障定位（即宏观搜索光缆段）。如果当前资源是交接设备/接头，那找下一节点的方式是通过缆段的两端设施，来得到连接的缆段。如果当前资源是缆段，那找下一节点的方式是通过通过缆段的两端设施，来得到连接的交接设备/接头。
	 * <p>
	 * 总而言之，把待搜索的组网资源，抽象成一张拓扑网络。网络中有各种节点/线资源。点和点、线和线、点和线通过各种关系连接起来。而你只需要实现这个连接关系的查询（通过成端/跳接/接续/线两端/多对多关联关系表等等等）。
	 * @param currentNode
	 * @return
	 * @throws AppException
	 */
	public List<T> getNextNodes(T currentNode) throws Exception;

	/**
	 * 搜索到什么地方结束？判断节点是否有必要继续往下处理。这里主要处理特殊业务引起的搜索结束的情况。如果不需要此特殊判断逻辑，则直接返回true即可
	 * <p>
	 * 例：如业务上需要如果搜索到接头盒时停止，等等
	 * @param baseNode
	 * @return 
	 * @throws Exception
	 */
	public boolean toContinue(T baseNode) throws Exception;
}
