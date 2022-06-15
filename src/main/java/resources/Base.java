package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Base {
    static ExtentReports extent;
    public WebDriver driver;
    List<String> data_container;

    public static ExtentReports extend_conf() {
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter report = new ExtentSparkReporter(path);
        report.config().setReportName("Vivek Test");
        report.config().setDocumentTitle("Test Results");
        extent = new ExtentReports();
        extent.attachReporter(report);
        extent.setSystemInfo("Test", "Vivek");
        return extent;
    }

    public WebDriver driver_init() throws IOException {
        if (readProperty("browser").equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        if (readProperty("browser").equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\main\\java\\resources\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
        if (readProperty("browser").equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\java\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public String readProperty(String key) throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
        Properties pros = new Properties();
        pros.load(fis);
        System.out.println(pros.getProperty(key));
        return pros.getProperty(key);
    }

    public List<String> readExcel(String module) throws IOException {
        data_container = new ArrayList<String>();
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\Exceldriven.xlsx";
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        System.out.println("number of sheets is " + workbook.getNumberOfSheets());
        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {

            XSSFSheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            if (sheetName.equalsIgnoreCase("System1")) {
                Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
                Row firstrow = rows.next();
                Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
                int k = 0;
                int coloumn = 0;
                while (ce.hasNext()) {
                    Cell value = ce.next();

                    if (value.getStringCellValue().equalsIgnoreCase("Modules")) {

                        coloumn = k;
                        System.out.println("The modules present in " + k + "th column");

                    }

                    k++;
                }

                while (rows.hasNext()) {

                    Row r = rows.next();

                    if (r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(module)) {
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {

                            Cell c = cv.next();
                            CellType type = c.getCellType();

                            switch (type) {

                                case NUMERIC: // numeric value in excel

                                    data_container.add(Double.toString(c.getNumericCellValue()));
                                    System.out.println(Double.toString(c.getNumericCellValue()));
                                    break;

                                case STRING: // String Value in Excel
                                    data_container.add(c.getStringCellValue());
                                    System.out.println(c.getStringCellValue());
                                    break;
                                default:
                                    System.out.println("EMpty cell ");
                            }

                        }
                    }

                }

            }
        }
        return data_container;
    }

    public String readData(List<String> data, String field) {

        for (String s : data) {
            if (s.split("\\|")[0].equalsIgnoreCase(field)) {
                System.out.println(s.split("\\|")[1]);
                return s.split("\\|")[1];
            }
        }
        return "";
    }

    public void getScreenShot(String methodName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File destFile = new File(System.getProperty("user.dir") + "\\reports\\" + methodName + ".png");
        FileUtils.copyFile(src, destFile);
    }
}
