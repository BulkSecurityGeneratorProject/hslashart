import { ICountryArea } from 'app/shared/model//country-area.model';

export interface ICountry {
    id?: string;
    name?: string;
    code?: string;
    provinces?: ICountryArea[];
    territories?: ICountryArea[];
    countryStates?: ICountryArea[];
    cities?: ICountryArea[];
}

export class Country implements ICountry {
    constructor(
        public id?: string,
        public name?: string,
        public code?: string,
        public provinces?: ICountryArea[],
        public territories?: ICountryArea[],
        public countryStates?: ICountryArea[],
        public cities?: ICountryArea[]
    ) {}
}
