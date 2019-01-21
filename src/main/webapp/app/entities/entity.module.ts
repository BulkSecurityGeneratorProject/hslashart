import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HslashartCountryAreaModule } from './country-area/country-area.module';
import { HslashartCountryModule } from './country/country.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        HslashartCountryAreaModule,
        HslashartCountryModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HslashartEntityModule {}
