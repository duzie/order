package com.p.order.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Packing {

    private String plNo = "";
    private String date = "";
    private String remark1 = "";
    private String remark2 = "";
    private String remark3 = "";
    private String lookUp = "";
    private String other = "";
    private String company = "";
    private String code = "";
    private String address1 = "";
    private String address2 = "";
    private String address3 = "";
    private String contact = "";
    private String tel = "";
    private String dcompany = "";
    private String dcompany1 = "";
    private String daddress1 = "";
    private String daddress2 = "";
    private String daddress3 = "";
    private String dcontact = "";
    private String dtel = "";
    private List<PackItem> list;

    private String totalCartons;
    private String totalQty;
    private String totalNw;
    private String totalGw;
    private String totalSize;

    public List<List<PackItem>> calc() {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }
        totalCartons = list.stream().collect(Collectors.groupingBy(PackItem::getA)).keySet().size() + "";
        totalQty = list.stream().mapToInt(d -> Integer.parseInt(d.getD())).sum() + "";
        Set<String> cartonSet = list.stream().collect(Collectors.groupingBy(PackItem::getA)).keySet();
        List<PackItem> groupList = new ArrayList<>();
        for (String carton : cartonSet) {
            list.stream().filter(p -> p.getA().equals(carton)).findFirst()
                    .ifPresent(groupList::add);
        }
        double sumNw = groupList.stream().mapToDouble(g -> Double.parseDouble(g.getE())).sum();
        totalNw = String.format("%.2f", sumNw);
        double sumGw = groupList.stream().mapToDouble(g -> Double.parseDouble(g.getF())).sum();
        totalGw = String.format("%.2f", sumGw);
        int sum = groupList.stream().mapToInt(PackItem::getSize).sum();
        BigDecimal b = new BigDecimal(sum).divide(new BigDecimal(1000000));
        totalSize = String.format("%.2f", b);
        return splitList(list, 18);

    }

    private static <T> List<List<T>> splitList(List<T> list, int size) {
        List<List<T>> dividedList = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            dividedList.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return dividedList;
    }
}
