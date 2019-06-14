/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuerillaQuestStageRewardDetailComponent } from 'app/entities/m-guerilla-quest-stage-reward/m-guerilla-quest-stage-reward-detail.component';
import { MGuerillaQuestStageReward } from 'app/shared/model/m-guerilla-quest-stage-reward.model';

describe('Component Tests', () => {
  describe('MGuerillaQuestStageReward Management Detail Component', () => {
    let comp: MGuerillaQuestStageRewardDetailComponent;
    let fixture: ComponentFixture<MGuerillaQuestStageRewardDetailComponent>;
    const route = ({ data: of({ mGuerillaQuestStageReward: new MGuerillaQuestStageReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuerillaQuestStageRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGuerillaQuestStageRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuerillaQuestStageRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGuerillaQuestStageReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
