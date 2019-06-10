import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDummyEmblem } from 'app/shared/model/m-dummy-emblem.model';
import { MDummyEmblemService } from './m-dummy-emblem.service';
import { MDummyEmblemComponent } from './m-dummy-emblem.component';
import { MDummyEmblemDetailComponent } from './m-dummy-emblem-detail.component';
import { MDummyEmblemUpdateComponent } from './m-dummy-emblem-update.component';
import { MDummyEmblemDeletePopupComponent } from './m-dummy-emblem-delete-dialog.component';
import { IMDummyEmblem } from 'app/shared/model/m-dummy-emblem.model';

@Injectable({ providedIn: 'root' })
export class MDummyEmblemResolve implements Resolve<IMDummyEmblem> {
  constructor(private service: MDummyEmblemService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDummyEmblem> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDummyEmblem>) => response.ok),
        map((mDummyEmblem: HttpResponse<MDummyEmblem>) => mDummyEmblem.body)
      );
    }
    return of(new MDummyEmblem());
  }
}

export const mDummyEmblemRoute: Routes = [
  {
    path: '',
    component: MDummyEmblemComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDummyEmblems'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDummyEmblemDetailComponent,
    resolve: {
      mDummyEmblem: MDummyEmblemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDummyEmblems'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDummyEmblemUpdateComponent,
    resolve: {
      mDummyEmblem: MDummyEmblemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDummyEmblems'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDummyEmblemUpdateComponent,
    resolve: {
      mDummyEmblem: MDummyEmblemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDummyEmblems'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDummyEmblemPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDummyEmblemDeletePopupComponent,
    resolve: {
      mDummyEmblem: MDummyEmblemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDummyEmblems'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
