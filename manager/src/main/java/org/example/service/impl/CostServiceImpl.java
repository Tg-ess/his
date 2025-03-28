package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mapper.CostMapper;
import org.example.pojo.Cost;
import org.example.service.ICostService;
import org.springframework.stereotype.Service;

@Service
public class CostServiceImpl extends ServiceImpl<CostMapper, Cost> implements ICostService {
}
