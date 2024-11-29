import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        System.out.println("ゲームを開始します");
        //空の山札を作成
        List<Integer> deck = new ArrayList<>(52);
        //山札をシャッフル
        shuffleDeck(deck);

        //プレイヤー・ディーラーの手札リストを生成
        List<Integer> player = new ArrayList<>();
        List<Integer> dealer = new ArrayList<>();

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

        System.out.println("あなたの現在のポイントは" + playerPoint + "です。");

        // プレイヤーがカードを引くフェーズ
        Scanner scan = new Scanner(System.in);  // Scannerはループ外で1回だけ作成

        while (true) {
            System.out.println("カードを引きますか？Yes:y or No:n");
            // 入力を確認し、次の行が存在する場合に処理を実行
            if (scan.hasNextLine()) {
                String str = scan.nextLine();

                if ("n".equals(str)) {
                    break;
                } else if ("y".equals(str)) {
                    //手札に山札から1枚加える
                    player.add(deck.get(deckCount));
                    //山札と手札を一枚進める
                    deckCount++;
                    playerHands++;

                    System.out.println("あなたの" + playerHands + "枚目のカードは" + toDescription(player.get(playerHands - 1)));
                    playerPoint = sumPoint(player);
                    System.out.println("現在の合計は" + playerPoint);
                    //プレイヤーのバーストチェック
                    if (isBusted(playerPoint)) {
                        System.out.println("残念、バーストしてしまいました。");
                        return;
                    }
                } else {
                    System.out.println("あなたの入力は" + str + " です。y か n を入力してください。");
                }
            } else {
                // 次の行がない場合、入力待機
                System.out.println("入力がありません。ゲームを終了します。");
                break;
            }
        }

        //ディーラーが手札を17以上にするまでカードを引くフェーズ
        while (true) {
            //手札が17以上の場合ブレーク
            if (dealerPoint >= 17) {
                break;
            } else {
                //手札に山札から1枚加える
                dealer.add(deck.get(deckCount));
                //山札を一枚進める
                deckCount++;

                //ディーラーの合計ポイントを計算
                dealerPoint = sumPoint(dealer);
                //ディーラーのバーストチェック
                if (isBusted(dealerPoint)) {
                    System.out.println("ディーラーがバーストしました。あなたの勝ちです！");
                    return;
                }

            }
        }

        System.out.println("あなたのポイントは" + playerPoint);
        System.out.println("ディーラーのポイントは" + dealerPoint);

        if (playerPoint == dealerPoint) {
            System.out.println("引き分けです。");
        } else if (playerPoint > dealerPoint) {
            System.out.println("勝ちました！");
        } else {
            System.out.println("負けました・・・");
        }
    }

    // 山札（deck）に値を入れ、シャッフルするメソッド
    private static void shuffleDeck(List<Integer> deck) {
        // リストに1-52の連番を代入
        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }
        //山札をシャッフル
        Collections.shuffle(deck);
    }

    // 手札がバーストしているか判定するメソッド
    private static boolean isBusted(int point) {
        return point > 21;
    }

    // 現在の合計ポイントを計算するメソッド
    private static int sumPoint(List<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += toPoint(toNumber(list.get(i)));
        }
        return sum;
    }

    // トランプの数字を得点計算用のポイントに変換するメソッド
    private static int toPoint(int num) {
        if (num == 11 || num == 12 || num == 13) {
            num = 10;
        }
        return num;
    }

    // 山札の数を（スート）の（ランク）の文字列に置き換えるメソッド
    private static String toDescription(int cardNumber) {
        String rank = toRank(toNumber(cardNumber));
        String suit = toSuit(cardNumber);
        return suit + "の" + rank;
    }

    // 山札の数をカードの数に置き換えるメソッド
    private static int toNumber(int cardNumber) {
        int number = cardNumber % 13;
        if (number == 0) {
            number = 13;
        }
        return number;
    }

    // カード番号をランク（AやJ,Q,K）に変換するメソッド
    private static String toRank(int number) {
        switch (number) {
            case 1: return "A";
            case 11: return "J";
            case 12: return "Q";
            case 13: return "K";
            default: return String.valueOf(number);
        }
    }

    // 山札の数をスート（ハートやスペードなどのマーク）に置き換えるメソッド
    private static String toSuit(int cardNumber) {
        switch ((cardNumber - 1) / 13) {
            case 0: return "クラブ";
            case 1: return "ダイヤ";
            case 2: return "ハート";
            case 3: return "スペード";
            default: return "例外です";
        }
    }
}
