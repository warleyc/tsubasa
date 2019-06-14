import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTargetRarityGroup } from 'app/shared/model/m-target-rarity-group.model';
import { MTargetRarityGroupService } from './m-target-rarity-group.service';
import { MTargetRarityGroupComponent } from './m-target-rarity-group.component';
import { MTargetRarityGroupDetailComponent } from './m-target-rarity-group-detail.component';
import { MTargetRarityGroupUpdateComponent } from './m-target-rarity-group-update.component';
import { MTargetRarityGroupDeletePopupComponent } from './m-target-rarity-group-delete-dialog.component';
import { IMTargetRarityGroup } from 'app/shared/model/m-target-rarity-group.model';

@Injectable({ providedIn: 'root' })
export class MTargetRarityGroupResolve implements Resolve<IMTargetRarityGroup> {
  constructor(private service: MTargetRarityGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTargetRarityGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTargetRarityGroup>) => response.ok),
        map((mTargetRarityGroup: HttpResponse<MTargetRarityGroup>) => mTargetRarityGroup.body)
      );
    }
    return of(new MTargetRarityGroup());
  }
}

export const mTargetRarityGroupRoute: Routes = [
  {
    path: '',
    component: MTargetRarityGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTargetRarityGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTargetRarityGroupDetailComponent,
    resolve: {
      mTargetRarityGroup: MTargetRarityGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetRarityGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTargetRarityGroupUpdateComponent,
    resolve: {
      mTargetRarityGroup: MTargetRarityGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetRarityGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTargetRarityGroupUpdateComponent,
    resolve: {
      mTargetRarityGroup: MTargetRarityGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetRarityGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTargetRarityGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTargetRarityGroupDeletePopupComponent,
    resolve: {
      mTargetRarityGroup: MTargetRarityGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTargetRarityGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
