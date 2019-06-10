import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MCardLevel } from 'app/shared/model/m-card-level.model';
import { MCardLevelService } from './m-card-level.service';
import { MCardLevelComponent } from './m-card-level.component';
import { MCardLevelDetailComponent } from './m-card-level-detail.component';
import { MCardLevelUpdateComponent } from './m-card-level-update.component';
import { MCardLevelDeletePopupComponent } from './m-card-level-delete-dialog.component';
import { IMCardLevel } from 'app/shared/model/m-card-level.model';

@Injectable({ providedIn: 'root' })
export class MCardLevelResolve implements Resolve<IMCardLevel> {
  constructor(private service: MCardLevelService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMCardLevel> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MCardLevel>) => response.ok),
        map((mCardLevel: HttpResponse<MCardLevel>) => mCardLevel.body)
      );
    }
    return of(new MCardLevel());
  }
}

export const mCardLevelRoute: Routes = [
  {
    path: '',
    component: MCardLevelComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MCardLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MCardLevelDetailComponent,
    resolve: {
      mCardLevel: MCardLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MCardLevelUpdateComponent,
    resolve: {
      mCardLevel: MCardLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MCardLevelUpdateComponent,
    resolve: {
      mCardLevel: MCardLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardLevels'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mCardLevelPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MCardLevelDeletePopupComponent,
    resolve: {
      mCardLevel: MCardLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MCardLevels'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
