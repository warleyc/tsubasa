/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestStageRewardDetailComponent } from 'app/entities/m-quest-stage-reward/m-quest-stage-reward-detail.component';
import { MQuestStageReward } from 'app/shared/model/m-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MQuestStageReward Management Detail Component', () => {
    let comp: MQuestStageRewardDetailComponent;
    let fixture: ComponentFixture<MQuestStageRewardDetailComponent>;
    const route = ({ data: of({ mQuestStageReward: new MQuestStageReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestStageRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestStageRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestStageRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestStageReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
