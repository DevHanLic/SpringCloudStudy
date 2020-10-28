package com.springcloud.service.Impl;

import com.springcloud.mapper.DeptMapper;
import com.springcloud.entities.DeptEntity;
import com.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl  implements DeptService {

    @Autowired
    DeptMapper deptMapper;

    @Override
    public boolean addDept(DeptEntity deptEntity) {
        return   deptMapper.addDept(deptEntity);
    }

    @Override
    public DeptEntity findById(Long deptNo) {
        return  deptMapper.findById(deptNo);
    }

    @Override
    public List<DeptEntity> findAll() {
        return deptMapper.findAll();
    }
}
