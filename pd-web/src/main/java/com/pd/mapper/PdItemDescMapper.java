package com.pd.mapper;

import com.pd.pojo.PdItemDesc;
import com.pd.pojo.PdItemDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PdItemDescMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    long countByExample(PdItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    int deleteByExample(PdItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    int deleteByPrimaryKey(Long itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    int insert(PdItemDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    int insertSelective(PdItemDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    List<PdItemDesc> selectByExampleWithBLOBs(PdItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    List<PdItemDesc> selectByExample(PdItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    PdItemDesc selectByPrimaryKey(Long itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    int updateByExampleSelective(@Param("record") PdItemDesc record, @Param("example") PdItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") PdItemDesc record, @Param("example") PdItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    int updateByExample(@Param("record") PdItemDesc record, @Param("example") PdItemDescExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    int updateByPrimaryKeySelective(PdItemDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(PdItemDesc record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pd_item_desc
     *
     * @mbg.generated Thu Oct 11 11:44:42 CST 2018
     */
    int updateByPrimaryKey(PdItemDesc record);
}