package com.test.config;

import javax.servlet.ServletContext;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * AdminAppInitializer
 * Created on 28/06/2017 08:45
 *
 * @author Florian RUYNAT <florian.ruynat@ext.ombudsman.europa.eu>
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(MyAppConf.class);

        container.addListener(new ContextLoaderListener(context));
        container.addListener(new RequestContextListener());

        // Fix initParameter to anihilate jersey web initializer
        container.setInitParameter("contextConfigLocation", "");
    }
}
