import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HslashartSharedModule } from 'app/shared';
import {
    ArtistComponent,
    ArtistDetailComponent,
    ArtistUpdateComponent,
    ArtistDeletePopupComponent,
    ArtistDeleteDialogComponent,
    artistRoute,
    artistPopupRoute
} from './';

const ENTITY_STATES = [...artistRoute, ...artistPopupRoute];

@NgModule({
    imports: [HslashartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ArtistComponent, ArtistDetailComponent, ArtistUpdateComponent, ArtistDeleteDialogComponent, ArtistDeletePopupComponent],
    entryComponents: [ArtistComponent, ArtistUpdateComponent, ArtistDeleteDialogComponent, ArtistDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HslashartArtistModule {}
