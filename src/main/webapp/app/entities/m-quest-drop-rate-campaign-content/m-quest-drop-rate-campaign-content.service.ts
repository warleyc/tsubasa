import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMQuestDropRateCampaignContent } from 'app/shared/model/m-quest-drop-rate-campaign-content.model';

type EntityResponseType = HttpResponse<IMQuestDropRateCampaignContent>;
type EntityArrayResponseType = HttpResponse<IMQuestDropRateCampaignContent[]>;

@Injectable({ providedIn: 'root' })
export class MQuestDropRateCampaignContentService {
  public resourceUrl = SERVER_API_URL + 'api/m-quest-drop-rate-campaign-contents';

  constructor(protected http: HttpClient) {}

  create(mQuestDropRateCampaignContent: IMQuestDropRateCampaignContent): Observable<EntityResponseType> {
    return this.http.post<IMQuestDropRateCampaignContent>(this.resourceUrl, mQuestDropRateCampaignContent, { observe: 'response' });
  }

  update(mQuestDropRateCampaignContent: IMQuestDropRateCampaignContent): Observable<EntityResponseType> {
    return this.http.put<IMQuestDropRateCampaignContent>(this.resourceUrl, mQuestDropRateCampaignContent, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMQuestDropRateCampaignContent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMQuestDropRateCampaignContent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
