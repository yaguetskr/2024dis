package org.vaadin.example;



import com.nimbusds.jose.shaded.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class API {
    private static int backendPort=8080;
    private static String backendUrlPrefix="http://localhost:"+ backendPort+"/%s";
    public  Rows getFilas() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl=String.format(backendUrlPrefix,"getrows");
        fullUrl = fullUrl.replaceAll(" " ,"%20");
        HttpRequest request= HttpRequest.newBuilder().uri(new URI(fullUrl)).GET().build();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request,HttpResponse.BodyHandlers.ofString());
        // Convierte la respuesta JSON a un objeto Rows utilizando Gson.
        Gson gson = new Gson();
        Rows rows = gson.fromJson(response.body(), Rows.class);

        // Devuelve el objeto Rows.
        return rows;
    }

    public Rows getGroups() throws URISyntaxException, IOException, InterruptedException {
        String fullUrl=String.format(backendUrlPrefix,"getgroups");
        fullUrl = fullUrl.replaceAll(" " ,"%20");
        HttpRequest request= HttpRequest.newBuilder().uri(new URI(fullUrl)).GET().build();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request,HttpResponse.BodyHandlers.ofString());
        // Convierte la respuesta JSON a un objeto Rows utilizando Gson.
        Gson gson = new Gson();
        Rows rows = gson.fromJson(response.body(), Rows.class);

        // Devuelve el objeto Rows.
        return rows;
    }

    public void edit(Row fila) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(backendUrlPrefix, "edit");
        fullUrl = fullUrl.replaceAll(" ", "%20");

        // Convierte el objeto Row a JSON
        Gson gson = new Gson();
        String jsonFila = gson.toJson(fila);

        // Crea una solicitud HTTP POST con el cuerpo JSON
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonFila))
                .build();

        // Envía la solicitud y obtiene la respuesta
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        // Puedes manejar la respuesta según sea necesario
        System.out.println("Respuesta del backend: " + response.body());
    }

    public void add(Row fila) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(backendUrlPrefix, "add");
        fullUrl = fullUrl.replaceAll(" ", "%20");

        // Convierte el objeto Row a JSON
        Gson gson = new Gson();
        String jsonFila = gson.toJson(fila);

        // Crea una solicitud HTTP POST con el cuerpo JSON
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonFila))
                .build();

        // Envía la solicitud y obtiene la respuesta
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

        // Puedes manejar la respuesta según sea necesario
        System.out.println("Respuesta del backend: " + response.body());
    }

    public void delete(String id) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl=String.format(backendUrlPrefix,"delete/"+id);
        fullUrl = fullUrl.replaceAll(" " ,"%20");
        HttpRequest request= HttpRequest.newBuilder().uri(new URI(fullUrl)).GET().build();
        HttpResponse<String> response = HttpClient.newBuilder().build().send(request,HttpResponse.BodyHandlers.ofString());
        // Convierte la respuesta JSON a un objeto Rows utilizando Gson.
        System.out.println("Respuesta del backend: " + response.body());
    }

}
