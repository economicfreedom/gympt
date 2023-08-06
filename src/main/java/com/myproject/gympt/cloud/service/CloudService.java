package com.myproject.gympt.cloud.service;


import com.myproject.gympt.cloud.db.*;
import com.myproject.gympt.cloud.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CloudService {
    private final ItCloudRepository itCloudRepository;
    private final PoliticsCloudRepository politicsCloudRepository;
    private final EconomyCloudRepository economyCloudRepository;
    private final SocietyCloudRepository societyCloudRepository;

    private final SocCloudConverter socCloudConverter;
    private final EcoCloudConverter ecoCloudConverter;
    private final PoliConverter policonverter;
    private final ItCloudConverter itConverter;
    private final ReqAndResRepository reqAndResRepository;
    private final ReqAndResConverter reqAndResConverter;
    public Map<String,Object>getWords(){


        PoliticsCloudEntity politicsCloudEntity = politicsCloudRepository.findFirstByOrderByIdDesc().get();
        ItCloudEntity itCloudEntity = itCloudRepository.findFirstByOrderByIdDesc().get();
        EconomyCloudEntity economyCloudEntity = economyCloudRepository.findFirstByOrderByIdDesc().get();
        SocietyCloudEntity societyCloudEntity = societyCloudRepository.findFirstByOrderByIdDesc().get();

        PoliCloudDTO poliCloudDTO = policonverter.toDto(politicsCloudEntity);
        SocCloudDTO socCloudDTO = socCloudConverter.toDto(societyCloudEntity);
        EcoCloudDTO ecoCloudDTO = ecoCloudConverter.toDto(economyCloudEntity);

        ItCloudDTO itCloudDTO = itConverter.toDto(itCloudEntity);

        List<Object> list = new ArrayList<>();

        list.add(poliCloudDTO);
        list.add(socCloudDTO);
        list.add(ecoCloudDTO);
        list.add(itCloudDTO);
        Map<String,Object> map = new HashMap<>();

        map.put("poli",poliCloudDTO);
        map.put("soc",socCloudDTO);
        map.put("eco",ecoCloudDTO);
        map.put("it",itCloudDTO);




        return map;

    }

    public ReqAndResDTO getEcoReqAndReq(){
        RequestAndResponseEntity eoc = reqAndResRepository.findByNewsTypeAndMaxCloudId("eco").get();


        return reqAndResConverter.toDto(eoc);
    }
    public ReqAndResDTO getSocReqAndReq(){
        RequestAndResponseEntity eoc = reqAndResRepository.findByNewsTypeAndMaxCloudId("nav").get();


        return reqAndResConverter.toDto(eoc);
    }
    public ReqAndResDTO getItReqAndReq(){
        RequestAndResponseEntity eoc = reqAndResRepository.findByNewsTypeAndMaxCloudId("sci").get();


        return reqAndResConverter.toDto(eoc);
    }
    public ReqAndResDTO getPoliReqAndReq(){
        RequestAndResponseEntity eoc = reqAndResRepository.findByNewsTypeAndMaxCloudId("pol").get();


        return reqAndResConverter.toDto(eoc);
    }

}
