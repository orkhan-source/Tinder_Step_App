package org.tinder.app;

import org.tinder.app.db.DbConnection;
import org.tinder.app.filters.StatusFilter;
import org.tinder.app.filters.RegistrationFilter;
import org.tinder.app.flyway.DbSetup;
import org.tinder.app.servlets.*;
import org.eclipse.jetty.server.Handler;
import org.tinder.app.filters.LoginFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.tinder.app.utils.ResourcesHandler;

import java.sql.Connection;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class App {


    public static void main(String[] args) throws Exception {

        DbSetup.migrate(
                "postgres://nglxiuekikrqwe:e36c325b1e86cb17efa754ba765e8e0ce5d40e6e8e339832dcc0c9c787cf0f85@ec2-44-199-22-207.compute-1.amazonaws.com:5432/ddmnm5bm93ii53",
                "nglxiuekikrqwe",
                "e36c325b1e86cb17efa754ba765e8e0ce5d40e6e8e339832dcc0c9c787cf0f85");

        Connection connection = new DbConnection().connection();

        String webPort = System.getenv("PORT");

        if(webPort == null || webPort.isEmpty()) {
            webPort = "9091";
        }

        ServletContextHandler handler = new ServletContextHandler();

        ResourcesHandler recourceshandler = new ResourcesHandler();

        ContextHandler jsHandler = recourceshandler.generateResourceHandler("src/main/resources/templates/js", "/js");

        handler.addServlet(new ServletHolder(new LoginServlet(connection)),"/login");
        handler.addServlet(new ServletHolder(new LikesServlet(connection)), "/liked");
        handler.addServlet(new ServletHolder(new MessagesServlet(connection)), "/message");

        handler.addServlet(new ServletHolder(new LoginServlet(connection)),"/login/*");
        handler.addServlet(new ServletHolder(new RegistrationServlet(connection)),"/reg/*");
        handler.addServlet(new ServletHolder(new UsersServlet(connection)),"/users/*");
        handler.addServlet(new ServletHolder(new LogoutServlet()),"/logout/*");

        handler.addFilter(StatusFilter.class,"/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        HandlerCollection handlerCollection = new HandlerCollection();

        handler.addFilter(new FilterHolder(new RegistrationFilter(connection)),"/reg/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new LoginFilter(connection)),"/login/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        handlerCollection.setHandlers(new Handler[] {jsHandler, handler});

        Server server = new Server(Integer.parseInt(webPort));

        server.setHandler(handlerCollection);

        server.start();
        server.join();

    }
}
