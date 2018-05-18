package com.algorithm.dijkstra.demo.routeSearch;

import java.util.ArrayList;
import java.util.List;

import com.algorithm.dijkstra.core.DijkstraRuleInterface;
import com.algorithm.dijkstra.demo.routeSearch.domain.LineVo;

public class RouteSearchImp implements DijkstraRuleInterface<RouteSearchNode> {

	@Override
	public List<RouteSearchNode> getNextNodes(RouteSearchNode currentNode) throws Exception {
		List<RouteSearchNode> list = new ArrayList<RouteSearchNode>();
		for (LineVo vo : currentNode.getLineList()) {
			RouteSearchNode node = new RouteSearchNode("999", "", currentNode.getLineList());
			if (vo.getaId().equals(currentNode.getNodeId())) {
				node.setNodeId(vo.getzId());
				node.setLength(vo.getLen());
				if (!list.contains(node)) {
					list.add(node);
				}
			} else if (vo.getzId().equals(currentNode.getNodeId())) {
				node.setNodeId(vo.getaId());
				node.setLength(vo.getLen());
				if (!list.contains(node)) {
					list.add(node);
				}
			}

		}
		return list;
	}

	@Override
	public boolean toContinue(RouteSearchNode baseNode) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}
}
