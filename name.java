import java.util.*;
public class Main {
    public static void main(String[] args) {
        // 自分の得意な言語で
        // Let's チャレンジ！！
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        
        String name = "";
        
        for (int i = 0; i <= num; i++) {
        
        String name1 = sc.nextLine();
        if (i < num && i > 0) {
            name1 = name1 + ",";
        }
        
        name = name + name1;
    }
    
    System.out.println("Hello" + " " + name + ".");
}
}


//繰り返しがなぜ５回になったのか謎　i <= num にしてたので、6回のはずでは？（0,1,2,3,4,5）
→Answer　7行目のnextIntが原因
nextInt()は数値だけをスキャンし改行はスキャンしない。そのあとのnextLine()は改行をスキャンするため、nextInt()の後ろ（入力１行目の5）にある改行もスキャンしてしまっている。
なので、forの繰り返しで6回されているが、一回目の繰り返しでは改行のため、目には見えてないだけ
つまり入力値は
5(ここの改行部だけスキャン)
Alice
Bob
Carol
Dave
Ellen
となっている。

/*
入力例2
5
Alice
Bob
Carol
Dave
Ellen

出力例2
Hello Alice,Bob,Carol,Dave,Ellen.
*/
