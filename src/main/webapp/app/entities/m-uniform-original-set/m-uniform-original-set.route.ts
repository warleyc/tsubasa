import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MUniformOriginalSet } from 'app/shared/model/m-uniform-original-set.model';
import { MUniformOriginalSetService } from './m-uniform-original-set.service';
import { MUniformOriginalSetComponent } from './m-uniform-original-set.component';
import { MUniformOriginalSetDetailComponent } from './m-uniform-original-set-detail.component';
import { MUniformOriginalSetUpdateComponent } from './m-uniform-original-set-update.component';
import { MUniformOriginalSetDeletePopupComponent } from './m-uniform-original-set-delete-dialog.component';
import { IMUniformOriginalSet } from 'app/shared/model/m-uniform-original-set.model';

@Injectable({ providedIn: 'root' })
export class MUniformOriginalSetResolve implements Resolve<IMUniformOriginalSet> {
  constructor(private service: MUniformOriginalSetService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMUniformOriginalSet> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MUniformOriginalSet>) => response.ok),
        map((mUniformOriginalSet: HttpResponse<MUniformOriginalSet>) => mUniformOriginalSet.body)
      );
    }
    return of(new MUniformOriginalSet());
  }
}

export const mUniformOriginalSetRoute: Routes = [
  {
    path: '',
    component: MUniformOriginalSetComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MUniformOriginalSets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MUniformOriginalSetDetailComponent,
    resolve: {
      mUniformOriginalSet: MUniformOriginalSetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUniformOriginalSets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MUniformOriginalSetUpdateComponent,
    resolve: {
      mUniformOriginalSet: MUniformOriginalSetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUniformOriginalSets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MUniformOriginalSetUpdateComponent,
    resolve: {
      mUniformOriginalSet: MUniformOriginalSetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUniformOriginalSets'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mUniformOriginalSetPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MUniformOriginalSetDeletePopupComponent,
    resolve: {
      mUniformOriginalSet: MUniformOriginalSetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MUniformOriginalSets'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
