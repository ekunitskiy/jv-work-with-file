package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final int OPERATION_TYPE_POSITION = 0;
    public static final int AMOUNT_POSITION = 1;

    private StringBuilder stringBuilder = new StringBuilder();

    private int supply;
    private int buy;
    private int result;

    public void getStatistic(String fromFileName, String toFileName) {
        supply = 0;
        buy = 0;
        result = 0;
        stringBuilder = new StringBuilder();
        String[] readFromFile = readDataFromFile(fromFileName);
        for (int i = 0; i < readFromFile.length; i++) {
            String[] splitedString = readFromFile[i].split(",");
            switch (splitedString[OPERATION_TYPE_POSITION]) {
                case ("supply"):
                    supply += Integer.parseInt(splitedString[AMOUNT_POSITION]);
                    break;

                case ("buy"):
                    buy += Integer.parseInt(splitedString[AMOUNT_POSITION]);
                    break;
                default:
                    throw new RuntimeException("Array is empty");
            }
        }
        result = supply - buy;

        stringBuilder = new StringBuilder();
        stringBuilder.append("supply,").append(supply).append(" ")
                .append(System.lineSeparator())
                .append("buy,").append(buy).append(" ")
                .append(System.lineSeparator())
                .append("result,").append(result)
                .append(System.lineSeparator());

        String[] resultArray = stringBuilder.toString().split(" ");
        writeDataToFile(toFileName, resultArray);
    }

    public String[] readDataFromFile(String fromFileName) {
        stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String readLine = reader.readLine();
            while (readLine != null) {
                stringBuilder.append(readLine).append(" ");
                readLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        if (!stringBuilder.toString().isEmpty()) {
            String[] readData = stringBuilder.toString().split(" ");
            return readData;
        } else {
            return new String[]{};
        }
    }

    public void writeDataToFile(String toFileName, String[] dataForWriting) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String data : dataForWriting) {
                if (!data.isEmpty()) {
                    bufferedWriter.write(data);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    public void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    public int getSupply() {
        return supply;
    }

    public void setSupply(int supply) {
        this.supply = supply;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
