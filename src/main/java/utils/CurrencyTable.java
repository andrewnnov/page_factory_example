package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class CurrencyTable {

    protected WebElement tableElement;
    protected WebDriver chromeDriver;


    public CurrencyTable(WebElement tableElement, WebDriver chromeDriver) {
        this.tableElement = tableElement;
        this.chromeDriver = chromeDriver;
    }

    //получаем строки
    public List<WebElement> getRows() {
        List<WebElement> rows = tableElement.findElements(By.xpath("//tr"));
        rows.remove(0);
        return rows;
    }


    public List<List<WebElement>> getRowsWithColumns() {
        List<WebElement> rows = getRows();
        List<List<WebElement>> rowsWithColumns = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> rowWithColumns = row.findElements(By.xpath(".//td"));
            rowsWithColumns.add(rowWithColumns);
        }
        return rowsWithColumns;
    }

    public String getValueFromCell(int rowNumber, int columnNumber) {
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        WebElement cell = rowsWithColumns.get(rowNumber - 1).get(columnNumber - 1);
        return cell.getText();
    }
}
