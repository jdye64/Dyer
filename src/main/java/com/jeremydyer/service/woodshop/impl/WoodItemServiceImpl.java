package com.jeremydyer.service.woodshop.impl;

import com.jeremydyer.core.woodshop.WoodItem;
import com.jeremydyer.core.woodshop.project.ProjectCut;
import com.jeremydyer.service.woodshop.WoodItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * User: Jeremy Dyer
 * Date: 4/2/14
 * Time: 11:06 AM
 */
@Service
public class WoodItemServiceImpl
    implements WoodItemService {

    private static final Logger logger = LoggerFactory.getLogger(WoodItemServiceImpl.class);


    @Override
    public WoodItem cutWood(WoodItem woodItem, ProjectCut cut) {
        if (woodItem.getHeight() != cut.getHeight()) {
            if (woodItem.getHeight() > cut.getHeight()) {
                woodItem.setHeight(woodItem.getHeight() - cut.getHeight());
            }
        }

        if (woodItem.getWidth() != cut.getWidth()) {
            if (woodItem.getWidth() > cut.getWidth()) {
                woodItem.setWidth(woodItem.getWidth() - cut.getWidth());
            }
        }

        //The length will always just be subtracted.
        woodItem.setLength(woodItem.getLength() - cut.getLength());

        return woodItem;
    }
}
