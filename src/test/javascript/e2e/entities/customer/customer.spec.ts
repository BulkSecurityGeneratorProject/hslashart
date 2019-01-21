/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CustomerComponentsPage, CustomerDeleteDialog, CustomerUpdatePage } from './customer.page-object';

const expect = chai.expect;

describe('Customer e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let customerUpdatePage: CustomerUpdatePage;
    let customerComponentsPage: CustomerComponentsPage;
    let customerDeleteDialog: CustomerDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Customers', async () => {
        await navBarPage.goToEntity('customer');
        customerComponentsPage = new CustomerComponentsPage();
        expect(await customerComponentsPage.getTitle()).to.eq('hslashartApp.customer.home.title');
    });

    it('should load create Customer page', async () => {
        await customerComponentsPage.clickOnCreateButton();
        customerUpdatePage = new CustomerUpdatePage();
        expect(await customerUpdatePage.getPageTitle()).to.eq('hslashartApp.customer.home.createOrEditLabel');
        await customerUpdatePage.cancel();
    });

    it('should create and save Customers', async () => {
        const nbButtonsBeforeCreate = await customerComponentsPage.countDeleteButtons();

        await customerComponentsPage.clickOnCreateButton();
        await promise.all([
            customerUpdatePage.setFirstNameInput('firstName'),
            customerUpdatePage.setLastNameInput('lastName'),
            customerUpdatePage.setGenderOtherInput('genderOther'),
            customerUpdatePage.setPhoneMainInput('phoneMain'),
            customerUpdatePage.setPhoneMobileInput('phoneMobile'),
            customerUpdatePage.setShippingLastNameInput('shippingLastName'),
            customerUpdatePage.setShippingFirstNameInput('shippingFirstName'),
            customerUpdatePage.setShippingGenderOtherInput('shippingGenderOther'),
            customerUpdatePage.setShippingAddressLine1Input('shippingAddressLine1'),
            customerUpdatePage.setShippingAddressLine2Input('shippingAddressLine2'),
            customerUpdatePage.setShippingCityInput('shippingCity'),
            customerUpdatePage.setShippingCommentaryInput('shippingCommentary'),
            customerUpdatePage.setBillingLastNameInput('billingLastName'),
            customerUpdatePage.setBillingFirstNameInput('billingFirstName'),
            customerUpdatePage.setBillingGenderOtherInput('billingGenderOther'),
            customerUpdatePage.setBillingAddressLine1Input('billingAddressLine1'),
            customerUpdatePage.setBillingAddressLine2Input('billingAddressLine2'),
            customerUpdatePage.setShippingPostalCodeInput('shippingPostalCode'),
            customerUpdatePage.setBillingPostalCodeInput('billingPostalCode'),
            customerUpdatePage.setShippingCountryInput('shippingCountry'),
            customerUpdatePage.setShippingProvinceInput('shippingProvince'),
            customerUpdatePage.setShippingCountryStateInput('shippingCountryState'),
            customerUpdatePage.setShippingTerritoryInput('shippingTerritory'),
            customerUpdatePage.setBillingCityInput('billingCity'),
            customerUpdatePage.setBillingCountryInput('billingCountry'),
            customerUpdatePage.setBillingProvinceInput('billingProvince'),
            customerUpdatePage.setBillingTerritoryInput('billingTerritory'),
            customerUpdatePage.setBillingCountryStateInput('billingCountryState'),
            customerUpdatePage.genderSelectLastOption(),
            customerUpdatePage.shippingGenderSelectLastOption(),
            customerUpdatePage.billingGenderSelectLastOption()
        ]);
        expect(await customerUpdatePage.getFirstNameInput()).to.eq('firstName');
        expect(await customerUpdatePage.getLastNameInput()).to.eq('lastName');
        expect(await customerUpdatePage.getGenderOtherInput()).to.eq('genderOther');
        expect(await customerUpdatePage.getPhoneMainInput()).to.eq('phoneMain');
        expect(await customerUpdatePage.getPhoneMobileInput()).to.eq('phoneMobile');
        expect(await customerUpdatePage.getShippingLastNameInput()).to.eq('shippingLastName');
        expect(await customerUpdatePage.getShippingFirstNameInput()).to.eq('shippingFirstName');
        expect(await customerUpdatePage.getShippingGenderOtherInput()).to.eq('shippingGenderOther');
        expect(await customerUpdatePage.getShippingAddressLine1Input()).to.eq('shippingAddressLine1');
        expect(await customerUpdatePage.getShippingAddressLine2Input()).to.eq('shippingAddressLine2');
        expect(await customerUpdatePage.getShippingCityInput()).to.eq('shippingCity');
        expect(await customerUpdatePage.getShippingCommentaryInput()).to.eq('shippingCommentary');
        expect(await customerUpdatePage.getBillingLastNameInput()).to.eq('billingLastName');
        expect(await customerUpdatePage.getBillingFirstNameInput()).to.eq('billingFirstName');
        expect(await customerUpdatePage.getBillingGenderOtherInput()).to.eq('billingGenderOther');
        expect(await customerUpdatePage.getBillingAddressLine1Input()).to.eq('billingAddressLine1');
        expect(await customerUpdatePage.getBillingAddressLine2Input()).to.eq('billingAddressLine2');
        expect(await customerUpdatePage.getShippingPostalCodeInput()).to.eq('shippingPostalCode');
        expect(await customerUpdatePage.getBillingPostalCodeInput()).to.eq('billingPostalCode');
        expect(await customerUpdatePage.getShippingCountryInput()).to.eq('shippingCountry');
        expect(await customerUpdatePage.getShippingProvinceInput()).to.eq('shippingProvince');
        expect(await customerUpdatePage.getShippingCountryStateInput()).to.eq('shippingCountryState');
        expect(await customerUpdatePage.getShippingTerritoryInput()).to.eq('shippingTerritory');
        expect(await customerUpdatePage.getBillingCityInput()).to.eq('billingCity');
        expect(await customerUpdatePage.getBillingCountryInput()).to.eq('billingCountry');
        expect(await customerUpdatePage.getBillingProvinceInput()).to.eq('billingProvince');
        expect(await customerUpdatePage.getBillingTerritoryInput()).to.eq('billingTerritory');
        expect(await customerUpdatePage.getBillingCountryStateInput()).to.eq('billingCountryState');
        await customerUpdatePage.save();
        expect(await customerUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await customerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Customer', async () => {
        const nbButtonsBeforeDelete = await customerComponentsPage.countDeleteButtons();
        await customerComponentsPage.clickOnLastDeleteButton();

        customerDeleteDialog = new CustomerDeleteDialog();
        expect(await customerDeleteDialog.getDialogTitle()).to.eq('hslashartApp.customer.delete.question');
        await customerDeleteDialog.clickOnConfirmButton();

        expect(await customerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
