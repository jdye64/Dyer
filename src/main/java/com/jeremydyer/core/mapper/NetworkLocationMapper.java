package com.jeremydyer.core.mapper;

import com.jeremydyer.core.NetworkLocation;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jeremydyer on 3/27/14.
 */
public class NetworkLocationMapper
        implements ResultSetMapper<NetworkLocation> {

    public NetworkLocation map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        NetworkLocation location = new NetworkLocation();
        location.setDescription(r.getString("description"));
        location.setNetworkLocationId(r.getLong("network_location_id"));
        location.setMobileDescription(r.getString("mobile_description"));
        location.setPublicDns(r.getString("public_dns"));
        location.setPublicIpAddress(r.getString("public_ipaddress"));
        return location;
    }
}