/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.setups;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 *
 * @author rizzoli
 */
@Slf4j
@Configuration
public class GlobalPostFiltering {

    
    /**
     * Un filtro post se ejecuta después de que se haya recibido la respuesta del servicio de destino. 
     * Puedes usarlo para realizar acciones en la respuesta antes de enviarla al cliente.
     * @return 
     */
    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        log.info("Global Post Filter executed");
                    }));
        };
    }

}
