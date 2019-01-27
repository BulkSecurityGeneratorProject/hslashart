import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IArtwork } from 'app/shared/model/artwork.model';

type EntityResponseType = HttpResponse<IArtwork>;
type EntityArrayResponseType = HttpResponse<IArtwork[]>;

@Injectable({ providedIn: 'root' })
export class ArtworkService {
    public resourceUrl = SERVER_API_URL + 'api/artworks';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/artworks';

    constructor(protected http: HttpClient) {}

    create(artwork: IArtwork): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(artwork);
        return this.http
            .post<IArtwork>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(artwork: IArtwork): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(artwork);
        return this.http
            .put<IArtwork>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http
            .get<IArtwork>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IArtwork[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IArtwork[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(artwork: IArtwork): IArtwork {
        const copy: IArtwork = Object.assign({}, artwork, {
            creationDate: artwork.creationDate != null && artwork.creationDate.isValid() ? artwork.creationDate.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.creationDate = res.body.creationDate != null ? moment(res.body.creationDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((artwork: IArtwork) => {
                artwork.creationDate = artwork.creationDate != null ? moment(artwork.creationDate) : null;
            });
        }
        return res;
    }
}
