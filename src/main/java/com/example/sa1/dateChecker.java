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
    public boolean validDate(String s1){
        int d,m,y;
        d = Integer.parseInt(s1.substring(0, 1));
        m = Integer.parseInt(s1.substring(3, 4));
        y = Integer.parseInt(s1.substring(6, 9));
        if(d<=0 || m<=0){
            return false;
        }
        if(y>=2022){
            if(m<=12){
                if(m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12 && d<=31){
                    return true;
                }
                if(m==4 || m==6 || m==9 || m==11 && d<=30){
                    return true;
                }
                if(m==2 && ((y%4==0 && d<=29) || (y%4!=0 && d<=28))){
                    return true;
                }

                return false;
            }
            else
                return false;
        }
        else{
            return false;
        }
    }
}
