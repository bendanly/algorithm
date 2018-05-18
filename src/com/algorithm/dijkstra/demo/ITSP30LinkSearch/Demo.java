package com.algorithm.dijkstra.demo.ITSP30LinkSearch;

import java.util.ArrayList;
import java.util.List;

import com.algorithm.dijkstra.core.DijkstraProcess;
import com.algorithm.dijkstra.demo.ITSP30LinkSearch.LinkDomain.LinkParamVo;
import com.algorithm.util.Constant;
/**
 * 搜索算法应用示例1，将无序的链路组成理成有序链路组成，支持网状的链路结构
 * @author liyang
 *
 */
public class Demo {
	public static void main(String[] args) throws Exception {
		// /////////////////////////////////////////////模拟测试数据�?��////////////////////////////////////////////////
		List<LinkParamVo> lowerLinks = new ArrayList<LinkParamVo>();
		LinkParamVo link = new LinkParamVo("331997100000000000447071","331997100000000000452806","331997100000000000452937");
		
		lowerLinks.add(link);
		link = new LinkParamVo("331997100000000000454831","331997100000000000452937", "331997100000000000452967");
		
		lowerLinks.add(link);
		link = new LinkParamVo("331997100000000000453247","331997100000000000452967","331997100000000000453003");
		
		lowerLinks.add(link);
		link = new LinkParamVo("331997100000000000454832","331997100000000000453003", "331997100000000000453038");
		
		lowerLinks.add(link);
		link = new LinkParamVo("331997100000000000453354", "331997100000000000453038","331997100000000000453074");
		
		lowerLinks.add(link);
		link = new LinkParamVo("331997100000000000454833", "331997100000000000453074","331997100000000000453099");
		
		lowerLinks.add(link);
		link = new LinkParamVo("331997100000000000453441","331997100000000000453099", "331997100000000000453171");
		
		lowerLinks.add(link);
		// /////////////////////////////////////////////模拟测试数据结束////////////////////////////////////////////////

		// /////////////////////////////////////////////算法使用样例////////////////////////////////////////////////
		DijkstraProcess<DijkstraNode1Link> mainProcess = new DijkstraProcess<DijkstraNode1Link>(
				new DijkstraRuleImp1Link());
		DijkstraNode1Link beginNode = new DijkstraNode1Link("1", "331997100000000000453038", lowerLinks);

		mainProcess.setMaxRoute(-1);
		mainProcess.setMaxLength(900);
		List<DijkstraNode1Link> list = mainProcess.routeSearch(beginNode,null, Constant.SearchType.nodeLevelFirst);
		for (DijkstraNode1Link baseNode : list) {
			System.out.println("路由");
			for (DijkstraNode1Link tNode : baseNode.getRouteList()) {
				System.out.println("[" + tNode.getNodeTypeId() + "][" + tNode.getNodeId() + "][" + tNode.getLength()
						+ "]");
			}
		}
		if (beginNode.equals(beginNode)) {

		}
	}
}
