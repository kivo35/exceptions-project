package com.sqa.kv.exceptions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Colors
{
	@Test
	public void getBackgroundColor() throws InvalidColoredElementException
	{
		String url = "https://color.adobe.com/create/color-wheel/?base=2&rule=Analogous&selected=0&name=My%20Color%20Theme&mode=rgb&rgbvalues=1,0.3270833333331894,0.050000000000000044,0.91,0.17157291666660116,0.04550000000000004,1,0,0,0.91,0.04550000000000004,0.4777500000001965,1,0.050000000000000044,0.999999999999784&swatchOrder=0,1,2,3,4";
		WebDriver driver = new FirefoxDriver();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		String cssSelectorLocator = driver.findElement(By.cssSelector(".marker.selected > div")).getCssValue(
				"background-color");
		System.out.println("background-color:" + cssSelectorLocator);

		if (!(cssSelectorLocator.contains("background-color:")) || cssSelectorLocator == null)
		{
			throw new InvalidColoredElementException(cssSelectorLocator);
		}

		try
		{
			getBackgroundColor(driver, url, cssSelectorLocator);
			System.out.println("The color is passed!");
		}
		catch (InvalidWarmthException e)
		{
			System.out.println(e.getColor() + " is not a warm color!");
			e.printStackTrace();
		}
		catch (InvalidColdException e)
		{
			System.out.println(e.getColor() + " is not a cold color!");
			e.printStackTrace();
		}
		catch (UnlikedColorException e)
		{
			System.out.println(e.getColor() + " is not the color I like! :(");
			e.printStackTrace();
		}

		driver.close();
	}

	public boolean getBackgroundColor(WebDriver driver, String url, String cssSelectorLocator)
			throws InvalidWarmthException, InvalidColdException, UnlikedColorException
	{
		String[] warmColors = { "rgb(255, 83, 13)", "rgb(255,255,0)", "rgb(255,165,0)" };
		String[] coldColors = { "rgb(0,0,255)", "rgb(128,0,128)", "rgb(0,128,0)" };
		String[] unlikedColors = { "rgb(0,0,0))", "rgb(47,79,79)", "rgb(184,134,11)" };
		String splitted[] = cssSelectorLocator.split("background-color: ");
		splitted[1] = splitted[1].replace(";", "");

		for (int i = 0; i < 3; i++)
		{
			if (!(splitted[1].equals(warmColors[i])))
			{
				throw new InvalidWarmthException(splitted[1]);
			}
			else if (!splitted[1].equals(coldColors[i]))
			{
				throw new InvalidColdException(splitted[1]);
			}
			else if (!splitted[1].equals(unlikedColors[i]))
			{
				throw new UnlikedColorException(splitted[1]);
			}
		}
		return true;
	}
}
