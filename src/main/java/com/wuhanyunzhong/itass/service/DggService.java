package com.wuhanyunzhong.itass.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DggService {

    public Integer test();

    public Map setest();

    public Integer insertDepart(List partList);
}
