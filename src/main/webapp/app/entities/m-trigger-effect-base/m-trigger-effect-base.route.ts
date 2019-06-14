import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTriggerEffectBase } from 'app/shared/model/m-trigger-effect-base.model';
import { MTriggerEffectBaseService } from './m-trigger-effect-base.service';
import { MTriggerEffectBaseComponent } from './m-trigger-effect-base.component';
import { MTriggerEffectBaseDetailComponent } from './m-trigger-effect-base-detail.component';
import { MTriggerEffectBaseUpdateComponent } from './m-trigger-effect-base-update.component';
import { MTriggerEffectBaseDeletePopupComponent } from './m-trigger-effect-base-delete-dialog.component';
import { IMTriggerEffectBase } from 'app/shared/model/m-trigger-effect-base.model';

@Injectable({ providedIn: 'root' })
export class MTriggerEffectBaseResolve implements Resolve<IMTriggerEffectBase> {
  constructor(private service: MTriggerEffectBaseService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTriggerEffectBase> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTriggerEffectBase>) => response.ok),
        map((mTriggerEffectBase: HttpResponse<MTriggerEffectBase>) => mTriggerEffectBase.body)
      );
    }
    return of(new MTriggerEffectBase());
  }
}

export const mTriggerEffectBaseRoute: Routes = [
  {
    path: '',
    component: MTriggerEffectBaseComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTriggerEffectBases'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTriggerEffectBaseDetailComponent,
    resolve: {
      mTriggerEffectBase: MTriggerEffectBaseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTriggerEffectBases'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTriggerEffectBaseUpdateComponent,
    resolve: {
      mTriggerEffectBase: MTriggerEffectBaseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTriggerEffectBases'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTriggerEffectBaseUpdateComponent,
    resolve: {
      mTriggerEffectBase: MTriggerEffectBaseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTriggerEffectBases'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTriggerEffectBasePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTriggerEffectBaseDeletePopupComponent,
    resolve: {
      mTriggerEffectBase: MTriggerEffectBaseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTriggerEffectBases'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
