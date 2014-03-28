package com.jeremydyer.core.mapper;

import com.jeremydyer.core.NetworkDevice;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jeremydyer on 3/27/14.
 */
public class NetworkDeviceMapper
    implements ResultSetMapper<NetworkDevice> {

    public NetworkDevice map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
        NetworkDevice dev = new NetworkDevice();

        return dev;
    }
}
