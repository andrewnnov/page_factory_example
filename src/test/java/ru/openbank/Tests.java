package ru.openbank;


import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.PageFactoryBankOpen;
import utils.CurrencyTable;


import static utils.ConvertToDouble.convertStringToDouble;


public class Tests extends BaseTest {

    @Feature("Проверка что курс продажи больше курса покупки_v1")
    @DisplayName("Проверка что курс продажи больше курса покупки_v1")
    @ParameterizedTest(name="{displayName} {arguments}")
    @CsvSource({"Открытие, https://www.open.ru"})
    public void compareCourseUsdAndEuroTest(String keyWord, String result) {
        chromeDriver.get("https://www.google.com/");
        PageFactoryBankOpen pageFactoryBankOpen = PageFactory.initElements(chromeDriver, PageFactoryBankOpen.class);
        pageFactoryBankOpen.find(keyWord);
        Assertions.assertTrue(pageFactoryBankOpen.getResultSearch().stream().anyMatch(x->x.getText()
                .contains(result)), "Ссылки на банк \"Открытие\" не найдены");

        pageFactoryBankOpen.chooseTheCurrentLink(result);
        pageFactoryBankOpen.switchToWindow("Частным клиентам | Банк Открытие");

        String usdAmountBuy = pageFactoryBankOpen.getUsdAmountBuy().getText().replace(",", ".");
        String usdAmountSell = pageFactoryBankOpen.getUsdAmountSell().getText().replace(",", ".");
        String euroAmountBuy = pageFactoryBankOpen.getEuroAmountBuy().getText().replace(",", ".");
        String euroAmountSell = pageFactoryBankOpen.getEuroAmountSell().getText().replace(",", ".");

        Assertions.
                assertTrue(convertStringToDouble(usdAmountBuy)
                        - convertStringToDouble(usdAmountSell) < 0, "Курс продажи доллара меньше курса покупки");
        Assertions.
                assertTrue(convertStringToDouble(euroAmountBuy)
                        - convertStringToDouble(euroAmountSell) < 0, "Курс продажи евро меньше курса покупки");
    }

    @Feature("Проверка что курс продажи больше курса покупки_v2")
    @DisplayName("Проверка что курс продажи больше курса покупки_v2")
    @ParameterizedTest(name="{displayName} {arguments}")
    @CsvSource({"Открытие, https://www.open.ru"})
    public void compareCourseUsdAndEuroWithTableTest(String keyWord, String result) {
        chromeDriver.get("https://www.google.com/");
        PageFactoryBankOpen pageFactoryBankOpen = PageFactory.initElements(chromeDriver, PageFactoryBankOpen.class);
        pageFactoryBankOpen.find(keyWord);
        Assertions.assertTrue(pageFactoryBankOpen.getResultSearch().stream().anyMatch(x->x.getText()
                .contains(result)), "Ссылки на банк \"Открытие\" не найдены");

        pageFactoryBankOpen.chooseTheCurrentLink(result);
        pageFactoryBankOpen.switchToWindow("Частным клиентам | Банк Открытие");

        WebElement webElementTable = pageFactoryBankOpen.getWebElementTable();
        CurrencyTable currencyTable = new CurrencyTable(webElementTable, chromeDriver);

        String usdAmountBuy = currencyTable.getValueFromCell(1, 2).replace(",", ".");
        String usdAmountSell = currencyTable.getValueFromCell(1, 4).replace(",", ".");
        String euroAmountBuy = currencyTable.getValueFromCell(2, 2).replace(",", ".");
        String euroAmountSell = currencyTable.getValueFromCell(2, 4).replace(",", ".");


        Assertions.
                assertTrue(convertStringToDouble(usdAmountBuy)
                        - convertStringToDouble(usdAmountSell) < 0, "Курс продажи доллара меньше курса покупки");
        Assertions.
                assertTrue(convertStringToDouble(euroAmountBuy)
                        - convertStringToDouble(euroAmountSell) < 0, "Курс продажи евро меньше курса покупки");
    }

}
