import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

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
        return this.http.post<IArtwork>(this.resourceUrl, artwork, { observe: 'response' });
    }

    update(artwork: IArtwork): Observable<EntityResponseType> {
        return this.http.put<IArtwork>(this.resourceUrl, artwork, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IArtwork>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IArtwork[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IArtwork[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
