package com.desafio.principal;

import com.desafio.apiclient.IntercambioService;
import com.desafio.modelos.Divisa;
import com.desafio.modelos.ExchangeUnitsRecord;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class ForeignExhangeApplication {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int opcionDivisaOrigen = 0;
        int opcionDivisaDestino = 0;
        int banderaSalidaDivisaOrigen = 0;
        int banderaSalidaDivisaDestino = 0;
        int contadorDivisas = 0;
        double valorOrigen = 0;

        try {

            //creando una lista de conversiones a crear
            List<Divisa> currenciesListAvailable = Arrays.asList(new Divisa("USD", "Dolar"),
                    new Divisa("EUR", "Euro"),
                    new Divisa("ARS", "Peso Argentino"),
                    new Divisa("COP", "Peso Colombiano"),
                    new Divisa("CUP", "Peso Cubano"));

            do {
                contadorDivisas = 0;

                System.out.printf("\n");
                System.out.println("""
                        ***************************************
                        ***  Bienvenido a ExchangeConverter ***
                        ***************************************
                                            
                        Seleccione la Divisa Origen: """);

                System.out.printf("\n");

                for (Divisa divisa : currenciesListAvailable) {
                    System.out.println((++contadorDivisas) + ") " + divisa.getName());
                }

                System.out.println((++contadorDivisas) + ") Salir");
                banderaSalidaDivisaOrigen = contadorDivisas;

                System.out.printf("\n");
                System.out.print("Escoja una de las divisas mostradas: ");
                opcionDivisaOrigen = entrada.nextInt();

                if (opcionDivisaOrigen < banderaSalidaDivisaOrigen) {
                    System.out.printf("\n");
                    System.out.println("Ha seleccionado la divida (" + currenciesListAvailable.get(opcionDivisaOrigen - 1).getName() + ") como origen.");
                    Thread.sleep(2000);

                    do {
                        contadorDivisas = 0;
                        System.out.printf("\n");
                        System.out.println("Seleccione la Divisa Destino: ");

                        System.out.printf("\n");

                        for (Divisa divisa : currenciesListAvailable) {
                            if (divisa.getCode().equals(currenciesListAvailable.get(opcionDivisaOrigen - 1).getCode())) {
                                System.out.println((++contadorDivisas) + ") " + divisa.getName() + " [Divisa Origen]");
                            } else {
                                System.out.println((++contadorDivisas) + ") " + divisa.getName());
                            }
                        }

                        System.out.println((++contadorDivisas) + ") Salir");
                        banderaSalidaDivisaDestino = contadorDivisas;

                        System.out.printf("\n");

                        System.out.print("Escoja una de las divisas mostradas: ");
                        opcionDivisaDestino = entrada.nextInt();

                        if (opcionDivisaOrigen == opcionDivisaDestino) {

                            System.out.printf("\n");
                            System.out.println("No puede selecionar la misma divisa como Origen y Destino. Seleccione otra divisa destino.");
                            Thread.sleep(2000);

                        } else if(opcionDivisaDestino > banderaSalidaDivisaDestino){

                            System.out.printf("\n");
                            System.out.println("La opcion seleccionada no es correcta. Intentelo nuevamente");
                            Thread.sleep(2000);

                        } else if (opcionDivisaDestino != banderaSalidaDivisaDestino) {

                            System.out.printf("\n");
                            System.out.println("Ha seleccionado la divida (" + currenciesListAvailable.get(opcionDivisaDestino - 1).getName() + ") como destino.");
                            Thread.sleep(2000);

                            String divisaOrigen = currenciesListAvailable.get(opcionDivisaOrigen- 1).getCode();
                            String divisaDestino = currenciesListAvailable.get(opcionDivisaDestino - 1).getCode();

                            do{
                                System.out.printf("\n");
                                System.out.println("***************************************");
                                System.out.println("Par de divisas seleccionadas ["+divisaOrigen+"] -> ["+divisaDestino+"].");
                                System.out.print("Ingrese el valor que desea convertir (Ingrese -1 para regresar al menu inicial): ");
                                valorOrigen = entrada.nextDouble();

                                if(valorOrigen > 0) {
                                    double tasaDeCambio = IntercambioService.obtenerTasaCambio(divisaOrigen, divisaDestino);

                                    //se realiza el calculo
                                    double valorFinal = valorOrigen * tasaDeCambio;

                                    System.out.printf("\n");
                                    System.out.printf("%.2f[%s] equivale a %.2f[%s]", valorOrigen, divisaOrigen, valorFinal, divisaDestino);
                                    System.out.printf("\n");

                                    valorOrigen = -1;

                                    Thread.sleep(2000);

                                } else if (valorOrigen != -1) {
                                    System.out.printf("\n");
                                    System.out.println("Debe ingresar una cantidad mayor a cero");

                                    Thread.sleep(2000);
                                }

                            }while( valorOrigen != -1);

                            opcionDivisaDestino = banderaSalidaDivisaDestino;
                        }

                    } while (opcionDivisaDestino != banderaSalidaDivisaDestino);

                } else if(opcionDivisaOrigen > banderaSalidaDivisaOrigen){

                    System.out.printf("\n");
                    System.out.println("La opcion seleccionada no es correcta. Intentelo nuevamente");
                    Thread.sleep(2000);

                }

            } while (opcionDivisaOrigen != banderaSalidaDivisaOrigen);
        } catch (InputMismatchException e) {
            System.out.printf("\n");
            System.out.println("Error en la información proporcionada");
        } catch (InterruptedException e) {
            System.out.printf("\n");
            System.out.println("Se ha presentado un error General: " + e.getMessage());
        } catch (Exception w){
            System.out.printf("\n");
            System.out.println("Se ha presentado un problema: " + w.getMessage());
        }

    }
}
