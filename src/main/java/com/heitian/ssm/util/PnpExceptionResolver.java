package com.heitian.ssm.util;

import com.sun.deploy.net.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oasis on 11/28/16.
 */
@Component
public class PnpExceptionResolver implements HandlerExceptionResolver {

    private final Logger log = Logger.getLogger(PnpExceptionResolver.class);
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        log.debug("Error!!!", ex);
        return new ModelAndView("error");
    }
}
