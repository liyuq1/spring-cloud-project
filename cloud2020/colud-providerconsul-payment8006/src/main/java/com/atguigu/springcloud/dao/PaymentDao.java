package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * (Payment)表数据库访问层
 *
 * @author liyu
 * @since 2020-11-03 10:04:25
 */
@Mapper
@Component
public interface PaymentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Payment queryById(Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param entity 实例对象
     * @return 对象列表
     */
    List<Payment> selectList(Payment entity);

    /**
     * 新增数据
     *
     * @param entity 实例对象
     * @return 影响行数
     */
    int insert(Payment entity);

    /**
     * 新增指定列
     *
     * @param entity 实例对象
     * @return 影响行数
     */
    int insertSelective(Payment entity);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PaymentEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Payment> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Payment> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Payment> entities);

    /**
     * 修改数据
     *
     * @param entity 实例对象
     * @return 影响行数
     */
    int update(Payment entity);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}