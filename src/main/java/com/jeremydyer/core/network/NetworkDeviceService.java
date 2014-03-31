package com.jeremydyer.core.network;

import com.jeremydyer.core.network.enums.DyerServiceEnum;
import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Defines a custom service that is running on the Network Device. This is not meant to hold the linux level services
 * running on the device but rather the custom services that I have written like, GPIO, camera software, etc.
 *
 * User: Jeremy Dyer
 * Date: 3/26/14
 * Time: 11:00 AM
 */
@Table(name = "service")
//@Specialize(typeColumn = "javatype")
public class NetworkDeviceService
    implements Serializable {

    @Id
    @Column(name = "service_id")
    @SaveWhen(insert = true, update = false)
    private Long networkDeviceServiceId;

    @Column(name = "service_type")
    @SaveWhen(insert = true, update = true)
    private DyerServiceEnum serviceType;

    @Column(name = "service_port")
    @SaveWhen(insert = true, update = true)
    private int servicePort;

    public Long getNetworkDeviceServiceId() {
        return networkDeviceServiceId;
    }

    public void setNetworkDeviceServiceId(Long networkDeviceServiceId) {
        this.networkDeviceServiceId = networkDeviceServiceId;
    }

    public DyerServiceEnum getServiceType() {
        return serviceType;
    }

    public void setServiceType(DyerServiceEnum serviceType) {
        this.serviceType = serviceType;
    }

    public int getServicePort() {
        return servicePort;
    }

    public void setServicePort(int servicePort) {
        this.servicePort = servicePort;
    }
}
