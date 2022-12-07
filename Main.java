package cal;


import java.util.*;

public class Main {
    static Map<String,Integer> mapRome = new HashMap<>(){{
        put("I",1);put("II",2);put("III",3);put("IV",4);put("V",5);
        put("VI",6);put("VII",7);put("VIII",8);put("IX",9);put("X",10);
        put("L",50);put("C",100);
    }};
    static boolean romeNumber;
    static int oneNumber,twoNumber;

     public static void main(String[] args) {

        try(Scanner s = new Scanner(System.in);){
            System.out.print("Ввод:");
            System.out.println(calc(s.nextLine()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String calc(String input) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(List.of(input.split(" ")));

        if(list.size()<3 || list.size()>3){

            throw new Exception();
        }
        return resultMet(list).toString();
    }
    static String resultMet(ArrayList<String> numbers) throws Exception {
        String sing=numbers.get(1);
        int result ;
        getNum(numbers.get(0),numbers.get(2));
        switch (sing){
            case "+":
                result = oneNumber+ twoNumber;
                break;
            case "-":
                result = oneNumber - twoNumber;
                break;
            case "/":
                result = oneNumber/twoNumber;
                break;
            case "*":
                result = oneNumber* twoNumber;
                break;
            default:
                throw new Exception();
        }

        if(romeNumber && result>=0){
            return getRome(result);
        }
        return result+"";
    }

    static void getNum(String tempOne, String tempTwo) throws Exception {
        try {
            oneNumber = Integer.parseInt(tempOne);
            twoNumber = Integer.parseInt(tempTwo);
            romeNumber=false;

        }catch (Exception e){
            try {
                oneNumber = mapRome.get(tempOne);
                twoNumber = mapRome.get(tempTwo);
                romeNumber=true;

            }catch (Exception j){
                throw new Exception();
            }
        }
        if(oneNumber >=11 || twoNumber>=11){
            throw new Exception();
        }
    }
    static String getRome(int number){
        int h,l,d,u;
        h = number /100;
        l=(number-(h*100))/50;
        d = (number-((h*100)+ (l*50)))/10;
        u = number-((h*100)+(l*50)+d*10);

        return romeConverted(h,l,d,u);
    }
    static String romeConverted(int h, int l, int d, int u){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <mapRome.size() ; i++) {
            for (Map.Entry<String, Integer> entry : mapRome.entrySet()) {
                if (h != 0) {
                    if (entry.getValue() == 100) {
                        builder.append(entry.getKey().toString().repeat(h));
                        h = 0;
                        break;
                    }
                } else if (l != 0) {
                    if (entry.getValue() == 50) {
                        builder.append(entry.getKey().toString().repeat(l));
                        l = 0;
                    }
                } else if (d != 0) {
                    if (entry.getValue() == 10) {
                        builder.append(entry.getKey().toString().repeat(d));
                        d = 0;
                    }
                } else if (u != 0) {
                    if (entry.getValue() == u) {
                        builder.append(entry.getKey().toString());
                        u = 0;
                    }
                }
            }
        }
        return builder.toString();
    }

}
