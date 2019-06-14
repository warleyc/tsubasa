import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MRegulationMatchTutorialMessage } from 'app/shared/model/m-regulation-match-tutorial-message.model';
import { MRegulationMatchTutorialMessageService } from './m-regulation-match-tutorial-message.service';
import { MRegulationMatchTutorialMessageComponent } from './m-regulation-match-tutorial-message.component';
import { MRegulationMatchTutorialMessageDetailComponent } from './m-regulation-match-tutorial-message-detail.component';
import { MRegulationMatchTutorialMessageUpdateComponent } from './m-regulation-match-tutorial-message-update.component';
import { MRegulationMatchTutorialMessageDeletePopupComponent } from './m-regulation-match-tutorial-message-delete-dialog.component';
import { IMRegulationMatchTutorialMessage } from 'app/shared/model/m-regulation-match-tutorial-message.model';

@Injectable({ providedIn: 'root' })
export class MRegulationMatchTutorialMessageResolve implements Resolve<IMRegulationMatchTutorialMessage> {
  constructor(private service: MRegulationMatchTutorialMessageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMRegulationMatchTutorialMessage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MRegulationMatchTutorialMessage>) => response.ok),
        map((mRegulationMatchTutorialMessage: HttpResponse<MRegulationMatchTutorialMessage>) => mRegulationMatchTutorialMessage.body)
      );
    }
    return of(new MRegulationMatchTutorialMessage());
  }
}

export const mRegulationMatchTutorialMessageRoute: Routes = [
  {
    path: '',
    component: MRegulationMatchTutorialMessageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MRegulationMatchTutorialMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MRegulationMatchTutorialMessageDetailComponent,
    resolve: {
      mRegulationMatchTutorialMessage: MRegulationMatchTutorialMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRegulationMatchTutorialMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MRegulationMatchTutorialMessageUpdateComponent,
    resolve: {
      mRegulationMatchTutorialMessage: MRegulationMatchTutorialMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRegulationMatchTutorialMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MRegulationMatchTutorialMessageUpdateComponent,
    resolve: {
      mRegulationMatchTutorialMessage: MRegulationMatchTutorialMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRegulationMatchTutorialMessages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mRegulationMatchTutorialMessagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MRegulationMatchTutorialMessageDeletePopupComponent,
    resolve: {
      mRegulationMatchTutorialMessage: MRegulationMatchTutorialMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MRegulationMatchTutorialMessages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
