import { IUser } from 'app/core/user/user.model';

export const enum Gender {
    MALE = 'MALE',
    FEMALE = 'FEMALE',
    OTHER = 'OTHER'
}

export interface ICustomer {
    id?: string;
    firstName?: string;
    lastName?: string;
    gender?: Gender;
    genderOther?: string;
    phoneMain?: string;
    phoneMobile?: string;
    shippingLastName?: string;
    shippingFirstName?: string;
    shippingGender?: Gender;
    shippingGenderOther?: string;
    shippingAddressLine1?: string;
    shippingAddressLine2?: string;
    shippingCity?: string;
    shippingCommentary?: string;
    billingLastName?: string;
    billingFirstName?: string;
    billingGender?: Gender;
    billingGenderOther?: string;
    billingAddressLine1?: string;
    billingAddressLine2?: string;
    shippingPostalCode?: string;
    billingPostalCode?: string;
    shippingCountry?: string;
    shippingProvince?: string;
    shippingCountryState?: string;
    shippingTerritory?: string;
    billingCity?: string;
    billingCountry?: string;
    billingProvince?: string;
    billingTerritory?: string;
    billingCountryState?: string;
    user?: IUser;
}

export class Customer implements ICustomer {
    constructor(
        public id?: string,
        public firstName?: string,
        public lastName?: string,
        public gender?: Gender,
        public genderOther?: string,
        public phoneMain?: string,
        public phoneMobile?: string,
        public shippingLastName?: string,
        public shippingFirstName?: string,
        public shippingGender?: Gender,
        public shippingGenderOther?: string,
        public shippingAddressLine1?: string,
        public shippingAddressLine2?: string,
        public shippingCity?: string,
        public shippingCommentary?: string,
        public billingLastName?: string,
        public billingFirstName?: string,
        public billingGender?: Gender,
        public billingGenderOther?: string,
        public billingAddressLine1?: string,
        public billingAddressLine2?: string,
        public shippingPostalCode?: string,
        public billingPostalCode?: string,
        public shippingCountry?: string,
        public shippingProvince?: string,
        public shippingCountryState?: string,
        public shippingTerritory?: string,
        public billingCity?: string,
        public billingCountry?: string,
        public billingProvince?: string,
        public billingTerritory?: string,
        public billingCountryState?: string,
        public user?: IUser
    ) {}
}
