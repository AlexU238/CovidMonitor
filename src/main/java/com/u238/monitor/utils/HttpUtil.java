package com.u238.monitor.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

// has elements of code by LionRanger at https://dirask.com/posts/Spring-Boot-get-client-IP-address-from-request-HttpServletRequest-pBv9Bp
public final class HttpUtil {

    private static final String[] IP_HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"

            //can add more matching headers here
    };

    private HttpUtil() {

    }

    private static String getRequestIP(HttpServletRequest request) {
        for (String header : IP_HEADERS) {
            String value = request.getHeader(header);
            if (value == null || value.isEmpty()) {
                continue;
            }
            String[] parts = value.split("\\s*,\\s*");
            return parts[0];
        }
        return request.getRemoteAddr();
    }

    public static String getIp(HttpServletRequest request) {
        String ip = getRequestIP(request);

        // the if section is for when the index page is entered form localhost
        if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                return null;
            }
        } else {
            return ip;
        }
    }
}


