import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTargetTriggerEffectGroup } from 'app/shared/model/m-target-trigger-effect-group.model';
import { MTargetTriggerEffectGroupService } from './m-target-trigger-effect-group.service';
import { MTargetTriggerEffectGroupComponent } from './m-target-trigger-effect-group.component';
import { MTargetTriggerEffectGroupDetailComponent } from './m-target-trigger-effect-group-detail.component';
import { MTargetTriggerEffectGroupUpdateComponent } from './m-target-trigger-effect-group-update.component';
import { MTargetTriggerEffectGroupDeletePopupComponent } from './m-target-trigger-effect-group-delete-dialog.component';
import { IMTargetTriggerEffectGroup } from 'app/shared/model/m-target-trigger-effect-group.model';

@Injectable({ providedIn: 'root' })
export class MTargetTriggerEffectGroupResolve implements Resolve<IMTargetTriggerEffectGroup> {
  constructor(private service: MTargetTriggerEffectGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTargetTriggerEffectGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTargetTriggerEffectGroup>) => response.ok),
        map((mTargetTriggerEffectGroup: HttpResponse<MTargetTriggerEffectGroup>) => mTargetTriggerEffectGroup.body)
      );
    }
    return of(new MTargetTriggerEffectGroup());
  }
}

export const mTargetTriggerEffectGroupRoute: Routes = [
  {
    path: '',
    component: MTargetTriggerEffectGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTargetTriggerEffectGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTargetTriggerEffectGroupDetailComponent,
    resolve: {
      mTargetTriggerEffectGroup: MTargetTriggerEffectGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetTriggerEffectGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTargetTriggerEffectGroupUpdateComponent,
    resolve: {
      mTargetTriggerEffectGroup: MTargetTriggerEffectGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetTriggerEffectGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTargetTriggerEffectGroupUpdateComponent,
    resolve: {
      mTargetTriggerEffectGroup: MTargetTriggerEffectGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetTriggerEffectGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTargetTriggerEffectGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTargetTriggerEffectGroupDeletePopupComponent,
    resolve: {
      mTargetTriggerEffectGroup: MTargetTriggerEffectGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetTriggerEffectGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
