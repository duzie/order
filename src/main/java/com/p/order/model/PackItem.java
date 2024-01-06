package com.p.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PackItem {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;


    private boolean prevSame;
    private long rowspan = 1;

    public void setD(String d) {
        if (d != null) {
            d = d.trim();
        }
        this.d = d;
    }

    public void setE(String e) {
        if (e != null) {
            e = e.trim();
        }
        this.e = e;
    }

    public void setF(String f) {
        if (f != null) {
            f = f.trim();
        }
        this.f = f;
    }

    public PackItem(String a, String b, String c, String d, String e, String f, String g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
    }

    public int getSize() {
        if (StringUtils.isBlank(g)) {
            return 0;
        }
        g = g.trim();
        String[] split = g.split("\\*");
        return Integer.parseInt(split[0]) * Integer.parseInt(split[1]) * Integer.parseInt(split[2]);
    }
}
