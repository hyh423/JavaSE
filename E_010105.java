package com.kkb.e;


import java.util.Scanner;

/**
 * 快递 E 栈训练任务(控制台简易版)
 */
public class E_010105 {

    public static Scanner input = new Scanner(System.in);
    public static int[] numArr = new int[10];//快递单 号数组
    public static String[] companyArr = new String[10];//快递公司 数组
    public static int[] codeArr = new int[10];//快递取件码 数组
    public static int index = 0;//快递件数，每个快递数组对应1个下标

    public static void main(String[] args) {
        while (true){
            if (startMenu()!=0) {
                startMenu();
            }else{
                System.out.println("欢迎下次继续使用！");
                return;//退出死循环
            }
        }
    }
    //初始菜单
    public static int startMenu(){
        System.out.println("======欢迎使用花花快递e柜=====");
        System.out.println("请输入您的身份：1-快递员 2-用户 0-退出");
        int idnums = input.nextInt();
        switch (idnums){
            case 0:
                break;//输入为0则退出
            case 1:
                courierMenu();//快递员菜单
                break;
            case 2:
                userMenu();//用户菜单
                break;
            default:
                System.out.println("请输入正确身份对应的序号！");
                break;
        }
        return idnums;
    }
    //快递员菜单
    public static void courierMenu(){
        System.out.println("请选择操作：1-存快递 2-删除快递 3-修改快递信息 4-查看所有快递");
        int num = input.nextInt();
        switch (num){
            case 1: //存快递
                saveCourier();
                break;
            case 2://删除快递
                delCourier();
                break;
            case 3://修改快递信息
                modCourier();
                break;
            case 4://查看所有快递
                checkCourier();
                break;
            default:
                System.out.println();
                break;
        }
    }

    /**
     * 存快递
     */
    public static void saveCourier(){
        System.out.println("请输入快递单号：");
        int trackingNum = input.nextInt();
        //存快递单号
        numArr[index] = trackingNum;
        System.out.println("请输入快递公司名称：");
        String company = input.next();
        //存快递公司名称
        companyArr[index] = company;
        //随机数范围（1000-9999）内生成取件码
        int takeCode = 0;
        do {
            takeCode = (int) (Math.random()*8999+1000);
        }while (isExist(takeCode) != -1);
        //存取件码
        codeArr[index] = takeCode;
        System.out.println("快递已存入，取件码是："+takeCode);
        index++;
        hintMenu();
    }
    //判断取件码是否重复
    public static int isExist(int code){
        for (int i=0;i<index;i++){
            if (codeArr[i] == code){
                return i;
            }
        }
        return -1;
    }
    /**
     * 删除快递
     */
    public static void delCourier(){
        System.out.println("请输入要删除的快递单号：");
        int removeNum = input.nextInt();
        int delIndex = findNum(removeNum);
        if (delIndex == -1){
            System.out.println("未找到快递");
            courierMenu();
        }else{
            del(delIndex);
            System.out.println("删除成功");
            hintMenu();
        }
    }
    public static void del(int delIndex){
        if (delIndex != numArr.length-1){//开始删除
            for (int i=delIndex;i<index;i++){
                numArr[i] = numArr[i+1];
                companyArr[i] = companyArr[i+1];
                codeArr[i] = codeArr[+1];
            }
        }
        index--;//删除完后，快递数量减1
    }
    //查找下标
    public static int findNum(int number){
        for (int i=0;i<index;i++){
            if ( numArr[i]== number){
                return i;
            }
        }
        return -1;
    }

    /**
     * 修改快递
     */
    public static void modCourier(){
        System.out.println("请输入要修改的快递单号：");
        int modNum = input.nextInt();
        int tempIndex = findNum(modNum);
        if (tempIndex == -1){
            System.out.println("未找到快递");
        }else{
            System.out.println("请输入新的快递单号：");
            modNum = input.nextInt();
            System.out.println("请输入新的公司名称：");
            String company = input.next();
            numArr[tempIndex] = modNum;
            companyArr[tempIndex] = company;
            System.out.println("修改成功！");
        }
    }

    /**
     * 查找快递
     */
    public static void checkCourier(){
        System.out.println("----- 所有快递信息 -----");
        System.out.println("快递单号\t公司名称\t取件码");
        for (int i=0;i<index;i++){
            System.out.print(numArr[i]+"\t"+companyArr[i]+"\t"+codeArr[i]);
            System.out.println();
        }
    }
    /**
     * 用户菜单
     */
    public static void userMenu(){
        System.out.println("请输入取件码：");
        int code = input.nextInt();
        int codeIndex = isExist(code);
        if (codeIndex == -1){
            System.out.println("未找到该取件码对应快递！");
        }else{
            del(codeIndex);//取件成功后删除快递
            System.out.println("取件成功！");
        }
    }

    /**
     * 做完一项业务后提示是否继续操作
     */
    public static void hintMenu(){
        System.out.println("是否还要继续操作：1-返回菜单 0-退出");
        int handleNum = input.nextInt();
        if (handleNum == 1){
            courierMenu();
        }else if (handleNum == 0){
            startMenu();
        }else{
            System.out.println("小哥辛苦了！");
        }
    }
}
