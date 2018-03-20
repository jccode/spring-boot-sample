package com.github.jccode.springbootrestintegrateddemo.repo;

import com.github.jccode.springbootrestintegrateddemo.model.Order;
import com.github.jccode.springbootrestintegrateddemo.model.OrderAndUser;
import com.github.jccode.springbootrestintegrateddemo.model.OrderCriteria;
import com.github.jccode.springbootrestintegrateddemo.model.OrderExt;
import com.github.jccode.springbootsample.core.repo.CrudMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OrderMapper extends com.github.jccode.springbootsample.core.repo.CrudMapper<Order> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    long countByExample(OrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    int deleteByExample(OrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    int insert(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    int insertSelective(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    List<Order> selectByExampleWithRowbounds(OrderCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    List<Order> selectByExample(OrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    Order selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Order record, @Param("example") OrderCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDERS
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Order record);



    List<OrderAndUser> findOrderAndUsers(OrderCriteria example);
    List<OrderExt> findOrderExt(OrderCriteria example);
}