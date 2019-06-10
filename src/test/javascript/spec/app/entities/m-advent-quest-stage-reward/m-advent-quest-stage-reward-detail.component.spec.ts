/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAdventQuestStageRewardDetailComponent } from 'app/entities/m-advent-quest-stage-reward/m-advent-quest-stage-reward-detail.component';
import { MAdventQuestStageReward } from 'app/shared/model/m-advent-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MAdventQuestStageReward Management Detail Component', () => {
    let comp: MAdventQuestStageRewardDetailComponent;
    let fixture: ComponentFixture<MAdventQuestStageRewardDetailComponent>;
    const route = ({ data: of({ mAdventQuestStageReward: new MAdventQuestStageReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAdventQuestStageRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MAdventQuestStageRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MAdventQuestStageRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mAdventQuestStageReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
