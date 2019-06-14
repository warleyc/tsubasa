import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTrainingCard } from 'app/shared/model/m-training-card.model';
import { MTrainingCardService } from './m-training-card.service';
import { MTrainingCardComponent } from './m-training-card.component';
import { MTrainingCardDetailComponent } from './m-training-card-detail.component';
import { MTrainingCardUpdateComponent } from './m-training-card-update.component';
import { MTrainingCardDeletePopupComponent } from './m-training-card-delete-dialog.component';
import { IMTrainingCard } from 'app/shared/model/m-training-card.model';

@Injectable({ providedIn: 'root' })
export class MTrainingCardResolve implements Resolve<IMTrainingCard> {
  constructor(private service: MTrainingCardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTrainingCard> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTrainingCard>) => response.ok),
        map((mTrainingCard: HttpResponse<MTrainingCard>) => mTrainingCard.body)
      );
    }
    return of(new MTrainingCard());
  }
}

export const mTrainingCardRoute: Routes = [
  {
    path: '',
    component: MTrainingCardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTrainingCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTrainingCardDetailComponent,
    resolve: {
      mTrainingCard: MTrainingCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTrainingCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTrainingCardUpdateComponent,
    resolve: {
      mTrainingCard: MTrainingCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTrainingCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTrainingCardUpdateComponent,
    resolve: {
      mTrainingCard: MTrainingCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTrainingCards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTrainingCardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTrainingCardDeletePopupComponent,
    resolve: {
      mTrainingCard: MTrainingCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTrainingCards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
