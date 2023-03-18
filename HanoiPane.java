import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class HanoiPane extends Pane {
    
    // 创建一个 HanoiTower 实例
    private HanoiTower tower = new HanoiTower(3);
    
    // 创建三个 Disk 实例
    private Disk disk1 = new Disk(60, 20, Color.RED);
    private Disk disk2 = new Disk(80, 20, Color.BLUE);
    private Disk disk3 = new Disk(100, 20, Color.GREEN);
    
    // 创建一个 AnimationThread 实例
    private AnimationThread animationThread;
    
    public HanoiPane() {
        // 设置 HanoiPane 的背景颜色
        this.setStyle("-fx-background-color: #FFFFFF;");
        
        // 添加三个 Disk 到 HanoiPane 中
        this.getChildren().addAll(disk1, disk2, disk3);
        
        // 将三个 Disk 放到起始位置
        disk1.setTranslateX(100);
        disk1.setTranslateY(400);
        disk2.setTranslateX(100);
        disk2.setTranslateY(420);
        disk3.setTranslateX(100);
        disk3.setTranslateY(440);
        
        // 创建 AnimationThread 实例
        animationThread = new AnimationThread(disk1, disk2, disk3);
        
        // 设置 HanoiPane 的点击事件
        this.setOnMouseClicked(event -> {
            // 获取用户点击的位置
            double x = event.getX();
            double y = event.getY();
            
            // 如果用户点击了第一根柱子，尝试将盘子从第一根柱子移动到其他柱子
            if (x >= 80 && x <= 120 && y >= 200 && y <= 400) {
                if (tower.move(0, 1)) {
                    // 如果移动成功，启动 AnimationThread 来播放动画
                    animationThread.play(tower.getLastMove());
                } else {
                    // 如果移动失败，播放错误音效
                    SoundPlayer.playErrorSound();
                }
            }
            // 如果用户点击了第二根柱子，尝试将盘子从第二根柱子移动到其他柱子
        else if (x >= 280 && x <= 320 && y >= 200 && y <= 400) {
            if (tower.move(1, 0) || tower.move(1, 2)) {
                // 如果移动成功，启动 AnimationThread 来播放动画
                animationThread.play(tower.getLastMove());
            } else {
                // 如果移动失败，播放错误音效
                SoundPlayer.playErrorSound();
            }
        }
        // 如果用户点击了第三根柱子，尝试将盘子从第三根柱子移动到其他柱子
        else if (x >= 480 && x <= 520 && y >= 200 && y <= 400) {
            if (tower.move(2, 1)) {
                // 如果移动成功，启动 AnimationThread 来播放动画
                animationThread.play(tower.getLastMove());
            } else {
                // 如果移动失败，播放错误音效
                SoundPlayer.playErrorSound();
            }
        }
    });
}
