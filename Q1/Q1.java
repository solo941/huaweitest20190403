package com.huawei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        ArrayList<String> allString = new ArrayList<>();
        int allLength = 0;
        Map<Integer,String[]> stringMap = new HashMap();
        //todo

        Scanner sc = new Scanner(System.in);
        Integer num = sc.nextInt();
        int lineNum = 0;
        /*
        在nextInt()获取第一行数字后，光标停留在有效整数后面，
        nextLine()会读取当前光标到换行符，并将光标移动到下一行的开始位置
        这里会多读一行空字符.
         */
        sc.nextLine();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] lineStringArray = line.split(",");
            stringMap.put(lineNum,lineStringArray);
            allLength = allLength + lineStringArray.length;
            lineNum++;
    }
        int loop = 1;
        while (allString.size() < allLength) {
            for (int i = 0; i < lineNum; i++) {
                String[] nowStringArray = stringMap.get(i);
                if (nowStringArray.length >= num * loop) {
                    for (int j = (loop - 1) * num; j < num * loop; j++) {
                        allString.add(nowStringArray[j]);
                    }
                }
                if (nowStringArray.length < num * loop && nowStringArray.length > num * (loop - 1)) {
                    for (int j = (loop - 1) * num; j < nowStringArray.length; j++) {
                        allString.add(nowStringArray[j]);
                    }
                }
            }
            loop++;
        }
    String result = "";
    for (int index = 0; index< allString.size(); index++){
        if (index != allString.size() -1){
            result = result + allString.get(index) + ",";
        }
        else result = result + allString.get(index);
    }
        System.out.println(result);

    }
}
