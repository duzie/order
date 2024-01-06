package com.p.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.p.order.mapper.ConfMapper;
import com.p.order.model.Conf;
import com.p.order.model.Print;
import com.p.order.service.ConfService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfServiceImpl extends ServiceImpl<ConfMapper, Conf> implements ConfService {
    @Override
    public Print getPrint() {
        Print print = new Print();
        List<Conf> list = this.lambdaQuery().list();
        if (list.size() == 0) {
            return print;
        }
        for (Conf conf : list) {
            if ("sn".equals(conf.getItem())) {
                print.setSn(conf.getValue());
            }
            if ("barcodeHeight".equals(conf.getItem())) {
                print.setBarcodeHeight(conf.getValue());
            }
            if ("barcodeWidth".equals(conf.getItem())) {
                print.setBarcodeWidth(conf.getValue());
            }
            if ("modelWidth".equals(conf.getItem())) {
                print.setModelWidth(conf.getValue());
            }
            if ("colorWidth".equals(conf.getItem())) {
                print.setColorWidth(conf.getValue());
            }
            if ("capacityWidth".equals(conf.getItem())) {
                print.setCapacityWidth(conf.getValue());
            }
            if ("qtyWidth".equals(conf.getItem())) {
                print.setQtyWidth(conf.getValue());
            }
            if ("btmWidth".equals(conf.getItem())) {
                print.setBtmWidth(conf.getValue());
            }
            if ("lineHeight".equals(conf.getItem())) {
                print.setLineHeight(conf.getValue());
            }
            if ("fontSize".equals(conf.getItem())) {
                print.setFontSize(conf.getValue());
            }
        }
        return print;
    }
}
