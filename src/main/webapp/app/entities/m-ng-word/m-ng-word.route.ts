import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MNgWord } from 'app/shared/model/m-ng-word.model';
import { MNgWordService } from './m-ng-word.service';
import { MNgWordComponent } from './m-ng-word.component';
import { MNgWordDetailComponent } from './m-ng-word-detail.component';
import { MNgWordUpdateComponent } from './m-ng-word-update.component';
import { MNgWordDeletePopupComponent } from './m-ng-word-delete-dialog.component';
import { IMNgWord } from 'app/shared/model/m-ng-word.model';

@Injectable({ providedIn: 'root' })
export class MNgWordResolve implements Resolve<IMNgWord> {
  constructor(private service: MNgWordService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMNgWord> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MNgWord>) => response.ok),
        map((mNgWord: HttpResponse<MNgWord>) => mNgWord.body)
      );
    }
    return of(new MNgWord());
  }
}

export const mNgWordRoute: Routes = [
  {
    path: '',
    component: MNgWordComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MNgWords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MNgWordDetailComponent,
    resolve: {
      mNgWord: MNgWordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNgWords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MNgWordUpdateComponent,
    resolve: {
      mNgWord: MNgWordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNgWords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MNgWordUpdateComponent,
    resolve: {
      mNgWord: MNgWordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNgWords'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mNgWordPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MNgWordDeletePopupComponent,
    resolve: {
      mNgWord: MNgWordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNgWords'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
