package com.jeremydyer.core;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 1:12 PM
 */
@Table(name = "service_command")
//@Specialize(typeColumn = "javatype")
public class NetworkDeviceServiceCommand
        implements Serializable {

    @Id
    @Column(name = "service_command_id")
    @SaveWhen(insert = true, update = false)
    private Long serviceCommandId;

    @Column(name = "device_service_id")
    @SaveWhen(insert = true, update = true)
    private Long networkDeviceServiceId;    //Service that this command is for.

    @Column(name = "command_uri")
    @SaveWhen(insert = true, update = true)
    private String commandUri;              //All services are remote and conform to the REST standard

    @Column(name = "http_method")
    @SaveWhen(insert = true, update = true)
    private String httpMethod;          //Holds the type of method that should be used to invoke the service.

    @Column(name = "command_name")
    @SaveWhen(insert = true, update = true)
    private String commandName;

    @Column(name = "command_desc")
    @SaveWhen(insert = true, update = true)
    private String commandDescription;

    @Column(name = "command_mobile_desc")
    @SaveWhen(insert = true, update = true)
    private String commandMobileDescription;


    public Long getServiceCommandId() {
        return serviceCommandId;
    }

    public void setServiceCommandId(Long serviceCommandId) {
        this.serviceCommandId = serviceCommandId;
    }

    public Long getNetworkDeviceServiceId() {
        return networkDeviceServiceId;
    }

    public void setNetworkDeviceServiceId(Long networkDeviceServiceId) {
        this.networkDeviceServiceId = networkDeviceServiceId;
    }

    public String getCommandUri() {
        return commandUri;
    }

    public void setCommandUri(String commandUri) {
        this.commandUri = commandUri;
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

    public String getCommandMobileDescription() {
        return commandMobileDescription;
    }

    public void setCommandMobileDescription(String commandMobileDescription) {
        this.commandMobileDescription = commandMobileDescription;
    }
}
