/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMarathonQuestStageRewardDetailComponent } from 'app/entities/m-marathon-quest-stage-reward/m-marathon-quest-stage-reward-detail.component';
import { MMarathonQuestStageReward } from 'app/shared/model/m-marathon-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MMarathonQuestStageReward Management Detail Component', () => {
    let comp: MMarathonQuestStageRewardDetailComponent;
    let fixture: ComponentFixture<MMarathonQuestStageRewardDetailComponent>;
    const route = ({ data: of({ mMarathonQuestStageReward: new MMarathonQuestStageReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMarathonQuestStageRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MMarathonQuestStageRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MMarathonQuestStageRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mMarathonQuestStageReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
