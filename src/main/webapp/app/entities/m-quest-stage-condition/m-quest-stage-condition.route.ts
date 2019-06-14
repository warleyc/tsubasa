import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestStageCondition } from 'app/shared/model/m-quest-stage-condition.model';
import { MQuestStageConditionService } from './m-quest-stage-condition.service';
import { MQuestStageConditionComponent } from './m-quest-stage-condition.component';
import { MQuestStageConditionDetailComponent } from './m-quest-stage-condition-detail.component';
import { MQuestStageConditionUpdateComponent } from './m-quest-stage-condition-update.component';
import { MQuestStageConditionDeletePopupComponent } from './m-quest-stage-condition-delete-dialog.component';
import { IMQuestStageCondition } from 'app/shared/model/m-quest-stage-condition.model';

@Injectable({ providedIn: 'root' })
export class MQuestStageConditionResolve implements Resolve<IMQuestStageCondition> {
  constructor(private service: MQuestStageConditionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestStageCondition> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestStageCondition>) => response.ok),
        map((mQuestStageCondition: HttpResponse<MQuestStageCondition>) => mQuestStageCondition.body)
      );
    }
    return of(new MQuestStageCondition());
  }
}

export const mQuestStageConditionRoute: Routes = [
  {
    path: '',
    component: MQuestStageConditionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestStageConditions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestStageConditionDetailComponent,
    resolve: {
      mQuestStageCondition: MQuestStageConditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStageConditions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestStageConditionUpdateComponent,
    resolve: {
      mQuestStageCondition: MQuestStageConditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStageConditions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestStageConditionUpdateComponent,
    resolve: {
      mQuestStageCondition: MQuestStageConditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStageConditions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestStageConditionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestStageConditionDeletePopupComponent,
    resolve: {
      mQuestStageCondition: MQuestStageConditionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestStageConditions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
