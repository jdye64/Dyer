package com.jeremydyer.service.woodshop;

import com.jeremydyer.core.woodshop.WoodItem;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 2:12 PM
 */
public interface WoodInventoryService {

    List<WoodItem> retrieveInventory();

    List<WoodItem> retrieveCheckedOutItems();

    WoodItem checkinWoodItem(WoodItem woodItem);

    WoodItem checkoutWoodItem(Long woodItemId);

    WoodItem retrieveWoodItem(Long woodItemId);
}
