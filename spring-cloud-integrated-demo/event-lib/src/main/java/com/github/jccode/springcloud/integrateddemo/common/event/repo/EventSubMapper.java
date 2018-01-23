package com.github.jccode.springcloud.integrateddemo.common.event.repo;

import com.github.jccode.springbootsample.core.repo.CrudMapper;
import com.github.jccode.springcloud.integrateddemo.common.event.model.EventSub;
import com.github.jccode.springcloud.integrateddemo.common.event.model.EventSubCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface EventSubMapper extends com.github.jccode.springbootsample.core.repo.CrudMapper<EventSub> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    long countByExample(EventSubCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    int deleteByExample(EventSubCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    int insert(EventSub record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    int insertSelective(EventSub record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    List<EventSub> selectByExampleWithRowbounds(EventSubCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    List<EventSub> selectByExample(EventSubCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    EventSub selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") EventSub record, @Param("example") EventSubCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") EventSub record, @Param("example") EventSubCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EventSub record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EVENT_SUB
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EventSub record);
}