import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestClearRewardWeight } from 'app/shared/model/m-quest-clear-reward-weight.model';
import { MQuestClearRewardWeightService } from './m-quest-clear-reward-weight.service';
import { MQuestClearRewardWeightComponent } from './m-quest-clear-reward-weight.component';
import { MQuestClearRewardWeightDetailComponent } from './m-quest-clear-reward-weight-detail.component';
import { MQuestClearRewardWeightUpdateComponent } from './m-quest-clear-reward-weight-update.component';
import { MQuestClearRewardWeightDeletePopupComponent } from './m-quest-clear-reward-weight-delete-dialog.component';
import { IMQuestClearRewardWeight } from 'app/shared/model/m-quest-clear-reward-weight.model';

@Injectable({ providedIn: 'root' })
export class MQuestClearRewardWeightResolve implements Resolve<IMQuestClearRewardWeight> {
  constructor(private service: MQuestClearRewardWeightService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestClearRewardWeight> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestClearRewardWeight>) => response.ok),
        map((mQuestClearRewardWeight: HttpResponse<MQuestClearRewardWeight>) => mQuestClearRewardWeight.body)
      );
    }
    return of(new MQuestClearRewardWeight());
  }
}

export const mQuestClearRewardWeightRoute: Routes = [
  {
    path: '',
    component: MQuestClearRewardWeightComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestClearRewardWeights'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestClearRewardWeightDetailComponent,
    resolve: {
      mQuestClearRewardWeight: MQuestClearRewardWeightResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestClearRewardWeights'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestClearRewardWeightUpdateComponent,
    resolve: {
      mQuestClearRewardWeight: MQuestClearRewardWeightResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestClearRewardWeights'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestClearRewardWeightUpdateComponent,
    resolve: {
      mQuestClearRewardWeight: MQuestClearRewardWeightResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestClearRewardWeights'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestClearRewardWeightPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestClearRewardWeightDeletePopupComponent,
    resolve: {
      mQuestClearRewardWeight: MQuestClearRewardWeightResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestClearRewardWeights'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
