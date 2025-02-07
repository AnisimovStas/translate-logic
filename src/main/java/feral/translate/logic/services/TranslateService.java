package feral.translate.logic.services;

import feral.translate.logic.client.TranslateEngineClient;
import feral.translate.logic.domain.Translate;
import feral.translate.logic.domain.TranslateDTO;
import feral.translate.logic.domain.TranslateResponse;
import feral.translate.logic.repositories.TranslateRepository;
import feral.translate.logic.repositories.UserTranslatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TranslateService {
    private final TranslateRepository translateRepository;
    private final TranslateEngineClient translateEngineClient;
    private final UserTranslatesRepository userTranslatesRepository;

    @Transactional
    public Translate translate(TranslateDTO dto) {
        //1. смотрим, есть ли слово в таблице
        //2. если нет, то делаем запрос и добавляем в таблицу
        //3. если есть, то записываем ее в сущность перевода
        //4. добавляем/обновляем слово у пользователя
        //5. отдаем сущность translate

        Optional<Translate> translateOpt = translateRepository.findByOrigin(dto.getOrigin());
        Translate translate = translateOpt.orElse(null);
        if (translate == null) {
            String translatedText = translateEngineClient.translate(dto.getOrigin());
            translate = Translate.builder()
                .origin(dto.getOrigin())
                .translate(translatedText)
                .build();
            translateRepository.save(translate);
        }

        userTranslatesRepository.saveOrUpdate(dto.getUserId(), translate.getId());
        return translate;
    }

    public Page<TranslateResponse> search(Long userId, Pageable pageable) {
        return translateRepository.searchByUserId(userId, pageable);
    }
}
