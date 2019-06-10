import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMAnnounceText } from 'app/shared/model/m-announce-text.model';

type EntityResponseType = HttpResponse<IMAnnounceText>;
type EntityArrayResponseType = HttpResponse<IMAnnounceText[]>;

@Injectable({ providedIn: 'root' })
export class MAnnounceTextService {
  public resourceUrl = SERVER_API_URL + 'api/m-announce-texts';

  constructor(protected http: HttpClient) {}

  create(mAnnounceText: IMAnnounceText): Observable<EntityResponseType> {
    return this.http.post<IMAnnounceText>(this.resourceUrl, mAnnounceText, { observe: 'response' });
  }

  update(mAnnounceText: IMAnnounceText): Observable<EntityResponseType> {
    return this.http.put<IMAnnounceText>(this.resourceUrl, mAnnounceText, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMAnnounceText>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMAnnounceText[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
