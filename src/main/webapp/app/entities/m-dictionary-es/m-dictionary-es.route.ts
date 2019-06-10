import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDictionaryEs } from 'app/shared/model/m-dictionary-es.model';
import { MDictionaryEsService } from './m-dictionary-es.service';
import { MDictionaryEsComponent } from './m-dictionary-es.component';
import { MDictionaryEsDetailComponent } from './m-dictionary-es-detail.component';
import { MDictionaryEsUpdateComponent } from './m-dictionary-es-update.component';
import { MDictionaryEsDeletePopupComponent } from './m-dictionary-es-delete-dialog.component';
import { IMDictionaryEs } from 'app/shared/model/m-dictionary-es.model';

@Injectable({ providedIn: 'root' })
export class MDictionaryEsResolve implements Resolve<IMDictionaryEs> {
  constructor(private service: MDictionaryEsService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDictionaryEs> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDictionaryEs>) => response.ok),
        map((mDictionaryEs: HttpResponse<MDictionaryEs>) => mDictionaryEs.body)
      );
    }
    return of(new MDictionaryEs());
  }
}

export const mDictionaryEsRoute: Routes = [
  {
    path: '',
    component: MDictionaryEsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDictionaryEs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDictionaryEsDetailComponent,
    resolve: {
      mDictionaryEs: MDictionaryEsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryEs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDictionaryEsUpdateComponent,
    resolve: {
      mDictionaryEs: MDictionaryEsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryEs'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDictionaryEsUpdateComponent,
    resolve: {
      mDictionaryEs: MDictionaryEsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryEs'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDictionaryEsPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDictionaryEsDeletePopupComponent,
    resolve: {
      mDictionaryEs: MDictionaryEsResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDictionaryEs'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
