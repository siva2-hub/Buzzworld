// @ts-check
const { test, expect } = require('@playwright/test');
const exp = require('constants');
const { appendFile } = require('fs');
const { default: PageHelper } = require('./UsedFunction');
const helper = new PageHelper();
const webSiteUrl = 'https://stagingiidm.wpengine.com/';
const webStoreUrl = 'https://staging-store.iidm.com/';

test('Verify Home Page', async ({ page }) => {
  await page.goto(webSiteUrl)
  await expect(page.getByText('Accept All', { exact: true })).toBeVisible();
  await page.getByText('Accept All', { exact: true }).click();
  await page.getByRole('link', { name: 'Request a Quote! ' }).click();
  await expect(page.getByRole('heading', { name: 'Search from over 200k' })).toBeVisible();
  await expect(page).toHaveURL(webStoreUrl);
  await page.goto(webSiteUrl);
  await page.locator("(//*[contains(@id,'bt_bb_section')])[3]").getByRole('link', { name: 'Learn more' }).first().click();
  await expect(page.getByText('Products Overview', { exact: true })).toBeVisible();
  await expect(page.url()).toContain(webSiteUrl);
  await page.locator("//*[text()='Premier Products: Our Line Cards']").first().scrollIntoViewIfNeeded()
  await expect(page.getByText('Industrial Control Panels').first()).toBeVisible();
  await page.goto(webSiteUrl);
  await page.locator("(//*[contains(@id,'bt_bb_section')])[3]").getByRole('link', { name: 'Learn more' }).nth(1).click();
  await page.getByText('Electrical Control Panel Manufacturing Shops').scrollIntoViewIfNeeded();
  await expect(page.getByText('Electrical Control Panel Manufacturing Shops')).toBeVisible();
  await page.goto(webSiteUrl);
  await page.locator("(//*[contains(@id,'bt_bb_section')])[3]").getByRole('link', { name: 'Learn more' }).nth(2).click();
  await expect(page.locator('section').filter({ hasText: 'Five decades of superior' }).getByRole('link')).toBeVisible();
  await page.locator("//*[text()='Get quotation for repair service']").scrollIntoViewIfNeeded();
  await expect(page.locator("//*[text()='Get quotation for repair service']")).toBeVisible();
  await page.goto(webSiteUrl);
  await page.getByRole('link', { name: 'Industrial Electronics Repairs' }).click();
  await expect(page.getByRole('heading', { name: 'Request for Repair' })).toBeVisible();
  await page.goto(webSiteUrl);
  await page.locator("(//*[contains(@id,'bt_bb_section')])[4]").getByRole('link', { name: 'Industrial Field Service' }).click();
  await expect(page.getByText('Emergency Breakdown Industrial Field Service Repairs')).toBeVisible();
  await page.goto(webSiteUrl);
  await page.getByRole('link', { name: 'Value Added Services' }).click();
  await expect(page.getByRole('heading', { name: 'Trust your contract' })).toBeVisible();
  await page.goto(webSiteUrl);
  await page.locator('.bt_bb_column_inner_content > .bt_bb_button > .bt_bb_link').first().click();
  await expect(page.getByRole('heading', { name: 'Ownership Team' })).toBeVisible();
  await page.locator("//*[text()='Let us know about your project']").click();
  await expect(page.getByPlaceholder('Your Name')).toBeVisible();
  await expect(page.url()).toContain(webSiteUrl);
  await expect(page.getByPlaceholder('Your Company Name')).toBeVisible();
  await expect(page.getByPlaceholder('Your Phone number')).toBeVisible();
  await expect(page.getByPlaceholder('Your Email')).toBeVisible();
  await expect(page.getByPlaceholder('Your Message')).toBeVisible();
  await page.goBack();
  await page.locator("//*[text()='Ownership Team']").scrollIntoViewIfNeeded();
  await page.goto(webSiteUrl);
  await page.locator('div:nth-child(2) > .bt_bb_column_content > .bt_bb_column_content_inner > div:nth-child(2) > .bt_bb_row_inner > .bt_bb_column_inner > .bt_bb_column_inner_content > .bt_bb_button > .bt_bb_link').click();
  await expect(page.getByRole('heading', { name: 'Winning Teams Attract Winning' })).toBeVisible();
  await page.goto(webSiteUrl);
  await page.locator('div:nth-child(3) > .bt_bb_column_content > .bt_bb_column_content_inner > div:nth-child(2) > .bt_bb_row_inner > .bt_bb_column_inner > .bt_bb_column_inner_content > .bt_bb_button > .bt_bb_link').click();
  await expect(page.getByText('Home / Contact Information')).toBeVisible();
  await expect(page.getByRole('heading', { name: 'The Home of the Legendary' })).toBeVisible();
  await page.goto(webSiteUrl);
  await expect(page.getByRole('heading', { name: 'Need a quick quote or more' })).toBeVisible();
  await expect(page.getByPlaceholder('First Name')).toBeVisible();
  await expect(page.getByPlaceholder('name@mail.com')).toBeVisible();
  await expect(page.getByRole('button', { name: 'Submit your request' })).toBeVisible();
  await page.getByRole('button', { name: 'Submit your request' }).click();
  await expect(page.getByText('This is a required field.').nth(2)).toBeVisible();
  await expect(page.getByText('This is a required field.').nth(3)).toBeVisible();
  await expect(page.getByLabel('Contact form').getByText('One or more fields have an')).toBeVisible();
  await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
  await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
})

