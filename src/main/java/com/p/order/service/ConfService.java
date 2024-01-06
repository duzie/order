package com.p.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.p.order.model.Conf;
import com.p.order.model.Print;

public interface ConfService extends IService<Conf> {

    Print getPrint();
}
