import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MInitUserDeck } from 'app/shared/model/m-init-user-deck.model';
import { MInitUserDeckService } from './m-init-user-deck.service';
import { MInitUserDeckComponent } from './m-init-user-deck.component';
import { MInitUserDeckDetailComponent } from './m-init-user-deck-detail.component';
import { MInitUserDeckUpdateComponent } from './m-init-user-deck-update.component';
import { MInitUserDeckDeletePopupComponent } from './m-init-user-deck-delete-dialog.component';
import { IMInitUserDeck } from 'app/shared/model/m-init-user-deck.model';

@Injectable({ providedIn: 'root' })
export class MInitUserDeckResolve implements Resolve<IMInitUserDeck> {
  constructor(private service: MInitUserDeckService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMInitUserDeck> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MInitUserDeck>) => response.ok),
        map((mInitUserDeck: HttpResponse<MInitUserDeck>) => mInitUserDeck.body)
      );
    }
    return of(new MInitUserDeck());
  }
}

export const mInitUserDeckRoute: Routes = [
  {
    path: '',
    component: MInitUserDeckComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MInitUserDecks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MInitUserDeckDetailComponent,
    resolve: {
      mInitUserDeck: MInitUserDeckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MInitUserDecks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MInitUserDeckUpdateComponent,
    resolve: {
      mInitUserDeck: MInitUserDeckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MInitUserDecks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MInitUserDeckUpdateComponent,
    resolve: {
      mInitUserDeck: MInitUserDeckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MInitUserDecks'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mInitUserDeckPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MInitUserDeckDeletePopupComponent,
    resolve: {
      mInitUserDeck: MInitUserDeckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MInitUserDecks'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
