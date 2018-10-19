package com.github.jccode.esdemo.service;

import com.github.jccode.esdemo.dto.RateCalcQueryDto;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RateService
 *
 * @author 01372461
 */
@Service
public class RateCalcService {

    @Autowired
    private RestHighLevelClient client;

    public void getTopScoreRate(RateCalcQueryDto query) {

    }
}
