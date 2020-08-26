package com.company.clinic.web.screens.usedconsumables;

import com.company.clinic.entity.Consumable;
import com.company.clinic.service.ConsumablesService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import java.util.List;

@UiController("clinic_UsedConsumables")
@UiDescriptor("used-consumables.xml")
public class UsedConsumables extends Screen {
    @Inject
    private CollectionContainer<Consumable> consumablesDc;

    @Inject
    private ConsumablesService consumablesService;
    @Inject
    private DataManager dataManager;

    @Subscribe
    public void onInit(InitEvent event) {
        //first way
        //final List<Consumable> consumables = consumablesService.getConsumables();
        //consumablesDc.setItems(consumables);

        //second way
        LoadContext.Query query = new LoadContext.Query("select distinct c from clinic_Consumable c " +
                "where @between(c.createTs, now-7, now+1 , day)");

        LoadContext<Consumable> loadContext = new LoadContext<>(Consumable.class)
                .setQuery(query).setView(View.LOCAL);

        consumablesDc.setItems(dataManager.loadList(loadContext));

    }
}