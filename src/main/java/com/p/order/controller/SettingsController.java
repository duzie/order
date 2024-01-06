package com.p.order.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.p.order.model.Conf;
import com.p.order.model.Print;
import com.p.order.model.StockNumber;
import com.p.order.service.ConfService;
import com.p.order.service.StockNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SettingsController {

    @Autowired
    private StockNumberService stockNumberService;

    @Autowired
    private ConfService confService;

    @GetMapping("settings/list")
    @ResponseBody
    public List<StockNumber> list() {
        return stockNumberService.list();
    }

    @GetMapping("settings")
    public String settings(Model model) {
        List<StockNumber> list = stockNumberService.list();
        model.addAttribute("list", list);
        return "settings";
    }

    @GetMapping("print-settings")
    public String printSettings(Model model) {
        Print print = confService.getPrint();
        model.addAttribute("print", print);
        return "print-settings";
    }

    @PostMapping("print-settings/save")
    @ResponseBody
    public Map<String, String> printSettingSave(@RequestBody List<Conf> list) {
        confService.remove(Wrappers.emptyWrapper());
        confService.saveBatch(list);
        Map<String, String> map = new HashMap<>();
        map.put("success", "ok");
        return map;
    }


}
