/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestGoalRewardDetailComponent } from 'app/entities/m-quest-goal-reward/m-quest-goal-reward-detail.component';
import { MQuestGoalReward } from 'app/shared/model/m-quest-goal-reward.model';

describe('Component Tests', () => {
  describe('MQuestGoalReward Management Detail Component', () => {
    let comp: MQuestGoalRewardDetailComponent;
    let fixture: ComponentFixture<MQuestGoalRewardDetailComponent>;
    const route = ({ data: of({ mQuestGoalReward: new MQuestGoalReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestGoalRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestGoalRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestGoalRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestGoalReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
