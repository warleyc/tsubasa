import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MTicketQuestStage } from 'app/shared/model/m-ticket-quest-stage.model';
import { MTicketQuestStageService } from './m-ticket-quest-stage.service';
import { MTicketQuestStageComponent } from './m-ticket-quest-stage.component';
import { MTicketQuestStageDetailComponent } from './m-ticket-quest-stage-detail.component';
import { MTicketQuestStageUpdateComponent } from './m-ticket-quest-stage-update.component';
import { MTicketQuestStageDeletePopupComponent } from './m-ticket-quest-stage-delete-dialog.component';
import { IMTicketQuestStage } from 'app/shared/model/m-ticket-quest-stage.model';

@Injectable({ providedIn: 'root' })
export class MTicketQuestStageResolve implements Resolve<IMTicketQuestStage> {
  constructor(private service: MTicketQuestStageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMTicketQuestStage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MTicketQuestStage>) => response.ok),
        map((mTicketQuestStage: HttpResponse<MTicketQuestStage>) => mTicketQuestStage.body)
      );
    }
    return of(new MTicketQuestStage());
  }
}

export const mTicketQuestStageRoute: Routes = [
  {
    path: '',
    component: MTicketQuestStageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MTicketQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MTicketQuestStageDetailComponent,
    resolve: {
      mTicketQuestStage: MTicketQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MTicketQuestStageUpdateComponent,
    resolve: {
      mTicketQuestStage: MTicketQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestStages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MTicketQuestStageUpdateComponent,
    resolve: {
      mTicketQuestStage: MTicketQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestStages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mTicketQuestStagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MTicketQuestStageDeletePopupComponent,
    resolve: {
      mTicketQuestStage: MTicketQuestStageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MTicketQuestStages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
