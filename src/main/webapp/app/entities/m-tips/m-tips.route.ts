import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTips } from 'app/shared/model/m-tips.model';
import { MTipsService } from './m-tips.service';
import { MTipsComponent } from './m-tips.component';
import { MTipsDetailComponent } from './m-tips-detail.component';
import { MTipsUpdateComponent } from './m-tips-update.component';
import { MTipsDeletePopupComponent } from './m-tips-delete-dialog.component';
import { IMTips } from 'app/shared/model/m-tips.model';

@Injectable({ providedIn: 'root' })
export class MTipsResolve implements Resolve<IMTips> {
  constructor(private service: MTipsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTips> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTips>) => response.ok),
        map((mTips: HttpResponse<MTips>) => mTips.body)
      );
    }
    return of(new MTips());
  }
}

export const mTipsRoute: Routes = [
  {
    path: '',
    component: MTipsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTips'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTipsDetailComponent,
    resolve: {
      mTips: MTipsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTips'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTipsUpdateComponent,
    resolve: {
      mTips: MTipsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTips'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTipsUpdateComponent,
    resolve: {
      mTips: MTipsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTips'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTipsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTipsDeletePopupComponent,
    resolve: {
      mTips: MTipsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTips'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
