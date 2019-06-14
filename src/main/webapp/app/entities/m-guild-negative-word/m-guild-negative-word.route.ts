import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGuildNegativeWord } from 'app/shared/model/m-guild-negative-word.model';
import { MGuildNegativeWordService } from './m-guild-negative-word.service';
import { MGuildNegativeWordComponent } from './m-guild-negative-word.component';
import { MGuildNegativeWordDetailComponent } from './m-guild-negative-word-detail.component';
import { MGuildNegativeWordUpdateComponent } from './m-guild-negative-word-update.component';
import { MGuildNegativeWordDeletePopupComponent } from './m-guild-negative-word-delete-dialog.component';
import { IMGuildNegativeWord } from 'app/shared/model/m-guild-negative-word.model';

@Injectable({ providedIn: 'root' })
export class MGuildNegativeWordResolve implements Resolve<IMGuildNegativeWord> {
  constructor(private service: MGuildNegativeWordService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGuildNegativeWord> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGuildNegativeWord>) => response.ok),
        map((mGuildNegativeWord: HttpResponse<MGuildNegativeWord>) => mGuildNegativeWord.body)
      );
    }
    return of(new MGuildNegativeWord());
  }
}

export const mGuildNegativeWordRoute: Routes = [
  {
    path: '',
    component: MGuildNegativeWordComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGuildNegativeWords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGuildNegativeWordDetailComponent,
    resolve: {
      mGuildNegativeWord: MGuildNegativeWordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildNegativeWords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGuildNegativeWordUpdateComponent,
    resolve: {
      mGuildNegativeWord: MGuildNegativeWordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildNegativeWords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGuildNegativeWordUpdateComponent,
    resolve: {
      mGuildNegativeWord: MGuildNegativeWordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildNegativeWords'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGuildNegativeWordPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGuildNegativeWordDeletePopupComponent,
    resolve: {
      mGuildNegativeWord: MGuildNegativeWordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGuildNegativeWords'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
