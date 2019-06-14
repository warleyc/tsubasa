import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTriggerEffectRange } from 'app/shared/model/m-trigger-effect-range.model';
import { MTriggerEffectRangeService } from './m-trigger-effect-range.service';
import { MTriggerEffectRangeComponent } from './m-trigger-effect-range.component';
import { MTriggerEffectRangeDetailComponent } from './m-trigger-effect-range-detail.component';
import { MTriggerEffectRangeUpdateComponent } from './m-trigger-effect-range-update.component';
import { MTriggerEffectRangeDeletePopupComponent } from './m-trigger-effect-range-delete-dialog.component';
import { IMTriggerEffectRange } from 'app/shared/model/m-trigger-effect-range.model';

@Injectable({ providedIn: 'root' })
export class MTriggerEffectRangeResolve implements Resolve<IMTriggerEffectRange> {
  constructor(private service: MTriggerEffectRangeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTriggerEffectRange> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTriggerEffectRange>) => response.ok),
        map((mTriggerEffectRange: HttpResponse<MTriggerEffectRange>) => mTriggerEffectRange.body)
      );
    }
    return of(new MTriggerEffectRange());
  }
}

export const mTriggerEffectRangeRoute: Routes = [
  {
    path: '',
    component: MTriggerEffectRangeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTriggerEffectRanges'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTriggerEffectRangeDetailComponent,
    resolve: {
      mTriggerEffectRange: MTriggerEffectRangeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTriggerEffectRanges'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTriggerEffectRangeUpdateComponent,
    resolve: {
      mTriggerEffectRange: MTriggerEffectRangeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTriggerEffectRanges'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTriggerEffectRangeUpdateComponent,
    resolve: {
      mTriggerEffectRange: MTriggerEffectRangeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTriggerEffectRanges'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTriggerEffectRangePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTriggerEffectRangeDeletePopupComponent,
    resolve: {
      mTriggerEffectRange: MTriggerEffectRangeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTriggerEffectRanges'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
