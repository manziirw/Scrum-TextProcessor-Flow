package data;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

    // Setter for the data list
    public void setDataList(List<TextData> newDataList) {
        this.dataList = newDataList;
    }

    /**
     * Saves the current data list to a specified file, overwriting any existing contents.
     * @param stage The stage to display the file chooser.
     */
    public void saveToFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Data");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            // Overwrite the file with the current data list
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (TextData data : dataList) {
                    writer.write(data.getData());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads data from the specified file and populates the data list.
     * @param stage The stage to display the file chooser.
     */
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
