package com.myproject.gympt.cloud.service;

import com.myproject.gympt.Converter;
import com.myproject.gympt.cloud.db.SocietyCloudEntity;
import com.myproject.gympt.cloud.model.SocCloudDTO;
import org.springframework.stereotype.Service;

@Service
public class SocCloudConverter implements Converter<SocCloudDTO, SocietyCloudEntity> {
    @Override
    public SocCloudDTO toDto(SocietyCloudEntity societyCloudEntity) {
        return SocCloudDTO.builder()
                .id(societyCloudEntity.getId())
                .createAt(societyCloudEntity.getCreateAt())
                .content1(societyCloudEntity.getContent1())
                .content2(societyCloudEntity.getContent2())
                .content3(societyCloudEntity.getContent3())
                .content4(societyCloudEntity.getContent4())
                .content5(societyCloudEntity.getContent5())
                .content6(societyCloudEntity.getContent6())
                .content7(societyCloudEntity.getContent7())
                .content8(societyCloudEntity.getContent8())
                .content9(societyCloudEntity.getContent9())
                .content10(societyCloudEntity.getContent10())
                .build();
    }

    @Override
    public SocietyCloudEntity toEntity(SocCloudDTO socCloudDTO) {
        return null;
    }
}
