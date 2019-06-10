/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestStageRewardDetailComponent } from 'app/entities/m-challenge-quest-stage-reward/m-challenge-quest-stage-reward-detail.component';
import { MChallengeQuestStageReward } from 'app/shared/model/m-challenge-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MChallengeQuestStageReward Management Detail Component', () => {
    let comp: MChallengeQuestStageRewardDetailComponent;
    let fixture: ComponentFixture<MChallengeQuestStageRewardDetailComponent>;
    const route = ({ data: of({ mChallengeQuestStageReward: new MChallengeQuestStageReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestStageRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MChallengeQuestStageRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MChallengeQuestStageRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mChallengeQuestStageReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
