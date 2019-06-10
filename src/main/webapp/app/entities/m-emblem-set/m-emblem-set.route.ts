import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MEmblemSet } from 'app/shared/model/m-emblem-set.model';
import { MEmblemSetService } from './m-emblem-set.service';
import { MEmblemSetComponent } from './m-emblem-set.component';
import { MEmblemSetDetailComponent } from './m-emblem-set-detail.component';
import { MEmblemSetUpdateComponent } from './m-emblem-set-update.component';
import { MEmblemSetDeletePopupComponent } from './m-emblem-set-delete-dialog.component';
import { IMEmblemSet } from 'app/shared/model/m-emblem-set.model';

@Injectable({ providedIn: 'root' })
export class MEmblemSetResolve implements Resolve<IMEmblemSet> {
  constructor(private service: MEmblemSetService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMEmblemSet> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MEmblemSet>) => response.ok),
        map((mEmblemSet: HttpResponse<MEmblemSet>) => mEmblemSet.body)
      );
    }
    return of(new MEmblemSet());
  }
}

export const mEmblemSetRoute: Routes = [
  {
    path: '',
    component: MEmblemSetComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MEmblemSets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MEmblemSetDetailComponent,
    resolve: {
      mEmblemSet: MEmblemSetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEmblemSets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MEmblemSetUpdateComponent,
    resolve: {
      mEmblemSet: MEmblemSetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEmblemSets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MEmblemSetUpdateComponent,
    resolve: {
      mEmblemSet: MEmblemSetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEmblemSets'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mEmblemSetPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MEmblemSetDeletePopupComponent,
    resolve: {
      mEmblemSet: MEmblemSetResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEmblemSets'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
