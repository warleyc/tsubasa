import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MActionLevel } from 'app/shared/model/m-action-level.model';
import { MActionLevelService } from './m-action-level.service';
import { MActionLevelComponent } from './m-action-level.component';
import { MActionLevelDetailComponent } from './m-action-level-detail.component';
import { MActionLevelUpdateComponent } from './m-action-level-update.component';
import { MActionLevelDeletePopupComponent } from './m-action-level-delete-dialog.component';
import { IMActionLevel } from 'app/shared/model/m-action-level.model';

@Injectable({ providedIn: 'root' })
export class MActionLevelResolve implements Resolve<IMActionLevel> {
  constructor(private service: MActionLevelService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMActionLevel> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MActionLevel>) => response.ok),
        map((mActionLevel: HttpResponse<MActionLevel>) => mActionLevel.body)
      );
    }
    return of(new MActionLevel());
  }
}

export const mActionLevelRoute: Routes = [
  {
    path: '',
    component: MActionLevelComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MActionLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MActionLevelDetailComponent,
    resolve: {
      mActionLevel: MActionLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MActionLevelUpdateComponent,
    resolve: {
      mActionLevel: MActionLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MActionLevelUpdateComponent,
    resolve: {
      mActionLevel: MActionLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionLevels'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mActionLevelPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MActionLevelDeletePopupComponent,
    resolve: {
      mActionLevel: MActionLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MActionLevels'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
