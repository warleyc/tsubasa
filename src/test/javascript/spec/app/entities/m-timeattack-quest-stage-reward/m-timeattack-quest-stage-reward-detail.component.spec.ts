/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackQuestStageRewardDetailComponent } from 'app/entities/m-timeattack-quest-stage-reward/m-timeattack-quest-stage-reward-detail.component';
import { MTimeattackQuestStageReward } from 'app/shared/model/m-timeattack-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MTimeattackQuestStageReward Management Detail Component', () => {
    let comp: MTimeattackQuestStageRewardDetailComponent;
    let fixture: ComponentFixture<MTimeattackQuestStageRewardDetailComponent>;
    const route = ({ data: of({ mTimeattackQuestStageReward: new MTimeattackQuestStageReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackQuestStageRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTimeattackQuestStageRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTimeattackQuestStageRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTimeattackQuestStageReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
