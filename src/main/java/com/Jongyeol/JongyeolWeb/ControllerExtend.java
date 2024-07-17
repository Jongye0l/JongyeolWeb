package com.Jongyeol.JongyeolWeb;

import jakarta.servlet.http.HttpServletRequest;
import kr.jongyeol.jaServer.Logger;

public class ControllerExtend {
    public static void info(HttpServletRequest request, String string) {
        String ip = getIp(request);
        Logger logger = Logger.getLogger(ip);
        if(logger == null) Logger.MAIN_LOGGER.info(ip + ": " + string);
        else logger.info(string);
    }

    public static void error(HttpServletRequest request, String string) {
        String ip = getIp(request);
        Logger logger = Logger.getLogger(ip);
        if(logger == null) Logger.MAIN_LOGGER.error(ip + ": " + string);
        else logger.error(string);
    }

    public static void error(HttpServletRequest request, Exception e) {
        String ip = getIp(request);
        Logger logger = Logger.getLogger(ip);
        if(logger == null) Logger.MAIN_LOGGER.error(e, ip);
        else logger.error(e);
    }

    public static String getIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("Jongyeol-Nginx-IP");
        if(ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress))
            ipAddress = request.getRemoteAddr();
        else ipAddress = ipAddress.split(",")[0];
        return ipAddress;
    }
}
