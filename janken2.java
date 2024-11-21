import java.util.Scanner;

public class Janken {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1行目でじゃんけんを行う回数を取得
        int n = scanner.nextInt();
        scanner.nextLine(); // 改行を読み飛ばす
        
        // 各じゃんけんの結果を判定
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();   // 1行の入力（自分の手と相手の手）
            String[] hands = input.split(" ");   // 入力をスペースで分割
            String myHand = hands[0];   // 自分の手
            String opponentHand = hands[1];  // 相手の手
            
            // 文字列を数字に変換
            int myHandInt = convertToInt(myHand);
            int opponentHandInt = convertToInt(opponentHand);
            
            // 勝敗を判定
            if (myHandInt == opponentHandInt) {
                System.out.println("Draw");
            } else if ((myHandInt == 0 && opponentHandInt == 2) || 
                       (myHandInt == 2 && opponentHandInt == 5) || 
                       (myHandInt == 5 && opponentHandInt == 0)) {
                System.out.println("Win");
            } else {
                System.out.println("Lose");
            }
        }

        scanner.close();
    }
    
    // 文字列を数字に変換するヘルパーメソッド
    public static int convertToInt(String hand) {
        if (hand.equals("グー")) {
            return 0;
        } else if (hand.equals("チョキ")) {
            return 2;
        } else if (hand.equals("パー")) {
            return 5;
        } else {
            throw new IllegalArgumentException("Invalid hand: " + hand);
        }
    }
}

/*
入力値の例
3
グー チョキ
パー グー
チョキ チョキ
*/
