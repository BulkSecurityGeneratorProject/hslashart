import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Artwork } from 'app/shared/model/artwork.model';
import { ArtworkService } from '../../services/artwork.service';
import { ArtworkComponent } from './artwork.component';
import { ArtworkDetailComponent } from './artwork-detail/artwork-detail.component';
import { ArtworkUpdateComponent } from './artwork-update/artwork-update.component';
import { ArtworkDeletePopupComponent } from './artwork-delete-dialog/artwork-delete-dialog.component';
import { IArtwork } from 'app/shared/model/artwork.model';

@Injectable({ providedIn: 'root' })
export class ArtworkResolve implements Resolve<IArtwork> {
    constructor(private service: ArtworkService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Artwork> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Artwork>) => response.ok),
                map((artwork: HttpResponse<Artwork>) => artwork.body)
            );
        }
        return of(new Artwork());
    }
}

export const artworkRoute: Routes = [
    {
        path: 'artwork',
        component: ArtworkComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'hslashartApp.artwork.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'artwork/:id/view',
        component: ArtworkDetailComponent,
        resolve: {
            artwork: ArtworkResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.artwork.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'artwork/new',
        component: ArtworkUpdateComponent,
        resolve: {
            artwork: ArtworkResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.artwork.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'artwork/:id/edit',
        component: ArtworkUpdateComponent,
        resolve: {
            artwork: ArtworkResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.artwork.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const artworkPopupRoute: Routes = [
    {
        path: 'artwork/:id/delete',
        component: ArtworkDeletePopupComponent,
        resolve: {
            artwork: ArtworkResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.artwork.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
