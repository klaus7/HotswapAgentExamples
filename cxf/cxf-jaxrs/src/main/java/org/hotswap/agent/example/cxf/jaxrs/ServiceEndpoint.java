package org.hotswap.agent.example.cxf.jaxrs;

import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/services")
public class ServiceEndpoint {

    @Inject
    private Service1 service1;

    @Inject
    private Service2 service2;

    @Inject
    private StatelessBean statelessBean;

    public ServiceEndpoint() {
        super();
    }

    @Path("/service1")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response service1(@QueryParam("echo") final String payload) {
        service2.doSomething();
        return Response.ok(payload).build();
    }

    @Path("/service2")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response service2() {
        service1.doSomething();
        return Response.ok().build();
    }

    @Path("/person")
    @POST
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public AcceptPerson action(Person person) throws Exception {
        if (person != null) {
            AcceptPerson response = new AcceptPerson();
            response.setAccepted("Done.");
            return response;
        }
        return null;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("simple")
    public Response getSimple() {
        //statistics.updateStatistic(0);
        return Response.ok(statelessBean.getHello()).build();
    }
}
