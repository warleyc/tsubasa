import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDictionaryFr } from 'app/shared/model/m-dictionary-fr.model';
import { MDictionaryFrService } from './m-dictionary-fr.service';
import { MDictionaryFrComponent } from './m-dictionary-fr.component';
import { MDictionaryFrDetailComponent } from './m-dictionary-fr-detail.component';
import { MDictionaryFrUpdateComponent } from './m-dictionary-fr-update.component';
import { MDictionaryFrDeletePopupComponent } from './m-dictionary-fr-delete-dialog.component';
import { IMDictionaryFr } from 'app/shared/model/m-dictionary-fr.model';

@Injectable({ providedIn: 'root' })
export class MDictionaryFrResolve implements Resolve<IMDictionaryFr> {
  constructor(private service: MDictionaryFrService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDictionaryFr> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDictionaryFr>) => response.ok),
        map((mDictionaryFr: HttpResponse<MDictionaryFr>) => mDictionaryFr.body)
      );
    }
    return of(new MDictionaryFr());
  }
}

export const mDictionaryFrRoute: Routes = [
  {
    path: '',
    component: MDictionaryFrComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDictionaryFrs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDictionaryFrDetailComponent,
    resolve: {
      mDictionaryFr: MDictionaryFrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryFrs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDictionaryFrUpdateComponent,
    resolve: {
      mDictionaryFr: MDictionaryFrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryFrs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDictionaryFrUpdateComponent,
    resolve: {
      mDictionaryFr: MDictionaryFrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryFrs'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDictionaryFrPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDictionaryFrDeletePopupComponent,
    resolve: {
      mDictionaryFr: MDictionaryFrResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryFrs'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
