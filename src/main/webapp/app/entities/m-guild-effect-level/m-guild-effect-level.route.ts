import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGuildEffectLevel } from 'app/shared/model/m-guild-effect-level.model';
import { MGuildEffectLevelService } from './m-guild-effect-level.service';
import { MGuildEffectLevelComponent } from './m-guild-effect-level.component';
import { MGuildEffectLevelDetailComponent } from './m-guild-effect-level-detail.component';
import { MGuildEffectLevelUpdateComponent } from './m-guild-effect-level-update.component';
import { MGuildEffectLevelDeletePopupComponent } from './m-guild-effect-level-delete-dialog.component';
import { IMGuildEffectLevel } from 'app/shared/model/m-guild-effect-level.model';

@Injectable({ providedIn: 'root' })
export class MGuildEffectLevelResolve implements Resolve<IMGuildEffectLevel> {
  constructor(private service: MGuildEffectLevelService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGuildEffectLevel> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGuildEffectLevel>) => response.ok),
        map((mGuildEffectLevel: HttpResponse<MGuildEffectLevel>) => mGuildEffectLevel.body)
      );
    }
    return of(new MGuildEffectLevel());
  }
}

export const mGuildEffectLevelRoute: Routes = [
  {
    path: '',
    component: MGuildEffectLevelComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGuildEffectLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGuildEffectLevelDetailComponent,
    resolve: {
      mGuildEffectLevel: MGuildEffectLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildEffectLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGuildEffectLevelUpdateComponent,
    resolve: {
      mGuildEffectLevel: MGuildEffectLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildEffectLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGuildEffectLevelUpdateComponent,
    resolve: {
      mGuildEffectLevel: MGuildEffectLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildEffectLevels'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGuildEffectLevelPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGuildEffectLevelDeletePopupComponent,
    resolve: {
      mGuildEffectLevel: MGuildEffectLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildEffectLevels'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
