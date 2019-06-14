import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MNpcDeck } from 'app/shared/model/m-npc-deck.model';
import { MNpcDeckService } from './m-npc-deck.service';
import { MNpcDeckComponent } from './m-npc-deck.component';
import { MNpcDeckDetailComponent } from './m-npc-deck-detail.component';
import { MNpcDeckUpdateComponent } from './m-npc-deck-update.component';
import { MNpcDeckDeletePopupComponent } from './m-npc-deck-delete-dialog.component';
import { IMNpcDeck } from 'app/shared/model/m-npc-deck.model';

@Injectable({ providedIn: 'root' })
export class MNpcDeckResolve implements Resolve<IMNpcDeck> {
  constructor(private service: MNpcDeckService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMNpcDeck> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MNpcDeck>) => response.ok),
        map((mNpcDeck: HttpResponse<MNpcDeck>) => mNpcDeck.body)
      );
    }
    return of(new MNpcDeck());
  }
}

export const mNpcDeckRoute: Routes = [
  {
    path: '',
    component: MNpcDeckComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MNpcDecks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MNpcDeckDetailComponent,
    resolve: {
      mNpcDeck: MNpcDeckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNpcDecks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MNpcDeckUpdateComponent,
    resolve: {
      mNpcDeck: MNpcDeckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNpcDecks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MNpcDeckUpdateComponent,
    resolve: {
      mNpcDeck: MNpcDeckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNpcDecks'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mNpcDeckPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MNpcDeckDeletePopupComponent,
    resolve: {
      mNpcDeck: MNpcDeckResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MNpcDecks'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
