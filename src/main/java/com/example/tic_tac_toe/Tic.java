package com.example.tic_tac_toe;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Tic extends Application {
    private int count = 0;
    GridPane gridPane = new GridPane();

    public String getNodes(final int row, final int column, GridPane gridPane) {
        Button button = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                button= (Button) node;
                break;
            }
        }

        return button.getText();
    }

    public boolean isWon(String c, GridPane gridPane) {
        for (int row = 0; row < 3; row++) {
            if (getNodes(0, row, gridPane).equals(c) && getNodes(1, row, gridPane).equals(c)
                    && getNodes(2, row, gridPane).equals(c)) {
                return true;
            }
        }
        for (int col = 0; col < 3; col++) {
            if (getNodes(col, 0, gridPane).equals(c) && getNodes(col, 1, gridPane).equals(c)
                    && getNodes(col, 2, gridPane).equals(c)) {
                return true;
            }
        }

/////////////////// Diagonals /////////////////////////////////////////////////////
        if (getNodes (0, 0, gridPane).equals(c)
                && getNodes(1, 1, gridPane).equals(c)
                && getNodes(2, 2, gridPane).equals(c)) {
            return true;
        }
        if (getNodes(0, 2, gridPane).equals(c)
                && getNodes(1, 1, gridPane).equals(c)
                && getNodes(2, 0, gridPane).equals(c)) {
            return true;
        }
        return false;

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(100, 100);

                gridPane.add(button, j, i);

                button.setOnMouseClicked(mouseEvent -> {
                    count++;
                    if (count % 2 == 0) {
                        if (button.getText().equals("")) {
                            button.setText("O");
                            button.setFont(Font.font(20));
                        }

                    } else {
                        if (button.getText().equals("")) {
                            button.setText("X");
                            button.setFont(Font.font(20));
                        }
                    }

                    if (isWon("X", gridPane)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("X Won");
                        alert.show();
                    }

                    if (isWon("O", gridPane)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("O Won");
                        alert.show();
                    }
                });
            }
        }


        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }
}
