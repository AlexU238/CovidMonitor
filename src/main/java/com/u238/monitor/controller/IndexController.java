package com.u238.monitor.controller;

import com.u238.monitor.service.DataCollectionService;
import com.u238.monitor.utils.DBCheckUtil;
import com.u238.monitor.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;


@Controller
public class IndexController {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    private DataCollectionService dataCollectionService;
    private DBCheckUtil checkUtil;

    @Autowired
    public IndexController(@Qualifier("covidDataCollectionService") DataCollectionService dataCollectionService,
                           @Qualifier("DBInfoCheck") DBCheckUtil checkUtil) {
        this.dataCollectionService = dataCollectionService;
        this.checkUtil = checkUtil;
    }

    @RequestMapping("/")
    public String land(HttpServletRequest request) {
        logger.info("User with IP : " + HttpUtil.getIp(request));


        if (!checkUtil.check()) {
            dataCollectionService.collectData();
        }

        return "index";
    }
}
