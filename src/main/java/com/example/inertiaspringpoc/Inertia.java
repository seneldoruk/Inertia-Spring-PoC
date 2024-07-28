package com.example.inertiaspringpoc;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;


public class Inertia {
    static ObjectMapper objectMapper = new ObjectMapper();
    static String htmlStart;
    static String htmlEnd;

    public static void render(String component, Object props) throws IOException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        assert response != null;

        var inertiaHeader = request.getHeader("X-Inertia");
        var isInertiaRequest = inertiaHeader != null && inertiaHeader.equals("true");

        var url = request.getRequestURI();

        var page = new InertiaPage(component, props, url, "demo");
        var json = objectMapper.writeValueAsString(page);

        var writer = response.getWriter();
        if (isInertiaRequest) {
            response.setContentType("application/json");
            response.setHeader("Vary", "X-Inertia");
            response.setHeader("X-Inertia", "true");
            writer.write(json);
        } else {
            if (htmlStart == null) {
                var stream = new ClassPathResource("static/index.html").getInputStream();
                var indexHtml = StreamUtils.copyToString(stream, StandardCharsets.UTF_8);
                stream.close();
                var div = "<div id=\"root\"";
                var htmlArr = indexHtml.split(Pattern.quote(div));
                htmlStart = htmlArr[0] + div;
                htmlEnd = htmlArr[1];
            }

            response.setContentType("text/html");
            var tag = " data-page='" + json + "'";
            writer.write(htmlStart + tag + htmlEnd);
        }
        writer.flush();
        writer.close();

    }
}

record InertiaPage(String component, Object props, String url, String version) {
}
