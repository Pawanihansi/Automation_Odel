package cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static dataProviders.configFileReader.getPropertyValue;
import static dataProviders.datasetFileReader.getDatasetValue;
import static dataProviders.repositoryFileReader.constructElement;
import static dataProviders.repositoryFileReader.findElementRepo;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.Keys.valueOf;
import static org.openqa.selenium.Keys.values;

public class WebSteps {

    WebDriver driver;
    private Scenario scenario;
    private double variable1;
    private double variable2;

    private double Product1;
    private double Product2;
    private double Product3;
    private Object cartTotal;
    int itemsAddedToCart = 0;

    @Before
    public void setup(Scenario scenario) {
        String browser = getPropertyValue("browser");
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                if(getPropertyValue("headless").equals("true")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");
                    driver = new ChromeDriver(options);
                } else if(getPropertyValue("headless").equals("false")){
                    driver = new ChromeDriver();
                }

            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                if(getPropertyValue("headless").equals("true")) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--headless=new");
                    driver = new FirefoxDriver(options);
                } else if(getPropertyValue("headless").equals("false")){
                    driver = new FirefoxDriver();
                }

            }
            default -> throw new RuntimeException("Browser not supported");
        }
        scenario.log("Scenario expected on " + browser + " browser");
        this.scenario = scenario;


    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        //driver.quit();
        //Take Screenshot and attached to the report
        if (scenario.isFailed()) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
            scenario.attach(fileContent, "image/png", "Failed step screenshot");
        }


    }

    @Given("I have opened the system")
    public void iHaveOpenedTheSystem() {
        driver.get(getPropertyValue("baseURL"));

    }

    @When("I type {string} to the {string}")
    public void iTypeToThe(String text, String locator) {
        By element = constructElement(findElementRepo(locator));
        //Get value from dataset
        String value = getDatasetValue(text);
        scenario.log(text + " : " + value);
        driver.findElement(element).sendKeys(value);


    }

    @And("I click on {string}")
    public void iClickOn(String locator) {
        By element = constructElement(findElementRepo(locator));
        driver.findElement(element).click();

    }

    @Then("I see the exact {string} display on {string}")
    public void iSeeTheExactDisplayOn(String text, String locator) {
        By element = constructElement(findElementRepo(locator));
        //Get value from dataset
        String value = getDatasetValue(text);
        scenario.log(text + " : " + value);
        assertEquals("Assert field text is not match", value, driver.findElement(element).getText());
    }

    @Then("I see the exact {string} display on validation message\\(HTML form validation) {string}")
    public void iSeeTheExactDisplayOnValidationMessageHTMLFormValidation(String text, String locator) {
        By element = constructElement(findElementRepo(locator));

        //Get value from dataset
        String value = getDatasetValue(text);
        scenario.log(text + " : " + value);

        WebElement field = driver.findElement(element);
        String validationMessage = field.getAttribute("validationMessage");
        assertEquals("Assert field text is not match", value, field.getAttribute("validationMessage"));
    }

    @And("I click on {string} with {string} as text")
    public void iClickOnWithAsText(String locator, String text) {
        By elementLocated = constructElement(findElementRepo(locator));

        //Get value from dataset
        String value = getDatasetValue(text);
        scenario.log(text + " : " + value);

        //Create element collection
        List<WebElement> elements = driver.findElements(elementLocated);
        //Iterate through list
        for (WebElement element : elements) {
            if (element.getText().equals(value)) {
                element.click();
            }

        }

    }

    @And("I wait few seconds")
    public void iWaitFewSeconds() throws InterruptedException {
        Thread.sleep(10000);
    }



    @And("Press enter {string}")
    public void pressEnter(String locator) {
        By element = constructElement(findElementRepo(locator));
        driver.findElement(element).sendKeys(Keys.ENTER);
    }

    @And("I type {string} to the {string} with Press Enter")
    public void iTypeToTheWithPressEnter(String text, String locator) {
        By element = constructElement(findElementRepo(locator));
        //Get value from dataset
        String value = getDatasetValue(text);
        scenario.log(text + " : " + value);
        driver.findElement(element).sendKeys(value);
       // driver.findElement(element).sendKeys(ENTER);

    }

    @And("Scroll down")
    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,950)", "");
    }

    @And("I select value {string}")
    public void iSelectValue(String locator) {
        By element = constructElement(findElementRepo(locator));

        WebElement staticDropdown = driver.findElement(element);

        Select dropdown = new Select(staticDropdown);
        //Initialize the driver
        //dropdown.selectByIndex(3);
// select dropdown item using it's index value
        System.out.println(dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("Pieces (Pc(s))");


// select dropdown item using its text
//        System.out.println(dropdown.getFirstSelectedOption().getText());
//        dropdown.selectByValue("INR");
//// select dropdown item using value attribute
//        System.out.println(dropdown.getFirstSelectedOption().getText());

//        // Create object of the Select class
//        Select se = new Select(driver.findElement(element));
//
//       // Select the option by index
//        se.selectByValue("Pieces (Pc(s))");
    }

    @And("I click on {string} starts with {string} as text")
    public void iClickOnStartsWithAsText(String locator, String text) {
        By elementLocated = constructElement(findElementRepo(locator));

        //Get value from dataset
        String value = getDatasetValue(text);
        scenario.log(text + " : " + value);

        //Wait until element display
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocated));

        //Create element collection
        List<WebElement> elements = driver.findElements(elementLocated);
        //Iterate through list
        for (WebElement element : elements) {
            if (element.getText().startsWith(value)){
            element.click();
            }

        }
    }

    @And("I select UnitValue {string}")
    public void iSelectUnitValue(String locator) {
        By element = constructElement(findElementRepo(locator));



        WebElement dropdownElement = driver.findElement(element);
        Select dropdown = new Select(dropdownElement);

        // Get the value from the dataset properties file
        String unitValue = getDatasetValue("SelectUnitValue");
        dropdown.selectByVisibleText(unitValue);


        //System.out.println(dropdown.getFirstSelectedOption().getText());


    }

    //Correct select function
    @And("Do select visible text on {string} by {string}")
    public void doSelectVisibleTextOnBy(String locator, String text) {
        By element = constructElement(findElementRepo(locator));
        //Get value from dataset
        String value = getDatasetValue(text);
        scenario.log(text + " : " + value);

        WebElement dropdownElement = driver.findElement(element);

        Select select = new Select(dropdownElement);
        select.selectByVisibleText(getDatasetValue(text));
    }

    @And("I select value without select class on {string} by {string}")
    public void iSelectValueWithoutSelectClassOnBy(String locator, String text) {
        By element = constructElement(findElementRepo(locator));
        //Get value from dataset
        String value = getDatasetValue(text);
        scenario.log(text + " : " + value);

        // Store all School dropdown options into List
        List<WebElement> allOptions = driver.findElements(element);

        //Iterate through the options and match with the desired option to select
        for (WebElement option : allOptions) {
            if (option.getText().contains(value)) {
                option.click();

            }
        }


    }

    @And("I select cloth value without select class on {string} by {string}")
    public void iSelectClothValueWithoutSelectClassOnBy(String locator, String text) {
        By element = constructElement(findElementRepo(locator));
        //Get value from dataset
        String value = getDatasetValue(text);
        scenario.log(text + " : " + value);
        // Store all clothes div elements into a list
        List<WebElement> allClothes = driver.findElements(element);

        // Iterate through the div elements and match with the desired option to select
        for (WebElement cloth : allClothes) {
            // Assuming the cloth name is contained within the div element's text
            if (cloth.getText().startsWith(value)) {
                cloth.click();
                break; // Exit the loop once the desired cloth is found and selected
            }
        }


    }




    @And("I select span class on {string}  by {string}")
    public void iSelectSpanClassOnBy(String locator, String text) {
        By element = constructElement(findElementRepo(locator));
        //Get value from dataset
        String value = getDatasetValue(text);
        scenario.log(text + " : " + value);

        // Store all School dropdown options into List
        List<WebElement> allOptions = driver.findElements(element);

        //Iterate through the options and match with the desired option to select
        for (WebElement option : allOptions) {
            if (option.getText().equals(value)) {
                option.click();

            }
        }


    }

    @And("wait page loading")
    public void waitPageLoading() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Your Cart")));
    }

    @And("Get dynamic value {string}")
    public void getDynamicValue(String locator) {
        By element = constructElement(findElementRepo(locator));

        WebElement cartNo = driver.findElement(element);
        String valueIneed = cartNo.getText();
        System.out.println("Number of item in cart  : " + valueIneed);

//        // Extract the floating number using regular expressions
//        String pattern = "(\\d+\\.\\d+)";
//        Pattern regex = Pattern.compile(pattern);
//        Matcher matcher = regex.matcher(valueIneed);
//
//        String extractedValue = "";
//        if (matcher.find()) {
//            extractedValue = matcher.group(1);
//            System.out.println("Extracted floating number: " + extractedValue);
//        } else {
//            System.out.println("No floating number found in the dynamic value.");
//        }
//
//        // Store the extracted value for further use
//        // You can assign it to another variable or store it in an appropriate data structure
//        // For example, if you want to store it in a separate variable:
//        double floatingNumber = Double.parseDouble(extractedValue);

//        // Extract the numerical value by removing non-digit and non-decimal characters
//        String numericalValue = valueIneed.replaceAll("[^\\d.]", "");
//
//        // Convert the numerical value to a floating-point number
//        this.variable1 = Double.parseDouble(numericalValue);
//
//        System.out.println("Extracted numerical value: " + this.variable1);
//
//        // Store the extracted value for further use
//        // You can assign it to another variable or store it in an appropriate data structure
//        // For example, if you want to store it in a separate variable:
//        double anotherVariable = this.variable1;

    }

    @And("Get dynamic value1 {string}")
    public void getDynamicValue1(String locator) {
        By element = constructElement(findElementRepo(locator));

        WebElement cellIneed = driver.findElement(element);
        String valueIneed = cellIneed.getText();
        System.out.println("Cell value is : " + valueIneed);


        // Extract the numerical value by removing non-digit and non-decimal characters
        String numericalValue = valueIneed.replaceAll("[^\\d.]", "");

        // Convert the numerical value to a floating-point number
        this.variable2 = Double.parseDouble(numericalValue);

        System.out.println("Extracted numerical value: " + this.variable2);

        // Store the extracted value for further use
        // You can assign it to another variable or store it in an appropriate data structure
        // For example, if you want to store it in a separate variable:
        double anotherVariable = this.variable2;

    }


    @And("show variable value")
    public void showVariableValue() {
        System.out.println("Product1  price: " + this.Product1);
        System.out.println("Product2 price: " + this.Product2);
        System.out.println("Product3 price: " + this.Product3);

    }

    @And("Cart total")
    public void CartTotal() {
        this.cartTotal = this.variable1 + this.Product1+this.Product2+this.Product3 ;
        System.out.println("Cart total is " + this.cartTotal);
    }

    @And("I get product selling price1 on {string}")
    public void iGetProductSellingPrice1On(String locator) {
        By element = constructElement(findElementRepo(locator));

        WebElement cellIneed = driver.findElement(element);
        String valueIneed = cellIneed.getText();
        System.out.println("product selling price is : " + valueIneed);

        String numericalValue = valueIneed.replaceAll("[^\\d.]", "");

        // Convert the numerical value to a floating-point number
        this.Product1 = Double.parseDouble(numericalValue);

        System.out.println("Extracted numerical value: " + this.Product1);


        double anotherVariable = this.Product1;

    }

    @And("I get product selling price2 on {string}")
    public void iGetProductSellingPrice2On(String locator) {
        By element = constructElement(findElementRepo(locator));

        WebElement cellIneed = driver.findElement(element);
        String valueIneed = cellIneed.getText();
        System.out.println("product selling price is : " + valueIneed);

        String numericalValue = valueIneed.replaceAll("[^\\d.]", "");

        // Convert the numerical value to a floating-point number
        this.Product2 = Double.parseDouble(numericalValue);

        System.out.println("Extracted numerical value: " + this.Product2);


        double anotherVariable = this.Product2;

    }

    @And("I get product selling price3 on {string}")
    public void iGetProductSellingPric3eOn(String locator) {
        By element = constructElement(findElementRepo(locator));

        WebElement cellIneed = driver.findElement(element);
        String valueIneed = cellIneed.getText();
        System.out.println("product selling price is : " + valueIneed);

        String numericalValue = valueIneed.replaceAll("[^\\d.]", "");

        // Convert the numerical value to a floating-point number
        this.Product3 = Double.parseDouble(numericalValue);

        System.out.println("Extracted numerical value: " + this.Product3);


        double anotherVariable = this.Product3;

    }

//    @And("check stock status on {string}")
//    public void checkStockStatusOn(String locator) {
//        By element = constructElement(findElementRepo(locator));
//
//        // Create a loop to add items to the cart
//
//        while (itemsAddedToCart < 3) {
//            // Find all available items on the page
//            List<WebElement> availableItems = driver.findElements(element);
//
//            for (WebElement item : availableItems) {
//                // Check if the item is in stock
//                if (item.findElement(By.cssSelector(".stock-status")).getText().equals("In Stock")) {
//                    // Click the "Add to Cart" button for the in-stock item
//                    item.findElement(By.xpath(".//button[contains(text(), 'Add to Cart')]")).click();
//                    itemsAddedToCart++;
//
//                    // Wait for the cart to update
//                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Your Cart")));
//
//                    // Break out of the loop if we've added 3 items to the cart
//                    if (itemsAddedToCart >= 3) {
//                        break;
//                    }
//                }
//            }
//
//
//
//
//
//    }
}
