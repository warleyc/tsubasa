import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MStamp } from 'app/shared/model/m-stamp.model';
import { MStampService } from './m-stamp.service';
import { MStampComponent } from './m-stamp.component';
import { MStampDetailComponent } from './m-stamp-detail.component';
import { MStampUpdateComponent } from './m-stamp-update.component';
import { MStampDeletePopupComponent } from './m-stamp-delete-dialog.component';
import { IMStamp } from 'app/shared/model/m-stamp.model';

@Injectable({ providedIn: 'root' })
export class MStampResolve implements Resolve<IMStamp> {
  constructor(private service: MStampService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMStamp> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MStamp>) => response.ok),
        map((mStamp: HttpResponse<MStamp>) => mStamp.body)
      );
    }
    return of(new MStamp());
  }
}

export const mStampRoute: Routes = [
  {
    path: '',
    component: MStampComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MStampDetailComponent,
    resolve: {
      mStamp: MStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MStampUpdateComponent,
    resolve: {
      mStamp: MStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStamps'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MStampUpdateComponent,
    resolve: {
      mStamp: MStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStamps'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mStampPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MStampDeletePopupComponent,
    resolve: {
      mStamp: MStampResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStamps'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
