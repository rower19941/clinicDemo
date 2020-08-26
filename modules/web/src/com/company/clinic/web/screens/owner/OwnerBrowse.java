package com.company.clinic.web.screens.owner;

import com.haulmont.cuba.core.global.MetadataTools;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Owner;

import javax.inject.Inject;

@UiController("clinic_Owner.browse")
@UiDescriptor("owner-browse.xml")
@LookupComponent("ownersTable")
@LoadDataBeforeShow
public class OwnerBrowse extends StandardLookup<Owner> {
    @Inject
    private GroupTable<Owner> ownersTable;
    @Inject
    private Notifications notifications;
    @Inject
    private MetadataTools metadataTools;

    @Subscribe("ownersTable.greeting")
    public void onOwnersTableGreeting(Action.ActionPerformedEvent event) {
        Owner owner = ownersTable.getSingleSelected();
        if (owner != null) {
            notifications.create().withCaption("Hello, "+  metadataTools.getInstanceName(owner)).show();
        }



    }
    
}
