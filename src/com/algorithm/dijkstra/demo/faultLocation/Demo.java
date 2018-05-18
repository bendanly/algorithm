package com.algorithm.dijkstra.demo.faultLocation;

import java.util.List;

import com.algorithm.dijkstra.core.DijkstraProcess;
import com.algorithm.util.Constant;
/**
 * 光路故障定位例子（还需要测试）
 * @author liyang
 *
 */
public class Demo {
	public static void main(String[] args) throws Exception {
		List<DijkstraNode2Section> list = null;

		DijkstraProcess<DijkstraNode2Section> dijkstraProcess = new DijkstraProcess<DijkstraNode2Section>(
				new DijkstraRuleImp2Section());
		dijkstraProcess.setMaxLength(-1);
		DijkstraNode2Section beginNode = new DijkstraNode2Section("317", "14300001716305");

		list = dijkstraProcess.routeSearch(beginNode, null, Constant.SearchType.nodeLevelFirst);
		if (list != null) {
			int i = 0;
			for (DijkstraNode2Section destNode : list) {
				System.out.println("######>" + i + "<#####");
				for (DijkstraNode2Section routeNode : destNode.getRouteList()) {
					StringBuffer sBuffer = new StringBuffer();
					sBuffer.append("[").append(routeNode.getNodeTypeId()).append("][").append(routeNode.getNodeId())
							.append("]").append("->").append(routeNode.getLength());
					System.out.println(sBuffer.toString());
				}
			}
		}
	}
}
