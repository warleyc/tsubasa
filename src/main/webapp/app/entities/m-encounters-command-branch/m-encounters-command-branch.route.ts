import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MEncountersCommandBranch } from 'app/shared/model/m-encounters-command-branch.model';
import { MEncountersCommandBranchService } from './m-encounters-command-branch.service';
import { MEncountersCommandBranchComponent } from './m-encounters-command-branch.component';
import { MEncountersCommandBranchDetailComponent } from './m-encounters-command-branch-detail.component';
import { MEncountersCommandBranchUpdateComponent } from './m-encounters-command-branch-update.component';
import { MEncountersCommandBranchDeletePopupComponent } from './m-encounters-command-branch-delete-dialog.component';
import { IMEncountersCommandBranch } from 'app/shared/model/m-encounters-command-branch.model';

@Injectable({ providedIn: 'root' })
export class MEncountersCommandBranchResolve implements Resolve<IMEncountersCommandBranch> {
  constructor(private service: MEncountersCommandBranchService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMEncountersCommandBranch> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MEncountersCommandBranch>) => response.ok),
        map((mEncountersCommandBranch: HttpResponse<MEncountersCommandBranch>) => mEncountersCommandBranch.body)
      );
    }
    return of(new MEncountersCommandBranch());
  }
}

export const mEncountersCommandBranchRoute: Routes = [
  {
    path: '',
    component: MEncountersCommandBranchComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MEncountersCommandBranches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MEncountersCommandBranchDetailComponent,
    resolve: {
      mEncountersCommandBranch: MEncountersCommandBranchResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersCommandBranches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MEncountersCommandBranchUpdateComponent,
    resolve: {
      mEncountersCommandBranch: MEncountersCommandBranchResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersCommandBranches'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MEncountersCommandBranchUpdateComponent,
    resolve: {
      mEncountersCommandBranch: MEncountersCommandBranchResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersCommandBranches'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mEncountersCommandBranchPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MEncountersCommandBranchDeletePopupComponent,
    resolve: {
      mEncountersCommandBranch: MEncountersCommandBranchResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MEncountersCommandBranches'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
