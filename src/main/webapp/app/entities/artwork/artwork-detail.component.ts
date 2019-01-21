import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IArtwork } from 'app/shared/model/artwork.model';

@Component({
    selector: 'jhi-artwork-detail',
    templateUrl: './artwork-detail.component.html'
})
export class ArtworkDetailComponent implements OnInit {
    artwork: IArtwork;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ artwork }) => {
            this.artwork = artwork;
        });
    }

    previousState() {
        window.history.back();
    }
}
