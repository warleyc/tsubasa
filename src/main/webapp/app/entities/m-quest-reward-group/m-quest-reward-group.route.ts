import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestRewardGroup } from 'app/shared/model/m-quest-reward-group.model';
import { MQuestRewardGroupService } from './m-quest-reward-group.service';
import { MQuestRewardGroupComponent } from './m-quest-reward-group.component';
import { MQuestRewardGroupDetailComponent } from './m-quest-reward-group-detail.component';
import { MQuestRewardGroupUpdateComponent } from './m-quest-reward-group-update.component';
import { MQuestRewardGroupDeletePopupComponent } from './m-quest-reward-group-delete-dialog.component';
import { IMQuestRewardGroup } from 'app/shared/model/m-quest-reward-group.model';

@Injectable({ providedIn: 'root' })
export class MQuestRewardGroupResolve implements Resolve<IMQuestRewardGroup> {
  constructor(private service: MQuestRewardGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestRewardGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestRewardGroup>) => response.ok),
        map((mQuestRewardGroup: HttpResponse<MQuestRewardGroup>) => mQuestRewardGroup.body)
      );
    }
    return of(new MQuestRewardGroup());
  }
}

export const mQuestRewardGroupRoute: Routes = [
  {
    path: '',
    component: MQuestRewardGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestRewardGroupDetailComponent,
    resolve: {
      mQuestRewardGroup: MQuestRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestRewardGroupUpdateComponent,
    resolve: {
      mQuestRewardGroup: MQuestRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestRewardGroupUpdateComponent,
    resolve: {
      mQuestRewardGroup: MQuestRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestRewardGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestRewardGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestRewardGroupDeletePopupComponent,
    resolve: {
      mQuestRewardGroup: MQuestRewardGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestRewardGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
