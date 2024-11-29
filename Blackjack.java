import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/*カード枚数は52枚。ジョーカーは含めない。カードの重複が無いように山札を構築する。
プレイヤー、ディーラーの一対一で対戦するものとし、以下の挙動を取る
初期設定として、プレイヤー・ディーラーが交互に1枚ずつ山札からカードを取り手札とする。
プレイヤーからは自分の手札すべてと、ディーラーの1枚めの手札が確認できる。（ディーラーの2枚目移行の手札はわからない）

手札はAが1ポイント、2-10がそれぞれ2-10ポイント、J/Q/Kが10ポイントとして計算される。

プレイヤーは手札を1枚追加するか、しないかを選択できる。
手札を追加した場合、21ポイントを超えるとバーストとなり、ゲームに敗北する。
プレイヤーはバーストするか、好きなタイミングで止めるまで手札にカードを追加できる。
ディーラーは手札の合計ポイントが17以上になるまで山札を引き続ける。
ディーラーの手札が21ポイントを超えた場合、バーストしてプレイヤーの勝利。
ディーラーの手札が18以上21以下になったとき次の段階に移行する。

プレイヤー・ディーラーの手札のポイントを比較して、大きいほうが勝利。

ダブルダウン・スプリット・サレンダーなどの特殊ルールは無し。*/

class Main {

    public static void main(String[] args) {

         System.out.println("ゲームを開始します");
         //空の山札を作成
         List <Integer> deck = new ArrayList<>(52);
         //山札をシャッフル
         shuffleDeck(deck);


         //プレイヤー・ディーラーの手札リストを生成
         List <Integer> player = new ArrayList<>();
         List <Integer> dealer = new ArrayList<>();


         //プレイヤー・ディーラーがカードを2枚引く
         player.add(deck.get(0));
         dealer.add(deck.get(1));
         player.add(deck.get(2));
         dealer.add(deck.get(3));

         //山札の進行状況を記録する変数deckCountを定義
         int deckCount = 4;

         //プレイヤーの手札枚数を記録する変数playerHandsを定義
         int playerHands = 2;



        //プレイヤー・ディーラーの手札のポイントを表示
         System.out.println("あなたの1枚目のカードは" + toDescription(player.get(0)));

         System.out.println("ディーラーの1枚目のカードは" + toDescription(dealer.get(0)));

         System.out.println("あなたの2枚めのカードは" + toDescription(player.get(1)));

         System.out.println("ディーラーの2枚めのカードは秘密です。");

         //プレイヤー・ディーラーのポイントを集計
         int playerPoint = sumPoint(player);
         int dealerPoint = sumPoint(dealer);

         System.out.println("あなたの現在のポイントは" + playerPoint + "です。" );


         //プレイヤーがカードを引くフェーズ
         while(true) {
             System.out.println("カードを引きますか？Yes:y or No:n");
             Scanner scan = new Scanner(System.in);
             String str = scan.next();

             if("n".equals(str)) {
                 break;
             } else if("y".equals(str)) {
                //手札に山札から1枚加える
                 player.add(deck.get(deckCount));
                 //山札と手札を一枚進める
                 deckCount++;
                 playerHands ++;


                 System.out.println("あなたの" + playerHands + "枚目のカードは" + toDescription(player.get(playerHands - 1)));
                 playerPoint = sumPoint(player);
                 System.out.println("現在の合計は" + playerPoint );
                 //プレイヤーのバーストチェック
                 if(isBusted(playerPoint)) {
                     System.out.println("残念、バーストしてしまいました。");
                     return;
                 }
             } else {
                 System.out.println("あなたの入力は" + str + " です。y か n を入力してください。");
             }
         }

         //ディーラーが手札を17以上にするまでカードを引くフェーズ
         while(true) {
             //手札が17以上の場合ブレーク
             if(dealerPoint >= 17) {
                 break;
             } else {
                //手札に山札から1枚加える
                 dealer.add(deck.get(deckCount));
                 //山札を一枚進める
                 deckCount++;

                //ディーラーの合計ポイントを計算
                 dealerPoint = sumPoint(dealer);
                //ディーラーのバーストチェック
                 if(isBusted(dealerPoint)) {
                     System.out.println("ディーラーがバーストしました。あなたの勝ちです！");
                     return;
                 }

             }

         }
         System.out.println("あなたのポイントは" + playerPoint);
         System.out.println("ディーラーのポイントは"+ dealerPoint);

         if(playerPoint == dealerPoint) {
             System.out.println("引き分けです。");
         } else if(playerPoint > dealerPoint) {
             System.out.println("勝ちました！");
         } else {
             System.out.println("負けました・・・");
         }




    }
    //山札（deck）に値を入れ、シャッフルするメソッド
     private static void shuffleDeck(List<Integer> deck) {


        // リストに1-52の連番を代入
        for(int i = 1; i <= 52; i++){
            deck.add(i);
        }

        //山札をシャッフル
        Collections.shuffle(deck);


        //リストの中身を確認（デバッグ用）
        /* for (int i=0; i<deck.size(); i++)
        {
            System.out.println(deck.get(i));
        }*/
    }

    //手札がバーストしているか判定するメソッド
     private static boolean isBusted(int point) {
         if (point <= 21) {
             return false;
         } else {
             return true;
         }
     }

     //現在の合計ポイントを計算するメソッド
     private static int sumPoint(List<Integer> list) {
         int sum = 0;

         for(int i =0;i < list.size();i++) {
             sum = sum + toPoint(toNumber(list.get(i)));
         }

         return sum;
     }

     //トランプの数字を得点計算用のポイントに変換するメソッド.J/Q/Kは10とする
     private static int toPoint(int num) {
         if(num ==11||num == 12||num == 13) {
             num = 10;
         }

         return num;
     }

     //山札の数を（スート）の（ランク）の文字列に置き換えるメソッド
     private static String toDescription(int cardNumber) {
         String rank = toRank(toNumber(cardNumber));
         String suit = toSuit(cardNumber);

         return suit + "の" + rank;
     }

     //山札の数をカードの数に置き換えるメソッド
     private static int toNumber(int cardNumber) {
         int number = cardNumber % 13;
         if(number == 0) {
             number = 13;
         }

         return number;
     }

     //カード番号をランク（AやJ,Q,K）に変換するメソッド
     private static String toRank(int number) {
         switch(number) {
         case 1:
             return "A";
         case 11:
             return "J";
         case 12:
             return "Q";
         case 13:
             return "K";
        default:
            String str = String.valueOf(number);
            return str;
         }
     }

    //山札の数をスート（ハートやスペードなどのマーク）に置き換えるメソッド
     private static String toSuit(int cardNumber) {
         switch((cardNumber - 1)/13) {
         case 0:
             return "クラブ";
         case 1:
             return "ダイヤ";
         case 2:
             return "ハート";
         case 3:
             return "スペード";
        default:
            return "例外です";
         }
     }
}
