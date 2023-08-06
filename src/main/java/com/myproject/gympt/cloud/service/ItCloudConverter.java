package com.myproject.gympt.cloud.service;

import com.myproject.gympt.Converter;
import com.myproject.gympt.cloud.db.ItCloudEntity;
import com.myproject.gympt.cloud.model.ItCloudDTO;
import org.springframework.stereotype.Service;


@Service
public class ItCloudConverter implements Converter<ItCloudDTO, ItCloudEntity> {
    @Override
    public ItCloudEntity toEntity(ItCloudDTO itCloudDTO) {
        return null;
    }

    @Override
    public ItCloudDTO toDto(ItCloudEntity itCloudEntity) {

        return ItCloudDTO.builder()
                .id(itCloudEntity.getId())
                .createAt(itCloudEntity.getCreateAt())
                .content1(itCloudEntity.getContent1())
                .content2(itCloudEntity.getContent2())
                .content3(itCloudEntity.getContent3())

                .content4(itCloudEntity.getContent4())
                .content5(itCloudEntity.getContent5())
                .content6(itCloudEntity.getContent6())
                .content7(itCloudEntity.getContent7())
                .content8(itCloudEntity.getContent8())
                .content9(itCloudEntity.getContent9())
                .content10(itCloudEntity.getContent10())
                .build();




    }
}
