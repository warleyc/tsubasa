import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDictionaryZh } from 'app/shared/model/m-dictionary-zh.model';
import { MDictionaryZhService } from './m-dictionary-zh.service';
import { MDictionaryZhComponent } from './m-dictionary-zh.component';
import { MDictionaryZhDetailComponent } from './m-dictionary-zh-detail.component';
import { MDictionaryZhUpdateComponent } from './m-dictionary-zh-update.component';
import { MDictionaryZhDeletePopupComponent } from './m-dictionary-zh-delete-dialog.component';
import { IMDictionaryZh } from 'app/shared/model/m-dictionary-zh.model';

@Injectable({ providedIn: 'root' })
export class MDictionaryZhResolve implements Resolve<IMDictionaryZh> {
  constructor(private service: MDictionaryZhService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDictionaryZh> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDictionaryZh>) => response.ok),
        map((mDictionaryZh: HttpResponse<MDictionaryZh>) => mDictionaryZh.body)
      );
    }
    return of(new MDictionaryZh());
  }
}

export const mDictionaryZhRoute: Routes = [
  {
    path: '',
    component: MDictionaryZhComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDictionaryZhs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDictionaryZhDetailComponent,
    resolve: {
      mDictionaryZh: MDictionaryZhResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryZhs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDictionaryZhUpdateComponent,
    resolve: {
      mDictionaryZh: MDictionaryZhResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryZhs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDictionaryZhUpdateComponent,
    resolve: {
      mDictionaryZh: MDictionaryZhResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryZhs'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDictionaryZhPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDictionaryZhDeletePopupComponent,
    resolve: {
      mDictionaryZh: MDictionaryZhResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryZhs'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
