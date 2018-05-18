package com.algorithm.dijkstra.demo.optRoadRouteSearch;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.algorithm.dijkstra.core.DijkstraRuleInterface;

public class OptRoadRouteSearchImp implements DijkstraRuleInterface<OptRoadRouteSearchNode>{

	@Override
	public List<OptRoadRouteSearchNode> getNextNodes(OptRoadRouteSearchNode currentNode) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OptRoadRouteSearchNode> list = new ArrayList<OptRoadRouteSearchNode>();
		StringBuffer sql = new StringBuffer();		
		
		try {
			conn = getConnection();			
			sql
					.append(
							"select b.res_spec_id res_type_id, b.phy_eqp_id res_id,nvl(b.bse_eqp_id,-1) bse_eqp_id from lnk_phy_link_component a ")
					.append(
					"join phy_equipment b on b.phy_eqp_id = a.a_phy_eqp_id ").append(
					"join phy_equipment c on c.phy_eqp_id = a.z_phy_eqp_id where c.phy_eqp_id = ? and nvl(b.bse_eqp_id,-2)<>? and b.res_spec_id in (411,414,703,704,744,1165,8537) ")
					.append(
					"and exists(select 1 from (select link_id from lnk_phy_link_component ").append(
					"where link_id in (select a.link_id from lnk_phy_link_component a ").append(
					"join lnk_link b on b.link_id=a.link_id where b.mnt_state_id=170351 and b.res_spec_id=1614 and b.is_useable = '0' ")
					.append(
					"and not exists(select 1 from lnk_busi_link_2_link t where t.link_id=b.link_id) ").append(
					"and not exists(select 1 from lgc_line_link k where k.link_id=b.link_id) ) ").append(
					"group by link_id having count(*)=1) t where t.link_id = a.link_id) ").append("union ").append(
					"select c.res_spec_id, c.phy_eqp_id,nvl(c.bse_eqp_id,-1) from lnk_phy_link_component a ")
					.append(
					"join phy_equipment b on b.phy_eqp_id = a.a_phy_eqp_id ").append(
					"join phy_equipment c on c.phy_eqp_id = a.z_phy_eqp_id where b.phy_eqp_id = ? and nvl(c.bse_eqp_id,-2)<>? and c.res_spec_id in (411,414,703,704,744,1165,8537) ")
					.append(
					"and exists(select 1 from (select link_id from lnk_phy_link_component ").append(
					"where link_id in (select a.link_id from lnk_phy_link_component a ").append(
					"join lnk_link b on b.link_id=a.link_id where b.mnt_state_id=170351 and b.res_spec_id=1614 and b.is_useable = '0' ")
					.append(
					"and not exists(select 1 from lnk_busi_link_2_link t where t.link_id=b.link_id) ").append(
					"and not exists(select 1 from lgc_line_link k where k.link_id=b.link_id) ) ").append(
					"group by link_id having count(*)=1) t where t.link_id = a.link_id)");			
				ps = conn.prepareStatement(sql.toString());
				ps.setLong(1, Long.parseLong(currentNode.getNodeId()));
				ps.setLong(2, -1);
				ps.setLong(3, Long.parseLong(currentNode.getNodeId()));
				ps.setLong(4, -1);
				rs = ps.executeQuery();
				while (rs.next()) {
					OptRoadRouteSearchNode node = new OptRoadRouteSearchNode();
					node.setNodeTypeId(String.valueOf(rs.getLong("res_type_id")));
					node.setNodeId(String.valueOf(rs.getLong("res_id")));
					list.add(node);
				}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		} finally {
			cleanUp(conn, null, ps, rs);
		}
		return list;
	}

	@Override
	public boolean toContinue(OptRoadRouteSearchNode baseNode) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	//****************此段代码为示例之外的代码，用来获得数据库连接。实际情况中，会有框架模块提供数据库连接**************************
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
	//*******************************************
}
