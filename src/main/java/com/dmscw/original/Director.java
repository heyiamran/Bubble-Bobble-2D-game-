package com.dmscw.original;


import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Director {
    public static final int UNIT_SIZE = 20;
    public static final int WIDTH = 48;
    public static final int HEIGHT = 32;

    private Stage stage;
    private static Director instance = new Director();

    public static Director getInstance() {
        return instance;
    }
    public void init(Stage stage) {
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root , WIDTH*UNIT_SIZE, HEIGHT*UNIT_SIZE);
        stage.setTitle("BubbleBobble");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setWidth(WIDTH * UNIT_SIZE);
        stage.setHeight(HEIGHT* UNIT_SIZE);
        this.stage = stage;
        //toIndex();
        stage.show();
    }

    public void toIndex() {
       //Index.load(stage);
    }
    public void gameStart() {
        //interactableWorldFx.init(stage);//初始化调用场景
    }
//    public void gameOver(boolean success) {
//        String sound = success? "/sound/success.wav" : "/sound/aiyouwodemaya.mp3";
//        SoundEffect.play(sound);
//        gameScene.clear(stage);
//        GameOver.load(stage, success);
//    }
//


}

