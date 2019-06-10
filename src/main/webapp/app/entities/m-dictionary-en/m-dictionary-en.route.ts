import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDictionaryEn } from 'app/shared/model/m-dictionary-en.model';
import { MDictionaryEnService } from './m-dictionary-en.service';
import { MDictionaryEnComponent } from './m-dictionary-en.component';
import { MDictionaryEnDetailComponent } from './m-dictionary-en-detail.component';
import { MDictionaryEnUpdateComponent } from './m-dictionary-en-update.component';
import { MDictionaryEnDeletePopupComponent } from './m-dictionary-en-delete-dialog.component';
import { IMDictionaryEn } from 'app/shared/model/m-dictionary-en.model';

@Injectable({ providedIn: 'root' })
export class MDictionaryEnResolve implements Resolve<IMDictionaryEn> {
  constructor(private service: MDictionaryEnService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDictionaryEn> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDictionaryEn>) => response.ok),
        map((mDictionaryEn: HttpResponse<MDictionaryEn>) => mDictionaryEn.body)
      );
    }
    return of(new MDictionaryEn());
  }
}

export const mDictionaryEnRoute: Routes = [
  {
    path: '',
    component: MDictionaryEnComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDictionaryEns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDictionaryEnDetailComponent,
    resolve: {
      mDictionaryEn: MDictionaryEnResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryEns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDictionaryEnUpdateComponent,
    resolve: {
      mDictionaryEn: MDictionaryEnResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryEns'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDictionaryEnUpdateComponent,
    resolve: {
      mDictionaryEn: MDictionaryEnResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryEns'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDictionaryEnPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDictionaryEnDeletePopupComponent,
    resolve: {
      mDictionaryEn: MDictionaryEnResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryEns'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
