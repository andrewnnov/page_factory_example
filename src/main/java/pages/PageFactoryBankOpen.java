package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageFactoryBankOpen {

    private WebDriver chromeDriver;

    @FindBy(how = How.XPATH, using = "//input[@name='q']")
    protected WebElement searchField;

    @FindBy(how = How.XPATH, using = "//*[@class='aajZCb']//*[@name='btnK']")
    protected WebElement searchBtn;

    @FindBy(how = How.XPATH, using = "//div[@class='TbwUpd NJjxre']")
    protected List<WebElement> resultSearch;

    @FindBy(how = How.XPATH, using = "//table//tr[2]//td[2]")
    protected WebElement usdAmountBuy;

    @FindBy(how = How.XPATH, using = "//table//tr[2]//td[4]")
    protected WebElement usdAmountSell;

    @FindBy(how = How.XPATH, using = "//table//tr[3]//td[2]")
    protected WebElement euroAmountBuy;

    @FindBy(how = How.XPATH, using = "//table//tr[3]//td[4]")
    protected WebElement euroAmountSell;

    @FindBy(how = How.XPATH, using = "//table")
    protected WebElement webElementTable;

    public WebElement getUsdAmountBuy() {
        return usdAmountBuy;
    }

    public WebElement getWebElementTable() {
        return webElementTable;
    }

    public WebElement getUsdAmountSell() {
        return usdAmountSell;
    }

    public WebElement getEuroAmountBuy() {
        return euroAmountBuy;
    }

    public WebElement getEuroAmountSell() {
        return euroAmountSell;
    }

    public PageFactoryBankOpen(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public List<WebElement> getResultSearch() {
        return resultSearch;
    }

    public void find(String keysFind) {
        searchField.click();
        searchField.sendKeys(keysFind);
        searchBtn.click();
    }

    public void switchToWindow(String titleName) {
        for(String windowHandle : chromeDriver.getWindowHandles()) {
            if(windowHandle.contains(titleName)) {
                chromeDriver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void chooseTheCurrentLink(String link) {
        for (WebElement el: getResultSearch()) {
            if(el.getText().contains(link)) {
                el.click();
                break;
            }
        }
    }


}
