package com.desafio.apiclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ForeignExhangeServiceAPI {

    private HttpClient cliente;
    private HttpRequest solicitud;
    private HttpResponse<String> respuesta;

    private final String API_KEY = "33a0b0efb87f545020c902c8";
    private final String DIRECCION_BASE_API = "https://v6.exchangerate-api.com/v6/";

    public ForeignExhangeServiceAPI(){
        cliente = HttpClient.newHttpClient();
    }

    public void enviarSolicitudTasaCambio(String divisaOrigen, String divisaDestino) throws URISyntaxException, IOException, InterruptedException {

        URI direccionAPI = new URI(DIRECCION_BASE_API + API_KEY + "/pair/" + divisaOrigen + "/" + divisaDestino);

        solicitud = HttpRequest.newBuilder()
                .uri(direccionAPI)
                .GET()
                .build();

        this.respuesta = this.cliente.send(this.solicitud, HttpResponse.BodyHandlers.ofString());
    }

    public String obtenerRespuestaServicio() throws IOException, InterruptedException {
        return this.respuesta.body();
    }
}
