package com.algorithm.dijkstra.demo.faultLocation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.algorithm.dijkstra.core.DijkstraRuleInterface;

public class DijkstraRuleImp2Section implements DijkstraRuleInterface<DijkstraNode2Section>{
	

	@Override
	public boolean toContinue(DijkstraNode2Section baseNode) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zte.resmaster.opticalCable.Algorithm.Dijkstra.RuleInterface
	 * #getNextNodes
	 * (com.zte.resmaster.opticalCable.Algorithm.Dijkstra.Node)
	 */

	public List<DijkstraNode2Section> getNextNodes(DijkstraNode2Section currentNode) throws Exception {
		List<DijkstraNode2Section> list = new ArrayList<DijkstraNode2Section>();
		float length = getSectLength(currentNode.getNodeTypeId(), currentNode.getNodeId());
		if (currentNode.getNodeTypeId().equals("317")|| currentNode.getNodeTypeId().equals("702")) {
			list = getNearNodeMic(currentNode);
			if (currentNode.getRouteList() != null && list != null) {
				for (DijkstraNode2Section backwardNode : currentNode.getRouteList()) {
					for (DijkstraNode2Section nextNode : list) {
						if (nextNode.getNodeTypeId().equals(backwardNode.getNodeTypeId()) && nextNode.getNodeId().equals(backwardNode.getNodeId())) {
							list.remove(nextNode);
							break;
						}
					}
				}
				/*
				for (DijkstraNode2Section nextNode : list) {
					List<DijkstraNode2Section> tList = new ArrayList<DijkstraNode2Section>();
					if (currentNode.getRouteList() != null) {
						tList.addAll(currentNode.getRouteList());
					}
					if (currentNode.getNodeTypeId() .equals(nextNode.getNodeTypeId())&& nextNode.getNodeTypeId().equals("702")) {
						DijkstraNode2Section connectorNode = this.getMidResByTwoFiber(currentNode.getNodeTypeId(), currentNode.getNodeId(),
								nextNode.getNodeTypeId(), nextNode.getNodeId());
						connectorNode.setLength(currentNode.getLength() + length);
						connectorNode.setRouteCount(currentNode.getRouteCount() + 1);
						
						tList.add(connectorNode);
						nextNode.setLength(currentNode.getLength() + length);
						nextNode.setRouteCount(connectorNode.getRouteCount() + 1);
					} else {
						nextNode.setLength(currentNode.getLength() + length);
						nextNode.setRouteCount(currentNode.getRouteCount() + 1);
					}
					tList.add(nextNode);
					nextNode.setRouteList(tList);
				}
				*/
				for (DijkstraNode2Section nextNode : list) {				
					nextNode.setLength(length);
				}
			}
		}
		return list;
	}

	private List<DijkstraNode2Section> getNearNodeMic(DijkstraNode2Section baseNode) throws Exception {		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DijkstraNode2Section> list = new ArrayList<DijkstraNode2Section>();
		String sql = "";
		String resTypeId = baseNode.getNodeTypeId();
		try {
			conn = getConnection();
			if (resTypeId.equals("702")) {
				sql = "select a.phy_port_id res_id,b.parent_res_spec_id res_type_id from phy_term_port a,pub_resource_spec b where a.res_spec_id=b.res_spec_id and a.phy_port_id in (select phy_port_id from cbl_fiber_conjunction where fiber_id=?) "
						+ "union "
						+ "select fiber_id,res_spec_id from cbl_fiber where fiber_id in (select a_line_id from cbl_fiber_continue where z_line_id=?) "
						+ "union "
						+ "select fiber_id,res_spec_id from cbl_fiber where fiber_id in (select z_line_id from cbl_fiber_continue where a_line_id=?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, baseNode.getNodeId());
				ps.setString(2, baseNode.getNodeId());
				ps.setString(3, baseNode.getNodeId());
			} else {
				sql = "select fiber_id res_id,res_spec_id res_type_id from cbl_fiber where fiber_id in (select fiber_id from cbl_fiber_conjunction where phy_port_id=?) "
						+ "union "
						+ "select a.phy_port_id,b.parent_res_spec_id from phy_term_port a,pub_resource_spec b where a.res_spec_id=b.res_spec_id and a.phy_port_id in (select a_phy_port_id from lnk_phy_link_component where z_phy_port_id=? and cbl_line_id is null) "
						+ "union "
						+ "select a.phy_port_id,b.parent_res_spec_id from phy_term_port a,pub_resource_spec b where a.res_spec_id=b.res_spec_id and a.phy_port_id in (select z_phy_port_id from lnk_phy_link_component where a_phy_port_id=? and cbl_line_id is null)";
				
				ps = conn.prepareStatement(sql);
				ps.setString(1, baseNode.getNodeId());
				ps.setString(2, baseNode.getNodeId());
				ps.setString(3, baseNode.getNodeId());
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				String nextNodeTypeId = rs.getString("res_type_id");
				String nextNodeId = rs.getString("res_id");
				DijkstraNode2Section tNode = new DijkstraNode2Section(nextNodeTypeId, nextNodeId);
				list.add(tNode);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		} finally {
			cleanUp(conn, null, ps, rs);
		}
		return list;
	}

	public float getSectLength(String resTypeId, String resId) throws Exception {		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		float length = 0;
		try {			
			conn = getConnection();
			if (resTypeId.equals("701")) {
				sql = "select sum(length) length from (select length from cbl_section where cbl_sect_id=? union all select length from cbl_coil where cbl_sect_id=?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, resId);
				ps.setString(2, resId);
				rs = ps.executeQuery();
				if (rs.next()) {
					length = rs.getFloat("length");
				}
			} else if (resTypeId.equals("702")) {
				sql = "select sum(length) length from ( "
						+ "select length from cbl_section where cbl_sect_id in (select cbl_sect_id from cbl_fiber where fiber_id=?) "
						+ "union all "
						+ "select length from cbl_coil where cbl_sect_id in (select cbl_sect_id from cbl_fiber where fiber_id=?))";
				ps = conn.prepareStatement(sql);
				ps.setString(1, resId);
				ps.setString(2, resId);
				rs = ps.executeQuery();
				if (rs.next()) {
					length = rs.getFloat("length");
				}
			} else {
				length = 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		} finally {
			cleanUp(conn, null, ps, rs);
		}
		return length;
	}

	public DijkstraNode2Section getMidResByTwoFiber(String resTypeId1, String resId1, String resTypeId2, String resId2)
			throws Exception {		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		DijkstraNode2Section node = null;
		try {
			conn = getConnection();
			sql = "select connector_id,res_spec_id from cbl_connector where connector_id in "
					+ "(select connector_id from cbl_fiber_continue where a_line_id=? and z_line_id=? "
					+ "union all select connector_id from cbl_fiber_continue where z_line_id=? and a_line_id=?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, resId1);
			ps.setString(2, resId2);
			ps.setString(3, resId1);
			ps.setString(4, resId2);
			rs = ps.executeQuery();
			if (rs.next()) {
				String resId = rs.getString("connector_id");
				String resTypeId = rs.getString("res_spec_id");
				node = new DijkstraNode2Section(resTypeId, resId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		} finally {
			cleanUp(conn, null, ps, rs);
		}
		return node;
	}
	protected Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		return DriverManager.getConnection("jdbc:oracle:thin:@10.45.53.42:1521:orcl", "RESYZ2", "RESYZ");
	}
	protected void cleanUp(Connection conn, CallableStatement cs, PreparedStatement ps, ResultSet rs)
			throws Exception {
		try {
			try {
				if (rs != null)
					rs.close();
			} finally {
				try {
					if (ps != null)
						ps.close();
				} finally {
					try {
						if (cs != null)
							cs.close();
					} finally {
						if (conn != null) {
							conn.close();
							conn = null;
						}
					}
				}
			}
		} catch (SQLException ex) {
			throw new Exception("clean database resource error:" + ex.getMessage());
		}
	}

}
