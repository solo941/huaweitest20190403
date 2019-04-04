package com.huawei;

import java.util.*;

public class Q2 {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        HashMap<String,Integer> stringMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            //字符串之间有空格（包括空行)
            String s = sc.nextLine().trim();
            if (s.equals("")) break;
            strings.add(s);
        }
        ArrayList<String> notValidStrings = new ArrayList<>();
        for (int i = 0; i<strings.size(); i++){
            String nowString = strings.get(i);
            if(isValid(nowString)){
                if(!stringMap.containsKey(nowString)){
                    stringMap.put(nowString,1);
                    System.out.print(nowString + " ");
                }
            }
            else notValidStrings.add(nowString);
        }
        System.out.println();
        for (String s: notValidStrings){
            System.out.print(s + " ");
        }
        System.out.println();

        Set<String> validDistinctStringSet = stringMap.keySet();
        String[] asciiSort = new String[validDistinctStringSet.size()];
        int count = 0;
        for (String s: validDistinctStringSet){
            String newString = leftMove(s);
            asciiSort[count++] = newString;
            System.out.print(newString + " ");
        }
        System.out.println();

        Arrays.sort(asciiSort);
        for (String s: asciiSort) {
            System.out.print(s + " ");
        }

    }

    private static String leftMove(String s) {
        int moveLength = 10 % s.length();
        return s.substring(moveLength, s.length()) + s.substring(0, moveLength);
    }

    private static boolean isValid(String s) {
        char c;
        for(int i = 0; i< s.length(); i++){
            c = s.charAt(i);
            if(!((c>='0' && c <= '9') || (c>='a' && c <= 'z') || (c>='A' && c <= 'C'))) return false;
        }
        return true;
    }
}
