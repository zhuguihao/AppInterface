package com.gubang.dao;

import com.gubang.entity.ProductListInfo;

public interface ProductListInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_list
     *
     * @mbg.generated Sun Dec 10 14:18:31 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_list
     *
     * @mbg.generated Sun Dec 10 14:18:31 CST 2017
     */
    int insert(ProductListInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_list
     *
     * @mbg.generated Sun Dec 10 14:18:31 CST 2017
     */
    int insertSelective(ProductListInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_list
     *
     * @mbg.generated Sun Dec 10 14:18:31 CST 2017
     */
    ProductListInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_list
     *
     * @mbg.generated Sun Dec 10 14:18:31 CST 2017
     */
    int updateByPrimaryKeySelective(ProductListInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_list
     *
     * @mbg.generated Sun Dec 10 14:18:31 CST 2017
     */
    int updateByPrimaryKey(ProductListInfo record);
}