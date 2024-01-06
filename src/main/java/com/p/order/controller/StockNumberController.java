package com.p.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.p.order.model.StockNumber;
import com.p.order.service.StockNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class StockNumberController {

    @Autowired
    private StockNumberService stockNumberService;

    @GetMapping("/stock-number/all")
    public List<StockNumber> all() {
        return stockNumberService.list();
    }

    @PostMapping("/stock-number/list")
    public Page<StockNumber> list(@RequestBody Page<StockNumber> page) {
        return stockNumberService.page(page);
    }

    @PostMapping("/stock-number/save")
    public StockNumber save(@RequestBody StockNumber stockNumber) {
        String id = UUID.randomUUID().toString();
        stockNumber.setId(id);
        stockNumberService.save(stockNumber);
        return stockNumber;
    }

    @PostMapping("/stock-number/update")
    public boolean update(@RequestBody StockNumber stockNumber) {
        return stockNumberService.updateById(stockNumber);
    }

    @GetMapping("/stock-number/remove/{id}")
    public boolean remove(@PathVariable String id) {
        return stockNumberService.removeById(id);
    }

}
