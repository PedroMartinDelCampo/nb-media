/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pmg.mediasupport.mp3support;

import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javax.swing.JFrame;

/**
 *
 * @author Pedro
 */
public class MP3Player extends JFrame {
    
    private final Media media;
    private final MediaPlayer player;
    
    private final JFXPanel pane;
    private Scene scene;
    private BorderPane root;
    
    private Slider timeSlider;
    private Label timeSliderLabel;
    private Label currentTimeLabel;
    private Slider volumeSlider;
    private Label volumeSliderLabel;
    private Label currentVolumeLabel;
    private Button playPauseButton;
    private Button stopButton;
    
    private boolean isPlaying;
    
    public MP3Player(String url){
        // JFXPane constructor starts FXThread
        pane = new JFXPanel();
        media = new Media(url);
        player = new MediaPlayer(media);
        init(url.replace("file:///", "").replace("%20", " "));
    }

    private void init(String title){
        setChildren();
        root = new BorderPane();
        Node top = top();
        Node left = left();
        Node right = right();
        Node bottom = bottom();
        top.setLayoutX(0);
        top.setLayoutY(0);
        left.setLayoutX(0);
        left.setLayoutY(100);
        right.setLayoutX(200);
        right.setLayoutY(100);
        bottom.setLayoutX(0);
        bottom.setLayoutY(200);
        root.getChildren().addAll(top, left, right, bottom);
        scene = new Scene(root);
        pane.setScene(scene);
        getContentPane().add(pane);
        setSize(400, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle(title);
    }
    
    private void setChildren(){
        timeSlider = new Slider(0, media.getDuration().toMillis(), 0);
        timeSliderLabel = new Label("Time: ");
        currentTimeLabel = new Label("00:00:00");
        listenTime();
        volumeSlider = new Slider(0, 1.0, player.getVolume());
        volumeSliderLabel = new Label("Volume: ");
        currentVolumeLabel = new Label("1.0");
        listenVolume();
        playPauseButton = new Button("|>");
        listenPlayPause();
        stopButton = new Button("[]");
        listenStop();
    }
    
    private void listenTime(){
        // Update player time
        timeSlider.valueProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
                        -> {
                    player.seek(Duration.millis(newValue.doubleValue()));
        });
        // Update slider
        player.currentTimeProperty().addListener(
                (ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) 
                        -> {
                    timeSlider.setValue(newValue.toMillis());
        });
    }

    private void listenVolume() {
        volumeSlider.valueProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
                        -> {
                    player.setVolume(newValue.doubleValue());
        });
    }

    private void listenPlayPause() {
        playPauseButton.setOnAction((ActionEvent event) -> {
            if (isPlaying){
                player.pause();
                isPlaying = false;
                playPauseButton.setText("|>");
            } else {
                player.play();
                isPlaying = true;
                playPauseButton.setText("||");
            }
        });
    }

    private void listenStop() {
        stopButton.setOnAction((ActionEvent event) -> {
            if (isPlaying){
                player.stop();
                isPlaying = false;
                playPauseButton.setText("|>");
            }
        });
    }

    private Node top() {
        timeSlider.setLayoutX(50);
        currentTimeLabel.setLayoutX(50);
        currentTimeLabel.setLayoutY(40);
        return new AnchorPane(timeSlider, timeSliderLabel, currentTimeLabel);
    }

    private Node left() {
        return new AnchorPane(playPauseButton);
    }

    private Node right() {
        return new AnchorPane(stopButton);
    }

    private Node bottom() {
        volumeSlider.setLayoutX(100);
        currentVolumeLabel.setLayoutX(100);
        currentVolumeLabel.setLayoutY(40);
        return new AnchorPane(volumeSlider, volumeSliderLabel, currentVolumeLabel);
    }
    
}
