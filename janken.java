//じゃんけんを判定するプログラム

public static void main(String[] args) {
    // 1．初めの処理
    System.out.println("じゃんけん ...");
    Scanner scan = new Scanner(System.in);

    // 2．標準入力受付
    String input = scan.nextLine();
    String jankenTe = null;

    // 3．ユーザーの手を判定する
    if (input.equals("0")) {
        jankenTe = "グー";
    } else if (input.equals("1")) {
        jankenTe = "チョキ";
    } else if (input.equals("2")) {
        jankenTe = "パー";
    } else {
        System.out.println("想定外の値です。" + input);
    }

    String cpuTe = null;
    String jankenCpu = null;
    if (jankenTe == null) {
        // 強制終了
        System.exit(-1);
    } else {
        // 4．CPUの手を判定する
        cpuTe = String.valueOf(new Random().nextInt(2));

        if (cpuTe.equals("0")) {
            jankenCpu = "グー";
        } else if (cpuTe.equals("1")) {
            jankenCpu = "チョキ";
        } else if (cpuTe.equals("2")) {
            jankenCpu = "パー";
        }
    }
    System.out.println("ポン");
    System.out.println("あなた: " + jankenTe + " CPU; " + jankenCpu);

    // 5．勝敗判定を行う
    String hantei = input + cpuTe;
    String result = null;
    if (hantei.equals("01")) {
        result = "YOU_WIN";
    } else if (hantei.equals("12")) {
        result = "YOU_WIN";
    } else if (hantei.equals("20")) {
        result = "YOU_WIN";
    } else if (hantei.equals("00")) {
        result = "DRAW";
    } else if (hantei.equals("11")) {
        result = "DRAW";
    } else if (hantei.equals("22")) {
        result = "DRAW";
    } else if (hantei.equals("02")) {
        result = "YOU_LOSE";
    } else if (hantei.equals("10")) {
        result = "YOU_LOSE";
    } else if (hantei.equals("21")) {
        result = "YOU_LOSE";
    } else {
        System.out.println("想定外の値です。" + hantei);
    }
    // 6. 結果の表示
    System.out.println(hantei);
    System.out.println(result);
}
