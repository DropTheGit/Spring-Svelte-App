package com.ll.springsvelteapp.global.initDate;

import com.ll.springsvelteapp.standard.util.ut.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Dev {
    @Bean
    ApplicationRunner initDev() {
        return args -> {
            String cmd = "npx openapi-typescript http://localhost:8090/v3/api-docs/API%20V1 -o ./frontapp/src/lib/types/api/v1/schema.d.ts";
            Ut.cmd.runAsync(cmd);
        };
    }
}