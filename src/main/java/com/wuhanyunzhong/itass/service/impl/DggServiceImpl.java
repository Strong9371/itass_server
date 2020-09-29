package com.wuhanyunzhong.itass.service.impl;

import com.wuhanyunzhong.itass.mapper.DggMapper;
import com.wuhanyunzhong.itass.service.DggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@CacheConfig()
@Service
public class DggServiceImpl implements DggService {

    @Autowired
    DggMapper dggMapper;


    @Override
    public Integer test() {
        Integer test = dggMapper.test();
        return test;
    }

    @Override
    public Map setest() {
        Map setest = dggMapper.setest();
        return setest;
    }

    @Override
    public Integer insertDepart(List partList) {
        Integer integer = dggMapper.insertDepart(partList);
        return integer;
    }


}
