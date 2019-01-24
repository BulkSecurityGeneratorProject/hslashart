import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HslashartSharedModule } from 'app/shared';
import {
    ArtworkComponent,
    ArtworkDetailComponent,
    ArtworkUpdateComponent,
    ArtworkDeletePopupComponent,
    ArtworkDeleteDialogComponent,
    artworkRoute,
    artworkPopupRoute
} from './';

const ENTITY_STATES = [...artworkRoute, ...artworkPopupRoute];

@NgModule({
    imports: [HslashartSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ArtworkComponent,
        ArtworkDetailComponent,
        ArtworkUpdateComponent,
        ArtworkDeleteDialogComponent,
        ArtworkDeletePopupComponent
    ],
    entryComponents: [ArtworkComponent, ArtworkUpdateComponent, ArtworkDeleteDialogComponent, ArtworkDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HslashartArtworkModule {}
