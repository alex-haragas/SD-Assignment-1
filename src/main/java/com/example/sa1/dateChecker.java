package com.example.sa1;

public class dateChecker {
    public boolean compDate(String s1, String s2) {
        int d1, m1, y1, d2, m2, y2;
        d1 = Integer.parseInt(s1.substring(0, 1));
        m1 = Integer.parseInt(s1.substring(3, 4));
        y1 = Integer.parseInt(s1.substring(6, 9));
        d2 = Integer.parseInt(s2.substring(0, 1));
        m2 = Integer.parseInt(s2.substring(3, 4));
        y2 = Integer.parseInt(s2.substring(6, 9));
        if (y1 < y2) {
            return true;
        } else {
            if (y1 == y2) {
                if (m1 < m2) {
                    return true;
                } else {
                    if (m1 == m2) {
                        if (d1 <= d2) {
                            return true;
                        } else
                            return false;
                    } else
                        return false;
                }
            } else
                return false;
        }
    }

}
