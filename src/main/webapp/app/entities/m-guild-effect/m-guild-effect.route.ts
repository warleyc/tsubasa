import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGuildEffect } from 'app/shared/model/m-guild-effect.model';
import { MGuildEffectService } from './m-guild-effect.service';
import { MGuildEffectComponent } from './m-guild-effect.component';
import { MGuildEffectDetailComponent } from './m-guild-effect-detail.component';
import { MGuildEffectUpdateComponent } from './m-guild-effect-update.component';
import { MGuildEffectDeletePopupComponent } from './m-guild-effect-delete-dialog.component';
import { IMGuildEffect } from 'app/shared/model/m-guild-effect.model';

@Injectable({ providedIn: 'root' })
export class MGuildEffectResolve implements Resolve<IMGuildEffect> {
  constructor(private service: MGuildEffectService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGuildEffect> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGuildEffect>) => response.ok),
        map((mGuildEffect: HttpResponse<MGuildEffect>) => mGuildEffect.body)
      );
    }
    return of(new MGuildEffect());
  }
}

export const mGuildEffectRoute: Routes = [
  {
    path: '',
    component: MGuildEffectComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGuildEffects'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGuildEffectDetailComponent,
    resolve: {
      mGuildEffect: MGuildEffectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildEffects'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGuildEffectUpdateComponent,
    resolve: {
      mGuildEffect: MGuildEffectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildEffects'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGuildEffectUpdateComponent,
    resolve: {
      mGuildEffect: MGuildEffectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildEffects'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGuildEffectPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGuildEffectDeletePopupComponent,
    resolve: {
      mGuildEffect: MGuildEffectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildEffects'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
