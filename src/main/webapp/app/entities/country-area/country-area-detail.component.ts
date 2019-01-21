import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICountryArea } from 'app/shared/model/country-area.model';

@Component({
    selector: 'jhi-country-area-detail',
    templateUrl: './country-area-detail.component.html'
})
export class CountryAreaDetailComponent implements OnInit {
    countryArea: ICountryArea;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ countryArea }) => {
            this.countryArea = countryArea;
        });
    }

    previousState() {
        window.history.back();
    }
}
