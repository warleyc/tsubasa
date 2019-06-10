import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDictionaryDe } from 'app/shared/model/m-dictionary-de.model';
import { MDictionaryDeService } from './m-dictionary-de.service';
import { MDictionaryDeComponent } from './m-dictionary-de.component';
import { MDictionaryDeDetailComponent } from './m-dictionary-de-detail.component';
import { MDictionaryDeUpdateComponent } from './m-dictionary-de-update.component';
import { MDictionaryDeDeletePopupComponent } from './m-dictionary-de-delete-dialog.component';
import { IMDictionaryDe } from 'app/shared/model/m-dictionary-de.model';

@Injectable({ providedIn: 'root' })
export class MDictionaryDeResolve implements Resolve<IMDictionaryDe> {
  constructor(private service: MDictionaryDeService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDictionaryDe> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDictionaryDe>) => response.ok),
        map((mDictionaryDe: HttpResponse<MDictionaryDe>) => mDictionaryDe.body)
      );
    }
    return of(new MDictionaryDe());
  }
}

export const mDictionaryDeRoute: Routes = [
  {
    path: '',
    component: MDictionaryDeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDictionaryDes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDictionaryDeDetailComponent,
    resolve: {
      mDictionaryDe: MDictionaryDeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryDes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDictionaryDeUpdateComponent,
    resolve: {
      mDictionaryDe: MDictionaryDeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryDes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDictionaryDeUpdateComponent,
    resolve: {
      mDictionaryDe: MDictionaryDeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryDes'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDictionaryDePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDictionaryDeDeletePopupComponent,
    resolve: {
      mDictionaryDe: MDictionaryDeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryDes'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
