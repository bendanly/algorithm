package com.algorithm.dijkstra.demo.ITSP30LinkSearch;

import java.util.List;

import com.algorithm.dijkstra.core.DijkstraBaseNode;
import com.algorithm.dijkstra.demo.ITSP30LinkSearch.LinkDomain.LinkParamVo;

public class DijkstraNode1Link extends DijkstraBaseNode<DijkstraNode1Link> {

	private String linkId;
	private final List<LinkParamVo> links;

	public DijkstraNode1Link(String nodeTypeId, String nodeId, List<LinkParamVo> links) {
		super(nodeTypeId, nodeId);
		this.links = links;
	}

	/**
	 * @return the linkId
	 */
	public String getLinkId() {
		return linkId;
	}

	/**
	 * @param linkId
	 *            the linkId to set
	 */
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	/**
	 * @return the links
	 */
	public List<LinkParamVo> getLinks() {
		return links;
	}

}
