import test, { expect } from "@playwright/test"
const webSiteUrl = 'https://stagingiidm.wpengine.com/';
const webStoreUrl = 'https://staging-store.iidm.com/';

test('Header Services', async ({ page }) => {
    await page.goto(webSiteUrl);
    await expect(page.getByText('Accept All', { exact: true })).toBeVisible();
    await page.getByText('Accept All', { exact: true }).click();
    await page.getByRole('link', { name: 'Services ' }).hover();
    await page.locator('#menu-item-2062').getByRole('link', { name: 'Industrial Field Service' }).click();
    await expect(page.getByText('Emergency Breakdown Industrial Field Service Repairs')).toBeVisible();
    await page.locator("//*[text()='Checkout these Customer Success Stories']").click();
    await expect(page.getByRole('heading', { name: 'Customer Success Stories' })).toBeVisible();
    await expect(page.getByRole('heading', { name: 'Our team works with your team' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl); await page.goBack();
    await expect(page.getByText('Breakdown', { exact: true })).toBeVisible();
    await page.getByText('Preventive', { exact: true }).click();
    await page.locator("//*[text()='Our industrial field service techs are experts in:']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Our industrial field service techs are experts in:']")).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl)
    await page.locator("//*[text()='Submit your repair']").click();
    await expect(page.locator("//*[text()='Request for Repair']")).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl); await page.goBack();
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    //Contract Manufacturing at Services header dropdown
    await page.getByRole('link', { name: 'Services ' }).hover();
    await page.locator('#menu-item-2078').getByRole('link', { name: 'Contract Manufacturing' }).click();
    await expect(page.getByRole('heading', { name: 'Trust your contract' })).toBeVisible();
    await page.getByRole('heading', { name: 'Hiring a contract' }).click();
    await expect(page.url()).toContain(webSiteUrl)
    await page.locator("//*[text()='Submit your service request']").click();
    await expect(page.locator("//*[text()='Request for Repair']")).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl); await page.goBack();
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.getByRole('link', { name: 'Kitting industrial parts' }).click();
    await expect(page.getByRole('heading', { name: 'Trust your kitting projects' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await page.locator("//*[text()='Submit your request']").click();
    await expect(page.locator("//*[text()='Request for Repair']")).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl); await page.goBack()
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goto(webSiteUrl+'services/contract-manufacturing/')
    await page.getByRole('link', { name: 'LIght Mechanical, Pneumatic,' }).click();
    await expect(page.locator('p').filter({ hasText: /^Pre-Assembled Industrial Components$/ })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl)
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'UL 508A Panel shop' }).click();
    await expect(page.getByRole('heading', { name: 'Why trust your work to' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl)
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await expect(page.locator("//*[text()='People trust Innovative IDM. Why?']")).toBeVisible();
    await page.goto(webSiteUrl);
    //System Integration at Services header dropdown
    await page.getByRole('link', { name: 'Services ' }).hover();
    await page.locator('#menu-item-2094').getByRole('link', { name: 'System Integration' }).click();
    await expect(page.getByText('Automation system integration')).toBeVisible();
    await page.locator("//*[text()='Submit your service request']").click();
    await expect(page.locator("//*[text()='Request for Repair']")).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl); await page.goBack();
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.getByRole('link', { name: 'Custom Control Panels' }).click();
    await expect(page.getByRole('heading', { name: 'Custom control panel design' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl)
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Industrial controls' }).click();
    await expect(page.getByRole('heading', { name: 'Programming your industrial' })).toBeVisible();
    await expect(page.getByRole('heading', { name: 'Programming a New Industrial' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl)
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Control panel installation' }).click();
    await expect(page.getByRole('heading', { name: 'We get you up and running' })).toBeVisible();
    await expect(page.getByText('Install & start-up')).toBeVisible();
    await expect(page.getByRole('heading', { name: 'Installation' })).toBeVisible();
    await expect(page.getByRole('heading', { name: 'Start Up' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl)
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Continuous Support' }).click();
    await expect(page.getByText('Emergency Breakdown Industrial Field Service Repairs')).toBeVisible();
    await page.getByRole('img', { name: 'Preventive Maintenance' }).click();
    await page.getByRole('img', { name: 'We Travel To You' }).click();
    await expect(page.url()).toContain(webSiteUrl)
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Industrial control retrofits' }).click();
    await expect(page.getByRole('heading', { name: 'Industrial Machine Controls' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.locator("//*[text()='Request']").click();
    await expect(page.getByRole('heading', { name: 'Home of the Legendary Customer Experience', exact: true })).toBeVisible();
    await expect(page.getByText('Warehouse Locations')).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl); await page.goBack();
    await expect(page.locator("//*[text()='Our automation system technologies']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Services ' }).hover();
    await page.locator('#menu-item-16539').getByRole('link', { name: 'Frequently Asked Questions' }).click();
    await expect(page.getByRole('heading', { name: 'Have a Question?' })).toBeVisible();
    await expect(page.getByRole('heading', { name: 'Services FAQ' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl)
    await page.goBack();
    //Repair Services at Services Dropdown
    await page.getByRole('link', { name: 'Services ' }).hover();
    await page.locator('#menu-item-2416').getByRole('link', { name: 'Repair Service' }).click();
    await expect(page.getByRole('heading', { name: 'What we repair' })).toBeVisible();
    await expect(page.getByRole('heading', { name: 'Get quotation for repair' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await page.locator("//*[text()='Submit your repair']").nth(0).click();
    await expect(page.locator("//*[text()='Request for Repair']")).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl); await page.goBack();
    await page.locator("//*[text()='Submit your repair']").nth(1).click();
    await expect(page.locator("//*[text()='Request for Repair']")).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl); await page.goBack();
    await page.locator("//*[text()='Submit your repair']").nth(2).click();
    await expect(page.locator("//*[text()='Request for Repair']")).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl); await page.goBack();
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goto(webSiteUrl);
    //What we repair at services dropdown
    await page.getByRole('link', { name: 'Services ' }).hover();
    await page.locator('#menu-item-2423').getByRole('link', { name: 'What We Repair' }).click();
    await expect(page.getByRole('heading', { name: 'AC/DC drives • Power Supplies' })).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl);
    await page.locator("//*[text()='Submit your repair']").nth(0).click();
    await expect(page.locator("//*[text()='Request for Repair']")).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl); await page.goBack();
    await expect(page.getByRole('link', { name: 'View all manufacturers ' })).toBeVisible();
    await page.getByRole('link', { name: 'View all manufacturers ' }).click();
    await expect(page.locator("//*[text()='Our recommended manufacturers']")).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl); await page.goBack();
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
});