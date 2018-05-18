package com.algorithm.dijkstra.demo.optRoadRouteSearch;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.algorithm.dijkstra.core.DijkstraProcess;
import com.algorithm.util.Constant;
/**
 * 光路路由搜索例子，基于V5链路模型，搜索通达的链路（局向光纤）
 * @author liyang
 *
 */
public class Demo {
	public static void main(String[] args) throws Exception {
		List<OptRoadRouteSearchNode> list = null;

		DijkstraProcess<OptRoadRouteSearchNode> dijkstraProcess = new DijkstraProcess<OptRoadRouteSearchNode>(
				new OptRoadRouteSearchImp());
		dijkstraProcess.setMaxLength(-1);
		OptRoadRouteSearchNode beginNode = new OptRoadRouteSearchNode();
		beginNode.setNodeTypeId("703");
		beginNode.setNodeId("14320000003803");
		dijkstraProcess.setMaxRoute(5);
		long beginTime = new Date().getTime();
		//list = dijkstraProcess.routeSearch(beginNode, null, Constant.SearchType.nodeLevelFirst);
		list = dijkstraProcess.routeSearchBestOne(beginNode,Constant.SearchType.nodeLevelFirst);
		long endTime = new Date().getTime();
		
		if (list != null) {
			int i = 0;
			for (OptRoadRouteSearchNode destNode : list) {
				System.out.println("######>" + i + "<#####");
				for (OptRoadRouteSearchNode routeNode : destNode.getRouteList()) {
					StringBuffer sBuffer = new StringBuffer();
					sBuffer.append("[").append(routeNode.getNodeTypeId()).append("][").append(routeNode.getNodeId())
							.append("]").append("->").append(routeNode.getLength());
					System.out.println(sBuffer.toString());
				}
			}
		}
		System.out.println("耗时:"+(endTime-beginTime)+"ms");
	}
}
