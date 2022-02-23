package com.u238.monitor.controller;

import com.u238.monitor.utils.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;


@Controller
public class IndexController {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @RequestMapping("/")
    public String land(HttpServletRequest request) {
        logger.info(HttpUtil.getIp(request));
        return "index";
    }
}
