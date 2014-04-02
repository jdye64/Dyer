package com.jeremydyer.service.woodshop.impl;

import com.jeremydyer.core.woodshop.WoodItem;
import com.jeremydyer.dao.woodshop.WoodItemDao;
import com.jeremydyer.dao.woodshop.WoodSpeciesDao;
import com.jeremydyer.dao.woodshop.WoodTypeDao;
import com.jeremydyer.service.woodshop.WoodInventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Jeremy Dyer
 * Date: 3/31/14
 * Time: 2:12 PM
 */
@Service
public class WoodInventoryServiceImpl
    implements WoodInventoryService {

    private static final Logger logger = LoggerFactory.getLogger(WoodInventoryServiceImpl.class);

    @Autowired
    private WoodItemDao woodItemDao;

    @Autowired
    private WoodSpeciesDao woodSpeciesDao;

    @Autowired
    private WoodTypeDao woodTypeDao;


    @Override
    public List<WoodItem> retrieveInventory() {
        return woodItemDao.allWoodItems();
    }

    @Override
    public List<WoodItem> retrieveCheckedOutItems() {
        return woodItemDao.retrieveCheckedOutItems();
    }

    @Override
    public WoodItem checkinWoodItem(WoodItem woodItem) {
        //TODO: This isn't properly implemented anymore!
//        woodItem.setCheckoutStatus(CheckoutStatus.CHECKED_IN);
//
//        if (woodItem.getWoodInventoryId() == null) {
//            woodItem = woodItemDao.save(woodItem);
//        } else {
//            woodItem = woodItemDao.update(woodItem);
//        }

        return woodItem;
    }

    @Override
    public WoodItem checkoutWoodItem(Long woodItemId) {
        //TODO: This isn't properly implemented anymore!
        WoodItem wi = woodItemDao.find(woodItemId);
//        wi.setCheckoutStatus(CheckoutStatus.CHECKED_OUT);
//        woodItemDao.update(wi);
        return wi;
    }

    @Override
    public WoodItem retrieveWoodItem(Long woodItemId) {
        return woodItemDao.find(woodItemId);
    }
}
