import javax.swing.*;
import java.awt.*;

// View（表示）クラス
public class BaseballCountView {
    public JLabel ballLabel;
    public JLabel strikeLabel;
    public JLabel outLabel;
    public JButton ballButton;
    public JButton strikeButton;
    public JButton outButton;
    public JTextArea gameInfoTextArea;

    private static final int ICON_SIZE = 20;

    public BaseballCountView() {
        JFrame frame = new JFrame("ベースボールカウンター");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 閉じるボタンでプログラム終了
        frame.setSize(400, 300); // ウィンドウサイズ
        frame.setLayout(new BorderLayout());

        // 上部パネル（カウント表示用）
        JPanel topPanel = new JPanel(new GridLayout(3, 1)); // 3行1列
        topPanel.setBackground(Color.BLACK); // 背景色
        ballLabel = new JLabel("B: ");
        ballLabel.setForeground(Color.WHITE);
        strikeLabel = new JLabel("S: ");
        strikeLabel.setForeground(Color.WHITE);
        outLabel = new JLabel("O: ");
        outLabel.setForeground(Color.WHITE);

        topPanel.add(ballLabel);
        topPanel.add(strikeLabel);
        topPanel.add(outLabel);
        frame.add(topPanel, BorderLayout.NORTH);

        // 中央パネル（ゲーム情報表示用）
        gameInfoTextArea = new JTextArea();
        gameInfoTextArea.setEditable(false);
        frame.add(new JScrollPane(gameInfoTextArea), BorderLayout.CENTER);

        // 下部パネル（ボタン）
        JPanel bottomPanel = new JPanel(new FlowLayout());
        ballButton = new JButton("ボール");
        strikeButton = new JButton("ストライク");
        outButton = new JButton("アウト");
        bottomPanel.add(ballButton);
        bottomPanel.add(strikeButton);
        bottomPanel.add(outButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void updateLabels(int balls, int strikes, int outs) {
        ballLabel.setText(createHtmlLabel("B", balls, BaseballCountModel.MAX_BALLS, "green"));
        strikeLabel.setText(createHtmlLabel("S", strikes, BaseballCountModel.MAX_STRIKES, "yellow"));
        outLabel.setText(createHtmlLabel("O", outs, BaseballCountModel.MAX_OUTS, "red"));
    }

    private String createHtmlLabel(String label, int count, int max, String color) {
        StringBuilder result = new StringBuilder("<html><b>").append(label).append(": </b>");
        for (int i = 0; i < max; i++) {
            if (i < count) {
                result.append("<span style='color:").append(color).append(";'>■</span> ");
            } else {
                result.append("□ ");
            }
        }
        result.append("</html>");
        return result.toString();
    }
}
