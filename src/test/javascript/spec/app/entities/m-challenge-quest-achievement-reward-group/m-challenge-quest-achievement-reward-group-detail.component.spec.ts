/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestAchievementRewardGroupDetailComponent } from 'app/entities/m-challenge-quest-achievement-reward-group/m-challenge-quest-achievement-reward-group-detail.component';
import { MChallengeQuestAchievementRewardGroup } from 'app/shared/model/m-challenge-quest-achievement-reward-group.model';

describe('Component Tests', () => {
  describe('MChallengeQuestAchievementRewardGroup Management Detail Component', () => {
    let comp: MChallengeQuestAchievementRewardGroupDetailComponent;
    let fixture: ComponentFixture<MChallengeQuestAchievementRewardGroupDetailComponent>;
    const route = ({
      data: of({ mChallengeQuestAchievementRewardGroup: new MChallengeQuestAchievementRewardGroup(123) })
    } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestAchievementRewardGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MChallengeQuestAchievementRewardGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MChallengeQuestAchievementRewardGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mChallengeQuestAchievementRewardGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
