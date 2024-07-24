package data;

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
}
