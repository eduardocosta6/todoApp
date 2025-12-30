import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // Create a vertical container (VBox)
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // Create title label
        Label title = new Label("My To-Do List");

        // Create input field for new task
        TextField taskInput = new TextField();
        taskInput.setPromptText("Enter a new task...");

        // Create button to add task
        Button addButton = new Button("Add Task");

        // Create remove button
        Button removeButton = new Button("Remove Task");

        // Create list to display tasks with checkboxes
        ListView<CheckBox> taskList = new ListView<>();
        taskList.setPrefHeight(300);

        // Add button action
        addButton.setOnAction(e -> {
            String task = taskInput.getText();
            if (!task.isEmpty()) {
                CheckBox checkBox = new CheckBox(task);

                checkBox.setOnAction(event -> {
                    if (checkBox.isSelected()) {
                        checkBox.setDisable(true);
                    }
                });

                taskList.getItems().add(checkBox);
                taskInput.clear();
            }
        });

        // Remove button action
        removeButton.setOnAction(e -> {
            int selectedIndex = taskList.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                taskList.getItems().remove(selectedIndex);
            }
        });

        // Allow Enter key to add task
        taskInput.setOnAction(e -> {
            addButton.fire();
        });

        // Create horizontal box for buttons
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, removeButton);

        // Add all components to the container
        root.getChildren().addAll(title, taskInput, buttonBox, taskList);

        // Create scene with the layout
        Scene scene = new Scene(root, 500, 600);

        // Set up the window (Stage)
        stage.setTitle("To-Do App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}