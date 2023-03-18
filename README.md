import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // 创建一个 HanoiPane 实例，作为程序的主界面
        HanoiPane pane = new HanoiPane();
        
        // 创建一个 Scene 实例，将 HanoiPane 加入其中
        Scene scene = new Scene(pane, 800, 600);
        
        // 设置主舞台的标题和场景
        primaryStage.setTitle("Hanoi Tower Demo");
        primaryStage.setScene(scene);
        
        // 显示主舞台
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
