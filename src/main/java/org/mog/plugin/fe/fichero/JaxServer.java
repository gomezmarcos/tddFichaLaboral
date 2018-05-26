package org.mog.plugin.fe.fichero;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class JaxServer {
    public static void main(String[] args) throws Exception {
        /*
        Server server = new Server(8080);

        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setHost("0.0.0.0");
        serverConnector.setPort(8090);
        serverConnector.setName("main");

        server.addConnector(serverConnector);
        */

        Server server = new Server(8080);
        new ServletContextHandler()
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(org.eclipse.jetty.servlet.DefaultServlet.class, "/");
        context.addFilter(AppFilter.class, "/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        server.setHandler(context);
        server.start();
        server.join();
    }
}
