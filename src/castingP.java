import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by sunxuan on 4/19/16.
 */
public class castingP {

    public static void main(String args[]) throws Exception {
        int V;
//        int E = 0;
        int m;

        String thisLine = null;
//        try{
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("./3.txt"));
//            while ((thisLine=bufferedReader.readLine())!=null){
//                System.out.println(thisLine);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./3.txt"));
//        String line = readLine(1,bufferedReader);
        ArrayList<Integer> integerArrayList = readLineInteger(1, bufferedReader);
        V = integerArrayList.get(0) + integerArrayList.get(2) + 1;
//        System.out.println(V);
//        String[] numbersArray = line.split(" ");
//        System.out.println(numbersArray[0]);
//        V = Integer.valueOf(numbersArray[0])+Integer.valueOf(numbersArray[2])+1;
//        bufferedReader.close();
//        System.out.println(V);

//        int rowPeople = Integer.valueOf(numbersArray[2]);
//        int colRoles = Integer.valueOf(numbersArray[0]);
//        int colScenes = Integer.valueOf(numbersArray[1]);


//
//        System.out.println(rowPeople);
//        System.out.println(colRoles);
//        System.out.println(colScenes);
//
//        Boolean[][] firstTable = new Boolean[rowPeople][colRoles];
//        Boolean[][] secondTale = new Boolean[rowPeople][colScenes];

        int rowPeople = integerArrayList.get(2);
        int colRoles = integerArrayList.get(0);
        int colScenes = integerArrayList.get(1);

        m = colRoles; // 所用颜色

        Boolean[][] firstTable = new Boolean[rowPeople][colRoles]; // 布尔二维数组
        Boolean[][] secondTale = new Boolean[colRoles][colScenes];


        for (int i = 0; i < colRoles; i++) { // 表1 按列 添加 布尔值
            int j = 1;
            ArrayList<Integer> tempList = readLineInteger(j, bufferedReader);
            tempList.remove(tempList.get(0)); //删除掉 每行第一个数
            System.out.println("The " + (i + 1) + " Line: " + tempList);
            for (int p = 0; p < tempList.size(); p++) {
                firstTable[tempList.get(p) - 1][i] = true;
            }
            for (int q = 0; q < rowPeople; q++) {
                if (!tempList.contains(q + 1)) {
                    firstTable[q][i] = false;
                }
            }
        }

        System.out.println("**************");

        for (int i = 0; i < colScenes; i++) { // 表2 按列添加 布尔值
            int j = 1; // 一次读取一行
            ArrayList<Integer> integerArrayList1 = readLineInteger(j, bufferedReader); //数值转化为 数组
            integerArrayList1.remove(integerArrayList1.get(0)); //Delete the first number
            System.out.println(integerArrayList1);

            for (int q = 0; q < integerArrayList1.size(); q++) { //从第二位开始时读取
                secondTale[integerArrayList1.get(q) - 1][i] = true; //对应位置 改为 true
            }
            for (int p = 0; p < colRoles; p++) { //其余部分为 false
                if (!integerArrayList1.contains(p + 1)) {
                    secondTale[p][i] = false;
                }
            }
        }

        for (int i = 0; i < colRoles; i++) {
            System.out.println(firstTable[2][i]);
        }

        HashMap<edgesConnect, Boolean> connectHashMap = new HashMap<edgesConnect, Boolean>();
        for (int i = 0; i < colScenes; i++) { //列
            for (int j = 0; j < colRoles; j++) { //行
//                boolean p1up = firstTable[0][j]; //有的话 就是1
//                boolean p2up = firstTable[1][j];

                if (secondTale[j][i]) { //列先不变,行变换

                    for (int p = j + 1; p < colRoles; p++) {
                        if (secondTale[p][i]) {
                            edgesConnect temp = new edgesConnect(j + 1, p + 1); //2个点相连
                            connectHashMap.put(temp, true);
                            boolean p1up = firstTable[0][j]; //p1 是否可以扮演
                            boolean p2up = firstTable[1][j]; //p2 是否可以扮演
                            boolean p1down = firstTable[0][p];
                            boolean p2down = firstTable[1][p];
                            boolean p1final = p1up || p1down;// p1 一列是否存在1
                            boolean p2final = p2up || p2down; // 先||
                            if (p1final && p2final) { //后 && 判定两列是否都为1 如果是, 两点均要与最后一个点(额外设立的点)相连
                                edgesConnect tempStar1 = new edgesConnect(j+1, V);
                                edgesConnect tempStar2 = new edgesConnect(p+1, V);
                                connectHashMap.put(tempStar1, true);
                                connectHashMap.put(tempStar2, true);
                            }
                        }
                    }

                }
            }
        }
//

        System.out.println(V);
        System.out.println(connectHashMap.size());
        System.out.println(m);


    }

//    private static String readLine(int lineNum,BufferedReader bufferedReader) throws Exception{
//        String lineString= "";
//        ArrayList<Integer> arrayList= new ArrayList<Integer>();
//        int i =0;
//        while (i<lineNum){
//            lineString = bufferedReader.readLine();
//            i++;
//        }
//        String[] tempString = lineString.split(" ");
//
//        for (int j=0;j<tempString.length;j++){
//            arrayList.add(Integer.valueOf(tempString[j]));
//        }
//        System.out.println(arrayList);
//        return lineString;
//    }

    private static ArrayList<Integer> readLineInteger(int lineNum, BufferedReader bufferedReader) throws Exception {
        String lineString = "";
        String[] tempString = null;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        int i = 0;
        while (i < lineNum) {
            lineString = bufferedReader.readLine();
            i++;
        }
        tempString = lineString.split(" ");

        for (String aTempString : tempString) {
            arrayList.add(Integer.valueOf(aTempString));
        }
//        System.out.println(arrayList);

        return arrayList;
    }
}



