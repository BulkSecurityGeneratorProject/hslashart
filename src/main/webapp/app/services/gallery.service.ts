import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGallery } from 'app/shared/model/gallery.model';

type EntityResponseType = HttpResponse<IGallery>;
type EntityArrayResponseType = HttpResponse<IGallery[]>;

@Injectable({ providedIn: 'root' })
export class GalleryService {
    public resourceUrl = SERVER_API_URL + 'api/galleries';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/galleries';

    constructor(protected http: HttpClient) {}

    create(gallery: IGallery): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(gallery);
        return this.http
            .post<IGallery>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(gallery: IGallery): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(gallery);
        return this.http
            .put<IGallery>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http
            .get<IGallery>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGallery[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGallery[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(gallery: IGallery): IGallery {
        const copy: IGallery = Object.assign({}, gallery, {
            creationDate: gallery.creationDate != null && gallery.creationDate.isValid() ? gallery.creationDate.format(DATE_FORMAT) : null
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
            res.body.forEach((gallery: IGallery) => {
                gallery.creationDate = gallery.creationDate != null ? moment(gallery.creationDate) : null;
            });
        }
        return res;
    }
}
