/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLuckWeeklyQuestStageRewardDetailComponent } from 'app/entities/m-luck-weekly-quest-stage-reward/m-luck-weekly-quest-stage-reward-detail.component';
import { MLuckWeeklyQuestStageReward } from 'app/shared/model/m-luck-weekly-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MLuckWeeklyQuestStageReward Management Detail Component', () => {
    let comp: MLuckWeeklyQuestStageRewardDetailComponent;
    let fixture: ComponentFixture<MLuckWeeklyQuestStageRewardDetailComponent>;
    const route = ({ data: of({ mLuckWeeklyQuestStageReward: new MLuckWeeklyQuestStageReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLuckWeeklyQuestStageRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLuckWeeklyQuestStageRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLuckWeeklyQuestStageRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLuckWeeklyQuestStageReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
