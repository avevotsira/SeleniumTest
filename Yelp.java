import java.time.Duration;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Yelp {
    static class Restaurant implements Comparable<Restaurant> {
        String name;
        String website;
        int score;

        @Override
        public int compareTo(Restaurant other) {
            return other.score - this.score;
        }
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver",
                "C:\\Users\\chant\\Desktop\\coding\\Aupp\\geckodriver-v0.33.0-win64\\geckodriver.exe");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://www.yelp.com/search?find_desc=Chinese&find_loc=San+Francisco%2C+CA&sortby=rating");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        List<WebElement> restaurantDivs = driver
                .findElements(By.cssSelector(
                        ".container__09f24__mpR8_"));

        PriorityQueue<Restaurant> topRestaurants = new PriorityQueue<>();

        System.out.println(restaurantDivs.get(0).getText());
        System.out.println(restaurantDivs.get(1).getText());
        for (WebElement div : restaurantDivs) {
            try {
                div.click();
                String name = div.findElement(By.className("name")).getText();
                String website = div.findElement(By.className("website")).getAttribute("href");
                int rating = Integer.parseInt(div.findElement(By.className("rating")).getText());
                int reviews = Integer.parseInt(div.findElement(By.className("reviews")).getText());
                int photos = Integer.parseInt(div.findElement(By.className("photos")).getText());

                Restaurant restaurant = new Restaurant();
                restaurant.name = name;
                restaurant.website = website;
                restaurant.score = rating * reviews * photos;

                topRestaurants.add(restaurant);
                if (topRestaurants.size() > 3) {
                    topRestaurants.poll();
                }
            } catch (Exception e) {
                // Ignore restaurants with missing data
            }
        }

        while (!topRestaurants.isEmpty()) {
            Restaurant restaurant = topRestaurants.poll();
            System.out.println("Name: " + restaurant.name);
            System.out.println("Website: " + restaurant.website);
            System.out.println("Score: " + restaurant.score);
            System.out.println();
        }

    }
}
