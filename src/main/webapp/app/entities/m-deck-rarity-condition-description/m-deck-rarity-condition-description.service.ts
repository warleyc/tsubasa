import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMDeckRarityConditionDescription } from 'app/shared/model/m-deck-rarity-condition-description.model';

type EntityResponseType = HttpResponse<IMDeckRarityConditionDescription>;
type EntityArrayResponseType = HttpResponse<IMDeckRarityConditionDescription[]>;

@Injectable({ providedIn: 'root' })
export class MDeckRarityConditionDescriptionService {
  public resourceUrl = SERVER_API_URL + 'api/m-deck-rarity-condition-descriptions';

  constructor(protected http: HttpClient) {}

  create(mDeckRarityConditionDescription: IMDeckRarityConditionDescription): Observable<EntityResponseType> {
    return this.http.post<IMDeckRarityConditionDescription>(this.resourceUrl, mDeckRarityConditionDescription, { observe: 'response' });
  }

  update(mDeckRarityConditionDescription: IMDeckRarityConditionDescription): Observable<EntityResponseType> {
    return this.http.put<IMDeckRarityConditionDescription>(this.resourceUrl, mDeckRarityConditionDescription, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMDeckRarityConditionDescription>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMDeckRarityConditionDescription[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
