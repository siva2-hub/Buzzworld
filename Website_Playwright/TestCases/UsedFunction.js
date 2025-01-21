import { expect } from "@playwright/test";
const webSiteUrl = 'https://stagingiidm.wpengine.com/';
const webStoreUrl = 'https://staging-store.iidm.com/';

export default class PageHelper {
    async onHover(page, pathText, count) {
        await page.locator("(//*[text()='" + pathText + "'])[" + count + "]").hover();
    }
    async takeScreenShots(page, fileName) {
        await page.waitForLoadState('load')
        await page.screenshot({ path: 'screenshots/' + fileName + '.png', fullPage: true })
    }
    async goBackToHomePage(page) {
        await page.goBack()
        // await page.waitForLoadState('load')
        await this.onHover(page, 'Automation, Controls, Repairs & Service', 1)
    }
    async prodManfsLinks(page, headerName) {
        let btn1, btn2, btn3;
        if (headerName === 'Products') {
            btn1 = 1, btn2 = 2, btn3 = 3
        } else {
            btn1 = 4, btn2 = 5, btn3 = 6
        }
        await this.onHover(page, headerName, 1)
        await page.getByRole('link', { name: 'Products overview' }).click();
        await expect(page.getByRole('heading', { name: 'Looking for a part? We can' })).toBeVisible();
        await this.goBackToHomePage(page)
        await this.onHover(page, headerName, 1)
        await page.getByRole('link', { name: 'Industrial control panels', exact: true }).click();
        await expect(page.getByRole('heading', { name: 'Industrial control panels for' })).toBeVisible();
        await this.goBackToHomePage(page)
        await this.onHover(page, headerName, 1)
        await page.click("(//*[@class='logo-container'])[" + btn1 + "]")
        await expect(page.locator('p').filter({ hasText: /^Sunbelt Power Controls$/ })).toBeVisible();
        await this.goBackToHomePage(page)
        await this.onHover(page, headerName, 1)
        await page.click("(//*[@class='logo-container'])[" + btn2 + "]")
        await expect(page.getByRole('heading', { name: 'Request for Repair' })).toBeVisible();
        await this.goBackToHomePage(page)
        await this.onHover(page, headerName, 1)
        await page.click("(//*[@class='logo-container'])[" + btn3 + "]")
        await expect(page.getByRole('heading', { name: 'Aluminum Extrusion Assemblies' })).toBeVisible();

    }
    async MachineandFacilitySafetySectionLinksAt_PO(page) {
        let links = ['Aluminum Extrusion Assemblies', 'Safe T-Slot Guard', 'Machine Frames', 'Safe Space PPE', 'Quick Contact Form'];
        for (let index = 0; index < links.length; index++) {
            await page.locator("//*[text()='" + links[index] + "']").first().hover();
            await page.locator("//*[text()='" + links[index] + "']").first().click();
            switch (links[index]) {
                case 'Aluminum Extrusion Assemblies':
                    await expect(page.getByRole('heading', { name: 'Aluminum Extrusion Assemblies' })).toBeVisible();
                    await page.getByRole('heading', { name: 'Aluminum Extrusion uses' }).scrollIntoViewIfNeeded();
                    await expect(page.getByRole('heading', { name: 'Aluminum Extrusion uses' })).toBeVisible();
                    await expect(page.url()).toContain(webSiteUrl);
                    break;
                case 'Safe T-Slot Guard':
                    await page.getByRole('heading', { name: 'Safe T Slot Guard Photo' }).scrollIntoViewIfNeeded();
                    await expect(page.getByRole('heading', { name: 'Safe T Slot Guard Photo' })).toBeVisible();
                    await expect(page.url()).toContain(webSiteUrl);
                    break;
                case 'Machine Frames':
                    await page.getByRole('heading', { name: 'Machine Frames Photo Gallery' }).scrollIntoViewIfNeeded();
                    await expect(page.getByRole('heading', { name: 'Machine Frames Photo Gallery' })).toBeVisible();
                    await expect(page.url()).toContain(webSiteUrl);
                    break;
                case 'Safe Space PPE':
                    await page.getByRole('heading', { name: 'Safe Space PPE Photo Gallery' }).scrollIntoViewIfNeeded();
                    await expect(page.getByRole('heading', { name: 'Safe Space PPE Photo Gallery' })).toBeVisible();
                    await expect(page.url()).toContain(webSiteUrl);
                    break;
                case 'Quick Contact Form':
                    await page.locator("//*[text()='Home of the Legendary Customer Experience']").scrollIntoViewIfNeeded();
                    await expect(page.locator("//*[text()='Home of the Legendary Customer Experience']")).toBeVisible();
                    await expect(page.url()).toContain(webSiteUrl);
                    break;
                default:
                    break;
            }
            await page.goBack();
        }
    }
    async AluminumExtrusionAssembliesLinksAt_PO(page) {
        let links = ['Work Stations', 'Safety Solutions', 'Machine Guarding', 'Modular Fencing', 'Quick Quotes for You'];
        for (let index = 0; index < links.length; index++) {
            await page.locator("//*[text()='" + links[index] + "']").first().hover();
            await page.locator("//*[text()='" + links[index] + "']").first().click();
            switch (links[index]) {
                case 'Work Stations':
                    await page.getByRole('heading', { name: 'Work Stations Photo Gallery' }).scrollIntoViewIfNeeded();
                    await expect(page.getByRole('heading', { name: 'Work Stations Photo Gallery' })).toBeVisible();
                    await expect(page.url()).toContain(webSiteUrl);
                    break;
                case 'Safety Solutions':
                    await page.getByRole('heading', { name: 'Machine Frames Photo Gallery' }).scrollIntoViewIfNeeded();
                    await expect(page.getByRole('heading', { name: 'Machine Frames Photo Gallery' })).toBeVisible();
                    await expect(page.url()).toContain(webSiteUrl);
                    break;
                case 'Machine Guarding':
                    await page.getByRole('heading', { name: 'Machine hardguarding photo' }).scrollIntoViewIfNeeded();
                    await expect(page.getByRole('heading', { name: 'Machine hardguarding photo' })).toBeVisible();
                    await expect(page.url()).toContain(webSiteUrl);
                    break;
                case 'Modular Fencing':
                    await page.getByRole('heading', { name: 'Safe T Slot Guard Photo' }).scrollIntoViewIfNeeded();
                    await expect(page.getByRole('heading', { name: 'Safe T Slot Guard Photo' })).toBeVisible();
                    await expect(page.url()).toContain(webSiteUrl);
                    break;
                case 'Quick Quotes for You':
                    await expect(page.locator("//*[text()='Request for Quote']").nth(1)).toBeVisible();
                    await expect(page.url()).toContain(webStoreUrl);
                    break;
                default:
                    break;
            }
            await page.goBack();
        }
    }
}