import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestAchievementGroup } from 'app/shared/model/m-quest-achievement-group.model';
import { MQuestAchievementGroupService } from './m-quest-achievement-group.service';
import { MQuestAchievementGroupComponent } from './m-quest-achievement-group.component';
import { MQuestAchievementGroupDetailComponent } from './m-quest-achievement-group-detail.component';
import { MQuestAchievementGroupUpdateComponent } from './m-quest-achievement-group-update.component';
import { MQuestAchievementGroupDeletePopupComponent } from './m-quest-achievement-group-delete-dialog.component';
import { IMQuestAchievementGroup } from 'app/shared/model/m-quest-achievement-group.model';

@Injectable({ providedIn: 'root' })
export class MQuestAchievementGroupResolve implements Resolve<IMQuestAchievementGroup> {
  constructor(private service: MQuestAchievementGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestAchievementGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestAchievementGroup>) => response.ok),
        map((mQuestAchievementGroup: HttpResponse<MQuestAchievementGroup>) => mQuestAchievementGroup.body)
      );
    }
    return of(new MQuestAchievementGroup());
  }
}

export const mQuestAchievementGroupRoute: Routes = [
  {
    path: '',
    component: MQuestAchievementGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestAchievementGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestAchievementGroupDetailComponent,
    resolve: {
      mQuestAchievementGroup: MQuestAchievementGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestAchievementGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestAchievementGroupUpdateComponent,
    resolve: {
      mQuestAchievementGroup: MQuestAchievementGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestAchievementGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestAchievementGroupUpdateComponent,
    resolve: {
      mQuestAchievementGroup: MQuestAchievementGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestAchievementGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestAchievementGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestAchievementGroupDeletePopupComponent,
    resolve: {
      mQuestAchievementGroup: MQuestAchievementGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestAchievementGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
