package com.myproject.gympt.cloud.service;

import com.myproject.gympt.Converter;
import com.myproject.gympt.cloud.db.EconomyCloudEntity;
import com.myproject.gympt.cloud.db.PoliticsCloudEntity;
import com.myproject.gympt.cloud.model.EcoCloudDTO;
import com.myproject.gympt.cloud.model.PoliCloudDTO;
import org.springframework.stereotype.Service;


@Service
public class PoliConverter implements Converter<PoliCloudDTO, PoliticsCloudEntity> {
    @Override
    public PoliCloudDTO toDto(PoliticsCloudEntity politicsCloudEntity) {
        return PoliCloudDTO.builder()
                .id(politicsCloudEntity.getId())
                .createAt(politicsCloudEntity.getCreateAt())
                .content1(politicsCloudEntity.getContent1())
                .content2(politicsCloudEntity.getContent2())
                .content3(politicsCloudEntity.getContent3())
                .content4(politicsCloudEntity.getContent4())
                .content5(politicsCloudEntity.getContent5())
                .content6(politicsCloudEntity.getContent6())
                .content7(politicsCloudEntity.getContent7())
                .content8(politicsCloudEntity.getContent8())
                .content9(politicsCloudEntity.getContent9())
                .content10(politicsCloudEntity.getContent10())
                .build();
    }

    @Override
    public PoliticsCloudEntity toEntity(PoliCloudDTO ecoCloudDTO) {
        return null;
    }
}
