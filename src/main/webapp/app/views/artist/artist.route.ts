import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Artist } from 'app/shared/model/artist.model';
import { ArtistService } from '../../services/artist.service';
import { ArtistComponent } from './artist.component';
import { ArtistDetailComponent } from './artist-detail/artist-detail.component';
import { ArtistUpdateComponent } from './artist-update/artist-update.component';
import { ArtistDeletePopupComponent } from './artist-delete-dialog/artist-delete-dialog.component';
import { IArtist } from 'app/shared/model/artist.model';

@Injectable({ providedIn: 'root' })
export class ArtistResolve implements Resolve<IArtist> {
    constructor(private service: ArtistService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Artist> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Artist>) => response.ok),
                map((artist: HttpResponse<Artist>) => artist.body)
            );
        }
        return of(new Artist());
    }
}

export const artistRoute: Routes = [
    {
        path: 'artist',
        component: ArtistComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'hslashartApp.artist.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'artist/:id/view',
        component: ArtistDetailComponent,
        resolve: {
            artist: ArtistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.artist.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'artist/new',
        component: ArtistUpdateComponent,
        resolve: {
            artist: ArtistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.artist.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'artist/:id/edit',
        component: ArtistUpdateComponent,
        resolve: {
            artist: ArtistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.artist.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const artistPopupRoute: Routes = [
    {
        path: 'artist/:id/delete',
        component: ArtistDeletePopupComponent,
        resolve: {
            artist: ArtistResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hslashartApp.artist.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
