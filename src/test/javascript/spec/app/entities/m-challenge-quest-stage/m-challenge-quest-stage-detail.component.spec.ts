/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MChallengeQuestStageDetailComponent } from 'app/entities/m-challenge-quest-stage/m-challenge-quest-stage-detail.component';
import { MChallengeQuestStage } from 'app/shared/model/m-challenge-quest-stage.model';

describe('Component Tests', () => {
  describe('MChallengeQuestStage Management Detail Component', () => {
    let comp: MChallengeQuestStageDetailComponent;
    let fixture: ComponentFixture<MChallengeQuestStageDetailComponent>;
    const route = ({ data: of({ mChallengeQuestStage: new MChallengeQuestStage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MChallengeQuestStageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MChallengeQuestStageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MChallengeQuestStageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mChallengeQuestStage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
