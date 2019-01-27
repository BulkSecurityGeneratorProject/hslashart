import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IArtwork } from 'app/shared/model/artwork.model';
import { ArtworkService } from './artwork.service';
import { IGallery } from 'app/shared/model/gallery.model';
import { GalleryService } from 'app/entities/gallery';
import { IArtist } from 'app/shared/model/artist.model';
import { ArtistService } from 'app/entities/artist';

@Component({
    selector: 'jhi-artwork-update',
    templateUrl: './artwork-update.component.html'
})
export class ArtworkUpdateComponent implements OnInit {
    artwork: IArtwork;
    isSaving: boolean;

    galleries: IGallery[];

    artists: IArtist[];
    creationDateDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected artworkService: ArtworkService,
        protected galleryService: GalleryService,
        protected artistService: ArtistService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ artwork }) => {
            this.artwork = artwork;
        });
        this.galleryService.query().subscribe(
            (res: HttpResponse<IGallery[]>) => {
                this.galleries = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.artistService.query().subscribe(
            (res: HttpResponse<IArtist[]>) => {
                this.artists = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.artwork.id !== undefined) {
            this.subscribeToSaveResponse(this.artworkService.update(this.artwork));
        } else {
            this.subscribeToSaveResponse(this.artworkService.create(this.artwork));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IArtwork>>) {
        result.subscribe((res: HttpResponse<IArtwork>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackGalleryById(index: number, item: IGallery) {
        return item.id;
    }

    trackArtistById(index: number, item: IArtist) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
