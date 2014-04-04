package com.jeremydyer.service.power;

import com.jeremydyer.core.power.PowerOutlet;

/**
 * User: Jeremy Dyer
 * Date: 4/4/14
 * Time: 11:00 AM
 */
public interface PowerService {

    /**
     * Update the RPi GPIO pin state with the new information and returns the raw response
     * from the RPi
     *
     * @return
     *  Raw JSON response from the RPi
     */
    String setRPiPin(PowerOutlet powerOutlet);
}
