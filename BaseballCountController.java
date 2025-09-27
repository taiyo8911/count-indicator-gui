import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// コントローラークラス
public class BaseballCountController {
    private final BaseballCountModel model;
    private final BaseballCountView view;

    // コンストラクタ（モデルとビューを受け取る）
    public BaseballCountController(BaseballCountModel model, BaseballCountView view) {
        this.model = model;
        this.view = view;

        // ボールボタンにアクションリスナーを追加
        view.ballButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clearOnFirstAction())
                    return; // スリーアウトチェンジ後のクリア処理
                model.addBall();
                view.gameInfoTextArea.append("ボール\n");
                if (model.getBalls() == 0) {
                    view.gameInfoTextArea.append("フォアボール\n");
                }
                updateView();
            }
        });

        // ストライクボタンにアクションリスナーを追加
        view.strikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clearOnFirstAction())
                    return; // スリーアウトチェンジ後のクリア処理
                model.addStrike();
                view.gameInfoTextArea.append("ストライク\n");
                if (model.isChange()) {
                    view.gameInfoTextArea.append("スリーアウトチェンジ!\n");
                } else if (model.getStrikes() == 0) {
                    view.gameInfoTextArea.append("バッターアウト\n");
                }
                updateView();
            }
        });

        // アウトボタンにアクションリスナーを追加
        view.outButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clearOnFirstAction())
                    return; // スリーアウトチェンジ後のクリア処理
                model.addOut();
                view.gameInfoTextArea.append("アウト\n");
                if (model.isChange()) {
                    view.gameInfoTextArea.append("スリーアウトチェンジ!\n");
                }
                updateView();
            }
        });
    }

    // スリーアウトチェンジ後のクリア処理
    private boolean clearOnFirstAction() {
        if (model.isChange()) {
            view.gameInfoTextArea.setText(""); // ゲーム情報をクリア
            model.resetChangeFlag(); // チェンジフラグをリセット
            return true; // 初回のアクションを終了
        }
        return false; // 通常の処理を続行
    }

    // ビューを更新
    private void updateView() {
        view.updateLabels(model.getBalls(), model.getStrikes(), model.getOuts());
    }

    // メインメソッド
    public static void main(String[] args) {
        BaseballCountModel model = new BaseballCountModel();
        BaseballCountView view = new BaseballCountView();
        new BaseballCountController(model, view);
    }
}
