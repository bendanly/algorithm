package com.algorithm.dijkstra.demo.routeSearch;

import java.util.List;

import com.algorithm.dijkstra.core.DijkstraBaseNode;
import com.algorithm.dijkstra.demo.routeSearch.domain.LineVo;

public class RouteSearchNode extends DijkstraBaseNode<RouteSearchNode>{
	
	private final List<LineVo> lineList;
	public RouteSearchNode(String nodeTypeId, String nodeId,List<LineVo> lineList) {
		super(nodeTypeId, nodeId);
		this.lineList = lineList;
	}
	/**
	 * @return the lineList
	 */
	public List<LineVo> getLineList() {
		return lineList;
	}
}
