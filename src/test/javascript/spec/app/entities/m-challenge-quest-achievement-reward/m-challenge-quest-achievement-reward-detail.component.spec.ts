/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestAchievementRewardDetailComponent } from 'app/entities/m-challenge-quest-achievement-reward/m-challenge-quest-achievement-reward-detail.component';
import { MChallengeQuestAchievementReward } from 'app/shared/model/m-challenge-quest-achievement-reward.model';

describe('Component Tests', () => {
  describe('MChallengeQuestAchievementReward Management Detail Component', () => {
    let comp: MChallengeQuestAchievementRewardDetailComponent;
    let fixture: ComponentFixture<MChallengeQuestAchievementRewardDetailComponent>;
    const route = ({ data: of({ mChallengeQuestAchievementReward: new MChallengeQuestAchievementReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestAchievementRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MChallengeQuestAchievementRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MChallengeQuestAchievementRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mChallengeQuestAchievementReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
