import { ICountry } from 'app/shared/model//country.model';

export interface ICountryArea {
    id?: string;
    name?: string;
    code?: string;
    country?: ICountry;
}

export class CountryArea implements ICountryArea {
    constructor(public id?: string, public name?: string, public code?: string, public country?: ICountry) {}
}
