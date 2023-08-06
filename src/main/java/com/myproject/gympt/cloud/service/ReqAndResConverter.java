package com.myproject.gympt.cloud.service;

import com.myproject.gympt.Converter;
import com.myproject.gympt.cloud.db.RequestAndResponseEntity;
import com.myproject.gympt.cloud.model.ReqAndResDTO;
import org.springframework.stereotype.Service;

@Service
public class ReqAndResConverter implements Converter<ReqAndResDTO,RequestAndResponseEntity> {

    @Override
    public ReqAndResDTO toDto(RequestAndResponseEntity requestAndResponseEntity) {
        return ReqAndResDTO.builder()
                .id(requestAndResponseEntity.getId())
                .createAt(requestAndResponseEntity.getCreateAt())
                .newsType(requestAndResponseEntity.getNewsType())
                .request(requestAndResponseEntity.getRequest())
                .response(requestAndResponseEntity.getResponse())

                .build();
    }

    @Override
    public RequestAndResponseEntity toEntity(ReqAndResDTO reqAndResDTO) {
        return null;
    }
}
