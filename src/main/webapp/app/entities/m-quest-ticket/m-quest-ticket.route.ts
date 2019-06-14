import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MQuestTicket } from 'app/shared/model/m-quest-ticket.model';
import { MQuestTicketService } from './m-quest-ticket.service';
import { MQuestTicketComponent } from './m-quest-ticket.component';
import { MQuestTicketDetailComponent } from './m-quest-ticket-detail.component';
import { MQuestTicketUpdateComponent } from './m-quest-ticket-update.component';
import { MQuestTicketDeletePopupComponent } from './m-quest-ticket-delete-dialog.component';
import { IMQuestTicket } from 'app/shared/model/m-quest-ticket.model';

@Injectable({ providedIn: 'root' })
export class MQuestTicketResolve implements Resolve<IMQuestTicket> {
  constructor(private service: MQuestTicketService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMQuestTicket> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MQuestTicket>) => response.ok),
        map((mQuestTicket: HttpResponse<MQuestTicket>) => mQuestTicket.body)
      );
    }
    return of(new MQuestTicket());
  }
}

export const mQuestTicketRoute: Routes = [
  {
    path: '',
    component: MQuestTicketComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MQuestTickets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MQuestTicketDetailComponent,
    resolve: {
      mQuestTicket: MQuestTicketResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestTickets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MQuestTicketUpdateComponent,
    resolve: {
      mQuestTicket: MQuestTicketResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestTickets'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MQuestTicketUpdateComponent,
    resolve: {
      mQuestTicket: MQuestTicketResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestTickets'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mQuestTicketPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MQuestTicketDeletePopupComponent,
    resolve: {
      mQuestTicket: MQuestTicketResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MQuestTickets'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
