package com.springcloud.mapper;

import com.springcloud.entities.DeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptMapper {
    /**
     * 插入
     * @param deptEntity
     *
     * @return
     */
    boolean addDept(DeptEntity deptEntity);

    /**
     * 根据id查找
     * @param deptNo
     * @return
     */
    DeptEntity findById(Long deptNo);

    /**
     * 查询全部
     * @return
     */
    List<DeptEntity> findAll();
}
