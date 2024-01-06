package com.p.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.p.order.mapper.StockNumberMapper;
import com.p.order.model.StockNumber;
import com.p.order.service.StockNumberService;
import org.springframework.stereotype.Service;

@Service
public class StockNumberServiceImpl extends ServiceImpl<StockNumberMapper, StockNumber> implements StockNumberService {
}
