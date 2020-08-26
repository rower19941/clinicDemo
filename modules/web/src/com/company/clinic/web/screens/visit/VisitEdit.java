package com.company.clinic.web.screens.visit;

import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Visit;

@UiController("clinic_Visit.edit")
@UiDescriptor("visit-edit.xml")
@EditedEntityContainer("visitDc")
@LoadDataBeforeShow
public class VisitEdit extends StandardEditor<Visit> {

}