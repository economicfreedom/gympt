package com.myproject.gympt.cloud.service;

import com.myproject.gympt.Converter;
import com.myproject.gympt.cloud.db.EconomyCloudEntity;
import com.myproject.gympt.cloud.model.EcoCloudDTO;
import org.springframework.stereotype.Service;

@Service
public class EcoCloudConverter implements Converter<EcoCloudDTO, EconomyCloudEntity> {
    @Override
    public EcoCloudDTO toDto(EconomyCloudEntity economyCloudEntity) {
        return EcoCloudDTO.builder()
                .id(economyCloudEntity.getId())
                .createAt(economyCloudEntity.getCreateAt())
                .content1(economyCloudEntity.getContent1())
                .content2(economyCloudEntity.getContent2())
                .content3(economyCloudEntity.getContent3())
                .content4(economyCloudEntity.getContent4())
                .content5(economyCloudEntity.getContent5())
                .content6(economyCloudEntity.getContent6())
                .content7(economyCloudEntity.getContent7())
                .content8(economyCloudEntity.getContent8())
                .content9(economyCloudEntity.getContent9())
                .content10(economyCloudEntity.getContent10())
                .build();
    }

    @Override
    public EconomyCloudEntity toEntity(EcoCloudDTO ecoCloudDTO) {
        return null;
    }
}
