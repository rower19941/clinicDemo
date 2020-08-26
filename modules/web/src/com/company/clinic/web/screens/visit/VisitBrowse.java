package com.company.clinic.web.screens.visit;

import com.company.clinic.entity.Owner;
import com.company.clinic.service.VisitService;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Visit;

import javax.inject.Inject;
import java.util.Set;

@UiController("clinic_Visit.browse")
@UiDescriptor("visit-browse.xml")
@LookupComponent("visitsTable")
@LoadDataBeforeShow
public class VisitBrowse extends StandardLookup<Visit> {
    @Inject
    private GroupTable<Visit> visitsTable;

    @Inject
    private Notifications notifications;

    @Inject
    private MetadataTools metadataTools;

    @Inject
    private VisitService visitService;

    @Subscribe("costBtn")
    public void onCostBtnClick(Button.ClickEvent event) {
        Visit visit = visitsTable.getSingleSelected();
        if (visit != null) {
            notifications.create().withCaption("Hello, "+ visitService.cost(visit)).show();
        }
    }
    
}