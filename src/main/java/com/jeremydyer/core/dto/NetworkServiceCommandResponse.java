package com.jeremydyer.core.dto;

import java.io.Serializable;

/**
 * Wrapper for the response from executing a NetworkServiceCommand.
 *
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 2:43 PM
 */
public class NetworkServiceCommandResponse
    implements Serializable {

    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
