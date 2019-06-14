import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MLuck } from 'app/shared/model/m-luck.model';
import { MLuckService } from './m-luck.service';
import { MLuckComponent } from './m-luck.component';
import { MLuckDetailComponent } from './m-luck-detail.component';
import { MLuckUpdateComponent } from './m-luck-update.component';
import { MLuckDeletePopupComponent } from './m-luck-delete-dialog.component';
import { IMLuck } from 'app/shared/model/m-luck.model';

@Injectable({ providedIn: 'root' })
export class MLuckResolve implements Resolve<IMLuck> {
  constructor(private service: MLuckService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMLuck> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MLuck>) => response.ok),
        map((mLuck: HttpResponse<MLuck>) => mLuck.body)
      );
    }
    return of(new MLuck());
  }
}

export const mLuckRoute: Routes = [
  {
    path: '',
    component: MLuckComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MLucks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MLuckDetailComponent,
    resolve: {
      mLuck: MLuckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLucks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MLuckUpdateComponent,
    resolve: {
      mLuck: MLuckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLucks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MLuckUpdateComponent,
    resolve: {
      mLuck: MLuckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLucks'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mLuckPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MLuckDeletePopupComponent,
    resolve: {
      mLuck: MLuckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MLucks'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
