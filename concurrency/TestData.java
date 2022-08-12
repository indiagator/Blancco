package concurrency;

import javax.xml.transform.Templates;

public class TestData {

    private int[] testDataArray;

    TestData()
    {
        this.testDataArray = new int[5];
        for(int i=0; i<5; i++)
        {
            this.testDataArray[i] = i*5;
        }

    }

    public void setTestDataArray(int[] testDataArray) {
        this.testDataArray = testDataArray;
    }

    public int[] getTestDataArray() {
        return testDataArray;
    }

    public int getElementByIndex(int index)
    {
        return this.testDataArray[index];
    }

    public void setElementByIndex(int index, int value)
    {
        this.testDataArray[index] = value;
    }

    @Override
    public String toString() 
    {

        String tenpString = " ";

        for(int i = 0; i <5 ; i++)
        {
            tenpString = tenpString + this.testDataArray[i] + " ";
        }

        return tenpString;
    }
    
}
