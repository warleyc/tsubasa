import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDictionaryJa } from 'app/shared/model/m-dictionary-ja.model';
import { MDictionaryJaService } from './m-dictionary-ja.service';
import { MDictionaryJaComponent } from './m-dictionary-ja.component';
import { MDictionaryJaDetailComponent } from './m-dictionary-ja-detail.component';
import { MDictionaryJaUpdateComponent } from './m-dictionary-ja-update.component';
import { MDictionaryJaDeletePopupComponent } from './m-dictionary-ja-delete-dialog.component';
import { IMDictionaryJa } from 'app/shared/model/m-dictionary-ja.model';

@Injectable({ providedIn: 'root' })
export class MDictionaryJaResolve implements Resolve<IMDictionaryJa> {
  constructor(private service: MDictionaryJaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDictionaryJa> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDictionaryJa>) => response.ok),
        map((mDictionaryJa: HttpResponse<MDictionaryJa>) => mDictionaryJa.body)
      );
    }
    return of(new MDictionaryJa());
  }
}

export const mDictionaryJaRoute: Routes = [
  {
    path: '',
    component: MDictionaryJaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDictionaryJas'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDictionaryJaDetailComponent,
    resolve: {
      mDictionaryJa: MDictionaryJaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryJas'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDictionaryJaUpdateComponent,
    resolve: {
      mDictionaryJa: MDictionaryJaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryJas'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDictionaryJaUpdateComponent,
    resolve: {
      mDictionaryJa: MDictionaryJaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryJas'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDictionaryJaPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDictionaryJaDeletePopupComponent,
    resolve: {
      mDictionaryJa: MDictionaryJaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryJas'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
