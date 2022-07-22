import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.json.JsonInput;


import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;


public class UpResume {
    /*public static void addCookiesAll(WebDriver driver) throws IOException, ClassNotFoundException {
        FileInputStream read=new FileInputStream("cookies.bin");
        ObjectInputStream ois=new ObjectInputStream(read);
        Set<Cookie> cookies=(Set<Cookie>) ois.readObject();
        for(Cookie cookie:cookies ){

            System.out.println(cookie);
            driver.manage().addCookie(cookie);

            }
        //должна была быть аутентификация с помощью куки
    }*/
    public static void saveCookieInFile(WebDriver driver,String login,String pass){
        try {
            driver.get("https://hh.ru/account/login?backurl=%2F&hhtmFrom=main");
            //WebElement bupForAuth= driver.findElement(By.xpath("//*[@id=\"HH-React-Root\"]/div/div[2]/div/div/div/div/div[6]/a"));
            WebElement log = driver.findElement(By.xpath("//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div/div[1]/div[1]/div[1]/div[2]/div/div/form/div[1]/input"));
            log.sendKeys(login);
            WebElement bupForPass = driver.findElement(By.xpath("//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div/div[1]/div[1]/div[1]/div[2]/div/div/form/div[4]/button[2]"));
            bupForPass.click();
            WebElement passInput=driver.findElement(By.xpath("//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div/div[1]/div[1]/div[1]/div[2]/div/form/div[2]/span/input"));
            passInput.sendKeys(pass);
            WebElement bupForAuth= driver.findElement(By.xpath("//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div/div/div/div[1]/div[1]/div[1]/div[2]/div/form/div[4]/div/button[1]/span"));
            bupForAuth.click();
            Thread.sleep(4000);
            Set<Cookie> cookies = driver.manage().getCookies();
            FileOutputStream save=new FileOutputStream("cookies.bin");
            ObjectOutputStream oos=new  ObjectOutputStream(save);
            oos.writeObject(cookies);
            oos.close();
            // driver.manage().addCookie(new Cookie("_xsrf", "value"));
            // driver.manage().addCookie(new Cookie("_xsrf", "value"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public static void upResume(WebDriver driver){
       WebElement upButton =driver.findElement(By.xpath("//*[@id=\"HH-React-Root\"]/div/div[3]/div[1]/div/div/div[1]/div[3]/div[2]/div/div[6]/div/div/div[1]/div[1]/div/span/button"));
      upButton.click();
    }
    public static Long sleepMyProgram(String ms){
        String[] time=ms.split(":");

        Long timerFuter=((Long.parseLong(time[0])*3600000)+(Long.parseLong(time[1])*60000));



        LocalDateTime nowDate=LocalDateTime.now();
        String s= String.valueOf(nowDate.getHour());
        Long nowDate1=(Long.parseLong((s))*3600000+(Long.parseLong(String.valueOf(nowDate.getMinute()))*60000))+(10*60000) ;


        Long sleep=(timerFuter-nowDate1);
        System.out.println(sleep);
        return sleep;

    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\Загрузки\\UpResume\\chromedriver\\chromedriver.exe");//полный путь до драйвера под ваш браузер
        //следите за версией драйвера и брузера они должны совподать
        WebDriver driver = new ChromeDriver();
       UpResume.saveCookieInFile(driver,"your login","your password");
       while (true){
       driver.get("https://smolensk.hh.ru/applicant/resumes?hhtmFromLabel=header&hhtmFrom=main");
       Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = null;
        element = (WebElement) js.executeScript("return document.querySelector(\"#HH-React-Root > div > div.HH-MainContent.HH-Supernova-MainContent > div.main-content > div > div > div.bloko-column.bloko-column_container.bloko-column_xs-4.bloko-column_m-8.bloko-column_l-11 > div.bloko-column.bloko-column_xs-4.bloko-column_s-8.bloko-column_m-8.bloko-column_l-11 > div.bloko-gap.bloko-gap_top.bloko-gap_bottom > div > div.applicant-resumes-update > div\");");
       String stringDate=element.getText();
       String[]masString=stringDate.split(" ");
      if(masString.length==4){
          UpResume.upResume(driver);
          continue;
      }
       if(masString.length==6){
       Long sleep=UpResume.sleepMyProgram(masString[5]);
       System.out.println(masString.length+"-длинна массива; "+"заснул на-"+sleep);
       Thread.sleep((sleep));}

       UpResume.upResume(driver);

    }}
}
