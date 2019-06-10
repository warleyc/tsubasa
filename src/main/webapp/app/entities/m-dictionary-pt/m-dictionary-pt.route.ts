import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDictionaryPt } from 'app/shared/model/m-dictionary-pt.model';
import { MDictionaryPtService } from './m-dictionary-pt.service';
import { MDictionaryPtComponent } from './m-dictionary-pt.component';
import { MDictionaryPtDetailComponent } from './m-dictionary-pt-detail.component';
import { MDictionaryPtUpdateComponent } from './m-dictionary-pt-update.component';
import { MDictionaryPtDeletePopupComponent } from './m-dictionary-pt-delete-dialog.component';
import { IMDictionaryPt } from 'app/shared/model/m-dictionary-pt.model';

@Injectable({ providedIn: 'root' })
export class MDictionaryPtResolve implements Resolve<IMDictionaryPt> {
  constructor(private service: MDictionaryPtService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDictionaryPt> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDictionaryPt>) => response.ok),
        map((mDictionaryPt: HttpResponse<MDictionaryPt>) => mDictionaryPt.body)
      );
    }
    return of(new MDictionaryPt());
  }
}

export const mDictionaryPtRoute: Routes = [
  {
    path: '',
    component: MDictionaryPtComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDictionaryPts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDictionaryPtDetailComponent,
    resolve: {
      mDictionaryPt: MDictionaryPtResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryPts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDictionaryPtUpdateComponent,
    resolve: {
      mDictionaryPt: MDictionaryPtResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryPts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDictionaryPtUpdateComponent,
    resolve: {
      mDictionaryPt: MDictionaryPtResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryPts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDictionaryPtPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDictionaryPtDeletePopupComponent,
    resolve: {
      mDictionaryPt: MDictionaryPtResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryPts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
