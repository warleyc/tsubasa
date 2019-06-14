import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MSceneTutorialMessage } from 'app/shared/model/m-scene-tutorial-message.model';
import { MSceneTutorialMessageService } from './m-scene-tutorial-message.service';
import { MSceneTutorialMessageComponent } from './m-scene-tutorial-message.component';
import { MSceneTutorialMessageDetailComponent } from './m-scene-tutorial-message-detail.component';
import { MSceneTutorialMessageUpdateComponent } from './m-scene-tutorial-message-update.component';
import { MSceneTutorialMessageDeletePopupComponent } from './m-scene-tutorial-message-delete-dialog.component';
import { IMSceneTutorialMessage } from 'app/shared/model/m-scene-tutorial-message.model';

@Injectable({ providedIn: 'root' })
export class MSceneTutorialMessageResolve implements Resolve<IMSceneTutorialMessage> {
  constructor(private service: MSceneTutorialMessageService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMSceneTutorialMessage> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MSceneTutorialMessage>) => response.ok),
        map((mSceneTutorialMessage: HttpResponse<MSceneTutorialMessage>) => mSceneTutorialMessage.body)
      );
    }
    return of(new MSceneTutorialMessage());
  }
}

export const mSceneTutorialMessageRoute: Routes = [
  {
    path: '',
    component: MSceneTutorialMessageComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MSceneTutorialMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MSceneTutorialMessageDetailComponent,
    resolve: {
      mSceneTutorialMessage: MSceneTutorialMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSceneTutorialMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MSceneTutorialMessageUpdateComponent,
    resolve: {
      mSceneTutorialMessage: MSceneTutorialMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSceneTutorialMessages'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MSceneTutorialMessageUpdateComponent,
    resolve: {
      mSceneTutorialMessage: MSceneTutorialMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSceneTutorialMessages'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mSceneTutorialMessagePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MSceneTutorialMessageDeletePopupComponent,
    resolve: {
      mSceneTutorialMessage: MSceneTutorialMessageResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MSceneTutorialMessages'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
