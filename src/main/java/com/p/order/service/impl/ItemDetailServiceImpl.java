package com.p.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.p.order.mapper.ItemDetailMapper;
import com.p.order.model.ItemDetail;
import com.p.order.service.ItemDetailService;
import org.springframework.stereotype.Service;

@Service
public class ItemDetailServiceImpl extends ServiceImpl<ItemDetailMapper, ItemDetail> implements ItemDetailService {
}
