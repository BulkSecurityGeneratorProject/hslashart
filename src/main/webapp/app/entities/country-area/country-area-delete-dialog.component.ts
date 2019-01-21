import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICountryArea } from 'app/shared/model/country-area.model';
import { CountryAreaService } from './country-area.service';

@Component({
    selector: 'jhi-country-area-delete-dialog',
    templateUrl: './country-area-delete-dialog.component.html'
})
export class CountryAreaDeleteDialogComponent {
    countryArea: ICountryArea;

    constructor(
        protected countryAreaService: CountryAreaService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.countryAreaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'countryAreaListModification',
                content: 'Deleted an countryArea'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-country-area-delete-popup',
    template: ''
})
export class CountryAreaDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ countryArea }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CountryAreaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.countryArea = countryArea;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
