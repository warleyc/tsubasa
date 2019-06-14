import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MModelCard } from 'app/shared/model/m-model-card.model';
import { MModelCardService } from './m-model-card.service';
import { MModelCardComponent } from './m-model-card.component';
import { MModelCardDetailComponent } from './m-model-card-detail.component';
import { MModelCardUpdateComponent } from './m-model-card-update.component';
import { MModelCardDeletePopupComponent } from './m-model-card-delete-dialog.component';
import { IMModelCard } from 'app/shared/model/m-model-card.model';

@Injectable({ providedIn: 'root' })
export class MModelCardResolve implements Resolve<IMModelCard> {
  constructor(private service: MModelCardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMModelCard> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MModelCard>) => response.ok),
        map((mModelCard: HttpResponse<MModelCard>) => mModelCard.body)
      );
    }
    return of(new MModelCard());
  }
}

export const mModelCardRoute: Routes = [
  {
    path: '',
    component: MModelCardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MModelCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MModelCardDetailComponent,
    resolve: {
      mModelCard: MModelCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MModelCardUpdateComponent,
    resolve: {
      mModelCard: MModelCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MModelCardUpdateComponent,
    resolve: {
      mModelCard: MModelCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelCards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mModelCardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MModelCardDeletePopupComponent,
    resolve: {
      mModelCard: MModelCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MModelCards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
