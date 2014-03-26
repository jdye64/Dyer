package com.jeremydyer.core.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * Contains a request to execute a NetworkServiceCommand in the network environment.
 *
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 2:32 PM
 */
public class NetworkServiceCommandRequest
    implements Serializable {

    private Map<String, String> commandParameters;  //Parameters to pass to the command.

    public NetworkServiceCommandRequest() {}

    public Map<String, String> getCommandParameters() {
        return commandParameters;
    }

    public void setCommandParameters(Map<String, String> commandParameters) {
        this.commandParameters = commandParameters;
    }
}
