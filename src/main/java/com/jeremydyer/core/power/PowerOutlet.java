package com.jeremydyer.core.power;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Represents a RPiGPIOPin pin in the terms of being the gate to turn on an off individual power outlets.
 *
 * User: Jeremy Dyer
 * Date: 4/4/14
 * Time: 10:30 AM
 */
@Table(name = "power_outlet")
public class PowerOutlet
    implements Serializable {

    @Id
    @Column(name = "power_outlet_id")
    @SaveWhen(insert = true, update = false)
    private Long powerOutletId;

    @Column(name = "network_device_id")
    @SaveWhen(insert = true, update = true)
    private Long networkDeviceId;

    @Column(name = "small_photo_id")
    @SaveWhen(insert = true, update = true)
    private Long smallPhotoId;

    @Column(name = "description")
    @SaveWhen(insert = true, update = true)
    private String description;

    @Column(name = "short_description")
    @SaveWhen(insert = true, update = true)
    private String shortDescription;

    @Column(name = "gpio_pin")
    @SaveWhen(insert = true, update = true)
    private int gpioPin;

    @Column(name = "turned_on")
    @SaveWhen(insert = true, update = true)
    private int turnedOn;

    public Long getPowerOutletId() {
        return powerOutletId;
    }

    public void setPowerOutletId(Long powerOutletId) {
        this.powerOutletId = powerOutletId;
    }

    public Long getNetworkDeviceId() {
        return networkDeviceId;
    }

    public void setNetworkDeviceId(Long networkDeviceId) {
        this.networkDeviceId = networkDeviceId;
    }

    public Long getSmallPhotoId() {
        return smallPhotoId;
    }

    public void setSmallPhotoId(Long smallPhotoId) {
        this.smallPhotoId = smallPhotoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getGpioPin() {
        return gpioPin;
    }

    public void setGpioPin(int gpioPin) {
        this.gpioPin = gpioPin;
    }

    public int getTurnedOn() {
        return turnedOn;
    }

    public void setTurnedOn(int turnedOn) {
        this.turnedOn = turnedOn;
    }
}
