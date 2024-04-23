package com.desafio.modelos;

public record ExchangeUnitsRecord(String result,
                                  String base_code,
                                  String target_code,
                                  Double conversion_rate) {

}
