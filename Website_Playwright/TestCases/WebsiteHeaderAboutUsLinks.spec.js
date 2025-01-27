import test, { expect } from "@playwright/test";

const webSiteUrl = 'https://stagingiidm.wpengine.com/';
const webStoreUrl = 'https://staging-store.iidm.com/';

test('Header About Links', async ({ page }) => {
    await page.goto(webSiteUrl);
    await expect(page.getByText('Accept All', { exact: true })).toBeVisible();
    await page.getByText('Accept All', { exact: true }).click();
    await page.getByRole('link', { name: 'About ' }).hover();
    await page.locator('#menu-item-2524').getByRole('link', { name: 'Careers' }).click();
    const viewCurrentOpenings = page.getByRole('link', { name: 'View current openings' });
    await expect(viewCurrentOpenings).toBeVisible();
    const page1Promise = page.waitForEvent('popup');
    await viewCurrentOpenings.hover(); await viewCurrentOpenings.click();
    const page1 = await page1Promise;
    await expect(page1.locator('#jobs-list-container')).toContainText('Not Finding What You\'re Looking For?');
    await expect(page1.getByRole('paragraph')).toContainText('Share your information and we will contact you if new opportunities fitting your qualifications become available');
    await expect(page1).toHaveURL('https://recruiting.paylocity.com/recruiting/jobs/All/8facb93f-170f-4862-a611-885c813b9ca3/Innovative-IDM-LLC');
    await page.waitForTimeout(1500);
    await page1.close();
    await expect(page.url()).toContain(webSiteUrl)
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    /*const page1Promise = page.waitForEvent('popup');
     await page.getByRole('link', { name: 'View current openings' }).click();
    const page1 = await page1Promise;*/
    await page.goBack();
    await page.getByRole('link', { name: 'About ' }).hover();
    await page.locator('#menu-item-2547').getByRole('link', { name: 'News & Blogs' }).click();
    await expect(page.getByText('Categories', { exact: true })).toBeVisible();
    await expect(page.getByText('Archives', { exact: true })).toBeVisible();
    await expect(page.getByPlaceholder('Search blog')).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl)
    await page.goBack();
    await page.getByRole('link', { name: 'About ' }).hover();
    await page.locator('#menu-item-15115').getByRole('link', { name: 'Customer Success Stories' }).click();
    await expect(page.getByRole('heading', { name: 'Customer Success Stories' })).toBeVisible();
    await expect(page.getByRole('heading', { name: 'Our team works with your team' })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl)
    await page.goBack();
    await page.getByRole('link', { name: 'About ' }).hover();
    await page.locator('#menu-item-2487').getByRole('link', { name: 'Contact Information' }).click();
    await expect(page.getByRole('heading', { name: 'Home of the Legendary Customer Experience', exact: true })).toBeVisible();
    await expect(page.getByText('Warehouse Locations')).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl)
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'About ' }).hover();
    await page.getByRole('link', { name: 'Let us know' }).click();
    await expect(page.getByPlaceholder('Your Name')).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await expect(page.getByPlaceholder('Your Company Name')).toBeVisible();
    await expect(page.getByPlaceholder('Your Phone number')).toBeVisible();
    await expect(page.getByPlaceholder('Your Email')).toBeVisible();
    await expect(page.getByPlaceholder('Your Message')).toBeVisible();
})