import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { HslashartArtworkModule } from './artwork/artwork.module';
import { HslashartGalleryModule } from './gallery/gallery.module';
import { HslashartArtistModule } from './artist/artist.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        HslashartArtworkModule,
        HslashartGalleryModule,
        HslashartArtistModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HslashartViewModule {}
