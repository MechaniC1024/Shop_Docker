package com.PrestaShop.InitialConfiguration;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public enum Browsers {
	
	CHROME {
        public DesiredCapabilities create(){
        	
        	WebDriverManager.chromedriver().setup();
            return DesiredCapabilities.chrome();
        }
    },
    FIREFOX {
        public DesiredCapabilities create() {
        	
        	WebDriverManager.firefoxdriver().setup();
            return DesiredCapabilities.firefox();
        }
    };

    public DesiredCapabilities create(){
        return null;
    }
}
