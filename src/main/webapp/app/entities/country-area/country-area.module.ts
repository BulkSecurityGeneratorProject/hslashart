import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HslashartSharedModule } from 'app/shared';
import {
    CountryAreaComponent,
    CountryAreaDetailComponent,
    CountryAreaUpdateComponent,
    CountryAreaDeletePopupComponent,
    CountryAreaDeleteDialogComponent,
    countryAreaRoute,
    countryAreaPopupRoute
} from './';

const ENTITY_STATES = [...countryAreaRoute, ...countryAreaPopupRoute];

@NgModule({
    imports: [HslashartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CountryAreaComponent,
        CountryAreaDetailComponent,
        CountryAreaUpdateComponent,
        CountryAreaDeleteDialogComponent,
        CountryAreaDeletePopupComponent
    ],
    entryComponents: [CountryAreaComponent, CountryAreaUpdateComponent, CountryAreaDeleteDialogComponent, CountryAreaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HslashartCountryAreaModule {}
