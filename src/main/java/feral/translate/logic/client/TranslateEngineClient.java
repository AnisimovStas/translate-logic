package feral.translate.logic.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${services.translate.url}", name = "TranslateClient")
public interface TranslateEngineClient {

    @GetMapping("/api/v1/translate")
    String translate(
        @RequestParam("text") String text

    );
}
