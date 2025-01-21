import test, { expect } from "@playwright/test";
import { allowedNodeEnvironmentFlags } from "process";
const webSiteUrl = 'https://stagingiidm.wpengine.com/';
const webStoreUrl = 'https://staging-store.iidm.com/';

test('Header Technologies', async ({ page }) => {
    await page.goto(webSiteUrl);
    await expect(page.getByText('Accept All', { exact: true })).toBeVisible();
    await page.getByText('Accept All', { exact: true }).click();
    // await page.pause();
    await page.getByRole('link', { name: 'Technologies ' }).hover();
    await page.locator('#menu-item-2166').getByRole('link', { name: 'Industrial Sensors' }).click();
    await expect(page.url()).toContain(webSiteUrl)
    await expect(page.getByRole('heading', { name: 'Sensors and Vision' })).toBeVisible();
    await page.locator('//*[text()="Shop now"]').nth(0).click();
    await expect(page.getByRole('heading', { name: 'Industrial Control' })).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl);
    await expect(page.getByRole('link', { name: '#E2E-X9C112-R 2M' })).toBeVisible();
    await page.goBack();
    await page.locator('//*[text()="Shop now"]').nth(1).click();
    await expect(page.getByRole('heading', { name: 'Industrial Control' })).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl);
    await expect(page.getByText('#E2E-X9C112-R 2M Omron The')).toBeVisible();
    await page.goBack();
    await page.locator('//*[text()="Shop now"]').nth(2).click();
    await expect(page.getByRole('heading', { name: 'Industrial Control' })).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl);
    await expect(page.getByText('#E2E-X9C112-R 2M Omron The')).toBeVisible();
    await page.goBack();
    await page.locator('//*[text()="Shop now"]').nth(3).click();
    await expect(page.getByRole('heading', { name: 'Industrial Control' })).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl);
    await expect(page.getByText('#E2E-X9C112-R 2M Omron The')).toBeVisible();
    await page.goBack();
    await page.locator('//*[text()="Shop now"]').nth(4).click();
    await expect(page.getByRole('heading', { name: 'Industrial Control' })).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl);
    await expect(page.getByText('#E2E-X9C112-R 2M Omron The')).toBeVisible();
    await page.goBack();
    await page.locator('//*[text()="Shop now"]').nth(5).click();
    await expect(page.getByRole('heading', { name: 'Industrial Control' })).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl);
    await expect(page.getByText('#E2E-X9C112-R 2M Omron The')).toBeVisible();
    await page.goBack();
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Technologies ' }).hover();
    await page.locator('#menu-item-2204').getByRole('link', { name: 'Motion Control Technology' }).click();
    await expect(page.getByRole('heading', { name: 'Motion control technology' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Technologies ' }).hover();
    await page.locator('#menu-item-2363').getByRole('link', { name: 'Industrial Robotics' }).click();
    await expect(page.getByRole('heading', { name: 'Four Scenarios for Cobots' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Technologies ' }).hover();
    await page.locator('#menu-item-2394').getByRole('link', { name: 'Variable Frequency Drives' }).click();
    await expect(page.getByText('What is an AC Variable')).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await expect(page.getByRole('heading', { name: 'Featured VFD Products' })).toBeVisible();
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Technologies ' }).hover();
    await page.locator('#menu-item-2194').getByRole('link', { name: 'Machine Safety' }).click();
    await expect(page.getByRole('heading', { name: 'Machine and Facility Safety' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Technologies ' }).hover();
    await page.locator('#menu-item-2364').getByRole('link', { name: 'PLC Technology' }).click();
    await expect(page.getByRole('heading', { name: 'How PLCs revolutionized the' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await expect(page.getByRole('heading', { name: 'Featured Omron Automation' })).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Technologies ' }).hover();
    await page.locator('#menu-item-2393').getByRole('link', { name: 'Pneumatics' }).click();
    await expect(page.getByRole('heading', { name: 'How pneumatics are used' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Technologies ' }).hover();
    await page.getByRole('link', { name: 'Let us know' }).click();
    await expect(page.getByPlaceholder('Your Name')).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await expect(page.getByPlaceholder('Your Company Name')).toBeVisible();
    await expect(page.getByPlaceholder('Your Phone number')).toBeVisible();
    await expect(page.getByPlaceholder('Your Email')).toBeVisible();
    await expect(page.getByPlaceholder('Your Message')).toBeVisible();
})