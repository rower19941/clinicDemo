package com.company.clinic.service;

import com.company.clinic.entity.Visit;

import java.util.List;

public interface VisitService {
    String NAME = "clinic_VisitService";
    List<Visit> getVisits();
    Visit getVisit(Long id);
    double cost(Visit visit);
}