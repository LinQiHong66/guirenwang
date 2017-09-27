package com.inesv.digiccy.back.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/10/26 0026.
 */
public class MyAuthenticationFailureHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest arg0, HttpServletResponse arg1, AccessDeniedException arg2)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = arg0;
        // is ajax request?
        if ("XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With"))) {
            String msg = "{\"success\" : false, \"message\" : \"authentication-failure\"}";

            arg1.setContentType("json");
            OutputStream outputStream = arg1.getOutputStream();
            outputStream.write(msg.getBytes());
            outputStream.flush();
        }
    }

}
