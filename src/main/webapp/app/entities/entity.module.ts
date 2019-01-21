import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HslashartCountryAreaModule } from './country-area/country-area.module';
import { HslashartCountryModule } from './country/country.module';
import { HslashartArtworkModule } from './artwork/artwork.module';
import { HslashartGalleryModule } from './gallery/gallery.module';
import { HslashartArtistModule } from './artist/artist.module';
import { HslashartCustomerModule } from './customer/customer.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        HslashartCountryAreaModule,
        HslashartCountryModule,
        HslashartArtworkModule,
        HslashartGalleryModule,
        HslashartArtistModule,
        HslashartCustomerModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HslashartEntityModule {}
