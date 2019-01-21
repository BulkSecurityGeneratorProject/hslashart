/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ArtistComponentsPage, ArtistDeleteDialog, ArtistUpdatePage } from './artist.page-object';

const expect = chai.expect;

describe('Artist e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let artistUpdatePage: ArtistUpdatePage;
    let artistComponentsPage: ArtistComponentsPage;
    let artistDeleteDialog: ArtistDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Artists', async () => {
        await navBarPage.goToEntity('artist');
        artistComponentsPage = new ArtistComponentsPage();
        expect(await artistComponentsPage.getTitle()).to.eq('hslashartApp.artist.home.title');
    });

    it('should load create Artist page', async () => {
        await artistComponentsPage.clickOnCreateButton();
        artistUpdatePage = new ArtistUpdatePage();
        expect(await artistUpdatePage.getPageTitle()).to.eq('hslashartApp.artist.home.createOrEditLabel');
        await artistUpdatePage.cancel();
    });

    it('should create and save Artists', async () => {
        const nbButtonsBeforeCreate = await artistComponentsPage.countDeleteButtons();

        await artistComponentsPage.clickOnCreateButton();
        await promise.all([
            artistUpdatePage.setFirstNameInput('firstName'),
            artistUpdatePage.setLastNameInput('lastName'),
            artistUpdatePage.setBirthDateInput('2000-12-31'),
            artistUpdatePage.setCityInput('city'),
            artistUpdatePage.setCountryInput('country'),
            artistUpdatePage.setBiographyInput('biography'),
            artistUpdatePage.setCvInput('cv')
        ]);
        expect(await artistUpdatePage.getFirstNameInput()).to.eq('firstName');
        expect(await artistUpdatePage.getLastNameInput()).to.eq('lastName');
        expect(await artistUpdatePage.getBirthDateInput()).to.eq('2000-12-31');
        expect(await artistUpdatePage.getCityInput()).to.eq('city');
        expect(await artistUpdatePage.getCountryInput()).to.eq('country');
        expect(await artistUpdatePage.getBiographyInput()).to.eq('biography');
        expect(await artistUpdatePage.getCvInput()).to.eq('cv');
        await artistUpdatePage.save();
        expect(await artistUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await artistComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Artist', async () => {
        const nbButtonsBeforeDelete = await artistComponentsPage.countDeleteButtons();
        await artistComponentsPage.clickOnLastDeleteButton();

        artistDeleteDialog = new ArtistDeleteDialog();
        expect(await artistDeleteDialog.getDialogTitle()).to.eq('hslashartApp.artist.delete.question');
        await artistDeleteDialog.clickOnConfirmButton();

        expect(await artistComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
