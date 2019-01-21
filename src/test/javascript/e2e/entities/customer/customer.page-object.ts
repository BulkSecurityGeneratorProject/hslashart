import { element, by, ElementFinder } from 'protractor';

export class CustomerComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-customer div table .btn-danger'));
    title = element.all(by.css('jhi-customer div h2#page-heading span')).first();

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

export class CustomerUpdatePage {
    pageTitle = element(by.id('jhi-customer-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    firstNameInput = element(by.id('field_firstName'));
    lastNameInput = element(by.id('field_lastName'));
    genderOtherInput = element(by.id('field_genderOther'));
    phoneMainInput = element(by.id('field_phoneMain'));
    phoneMobileInput = element(by.id('field_phoneMobile'));
    shippingLastNameInput = element(by.id('field_shippingLastName'));
    shippingFirstNameInput = element(by.id('field_shippingFirstName'));
    shippingGenderOtherInput = element(by.id('field_shippingGenderOther'));
    shippingAddressLine1Input = element(by.id('field_shippingAddressLine1'));
    shippingAddressLine2Input = element(by.id('field_shippingAddressLine2'));
    shippingCityInput = element(by.id('field_shippingCity'));
    shippingCommentaryInput = element(by.id('field_shippingCommentary'));
    billingLastNameInput = element(by.id('field_billingLastName'));
    billingFirstNameInput = element(by.id('field_billingFirstName'));
    billingGenderOtherInput = element(by.id('field_billingGenderOther'));
    billingAddressLine1Input = element(by.id('field_billingAddressLine1'));
    billingAddressLine2Input = element(by.id('field_billingAddressLine2'));
    shippingPostalCodeInput = element(by.id('field_shippingPostalCode'));
    billingPostalCodeInput = element(by.id('field_billingPostalCode'));
    shippingCountryInput = element(by.id('field_shippingCountry'));
    shippingProvinceInput = element(by.id('field_shippingProvince'));
    shippingCountryStateInput = element(by.id('field_shippingCountryState'));
    shippingTerritoryInput = element(by.id('field_shippingTerritory'));
    billingCityInput = element(by.id('field_billingCity'));
    billingCountryInput = element(by.id('field_billingCountry'));
    billingProvinceInput = element(by.id('field_billingProvince'));
    billingTerritoryInput = element(by.id('field_billingTerritory'));
    billingCountryStateInput = element(by.id('field_billingCountryState'));
    genderSelect = element(by.id('field_gender'));
    shippingGenderSelect = element(by.id('field_shippingGender'));
    billingGenderSelect = element(by.id('field_billingGender'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setFirstNameInput(firstName) {
        await this.firstNameInput.sendKeys(firstName);
    }

    async getFirstNameInput() {
        return this.firstNameInput.getAttribute('value');
    }

    async setLastNameInput(lastName) {
        await this.lastNameInput.sendKeys(lastName);
    }

    async getLastNameInput() {
        return this.lastNameInput.getAttribute('value');
    }

    async setGenderOtherInput(genderOther) {
        await this.genderOtherInput.sendKeys(genderOther);
    }

    async getGenderOtherInput() {
        return this.genderOtherInput.getAttribute('value');
    }

    async setPhoneMainInput(phoneMain) {
        await this.phoneMainInput.sendKeys(phoneMain);
    }

    async getPhoneMainInput() {
        return this.phoneMainInput.getAttribute('value');
    }

    async setPhoneMobileInput(phoneMobile) {
        await this.phoneMobileInput.sendKeys(phoneMobile);
    }

    async getPhoneMobileInput() {
        return this.phoneMobileInput.getAttribute('value');
    }

    async setShippingLastNameInput(shippingLastName) {
        await this.shippingLastNameInput.sendKeys(shippingLastName);
    }

    async getShippingLastNameInput() {
        return this.shippingLastNameInput.getAttribute('value');
    }

    async setShippingFirstNameInput(shippingFirstName) {
        await this.shippingFirstNameInput.sendKeys(shippingFirstName);
    }

    async getShippingFirstNameInput() {
        return this.shippingFirstNameInput.getAttribute('value');
    }

    async setShippingGenderOtherInput(shippingGenderOther) {
        await this.shippingGenderOtherInput.sendKeys(shippingGenderOther);
    }

    async getShippingGenderOtherInput() {
        return this.shippingGenderOtherInput.getAttribute('value');
    }

    async setShippingAddressLine1Input(shippingAddressLine1) {
        await this.shippingAddressLine1Input.sendKeys(shippingAddressLine1);
    }

    async getShippingAddressLine1Input() {
        return this.shippingAddressLine1Input.getAttribute('value');
    }

    async setShippingAddressLine2Input(shippingAddressLine2) {
        await this.shippingAddressLine2Input.sendKeys(shippingAddressLine2);
    }

    async getShippingAddressLine2Input() {
        return this.shippingAddressLine2Input.getAttribute('value');
    }

    async setShippingCityInput(shippingCity) {
        await this.shippingCityInput.sendKeys(shippingCity);
    }

    async getShippingCityInput() {
        return this.shippingCityInput.getAttribute('value');
    }

    async setShippingCommentaryInput(shippingCommentary) {
        await this.shippingCommentaryInput.sendKeys(shippingCommentary);
    }

    async getShippingCommentaryInput() {
        return this.shippingCommentaryInput.getAttribute('value');
    }

    async setBillingLastNameInput(billingLastName) {
        await this.billingLastNameInput.sendKeys(billingLastName);
    }

    async getBillingLastNameInput() {
        return this.billingLastNameInput.getAttribute('value');
    }

    async setBillingFirstNameInput(billingFirstName) {
        await this.billingFirstNameInput.sendKeys(billingFirstName);
    }

    async getBillingFirstNameInput() {
        return this.billingFirstNameInput.getAttribute('value');
    }

    async setBillingGenderOtherInput(billingGenderOther) {
        await this.billingGenderOtherInput.sendKeys(billingGenderOther);
    }

    async getBillingGenderOtherInput() {
        return this.billingGenderOtherInput.getAttribute('value');
    }

    async setBillingAddressLine1Input(billingAddressLine1) {
        await this.billingAddressLine1Input.sendKeys(billingAddressLine1);
    }

    async getBillingAddressLine1Input() {
        return this.billingAddressLine1Input.getAttribute('value');
    }

    async setBillingAddressLine2Input(billingAddressLine2) {
        await this.billingAddressLine2Input.sendKeys(billingAddressLine2);
    }

    async getBillingAddressLine2Input() {
        return this.billingAddressLine2Input.getAttribute('value');
    }

    async setShippingPostalCodeInput(shippingPostalCode) {
        await this.shippingPostalCodeInput.sendKeys(shippingPostalCode);
    }

    async getShippingPostalCodeInput() {
        return this.shippingPostalCodeInput.getAttribute('value');
    }

    async setBillingPostalCodeInput(billingPostalCode) {
        await this.billingPostalCodeInput.sendKeys(billingPostalCode);
    }

    async getBillingPostalCodeInput() {
        return this.billingPostalCodeInput.getAttribute('value');
    }

    async setShippingCountryInput(shippingCountry) {
        await this.shippingCountryInput.sendKeys(shippingCountry);
    }

    async getShippingCountryInput() {
        return this.shippingCountryInput.getAttribute('value');
    }

    async setShippingProvinceInput(shippingProvince) {
        await this.shippingProvinceInput.sendKeys(shippingProvince);
    }

    async getShippingProvinceInput() {
        return this.shippingProvinceInput.getAttribute('value');
    }

    async setShippingCountryStateInput(shippingCountryState) {
        await this.shippingCountryStateInput.sendKeys(shippingCountryState);
    }

    async getShippingCountryStateInput() {
        return this.shippingCountryStateInput.getAttribute('value');
    }

    async setShippingTerritoryInput(shippingTerritory) {
        await this.shippingTerritoryInput.sendKeys(shippingTerritory);
    }

    async getShippingTerritoryInput() {
        return this.shippingTerritoryInput.getAttribute('value');
    }

    async setBillingCityInput(billingCity) {
        await this.billingCityInput.sendKeys(billingCity);
    }

    async getBillingCityInput() {
        return this.billingCityInput.getAttribute('value');
    }

    async setBillingCountryInput(billingCountry) {
        await this.billingCountryInput.sendKeys(billingCountry);
    }

    async getBillingCountryInput() {
        return this.billingCountryInput.getAttribute('value');
    }

    async setBillingProvinceInput(billingProvince) {
        await this.billingProvinceInput.sendKeys(billingProvince);
    }

    async getBillingProvinceInput() {
        return this.billingProvinceInput.getAttribute('value');
    }

    async setBillingTerritoryInput(billingTerritory) {
        await this.billingTerritoryInput.sendKeys(billingTerritory);
    }

    async getBillingTerritoryInput() {
        return this.billingTerritoryInput.getAttribute('value');
    }

    async setBillingCountryStateInput(billingCountryState) {
        await this.billingCountryStateInput.sendKeys(billingCountryState);
    }

    async getBillingCountryStateInput() {
        return this.billingCountryStateInput.getAttribute('value');
    }

    async setGenderSelect(gender) {
        await this.genderSelect.sendKeys(gender);
    }

    async getGenderSelect() {
        return this.genderSelect.element(by.css('option:checked')).getText();
    }

    async genderSelectLastOption() {
        await this.genderSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setShippingGenderSelect(shippingGender) {
        await this.shippingGenderSelect.sendKeys(shippingGender);
    }

    async getShippingGenderSelect() {
        return this.shippingGenderSelect.element(by.css('option:checked')).getText();
    }

    async shippingGenderSelectLastOption() {
        await this.shippingGenderSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setBillingGenderSelect(billingGender) {
        await this.billingGenderSelect.sendKeys(billingGender);
    }

    async getBillingGenderSelect() {
        return this.billingGenderSelect.element(by.css('option:checked')).getText();
    }

    async billingGenderSelectLastOption() {
        await this.billingGenderSelect
            .all(by.tagName('option'))
            .last()
            .click();
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

export class CustomerDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-customer-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-customer'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
