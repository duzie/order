package com.p.order.controller;

import com.p.order.model.ItemDetail;
import com.p.order.model.Sn;
import com.p.order.model.StockNumber;
import com.p.order.service.ConfService;
import com.p.order.service.ItemDetailService;
import com.p.order.service.StockNumberService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/item-detail")
public class ItemDetailController {

    @Autowired
    private ItemDetailService itemDetailService;

    @Autowired
    private StockNumberService stockNumberService;
    private final static String titles[] = new String[]{"Carton Number", "PART NO", "Model", "Color", "Capacity", "IMEI Number", "SN Number", "Date"};

    @Autowired
    private ConfService confService;

    @GetMapping("/print/{batchNo}/{type}/{page}")
    public String print(@PathVariable String batchNo, @PathVariable String type, @PathVariable Integer page, Model model) {
        model.addAttribute("page", page);
        model.addAttribute("print", confService.getPrint());
        List<ItemDetail> detailList = itemDetailService.lambdaQuery()
                .eq(ItemDetail::getBatchNo, batchNo)
                .list();
        if ("test".equals(batchNo)) {
            testData(detailList);
        }
        Sn sn = new Sn(detailList);
        model.addAttribute("sn", sn);
        model.addAttribute("table2", detailList.size() > 20);
        List<StockNumber> stockNumberList = stockNumberService.list();
        for (ItemDetail itemDetail : detailList) {
            stockNumberList.stream().filter(s -> s.getSkuNumber().equals(itemDetail.getProductNo()))
                    .findFirst()
                    .ifPresent(s -> {
                        itemDetail.setColor(s.getColor());
                        itemDetail.setCapacity(s.getCapacity());
                        itemDetail.setModel(s.getModel());
                    });
        }
        Map<String, List<ItemDetail>> labelMap = detailList.stream().collect(Collectors.groupingBy(ItemDetail::getLabel));
        List<ItemDetail> list = new ArrayList<>();
        for (String s : labelMap.keySet()) {
            ItemDetail itemDetail = new ItemDetail();
            ItemDetail dt = labelMap.get(s).get(0);
            itemDetail.setModel(dt.getModel());
            itemDetail.setColor(dt.getColor());
            itemDetail.setCapacity(dt.getCapacity());
            itemDetail.setQty(labelMap.get(s).size());
            list.add(itemDetail);
        }
        int size = list.size();
        if (size < 10) {
            for (int i = 0; i < 10 - size; i++) {
                ItemDetail itemDetail = new ItemDetail();
                list.add(itemDetail);
            }
        }
        model.addAttribute("list", list);
        model.addAttribute("quantity", detailList.get(0).getBoxTotal());
        model.addAttribute("cartonno", detailList.get(0).getCartoonNo());
        return "item-print" + type;
    }

    private void testData(List<ItemDetail> detailList) {
        String[] sns = {"C7CCF5A3N72R", "G0NF91S8N72R", "DNPZ988WN72R", "DNPZQ5ZPN72R", "F4GG330GN72R",
                "F4GD4404N72R", "GXGGC00EN72R", "DNPCW2LWN72R", "DX3CVWQQN73F", "DNPZQNWMN72R", "FFWFW0H2N72R",
                "DX3DC7VWN72R", "G0NZQ101N72R", "C6KZ8G1JN72R", "G0NCT0DMN72R", "G0NZPACPN72R", "DNPZK1B8N72R",
                "GXGGW0B8N72R", "DX3HD4LMN72R", "FK1ZP2M1N72R", "DX3HP0XMN72R", "DX3GD1XNN73F", "DNPZF17MN72W", "G0NZQ2RZN72X", "GXHG3024N72V"};
        for (String sn : sns) {
            ItemDetail itemDetail = new ItemDetail();
            itemDetail.setSn(sn);
            itemDetail.setQty(1);
            itemDetail.setModel("Apple iPhone X");
            itemDetail.setColor("red");
            itemDetail.setCapacity("64GB");
            itemDetail.setBoxTotal("213");
            itemDetail.setCartoonNo("213");
            detailList.add(itemDetail);
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public Map<String, String> save(@RequestBody List<ItemDetail> list) {
        list = list.stream().filter(l -> !l.getProductNo().equals("待扫描")).collect(Collectors.toList());
        if (list.size() == 0) {
            return null;
        }
        List<StockNumber> stockNumberList = stockNumberService.list();
        String batchNo = UUID.randomUUID().toString();
        for (ItemDetail itemDetail : list) {
            itemDetail.setBatchNo(batchNo);
            itemDetail.setId(UUID.randomUUID().toString());
            itemDetail.setCreateDate(new Date());
            stockNumberList.stream().filter(s -> s.getSkuNumber().equals(itemDetail.getProductNo()))
                    .findFirst()
                    .ifPresent(s -> {
                        itemDetail.setColor(s.getColor());
                        itemDetail.setCapacity(s.getCapacity());
                        itemDetail.setModel(s.getModel());
                    });
            itemDetailService.lambdaUpdate()
                    .eq(ItemDetail::getOrderNo, itemDetail.getOrderNo())
                    .eq(ItemDetail::getCartoonNo, itemDetail.getCartoonNo())
                    .eq(ItemDetail::getBoxTotal, itemDetail.getBoxTotal())
                    .eq(ItemDetail::getProductNo, itemDetail.getProductNo())
                    .eq(ItemDetail::getImei, itemDetail.getImei())
                    .eq(ItemDetail::getSn, itemDetail.getSn())
                    .remove();
        }

        itemDetailService.saveBatch(list);
        Map<String, String> map = new HashMap<>();
        map.put("batchNo", batchNo);
        return map;
    }

    @PostMapping("/list")
    @ResponseBody
    public List<ItemDetail> list(@RequestBody ItemDetail itemDetail) {
        return itemDetailService.lambdaQuery()
                .eq(ItemDetail::getOrderNo, itemDetail.getOrderNo())
                .eq(ItemDetail::getCartoonNo, itemDetail.getCartoonNo())
                .eq(ItemDetail::getBoxTotal, itemDetail.getBoxTotal())
                .list();
    }

    @GetMapping("/export/{orderNo}")
    public void export(@PathVariable String orderNo, HttpServletResponse response) throws IOException {
        List<ItemDetail> list = itemDetailService.lambdaQuery()
                .eq(ItemDetail::getOrderNo, orderNo)
                .list();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        {
            Row row = sheet.createRow(0);
            for (int i = 0; i < titles.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(titles[i]);
            }
        }
        {
            int line = 1;
            for (ItemDetail itemDetail : list) {
                Row row = sheet.createRow(line++);
                int i = 0;
                {
                    Cell cell = row.createCell(i++);
                    cell.setCellValue(itemDetail.getCartoonNo());
                }
                {
                    Cell cell = row.createCell(i++);
                    cell.setCellValue(itemDetail.getProductNo());
                }
                {
                    Cell cell = row.createCell(i++);
                    cell.setCellValue(itemDetail.getModel());
                }
                {
                    Cell cell = row.createCell(i++);
                    cell.setCellValue(itemDetail.getColor());
                }
                {
                    Cell cell = row.createCell(i++);
                    cell.setCellValue(itemDetail.getCapacity());
                }
                {
                    Cell cell = row.createCell(i++);
                    cell.setCellValue(itemDetail.getImei());
                }
                {
                    Cell cell = row.createCell(i++);
                    cell.setCellValue(itemDetail.getSn());
                }
                {
                    Cell cell = row.createCell(i++);
                    cell.setCellValue(DateFormatUtils.format(itemDetail.getCreateDate(), "yyyy/MM/dd"));
                }

            }
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=".concat(orderNo + ".xlsx"));
        workbook.write(response.getOutputStream());
    }

}
