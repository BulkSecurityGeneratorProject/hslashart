import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ICountryArea } from 'app/shared/model/country-area.model';
import { CountryAreaService } from './country-area.service';
import { ICountry } from 'app/shared/model/country.model';
import { CountryService } from 'app/entities/country';

@Component({
    selector: 'jhi-country-area-update',
    templateUrl: './country-area-update.component.html'
})
export class CountryAreaUpdateComponent implements OnInit {
    countryArea: ICountryArea;
    isSaving: boolean;

    countries: ICountry[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected countryAreaService: CountryAreaService,
        protected countryService: CountryService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ countryArea }) => {
            this.countryArea = countryArea;
        });
        this.countryService.query().subscribe(
            (res: HttpResponse<ICountry[]>) => {
                this.countries = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.countryArea.id !== undefined) {
            this.subscribeToSaveResponse(this.countryAreaService.update(this.countryArea));
        } else {
            this.subscribeToSaveResponse(this.countryAreaService.create(this.countryArea));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICountryArea>>) {
        result.subscribe((res: HttpResponse<ICountryArea>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCountryById(index: number, item: ICountry) {
        return item.id;
    }
}
