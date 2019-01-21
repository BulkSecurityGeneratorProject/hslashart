/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CountryAreaComponentsPage, CountryAreaDeleteDialog, CountryAreaUpdatePage } from './country-area.page-object';

const expect = chai.expect;

describe('CountryArea e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let countryAreaUpdatePage: CountryAreaUpdatePage;
    let countryAreaComponentsPage: CountryAreaComponentsPage;
    let countryAreaDeleteDialog: CountryAreaDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load CountryAreas', async () => {
        await navBarPage.goToEntity('country-area');
        countryAreaComponentsPage = new CountryAreaComponentsPage();
        expect(await countryAreaComponentsPage.getTitle()).to.eq('hslashartApp.countryArea.home.title');
    });

    it('should load create CountryArea page', async () => {
        await countryAreaComponentsPage.clickOnCreateButton();
        countryAreaUpdatePage = new CountryAreaUpdatePage();
        expect(await countryAreaUpdatePage.getPageTitle()).to.eq('hslashartApp.countryArea.home.createOrEditLabel');
        await countryAreaUpdatePage.cancel();
    });

    it('should create and save CountryAreas', async () => {
        const nbButtonsBeforeCreate = await countryAreaComponentsPage.countDeleteButtons();

        await countryAreaComponentsPage.clickOnCreateButton();
        await promise.all([
            countryAreaUpdatePage.setNameInput('name'),
            countryAreaUpdatePage.setCodeInput('code'),
            countryAreaUpdatePage.countrySelectLastOption()
        ]);
        expect(await countryAreaUpdatePage.getNameInput()).to.eq('name');
        expect(await countryAreaUpdatePage.getCodeInput()).to.eq('code');
        await countryAreaUpdatePage.save();
        expect(await countryAreaUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await countryAreaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last CountryArea', async () => {
        const nbButtonsBeforeDelete = await countryAreaComponentsPage.countDeleteButtons();
        await countryAreaComponentsPage.clickOnLastDeleteButton();

        countryAreaDeleteDialog = new CountryAreaDeleteDialog();
        expect(await countryAreaDeleteDialog.getDialogTitle()).to.eq('hslashartApp.countryArea.delete.question');
        await countryAreaDeleteDialog.clickOnConfirmButton();

        expect(await countryAreaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
