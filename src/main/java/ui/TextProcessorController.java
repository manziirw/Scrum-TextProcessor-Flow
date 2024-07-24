package ui;


import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import regex.TextProcessor;

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
    private ListView<String> dataList;

    private ObservableList<String> items;

    public void initialize() {

        items = FXCollections.observableArrayList();
        dataList.setItems(items);
    }

    @FXML
    private void handleMatch() {
        String text = inputArea.getText();
        String pattern = regexPattern.getText();
        boolean isMatch = TextProcessor.matchPattern(text, pattern);
        resultArea.setText("Match Found: " + isMatch);
    }

    @FXML
    private void handleReplace() {
        String text = inputArea.getText();
        String pattern = regexPattern.getText();
        String replacement = replacementText.getText();
        String replacedText = TextProcessor.replaceText(text, pattern, replacement);
        resultArea.setText("Replaced Text: " + replacedText);
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
            items.add(data);
            dataInput.clear();
        } else {
            showAlert("Input Error", "Please enter some data to add.");
        }
    }

    @FXML
    private void handleUpdateData() {
        String selectedData = dataList.getSelectionModel().getSelectedItem();
        String newData = dataInput.getText();

        if (selectedData != null && !newData.isEmpty()) {
            int selectedIndex = dataList.getSelectionModel().getSelectedIndex();
            items.set(selectedIndex, newData);
            dataInput.clear();
        } else {
            showAlert("Update Error", "Please select an item and enter new data.");
        }
    }

    @FXML
    private void handleDeleteData() {
        String selectedData = dataList.getSelectionModel().getSelectedItem();
        if (selectedData != null) {
            items.remove(selectedData);
        } else {
            showAlert("Delete Error", "Please select an item to delete.");
        }
    }

    @FXML
    private void handleClearData() {
        dataInput.clear();
        dataList.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

