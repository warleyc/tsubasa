import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MEventTitleEffect } from 'app/shared/model/m-event-title-effect.model';
import { MEventTitleEffectService } from './m-event-title-effect.service';
import { MEventTitleEffectComponent } from './m-event-title-effect.component';
import { MEventTitleEffectDetailComponent } from './m-event-title-effect-detail.component';
import { MEventTitleEffectUpdateComponent } from './m-event-title-effect-update.component';
import { MEventTitleEffectDeletePopupComponent } from './m-event-title-effect-delete-dialog.component';
import { IMEventTitleEffect } from 'app/shared/model/m-event-title-effect.model';

@Injectable({ providedIn: 'root' })
export class MEventTitleEffectResolve implements Resolve<IMEventTitleEffect> {
  constructor(private service: MEventTitleEffectService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMEventTitleEffect> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MEventTitleEffect>) => response.ok),
        map((mEventTitleEffect: HttpResponse<MEventTitleEffect>) => mEventTitleEffect.body)
      );
    }
    return of(new MEventTitleEffect());
  }
}

export const mEventTitleEffectRoute: Routes = [
  {
    path: '',
    component: MEventTitleEffectComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MEventTitleEffects'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MEventTitleEffectDetailComponent,
    resolve: {
      mEventTitleEffect: MEventTitleEffectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEventTitleEffects'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MEventTitleEffectUpdateComponent,
    resolve: {
      mEventTitleEffect: MEventTitleEffectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEventTitleEffects'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MEventTitleEffectUpdateComponent,
    resolve: {
      mEventTitleEffect: MEventTitleEffectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEventTitleEffects'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mEventTitleEffectPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MEventTitleEffectDeletePopupComponent,
    resolve: {
      mEventTitleEffect: MEventTitleEffectResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEventTitleEffects'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
