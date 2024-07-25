package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.List;

public class DataCollectionTest {

    private DataCollection dataCollection;

    @BeforeEach
    public void setUp() {
        dataCollection = new DataCollection();
    }

    @Test
    public void testAddData() {
        dataCollection.addData("Sample Data");
        List<TextData> dataList = dataCollection.getDataList();
        assertEquals(1, dataList.size());
        assertEquals("Sample Data", dataList.get(0).getData());
    }

    @Test
    public void testUpdateData() {
        dataCollection.addData("Old Data");
        dataCollection.updateData(0, "New Data");
        List<TextData> dataList = dataCollection.getDataList();
        assertEquals(1, dataList.size());
        assertEquals("New Data", dataList.get(0).getData());
    }

    @Test
    public void testDeleteData() {
        dataCollection.addData("Data to be deleted");
        dataCollection.deleteData(0);
        List<TextData> dataList = dataCollection.getDataList();
        assertTrue(dataList.isEmpty());
    }

    @Test
    public void testGetDataList() {
        dataCollection.addData("First Data");
        dataCollection.addData("Second Data");
        List<TextData> dataList = dataCollection.getDataList();
        assertEquals(2, dataList.size());
        assertEquals("First Data", dataList.get(0).getData());
        assertEquals("Second Data", dataList.get(1).getData());
    }

    @Test
    public void testSetDataList() {
        List<TextData> newDataList = List.of(new TextData("Data 1"), new TextData("Data 2"));
        dataCollection.setDataList(newDataList);
        List<TextData> dataList = dataCollection.getDataList();
        assertEquals(2, dataList.size());
        assertEquals("Data 1", dataList.get(0).getData());
        assertEquals("Data 2", dataList.get(1).getData());
    }

}
