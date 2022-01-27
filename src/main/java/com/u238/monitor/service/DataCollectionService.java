package com.u238.monitor.service;

import com.u238.monitor.model.Summary;

public interface DataCollectionService {

    void collectData();

    Summary collectDataSummary();
}
