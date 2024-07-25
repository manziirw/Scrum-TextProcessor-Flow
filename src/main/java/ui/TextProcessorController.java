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
            dataCollection.addData(data);
            updateListView();
            dataInput.clear();
        } else {
            showAlert("Input Error", "Please enter some data to add.");
        }
    }

    @FXML
    private void handleUpdateData() {
        TextData selectedData = dataList.getSelectionModel().getSelectedItem();
        String newData = dataInput.getText();

        if (selectedData != null && !newData.isEmpty()) {
            int selectedIndex = dataList.getSelectionModel().getSelectedIndex();
            dataCollection.updateData(selectedIndex, newData);
            updateListView();
            dataInput.clear();
        } else {
            showAlert("Update Error", "Please select an item and enter new data.");
        }
    }

    @FXML
    private void handleDeleteData() {
        int selectedIndex = dataList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            dataCollection.deleteData(selectedIndex);
            updateListView();
        } else {
            showAlert("Delete Error", "Please select an item to delete.");
        }
    }

    @FXML
    private void handleClearData() {
        dataInput.clear();
        dataList.getSelectionModel().clearSelection();
        dataCollection.getDataList().clear(); // Clear all data
        updateListView();
    }

    @FXML
    private void handleSaveData() {
        Stage stage = (Stage) dataList.getScene().getWindow();
        dataCollection.saveToFile(stage);
    }

    @FXML
    private void handleLoadData() {
        Stage stage = (Stage) dataList.getScene().getWindow();
        dataCollection.loadFromFile(stage);
        updateListView();
    }

    private void updateListView() {
        items.setAll(dataCollection.getDataList());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
