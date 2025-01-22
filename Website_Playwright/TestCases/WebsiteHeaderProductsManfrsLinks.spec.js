import test, { expect } from "@playwright/test";
import PageHelper from "./UsedFunction";
const helper = new PageHelper()

test('Website Products & Manufacturer Links', async ({ page }) => {
    await page.goto('https://stagingiidm.wpengine.com/');
    await expect(page.getByText('Accept All', { exact: true })).toBeVisible();
    await page.getByText('Accept All', { exact: true }).click();
    await helper.onHover(page, 'Products', 1)
    await page.getByRole('link', { name: 'Absolute Encoders' }).click();
    await expect(page.getByRole('heading', { name: 'Absolute Encoders' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await helper.onHover(page, 'Products', 1)
    await page.locator('#mega-menu-item-16069').getByRole('link', { name: 'AC Drives' }).click();
    await expect(page.getByRole('heading', { name: 'AC Drives' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await helper.onHover(page, 'Products', 1)
    await page.getByRole('link', { name: 'Actuators' }).click();
    await expect(page.getByRole('heading', { name: 'Actuators' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await helper.onHover(page, 'Products', 1)
    await page.getByRole('link', { name: 'Air Dryers & Main Line Filters' }).click();
    await expect(page.getByRole('heading', { name: 'Air Dryers & Main Line Filters' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await helper.onHover(page, 'Products', 1)
    await page.getByRole('link', { name: 'Airline Equipment' }).click();
    await expect(page.getByRole('heading', { name: 'Airline Equipment' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await helper.onHover(page, 'Products', 1)
    await page.getByRole('link', { name: 'Angle encoders' }).click();
    await expect(page.getByRole('heading', { name: 'Angle encoders' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await helper.onHover(page, 'Products', 1)
    await page.getByRole('link', { name: 'Automatic Doors and Platform' }).click();
    await expect(page.getByRole('heading', { name: 'Automatic Doors and Platform' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await helper.onHover(page, 'Products', 1)
    await page.getByRole('link', { name: 'Automation technology' }).click();
    await expect(page.getByRole('heading', { name: 'Automation technology' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await helper.onHover(page, 'Products', 1)
    await page.getByRole('link', { name: 'Back Panels' }).click();
    await expect(page.getByRole('heading', { name: 'Back Panels' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await helper.onHover(page, 'Products', 1)
    await page.getByRole('link', { name: 'Bearings' }).click();
    await expect(page.getByRole('heading', { name: 'Bearings' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await helper.onHover(page, 'Products', 1)
    await page.getByRole('link', { name: 'See more categories' }).click();
    await expect(page.getByRole('heading', { name: 'All Categories' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await helper.prodManfsLinks(page, 'Products');
    await helper.goBackToHomePage(page)
    //Manufacturer links at header
    await helper.headerManifacturerLinks(page);
    await helper.goBackToHomePage(page)
    await helper.prodManfsLinks(page, 'Manufacturers')
    await helper.goBackToHomePage(page);
})