/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MWeeklyQuestStageRewardDetailComponent } from 'app/entities/m-weekly-quest-stage-reward/m-weekly-quest-stage-reward-detail.component';
import { MWeeklyQuestStageReward } from 'app/shared/model/m-weekly-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MWeeklyQuestStageReward Management Detail Component', () => {
    let comp: MWeeklyQuestStageRewardDetailComponent;
    let fixture: ComponentFixture<MWeeklyQuestStageRewardDetailComponent>;
    const route = ({ data: of({ mWeeklyQuestStageReward: new MWeeklyQuestStageReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MWeeklyQuestStageRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MWeeklyQuestStageRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MWeeklyQuestStageRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mWeeklyQuestStageReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
