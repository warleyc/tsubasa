import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLeagueEffect } from 'app/shared/model/m-league-effect.model';
import { MLeagueEffectService } from './m-league-effect.service';
import { MLeagueEffectComponent } from './m-league-effect.component';
import { MLeagueEffectDetailComponent } from './m-league-effect-detail.component';
import { MLeagueEffectUpdateComponent } from './m-league-effect-update.component';
import { MLeagueEffectDeletePopupComponent } from './m-league-effect-delete-dialog.component';
import { IMLeagueEffect } from 'app/shared/model/m-league-effect.model';

@Injectable({ providedIn: 'root' })
export class MLeagueEffectResolve implements Resolve<IMLeagueEffect> {
  constructor(private service: MLeagueEffectService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLeagueEffect> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLeagueEffect>) => response.ok),
        map((mLeagueEffect: HttpResponse<MLeagueEffect>) => mLeagueEffect.body)
      );
    }
    return of(new MLeagueEffect());
  }
}

export const mLeagueEffectRoute: Routes = [
  {
    path: '',
    component: MLeagueEffectComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLeagueEffects'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLeagueEffectDetailComponent,
    resolve: {
      mLeagueEffect: MLeagueEffectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueEffects'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLeagueEffectUpdateComponent,
    resolve: {
      mLeagueEffect: MLeagueEffectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueEffects'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLeagueEffectUpdateComponent,
    resolve: {
      mLeagueEffect: MLeagueEffectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueEffects'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLeagueEffectPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLeagueEffectDeletePopupComponent,
    resolve: {
      mLeagueEffect: MLeagueEffectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLeagueEffects'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
