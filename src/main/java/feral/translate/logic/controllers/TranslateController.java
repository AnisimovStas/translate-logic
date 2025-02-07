package feral.translate.logic.controllers;

import feral.translate.logic.domain.Translate;
import feral.translate.logic.domain.TranslateDTO;
import feral.translate.logic.domain.TranslateResponse;
import feral.translate.logic.services.TranslateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/translate")
@RequiredArgsConstructor
public class TranslateController {
    private final TranslateService translateService;

    @PostMapping
    public Translate translate(@RequestBody TranslateDTO dto) {
        return translateService.translate(dto);
    }

    @PostMapping("/search")
    public Page<TranslateResponse> search(@RequestBody Long userId, Pageable pageable) {
        return translateService.search(userId, pageable);
    }

}
