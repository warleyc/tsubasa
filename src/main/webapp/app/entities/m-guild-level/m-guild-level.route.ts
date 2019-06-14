import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGuildLevel } from 'app/shared/model/m-guild-level.model';
import { MGuildLevelService } from './m-guild-level.service';
import { MGuildLevelComponent } from './m-guild-level.component';
import { MGuildLevelDetailComponent } from './m-guild-level-detail.component';
import { MGuildLevelUpdateComponent } from './m-guild-level-update.component';
import { MGuildLevelDeletePopupComponent } from './m-guild-level-delete-dialog.component';
import { IMGuildLevel } from 'app/shared/model/m-guild-level.model';

@Injectable({ providedIn: 'root' })
export class MGuildLevelResolve implements Resolve<IMGuildLevel> {
  constructor(private service: MGuildLevelService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGuildLevel> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGuildLevel>) => response.ok),
        map((mGuildLevel: HttpResponse<MGuildLevel>) => mGuildLevel.body)
      );
    }
    return of(new MGuildLevel());
  }
}

export const mGuildLevelRoute: Routes = [
  {
    path: '',
    component: MGuildLevelComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGuildLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGuildLevelDetailComponent,
    resolve: {
      mGuildLevel: MGuildLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGuildLevelUpdateComponent,
    resolve: {
      mGuildLevel: MGuildLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGuildLevelUpdateComponent,
    resolve: {
      mGuildLevel: MGuildLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildLevels'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGuildLevelPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGuildLevelDeletePopupComponent,
    resolve: {
      mGuildLevel: MGuildLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildLevels'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
