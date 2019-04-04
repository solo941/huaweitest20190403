package com.huawei;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int groupNums = sc.nextInt();
        sc.nextLine();
        while (sc.hasNextLine()) {
            for (int i = 0; i < groupNums; i++) {
                int result = 0;
                String[] strings = sc.nextLine().split(",");
                int persons = Integer.parseInt(strings[0]);
                int machineClean = 0;
                int NatureClean = Integer.parseInt(strings[3]);
                int cleanTime = 0;
                    String[] eachTimeStrings = sc.nextLine().split(",");
                    Integer[] eachTime = new Integer[eachTimeStrings.length];
                    Queue<CaffeeMachine> queue = new PriorityQueue<>();
                    for (int j = 0; j < eachTimeStrings.length; j++) {
                        Integer time = Integer.parseInt(eachTimeStrings[j]);
                        eachTime[j] = time;
                        CaffeeMachine machine = new CaffeeMachine(time);
                        //优先队列存储<machine,剩余等待时间>
                        queue.add(machine);
                    }
                    while (persons-- > 0) {
                        CaffeeMachine machine = queue.poll();
                        int time = machine.getTime();
                        for (CaffeeMachine c : queue) {
                            c.changeTime(time);
                        }

                        int initialTime = machine.getInitialTime();
                        //通过上一次生产咖啡到本次的时间间隔，判断清洁器能否使用
                        if (machineClean < NatureClean && time >= machineClean) {
                            result += time;
                            machineClean = Integer.parseInt(strings[2]);
                            cleanTime -= time;
                            cleanTime = Math.max(cleanTime, machineClean);
                        } else if (machineClean < NatureClean && time < machineClean) {
                            result += time;
                            machineClean -= time;
                            cleanTime -= time;
                            cleanTime = Math.max(cleanTime, NatureClean);
                        } else {
                            result += time;
                            cleanTime -= time;
                            cleanTime = Math.max(cleanTime, NatureClean);
                        }
                        //通过HashMap获取该咖啡机的生产咖啡时间,这里可以优化，生产时间以类的属性存储
                        CaffeeMachine newMachine = new CaffeeMachine(initialTime);
                        queue.add(newMachine);
                    }
                result += cleanTime;
                System.out.println(result);
            }
        }
    }
    private static class CaffeeMachine implements Comparable{
        private int time;

        public int getInitialTime() {
            return initialTime;
        }

        private int initialTime;

        public void setTime(int time) {
            this.time = time;
        }

        public int getTime() {
            return time;
        }

        public void changeTime(int t){
            this.time -= t;
        }

        private CaffeeMachine(int time) {
            this.time = time;
            this.initialTime = time;
        }

        @Override
        public int compareTo(Object o) {
            CaffeeMachine cm = (CaffeeMachine) o;
            if (cm.getTime() < this.time) return 1;
            else if (cm.getTime() == this.time) return 0;
            return -1;
        }
    }

}
