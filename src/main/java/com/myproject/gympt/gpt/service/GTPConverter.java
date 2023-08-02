package com.myproject.gympt.gpt.service;

import com.myproject.gympt.Converter;
import com.myproject.gympt.gpt.db.GPTRepository;
import com.myproject.gympt.gpt.db.GptEntity;
import com.myproject.gympt.gpt.model.GPTDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GTPConverter implements Converter<GPTDTO,GptEntity> {
    private final GPTRepository gptRepository;
    @Override
    public GPTDTO toDto(GptEntity gptEntity) {

        return GPTDTO.builder()
                .id(gptEntity.getId())
                .userId(gptEntity.getId())
                .createdAt(gptEntity.getCreatedAt())
                .request(gptEntity.getRequest())
                .response(gptEntity.getResponse())
                .type(gptEntity.getType())
                .build();

    }

    @Override
    public GptEntity toEntity(GPTDTO gptdto) {
        return gptRepository.findById(gptdto.getId()).get();


    }
}
