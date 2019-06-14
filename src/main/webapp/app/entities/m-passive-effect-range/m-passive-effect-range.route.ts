import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MPassiveEffectRange } from 'app/shared/model/m-passive-effect-range.model';
import { MPassiveEffectRangeService } from './m-passive-effect-range.service';
import { MPassiveEffectRangeComponent } from './m-passive-effect-range.component';
import { MPassiveEffectRangeDetailComponent } from './m-passive-effect-range-detail.component';
import { MPassiveEffectRangeUpdateComponent } from './m-passive-effect-range-update.component';
import { MPassiveEffectRangeDeletePopupComponent } from './m-passive-effect-range-delete-dialog.component';
import { IMPassiveEffectRange } from 'app/shared/model/m-passive-effect-range.model';

@Injectable({ providedIn: 'root' })
export class MPassiveEffectRangeResolve implements Resolve<IMPassiveEffectRange> {
  constructor(private service: MPassiveEffectRangeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMPassiveEffectRange> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MPassiveEffectRange>) => response.ok),
        map((mPassiveEffectRange: HttpResponse<MPassiveEffectRange>) => mPassiveEffectRange.body)
      );
    }
    return of(new MPassiveEffectRange());
  }
}

export const mPassiveEffectRangeRoute: Routes = [
  {
    path: '',
    component: MPassiveEffectRangeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MPassiveEffectRanges'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MPassiveEffectRangeDetailComponent,
    resolve: {
      mPassiveEffectRange: MPassiveEffectRangeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPassiveEffectRanges'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MPassiveEffectRangeUpdateComponent,
    resolve: {
      mPassiveEffectRange: MPassiveEffectRangeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPassiveEffectRanges'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MPassiveEffectRangeUpdateComponent,
    resolve: {
      mPassiveEffectRange: MPassiveEffectRangeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPassiveEffectRanges'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mPassiveEffectRangePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MPassiveEffectRangeDeletePopupComponent,
    resolve: {
      mPassiveEffectRange: MPassiveEffectRangeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MPassiveEffectRanges'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
