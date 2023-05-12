package LR7;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.File;

@Path("/tour")
public class TourService {
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response sendXmlDocument() {
        File file = new File("D:\\Users\\Vitalya\\Desktop\\Studing\\РИС\\LR7\\src\\main\\resources\\tour.xml");
        return Response.ok(file).build();
    }
}