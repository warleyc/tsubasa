import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MChallengeQuestWorld } from 'app/shared/model/m-challenge-quest-world.model';
import { MChallengeQuestWorldService } from './m-challenge-quest-world.service';
import { MChallengeQuestWorldComponent } from './m-challenge-quest-world.component';
import { MChallengeQuestWorldDetailComponent } from './m-challenge-quest-world-detail.component';
import { MChallengeQuestWorldUpdateComponent } from './m-challenge-quest-world-update.component';
import { MChallengeQuestWorldDeletePopupComponent } from './m-challenge-quest-world-delete-dialog.component';
import { IMChallengeQuestWorld } from 'app/shared/model/m-challenge-quest-world.model';

@Injectable({ providedIn: 'root' })
export class MChallengeQuestWorldResolve implements Resolve<IMChallengeQuestWorld> {
  constructor(private service: MChallengeQuestWorldService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMChallengeQuestWorld> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MChallengeQuestWorld>) => response.ok),
        map((mChallengeQuestWorld: HttpResponse<MChallengeQuestWorld>) => mChallengeQuestWorld.body)
      );
    }
    return of(new MChallengeQuestWorld());
  }
}

export const mChallengeQuestWorldRoute: Routes = [
  {
    path: '',
    component: MChallengeQuestWorldComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MChallengeQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MChallengeQuestWorldDetailComponent,
    resolve: {
      mChallengeQuestWorld: MChallengeQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MChallengeQuestWorldUpdateComponent,
    resolve: {
      mChallengeQuestWorld: MChallengeQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MChallengeQuestWorldUpdateComponent,
    resolve: {
      mChallengeQuestWorld: MChallengeQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestWorlds'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mChallengeQuestWorldPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MChallengeQuestWorldDeletePopupComponent,
    resolve: {
      mChallengeQuestWorld: MChallengeQuestWorldResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MChallengeQuestWorlds'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
