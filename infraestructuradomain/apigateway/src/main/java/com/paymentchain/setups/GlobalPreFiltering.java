/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.setups;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 * @author rizzoli
 */
@Slf4j
@Component
public class GlobalPreFiltering implements GlobalFilter {

    /**
     * Un filtro pre se ejecuta antes de que se envíe la solicitud al servicio de destino. 
     * Puedes usarlo para realizar verificaciones o modificaciones en la solicitud entrante.
     * @param exchange
     * @param chain
     * @return 
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Global prefilter executed");
        return chain.filter(exchange);
    }
}
