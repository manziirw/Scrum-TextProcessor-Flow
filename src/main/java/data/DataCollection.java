package data;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataCollection {
    private List<TextData> dataList;

    public DataCollection() {
        dataList = new ArrayList<>();
    }

    public void addData(String data) {
        dataList.add(new TextData(data));
    }

    public void updateData(int index, String newData) {
        if (index >= 0 && index < dataList.size()) {
            dataList.get(index).setData(newData);
        }
    }

    public void deleteData(int index) {
        if (index >= 0 && index < dataList.size()) {
            dataList.remove(index);
        }
    }

    public List<TextData> getDataList() {
        return dataList;
    }


    public void setDataList(List<TextData> newDataList) {
        this.dataList = newDataList;
    }


    public void saveToFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Data");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (TextData data : dataList) {
                    writer.write(data.getData());
                    writer.newLine();
                }
                showAlert("Success", "Data successfully saved to file.");
            } catch (IOException e) {
                handleException("Error Saving File", "An error occurred while saving the file.", e);
            }
        }
    }

    public void loadFromFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Data File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                dataList.clear();  // Clear existing data to avoid duplicates
                String line;
                while ((line = reader.readLine()) != null) {
                    dataList.add(new TextData(line));
                }
                showAlert("Success", "Data successfully loaded from file.");
            } catch (IOException e) {
                handleException("Error Loading File", "An error occurred while loading the file.", e);
            }
        }
    }

    private void handleException(String title, String message, Exception e) {

        e.printStackTrace();

        // Show an alert dialog with the error message
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
