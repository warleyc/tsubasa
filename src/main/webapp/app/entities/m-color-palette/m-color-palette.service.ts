import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMColorPalette } from 'app/shared/model/m-color-palette.model';

type EntityResponseType = HttpResponse<IMColorPalette>;
type EntityArrayResponseType = HttpResponse<IMColorPalette[]>;

@Injectable({ providedIn: 'root' })
export class MColorPaletteService {
  public resourceUrl = SERVER_API_URL + 'api/m-color-palettes';

  constructor(protected http: HttpClient) {}

  create(mColorPalette: IMColorPalette): Observable<EntityResponseType> {
    return this.http.post<IMColorPalette>(this.resourceUrl, mColorPalette, { observe: 'response' });
  }

  update(mColorPalette: IMColorPalette): Observable<EntityResponseType> {
    return this.http.put<IMColorPalette>(this.resourceUrl, mColorPalette, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMColorPalette>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMColorPalette[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
