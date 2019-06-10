import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMArousal } from 'app/shared/model/m-arousal.model';

type EntityResponseType = HttpResponse<IMArousal>;
type EntityArrayResponseType = HttpResponse<IMArousal[]>;

@Injectable({ providedIn: 'root' })
export class MArousalService {
  public resourceUrl = SERVER_API_URL + 'api/m-arousals';

  constructor(protected http: HttpClient) {}

  create(mArousal: IMArousal): Observable<EntityResponseType> {
    return this.http.post<IMArousal>(this.resourceUrl, mArousal, { observe: 'response' });
  }

  update(mArousal: IMArousal): Observable<EntityResponseType> {
    return this.http.put<IMArousal>(this.resourceUrl, mArousal, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMArousal>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMArousal[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
