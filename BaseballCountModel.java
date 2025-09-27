// モデルクラス（データを保持するクラス）
public class BaseballCountModel {
    private int balls; // ボール数
    private int strikes; // ストライク数
    private int outs; // アウト数
    private boolean isChange; // チェンジかどうか

    public static final int MAX_BALLS = 3; // 最大ボール数
    public static final int MAX_STRIKES = 2; // 最大ストライク数
    public static final int MAX_OUTS = 2; // 最大アウト数

    // コンストラクタ（初期化）
    public BaseballCountModel() {
        resetCounts(); // カウントをリセット
    }

    // リセット関数
    public void resetCounts() {
        balls = 0;
        strikes = 0;
        outs = 0;
        isChange = false;
    }

    // ボール追加関数
    public void addBall() {
        // チェンジでない場合
        if (!isChange) {
            balls++; // ボール数を1増やす
            // ボール数が最大ボール数を超えた場合
            if (balls > MAX_BALLS) {
                balls = 0; // ボール数を0に戻す
                strikes = 0; // ストライク数を0に戻す
            }
        }
    }

    // ストライク追加関数
    public void addStrike() {
        // チェンジでない場合
        if (!isChange) {
            strikes++; // ストライク数を1増やす
            // ストライク数が最大ストライク数を超えた場合
            if (strikes > MAX_STRIKES) {
                balls = 0; // ボール数を0に戻す
                strikes = 0; // ストライク数を0に戻す
                outs++; // アウト数を1増やす
                // アウト数が最大アウト数を超えた場合
                if (outs > MAX_OUTS) {
                    outs = 0; // アウト数を0に戻す
                    isChange = true; // チェンジフラグを立てる
                }
            }
        }
    }

    // アウト追加関数
    public void addOut() {
        // チェンジでない場合
        if (!isChange) {
            // アウト数を1増やす
            outs++;

            // アウト数が最大アウト数を超えた場合
            if (outs > MAX_OUTS) {
                outs = 0; // アウト数を0に戻す
                isChange = true; // チェンジフラグを立てる
            }
            // ボール数を0に戻す
            balls = 0;

            // ストライク数を0に戻す
            strikes = 0;
        }
    }

    public int getBalls() {
        return balls;
    }

    public int getStrikes() {
        return strikes;
    }

    public int getOuts() {
        return outs;
    }

    public boolean isChange() {
        return isChange;
    }

    public void resetChangeFlag() {
        isChange = false;
    }
}