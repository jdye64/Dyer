package com.jeremydyer.core.dto;

import com.jeremydyer.core.NetworkDevice;
import com.jeremydyer.core.NetworkDeviceService;
import com.jeremydyer.core.NetworkDeviceServiceCommand;

/**
 * Created by jeremydyer on 3/27/14.
 */
public class NetworkServiceCommandResponseDTO {

    private NetworkDevice device;
    private NetworkDeviceService service;
    private NetworkDeviceServiceCommand command;
    private String commandResponse;

    public NetworkServiceCommandResponseDTO(NetworkDevice device, NetworkDeviceService service,
                                            NetworkDeviceServiceCommand command, String commandResponse) {
        this.device = device;
        this.service = service;
        this.command = command;
        this.commandResponse = commandResponse;
    }

    public NetworkDevice getDevice() {
        return device;
    }

    public void setDevice(NetworkDevice device) {
        this.device = device;
    }

    public NetworkDeviceService getService() {
        return service;
    }

    public void setService(NetworkDeviceService service) {
        this.service = service;
    }

    public NetworkDeviceServiceCommand getCommand() {
        return command;
    }

    public void setCommand(NetworkDeviceServiceCommand command) {
        this.command = command;
    }

    public String getCommandResponse() {
        return commandResponse;
    }

    public void setCommandResponse(String commandResponse) {
        this.commandResponse = commandResponse;
    }
}
