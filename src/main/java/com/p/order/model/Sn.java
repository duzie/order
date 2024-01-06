package com.p.order.model;

import lombok.Data;

import java.util.List;

@Data
public class Sn {
    public String a0;
    public String a1;
    public String a2;
    public String a3;
    public String a4;
    public String a5;
    public String a6;
    public String a7;
    public String a8;
    public String a9;
    public String a10;
    public String a11;
    public String a12;
    public String a13;
    public String a14;
    public String a15;
    public String a16;
    public String a17;
    public String a18;
    public String a19;

    public String b0;
    public String b1;
    public String b2;
    public String b3;
    public String b4;
    public String b5;
    public String b6;
    public String b7;
    public String b8;
    public String b9;
    public String b10;
    public String b11;
    public String b12;
    public String b13;
    public String b14;
    public String b15;
    public String b16;
    public String b17;
    public String b18;
    public String b19;
    public String b20;
    public Sn(List<ItemDetail> detailList) {
        a0 = getValue(detailList, 0);
        a1 = getValue(detailList, 1);
        a2 = getValue(detailList, 2);
        a3 = getValue(detailList, 3);
        a4 = getValue(detailList, 4);
        a5 = getValue(detailList, 5);
        a6 = getValue(detailList, 6);
        a7 = getValue(detailList, 7);
        a8 = getValue(detailList, 8);
        a9 = getValue(detailList, 9);
        a10 = getValue(detailList, 10);
        a11 = getValue(detailList, 11);
        a12 = getValue(detailList, 12);
        a13 = getValue(detailList, 13);
        a14 = getValue(detailList, 14);
        a15 = getValue(detailList, 15);
        a16 = getValue(detailList, 16);
        a17 = getValue(detailList, 17);
        a18 = getValue(detailList, 18);
        a19 = getValue(detailList, 19);
        b0 = getValue(detailList, 20);
        b1 = getValue(detailList, 21);
        b2 = getValue(detailList, 22);
        b3 = getValue(detailList, 23);
        b4 = getValue(detailList, 24);
        b5 = getValue(detailList, 25);
        b6 = getValue(detailList, 26);
        b7 = getValue(detailList, 27);
        b8 = getValue(detailList, 28);
        b9 = getValue(detailList, 29);
        b10 = getValue(detailList, 30);
        b11 = getValue(detailList, 31);
        b12 = getValue(detailList, 32);
        b13 = getValue(detailList, 33);
        b14 = getValue(detailList, 34);
        b15 = getValue(detailList, 35);
        b16 = getValue(detailList, 36);
        b17 = getValue(detailList, 37);
        b18 = getValue(detailList, 38);
        b19 = getValue(detailList, 39);
    }

    private String getValue(List<ItemDetail> detailList, int i) {
        if (detailList == null) {
            return "";
        }
        if (i < detailList.size()) {
            return detailList.get(i).getSn();
        }
        return "a";
    }
}
