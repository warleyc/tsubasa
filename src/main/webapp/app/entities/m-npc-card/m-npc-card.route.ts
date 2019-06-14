import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MNpcCard } from 'app/shared/model/m-npc-card.model';
import { MNpcCardService } from './m-npc-card.service';
import { MNpcCardComponent } from './m-npc-card.component';
import { MNpcCardDetailComponent } from './m-npc-card-detail.component';
import { MNpcCardUpdateComponent } from './m-npc-card-update.component';
import { MNpcCardDeletePopupComponent } from './m-npc-card-delete-dialog.component';
import { IMNpcCard } from 'app/shared/model/m-npc-card.model';

@Injectable({ providedIn: 'root' })
export class MNpcCardResolve implements Resolve<IMNpcCard> {
  constructor(private service: MNpcCardService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMNpcCard> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MNpcCard>) => response.ok),
        map((mNpcCard: HttpResponse<MNpcCard>) => mNpcCard.body)
      );
    }
    return of(new MNpcCard());
  }
}

export const mNpcCardRoute: Routes = [
  {
    path: '',
    component: MNpcCardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MNpcCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MNpcCardDetailComponent,
    resolve: {
      mNpcCard: MNpcCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNpcCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MNpcCardUpdateComponent,
    resolve: {
      mNpcCard: MNpcCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNpcCards'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MNpcCardUpdateComponent,
    resolve: {
      mNpcCard: MNpcCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNpcCards'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mNpcCardPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MNpcCardDeletePopupComponent,
    resolve: {
      mNpcCard: MNpcCardResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNpcCards'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
