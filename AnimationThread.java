import javafx.animation.AnimationTimer;

public class AnimationThread extends AnimationTimer {
    
    private HanoiPane hanoiPane;
    private long lastTime = 0;
    private int speed = 100; // 控制移动速度
    
    public AnimationThread(HanoiPane hanoiPane) {
        this.hanoiPane = hanoiPane;
    }

    @Override
    public void handle(long now) {
        if (lastTime == 0) {
            lastTime = now;
            return;
        }
        
        if ((now - lastTime) < (1000000000 / speed)) { // 控制帧率
            return;
        }
        
        hanoiPane.move(); // 移动盘子
        lastTime = now;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
