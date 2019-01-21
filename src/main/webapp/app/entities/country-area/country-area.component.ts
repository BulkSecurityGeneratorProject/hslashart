import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICountryArea } from 'app/shared/model/country-area.model';
import { AccountService } from 'app/core';
import { CountryAreaService } from './country-area.service';

@Component({
    selector: 'jhi-country-area',
    templateUrl: './country-area.component.html'
})
export class CountryAreaComponent implements OnInit, OnDestroy {
    countryAreas: ICountryArea[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        protected countryAreaService: CountryAreaService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected activatedRoute: ActivatedRoute,
        protected accountService: AccountService
    ) {
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.countryAreaService
                .search({
                    query: this.currentSearch
                })
                .subscribe(
                    (res: HttpResponse<ICountryArea[]>) => (this.countryAreas = res.body),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.countryAreaService.query().subscribe(
            (res: HttpResponse<ICountryArea[]>) => {
                this.countryAreas = res.body;
                this.currentSearch = '';
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCountryAreas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICountryArea) {
        return item.id;
    }

    registerChangeInCountryAreas() {
        this.eventSubscriber = this.eventManager.subscribe('countryAreaListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
