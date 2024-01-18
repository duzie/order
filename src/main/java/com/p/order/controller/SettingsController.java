package com.p.order.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.p.order.model.*;
import com.p.order.service.ConfService;
import com.p.order.service.StockNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SettingsController {

    @Autowired
    private StockNumberService stockNumberService;

    @Autowired
    private ConfService confService;

    @GetMapping("settings/list")
    @ResponseBody
    public Map<String, Object> list() {
        List<StockNumber> list = stockNumberService.list();
        List<String> modelList = list.stream().map(StockNumber::getModel).distinct().collect(Collectors.toList());
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("modelList", modelList);
        return map;
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

    private Packing packing;

    @PostMapping("/packing/save")
    @ResponseBody
    public Map<String, String> packingSave(@RequestBody Packing packing) {
        this.packing = packing;
        Map<String, String> map = new HashMap<>();
        map.put("success", "ok");
        return map;
    }

    @GetMapping("/packing/print")
    public String packingPrint(Model model, boolean test) {
        if (test) {
            this.packing = testPacking();
        }
        List<List<PackItem>> list = packing.calc();
        for (List<PackItem> packItems : list) {
            for (PackItem packItem : packItems) {
                String a = packItem.getA();
                long count = packItems.stream().filter(p -> p.getA().equals(a)).count();
                packItem.setRowspan(count);
            }
            for (int i = 1; i < packItems.size(); i++) {
                if (packItems.get(i).getA().equals(packItems.get(i - 1).getA())) {
                    packItems.get(i).setPrevSame(true);
                }
            }
        }
        model.addAttribute("list", list);
        model.addAttribute("data", packing);
        return "packing-list-print";
    }

    private Packing testPacking() {
        Packing pk = new Packing();
        pk.setLookUp("Room 05, 10/F, Comweb Plaza, 12 Cheung Yue Street, Cheung Sha Wan, Kowloon, Hong Kong");
        pk.setOther("Tel: (852) 2439 9010 Fax: (852) 2439 6462");
        pk.setPlNo("PL-230101");
        pk.setDate("2023/11/10");
        pk.setCompany("Onyx Trade SRL");
        pk.setAddress1("Via C Rosaroll 77/e");
        pk.setAddress2("80139 Napoli (NA),Itlay");
        pk.setAddress3("test");
        pk.setContact("Mr Roberto Prisco");
        pk.setTel("+39 123456");

        pk.setDcompany("Lenca Logistics B.V. ");
        pk.setDcompany1("C/O :Onyx Trade SRL");
        pk.setDaddress1("Kopraweg 3, 1047 BP Amsterdam");
        pk.setDaddress2("The Netherlands");
        pk.setDaddress3("test");
        pk.setDcontact("Mr. Murat Saygili");
        pk.setDtel("+31 123456");

        pk.setRemark1("Mobile Phone: PI967-Secton II");
        pk.setRemark2("HS Code: 85171200");
        pk.setRemark3("test");

        List<PackItem> list = new ArrayList<>();
        list.add(new PackItem("1", "CPO-AX256SA+", "Apple iPhone X 256GB Silver (Used)A+", "6", "10", "13", "43*35*29"));
        list.add(new PackItem("1", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "34", "10", "13", "43*35*29"));
        list.add(new PackItem("2", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "10", "10", "13", "43*35*29"));
        list.add(new PackItem("2", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "10", "10", "13", "43*35*29"));
        list.add(new PackItem("2", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "10", "10", "13", "43*35*29"));
        list.add(new PackItem("2", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "10", "10", "13", "43*35*29"));
        list.add(new PackItem("2", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "9", "10", "13", "43*35*29"));
        list.add(new PackItem("3", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("3", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "10", "10", "13", "43*35*29"));
        list.add(new PackItem("3", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "10", "10", "13", "43*35*29"));
        list.add(new PackItem("4", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "9", "10", "13", "43*35*29"));
        list.add(new PackItem("4", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "9", "10", "13", "43*35*29"));
        list.add(new PackItem("4", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "9", "10", "13", "43*35*29"));
        list.add(new PackItem("4", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "9", "10", "13", "43*35*29"));
        list.add(new PackItem("4", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("4", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "10", "10", "13", "43*35*29"));
        list.add(new PackItem("4", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "10", "10", "13", "43*35*29"));
        list.add(new PackItem("4", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "9", "10", "13", "43*35*29"));
        list.add(new PackItem("5", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("6", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("6", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("6", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("7", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("7", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("7", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("7", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("8", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("8", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("9", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("10", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("10", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("11", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("11", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        list.add(new PackItem("11", "CPO-AX256SGA+", "Apple iPhone X 256GB Space Gray (Used)A+", "1", "10", "13", "43*35*29"));
        pk.setList(list);
        return pk;
    }

}
