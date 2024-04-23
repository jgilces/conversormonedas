package com.desafio.apiclient;

import com.desafio.modelos.ExchangeUnitsRecord;
import com.desafio.principal.ForeignExhangeApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public class IntercambioService {

    public static double obtenerTasaCambio(String codeFrom, String codeTo) throws Exception {

        try{
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            ForeignExhangeServiceAPI foreignExhangeServiceAPI = new ForeignExhangeServiceAPI();

            foreignExhangeServiceAPI.enviarSolicitudTasaCambio(codeFrom, codeTo);

            ExchangeUnitsRecord exchangeUnitsRecord = gson.fromJson(foreignExhangeServiceAPI.obtenerRespuestaServicio(), ExchangeUnitsRecord.class);

            return exchangeUnitsRecord.conversion_rate();
        }catch(URISyntaxException w){
            throw new Exception("Error en el formato de la URI");
        }catch(IOException | InterruptedException w){
            throw new Exception("Error al consumir el servicio: " + w.getMessage());
        }
    }
}
