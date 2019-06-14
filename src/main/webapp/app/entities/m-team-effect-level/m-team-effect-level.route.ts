import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTeamEffectLevel } from 'app/shared/model/m-team-effect-level.model';
import { MTeamEffectLevelService } from './m-team-effect-level.service';
import { MTeamEffectLevelComponent } from './m-team-effect-level.component';
import { MTeamEffectLevelDetailComponent } from './m-team-effect-level-detail.component';
import { MTeamEffectLevelUpdateComponent } from './m-team-effect-level-update.component';
import { MTeamEffectLevelDeletePopupComponent } from './m-team-effect-level-delete-dialog.component';
import { IMTeamEffectLevel } from 'app/shared/model/m-team-effect-level.model';

@Injectable({ providedIn: 'root' })
export class MTeamEffectLevelResolve implements Resolve<IMTeamEffectLevel> {
  constructor(private service: MTeamEffectLevelService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTeamEffectLevel> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTeamEffectLevel>) => response.ok),
        map((mTeamEffectLevel: HttpResponse<MTeamEffectLevel>) => mTeamEffectLevel.body)
      );
    }
    return of(new MTeamEffectLevel());
  }
}

export const mTeamEffectLevelRoute: Routes = [
  {
    path: '',
    component: MTeamEffectLevelComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTeamEffectLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTeamEffectLevelDetailComponent,
    resolve: {
      mTeamEffectLevel: MTeamEffectLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeamEffectLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTeamEffectLevelUpdateComponent,
    resolve: {
      mTeamEffectLevel: MTeamEffectLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeamEffectLevels'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTeamEffectLevelUpdateComponent,
    resolve: {
      mTeamEffectLevel: MTeamEffectLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeamEffectLevels'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTeamEffectLevelPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTeamEffectLevelDeletePopupComponent,
    resolve: {
      mTeamEffectLevel: MTeamEffectLevelResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTeamEffectLevels'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
