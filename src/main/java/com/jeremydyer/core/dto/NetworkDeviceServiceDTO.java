package com.jeremydyer.core.dto;

import com.jeremydyer.core.NetworkDeviceService;
import com.jeremydyer.core.NetworkDeviceServiceCommand;
import com.jeremydyer.dao.NetworkDeviceServiceCommandDao;

import java.util.List;

/**
 * Created by jeremydyer on 3/27/14.
 */
public class NetworkDeviceServiceDTO {

    private NetworkDeviceService service;
    private List<NetworkDeviceServiceCommand> commands;

    public NetworkDeviceServiceDTO(NetworkDeviceService service, List<NetworkDeviceServiceCommand> commands) {
        this.service = service;
        this.commands = commands;
    }

    public NetworkDeviceService getService() {
        return service;
    }

    public void setService(NetworkDeviceService service) {
        this.service = service;
    }

    public List<NetworkDeviceServiceCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<NetworkDeviceServiceCommand> commands) {
        this.commands = commands;
    }
}
