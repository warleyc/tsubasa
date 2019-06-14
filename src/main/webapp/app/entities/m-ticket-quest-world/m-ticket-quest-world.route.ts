import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTicketQuestWorld } from 'app/shared/model/m-ticket-quest-world.model';
import { MTicketQuestWorldService } from './m-ticket-quest-world.service';
import { MTicketQuestWorldComponent } from './m-ticket-quest-world.component';
import { MTicketQuestWorldDetailComponent } from './m-ticket-quest-world-detail.component';
import { MTicketQuestWorldUpdateComponent } from './m-ticket-quest-world-update.component';
import { MTicketQuestWorldDeletePopupComponent } from './m-ticket-quest-world-delete-dialog.component';
import { IMTicketQuestWorld } from 'app/shared/model/m-ticket-quest-world.model';

@Injectable({ providedIn: 'root' })
export class MTicketQuestWorldResolve implements Resolve<IMTicketQuestWorld> {
  constructor(private service: MTicketQuestWorldService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTicketQuestWorld> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTicketQuestWorld>) => response.ok),
        map((mTicketQuestWorld: HttpResponse<MTicketQuestWorld>) => mTicketQuestWorld.body)
      );
    }
    return of(new MTicketQuestWorld());
  }
}

export const mTicketQuestWorldRoute: Routes = [
  {
    path: '',
    component: MTicketQuestWorldComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTicketQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTicketQuestWorldDetailComponent,
    resolve: {
      mTicketQuestWorld: MTicketQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTicketQuestWorldUpdateComponent,
    resolve: {
      mTicketQuestWorld: MTicketQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTicketQuestWorldUpdateComponent,
    resolve: {
      mTicketQuestWorld: MTicketQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTicketQuestWorldPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTicketQuestWorldDeletePopupComponent,
    resolve: {
      mTicketQuestWorld: MTicketQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestWorlds'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
