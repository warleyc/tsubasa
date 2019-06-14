import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MGachaTicket } from 'app/shared/model/m-gacha-ticket.model';
import { MGachaTicketService } from './m-gacha-ticket.service';
import { MGachaTicketComponent } from './m-gacha-ticket.component';
import { MGachaTicketDetailComponent } from './m-gacha-ticket-detail.component';
import { MGachaTicketUpdateComponent } from './m-gacha-ticket-update.component';
import { MGachaTicketDeletePopupComponent } from './m-gacha-ticket-delete-dialog.component';
import { IMGachaTicket } from 'app/shared/model/m-gacha-ticket.model';

@Injectable({ providedIn: 'root' })
export class MGachaTicketResolve implements Resolve<IMGachaTicket> {
  constructor(private service: MGachaTicketService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMGachaTicket> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MGachaTicket>) => response.ok),
        map((mGachaTicket: HttpResponse<MGachaTicket>) => mGachaTicket.body)
      );
    }
    return of(new MGachaTicket());
  }
}

export const mGachaTicketRoute: Routes = [
  {
    path: '',
    component: MGachaTicketComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MGachaTickets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MGachaTicketDetailComponent,
    resolve: {
      mGachaTicket: MGachaTicketResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaTickets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MGachaTicketUpdateComponent,
    resolve: {
      mGachaTicket: MGachaTicketResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaTickets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MGachaTicketUpdateComponent,
    resolve: {
      mGachaTicket: MGachaTicketResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaTickets'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mGachaTicketPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MGachaTicketDeletePopupComponent,
    resolve: {
      mGachaTicket: MGachaTicketResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MGachaTickets'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
