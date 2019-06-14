/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestStageConditionDetailComponent } from 'app/entities/m-quest-stage-condition/m-quest-stage-condition-detail.component';
import { MQuestStageCondition } from 'app/shared/model/m-quest-stage-condition.model';

describe('Component Tests', () => {
  describe('MQuestStageCondition Management Detail Component', () => {
    let comp: MQuestStageConditionDetailComponent;
    let fixture: ComponentFixture<MQuestStageConditionDetailComponent>;
    const route = ({ data: of({ mQuestStageCondition: new MQuestStageCondition(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestStageConditionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MQuestStageConditionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MQuestStageConditionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mQuestStageCondition).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
