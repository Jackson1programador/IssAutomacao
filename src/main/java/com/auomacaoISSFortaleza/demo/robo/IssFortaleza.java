package com.auomacaoISSFortaleza.demo.robo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import io.github.bonigarcia.wdm.WebDriverManager;

@Service
public class IssFortaleza {

    public void executarEncerramento(Long IdEmpresa, Long IdCliente) {
        System.setProperty("webdriver.chrome.driver", "C:\\caminho\\para\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.google.com");
            System.out.println("Google aberto com sucesso!");
            Thread.sleep(2000);

            driver.get("https://ge.globo.com/");
            System.out.println("Globo Esporte aberto com sucesso!");
            Thread.sleep(5000);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
