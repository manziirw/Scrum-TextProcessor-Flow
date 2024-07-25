package ui;

import data.DataCollection;
import data.TextData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import regex.TextProcessor;
import javafx.stage.Stage;

public class TextProcessorController {

    @FXML
    private TextArea inputArea;

    @FXML
    private TextArea resultArea;

    @FXML
    private TextField dataInput;

    @FXML
    private TextField regexPattern;

    @FXML
    private TextField replacementText;

    @FXML
    private ListView<TextData> dataList;

    private DataCollection dataCollection;
    private ObservableList<TextData> items;

    @FXML
    public void initialize() {
        dataCollection = new DataCollection();
        items = FXCollections.observableArrayList();
        dataList.setItems(items);

        // Listen for selection changes
        dataList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                dataInput.setText(newValue.getData());
            }
        });
    }

    @FXML
    private void handleMatch() {
        String text = inputArea.getText();
        String pattern = regexPattern.getText();

        // Check if the text or pattern is empty
        if (text.isEmpty() || pattern.isEmpty()) {
            showAlert("Input Error", "Both text and regex pattern must be provided.");
            return; // Exit the method if there is an error
        }

        try {
            boolean isMatch = TextProcessor.matchPattern(text, pattern);
            resultArea.setText("Match Found: " + isMatch);
        } catch (Exception e) {
            showAlert("Error", "An error occurred while matching the pattern: " + e.getMessage());
        }
    }

    @FXML
    private void handleReplace() {
        String text = inputArea.getText();
        String pattern = regexPattern.getText();
        String replacement = replacementText.getText();

        // Check if the text, pattern, or replacement is empty
        if (text.isEmpty() || pattern.isEmpty() || replacement.isEmpty()) {
            showAlert("Input Error", "Text, text to be replaced, and replacement text must all be provided.");
            return; // Exit the method if there is an error
        }

        try {
            String replacedText = TextProcessor.replaceText(text, pattern, replacement);
            resultArea.setText("Replaced Text: " + replacedText);
        } catch (Exception e) {
            showAlert("Error", "An error occurred while replacing text: " + e.getMessage());
        }
    }

    @FXML
    private void handleClearText() {
        inputArea.clear();
        resultArea.clear();
        regexPattern.clear();
        replacementText.clear();
    }

    @FXML
    private void handleAddData() {
        String data = dataInput.getText();
        if (!data.isEmpty()) {
            try {
                dataCollection.addData(data);
                updateListView();
                dataInput.clear();
            } catch (Exception e) {
                showAlert("Error", "An error occurred while adding data: " + e.getMessage());
            }
        } else {
            showAlert("Input Error", "Please enter some data to add.");
        }
    }

    @FXML
    private void handleUpdateData() {
        TextData selectedData = dataList.getSelectionModel().getSelectedItem();
        String newData = dataInput.getText();

        if (selectedData != null && !newData.isEmpty()) {
            try {
                int selectedIndex = dataList.getSelectionModel().getSelectedIndex();
                dataCollection.updateData(selectedIndex, newData);
                updateListView();
                dataInput.clear();
            } catch (Exception e) {
                showAlert("Error", "An error occurred while updating data: " + e.getMessage());
            }
        } else {
            showAlert("Update Error", "Please select an item and enter new data.");
        }
    }

    @FXML
    private void handleDeleteData() {
        int selectedIndex = dataList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            try {
                dataCollection.deleteData(selectedIndex);
                updateListView();
            } catch (Exception e) {
                showAlert("Error", "An error occurred while deleting data: " + e.getMessage());
            }
        } else {
            showAlert("Delete Error", "Please select an item to delete.");
        }
    }

    @FXML
    private void handleClearData() {
        dataInput.clear();
        dataList.getSelectionModel().clearSelection();
        try {
            dataCollection.getDataList().clear();
            updateListView();
        } catch (Exception e) {
            showAlert("Error", "An error occurred while clearing data: " + e.getMessage());
        }
    }

    @FXML
    private void handleSaveData() {
        Stage stage = (Stage) dataList.getScene().getWindow();
        try {
            dataCollection.saveToFile(stage);
        } catch (Exception e) {
            showAlert("Save Error", "An error occurred while saving data: " + e.getMessage());
        }
    }

    @FXML
    private void handleLoadData() {
        Stage stage = (Stage) dataList.getScene().getWindow();
        try {
            dataCollection.loadFromFile(stage);
            updateListView();
        } catch (Exception e) {
            showAlert("Load Error", "An error occurred while loading data: " + e.getMessage());
        }
    }

    private void updateListView() {
        items.setAll(dataCollection.getDataList());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
