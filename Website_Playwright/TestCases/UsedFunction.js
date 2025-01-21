import { expect } from "@playwright/test";

export default class PageHelper {
    async onHover(page, pathText, count) {
        await page.hover("(//*[text()='" + pathText + "'])[" + count + "]");
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
}