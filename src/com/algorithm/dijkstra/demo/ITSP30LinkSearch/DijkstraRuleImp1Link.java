package com.algorithm.dijkstra.demo.ITSP30LinkSearch;

import java.util.ArrayList;
import java.util.List;

import com.algorithm.dijkstra.core.DijkstraRuleInterface;
import com.algorithm.dijkstra.demo.ITSP30LinkSearch.LinkDomain.LinkParamVo;
import com.algorithm.util.CommonUtils;

public class DijkstraRuleImp1Link implements DijkstraRuleInterface<DijkstraNode1Link> {

	public List<DijkstraNode1Link> getNextNodes(DijkstraNode1Link currentNode) throws Exception {
		List<DijkstraNode1Link> list = null;
		//DijkstraNode1Link curNode1Link = (DijkstraNode1Link)currentNode;
		list = this.getNearNode(currentNode.getNodeId(), currentNode.getLinks());

		return list;
	}
 
	private List<DijkstraNode1Link> getNearNode(String portId, List<LinkParamVo> links) throws Exception {
		List<DijkstraNode1Link> nodeList = new ArrayList<DijkstraNode1Link>();
		if (links != null && CommonUtils.isVailable(portId)) {
			for (LinkParamVo link : links) {
				if (portId.equals(link.getaPortId())) {
					DijkstraNode1Link node = new DijkstraNode1Link("1", link.getzPortId(), links);
					node.setLinkId(link.getId());
					nodeList.add(node);
				} else if (portId.equals(link.getzPortId())) {
					DijkstraNode1Link node = new DijkstraNode1Link("1", link.getaPortId(), links);
					node.setLinkId(link.getId());
					nodeList.add(node);
				}
			}
		}
		return nodeList;
	}

	@Override
	public boolean toContinue(DijkstraNode1Link baseNode) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}
}
