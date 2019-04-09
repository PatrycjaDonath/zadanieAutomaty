package utils;

import core.DriverFactory;

public class BaseClassWithUtils extends DriverFactory {
    public WaitUtil waitUtil = new WaitUtil();
    public GeneralUtil generalUtil = new GeneralUtil();
}
