import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';

import { IArtist } from 'app/shared/model/artist.model';
import { ArtistService } from './artist.service';

@Component({
    selector: 'jhi-artist-update',
    templateUrl: './artist-update.component.html'
})
export class ArtistUpdateComponent implements OnInit {
    artist: IArtist;
    isSaving: boolean;
    birthDateDp: any;

    constructor(protected artistService: ArtistService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ artist }) => {
            this.artist = artist;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.artist.id !== undefined) {
            this.subscribeToSaveResponse(this.artistService.update(this.artist));
        } else {
            this.subscribeToSaveResponse(this.artistService.create(this.artist));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IArtist>>) {
        result.subscribe((res: HttpResponse<IArtist>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
