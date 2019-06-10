import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDictionaryIt } from 'app/shared/model/m-dictionary-it.model';
import { MDictionaryItService } from './m-dictionary-it.service';
import { MDictionaryItComponent } from './m-dictionary-it.component';
import { MDictionaryItDetailComponent } from './m-dictionary-it-detail.component';
import { MDictionaryItUpdateComponent } from './m-dictionary-it-update.component';
import { MDictionaryItDeletePopupComponent } from './m-dictionary-it-delete-dialog.component';
import { IMDictionaryIt } from 'app/shared/model/m-dictionary-it.model';

@Injectable({ providedIn: 'root' })
export class MDictionaryItResolve implements Resolve<IMDictionaryIt> {
  constructor(private service: MDictionaryItService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDictionaryIt> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDictionaryIt>) => response.ok),
        map((mDictionaryIt: HttpResponse<MDictionaryIt>) => mDictionaryIt.body)
      );
    }
    return of(new MDictionaryIt());
  }
}

export const mDictionaryItRoute: Routes = [
  {
    path: '',
    component: MDictionaryItComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDictionaryIts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDictionaryItDetailComponent,
    resolve: {
      mDictionaryIt: MDictionaryItResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryIts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDictionaryItUpdateComponent,
    resolve: {
      mDictionaryIt: MDictionaryItResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryIts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDictionaryItUpdateComponent,
    resolve: {
      mDictionaryIt: MDictionaryItResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryIts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDictionaryItPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDictionaryItDeletePopupComponent,
    resolve: {
      mDictionaryIt: MDictionaryItResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryIts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
