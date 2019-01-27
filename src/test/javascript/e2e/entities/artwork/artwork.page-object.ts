import { element, by, ElementFinder } from 'protractor';

export class ArtworkComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-artwork div table .btn-danger'));
    title = element.all(by.css('jhi-artwork div h2#page-heading span')).first();

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

export class ArtworkUpdatePage {
    pageTitle = element(by.id('jhi-artwork-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    titleInput = element(by.id('field_title'));
    descriptionInput = element(by.id('field_description'));
    priceInput = element(by.id('field_price'));
    currencySelect = element(by.id('field_currency'));
    imageInput = element(by.id('field_image'));
    thumbnailInput = element(by.id('field_thumbnail'));
    dimensionsInput = element(by.id('field_dimensions'));
    creationDateInput = element(by.id('field_creationDate'));
    creditLineInput = element(by.id('field_creditLine'));
    copyrightImageInput = element(by.id('field_copyrightImage'));
    classificationInput = element(by.id('field_classification'));
    availabilitySelect = element(by.id('field_availability'));
    artistSelect = element(by.id('field_artist'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setTitleInput(title) {
        await this.titleInput.sendKeys(title);
    }

    async getTitleInput() {
        return this.titleInput.getAttribute('value');
    }

    async setDescriptionInput(description) {
        await this.descriptionInput.sendKeys(description);
    }

    async getDescriptionInput() {
        return this.descriptionInput.getAttribute('value');
    }

    async setPriceInput(price) {
        await this.priceInput.sendKeys(price);
    }

    async getPriceInput() {
        return this.priceInput.getAttribute('value');
    }

    async setCurrencySelect(currency) {
        await this.currencySelect.sendKeys(currency);
    }

    async getCurrencySelect() {
        return this.currencySelect.element(by.css('option:checked')).getText();
    }

    async currencySelectLastOption() {
        await this.currencySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setImageInput(image) {
        await this.imageInput.sendKeys(image);
    }

    async getImageInput() {
        return this.imageInput.getAttribute('value');
    }

    async setThumbnailInput(thumbnail) {
        await this.thumbnailInput.sendKeys(thumbnail);
    }

    async getThumbnailInput() {
        return this.thumbnailInput.getAttribute('value');
    }

    async setDimensionsInput(dimensions) {
        await this.dimensionsInput.sendKeys(dimensions);
    }

    async getDimensionsInput() {
        return this.dimensionsInput.getAttribute('value');
    }

    async setCreationDateInput(creationDate) {
        await this.creationDateInput.sendKeys(creationDate);
    }

    async getCreationDateInput() {
        return this.creationDateInput.getAttribute('value');
    }

    async setCreditLineInput(creditLine) {
        await this.creditLineInput.sendKeys(creditLine);
    }

    async getCreditLineInput() {
        return this.creditLineInput.getAttribute('value');
    }

    async setCopyrightImageInput(copyrightImage) {
        await this.copyrightImageInput.sendKeys(copyrightImage);
    }

    async getCopyrightImageInput() {
        return this.copyrightImageInput.getAttribute('value');
    }

    async setClassificationInput(classification) {
        await this.classificationInput.sendKeys(classification);
    }

    async getClassificationInput() {
        return this.classificationInput.getAttribute('value');
    }

    async setAvailabilitySelect(availability) {
        await this.availabilitySelect.sendKeys(availability);
    }

    async getAvailabilitySelect() {
        return this.availabilitySelect.element(by.css('option:checked')).getText();
    }

    async availabilitySelectLastOption() {
        await this.availabilitySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async artistSelectLastOption() {
        await this.artistSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async artistSelectOption(option) {
        await this.artistSelect.sendKeys(option);
    }

    getArtistSelect(): ElementFinder {
        return this.artistSelect;
    }

    async getArtistSelectedOption() {
        return this.artistSelect.element(by.css('option:checked')).getText();
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

export class ArtworkDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-artwork-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-artwork'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
