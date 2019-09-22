package com.tiger;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Test01 {


    public static void main(String[] args) {
        int a[] = {1, 3};
        int b[] = {};
        //System.out.println(findMedianSortedArrays(a, b));

        String r = convert("LEETCODEISHIRING", 4);
        // LCIRETOESIIGEDHN
        System.out.println(r);

        char ch = '\u0000'; //空白字符
        System.out.println(ch);

        System.out.println(myAtoi("-2147483647"));

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                list.add(nums1[index1]);
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                list.add(nums2[index2]);
                index2++;
            } else {
                list.add(nums1[index1]);
                list.add(nums2[index2]);
                index1++;
                index2++;
            }
        }
        if (index1 >= 0) {
            for (; index1 < nums1.length; index1++) {
                list.add(nums1[index1]);
            }
        }

        if (index2 >= 0) {
            for (; index2 < nums2.length; index2++) {
                list.add(nums2[index2]);
            }
        }
        System.out.println(list.toString());
        return (index1 + index2) % 2 == 0 ? (list.get((index1 + index2) / 2 - 1) + list.get((index1 + index2) / 2)) / 2.0 : list.get((index1 + index2) / 2);
    }

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int maxLen = 0;
        List<Character> tempList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            tempList.clear();
            tempList.add(s.charAt(i));
            for (int j = i + 1; j < len; j++) {
                if (tempList.contains(s.charAt(j))) {
                    break;
                } else {
                    tempList.add(s.charAt(j));
                }
            }
            if (maxLen < tempList.size()) {
                maxLen = tempList.size();
            }
        }
        return maxLen;

    }

    public static String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        String r = "";
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                String sub = s.substring(i, j + 1);
                if (isPalindrome(sub)) {
                    if (r.length() < sub.length()) {
                        r = sub;
                    }
                }
            }
        }
        return r;
    }

    public static boolean isPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return true;
        }
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static String convert(String s, int numRows) {
        int single = numRows - 2; //只有一个字符的列数
        int len = s.length();
        int cols = (len / (single + numRows) + 1) * (numRows - 1); // 最多列数
        char[][] array = new char[numRows][cols];
        // char中默认字符为\u0000
        int index = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < numRows; j++) {
                if (index >= len) {
                    break;
                }
                if (i % (single + 1) == 0) {
                    array[j][i] = s.charAt(index++);
                } else if (j + i % (numRows - 1) == numRows - 1) {
                    array[j][i] = s.charAt(index++);
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < cols; j++) {
                builder.append(array[i][j]);
            }
        }
        return builder.toString();
    }


    public static int reverse(int x) {
        LinkedList<Integer> queue = new LinkedList<>();
        int data = x < 0 ? -x : x;
        while (data > 0) {
            queue.push(data % 10);
            data = data / 10;
        }
        long result = 0;
        // 删除为队头0的
        while (!queue.isEmpty() && queue.getFirst() == 0) {
            queue.removeFirst();
        }
        while (!queue.isEmpty()) {
            result = result * 10 + queue.removeLast();
        }
        result = x < 0 ? -result : result;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) result;
    }

    public static int myAtoi(String str) {
        String trimStr = str.trim();
        // 去除空格后的第一个字符不是数字或者正负号
        if (trimStr.length() <= 0 || (!(trimStr.charAt(0) <= 0x39 && trimStr.charAt(0) >= 0x30) && trimStr.charAt(0) != '-' && trimStr.charAt(0) != '+')) {
            return 0;
        }
        int len = trimStr.length();
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            char ch = trimStr.charAt(i);
            // 判断该字符为-，+，0-9，可能是数字字符
            if (ch == '-' || ch == '+') {
                if (characters.size() == 0) {
                    characters.add(ch);
                } else {
                    break;
                }
            } else if (ch >= 0x30 && ch <= 0x39) {
                characters.add(ch);
            } else {
                break;
            }
        }
        if (characters.size() == 0) {
            return 0;
        }
        long result = 0;

        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i) == '-' || characters.get(i) == '+') {
                continue;
            }
            result = result * 10 + characters.get(i) - 0x30;
            if (result > Integer.MAX_VALUE + 1L) {
                break;
            }
        }
        if (characters.get(0) == '-') {
            result = -result;
        }
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) result;
    }


}
