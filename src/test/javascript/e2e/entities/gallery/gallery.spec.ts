/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GalleryComponentsPage, GalleryDeleteDialog, GalleryUpdatePage } from './gallery.page-object';

const expect = chai.expect;

describe('Gallery e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let galleryUpdatePage: GalleryUpdatePage;
    let galleryComponentsPage: GalleryComponentsPage;
    let galleryDeleteDialog: GalleryDeleteDialog;

    before(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Galleries', async () => {
        await navBarPage.goToEntity('gallery');
        galleryComponentsPage = new GalleryComponentsPage();
        expect(await galleryComponentsPage.getTitle()).to.eq('hslashartApp.gallery.home.title');
    });

    it('should load create Gallery page', async () => {
        await galleryComponentsPage.clickOnCreateButton();
        galleryUpdatePage = new GalleryUpdatePage();
        expect(await galleryUpdatePage.getPageTitle()).to.eq('hslashartApp.gallery.home.createOrEditLabel');
        await galleryUpdatePage.cancel();
    });

    it('should create and save Galleries', async () => {
        const nbButtonsBeforeCreate = await galleryComponentsPage.countDeleteButtons();

        await galleryComponentsPage.clickOnCreateButton();
        await promise.all([
            galleryUpdatePage.setTitleInput('title')
            // galleryUpdatePage.artworkSelectLastOption(),
        ]);
        expect(await galleryUpdatePage.getTitleInput()).to.eq('title');
        await galleryUpdatePage.save();
        expect(await galleryUpdatePage.getSaveButton().isPresent()).to.be.false;

        expect(await galleryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1);
    });

    it('should delete last Gallery', async () => {
        const nbButtonsBeforeDelete = await galleryComponentsPage.countDeleteButtons();
        await galleryComponentsPage.clickOnLastDeleteButton();

        galleryDeleteDialog = new GalleryDeleteDialog();
        expect(await galleryDeleteDialog.getDialogTitle()).to.eq('hslashartApp.gallery.delete.question');
        await galleryDeleteDialog.clickOnConfirmButton();

        expect(await galleryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
    });

    after(async () => {
        await navBarPage.autoSignOut();
    });
});
