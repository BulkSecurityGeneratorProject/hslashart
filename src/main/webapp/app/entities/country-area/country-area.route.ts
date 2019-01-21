import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CountryArea } from 'app/shared/model/country-area.model';
import { CountryAreaService } from './country-area.service';
import { CountryAreaComponent } from './country-area.component';
import { CountryAreaDetailComponent } from './country-area-detail.component';
import { CountryAreaUpdateComponent } from './country-area-update.component';
import { CountryAreaDeletePopupComponent } from './country-area-delete-dialog.component';
import { ICountryArea } from 'app/shared/model/country-area.model';

@Injectable({ providedIn: 'root' })
export class CountryAreaResolve implements Resolve<ICountryArea> {
    constructor(private service: CountryAreaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CountryArea> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CountryArea>) => response.ok),
                map((countryArea: HttpResponse<CountryArea>) => countryArea.body)
            );
        }
        return of(new CountryArea());
    }
}

export const countryAreaRoute: Routes = [
    {
        path: 'country-area',
        component: CountryAreaComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.countryArea.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'country-area/:id/view',
        component: CountryAreaDetailComponent,
        resolve: {
            countryArea: CountryAreaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.countryArea.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'country-area/new',
        component: CountryAreaUpdateComponent,
        resolve: {
            countryArea: CountryAreaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.countryArea.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'country-area/:id/edit',
        component: CountryAreaUpdateComponent,
        resolve: {
            countryArea: CountryAreaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.countryArea.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const countryAreaPopupRoute: Routes = [
    {
        path: 'country-area/:id/delete',
        component: CountryAreaDeletePopupComponent,
        resolve: {
            countryArea: CountryAreaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.countryArea.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
