package com.delabassee.simple.service;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author davidd
 */
@Path("/book")
public class HotelResource {

    @GET
    @Path("{param}")
    @Produces("application/json")
    public String hotelResource(@PathParam("param") String param) throws InterruptedException, ExecutionException {

        Client client = ClientBuilder.newClient();

        String today = new SimpleDateFormat("dd/M/yyyy").format(new Date());
        BookingRequest bookingRequest = new BookingRequest(param, today);
        
        CompletionStage<Response> cfGetHotels
                = client.target("http://10.0.0.132:8080/r/tokyo/func1")
                        .request(MediaType.APPLICATION_JSON)
                        .rx()
                        .post(Entity.entity(bookingRequest, MediaType.APPLICATION_JSON));

        Function<Response, Hotel> getCheapestHotelFunc = resp -> {
            List<Hotel> suggestions = resp.readEntity(new GenericType<List<Hotel>>() {});
            //suggestions.forEach((suggestion) -> { System.out.println(suggestion.details()); });
            return suggestions.stream().min(Comparator.comparing(Hotel::getRate)).get();
        };

        Function<Hotel, CompletionStage<BookingConfirmation>> bookHotelFunc = proposedHotel -> {
            Hotel hotelToBook = new Hotel(bookingRequest.getCity(), proposedHotel.getDate(), proposedHotel.getHotel(), proposedHotel.getCoupon());

             CompletionStage<BookingConfirmation> conf       
                    = client.target("http://10.0.0.132:8080/r/tokyo/func2")
                            .request(MediaType.APPLICATION_JSON)
                            .rx()
                            .post(Entity.entity(hotelToBook, MediaType.APPLICATION_JSON))
                            .thenApply((t) -> {
                                BookingConfirmation confirmation = t.readEntity(BookingConfirmation.class);
                                return confirmation;
                            }).exceptionally((t) -> {
                                System.out.println("Exception " + t.toString());
                                return null;
                            });
            return conf;
        };

        BookingConfirmation confirmed = 
                cfGetHotels.thenApply(getCheapestHotelFunc)
                           .thenApply(bookHotelFunc)
                           .toCompletableFuture().get().toCompletableFuture().get();
                
        System.out.println("Cheapest hotel booked! " + confirmed.toJson());

        return confirmed.toJson();
    }

    
}

    /*
    @GET
    @Path("{param}")
    public String myResource(@PathParam("param") String param) throws InterruptedException, ExecutionException {

        Client client = ClientBuilder.newClient();

        Form form = new Form();
        form.param("", param);

        //APPLICATION_FORM_URLENCODED)
        //WebTarget target = client.target("http://127.0.0.1:8080/r/tokyo/func1");
        CompletionStage<Response> cfA = client.target("http://127.0.0.1:8080/r/tokyo/func1")
                .request(MediaType.TEXT_PLAIN)
                .accept(MediaType.TEXT_PLAIN)
                .rx()
                .post(Entity.form(form));

        Function<Response, CompletionStage<Response>> function = (t) -> {
            String entity = t.readEntity(String.class);
            System.out.println("--> " + entity);
            form.param("", entity);

            CompletionStage<Response> response = client.target("http://127.0.0.1:8080/r/tokyo/func2")
                    .request(MediaType.TEXT_PLAIN)
                    .accept(MediaType.TEXT_PLAIN)
                    .rx()
                    .post(Entity.form(form));

            return response;
        };

        String result = cfA.thenCompose(function).exceptionally((t) -> {
            String error =  "--> Exception: " + t.getMessage();
            return Response.serverError().build();
        }).toCompletableFuture().get().readEntity(String.class);                
                        
                        /*
        CompletionStage<String> cfA = client.target("http://10.0.0.132:9001/aaa/test")
                .path(param)
                .request()
                .rx()
                .get(String.class);

        Function<String, CompletionStage<String>> function = (cfB) -> {
            return client.target("http://10.0.0.132:9002/bbb/test/")
                    .path(cfB)
                    .request()
                    .rx()
                    .get(String.class);
        };

        String result = cfA.thenCompose(function)
                        .exceptionally((t) -> {
                            return "--> Exception in the RX invocations! " + t.getMessage();
                        })
                        .toCompletableFuture().get();

 //       return result;
*/