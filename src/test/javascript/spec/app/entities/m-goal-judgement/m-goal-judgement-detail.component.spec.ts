/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGoalJudgementDetailComponent } from 'app/entities/m-goal-judgement/m-goal-judgement-detail.component';
import { MGoalJudgement } from 'app/shared/model/m-goal-judgement.model';

describe('Component Tests', () => {
  describe('MGoalJudgement Management Detail Component', () => {
    let comp: MGoalJudgementDetailComponent;
    let fixture: ComponentFixture<MGoalJudgementDetailComponent>;
    const route = ({ data: of({ mGoalJudgement: new MGoalJudgement(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGoalJudgementDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGoalJudgementDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGoalJudgementDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGoalJudgement).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
