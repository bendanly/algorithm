package com.algorithm.dijkstra.demo.routeSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.algorithm.dijkstra.core.DijkstraProcess;
import com.algorithm.dijkstra.demo.routeSearch.domain.LineVo;
import com.algorithm.util.Constant.SearchType;

/**
 * 二维网络结构搜索示例。原始数据示例图见 com.algorithm.demo.routeSearch.节点网络示例.jpg
 * 
 * @author liyang
 * 
 */
public class Demo {
	public static void main(String args[]) throws Exception {
		System.out.println("findUserBySessionID调用前："+new Date().toLocaleString());
		// 按照图例，先把示例数据做好
		List<LineVo> lineList = new ArrayList<LineVo>();
		LineVo vo = new LineVo("1", "2", 10);
		lineList.add(vo);
		vo = new LineVo("1", "2", 10);
		lineList.add(vo);
		vo = new LineVo("1", "6", 3);
		lineList.add(vo);
		vo = new LineVo("2", "1", 10);
		lineList.add(vo);
		vo = new LineVo("2", "3", 5);
		lineList.add(vo);
		vo = new LineVo("2", "5", 8);
		lineList.add(vo);
		vo = new LineVo("3", "2", 5);
		lineList.add(vo);
		vo = new LineVo("3", "4", 15);
		lineList.add(vo);
		vo = new LineVo("3", "9", 11);
		lineList.add(vo);
		vo = new LineVo("4", "3", 15);
		lineList.add(vo);
		vo = new LineVo("4", "10", 14);
		lineList.add(vo);
		vo = new LineVo("5", "2", 8);
		lineList.add(vo);
		vo = new LineVo("5", "9", 9);
		lineList.add(vo);
		vo = new LineVo("5", "6", 6);
		lineList.add(vo);
		vo = new LineVo("6", "1", 3);
		lineList.add(vo);
		vo = new LineVo("6", "5", 6);
		lineList.add(vo);
		vo = new LineVo("6", "7", 5);
		lineList.add(vo);
		vo = new LineVo("6", "8", 6);
		lineList.add(vo);
		vo = new LineVo("7", "6", 5);
		lineList.add(vo);
		vo = new LineVo("7", "8", 5);
		lineList.add(vo);
		vo = new LineVo("8", "6", 6);
		lineList.add(vo);
		vo = new LineVo("8", "7", 5);
		lineList.add(vo);
		vo = new LineVo("8", "9", 7);
		lineList.add(vo);
		vo = new LineVo("8", "10", 10);
		lineList.add(vo);
		vo = new LineVo("8", "11", 4);
		lineList.add(vo);
		vo = new LineVo("9", "3", 11);
		lineList.add(vo);
		vo = new LineVo("9", "5", 9);
		lineList.add(vo);
		vo = new LineVo("9", "8", 7);
		lineList.add(vo);
		vo = new LineVo("9", "10", 15);
		lineList.add(vo);
		vo = new LineVo("10", "4", 14);
		lineList.add(vo);
		vo = new LineVo("10", "9", 15);
		lineList.add(vo);
		vo = new LineVo("10", "8", 10);
		lineList.add(vo);
		vo = new LineVo("10", "12", 8);
		lineList.add(vo);
		vo = new LineVo("10", "13", 5);
		lineList.add(vo);
		vo = new LineVo("11", "8", 4);
		lineList.add(vo);
		vo = new LineVo("11", "12", 9);
		lineList.add(vo);
		vo = new LineVo("12", "11", 9);
		lineList.add(vo);
		vo = new LineVo("12", "10", 8);
		lineList.add(vo);
		vo = new LineVo("12", "14", 10);
		lineList.add(vo);
		vo = new LineVo("13", "10", 5);
		lineList.add(vo);
		vo = new LineVo("13", "15", 12);
		lineList.add(vo);
		vo = new LineVo("14", "12", 10);
		lineList.add(vo);
		vo = new LineVo("14", "15", 11);
		lineList.add(vo);
		vo = new LineVo("15", "13", 12);
		lineList.add(vo);
		vo = new LineVo("15", "14", 11);
		lineList.add(vo);
		RouteSearchNode beginNode = new RouteSearchNode("999", "15", lineList);
		DijkstraProcess<RouteSearchNode> process = new DijkstraProcess<RouteSearchNode>(new RouteSearchImp());
		RouteSearchNode endNode = new RouteSearchNode("999", "1", lineList);

		// 搜节点15到节点1的最优路径
		System.out.println("*************************示例1：从起始节点15到达终止节点1开始*****************************");
		RouteSearchNode result = process.routeSearchBestOne(beginNode, endNode, SearchType.lengthFirst);
		System.out.println("[15]到达[1]的最优路径是：");
		for (RouteSearchNode node : result.getRouteList()) {
			System.out.print("->>" + node.getNodeId());
		}
		System.out.println();
		System.out.println("*************************示例1：从起始节点15到达终止节点1结束*****************************");
		System.out.println();

		// 某个点到达网络结构中的所有节点的最优路径
		System.out.println("*************************示例2：从起始节点15到达网络中的所有节点的最优路径开始********************");
		// 搜索过程中会修改原始入参节点，如果想第二次搜索，请重新初始化起始节点
		beginNode = new RouteSearchNode("999", "15", lineList);
		List<RouteSearchNode> allNode = process.routeSearchBestOne(beginNode,SearchType.lengthFirst);
		for (RouteSearchNode node1 : allNode) {
			System.out.print("节点[" + beginNode.getNodeId() + "]到达节点[" + node1.getNodeId() + "]的最优长度为：" + node1.getLength()
					+ "最优路由：");
			if (node1.getRouteList() != null) {
				for (RouteSearchNode node2 : node1.getRouteList()) {
					System.out.print("->>" + node2.getNodeId());
				}
			}
			System.out.println();
		}
		System.out.println("*************************示例2：从起始15节点到达网络中的所有节点的最优路径结束********************");
		System.out.println();

		// 某个起点到某个目标节点所有可能的路径
		System.out.println("*************************示例3：从起始节点15到达目标节点1的所有路径开始********************");
		beginNode = new RouteSearchNode("999", "15", lineList);
		endNode = new RouteSearchNode("999", "1", lineList);
		allNode = process.routeSearch(beginNode, endNode, SearchType.lengthFirst);
		Collections.sort(allNode, new Comparator<RouteSearchNode>() {
			@Override
			public int compare(RouteSearchNode o1, RouteSearchNode o2) {
				int i1 = Integer.parseInt(o1.getNodeId());
				int i2 = Integer.parseInt(o2.getNodeId());
				if (i1 < i2) {
					return -1;
				} else if (i1 > i2) {
					return 1;
				} else {
					return 0;
				}
			}

		});
		for (RouteSearchNode node1 : allNode) {
			System.out.print("节点[" + beginNode.getNodeId() + "]到达节点[" + node1.getNodeId() + "]的长度为：" + node1.getLength()
					+ "可能路由：");
			for (RouteSearchNode node2 : node1.getRouteList()) {
				System.out.print("->>" + node2.getNodeId());
			}
			System.out.println();
		}
		System.out.println("*************************示例3：从起始节点15到达目标节点1的所有路径结束********************");
	}
}
