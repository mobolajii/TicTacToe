package com.example.tic_tac_toe;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Tic extends Application{


    private boolean round=true;
    private  int countX = 0;
    private int countO = 0;


    /*
        To get the children
     */

    private String getNodes(int row, int col, GridPane gridPane){

        Button button =  null;
        ObservableList<Node>children = gridPane.getChildren();

        for(Node node:children){
            if(GridPane.getColumnIndex(node) ==col && GridPane.getRowIndex(node)==row){
                button = (Button) node;
                break;
            }
        }

        return button.getText();
    }

    //method for winning positions
    private boolean isWon(String s, GridPane gridPane){

        for (int i = 0; i < 3; i++) {
            if(getNodes(0,i, gridPane).equals(s) &&getNodes(1,i, gridPane).equals(s)
                    && getNodes(2,i, gridPane).equals(s)){
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if(getNodes(j,0, gridPane).equals(s) &&getNodes(j,1, gridPane).equals(s)
                    && getNodes(j,2, gridPane).equals(s)){
                return true;
            }
        }

        if(getNodes(0,0, gridPane).equals(s) &&getNodes(1,1, gridPane).equals(s)
                && getNodes(2,2, gridPane).equals(s)){
            return true;
        }

        if(getNodes(0,2, gridPane).equals(s) &&getNodes(1,1, gridPane).equals(s)
                && getNodes(2,0, gridPane).equals(s)){
            return true;
        }

        return false;
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(100, 100);
                button.setFont(Font.font(20));

                button.setOnMouseClicked(mouseEvent-> {

                    if(round && button.getText().equals("")){
                        button.setText("X");
                        round = false;
                        button.setTextFill(Color.RED);
                        countX++;
                    }if(!round && button.getText().equals("")){
                        button.setText("O");
                        button.setTextFill(Color.BLUE);
                        round = true;
                        countO++;

                    }

                    if(isWon("X", gridPane)){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("X won this round!");
                        alert.show();

                    }

                    if(isWon("O", gridPane)){
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("O won this round!");
                        alert.show();

                    }

                    if(!isWon("X", gridPane) && !isWon("O", gridPane)  ){
                        if((countX==4 && countO==5) || (countO==4 && countX==5)){
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setContentText("It's a draw");
                            alert.show();
                        }
                    }

                });

                gridPane.add(button, j, i);
            }
        }


        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }
}
