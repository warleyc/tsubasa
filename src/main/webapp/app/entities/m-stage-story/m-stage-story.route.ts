import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MStageStory } from 'app/shared/model/m-stage-story.model';
import { MStageStoryService } from './m-stage-story.service';
import { MStageStoryComponent } from './m-stage-story.component';
import { MStageStoryDetailComponent } from './m-stage-story-detail.component';
import { MStageStoryUpdateComponent } from './m-stage-story-update.component';
import { MStageStoryDeletePopupComponent } from './m-stage-story-delete-dialog.component';
import { IMStageStory } from 'app/shared/model/m-stage-story.model';

@Injectable({ providedIn: 'root' })
export class MStageStoryResolve implements Resolve<IMStageStory> {
  constructor(private service: MStageStoryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMStageStory> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MStageStory>) => response.ok),
        map((mStageStory: HttpResponse<MStageStory>) => mStageStory.body)
      );
    }
    return of(new MStageStory());
  }
}

export const mStageStoryRoute: Routes = [
  {
    path: '',
    component: MStageStoryComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'MStageStories'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MStageStoryDetailComponent,
    resolve: {
      mStageStory: MStageStoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStageStories'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MStageStoryUpdateComponent,
    resolve: {
      mStageStory: MStageStoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStageStories'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MStageStoryUpdateComponent,
    resolve: {
      mStageStory: MStageStoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStageStories'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mStageStoryPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MStageStoryDeletePopupComponent,
    resolve: {
      mStageStory: MStageStoryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MStageStories'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
