const { test, expect } = require('@playwright/test');
const exp = require('constants');
const { default: PageHelper } = require('./UsedFunction');
const helper = new PageHelper();
test('Home Page Footer Links', async ({ page }) => {
    await page.goto('https://stagingiidm.wpengine.com/')
    await page.waitForLoadState('load')
    await helper.onHover(page, 'Automation, Controls, Repairs & Service', 1)
    await expect(page.getByText('Accept All', { exact: true })).toBeVisible();
    await page.getByText('Accept All', { exact: true }).click();
    await page.getByRole('link', { name: 'Products Brief' }).hover()
    await page.getByRole('link', { name: 'Products Brief' }).click()
    await expect(page.getByText('Products Overview', { exact: true })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Industrial Control Panels' }).hover();
    await page.getByRole('link', { name: 'Industrial Control Panels' }).click();
    await expect(page.getByRole('heading', { name: 'Industrial control panels for' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Personal Protective Equipments' }).hover();
    await page.getByRole('link', { name: 'Personal Protective Equipments' }).click();
    await expect(page.getByRole('heading', { name: 'When social distancing is not' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Aluminum Extrusion Assemblies' }).hover();
    await page.getByRole('link', { name: 'Aluminum Extrusion Assemblies' }).click();
    await expect(page.getByRole('heading', { name: 'Aluminum Extrusion Assemblies' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Sunbelt Power' }).hover();
    await page.getByRole('link', { name: 'Sunbelt Power' }).click();
    await expect(page.getByRole('heading', { name: 'Sunbelt Power Controls —' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.locator("(//*[text()='Shop Now'])[2]").hover()
    await page.locator("(//*[text()='Shop Now'])[2]").click()
    await expect(page.getByRole('heading', { name: 'Search -' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.locator('#menu-services-menu-1').getByRole('link', { name: 'Industrial Field Service' }).hover();
    await page.locator('#menu-services-menu-1').getByRole('link', { name: 'Industrial Field Service' }).click();
    await expect(page.getByText('Emergency Breakdown Industrial Field Service Repairs')).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Contract Manufacturing' }).hover();
    await page.getByRole('link', { name: 'Contract Manufacturing' }).click();
    await expect(page.getByRole('heading', { name: 'Trust your contract' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'System Integration' }).hover();
    await page.getByRole('link', { name: 'System Integration' }).click();
    await expect(page.getByText('Automation system integration')).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Frequently Asked Questions' }).hover();
    await page.getByRole('link', { name: 'Frequently Asked Questions' }).click();
    await expect(page.getByRole('heading', { name: 'Have a Question?' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Repair Service' }).hover();
    await page.getByRole('link', { name: 'Repair Service' }).click();
    await expect(page.getByText('Home / Repair Service')).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.locator("(//*[text()='What We Repair'])[2]").hover()
    await page.locator("(//*[text()='What We Repair'])[2]").click()
    await expect(page.getByText('WE REPAIR AC/DC drives •')).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Industrial Sensors' }).hover();
    await page.getByRole('link', { name: 'Industrial Sensors' }).click();
    await expect(page.getByText('Home / Technologies /')).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Machine Safety' }).hover();
    await page.getByRole('link', { name: 'Machine Safety' }).click();
    await expect(page.getByText('Home / Technologies / Machine')).toBeVisible();
    await expect(page.getByRole('heading', { name: 'Machine and Facility Safety' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Motion Control Technology' }).hover();
    await page.getByRole('link', { name: 'Motion Control Technology' }).click();
    await expect(page.getByRole('heading', { name: 'Motion control for speed,' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'PLC Technology' }).hover();
    await page.getByRole('link', { name: 'PLC Technology' }).click();
    await expect(page.getByText('PLCs monitor, control, and')).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Industrial Robotics' }).hover();
    await page.getByRole('link', { name: 'Industrial Robotics' }).click();
    await expect(page.getByRole('heading', { name: 'Ask us about robotic solutions' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Pneumatics' }).hover();
    await page.getByRole('link', { name: 'Pneumatics' }).click();
    await expect(page.getByRole('heading', { name: 'Pneumatic solutions for your' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Variable Frequency Drives' }).hover();
    await page.getByRole('link', { name: 'Variable Frequency Drives' }).click();
    await expect(page.getByRole('heading', { name: 'We stock high HP drives,' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Careers' }).hover();
    await page.getByRole('link', { name: 'Careers' }).click();
    await expect(page.getByText('Home / Careers')).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'News & Blogs' }).hover();
    await page.getByRole('link', { name: 'News & Blogs' }).click();
    await expect(page.getByRole('heading', { name: 'Automation NOW!' })).toBeVisible();
    await expect(page.getByText('Categories', { exact: true })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Customer Success Stories' }).hover();
    await page.getByRole('link', { name: 'Customer Success Stories' }).click();
    await expect(page.getByRole('heading', { name: 'Customer Success Stories' })).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.getByRole('link', { name: 'Contact Information' }).hover();
    await page.getByRole('link', { name: 'Contact Information' }).click();
    await expect(page.getByText('Home / Contact Information')).toBeVisible();
    await helper.goBackToHomePage(page)
    await page.waitForTimeout(2000)
})