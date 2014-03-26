package com.jeremydyer.core;

import javax.ws.rs.HttpMethod;
import java.io.Serializable;

/**
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 1:12 PM
 */
public class NetworkDeviceServiceCommand
    implements Serializable {

    private Long deviceCommandId;
    private Long networkDeviceServiceId;    //Service that this command is for.
    private String serviceUri;              //All services are remote and conform to the REST standard
    private String httpMethod;          //Holds the type of method that should be used to invoke the service.
    private String commandName;
    private String commandDescription;



    public Long getDeviceCommandId() {
        return deviceCommandId;
    }

    public void setDeviceCommandId(Long deviceCommandId) {
        this.deviceCommandId = deviceCommandId;
    }

    public Long getNetworkDeviceServiceId() {
        return networkDeviceServiceId;
    }

    public void setNetworkDeviceServiceId(Long networkDeviceServiceId) {
        this.networkDeviceServiceId = networkDeviceServiceId;
    }

    public String getServiceUri() {
        return serviceUri;
    }

    public void setServiceUri(String serviceUri) {
        this.serviceUri = serviceUri;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandDescription() {
        return commandDescription;
    }

    public void setCommandDescription(String commandDescription) {
        this.commandDescription = commandDescription;
    }
}
