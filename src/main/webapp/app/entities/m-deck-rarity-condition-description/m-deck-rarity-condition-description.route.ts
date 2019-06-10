import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MDeckRarityConditionDescription } from 'app/shared/model/m-deck-rarity-condition-description.model';
import { MDeckRarityConditionDescriptionService } from './m-deck-rarity-condition-description.service';
import { MDeckRarityConditionDescriptionComponent } from './m-deck-rarity-condition-description.component';
import { MDeckRarityConditionDescriptionDetailComponent } from './m-deck-rarity-condition-description-detail.component';
import { MDeckRarityConditionDescriptionUpdateComponent } from './m-deck-rarity-condition-description-update.component';
import { MDeckRarityConditionDescriptionDeletePopupComponent } from './m-deck-rarity-condition-description-delete-dialog.component';
import { IMDeckRarityConditionDescription } from 'app/shared/model/m-deck-rarity-condition-description.model';

@Injectable({ providedIn: 'root' })
export class MDeckRarityConditionDescriptionResolve implements Resolve<IMDeckRarityConditionDescription> {
  constructor(private service: MDeckRarityConditionDescriptionService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMDeckRarityConditionDescription> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MDeckRarityConditionDescription>) => response.ok),
        map((mDeckRarityConditionDescription: HttpResponse<MDeckRarityConditionDescription>) => mDeckRarityConditionDescription.body)
      );
    }
    return of(new MDeckRarityConditionDescription());
  }
}

export const mDeckRarityConditionDescriptionRoute: Routes = [
  {
    path: '',
    component: MDeckRarityConditionDescriptionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MDeckRarityConditionDescriptions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MDeckRarityConditionDescriptionDetailComponent,
    resolve: {
      mDeckRarityConditionDescription: MDeckRarityConditionDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDeckRarityConditionDescriptions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MDeckRarityConditionDescriptionUpdateComponent,
    resolve: {
      mDeckRarityConditionDescription: MDeckRarityConditionDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDeckRarityConditionDescriptions'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MDeckRarityConditionDescriptionUpdateComponent,
    resolve: {
      mDeckRarityConditionDescription: MDeckRarityConditionDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDeckRarityConditionDescriptions'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mDeckRarityConditionDescriptionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MDeckRarityConditionDescriptionDeletePopupComponent,
    resolve: {
      mDeckRarityConditionDescription: MDeckRarityConditionDescriptionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MDeckRarityConditionDescriptions'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
