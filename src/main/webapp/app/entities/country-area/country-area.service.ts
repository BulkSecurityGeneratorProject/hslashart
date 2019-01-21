import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICountryArea } from 'app/shared/model/country-area.model';

type EntityResponseType = HttpResponse<ICountryArea>;
type EntityArrayResponseType = HttpResponse<ICountryArea[]>;

@Injectable({ providedIn: 'root' })
export class CountryAreaService {
    public resourceUrl = SERVER_API_URL + 'api/country-areas';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/country-areas';

    constructor(protected http: HttpClient) {}

    create(countryArea: ICountryArea): Observable<EntityResponseType> {
        return this.http.post<ICountryArea>(this.resourceUrl, countryArea, { observe: 'response' });
    }

    update(countryArea: ICountryArea): Observable<EntityResponseType> {
        return this.http.put<ICountryArea>(this.resourceUrl, countryArea, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<ICountryArea>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICountryArea[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICountryArea[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
