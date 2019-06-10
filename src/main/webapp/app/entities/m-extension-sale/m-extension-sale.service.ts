import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMExtensionSale } from 'app/shared/model/m-extension-sale.model';

type EntityResponseType = HttpResponse<IMExtensionSale>;
type EntityArrayResponseType = HttpResponse<IMExtensionSale[]>;

@Injectable({ providedIn: 'root' })
export class MExtensionSaleService {
  public resourceUrl = SERVER_API_URL + 'api/m-extension-sales';

  constructor(protected http: HttpClient) {}

  create(mExtensionSale: IMExtensionSale): Observable<EntityResponseType> {
    return this.http.post<IMExtensionSale>(this.resourceUrl, mExtensionSale, { observe: 'response' });
  }

  update(mExtensionSale: IMExtensionSale): Observable<EntityResponseType> {
    return this.http.put<IMExtensionSale>(this.resourceUrl, mExtensionSale, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMExtensionSale>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMExtensionSale[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
