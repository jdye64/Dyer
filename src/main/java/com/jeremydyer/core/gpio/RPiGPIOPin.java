package com.jeremydyer.core.gpio;

import com.makeandbuild.persistence.jdbc.SaveWhen;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Represents a single RPiGPIOPin pin.
 *
 * User: Jeremy Dyer
 * Date: 4/4/14
 * Time: 10:30 AM
 */
public abstract class RPiGPIOPin
    implements Serializable {

    @Column(name = "gpio_pin")
    @SaveWhen(insert = true, update = true)
    private Long gpioPin;

    public Long getGpioPin() {
        return gpioPin;
    }

    public void setGpioPin(Long gpioPin) {
        this.gpioPin = gpioPin;
    }
}
