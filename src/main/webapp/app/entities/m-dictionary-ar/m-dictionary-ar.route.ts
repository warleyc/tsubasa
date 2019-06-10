import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDictionaryAr } from 'app/shared/model/m-dictionary-ar.model';
import { MDictionaryArService } from './m-dictionary-ar.service';
import { MDictionaryArComponent } from './m-dictionary-ar.component';
import { MDictionaryArDetailComponent } from './m-dictionary-ar-detail.component';
import { MDictionaryArUpdateComponent } from './m-dictionary-ar-update.component';
import { MDictionaryArDeletePopupComponent } from './m-dictionary-ar-delete-dialog.component';
import { IMDictionaryAr } from 'app/shared/model/m-dictionary-ar.model';

@Injectable({ providedIn: 'root' })
export class MDictionaryArResolve implements Resolve<IMDictionaryAr> {
  constructor(private service: MDictionaryArService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDictionaryAr> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDictionaryAr>) => response.ok),
        map((mDictionaryAr: HttpResponse<MDictionaryAr>) => mDictionaryAr.body)
      );
    }
    return of(new MDictionaryAr());
  }
}

export const mDictionaryArRoute: Routes = [
  {
    path: '',
    component: MDictionaryArComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDictionaryArs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDictionaryArDetailComponent,
    resolve: {
      mDictionaryAr: MDictionaryArResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryArs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDictionaryArUpdateComponent,
    resolve: {
      mDictionaryAr: MDictionaryArResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryArs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDictionaryArUpdateComponent,
    resolve: {
      mDictionaryAr: MDictionaryArResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryArs'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDictionaryArPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDictionaryArDeletePopupComponent,
    resolve: {
      mDictionaryAr: MDictionaryArResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryArs'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
