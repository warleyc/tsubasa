import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLuckRateGroup } from 'app/shared/model/m-luck-rate-group.model';
import { MLuckRateGroupService } from './m-luck-rate-group.service';
import { MLuckRateGroupComponent } from './m-luck-rate-group.component';
import { MLuckRateGroupDetailComponent } from './m-luck-rate-group-detail.component';
import { MLuckRateGroupUpdateComponent } from './m-luck-rate-group-update.component';
import { MLuckRateGroupDeletePopupComponent } from './m-luck-rate-group-delete-dialog.component';
import { IMLuckRateGroup } from 'app/shared/model/m-luck-rate-group.model';

@Injectable({ providedIn: 'root' })
export class MLuckRateGroupResolve implements Resolve<IMLuckRateGroup> {
  constructor(private service: MLuckRateGroupService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLuckRateGroup> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLuckRateGroup>) => response.ok),
        map((mLuckRateGroup: HttpResponse<MLuckRateGroup>) => mLuckRateGroup.body)
      );
    }
    return of(new MLuckRateGroup());
  }
}

export const mLuckRateGroupRoute: Routes = [
  {
    path: '',
    component: MLuckRateGroupComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLuckRateGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLuckRateGroupDetailComponent,
    resolve: {
      mLuckRateGroup: MLuckRateGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckRateGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLuckRateGroupUpdateComponent,
    resolve: {
      mLuckRateGroup: MLuckRateGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckRateGroups'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLuckRateGroupUpdateComponent,
    resolve: {
      mLuckRateGroup: MLuckRateGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckRateGroups'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLuckRateGroupPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLuckRateGroupDeletePopupComponent,
    resolve: {
      mLuckRateGroup: MLuckRateGroupResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLuckRateGroups'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
