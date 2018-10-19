package com.github.jccode.esdemo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * RateCalcQueryDto
 *
 * 标志:
 * - 是否必传: 必传(R), 非必传(O)
 * - 过滤条件(F) / 优先级条件(C)
 * - 优先级顺序为Ci(i越小,优先级越大.即:C1 > C2 > C3 > ...)
 *
 * @author 01372461
 */
@Data
public class RateCalcQueryDto implements Serializable {

    /**
     * 日期:查询该天的运价 (R,F)
     */
    private String date;

    /**
     * 适用承运人航司列表 (R,F)
     *
     */
    private String suitCarrierList;

    /**
     * 起运机场列表 (R,F)
     *
     */
    private String originAirportList;

    /**
     * 到达站机场列表 (R,F)
     *
     */
    private String destAirportList;

    /**
     * 适用产品类/商品类列表 (R,F)
     *
     */
    private String suitProductList;

    /**
     * 运价文件号 (O,F)
     *
     */
    private String rateDocNo;

    /**
     * 适用销售区域/代理人/柜台范围列表 (R,C1)
     *
     */
    private String suitAgtList;

    /**
     * 中转机场点列表 (R,C2)
     *
     */
    private String transfersPointList;


    /**
     * 适用航班列表 (R,C3)
     *
     */
    private String suitFlightList;


    /**
     * 商品重量 (R)
     */
    private double weight;
}
