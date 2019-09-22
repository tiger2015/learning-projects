package com.tiger;

import java.util.ArrayList;
import java.util.List;

import static com.tiger.Test02.SIG.V;
import static com.tiger.Test02.SIG.X;


public class Test02 {

    enum SIG {
        I(1),
        IV(4),
        V(5),
        IX(9),
        X(10),
        XL(40),
        L(50),
        XC(90),
        C(100),
        CD(400),
        D(500),
        CM(900),
        M(1000),
        NONE(-1);
        int val;

        SIG(int val) {
            this.val = val;
        }

        public static SIG getSig(String str) {
            for (SIG sig : SIG.values()) {
                if (sig.toString().equalsIgnoreCase(str)) {
                    return sig;
                }
            }
            return NONE;
        }
    }


    public static void main(String[] args) {

        String s = intToRoman(9);
        System.out.println(s);
    }


    public static int maxArea(int[] height) {
        int size = height.length;
        if (size <= 1) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                // 子数组： i : j 长度 j-i+1
                int capacity = (Math.min(height[i], height[j])) * (j - i);
                if (capacity > max) {
                    max = capacity;
                }
            }
        }
        return max;
    }


    public static String intToRoman(int num) {
        List<SIG> list = new ArrayList<>();
        while (num > 0) {
            if (num / SIG.M.val > 0) {
                list.add(SIG.M);
                num = num - SIG.M.val;
            } else if (num / SIG.D.val > 0) {
                list.add(SIG.D);
                num = num - SIG.D.val;
            } else if (num / SIG.C.val > 0) {
                list.add(SIG.C);
                num = num - SIG.C.val;
            } else if (num / SIG.L.val > 0) {
                list.add(SIG.L);
                num = num - SIG.L.val;
            } else if (num / X.val > 0) {
                num = num - X.val;
                list.add(X);
            } else if (num / V.val > 0) {
                list.add(V);
                num = num - V.val;
            } else if (num / SIG.I.val > 0) {
                list.add(SIG.I);
                num = num - SIG.I.val;
            }
        }
        // 连续四个相同
        List<SIG> trimList = new ArrayList<>();
        int i = 0;
        for (; i <= list.size() - 4; ) {
            if (list.get(i) == list.get(i + 1) && list.get(i + 1) == list.get(i + 2) && list.get(i + 2) == list.get(i + 3)) {
                switch (list.get(i)) {
                    case C: // CCCC ->CD
                        trimList.add(SIG.C);
                        trimList.add(SIG.D);
                        break;
                    case X:  // XXXX->XL
                        trimList.add(X);
                        trimList.add(SIG.L);
                        break;
                    case I: // IIII -> IV
                        trimList.add(SIG.I);
                        trimList.add(V);
                        break;
                }
                i += 4;
            } else {
                trimList.add(list.get(i));
                i += 1;
            }
        }

        for (; i < list.size(); i++) {
            trimList.add(list.get(i));
        }

        // 连续3个 DCD -> CM  LXL -> XC VIV -> IX
        List<SIG> result = new ArrayList<>();
        i = 0;
        for (; i <= trimList.size() - 3; ) {
            if (trimList.get(i) == SIG.D && trimList.get(i + 1) == SIG.C && trimList.get(i + 2) == SIG.D) {
                result.add(SIG.C);
                result.add(SIG.M);
                i += 3;
            } else if (trimList.get(i) == SIG.L && trimList.get(i + 1) == X && trimList.get(i + 2) == SIG.L) {
                result.add(X);
                result.add(SIG.C);
                i += 3;
            } else if (trimList.get(i) == V && trimList.get(i + 1) == SIG.I && trimList.get(i + 2) == V) {
                result.add(SIG.I);
                result.add(X);
                i += 3;
            } else {
                result.add(trimList.get(i));
                i++;
            }
        }
        for (; i < trimList.size(); i++) {
            result.add(trimList.get(i));
        }
        StringBuilder builder = new StringBuilder();
        for (i = 0; i < result.size(); i++) {
            builder.append(result.get(i).toString());
        }
        return builder.toString();
    }

    public static int romanToInt(String s) {
        int result = 0;
        int size = s.length();
        int i = 0;
        for (; i < size; ) {
            SIG sig = SIG.getSig(s.substring(i, i + 1));
            switch (sig) {
                case V:
                case L:
                case D:
                case M:
                    result += sig.val;
                    i++;
                    break;
                case I:
                case X:
                case C:
                    if (i + 1 < size) {
                        SIG next = SIG.getSig(s.substring(i, i + 2));

                        switch (next) {
                            case IV:
                            case IX:
                            case XL:
                            case XC:
                            case CD:
                            case CM:
                                result += next.val;
                                i += 2;
                                break;
                            case NONE:
                                result += sig.val;
                                i++;
                                break;
                        }
                    } else {
                        result += sig.val;
                        i++;
                    }
                    break;
            }
        }
        return result;
    }


}
