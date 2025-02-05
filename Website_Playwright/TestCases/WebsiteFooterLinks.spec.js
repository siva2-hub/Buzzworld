const { test, expect } = require('@playwright/test');
const exp = require('constants');
const { default: PageHelper } = require('./UsedFunction');
const { consoleErrors, printConsoleErrors } = require('./ConsoleErrors');
const helper = new PageHelper();
const webSiteUrl = 'https://stagingiidm.wpengine.com/';
const webStoreUrl = 'https://staging-store.iidm.com/';

test('Home Page Footer Links', async ({ page }) => {
    let errors = await consoleErrors(page);
    await page.goto(webSiteUrl)
    await page.waitForLoadState('load')
    await helper.onHover(page, 'Automation, Controls, Repairs & Service', 1)
    await expect(page.getByText('Accept All', { exact: true })).toBeVisible();
    await page.getByText('Accept All', { exact: true }).click();
    //Product Overview link at Footer
    await page.getByRole('link', { name: 'Products Brief' }).hover()
    await page.getByRole('link', { name: 'Products Brief' }).click()
    await expect(page.getByText('Products Overview', { exact: true })).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await page.locator("//*[text()='Shop Now']").nth(1).click();
    await expect(page.getByRole('heading', { name: 'Search -' })).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl);
    await page.goBack(); // go back to home page
    let storeLinks = ['Sensor & components', 'Automation products', 'Identification, marking & verification', 'Vision systems',
        'Machine safety technology', 'Motion & Drives', 'Robotics', 'HVAC Assembly Line Control Panel', 'Plastics Extruder Panel',
        '16-Axis Punch Press Control Panel', 'Multi-Axis Motion Blanker Control Panel', 'Large Volume Contract Manufacturing',
        'Remote VFD Control Panel'];
    for (let index = 0; index < storeLinks.length; index++) {
        await page.locator("//*[text()='" + storeLinks[index] + "']").first().click();
        await expect(page.getByRole('heading', { name: 'Search -' })).toBeVisible();
        await expect(page.url()).toContain(webStoreUrl); await page.goBack();
    }
    await page.locator('#productLineCards').getByRole('link', { name: 'Shop Now ' }).click();
    await expect(page.getByRole('heading', { name: 'Search -' })).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl);
    await page.goBack();
    await page.getByRole('link', { name: 'Explore More ' }).first().click();
    await expect(page.getByRole('heading', { name: 'Industrial control panels for' })).toBeVisible();
    await expect(page.getByRole('link', { name: 'Let us know if you have a' })).toBeVisible();
    await page.getByRole('link', { name: 'Let us know if you have a' }).click();
    await expect(page.getByRole('heading', { name: 'Let us know' })).toBeVisible();
    await expect(page.getByPlaceholder('Your Name')).toBeVisible();
    await expect(page.getByPlaceholder('Your Company Name')).toBeVisible();
    await expect(page.getByPlaceholder('Your Phone number')).toBeVisible();
    await expect(page.getByPlaceholder('Your Email')).toBeVisible();
    await expect(page.getByPlaceholder('Your Message')).toBeVisible();
    await page.goBack(); await page.goBack();
    await helper.MachineandFacilitySafetySectionLinksAt_PO(page);
    await page.getByRole('link', { name: 'Explore ' }).nth(0).click();
    await page.getByRole('heading', { name: 'Safe Space PPE Photo Gallery' }).scrollIntoViewIfNeeded();
    await expect(page.getByRole('heading', { name: 'Safe Space PPE Photo Gallery' })).toBeVisible();
    await page.goBack();
    await helper.AluminumExtrusionAssembliesLinksAt_PO(page);
    await page.getByRole('link', { name: 'Explore More ' }).nth(1).click();
    await page.getByRole('heading', { name: 'Aluminum Extrusion uses' }).scrollIntoViewIfNeeded();
    await expect(page.getByRole('heading', { name: 'Aluminum Extrusion uses' })).toBeVisible();
    await page.goBack();
    await page.goto(webSiteUrl);
    // Industrial Control Panels link at Footer
    await page.getByRole('link', { name: 'Industrial Control Panels' }).hover();
    await page.getByRole('link', { name: 'Industrial Control Panels' }).click();
    await expect(page.getByRole('heading', { name: 'Industrial control panels for' })).toBeVisible();
    await expect(page.getByRole('link', { name: 'Let us know if you have a' })).toBeVisible();
    await page.getByRole('link', { name: 'Let us know if you have a' }).click();
    await expect(page.getByRole('heading', { name: 'Let us know' })).toBeVisible();
    await expect(page.getByPlaceholder('Your Name')).toBeVisible();
    await expect(page.getByPlaceholder('Your Company Name')).toBeVisible();
    await page.goBack(); await page.goto(webSiteUrl);
    await page.getByRole('link', { name: 'Personal Protective Equipments' }).hover();
    await page.getByRole('link', { name: 'Personal Protective Equipments' }).click();
    await expect(page.getByRole('heading', { name: 'When social distancing is not' })).toBeVisible();
    await page.getByRole('heading', { name: 'Safe Space PPE Photo Gallery' }).scrollIntoViewIfNeeded();
    await expect(page.getByRole('heading', { name: 'Safe Space PPE Photo Gallery' })).toBeVisible();
    await page.locator("//*[text()='Request a Quote']").click();
    await expect(page.locator("//*[text()='Request for Quote']").nth(1)).toBeVisible();
    await expect(page.url()).toContain(webStoreUrl);
    await page.goBack();
    await page.getByRole('link', { name: 'Aluminum Extrusion Assemblies' }).hover();
    await page.getByRole('link', { name: 'Aluminum Extrusion Assemblies' }).click();
    await expect(page.getByRole('heading', { name: 'Aluminum Extrusion Assemblies' })).toBeVisible();
    await page.getByRole('heading', { name: 'Aluminum Extrusion uses' }).scrollIntoViewIfNeeded();
    await expect(page.getByRole('heading', { name: 'Aluminum Extrusion uses' })).toBeVisible();
    await page.getByRole('link', { name: 'Learn More' }).nth(0).click();
    await page.getByRole('heading', { name: 'Machine hardguarding photo' }).scrollIntoViewIfNeeded();
    await expect(page.getByRole('heading', { name: 'Machine hardguarding photo' })).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Learn More' }).nth(1).click();
    await page.getByRole('heading', { name: 'Safe T Slot Guard Photo' }).scrollIntoViewIfNeeded();
    await expect(page.getByRole('heading', { name: 'Safe T Slot Guard Photo' })).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Learn More' }).nth(2).click();
    await page.getByRole('heading', { name: 'Machine Frames Photo Gallery' }).scrollIntoViewIfNeeded();
    await expect(page.getByRole('heading', { name: 'Machine Frames Photo Gallery' })).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Learn More' }).nth(3).click();
    await page.getByRole('heading', { name: 'Work Stations Photo Gallery' }).scrollIntoViewIfNeeded();
    await expect(page.getByRole('heading', { name: 'Work Stations Photo Gallery' })).toBeVisible();
    await page.goBack();
    await page.getByRole('link', { name: 'Learn More' }).nth(4).click();
    await page.getByRole('heading', { name: 'Safe Space PPE Photo Gallery' }).scrollIntoViewIfNeeded();
    await expect(page.getByRole('heading', { name: 'Safe Space PPE Photo Gallery' })).toBeVisible();
    await page.goBack();
    await page.locator("//*[text()='Let us know your requirement']").click();
    await expect(page.getByPlaceholder('Your Name')).toBeVisible();
    await expect(page.url()).toContain(webSiteUrl);
    await expect(page.getByPlaceholder('Your Company Name')).toBeVisible();
    await expect(page.getByPlaceholder('Your Phone number')).toBeVisible();
    await expect(page.getByPlaceholder('Your Email')).toBeVisible();
    await expect(page.getByPlaceholder('Your Message')).toBeVisible();
    await page.goBack(); await page.goto(webSiteUrl);
    await page.getByRole('link', { name: 'Sunbelt Power' }).hover();
    await page.getByRole('link', { name: 'Sunbelt Power' }).click();
    await expect(page.getByRole('heading', { name: 'Sunbelt Power Controls —' })).toBeVisible();
    await page.locator("//*[text()='Know more']").nth(0).click();
    await page.getByRole('heading', { name: 'MODELS SE-5 AND SE-' }).scrollIntoViewIfNeeded();
    await expect(page.getByRole('heading', { name: 'MODELS SE-5 AND SE-' })).toBeVisible();
    await page.getByRole('heading', { name: 'STANDARD SIMPLEX PUMP' }).scrollIntoViewIfNeeded();
    await expect(page.getByRole('heading', { name: 'STANDARD SIMPLEX PUMP' })).toBeVisible();
    await page.goBack();
    await page.locator("//*[text()='Know more']").nth(1).click();
    await expect(page.getByRole('heading', { name: 'STANDARD DUPLEX CONTROL' })).toBeVisible();
    await page.goBack();
    await page.locator("//*[text()='Know more']").nth(2).click();
    await expect(page.locator('#top')).toContainText('MODEL S-35 THREE PHASE COMBINATION');
    await page.goBack();
    await page.locator("//*[text()='Know more']").nth(3).click();
    await expect(page.locator('//*[text()="Solstice SP-100 Detention Pond Level System"]').nth(0)).toBeVisible();
    await page.goBack();
    await page.locator("//*[text()='Know more']").nth(4).click();
    await expect(page.locator('//*[text()="Super Economy Pump Controllers"]').nth(1)).toBeVisible();
    await page.locator('//*[text()="LIST PRICING"]').scrollIntoViewIfNeeded();
    await expect(page.locator('//*[text()="LIST PRICING"]')).toBeVisible();
    await page.goBack();
    await page.locator("//*[text()='Know more']").nth(5).click();
    await expect(page.locator('//*[text()="SB-2000 Pump Controller"]')).toBeVisible();
    await page.locator('//*[text()="OPTIONAL FEATURES:"]').scrollIntoViewIfNeeded();
    await expect(page.locator('//*[text()="OPTIONAL FEATURES:"]')).toBeVisible();
    await page.goBack();
    await page.locator("//*[text()='Explore all posts']").scrollIntoViewIfNeeded();
    await expect(page.locator("//*[text()='Explore all posts']")).toBeVisible();
    await helper.goBackToHomePage(page);
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
    await helper.goBackToHomePage(page);
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
    await page.waitForTimeout(2000);
    await printConsoleErrors(errors);
})