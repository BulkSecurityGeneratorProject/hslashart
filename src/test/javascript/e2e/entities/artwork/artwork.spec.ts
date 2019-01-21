/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ArtworkComponentsPage, ArtworkDeleteDialog, ArtworkUpdatePage } from './artwork.page-object';

const expect = chai.expect;

describe('Artwork e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let artworkUpdatePage: ArtworkUpdatePage;
    let artworkComponentsPage: ArtworkComponentsPage;
    let artworkDeleteDialog: ArtworkDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Artworks', async () => {
        await navBarPage.goToEntity('artwork');
        artworkComponentsPage = new ArtworkComponentsPage();
        expect(await artworkComponentsPage.getTitle()).to.eq('hslashartApp.artwork.home.title');
    });

    it('should load create Artwork page', async () => {
        await artworkComponentsPage.clickOnCreateButton();
        artworkUpdatePage = new ArtworkUpdatePage();
        expect(await artworkUpdatePage.getPageTitle()).to.eq('hslashartApp.artwork.home.createOrEditLabel');
        await artworkUpdatePage.cancel();
    });

    it('should create and save Artworks', async () => {
        const nbButtonsBeforeCreate = await artworkComponentsPage.countDeleteButtons();

        await artworkComponentsPage.clickOnCreateButton();
        await promise.all([artworkUpdatePage.setTitleInput('title')]);
        expect(await artworkUpdatePage.getTitleInput()).to.eq('title');
        await artworkUpdatePage.save();
        expect(await artworkUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await artworkComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Artwork', async () => {
        const nbButtonsBeforeDelete = await artworkComponentsPage.countDeleteButtons();
        await artworkComponentsPage.clickOnLastDeleteButton();

        artworkDeleteDialog = new ArtworkDeleteDialog();
        expect(await artworkDeleteDialog.getDialogTitle()).to.eq('hslashartApp.artwork.delete.question');
        await artworkDeleteDialog.clickOnConfirmButton();

        expect(await artworkComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
