import { element, by, ElementFinder } from 'protractor';

export class GalleryComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-gallery div table .btn-danger'));
    title = element.all(by.css('jhi-gallery div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class GalleryUpdatePage {
    pageTitle = element(by.id('jhi-gallery-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    titleInput = element(by.id('field_title'));
    artworkSelect = element(by.id('field_artwork'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setTitleInput(title) {
        await this.titleInput.sendKeys(title);
    }

    async getTitleInput() {
        return this.titleInput.getAttribute('value');
    }

    async artworkSelectLastOption() {
        await this.artworkSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async artworkSelectOption(option) {
        await this.artworkSelect.sendKeys(option);
    }

    getArtworkSelect(): ElementFinder {
        return this.artworkSelect;
    }

    async getArtworkSelectedOption() {
        return this.artworkSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class GalleryDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-gallery-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-gallery'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
