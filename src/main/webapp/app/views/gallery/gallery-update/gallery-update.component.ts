import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IGallery } from 'app/shared/model/gallery.model';
import { GalleryService } from '../../../services/gallery.service';
import { IArtwork } from 'app/shared/model/artwork.model';
import { ArtworkService } from 'app/entities/artwork';
import { IArtist } from 'app/shared/model/artist.model';
import { ArtistService } from 'app/entities/artist';

@Component({
    selector: 'jhi-gallery-update',
    templateUrl: './gallery-update.component.html'
})
export class GalleryUpdateComponent implements OnInit {
    gallery: IGallery;
    isSaving: boolean;

    artworks: IArtwork[];

    artists: IArtist[];
    creationDateDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected galleryService: GalleryService,
        protected artworkService: ArtworkService,
        protected artistService: ArtistService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ gallery }) => {
            this.gallery = gallery;
        });
        this.artworkService.query().subscribe(
            (res: HttpResponse<IArtwork[]>) => {
                this.artworks = res.body;
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
        if (this.gallery.id !== undefined) {
            this.subscribeToSaveResponse(this.galleryService.update(this.gallery));
        } else {
            this.subscribeToSaveResponse(this.galleryService.create(this.gallery));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IGallery>>) {
        result.subscribe((res: HttpResponse<IGallery>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackArtworkById(index: number, item: IArtwork) {
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
