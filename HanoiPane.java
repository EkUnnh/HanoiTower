// 导入 JavaFX 相关类
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// 定义类 HanoiPane，继承自 Pane 类
public class HanoiPane extends Pane {
    // 定义私有成员变量：盘子数量、汉诺塔对象、矩形数组
    private int numDiscs;
    private HanoiTower hanoiTower;
    private Rectangle[] discs;

    // 定义常量
    private static final double BASE_WIDTH = 300;
    private static final double BASE_HEIGHT = 10;
    private static final double DISC_WIDTH_DIFF = 50;
    private static final double DISC_HEIGHT = 10;
    private static final double DISC_X_OFFSET = 10;
    private static final double DISC_Y_OFFSET = 5;

    // 定义构造函数，接收盘子数量、窗口高度和汉诺塔对象作为参数
    public HanoiPane(int numDiscs, int windowHeight, HanoiTower hanoiTower) {
        this.numDiscs = numDiscs;
        this.hanoiTower = new HanoiTower(numDiscs);
        setupPane(); // 调用 setupPane() 方法初始化界面
    }

    // 定义私有方法，用于初始化界面
    private void setupPane() {
        // 设置首选宽度和高度、背景颜色
        setPrefWidth(BASE_WIDTH + numDiscs * DISC_WIDTH_DIFF);
        setPrefHeight(numDiscs * 50);
        setStyle("-fx-background-color: #f0f0f0");

        // 创建底座矩形并添加到 Pane 中
        double baseX = getWidth() / 2 - BASE_WIDTH / 2;
        double baseY = getHeight() - BASE_HEIGHT;
        Rectangle base = new Rectangle(baseX, baseY, BASE_WIDTH, BASE_HEIGHT);
        base.setFill(Color.BROWN);
        getChildren().add(base);

        // 创建矩形数组 discs，用于表示盘子，添加到 Pane 中
        discs = new Rectangle[numDiscs];
        for (int i = 0; i < numDiscs; i++) {
            double width = BASE_WIDTH - i * DISC_WIDTH_DIFF;
            double height = DISC_HEIGHT;
            double x = baseX + (BASE_WIDTH - width) / 2;
            double y = baseY - (i + 1) * height - i * DISC_Y_OFFSET;
            Rectangle disc = new Rectangle(x, y, width, height);
            disc.setFill(Color.rgb(255 - i * (255 / numDiscs), i * (255 / numDiscs), 0));
            getChildren().add(disc);
            discs[i] = disc;
        }

        // 更新盘子位置
        updateDiscsPosition(0, 0, 0);
    }
    // 定义私有方法，用于更新盘子位置
    private void updateDiscsPosition(int start, int end, double fraction) {
        // 获取汉诺塔的塔数组
        int[] towers = hanoiTower.getTowers();
        // 遍历每个圆盘
        for (int i = 0; i < numDiscs; i++) {
            // 获取当前圆盘
            Rectangle disc = discs[i];
            // 获取圆盘宽度和高度
            double width = disc.getWidth();
            double height = disc.getHeight();
            // 根据当前圆盘所在塔的编号计算圆盘的x坐标
            double x = getWidth() / 6 * (towers[i] + 1) - width / 2;
            // 根据当前圆盘的编号计算圆盘的y坐标
            double y = getHeight() - (i + 1) * height - i * DISC_Y_OFFSET;
            // 如果当前圆盘在起始塔中，则更新圆盘的x坐标，使其随着时间的推移逐渐移动
            if (towers[i] == start) {
                x += getWidth() / 6 * fraction;
                // 如果当前圆盘在目标塔中，则更新圆盘的x坐标，使其逐渐移动到目标塔
            } else if (towers[i] == end) {
                x += getWidth() / 6 * (1 - fraction);
            }
            // 更新圆盘的位置
            disc.setX(x);
            disc.setY(y);
        }
    }

    public void moveDiscs(int start, int end) {
        // 创建一个动画线程，该线程负责控制圆盘的移动
        AnimationThread animationThread = new AnimationThread(
                // 在动画线程中更新圆盘的位置
                fraction -> updateDiscsPosition(start, end, fraction),
                // 在动画线程中移动汉诺塔
                () -> hanoiTower.move(start, end)
        );
        // 启动动画线程
        animationThread.start();
    }

}
